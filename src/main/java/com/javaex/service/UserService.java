package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.User;

@Service
public class UserService {

	@Autowired
	private UserDao dao;

	//Register
	public int exeRegister(User user) {
		int count = dao.registeration(user);
		return count;
	}

	/* 로그인 */
	public User exeLogin(String id) {
		System.out.println("UserService.exeJoin()");

		User authUser = dao.selectUser(id);
		return authUser;

	}

	public User exeUpdate (User userVo) {
		System.out.println("UserService.exeUpdate()");
		User user = dao.updateUser(userVo);
		return user;
	}

	// 아이디 중복 체크 서비스 메서드
    public boolean isDuplicateUserId(String id) {
        return dao.existsById(id);
    }

    // 아이디 중복체크 2 axios, javascript 사용
    public boolean exeIdCheck(String id) {
    	System.out.println("UserSevice.exeIdCheck()");
    	System.out.println(id);

    	int count = dao.selectUserById(id);
    	if (count ==1) {
    		return false;
    	}

    	return true;
    }
    
 // 회원정보수정폼(1명 데이터가져오기)
 	public User exeEditForm(int no) {
 		System.out.println("UserService.exeEditForm()");

 		User userVo = dao.userSelectOneByNo(no);
 		return userVo;
 	}

    

}
