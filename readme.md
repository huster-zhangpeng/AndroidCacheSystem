Android通用缓存
-----------

本项目是个android上的缓存项目，支持文件缓存和key-value缓存。

使用方法
-----------

```java
// 创建一个3级缓存系统
IKeyValueCache cache = new MemCache(new PreferencesCache(new JSONCache(null)));
// save an item
Item item = new Item(1); //也可以是Item(2.5f);Item(true);Item("test");
cache.put("test", item);

// retrieve an int item
int result;
if(cache.isExist("test")) {
    Item item = cache.get("test");
    result = item.asInt();
} else {
    result = 0;
}
// 字符串、浮点、布尔使用方式大同小异

// 创建一个文件缓存，可用来缓存网上下载的图片等资源
IFileCache fileCache = new InternalStorage(new ExternalStorage(null));
// save file
byte[] bytes = bytes of the content;
fileCache.put("test.img", bytes);
// retrieve the file bytes
if(fileCache.isExist("test")){
    bytes = fileCache.get("test");
} else {
    bytes = null;
}
```
