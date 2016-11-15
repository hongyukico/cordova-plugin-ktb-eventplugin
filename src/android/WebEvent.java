package com.ktb.plugin;


import org.greenrobot.eventbus.EventBus;

/**
 * 项目名称：android
 * 类描述：
 * 创建人：HONGYU.LIU
 * 创建时间：2016/10/9 11:53
 * 修改人：HONGYU.LIU
 * 修改时间：2016/10/9 11:53
 * 修改备注：
 */
public class WebEvent extends EventBus {
    public String key;
    public String info;

    public WebEvent(String key, String info) {
        this.key = key;
        this.info = info;
    }

}
