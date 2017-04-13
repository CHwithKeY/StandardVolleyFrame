package chwithkey.standardvolleyframe.http;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Set;

import chwithkey.standardvolleyframe.base.Base_Act;
import chwithkey.standardvolleyframe.base.Base_Frag;
import chwithkey.standardvolleyframe.base.Base_Serv;
import chwithkey.standardvolleyframe.publicClass.ColorSnackBar;
import chwithkey.standardvolleyframe.sharedinfo.SharedAction;

import static chwithkey.standardvolleyframe.publicClass.Methods.cast;


/**
 * Created by KeY on 2016/3/29.
 */
public final class HttpHandler extends Handler {

    private Context context;

    private Base_Frag fragment;
    private Base_Serv service;

    // Activity的请求时候的构造函数
    public HttpHandler(Context context) {
        this.context = context;
    }

    // Fragment的请求时候的构造函数
    public HttpHandler(Context context, Base_Frag fragment) {
        this.context = context;
        this.fragment = fragment;
    }

    // Service的请求时候的构造函数
    public HttpHandler(Context context, Base_Serv service) {
        this.context = context;
        this.service = service;
    }

    public void handleResponse(String tag, String result) throws JSONException {
        // 根据判断决定是哪一方的请求
        if (fragment != null) {
            fragment.onMultiHandleResponse(tag, result);
        } else if (service != null) {
            service.onMultiHandleResponse(tag, result);
        } else {
            ((Base_Act) context).onMultiHandleResponse(tag, result);
        }
    }

    public void handleNullMsg(String tag) throws JSONException {
        if (fragment != null) {
            fragment.onNullResponse(tag);
        } else if (service != null) {
            service.onNullResponse(tag);
        } else {
            ((Base_Act) context).onNullResponse(tag);
        }
    }

    @Override
    public void handleMessage(Message msg) {

        switch (msg.what) {
            case HttpSet.httpResponse:
                HashMap<String, String> map = cast(msg.obj);

                String tag = "";
                Set set = map.keySet();
                for (Object aSet : set) {
                    tag = (String) aSet;
                }

                String result = map.get(tag);

                try {
                    handleResponse(tag, result);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;

            case HttpSet.httpNull:
                String null_tag = cast(msg.obj);
                try {
                    handleNullMsg(null_tag);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;

            default:
                break;
        }
    }

}
