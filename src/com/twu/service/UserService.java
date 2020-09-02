package com.twu.service;

import com.twu.data.Users;
import com.twu.pojo.User;

public class UserService {

    public User currentUser=null;
    public User getAuthUser(String name,String password) {
        currentUser= Users.getUser(name,password);
        return currentUser;
    }

    public int vote(int votesNum) {
        currentUser.setRemainVotes(currentUser.getRemainVotes()-votesNum);
        return currentUser.getRemainVotes();
    }


}
