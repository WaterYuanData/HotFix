package com.yuan.hotfix.andfix;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Enumeration;

import dalvik.system.DexFile;

public class DxManager {
    private static final String TAG = "DxManager";

    Context context;

    DxManager(Context context) {
        this.context = context;
    }

    public void loadDex(File dexFilePath) {
        File optFile = new File(context.getCacheDir(), dexFilePath.getName());
        if (optFile.exists()) {
            optFile.delete();
        }
        try {
            // 加载dex，若dex在SD卡，则需要先申请SD的读写权限
            DexFile dexFile = DexFile.loadDex(dexFilePath.getAbsolutePath(), optFile.getAbsolutePath(), Context.MODE_PRIVATE);
            Enumeration<String> enumeration = dexFile.entries();
            while (enumeration.hasMoreElements()) {
                String className = enumeration.nextElement();
                Class realClazz = dexFile.loadClass(className, context.getClassLoader());
                Log.i(TAG, "loadDex: 找到类=" + className);
                fix(realClazz);
            }
        } catch (IOException e) {
            e.printStackTrace();
            /*
             * java.io.IOException: No original dex files found for dex location /storage/emulated/0/_ae/out.dex
             * 是缺少授予WRITE_EXTERNAL_STORAGE权限
             * */
        }
    }

    private void fix(Class realClazz) {
        Method[] declaredMethods = realClazz.getDeclaredMethods();
        for (Method method : declaredMethods) {
            Replace replace = method.getAnnotation(Replace.class);
            if (replace == null) {
                continue;
            }
            String wrongClazzName = replace.clazz();
            String wrongMethod1Name = replace.method();
            try {
                Class<?> wrongClazz = Class.forName(wrongClazzName);
                //                Method wrongMethod = wrongClazz.getDeclaredMethod(wrongMethod1Name);
                Method wrongMethod = wrongClazz.getMethod(wrongMethod1Name, method.getParameterTypes());
                replace(wrongMethod, method);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }

        }
    }

    private void replace(Method wrongMethod, Method method) {
    }
}
