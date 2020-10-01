package com.example.wordcard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public boolean onTouchEvent(MotionEvent event) {
        Intent intent = new Intent(getApplication(), MenuActivity.class);
        startActivity(intent);
        return super.onTouchEvent(event);
    }
}