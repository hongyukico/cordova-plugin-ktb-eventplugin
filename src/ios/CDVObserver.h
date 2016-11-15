//
//  CDVNotification.h
//  HelloCordova
//
//  Created by zyj7815 on 16/10/9.
//
//

#import <Cordova/CDVPlugin.h>

@interface CDVObserver : CDVPlugin


/**
 添加监听
 */
- (void)fireEvent:(CDVInvokedUrlCommand *)command;


/**
 注册监听
 */
- (void)addEventListener:(CDVInvokedUrlCommand *)command;


@end
