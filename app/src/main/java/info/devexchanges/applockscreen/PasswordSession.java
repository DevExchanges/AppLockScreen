package info.devexchanges.applockscreen;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class PasswordSession {

    // Shared Preferences
    private SharedPreferences pref;

    // Editor for Shared preferences
    private SharedPreferences.Editor editor;

    // Context
    private Context context;

    // Shared preferences mode
    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "password";
    public static final String KEY_PW = "password";
    public static final String KEY_IS_PASS = "isPass";

    @SuppressLint("CommitPrefEdits")
    public PasswordSession(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public String getKeyPassword() {
        return pref.getString(KEY_PW, "");
    }

    public void setKeyPassword(String s) {
        editor.putString(KEY_PW, s);
        editor.commit();
    }

    public boolean getKeyIsPass() {
        return pref.getBoolean(KEY_IS_PASS, false);
    }

    public void setKeyIsPass (boolean isPass) {
        editor.putBoolean(KEY_IS_PASS, isPass);
        editor.commit();
    }
}
