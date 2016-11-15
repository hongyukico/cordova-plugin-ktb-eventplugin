var EventPlugin =function () {}
EventPlugin.prototype.eventResult = {}


EventPlugin.prototype.error_callback = function (msg) {
  console.log('Javascript Callback Error: ' + msg)
}

EventPlugin.prototype.success_callback = function (msg) {
  console.log('Javascript Callback success: ' + msg)
}


//调用原生的插件
EventPlugin.prototype.call_native = function (name, args, callback) {//调用原生的插件 action_name 、params、回调
  ret = cordova.exec(callback, this.error_callback, 'EventPlugin', name, [args])
  return ret;
}

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

if (!window.plugins) {
  window.plugins = {}
}

if (!window.plugins.EventPlugin) {
  window.plugins.EventPlugin = new EventPlugin();
}

module.exports = new EventPlugin();


