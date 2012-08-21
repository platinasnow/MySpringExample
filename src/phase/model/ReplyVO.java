package phase.model;

import java.util.Date;

public class ReplyVO {

	private Integer seq;
	private Integer boardNum;
	private Integer principle;
	private String userId;
	private String content;
	private boolean viewEnable;
	private Date indate;
	private Integer rseq;

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Integer getBoardNum() {
		return boardNum;
	}

	public void setBoardNum(Integer boardNum) {
		this.boardNum = boardNum;
	}

	public Integer getPrinciple() {
		return principle;
	}

	public void setPrinciple(Integer principle) {
		this.principle = principle;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isViewEnable() {
		return viewEnable;
	}

	public void setViewEnable(boolean viewEnable) {
		this.viewEnable = viewEnable;
	}

	public Date getIndate() {
		return indate;
	}

	public void setIndate(Date indate) {
		indate = indate;
	}

	public Integer getRseq() {
		return rseq;
	}

	public void setRseq(Integer rseq) {
		this.rseq = rseq;
	}

}
