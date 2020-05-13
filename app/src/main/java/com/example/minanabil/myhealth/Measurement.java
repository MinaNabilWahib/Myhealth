package com.example.minanabil.myhealth;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mina Nabil on 1/30/2018.
 */

public class Measurement {
    private String measure;
    private String name;
    private String UserName;

    public Measurement(){

    }
    public Measurement( String name ,String measure ,String username){
        this.name=name;
        this.UserName = username;
        this.measure = measure;
    }
//    public Measurement( String name ,String measure ){
//        this.name=name;
//        this.measure=measure;
//
//    }
    public String getText() {
        return measure;
    }

    public void setText(String measure) {
        this.measure = measure;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserName (String username) { this.UserName = username;  }

    public String getUserName () { return UserName; }

}

