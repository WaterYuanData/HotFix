package com.yuan.hotfix.andfix;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yuan.hotfix.R;

import java.io.File;

public class Main2Activity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private TextView textView;
    private Button calculateButton;
    private Button fixButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        init();
    }

    private void init() {
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.calculate:
                        int calculate = new Calculator().calculate();
                        // 直接设置数字，会把int当成resid，造成找不到资源的异常
                        textView.setText("" + calculate);
                        break;
                    case R.id.fix:
                        // etPath();
                        DxManager dxManager = new DxManager(getApplicationContext());
                        dxManager.loadDex(getPath());
                        break;
                }
            }
        };

        textView = findViewById(R.id.tv);
        calculateButton = findViewById(R.id.calculate);
        fixButton = findViewById(R.id.fix);

        calculateButton.setOnClickListener(onClickListener);
        fixButton.setOnClickListener(onClickListener);
    }

    private File getPath() {
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        Log.i(TAG, "getPath: getExternalStorageDirectory=" + externalStorageDirectory.getAbsolutePath());
        File file = new File(externalStorageDirectory.getAbsolutePath() + "/_ae");
        Log.i(TAG, "getPath: exists=" + file.exists());
        file = new File(externalStorageDirectory.getAbsolutePath() + "/_ae/fix.dex");
        Log.i(TAG, "getPath: _ae/fix.dex exists=" + file.exists());
        return file;
        /*
2019-06-02 17:44:08.194 32253-32253/com.yuan.testandfix I/MainActivity: getPath: getExternalStorageDirectory=/storage/emulated/0
2019-06-02 17:44:08.194 32253-32253/com.yuan.testandfix I/MainActivity: getPath: exists=true
        * */
    }
}
