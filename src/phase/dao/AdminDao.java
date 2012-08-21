package phase.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;

import phase.model.UserAuthorityVO;
import phase.model.UserInfoVO;

@Component
public class AdminDao {

	@Autowired
	private SqlMapClientTemplate sqlMapClientTemplate;

	public List<UserInfoVO> getUserInfoList(String viewGroup, String page,
			String search, String searchData, String preDate, String postDate) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("viewGroup", viewGroup);
		map.put("page", page);
		map.put("search", search);
		map.put("searchData", searchData);
		map.put("preDate", preDate);
		map.put("postDate", postDate);
		return sqlMapClientTemplate.queryForList("admin.getUserInfoList", map);
	}

	public Integer getUserInfoTotalCount(String viewGroup, String search,
			String searchData, String preDate, String postDate) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("viewGroup", viewGroup);
		map.put("search", search);
		map.put("searchData", searchData);
		map.put("preDate", preDate);
		map.put("postDate", postDate);
		return (Integer) sqlMapClientTemplate.queryForObject(
				"admin.getUserInfoTotalCount", map);
	}

	public void joinAcception(String userId) {
		UserInfoVO userInfo = new UserInfoVO();
		userInfo.setUserId(userId);
		sqlMapClientTemplate.update("admin.joinAcception", userInfo);
	}

	public void blockUser(String userId) {
		UserInfoVO userInfo = new UserInfoVO();
		userInfo.setUserId(userId);
		sqlMapClientTemplate.update("admin.blockUser", userInfo);
	}

	public void unblockUser(String userId) {
		UserInfoVO userInfo = new UserInfoVO();
		userInfo.setUserId(userId);
		sqlMapClientTemplate.update("admin.unblockUser", userInfo);
	}

	public void changeAuthority(String userId, String authority) {
		UserAuthorityVO userAuth = new UserAuthorityVO();
		userAuth.setAuthority(authority);
		userAuth.setUserId(userId);
		sqlMapClientTemplate.update("admin.changeAuthority", userAuth);
	}

	public String getUserIdByPrincipal(Integer principal) {
		UserAuthorityVO userAuth = new UserAuthorityVO();
		userAuth.setPrinciple(principal);
		return (String) sqlMapClientTemplate.queryForObject(
				"admin.getUserIdByPrincipal", userAuth);
	}
	
	public String getUserAuthorityByUserId(String userId){
		UserAuthorityVO userAuth = new UserAuthorityVO();
		userAuth.setUserId(userId);
		return (String) sqlMapClientTemplate.queryForObject(
				"admin.getUserAuthorityByUserId", userAuth);
	}

}
