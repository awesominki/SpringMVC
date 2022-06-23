package org.kosta.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.kosta.myapp.model.DeptService;
import com.kosta.myapp.vo.DeptDTO;


@Controller
public class DeptController {
	Logger logger = LoggerFactory.getLogger(DeptController.class);
	
	@Autowired
	DeptService dService;
	
	@RequestMapping(value = "/dept/deptList.do", method = RequestMethod.GET)
	public void deptList(Model model, HttpServletRequest request) {
		// 주소명과 메소드명이 같으면 void로 해도 알아서 띄워준다.
		List<DeptDTO> dlist = dService.selectAll();
		
		Map<String, ?> flashMap =   
		         RequestContextUtils.getInputFlashMap(request);
		String resultMessage = null;
		if(flashMap !=null ) {
			resultMessage = (String)flashMap.get("resultMessage");
		}
		model.addAttribute("resultMessage", resultMessage);
		model.addAttribute("deptlist",dlist);
	}
	
	@RequestMapping(value = "/dept/deptDelete.do", method = RequestMethod.GET)
	public String deptDelete(int deptid, RedirectAttributes redirectAttr) {
		logger.info(deptid + "를 삭제합니다.");
		int result = dService.deptDelete(deptid);
		redirectAttr.addFlashAttribute("resultMessage",result + "건 삭제");
		return "redirect:/dept/deptList.do";
	}
	
	@RequestMapping(value = "/dept/deptInsert.do", method = RequestMethod.GET)
	public String deptInsertGet() {
		return "dept/deptInsert";
	}
	
	@RequestMapping(value = "/dept/deptInsert.do", method = RequestMethod.POST)
	public String deptInsertPost(DeptDTO dept, RedirectAttributes redirectAttr) {
		logger.info(dept.toString());
		
		int result = dService.deptInsert(dept);
		logger.info(result + "건 입력");
		redirectAttr.addFlashAttribute("resultMessage",result + "건 입력");
		return "redirect:/dept/deptList.do";
	}
	
	@RequestMapping(value = "/dept/deptUpdate.do", method = RequestMethod.GET)
	public String deptUpdateGet(int dept_id,Model model) {
		//상세보기는 부서번호로 정보를 조회한 후 page보이기
		DeptDTO deptvo = dService.selectById(dept_id);
		model.addAttribute("dept",deptvo);
		return "dept/deptDetail";
	}
	
	@RequestMapping(value = "/dept/deptUpdate.do", method = RequestMethod.POST)
	public String deptUpdatePost(DeptDTO dept, RedirectAttributes redirectAttr) { //new DeptDTO(); dept,setDepartment_id(request.getParameter("department_id"))
		logger.info(dept.toString());
		int result = dService.deptUpdate(dept);
		logger.info(result + "건 수정");
		redirectAttr.addFlashAttribute("resultMessage",result + "건 수정");
		return "redirect:/dept/deptList.do";
	}
}
