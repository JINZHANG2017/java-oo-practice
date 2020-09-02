package com.twu.data;

import com.twu.pojo.User;

import java.util.ArrayList;
import java.util.List;

public class Users {
    private Users(){};
    private static List<User> users=new ArrayList<>();

    //添加user
    static{
        //添加一个管理员
        users.add(new User(1,"admin","admin"));
        //添加一个普通用户
        users.add(new User(2,"jin","123456"));
    }
    //通过用户名和密码来获取user
    public static User getUser(String username,String password){
        User user=null;
        for(User u:users){
            if(u.getUsername().equals(username)&&u.getPassword().equals(password)){
                user=u;
            }
        }
        return user;
    }


}
