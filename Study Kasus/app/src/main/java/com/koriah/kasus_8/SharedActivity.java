package com.koriah.kasus_8;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by kori'ahtun on 6/2/2018.
 */

public class SharedActivity  {
    public static final String SESSION_SERVIS = "sessionServis";
    public static final String SESSION_EMAIl = "sessionEmail";
    public static final String SESSION_STATUS = "sessionStatus";

    SharedPreferences session;
    SharedPreferences.Editor sessionEditor;

    public SharedActivity (Context context){
        session = context.getSharedPreferences(SESSION_SERVIS, Context.MODE_PRIVATE);
        sessionEditor = session.edit();
    }

    public void saveStr (String keySP, String value){
        sessionEditor.putString(keySP, value);
        sessionEditor.commit();
    }

    public void saveBool(String keySP, boolean value){
        sessionEditor.putBoolean(keySP, value);
        sessionEditor.commit();
    }

    public Boolean getSessionStatus(){
        return session.getBoolean(SESSION_STATUS, false);
    }

    public String getSessionEmail(){
        return session.getString(SESSION_EMAIl, "");
    }
}
