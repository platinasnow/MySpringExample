package phase.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;

import phase.model.BoardVO;
import phase.model.ReplyVO;

import com.ibatis.sqlmap.client.SqlMapClient;

@Component
public class BoardDao {

	@Autowired
	private SqlMapClientTemplate sqlMapClientTemplate;

	public List<BoardVO> getBoardListString(String page, String search,
			String searchData, String preDate, String postDate, String boardNum) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("page", page);
		map.put("search", search);
		map.put("searchData", searchData);
		map.put("preDate", preDate);
		map.put("postDate", postDate);
		map.put("boardNum", boardNum);
		return sqlMapClientTemplate.queryForList("board.getBoardList", map);
	}

	public Integer getBoardTotalCount(String page, String search,
			String searchData, String preDate, String postDate, String boardNum) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("page", page);
		map.put("search", search);
		map.put("searchData", searchData);
		map.put("preDate", preDate);
		map.put("postDate", postDate);
		map.put("boardNum", boardNum);
		return (Integer) sqlMapClientTemplate.queryForObject(
				"board.getBoardTotalCount", map);
	}

	public Integer getSeqNum(Integer boardNum) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("boardNum", boardNum);
		return (Integer) sqlMapClientTemplate.queryForObject("board.getSeqNum",
				map);
	}

	public void insertBoardContent(BoardVO boardVO) {
		sqlMapClientTemplate.insert("board.insertBoardContent", boardVO);
	}

	public void deleteBoardContent(Integer boardNum, Integer seq) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("boardNum", boardNum);
		map.put("seq", seq);
		sqlMapClientTemplate.update("board.deleteBoardContent", map);
	}

	public void modifyBoardContent(BoardVO boardVO) {
		sqlMapClientTemplate.update("board.modifyBoardContent", boardVO);
	}

	public BoardVO getBoardItem(Integer boardNum, Integer seq) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("boardNum", boardNum);
		map.put("seq", seq);
		return (BoardVO) sqlMapClientTemplate.queryForObject("board.getBoardItem",
				map);
	}

	public String checkModifableUser(Integer boardNum, Integer seq,
			Integer principle) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("boardNum", boardNum);
		map.put("seq", seq);
		map.put("principle", principle);
		return (String) sqlMapClientTemplate.queryForObject("board.checkModifableUser",map);
	}
	
	public void addCountBoardHit(Integer boardNum, Integer seq){
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("boardNum", boardNum);
		map.put("seq", seq);
		sqlMapClientTemplate.update("board.addCountBoardHit",map);
	}
	
	public List<ReplyVO> getReplyList (Integer boardNum, Integer seq){
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("boardNum", boardNum);
		map.put("seq", seq);
		return sqlMapClientTemplate.queryForList("board.getReplyList",map);
	}
	
	public void insertReply(ReplyVO replyVO){
		sqlMapClientTemplate.insert("board.insertReply",replyVO);
	}
	
	public void modifyReplyCount(Integer boardNum, Integer seq,Integer reply){
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("boardNum", boardNum);
		map.put("seq", seq);
		map.put("reply", reply);
		sqlMapClientTemplate.update("board.modifyReplyCount",map);
	}
}
