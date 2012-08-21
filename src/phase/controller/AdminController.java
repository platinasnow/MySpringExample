package phase.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import phase.model.UserInfoVO;
import phase.service.AdminService;
import phase.util.Paging;

@Controller("adminController")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@RequestMapping("admin/memberManage.do")
	public ModelAndView memberManagePage(
			@RequestParam(required = false) String viewGroup,
			@RequestParam(defaultValue = "1") String page,
			@RequestParam(required = false) String search,
			@RequestParam(required = false) String searchData,
			@RequestParam(required = false) String preDate,
			@RequestParam(required = false) String postDate) {

		ModelAndView mav = new ModelAndView("admin/memberManage");
		int totalCount = adminService.getUserInfoTotalCount(viewGroup, search,
				searchData, preDate, postDate);
		Paging paging = new Paging(totalCount, 10, 10);
		paging.setPage(Integer.parseInt(page));
		mav.addObject("userInfoList", adminService.getUserInfoList(viewGroup,
				page, search, searchData, preDate, postDate));
		mav.addObject("paging", paging);
		mav.addObject("viewGroup", viewGroup);
		return mav;
	}

	@RequestMapping("admin/joinAcception.do")
	public ModelAndView joinAcception(
			@RequestParam(required = false) String viewGroup,
			@RequestParam(required = false) List<String> checkBox) {
		if (checkBox != null) {
			for (int idx = 0; idx < checkBox.size(); idx++) {
				adminService.joinAcception(checkBox.get(idx));
			}
		}
		return new ModelAndView(new RedirectView(
				"/admin/memberManage.do?viewGroup=" + viewGroup, true));
	}

	@RequestMapping("admin/blockUser.do")
	public ModelAndView blockUser(
			@RequestParam(required = false) String viewGroup,
			@RequestParam(required = false) List<String> checkBox,
			@RequestParam String principal) {
		if (checkBox != null) {
			String adminId = adminService.getUserIdByPrincipal(Integer.parseInt(principal));
			for (int idx = 0; idx < checkBox.size(); idx++) {
				if (adminId.equals(checkBox.get(idx))) {// 자기 자신이 들어 있을때 패스
					// 에러메세지...
					System.out.println("자기자신");
				} else if ("ROLE_ADMIN".equals(adminService
						.getUserAuthorityByUserId(checkBox.get(idx)))) {
					// 운영자에게 차단명령을 내렸을 경우 에러메세지
					System.out.println("운영자");
				} else {
					adminService.blockUser(checkBox.get(idx));
				}
			}
		}
		return new ModelAndView(new RedirectView(
				"/admin/memberManage.do?viewGroup=" + viewGroup, true));
	}

	@RequestMapping("admin/unblockUser.do")
	public ModelAndView unblockUser(
			@RequestParam(required = false) String viewGroup,
			@RequestParam(required = false) List<String> checkBox) {
		if (checkBox != null) {
			for (int idx = 0; idx < checkBox.size(); idx++) {
				adminService.unblockUser(checkBox.get(idx));
			}
		}
		return new ModelAndView(new RedirectView(
				"/admin/memberManage.do?viewGroup=" + viewGroup, true));
	}

	@RequestMapping("admin/changeAuthorityAdmin.do")
	public ModelAndView changeAuthorityAdmin(
			@RequestParam(required = false) String viewGroup,
			@RequestParam(required = false) List<String> checkBox,
			@RequestParam String authority, @RequestParam String principal) {
		if (checkBox != null) {
			String adminId = adminService.getUserIdByPrincipal(Integer
					.parseInt(principal));
			if (adminId.equals(checkBox.get(0))) {// 자기 자신을 선택했을때 리턴
				return new ModelAndView(new RedirectView(
						"/admin/memberManage.do?viewGroup=" + viewGroup, true));
			} else {
				adminService.changeAuthority(checkBox.get(0), authority);
				adminService.changeAuthority(adminId, "ROLE_SUBADMIN");
			}
		}
		return new ModelAndView(new RedirectView("/logout.do", true));
	}

	@RequestMapping("admin/changeAuthority.do")
	public ModelAndView changeAuthority(
			@RequestParam(required = false) String viewGroup,
			@RequestParam(required = false) List<String> checkBox,
			@RequestParam String authority, @RequestParam String principal) {
		if (checkBox != null) {
			String adminId = adminService.getUserIdByPrincipal(Integer
					.parseInt(principal));
			if (adminId.equals(checkBox.get(0))) {// 자기 자신을 선택했을때 리턴
				return new ModelAndView(new RedirectView(
						"/admin/memberManage.do?viewGroup=" + viewGroup, true));
			} else {
				adminService.changeAuthority(checkBox.get(0), authority);
			}
		}
		return new ModelAndView(new RedirectView(
				"/admin/memberManage.do?viewGroup=" + viewGroup, true));
	}

}
