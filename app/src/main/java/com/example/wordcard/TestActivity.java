package com.example.wordcard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.view.MotionEvent;
import android.widget.TextView;

import java.util.Random;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class TestActivity extends AppCompatActivity {
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Realm.init(this);
        realm = Realm.getDefaultInstance();



        RealmQuery<Words> query = realm.where(Words.class);
        RealmResults<Words> result = query.findAll();

        int num = result.size();
        Random random = new Random();
        num = random.nextInt(num);
        Log.d("a",String.valueOf(num));
        setContentView(R.layout.activity_test);
        TextView textView1 = findViewById(R.id.textView2);
        textView1.setText(result.get(num).getEng());
        TextView textView2 = findViewById(R.id.textView3);
        textView2.setText(result.get(num).getJp());

    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        TextView textView2 = findViewById(R.id.textView3);
        textView2.setVisibility(View.VISIBLE);
        Button nextButton = findViewById(R.id.button4);
        nextButton.setVisibility(View.VISIBLE);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Button nextButton = findViewById(R.id.button4);
                nextButton.setVisibility(View.GONE);
                TextView textView2 = findViewById(R.id.textView3);
                textView2.setVisibility(View.INVISIBLE);
                Realm realm = Realm.getDefaultInstance();
                RealmQuery<Words> query = realm.where(Words.class);
                RealmResults<Words> result = query.findAll();

                int num = result.size();
                Random random = new Random();
                num = random.nextInt(num);
                TextView textView1 = findViewById(R.id.textView2);
                textView1.setText(result.get(num).getEng());
                textView2.setText(result.get(num).getJp());
            }
        });
        return super.onTouchEvent(event);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }


}
