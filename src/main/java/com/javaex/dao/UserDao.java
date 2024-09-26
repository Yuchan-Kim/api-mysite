package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.User;

@Repository
public class UserDao {
    @Autowired
    private SqlSession sqlsession;

    // Register
    public int registeration(User user) {
        System.out.println("Registration Process()");
        int count = sqlsession.insert("user.insert", user);
        return count;
    }

    // Select User for Login
	public User selectUser(String id ) {
		System.out.println("UserDao.selectUser()");
		User authUser = sqlsession.selectOne("user.selectUserById", id);
		return authUser;
	}

    // Update User
    public int updateUser(User userVo) {
        System.out.println("UserDao.updateUser()");
        int count = sqlsession.update("user.updateUser", userVo);

        return count;
    }

    // Check if User ID Exists
    public boolean existsById(String id) {
    	int count = sqlsession.selectOne("user.selectUserById", id);
        return count > 0;
    }

    // Select User by ID for Duplicate Check
    public int selectUserById(String id) {
        System.out.println("UserDao.selectUserById()");
        System.out.println(id);
        int count = sqlsession.selectOne("user.selectById", id);
        System.out.println(count);
        return count;
    }
    
 // no로 한명데이터 가져오기(회원정보수정 폼)
 	public User userSelectOneByNo(int no) {
 		System.out.println("UserDao.userSelectOneByNo()");

 		User userVo = sqlsession.selectOne("user.selectOneByNo", no);
 		return userVo;
 	}

    
}
