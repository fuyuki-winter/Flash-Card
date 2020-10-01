// Please note : @LinkingObjects and default values are not represented in the schema and thus will not be part of the generated models
package com.example.wordcard;

import io.realm.RealmObject;

public class Words extends RealmObject {
    private Long Id;
    private String Eng;
    private String Jp;

    public Long getId() { return Id; }


    public String getEng() { return Eng; }

    public void setEng(String Eng) { this.Eng = Eng; }

    public String getJp() { return Jp; }

    public void setJp(String Jp) { this.Jp = Jp; }


    public void set﻿Id(long Id) {
        this.Id = Id;
    }


}
