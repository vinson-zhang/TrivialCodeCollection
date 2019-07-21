package com.zt.study.reflect;

/**
 * ZhangTao
 * 2019/7/21 13:27
 * Description:
 */
public class User {


    public User(){

    }

    public User(String name){
        this.name = name;
    }

    private String name;

    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
