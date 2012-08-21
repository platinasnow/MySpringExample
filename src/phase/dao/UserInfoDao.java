package phase.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;

import phase.model.UserInfoVO;

@Component
public class UserInfoDao {

	@Autowired
	private SqlMapClientTemplate sqlMapClientTemplate;
	
	public void insertAccount(UserInfoVO userInfo){
		sqlMapClientTemplate.insert("login.insertAccount",userInfo);
	}
	
	public void insertAuthority(UserInfoVO userInfo){
		sqlMapClientTemplate.insert("login.insertAuthority",userInfo);
	}
	public Integer getUserInfoCount(){
		return (Integer) sqlMapClientTemplate.queryForObject("login.getUserInfoCount");
	}
	
	public String validationUserId(String userId){
		return (String) sqlMapClientTemplate.queryForObject("login.validationUserId",userId);
	}
	
}	
