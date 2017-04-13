package chwithkey.standardvolleyframe.sharedinfo;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import chwithkey.standardvolleyframe.publicClass.Methods;

/**
 * Created by KeY on 2016/6/3.
 */
public class SharedAction {
    protected SharedPreferences sp;

    public final static String FILE_NAME = "sp_file";

    public final static String KEY_LAST_ID = "last_id";

    public SharedAction() {

    }

    public SharedAction(Context context) {
        sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

    public void setShared(SharedPreferences sp) {
        this.sp = sp;
    }

    public void clearLastIdInfo() {
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(KEY_LAST_ID, 0);
        editor.apply();
    }

    public void setParams(String key_name, Object object) {
        SharedPreferences.Editor editor = sp.edit();
        if (object instanceof Integer) {
            editor.putInt(key_name, (Integer) Methods.cast(object));
        } else if (object instanceof String) {
            editor.putString(key_name, (String) Methods.cast(object));
        } else if (object instanceof Boolean) {
            editor.putBoolean(key_name, (Boolean) Methods.cast(object));
        } else if (object instanceof Float) {
            editor.putFloat(key_name, (Float) Methods.cast(object));
        } else {
            Log.e(getClass().getName(), "无法判断放入的 sp 的变量类型");
        }

        editor.apply();
    }

    public String getParams(String key_name) {
        return sp.getAll().get(key_name).toString();
    }
}
