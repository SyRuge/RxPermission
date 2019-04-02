package com.xcx.permission;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.xcx.rxpermission.RxPermission;

import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initListener();
    }

    private void initListener() {
        findViewById(R.id.bt_request).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxPermission.with(MainActivity.this)
                        .requestPermissions(Manifest.permission.CAMERA)
                        .subscribe(new Consumer<int[]>() {
                            @Override
                            public void accept(int[] ints) throws Exception {
                                for (int i : ints) {
                                    if (i == PackageManager.PERMISSION_GRANTED) {
                                        Toast.makeText(getApplicationContext(), "授权成功!", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(getApplicationContext(), "授权失败!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });
            }
        });
    }
}
