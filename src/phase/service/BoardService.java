package phase.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import phase.dao.BoardDao;
import phase.model.BoardVO;
import phase.model.ReplyVO;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;

	/**
	 * 게시판 목록을 반환한다.
	 * 
	 * @param page
	 * @param search
	 * @param searchData
	 * @param preDate
	 * @param postDate
	 * @return
	 */
	public List<BoardVO> getBoardList(String page, String search,
			String searchData, String preDate, String postDate, String boardNum) {
		return boardDao.getBoardListString(page, search, searchData, preDate,
				postDate, boardNum);
	}

	/**
	 * 게시판의 총 글수를 반환한다.
	 * 
	 * @param page
	 * @param search
	 * @param searchData
	 * @param preDate
	 * @param postDate
	 * @return
	 */
	public Integer getBoardTotalCount(String page, String search,
			String searchData, String preDate, String postDate, String boardNum) {
		return boardDao.getBoardTotalCount(page, search, searchData, preDate,
				postDate, boardNum);
	}

	/**
	 * 게시판의 시퀀스를 반환한다.
	 * 
	 * @param boardNum
	 * @return
	 */
	public Integer getSeqNum(Integer boardNum) {
		return boardDao.getSeqNum(boardNum);
	}

	/**
	 * 게시글을 등록한다.
	 * 
	 * @param boardVO
	 */
	public void insertBoardContent(Integer boardNum, Integer seq, String title,
			String content, String writer, byte[] fileData, Integer principle) {
		BoardVO boardVO = new BoardVO();
		boardVO.setBoardNum(boardNum);
		boardVO.setSeq(seq + 1);
		boardVO.setTitle(title);
		String tempContent = content.replace("\r\n", "<br/>");
		boardVO.setContent(tempContent);
		boardVO.setFileData(fileData);
		boardVO.setWriter(writer);
		boardVO.setPrinciple(principle);
		boardDao.insertBoardContent(boardVO);
	}

	/**
	 * 게시글을 삭제한다.
	 * 
	 * @param boardNum
	 * @param seq
	 */
	public void deleteBoardContent(Integer boardNum, Integer seq) {
		boardDao.deleteBoardContent(boardNum, seq);
	}

	/**
	 * 게시글을 수정한다.
	 * @param boardVO
	 */
	public void modifyBoardContent(Integer boardNum, Integer seq, String title,
			String content, byte[] fileData) {
		BoardVO boardVO = new BoardVO();
		boardVO.setBoardNum(boardNum);
		boardVO.setSeq(seq);
		boardVO.setTitle(title);
		String tempContent = content.replace("\r\n", "<br/>");
		boardVO.setContent(tempContent);
		boardVO.setFileData(fileData);
		boardDao.modifyBoardContent(boardVO);
	}

	/**
	 * 게시글을 반환한다.(<br/>을 \n으로 치환한다.)
	 * @param boardNum
	 * @param seq
	 * @return
	 */
	public BoardVO getBoardItemToReplaceBr(Integer boardNum, Integer seq) {
		BoardVO tempBoardVo = boardDao.getBoardItem(boardNum, seq);
		tempBoardVo.setContent(tempBoardVo.getContent().replace("<br/>", "\n"));
		return tempBoardVo;
	}
	
	/**
	 * 게시글을 반환한다.
	 * @param boardNum
	 * @param seq
	 * @return
	 */
	public BoardVO getBoardItem(Integer boardNum, Integer seq) {
		return boardDao.getBoardItem(boardNum, seq);
	}

	/**
	 * 수정 할 수 있는 유저인지 체크한다. (가능하면 문자 EXIST를 반환)
	 * @param boardNum
	 * @param seq
	 * @param principle
	 * @return
	 */
	public String checkModifableUser(Integer boardNum, Integer seq,
			Integer principle) {
		return boardDao.checkModifableUser(boardNum, seq, principle);
	}

	/**
	 * 게시판의 글을 보면 조회수가 1 증가한다.
	 * @param boardNum
	 * @param seq
	 */
	public void addCountBoardHit(Integer boardNum, Integer seq) {
		boardDao.addCountBoardHit(boardNum, seq);
	}

	/**
	 * 덧글의 목록을 리턴한다.
	 * @param boardNum
	 * @param seq
	 * @return
	 */
	public List<ReplyVO> getReplyList(Integer boardNum, Integer seq) {
		return boardDao.getReplyList(boardNum, seq);
	}

	/**
	 * 덧글을 삽입한다.
	 * @param boardNum
	 * @param seq
	 * @param content
	 * @param userId
	 * @param principle
	 */
	public void insertReply(Integer boardNum, Integer seq, String content,
			String userId, Integer principle) {
		ReplyVO replyVO = new ReplyVO();
		replyVO.setBoardNum(boardNum);
		replyVO.setSeq(seq);
		String tempContent = content.replace("\r\n", "<br/>");
		replyVO.setContent(tempContent);
		replyVO.setUserId(userId);
		replyVO.setPrinciple(principle);
		boardDao.insertReply(replyVO);
		addReplyCount(boardNum, seq);
	}
	
	/**
	 * 덧글의 총수를 증가한다.
	 * @param boardNum
	 * @param seq
	 * @param reply
	 */
	public void addReplyCount(Integer boardNum, Integer seq){
		boardDao.modifyReplyCount(boardNum, seq, 1);
	}
	
	/**
	 * 덧글의 총수를 감소시킨다.
	 * @param boardNum
	 * @param seq
	 */
	public void subjectReplyCount(Integer boardNum, Integer seq){
		boardDao.modifyReplyCount(boardNum, seq, -1);
	}
}
