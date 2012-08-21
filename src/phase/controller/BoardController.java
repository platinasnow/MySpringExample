package phase.controller;

import java.util.List;

import oracle.sql.BLOB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.sun.org.apache.regexp.internal.recompile;

import phase.model.BoardVO;
import phase.service.AdminService;
import phase.service.BoardService;
import phase.util.Paging;

@Controller("boardController")
public class BoardController {

	@Autowired
	private BoardService boardService;

	@Autowired
	private AdminService adminService;

	@RequestMapping("board/boardList.do")
	public ModelAndView boardListPage(@RequestParam Integer boardNum,
			@RequestParam(defaultValue = "1") String page,
			@RequestParam(required = false) String search,
			@RequestParam(required = false) String searchData,
			@RequestParam(required = false) String preDate,
			@RequestParam(required = false) String postDate) {

		ModelAndView mav = new ModelAndView("board/boardList");
		int totalCount = boardService.getBoardTotalCount(page, search,
				searchData, preDate, postDate, String.valueOf(boardNum));
		Paging paging = new Paging(totalCount, 10, 10);
		paging.setPage(Integer.parseInt(page));
		mav.addObject("boardList", boardService.getBoardList(page, search,
				searchData, preDate, postDate, String.valueOf(boardNum)));
		mav.addObject("paging", paging);
		mav.addObject("boardNum", boardNum);
		return mav;
	}

	@RequestMapping("board/boardWrite.do")
	public ModelAndView boardWritePage(@RequestParam Integer boardNum) {
		ModelAndView mav = new ModelAndView("board/boardWrite");
		mav.addObject("boardNum", boardNum);
		return mav;
	}

	@RequestMapping(value = "board/boardWriteSubmit.do", method = RequestMethod.POST)
	public ModelAndView boardWriteSubmit(@RequestParam Integer boardNum,
			@RequestParam String title, @RequestParam String content,
			@RequestParam Integer principle,
			@RequestParam(required = false) byte[] fileupload) {
		int seq = boardService.getSeqNum(boardNum);
		String writer = adminService.getUserIdByPrincipal(principle);
		boardService.insertBoardContent(boardNum, seq, title, content,
				writer, fileupload, principle);
		return new ModelAndView(new RedirectView(
				"/board/boardList.do?boardNum=" + boardNum, true));
	}

	@RequestMapping("board/boardModify.do")
	public ModelAndView boardModifyPage(@RequestParam Integer boardNum,
			@RequestParam Integer seq, @RequestParam Integer principle) {
		ModelAndView mav = new ModelAndView("board/boardWrite");
		if ("EXIST".equals(boardService.checkModifableUser(boardNum, seq,
				principle))) {
			mav.addObject("boardItem", boardService.getBoardItemToReplaceBr(boardNum, seq));
			mav.addObject("boardType", "modify");
			mav.addObject("seq", seq);
			mav.addObject("boardNum", boardNum);
			return mav;
		} else {
			return new ModelAndView(new RedirectView(
					"/board/boardList.do?boardNum=" + boardNum, true));
		}
	}

	@RequestMapping(value = "board/boardModifySubmit.do", method = RequestMethod.POST)
	public ModelAndView boardModifySubmit(@RequestParam Integer boardNum,
			@RequestParam String title, @RequestParam String content,
			@RequestParam Integer seq,
			@RequestParam(required = false) byte[] fileupload) {

		boardService.modifyBoardContent(boardNum, seq, title, content,
				fileupload);
		return new ModelAndView(new RedirectView(
				"/board/boardList.do?boardNum=" + boardNum, true));
	}

	@RequestMapping(value = "board/deleteBoardItem.do", method = RequestMethod.POST)
	public ModelAndView deleteBoardItem(@RequestParam Integer boardNum,
			@RequestParam List<Integer> seq, @RequestParam Integer principle) {

		for (int idx = 0; idx < seq.size(); idx++) {
			if ("EXIST".equals(boardService.checkModifableUser(boardNum,
					seq.get(idx), principle))) {
				boardService.deleteBoardContent(boardNum, seq.get(idx));
			}
		}
		return new ModelAndView(new RedirectView(
				"/board/boardList.do?boardNum=" + boardNum, true));
	}

	@RequestMapping("board/boardView.do")
	public ModelAndView viewBoardItem(@RequestParam Integer boardNum,
			@RequestParam Integer b_seq) {
		ModelAndView mav = new ModelAndView("board/boardView");
		boardService.addCountBoardHit(boardNum, b_seq);
		mav.addObject("boardItem", boardService.getBoardItem(boardNum, b_seq));
		mav.addObject("replyList", boardService.getReplyList(boardNum, b_seq));
		return mav;
	}

	@RequestMapping(value = "board/replyBoard", method = RequestMethod.POST)
	public ModelAndView replyBoard(@RequestParam Integer boardNum,
			@RequestParam Integer seq, @RequestParam String content,
			@RequestParam Integer principle) {
		String userId = adminService.getUserIdByPrincipal(principle);
		boardService.insertReply(boardNum, seq, content, userId, principle);
		return new ModelAndView(new RedirectView(
				"/board/boardView.do?boardNum=" + boardNum + "&b_seq=" + seq,
				true));
	}

}
