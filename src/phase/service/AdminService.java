package phase.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import phase.dao.AdminDao;
import phase.model.UserInfoVO;

@Service
public class AdminService {

	@Autowired
	private AdminDao adminDao;

	/**
	 * 유저의 정보를 반환한다
	 * 
	 * @param viewGroup
	 * @param page
	 * @return
	 */
	public List<UserInfoVO> getUserInfoList(String viewGroup, String page,
			String search, String searchData, String preDate, String postDate) {
		return adminDao.getUserInfoList(viewGroup, page, search, searchData,
				preDate, postDate);
	}

	/**
	 * 유저의 총 수를 반환한다.
	 * 
	 * @param viewGroup
	 *            모두/승인/차단
	 * @return
	 */
	public Integer getUserInfoTotalCount(String viewGroup, String search,
			String searchData, String preDate, String postDate) {
		return adminDao.getUserInfoTotalCount(viewGroup, search, searchData,
				preDate, postDate);
	}

	/**
	 * 유저의 가입을 승인한다.
	 * 
	 * @param userId
	 */
	public void joinAcception(String userId) {
		adminDao.joinAcception(userId);
	}

	/**
	 * 유저를 차단한다.
	 * 
	 * @param userId
	 */
	public void blockUser(String userId) {
		adminDao.blockUser(userId);
	}

	/**
	 * 유저의 차단을 해제한다.
	 * 
	 * @param userId
	 */
	public void unblockUser(String userId) {
		adminDao.unblockUser(userId);
	}

	/**
	 * 유저의 권한을 조정한다.
	 * 
	 * @param userId
	 * @param authority
	 */
	public void changeAuthority(String userId, String authority) {
		adminDao.changeAuthority(userId, authority);
	}

	/**
	 * principal로 userid 를 반환한다.
	 * @param principal
	 * @return
	 */
	public String getUserIdByPrincipal(Integer principal) {
		return adminDao.getUserIdByPrincipal(principal);
	}
	
	/**
	 * principal 로 권한을 반환한다.
	 * @param principal
	 * @return
	 */
	public String getUserAuthorityByUserId(String userId){
		return adminDao.getUserAuthorityByUserId(userId);
	}
}
