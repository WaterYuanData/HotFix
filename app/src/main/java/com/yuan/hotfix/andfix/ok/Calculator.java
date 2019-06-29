package com.yuan.hotfix.andfix.ok;


import com.yuan.hotfix.andfix.Replace;

//Server
public class Calculator {

    @Replace(clazz = "com.yuan.hotfix.andfix.Calculator", method = "calculate")
    public int calculate() {
        int i = 1;
        int j = 10;
        return j / i;
    }
}
