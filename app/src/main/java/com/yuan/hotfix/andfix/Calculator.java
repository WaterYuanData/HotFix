package com.yuan.hotfix.andfix;

import android.util.Log;

// 客户端会出错的代码
public class Calculator {
    private static final String TAG = "Calculator";

    public int calculate() {
        Log.i(TAG, "calculate: ");
        int i = 0;
        int j = 10;
        i = j / i;
        try {
        } catch (Exception e) {
            Log.e(TAG, "calculate: ");
        }
        return i;
    }
}
