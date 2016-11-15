# Cordova Event plugin

for iOS and Android, by [ktb](https://github.com/hongyukico)

## Description
原生监听和网页跨页面的事件监听插件，支持android、ios平台


* 1.0.0 Works with Cordova 3.x
* 1.0.1+ Works with Cordova >= 4.0

## Installation

```
cordova plugin add cordova-plugin-ktb-eventplugin
```


## Usage

```javascript
	EventPlugin.prototype.fireEventByName= function (name,data) {
	   try {
		 this.receiveMessage ={"name":name,"info":data};
		 console.log('EventPlugin:receiveMessage ' + this.receiveMessage);
		 this.call_native('fireEvent',  this.receiveMessage, null);//触发原生的监听
	   } catch(exception) {
		 console.log('EventPlugin:pushCallback ' + exception)
	   }
	 }

	EventPlugin.prototype.addListener= function (name,callback) {
	   try {
		 document.addEventListener(name, callback, false);
		 this.call_native('addEventListener', name, null);//触发原生的监听
	   } catch(exception) {
		 console.log('EventPlugin:pushCallback ' + exception)
	   }
	 }

```
