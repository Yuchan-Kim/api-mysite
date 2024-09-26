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
		User authUser = sqlsession.selectOne("user.selectUser", id);
		return authUser;
	}

    // Update User
    public User updateUser(int userNum, String id, String name, String pw, String gender) {
        Map<String, Object> params = new HashMap<>();
        params.put("userNum", userNum);
        params.put("id", id);
        params.put("name", name);
        params.put("pw", pw);
        params.put("gender", gender);

        sqlsession.update("user.updateUser", params);

        User user = new User();
        user.setUserGender(gender);
        user.setUserId(id);
        user.setUserName(name);
        user.setUserNum(userNum);
        user.setUserPw(pw);

        return user;
    }

    // Check if User ID Exists
    public boolean existsById(String id) {
    	int count = sqlsession.selectOne("user.selectUser", id);
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

    
}
