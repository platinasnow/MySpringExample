package phase.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Service;

import phase.dao.UserInfoDao;
import phase.model.UserInfoVO;

@Service
public class UserInfoService {

	@Autowired
	private UserInfoDao userInfoDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * ������ �����Ѵ�.
	 * @param userId
	 * @param password
	 * @param name
	 * @param email
	 */
	public void insertAccount(String userId, String password, String name,
			String email) {
		UserInfoVO userInfo = new UserInfoVO();
		userInfo.setName(name);
		userInfo.setPassword(passwordEncoder.encodePassword(password, null));
		userInfo.setEmail(email);
		userInfo.setUserId(userId);
		userInfo.setPrinciple(userInfoDao.getUserInfoCount() + 1);
		userInfoDao.insertAccount(userInfo);
		userInfoDao.insertAuthority(userInfo);
	}

	/**
	 * UserId�� �ߺ��� �˻��Ѵ�. �����ϸ� EXIST�� ��ȯ�ȴ�.
	 * @param userId
	 * @return
	 */
	public String validationUserId(String userId) {
		return userInfoDao.validationUserId(userId);
	}
}
