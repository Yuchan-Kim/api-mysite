package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.UserService;
import com.javaex.util.JsonResult;
import com.javaex.util.JwtUtil;
import com.javaex.vo.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    // Register
    @PostMapping("/api/users/registration")
    public JsonResult registration(@RequestBody User user) {
        System.out.println("UserController.registration()");

        int count = service.exeRegister(user);
        if (count != 1) {
            return JsonResult.fail("Registration failed");
        } else {
            return JsonResult.success("Registration successful");
        }
    }

    // Login
    @PostMapping("api/users/login")
	public JsonResult login(@RequestBody User userVo, HttpServletResponse response) {
		System.out.println("UserController.login()");
		String id = userVo.getUserId();

		User authUser = service.exeLogin(id);
		System.out.println(userVo.toString());
		System.out.println(authUser);
		if(authUser != null ) {//로그인 성공
			JwtUtil.createTokenAndSetHeader(response, ""+authUser.getUserNum());
			return JsonResult.success(authUser);
		}else {
			return JsonResult.fail("로그인실패");
		}
	}
    
    //Edit user info, bring user info
    @GetMapping("api/users/me")
    public JsonResult editForm(HttpServletRequest request) {
    	System.out.println("UserController.editForm()");
    	
    	//요청 해더에서 토큰을 꺼낸후 유효성 체크. 정상이면 no 값을 꺼내준다. 
    	int no = JwtUtil.getNoFromHeader(request);
    	System.out.println(no);
    	
    	if(no != -1) {
    		User authUser = service.exeEditForm(no);
    		System.out.println(authUser.toString());
    		return JsonResult.success(authUser);
    	}else {
    		return JsonResult.fail("토큰 오류");
    	}
    }
    
    //Update User info
    @PutMapping("api/users/me")
    public JsonResult modifyUserInfo(HttpServletRequest request, @RequestBody User userVo) {
    	System.out.println("UserController.modifyUserInfo()");
    	System.out.println(userVo);
    	int no = JwtUtil.getNoFromHeader(request);
    	userVo.setUserNum(no);
    	System.out.println(userVo);
    	service.exeUpdate(userVo);
    	if(no != -1) {
    		return JsonResult.success(userVo);
    	}else {
    		return JsonResult.fail("토큰 오류");
    	}
    	
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
   
}
