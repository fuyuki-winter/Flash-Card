package com.example.wordcard;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import io.realm.internal.Row;

public class CsvReader {
    List<ListData> objects = new ArrayList<ListData>();
    public void reader(Context context) {
        AssetManager assetManager = context.getResources().getAssets();
        try {
            // CSVファイルの読み込み
            InputStream inputStream = assetManager.open("words.csv");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferReader = new BufferedReader(inputStreamReader);
            String line;
            long id_count = 0;
            while ((line = bufferReader.readLine()) != null) {
                id_count++;
                //カンマ区切りで１つづつ配列に入れる
                ListData data = new ListData();
                String[] RowData = line.split(",");

                //CSVの左([0]番目)から順番にセット
                //String r_data = RowData[0];
                //long ldata = Long.valueOf(r_data.trim());
                data.setId(id_count);
                data.SetEngWord(RowData[0]);
                data.SetJpWord(RowData[0]);
                objects.add(data);
            }
            bufferReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}