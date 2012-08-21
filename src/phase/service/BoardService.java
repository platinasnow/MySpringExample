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
	 * �Խ��� ����� ��ȯ�Ѵ�.
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
	 * �Խ����� �� �ۼ��� ��ȯ�Ѵ�.
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
	 * �Խ����� �������� ��ȯ�Ѵ�.
	 * 
	 * @param boardNum
	 * @return
	 */
	public Integer getSeqNum(Integer boardNum) {
		return boardDao.getSeqNum(boardNum);
	}

	/**
	 * �Խñ��� ����Ѵ�.
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
	 * �Խñ��� �����Ѵ�.
	 * 
	 * @param boardNum
	 * @param seq
	 */
	public void deleteBoardContent(Integer boardNum, Integer seq) {
		boardDao.deleteBoardContent(boardNum, seq);
	}

	/**
	 * �Խñ��� �����Ѵ�.
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
	 * �Խñ��� ��ȯ�Ѵ�.(<br/>�� \n���� ġȯ�Ѵ�.)
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
	 * �Խñ��� ��ȯ�Ѵ�.
	 * @param boardNum
	 * @param seq
	 * @return
	 */
	public BoardVO getBoardItem(Integer boardNum, Integer seq) {
		return boardDao.getBoardItem(boardNum, seq);
	}

	/**
	 * ���� �� �� �ִ� �������� üũ�Ѵ�. (�����ϸ� ���� EXIST�� ��ȯ)
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
	 * �Խ����� ���� ���� ��ȸ���� 1 �����Ѵ�.
	 * @param boardNum
	 * @param seq
	 */
	public void addCountBoardHit(Integer boardNum, Integer seq) {
		boardDao.addCountBoardHit(boardNum, seq);
	}

	/**
	 * ������ ����� �����Ѵ�.
	 * @param boardNum
	 * @param seq
	 * @return
	 */
	public List<ReplyVO> getReplyList(Integer boardNum, Integer seq) {
		return boardDao.getReplyList(boardNum, seq);
	}

	/**
	 * ������ �����Ѵ�.
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
	 * ������ �Ѽ��� �����Ѵ�.
	 * @param boardNum
	 * @param seq
	 * @param reply
	 */
	public void addReplyCount(Integer boardNum, Integer seq){
		boardDao.modifyReplyCount(boardNum, seq, 1);
	}
	
	/**
	 * ������ �Ѽ��� ���ҽ�Ų��.
	 * @param boardNum
	 * @param seq
	 */
	public void subjectReplyCount(Integer boardNum, Integer seq){
		boardDao.modifyReplyCount(boardNum, seq, -1);
	}
}
