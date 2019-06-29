package com.yuan.hotfix.andfix.ok;


import android.util.Log;

import com.yuan.hotfix.andfix.Replace;

//Server
public class Calculator {
    private static final String TAG = "Calculator";

    @Replace(clazz = "com.yuan.hotfix.andfix.Calculator", method = "calculate")
    public int calculate() {
        int i = 1;
        int j = 10;
        Log.i(TAG, "calculate: 修复成功");
        return j / i;
    }
}
