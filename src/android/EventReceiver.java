package com.ktb.plugin;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.ktb.activity.WebViewActivity;


/**
 * 项目名称：android
 * 类描述：
 * 创建人：HONGYU.LIU
 * 创建时间：2016/10/13 11:59
 * 修改人：HONGYU.LIU
 * 修改时间：2016/10/13 11:59
 * 修改备注：
 */

public class EventReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String msg = intent.getExtras().get("name").toString();//action
        String args = intent.getExtras().get("params").toString();//args
        final String jsEvent = String.format("cordova.fireDocumentEvent(%s,%s)", "'" + msg + "'", args);
        ((WebViewActivity) context).webView.loadUrl("javascript:" + jsEvent);
    }
}