package com.zt.study.reflect;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * ZhangTao
 * 2019/7/21 11:54
 * Description: java 反射
 */
public class JavaReflect {

    /**
     * Java反射的定义：JAVA反射机制是在运行状态中，对于任意一个类，
     * 都能够知道这个类的所有属性和方法；对于任意一个对象，都能够调用它的任意方法和属性；
     * 这种动态获取信息以及动态调用对象方法的功能称为java语言的反射机制。
     *
     * 简而言之：及时在运行时、访问、修改类的属性和方法
     *
     * 反射数据动态编译
     *
     * 应用场景
     * 1. 动态获取类文件结构信息，属性和方法； 调用对象的方法
     * 2. 常用的需求场景： 动态代理、工厂模式优化、Java JDBC操作等
     *
     * 每种类型的class 对象执行一个
     */

    public static void main(String[] args) {

    }

    /**
     * 每个类的， class 类型对象只有一个
     *
     * @throws ClassNotFoundException
     */
    @Test
    public void testClass() throws ClassNotFoundException {
        Class aClass = "hello".getClass();
        Class<?> aClass1 = Class.forName("java.lang.String");
        System.out.println(aClass == aClass1);
    }

    /**
     * 获取目标类型的 class 对象
     */
    @Test
    public void getObjectType() throws ClassNotFoundException {

        Boolean temp = false;
        System.out.println(temp.getClass());

        Class booleanClass = Boolean.class;
        System.out.println(booleanClass);

        Class<?> aClass = Class.forName("java.lang.Boolean");
        System.out.println(aClass);

        Class<Boolean> type = Boolean.TYPE;
        System.out.println(type);

    }

    @Test
    public void testReflectByUser() throws NoSuchFieldException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        User user = new User();
        Class<? extends User> aClass = user.getClass();

        ClassLoader classLoader = aClass.getClassLoader();
        System.out.println(classLoader);

        user.setName("124");


        Field[] declaredFields = aClass.getDeclaredFields();
        System.out.println(JSON.toJSONString(declaredFields));


        //访问修改私有变量
        Field name = aClass.getDeclaredField("name");
        System.out.println(JSON.toJSONString(name));
        name.setAccessible(true);

        name.set(user, "456");
        System.out.println(name.get(user));
        System.out.println(user.getName());


        Field[] fields = aClass.getFields();
        System.out.println(JSON.toJSONString(fields));

        //调用构造函数
        User user1 = aClass.newInstance();
        //调用无参构造函数
        System.out.println(JSON.toJSONString(user1));

        Constructor<? extends User> constructor = aClass.getConstructor(String.class);
        User user2 = constructor.newInstance("hello");
        System.out.println(JSON.toJSONString(user2));

        //反射调用方法
        Method setName = aClass.getMethod("setName", String.class);
        setName.invoke(user2, "mike");

        System.out.println(JSON.toJSONString(user2));


    }
}
