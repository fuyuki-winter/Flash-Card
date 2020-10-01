package com.example.wordcard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Button sendToTestButton = findViewById(R.id.button2);
        CsvReader parser = new CsvReader();
        parser.reader(getApplicationContext());
        Realm.init(this);
        Realm realm = Realm.getDefaultInstance();
        RealmQuery<Words> query = realm.where(Words.class);
        RealmResults<Words> result = query.findAll();
        if(result.size() == 0){
            realm.beginTransaction();
            for(int i = 0; i < parser.objects.size();i++){
                Words word = realm.createObject(Words.class);
                word.set﻿Id(parser.objects.get(i).getId());
                word.setEng(parser.objects.get(i).getEngWord());
                word.setJp(parser.objects.get(i).getJpWord());
            }
            realm.commitTransaction();
        }

        sendToTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), TestActivity.class);
                startActivity(intent);
            }
        });
        Button sendToRegButton = findViewById(R.id.button3);
        sendToRegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(getApplication(), ListActivity.class);
            startActivity(intent);
            }
        });
    }
}