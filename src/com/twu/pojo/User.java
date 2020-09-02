package com.twu.pojo;

public class User {

    //常量，每个用户默认票数
    private static int DEFAULT_REMAINVOTES=10;
    //用户类型，1为管理员，2为普通用户
    private int userType;
    //用户名
    private String username;
    //密码
    private String password;
    //剩余票数，默认为10
    private int remainVotes;

    public User(int userType, String username, String password) {
        this.remainVotes=DEFAULT_REMAINVOTES;
        this.userType = userType;
        this.username = username;
        this.password = password;
    }

    public int getRemainVotes() {
        return remainVotes;
    }

    public void setRemainVotes(int remainVotes) {
        this.remainVotes = remainVotes;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
