package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.UserService;
import com.javaex.util.JsonResult;
import com.javaex.util.JwtUtil;
import com.javaex.vo.User;

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
    
   
}
