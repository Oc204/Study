package com.webtest.Bean;
/*
 * javabean反射，mvc 中的model层
 * 实现逻辑封装，体现代码功能的独立性
 * 便捷且更明确地实现数据的传输
 */
public class User {

    private int id;//用户序列号
    private String username;
    private String password;
    
    
    public User() {
        super();
    }
    public User(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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