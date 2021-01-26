# Android SDK

Android SDK 的最新版本是2.0.0

## 一、集成SDK

### 方式一

#### 1.1、从github上获取SDK库

```git
clone 'trackingsystemsdk'库到本地:

git clone https://github.com/esmartdata/yh-sdk-android.git
```

#### 1.2、在settings.gradle引入SDK库

```java
# Android SDK

Android SDK 的最新版本是2.0.0

## 一、集成SDK

### 方式一

#### 1.1、从github上获取SDK库

​```git
clone 'trackingsystemsdk'库到本地:

git clone https://github.com/esmartdata/yh-sdk-android.git
​```

#### 1.2、在settings.gradle引入SDK库

​```java
include ':app'
include ':trackingsystemsdk'
​```

#### 1.3、在在app build.gradle的dependencies标签下引入SDK库

​```java
implementation project(':trackingsystemsdk')
​```

### 方式二

#### 1.1、通过下载aar文件集成项目

1、将下载好的文件放入app 的lib文件夹中
2、修改在app build.gradle的dependencies标签下加入

​```git
//默认情况
implementation fileTree(include: ['*.jar'], dir: 'libs')
//修改为
implementation fileTree(dir: 'libs', include: ['*.jar','*.aar'])
​```

可以看到dir就是你aar要放到的路径下， 这里配置为默认的libs， 后面include一定要加上*.aar，不然就不会加入编译，然后就可以用了



### 方式三

#### 1.1、通过maven引入

1、在根目录的build.gradle文件中添加以下代码:
​```android
maven { url "https://oss.sonatype.org/content/repositories/snapshots"}
​```
​```android
//完整代码块
allprojects {
    repositories {
        ......
        maven {
            url "https://oss.sonatype.org/content/repositories/snapshots"
        }
    }
}
​```

2、修改在app build.gradle的dependencies标签下加入

​```git
//默认情况
implementation 'com.esmartwave.analytics.androidsdk:TrackingSystemSDK:1.0.0-SNAPSHOT'
​```




#### 1.3、初始化SDK

自定义一个Application中：

参数说明：

| 字段    | 类型    | 是否必须 | 说明                                       |
| ------- | ------- | -------- | ------------------------------------------ |
| app_key | String  | 是       | 应用唯一标识，由TrackingSystem提供，初始化 |
| debug   | Boolean | 是       | 是否是调试模式                             |
| ts_app  | String  | 否       | 同一个应用，有不同版本时，以该属性区分     |
| ts_ext  | String  | 否       | 全局自定义扩展属性                         |

​```java
// 设置AppKey,ts_ext,ts_app，其中ts_ext,ts_app为非必传参数
TSConfOption confOption = new TSConfOption(this, "appkey", "ts_ext","ts_app", false);
//初始化SDK
TSAnalyticsSDK.startWithConfigOptions(confOption);
​```

至此已完成对页面数据的采集，用户访问页面，即可监测到对应数据。



## 二、设置用户属性

### 2.1 调用setUserInfo方法设置用户属性

在登录成功后设置用户信息，且最好在发送页面采集数据请求前调用，否则无法准确识别用户

参数说明：JsonObject，只支持以下属性的设置

| 字段      | 类型   | 是否必须 | 说明                               |
| --------- | ------ | -------- | ---------------------------------- |
| guid      | String | 是       | 应用唯一标识，由TrackingSystem提供 |
| real_name | String | 否       | 真实姓名                           |
| nick_name | String | 否       | 昵称                               |
| age       | Number | 否       | 年龄                               |
| birthday  | String | 否       | 生日                               |
| gender    | String | 否       | 性别: 男/女                        |
| account   | String | 否       | 账号                               |
| country   | String | 否       | 国家                               |
| province  | String | 否       | 省份                               |
| city      | String | 否       | 城市                               |


​```java
示例
//在登录成功后设置用户信息
TSUser user = new TSUser();
user.setGuid("应用唯一标识，由TrackingSystem提供");
TSAnalyticsSDK.sharedInstance().setUserInfo(user);
​```

### 2.2 采集页面数据

在需要自定义页面名称的控制器中继承协议，实现对应的协议方法即可

​```java
//获取单例 TSAnalyticsSDK
TSAnalyticsSDK analytics = TSAnalyticsSDK.sharedInstance()；

// 设置page_name：
analytics.setPageName("pageName");

// 设置page_title：
analytics.setPageTitle("pageTitle");
​```

至此已完成对页面数据的采集，用户访问页面，即可监测到对应数据



## 三、自定义事件埋码

 ⻚⾯有⼀个获取⼿机验证码的按钮需要，需要添加监测，可在点击按钮时调用事件采集接口

接口参数类型：JsonObject  
JsonObject中的每个参数如下

* event_name：String，必须，事件名称
* event_param：JsonObject，选填，事件属性

示例 

​```java
//初始化事件
JSONObject eventInfo = new JSONObject();
//初始化事件参数
JSONObject eventParam = new JSONObject();
eventParam.put("phone", "1861087138x");//事件参数-手机号 非必须
eventParam.put("verificationCode", "HELLO");//事件参数-验证码 非必需

//设置事件名称
eventInfo.put("eventName", "获取验证码");//事件名称 必须
//设置事件参数
eventInfo.put("eventParam", eventParam);//事件名称 必须

//调用采集事件接口
TSAnalyticsSDK.sharedInstance().event(eventInfo);
​```

通过上面的监测代码，我们可以监测到获取手机验证码的按钮，被点击了几次，以及哪些手机号在获取验证码
```

#### 1.3、在在app build.gradle的dependencies标签下引入SDK库

```java
implementation project(':trackingsystemsdk')
```

### 方式二

#### 1.1、通过下载aar文件集成项目

1、将下载好的文件放入app 的lib文件夹中
2、修改在app build.gradle的dependencies标签下加入

```git
//默认情况
implementation fileTree(include: ['*.jar'], dir: 'libs')
//修改为
implementation fileTree(dir: 'libs', include: ['*.jar','*.aar'])
```
可以看到dir就是你aar要放到的路径下， 这里配置为默认的libs， 后面include一定要加上*.aar，不然就不会加入编译，然后就可以用了



### 方式三

#### 1.1、通过maven引入

1、在根目录的build.gradle文件中添加以下代码:

maven { url "https://oss.sonatype.org/content/repositories/snapshots"}

```android
allprojects {
    repositories {
        ......
        maven {
            url "https://oss.sonatype.org/content/repositories/snapshots"
        }
    }
}
```

2、修改在app build.gradle的dependencies标签下加入

```git
//默认情况
implementation 'com.esmartwave.analytics.androidsdk:TrackingSystemSDK:1.0.0-SNAPSHOT'
```




#### 1.3、初始化SDK

自定义一个Application中：

参数说明：

| 字段    | 类型    | 是否必须 | 说明                                       |
| ------- | ------- | -------- | ------------------------------------------ |
| app_key | String  | 是       | 应用唯一标识，由TrackingSystem提供，初始化 |
| debug   | Boolean | 是       | 是否是调试模式                             |
| ts_app  | String  | 否       | 同一个应用，有不同版本时，以该属性区分     |
| ts_ext  | String  | 否       | 全局自定义扩展属性                         |

```java
// 设置AppKey,ts_ext,ts_app，其中ts_ext,ts_app为非必传参数
TSConfOption confOption = new TSConfOption(this, "appkey", "ts_ext","ts_app", false);
//初始化SDK
TSAnalyticsSDK.startWithConfigOptions(confOption);
```

至此已完成对页面数据的采集，用户访问页面，即可监测到对应数据。



## 二、设置用户属性

### 2.1 调用setUserInfo方法设置用户属性

在登录成功后设置用户信息，且最好在发送页面采集数据请求前调用，否则无法准确识别用户

参数说明：JsonObject，只支持以下属性的设置

| 字段      | 类型   | 是否必须 | 说明                               |
| --------- | ------ | -------- | ---------------------------------- |
| guid      | String | 是       | 应用唯一标识，由TrackingSystem提供 |
| real_name | String | 否       | 真实姓名                           |
| nick_name | String | 否       | 昵称                               |
| age       | Number | 否       | 年龄                               |
| birthday  | String | 否       | 生日                               |
| gender    | String | 否       | 性别: 男/女                        |
| account   | String | 否       | 账号                               |
| country   | String | 否       | 国家                               |
| province  | String | 否       | 省份                               |
| city      | String | 否       | 城市                               |


```java
示例
//在登录成功后设置用户信息
TSUser user = new TSUser();
user.setGuid("应用唯一标识，由TrackingSystem提供");
TSAnalyticsSDK.sharedInstance().setUserInfo(user);
```

### 2.2 采集页面数据

在需要自定义页面名称的控制器中继承协议，实现对应的协议方法即可
```java
//获取单例 TSAnalyticsSDK
TSAnalyticsSDK analytics = TSAnalyticsSDK.sharedInstance()；

// 设置page_name：
analytics.setPageName("pageName");

// 设置page_title：
analytics.setPageTitle("pageTitle");
```

至此已完成对页面数据的采集，用户访问页面，即可监测到对应数据



## 三、自定义事件埋码

 ⻚⾯有⼀个获取⼿机验证码的按钮需要，需要添加监测，可在点击按钮时调用事件采集接口

接口参数类型：JsonObject  
JsonObject中的每个参数如下

* event_name：String，必须，事件名称
* event_param：JsonObject，选填，事件属性

示例 

```java

//初始化事件
JSONObject eventInfo = new JSONObject();
//初始化事件参数
JSONObject eventParam = new JSONObject();
eventParam.put("phone", "1861087138x");//事件参数-手机号 非必须
eventParam.put("verificationCode", "HELLO");//事件参数-验证码 非必需

//设置事件名称
eventInfo.put("eventName", "获取验证码");//事件名称 必须
//设置事件参数
eventInfo.put("eventParam", eventParam);//事件名称 必须

//调用采集事件接口
TSAnalyticsSDK.sharedInstance().event(eventInfo);
```
通过上面的监测代码，我们可以监测到获取手机验证码的按钮，被点击了几次，以及哪些手机号在获取验证码