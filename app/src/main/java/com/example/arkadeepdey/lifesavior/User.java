package com.example.arkadeepdey.lifesavior;

import android.content.Context;
import android.content.SharedPreferences;

class User {
    Context context;
    SharedPreferences sharedPreferences;
    private String email;
    public  User(Context ctx){
        this.context=ctx;
        sharedPreferences=context.getSharedPreferences("userinfo",Context.MODE_PRIVATE);
    }

    public String getName() {
            String email=sharedPreferences.getString("email","");
            return email;
        }
    public void setName(String email) {
        this.email=email;
        sharedPreferences.edit().putString("email",email).commit();

    }
    public void removeuser(){
        sharedPreferences.edit().clear().commit();
    }

}

