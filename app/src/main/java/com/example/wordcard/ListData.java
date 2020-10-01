package com.example.wordcard;
public class ListData {
    String s_id;
    long id;
    String Engword;
    String JpWord;

    public void setId(long id){
        this.id = id;
    }

    public long getId(){
        return id;
    }

    public void SetEngWord (String name){
        this.Engword = name;
    }

    public String getEngWord(){
        return Engword;
    }

    public void SetJpWord(String yomi){
        this.JpWord = yomi;
    }

    public  String getJpWord(){
        return JpWord;
    }


}