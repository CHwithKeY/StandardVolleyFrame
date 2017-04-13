package chwithkey.standardvolleyframe.netDown;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import chwithkey.standardvolleyframe.R;
import chwithkey.standardvolleyframe.base.BaseAction;
import chwithkey.standardvolleyframe.base.Base_Frag;
import chwithkey.standardvolleyframe.http.HttpAction;
import chwithkey.standardvolleyframe.http.HttpHandler;
import chwithkey.standardvolleyframe.http.HttpResult;
import chwithkey.standardvolleyframe.http.HttpSet;


/**
 * Created by KeY on 2016/11/4.
 */

public class NetDownAction extends BaseAction {
    public final static String NET_DOWN_PING = "net_down_ping";
    public static String URL_PING = "";

    private Base_Frag base_frag;

    public static void setUrlPing(String urlPing) {
        URL_PING = urlPing;
    }

    public NetDownAction(Context context, Base_Frag base_frag) {
        super(context);
        this.base_frag = base_frag;
    }

    public void ping() {
        if (!checkNet()) {
            return;
        }

        String[] key = {""};
        String[] value = {""};

        HttpAction action = new HttpAction(context);
        action.setUrl(URL_PING);
        action.setMap(key, value);
        action.setTag(NET_DOWN_PING);
        action.setHandler(new HttpHandler(context, base_frag));
        action.setDialog(context.getString(R.string.base_refresh_progress_msg));
        action.interaction();
    }

    public boolean handleResponse(String result) throws JSONException {
        JSONObject obj = new JSONObject(result);
        String response = obj.getString(HttpResult.RESULT);
        Log.i("Result", "response  is : " + response);
        return response.equals("Link");
    }
}
