package phase.model;

import java.util.Date;

/**
 * @author Administrator
 *
 */
public class BoardVO {

	private Integer seq;
	private Integer boardNum;
	private String title;
	private String content;
	private boolean viewEnable;
	private byte[] fileData;
	private String writer;
	private Date indate;
	private String password;
	private Integer hit;
	private Integer principle;
	private Integer reply;

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public byte[] getFileData() {
		return fileData;
	}

	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}

	public Date getIndate() {
		return indate;
	}

	public void setIndate(Date indate) {
		this.indate = indate;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getHit() {
		return hit;
	}

	public void setHit(Integer hit) {
		this.hit = hit;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public Integer getPrinciple() {
		return principle;
	}

	public void setPrinciple(Integer principle) {
		this.principle = principle;
	}

	public Integer getReply() {
		return reply;
	}

	public void setReply(Integer reply) {
		this.reply = reply;
	}
	
}
