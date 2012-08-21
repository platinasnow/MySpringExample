package phase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import phase.model.UserInfoVO;
import phase.service.UserInfoService;

@Controller("loginController")
public class LoginController {

	@Autowired
	private UserInfoService userInfoService;

	@RequestMapping("/index.do")
	public ModelAndView listPage() {
		ModelAndView mav = new ModelAndView("/index");
		return mav;
	}

	@RequestMapping("/loginPage.do")
	public ModelAndView indexPage(@RequestParam(required = false) String error) {
		ModelAndView mav = new ModelAndView("login/loginPage/noLayout");
		mav.addObject("errorCheck", error);
		return mav;
	}

	@RequestMapping("/logout.do")
	public String logoutPage() {
		return "login/logout/noLayout";
	}

	@RequestMapping("/accountPage.do")
	public ModelAndView accountPage() {
		ModelAndView mav = new ModelAndView("login/accountPage/noLayout");
		return mav;
	}

	@RequestMapping("/makeAccount.do")
	public ModelAndView makeAccount(@ModelAttribute UserInfoVO userInfo) {
		ModelAndView mav = new ModelAndView("login/accountPage/noLayout");
		System.out.println("userinfo :" + userInfo.getUserId());
		userInfoService
				.insertAccount(userInfo.getUserId(), userInfo.getPassword(),
						userInfo.getName(), userInfo.getEmail());
		return new ModelAndView(new RedirectView("loginPage.do", true));
	}

	@RequestMapping("/system/validationUserId.do")
	@ResponseBody
	public String validationUserId(String userId) {
		String validationValue = userInfoService.validationUserId(userId);
		if (validationValue == null) {
			return "EMPTY";
		} else {
			return validationValue;
		}
	}

}
