package com.zt.study.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * ZhangTao
 * 2019/7/21 14:17
 * Description:
 */
public class PropertiesConfig {


    private static Map<String, Object> prpMap = new HashMap();

    static {
        Properties properties = new Properties();
        // 使用ClassLoader加载properties配置文件生成对应的输入流
        InputStream in = PropertiesConfig.class.getClassLoader().getResourceAsStream("private-config.properties");
        // 使用properties对象加载输入流
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(properties != null){
            for(String name : properties.stringPropertyNames()){
                prpMap.put(name, properties.getProperty(name));
            }
        }
    }

    public static String getProp(String name){
        return (String) prpMap.get(name);
    }

}
