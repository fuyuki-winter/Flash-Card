package com.example.wordcard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import io.realm.Realm;
import io.realm.RealmResults;

public class ListActivity extends AppCompatActivity {

    private static final String[] initData = {"黒猫", "白猫", "虎猫", "三毛猫", "錆び猫", "はちわれ"};
    private Realm mRealm;
    private MyAdapter mAdapter;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mListView = (ListView) findViewById(R.id.WordList);
        // 初期データを読み込みます
        initCat();
        Button sendToRegButton = findViewById(R.id.addbutton);
        sendToRegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 初期データを追加します。
     * 保存しているデータが0件の場合は、サンプルデータを作成します。
     */
    private void initCat(){

        mRealm = Realm.getDefaultInstance();

        // すべてのCatを取得します。
        RealmResults<Words> words = mRealm.where(Words.class).findAll().sort("Id");


        mAdapter = new MyAdapter(this, words);
        mListView.setAdapter(mAdapter);
        //mAdapter.setCallback((MyAdapter.DeleteListener) this);
    }

    //**
    // * Catを削除します
    // * @param catId
    // */
    /*private void deleteCat(long catId){
        final long id = catId;
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Words words = realm.where(Words.class).equalTo("Id", id).findFirst();
                words.deleteFromRealm();
            }
        });
    }*/

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRealm.close();
    }





    /*public void delete(long catId) {
        deleteCat(catId);
    }*/
}