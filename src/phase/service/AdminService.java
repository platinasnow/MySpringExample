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
	 * ������ ������ ��ȯ�Ѵ�
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
	 * ������ �� ���� ��ȯ�Ѵ�.
	 * 
	 * @param viewGroup
	 *            ���/����/����
	 * @return
	 */
	public Integer getUserInfoTotalCount(String viewGroup, String search,
			String searchData, String preDate, String postDate) {
		return adminDao.getUserInfoTotalCount(viewGroup, search, searchData,
				preDate, postDate);
	}

	/**
	 * ������ ������ �����Ѵ�.
	 * 
	 * @param userId
	 */
	public void joinAcception(String userId) {
		adminDao.joinAcception(userId);
	}

	/**
	 * ������ �����Ѵ�.
	 * 
	 * @param userId
	 */
	public void blockUser(String userId) {
		adminDao.blockUser(userId);
	}

	/**
	 * ������ ������ �����Ѵ�.
	 * 
	 * @param userId
	 */
	public void unblockUser(String userId) {
		adminDao.unblockUser(userId);
	}

	/**
	 * ������ ������ �����Ѵ�.
	 * 
	 * @param userId
	 * @param authority
	 */
	public void changeAuthority(String userId, String authority) {
		adminDao.changeAuthority(userId, authority);
	}

	/**
	 * principal�� userid �� ��ȯ�Ѵ�.
	 * @param principal
	 * @return
	 */
	public String getUserIdByPrincipal(Integer principal) {
		return adminDao.getUserIdByPrincipal(principal);
	}
	
	/**
	 * principal �� ������ ��ȯ�Ѵ�.
	 * @param principal
	 * @return
	 */
	public String getUserAuthorityByUserId(String userId){
		return adminDao.getUserAuthorityByUserId(userId);
	}
}
