package com.kosta.myapp.model;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.myapp.vo.DeptDTO;

//Service : Business Logic작성한다.
//DB작은 DAO를 호출한다.
@Service
public class DeptService {
	@Autowired
	private DeptDAO deptDAO;
	
	//1. 전체 조회
	public List<DeptDTO> selectAll(){
		return deptDAO.selectAll();
	}
	//2. 특정 부서 조회(부서코드로 조회)
	public DeptDTO selectById(int deptid) {
		return deptDAO.selectById(deptid);
	}
	//3. 지역코드로 조회
	public List<DeptDTO> selectByLocation(int locid) {
		return deptDAO.selectByLocation(locid);
	}
	//4. 신규 부서입력
	public int deptInsert(DeptDTO dept) {
		return deptDAO.deptInsert(dept);
	}
	//5. 특정 부서수정
	public int deptUpdate(DeptDTO dept) {
		return deptDAO.deptUpdate(dept);
	}
	//6. 특정 부서삭제
	public int deptDelete(int deptid) {
		return deptDAO.deptDelete(deptid);
	}
}
