package net.kosta.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.kosta.myapp.vo.Car;
import com.kosta.myapp.vo.UserVO;

@Controller
public class MyController2 {
	
	Logger logger = LoggerFactory.getLogger(MyController2.class);
	
	
	@RequestMapping("/spring/test1.do")
	public String method1() {
		
		return "day0622/test1";  //WEB-INF/views/day0622/test1.jsp
	}
	
	//@ModelAttribute("user")==>default�̸��� ������ �� �ִ�. 
	//@ModelAttribute �̸��� �����Ǹ� view���� ���� UserVO userVO�� ���̻���ؾ��Ѵ�. 
	
	@RequestMapping(value = "/spring/test2.do", method = RequestMethod.POST)
	public String method2(
			@RequestParam String userid, 
			 String userpass, 
			@ModelAttribute("user") UserVO user,
			Model model,
			HttpServletRequest request,
			HttpSession session
			) {
		String path = request.getRealPath(".");
		String path2 = session.getServletContext().getRealPath(".");
		String url = request.getRequestURI();
		logger.info( path );
		logger.info( path2 );
		logger.info( url );
		session.setAttribute("user", user);
		logger.info(user.toString());
		System.out.println(userid);
		System.out.println(userpass);
		model.addAttribute("userid", userid);
		model.addAttribute("userpass", userpass);
		//model.addAttribute("user", user);
		return "day0622/test2";   
	}
	
	
	/*
	 * return 값
	 * ModelAndView, Model, String, void 
	 * String : view이름 , @ResponseBody와 같이사용하면 document에 출력할 문자
	 * ModelAndView : data + view이름 
	 * Model : 메서드 파라메터로 정의하고 값은 addAttribute()로 setting,view이름 String으로 return 
	 * void : 요청 URI로부터 맨 앞의 슬래시와 확장자를 제외한 나머지 부분을 뷰 이름으로 사용
	 */
	
	@RequestMapping("/day0622/test3.do")
	public void method3(Model model,HttpServletRequest request) {
		Map<String, ?> flashMap =   
		         RequestContextUtils.getInputFlashMap(request);
		if(flashMap !=null ) {
			String subject = (String)flashMap.get("subject");
			Car mycar = (Car)flashMap.get("car");
			System.out.println(subject);
			System.out.println(mycar);
			model.addAttribute("key",subject);
			model.addAttribute("car",mycar);
		}
		
		
		//defalut로 요청주소와 같은 이름의 jsp로 forward된다.
	}
	
	@RequestMapping("/day0622/test4.do")
	public String method4(Model model,RedirectAttributes redirectAttr) {
		
		redirectAttr.addFlashAttribute("subject","SpringFramework");
		redirectAttr.addFlashAttribute("car",new Car("카니발",600,"white"));
		
		model.addAttribute("key","value!!!!!!!!");
		
		//return "day0622/test4"; //default로 forward된다.
		return "redirect:/day0622/test3.do"; //주소창이 바뀐다.
	}

}
