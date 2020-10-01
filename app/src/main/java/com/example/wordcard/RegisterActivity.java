package com.example.wordcard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private Realm mRealm;
    private EditText mEditName;
    private long word_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // ソフトウェアキーボードを開く
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        mEditName = (EditText) findViewById(R.id.editText);
        findViewById(R.id.btnOK).setOnClickListener(this);
        findViewById(R.id.btnCancel).setOnClickListener(this);

        // 初期データの設定
        initData();

    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        if (id == R.id.btnOK) {

            insert();
        }
        finish();
    }

    /**
     * 主キーを生成します。
     *
     * @return 次のID
     */
    public long nextWordId() {
        RealmResults<Words> results = mRealm.where(Words.class).findAll().sort("Id", Sort.DESCENDING);
        if (results.size() > 0) {
            return results.first().getId() + 1;
        }
        return 0;
    }
    /**
     * 初期データの設定
     */
    private void initData(){
        mRealm = Realm.getDefaultInstance();

        String cat_id = getIntent().getStringExtra("Id");
        if (TextUtils.isEmpty(cat_id)) {

            // データが無い場合は新しいIDを取得
            word_id = nextWordId();
        } else {

            // データがある場合は更新
            //word_id = Long.parseLong(String.valueOf(word_id));
            mEditName.setText(mRealm.where(Words.class).equalTo("Id", word_id).findFirst().getEng());
            mEditName.setSelection(mEditName.getText().length());
        }
    }

    /**
     * Catを追加します
     */
    public void insert() {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Words words = new Words();
                words.set﻿Id(word_id);
                words.setEng(mEditName.getText().toString());
                realm.insertOrUpdate(words);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mRealm.close();
    }
}