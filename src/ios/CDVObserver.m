//
//  CDVNotification.m
//  HelloCordova
//
//  Created by zyj7815 on 16/10/9.
//
//

#import "CDVObserver.h"

@interface CDVObserver ()

@end

@implementation CDVObserver

- (void)fireEvent:(CDVInvokedUrlCommand *)command {
    NSString *name = command.arguments[0][@"name"];
    NSDictionary *info = command.arguments[0];
    
    [[NSNotificationCenter defaultCenter]postNotificationName:name
                                                       object:nil
                                                     userInfo:info];
}


- (void)addEventListener:(CDVInvokedUrlCommand *)command {
    NSString *name = command.arguments[0];
    [[NSNotificationCenter defaultCenter]addObserver:self
                                            selector:@selector(nativeJS:)
                                                name:name
                                              object:nil];
}


- (void)nativeJS:(NSNotification *)notifi{

    dispatch_async(dispatch_get_main_queue(), ^{
        NSData *jsonData = [NSJSONSerialization dataWithJSONObject:notifi.userInfo options:NSJSONWritingPrettyPrinted error:nil];
        NSString *jsonString = [[NSString alloc] initWithData:jsonData encoding:NSUTF8StringEncoding];
        NSString *name = notifi.userInfo[@"name"];
    
        NSString *url = [NSString stringWithFormat:
                         @"cordova.fireDocumentEvent('%@',%@)",
                         name,jsonString];
        [self.commandDelegate evalJs:url];
    });
}


@end
