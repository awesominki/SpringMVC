package net.kosta.controller;

import java.sql.Date;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kosta.myapp.vo.Car;

@Controller
@RequestMapping("/hello")  //class level�ۼ� 
public class MyController {

	@RequestMapping("/my1")  //function level�ۼ� 
	public String test1(Model model) {
		model.addAttribute("major", "��ǻ�Ͱ���");
		model.addAttribute("phone", "010-1234-5678");
		model.addAttribute("car", new Car("ABC��", 1000, "white"));
		return "myjsp1";
	}
	
	@RequestMapping(value = {"/my2","/my3"}, method = RequestMethod.GET)
	public ModelAndView test2() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("major", "��ǻ�Ͱ���2");
		mv.addObject("phone", "010");
		mv.addObject("car", new Car("DDD��", 2000, "white"));
		mv.setViewName("myjsp1");
		return mv;
	}
	@RequestMapping(value = {"/my2", "/my3"}, method = RequestMethod.POST)
	public ModelAndView test3() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("major", "�������");
		mv.addObject("phone", "0104445555");
		mv.addObject("car", new Car("��������", 3000, "white"));
		mv.setViewName("myjsp1");
		return mv;
	}
	
	@ResponseBody //response.getWriter().append("aaaa")
	@RequestMapping(value = "/param.do",
			params = {"userid=hi","userpass","!email"},
			method = RequestMethod.GET
			)
	public String test4() {
		System.out.println("��û�� ����.......");
		return "paramResult";
	}
	
	@ResponseBody
	@RequestMapping("/param2.do")
	public String test5(String userid, int userpass, 
			String email , Date birthday) {
		//request.getParameter("userpass")
		System.out.println("��û�� ����.......");
		System.out.println(userid);
		System.out.println(userpass);
		System.out.println(email);
		System.out.println(birthday);
		return "paramResult2";
	}
	
	@ResponseBody
	@RequestMapping("/param3.do")
	public String test6(
			@RequestParam("userid") String userid, 
			@RequestParam("userpass") int pass, //�⺻Ÿ�ΰ�� null�̸� ����ȯ���� 500error
			String email2 ,   //�Ķ�����̸��� �ٸ��Ƿ� �����޽��� null
			Date birthday) {
		//request.getParameter("userpass")
		System.out.println("param3���� ��û�� ����.......");
		System.out.println(userid);
		System.out.println(pass);
		System.out.println(email2);
		System.out.println(birthday);
		return "paramResult2";
	}
	
	@ResponseBody
	@RequestMapping("/param4.do")
	public String test7(Car car, 
		  String email,
		  Date birthday) {
		System.out.println(car);
		System.out.println(email);
		System.out.println(birthday);
		return "car����";
	}
	
	@ResponseBody
	@RequestMapping("/param5.do")
	public String test8(@RequestParam Map<String,String> cart) {
		//�Ķ�����̸��� �Ű������̸��� ��ġ���� ���� ��� ������������ 
		for(String key:cart.keySet()) {
			System.out.println(key +"==>" + cart.get(key));
		}
		return "map test";
	}
	
 
	//@ModelAttribute�� �ڹٺ󽺱���� �̿��ؼ� View�� ���� �����Ѵ�. 
	@RequestMapping("/param6.do")
	public String test9(@ModelAttribute Car car, 
			 String email,
			 String birthday, Model model) {
		System.out.println(car);
		System.out.println(email);
		System.out.println(birthday);
		model.addAttribute("title", "@ModelAttribute����");
		model.addAttribute("email", email);
		model.addAttribute("birthday", birthday);
		return "carInfo";
	}
	
	
	
}




