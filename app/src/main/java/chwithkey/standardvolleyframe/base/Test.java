package chwithkey.standardvolleyframe.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONException;

import chwithkey.standardvolleyframe.R;
import chwithkey.standardvolleyframe.sharedinfo.SharedSet;

public class Test extends Base_Act {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }

    @Override
    public void varInit() {
        sharedAction.setParams(SharedSet.KEY_REFRESH_TIME, 1000);

        boolean rt =  (boolean) sharedAction.getParams(SharedSet.KEY_REFRESH_TIME);
        Log.i("Result", "refresh time is : " + sharedAction.getParams(SharedSet.KEY_REFRESH_TIME));
    }

    @Override
    protected void setupToolbar() {

    }

    @Override
    public void onMultiHandleResponse(String tag, String result) throws JSONException {

    }

    @Override
    public void onNullResponse(String tag) throws JSONException {

    }

    @Override
    public void onPermissionAccepted(int permission_code) {

    }

    @Override
    public void onPermissionRefused(int permission_code) {

    }


}
