package com.ktb.plugin;

import android.content.Intent;
import android.content.IntentFilter;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 项目名称：android
 * 类描述：
 * 创建人：HONGYU.LIU
 * 创建时间：2016/10/9 11:41
 * 修改人：HONGYU.LIU
 * 修改时间：2016/10/9 11:41
 * 修改备注：
 */

public class EventListenerPlugin extends CordovaPlugin {

    private static final String fireEvent = "fireEvent";
    private static final String addEventListener = "addEventListener";
    private static String TAG = "EventListenerPlugin";
    EventReceiver receiver;

    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
    }


    @Override //插件的执行方法，
    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
        if (action.equals(addEventListener)) {//注册监听
            return addListener(action, args, callbackContext);
        } else if (action.equals(fireEvent)) {//觸發eventbus
            return sendEvent(action, args, callbackContext);
        } else {
            return false;
        }
    }

    private boolean addListener(String name, JSONArray args, CallbackContext callbackContext) {
        receiver = new EventReceiver();// (这里可以写系统的广播接收者重写onReceiver方法就可以)
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.EventReceiver");
        this.cordova.getActivity().registerReceiver(receiver, filter);//注册receiver
        return true;
    }

    private boolean sendEvent(String name, JSONArray args, CallbackContext callbackContext) {
        try {
            JSONObject jsonObject = args.getJSONObject(0);
            EventBus.getDefault().post(new WebEvent(jsonObject.getString("name"),
                    jsonObject.getString("info")));
            Intent intent = new Intent("android.intent.action.EventReceiver");
            intent.putExtra("name", jsonObject.getString("name"));
            intent.putExtra("params", jsonObject.getString("info"));
            this.cordova.getActivity().sendBroadcast(intent);
            callbackContext.success();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (receiver != null)
            this.cordova.getActivity().unregisterReceiver(receiver);
    }
}
