package phase.model;

import java.util.Date;

public class UserInfoVO {

	private String userId;
	private String password;
	private String name;
	private String email;
	private Date indate;
	private Integer block;
	private Integer permit;
	private Integer principle;
	private String authority; // join½Ã

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getIndate() {
		return indate;
	}

	public void setIndate(Date indate) {
		this.indate = indate;
	}

	public Integer getBlock() {
		return block;
	}

	public void setBlock(Integer block) {
		this.block = block;
	}

	public Integer getPermit() {
		return permit;
	}

	public void setPermit(Integer permit) {
		this.permit = permit;
	}

	public Integer getPrinciple() {
		return principle;
	}

	public void setPrinciple(Integer principle) {
		this.principle = principle;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

}
