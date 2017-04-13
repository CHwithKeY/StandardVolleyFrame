package chwithkey.standardvolleyframe.netDown;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.json.JSONException;

import chwithkey.standardvolleyframe.R;
import chwithkey.standardvolleyframe.base.Base_Act;
import chwithkey.standardvolleyframe.base.Base_Frag;

/**
 * Created by KeY on 2016/11/4.
 */

public class NetDown_Frag extends Base_Frag implements View.OnClickListener {

    private NetDownAction netDownAction;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_public_net_down_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        varInit();

        setupRefreshImage(view);
    }

    private void varInit() {
        netDownAction = new NetDownAction(getContext(), this);
    }

    private void setupRefreshImage(View view) {
        ImageView refresh_img = (ImageView) view.findViewById(R.id.net_down_refresh_img);
        refresh_img.setOnClickListener(this);
    }

    @Override
    public void onMultiHandleResponse(String tag, String result) throws JSONException {
        if (netDownAction.handleResponse(result)) {
            Log.i("Result", "link success");
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.remove(this);
            transaction.commit();

//                getActivity().onCreate(null, null);
            ((Base_Act) getActivity()).varInit();
        }
    }

    @Override
    public void onNullResponse(String tag) throws JSONException {

    }

    @Override
    public void setCustomTag(String tag) {

    }

    @Override
    public String getCustomTag() {
        return null;
    }

    @Override
    public void onClick(View v) {
        netDownAction.ping();
    }
}
