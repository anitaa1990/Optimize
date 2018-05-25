# Optimize
<img src="https://img.shields.io/badge/API-14%2B-orange.svg?style=flat" alt="API" data-canonical-src="https://img.shields.io/badge/API-14%2B-orange.svg?style=flat" style="max-width:100%;">
Android library for displaying data based on JSON configuration fetched from server.</br> 
With this library, you can kiss goodbye to string.xml, dimen.xml, arrays.xml. Keep all your string/integer/array config in one file. The library will automatically fetch the data from the url you provide. </br></br> 


<p><a href="https://github.com/anitaa1990/Optimize/blob/master/media/1.png" target="_blank"><img src="https://github.com/anitaa1990/Optimize/blob/master/media/1.png" width="180" style="max-width:100%;"></a></p>


<h2>How it works?</h2>
Have you ever had to work on an app, make it production ready and upload it to PlayStore. Then there comes a tiny modification where you need to change the text in a screen or the color of a button in another screen? Or perhaps we need to change the number of times a certain background task should run? Or maybe change the time a certain task is executed? Or maybe your case is similar to mine and we need to keep the error messages and the default app messages the same as displayed in the web. Or in the ios version of the app. So there is a single file that all 3 systems can reuse. </br>
For example, all my app messages are kept in string.xml file. Suppose they were kept in a json file in the backend.</br>
And everytime the app is opened the latest data is downloaded and cached locally in the app.</br></br>

For example, in the sample app, you will see the url that I have given is </br>
https://s3-ap-southeast-1.amazonaws.com/android-optimize/optimize.json</br>
This is used to fetch my data from the backend asynchrously and cache it in app. </br>
Everytime the app is opened the data is fetched and based on the whether the content is changed or not, the data is cached locally.</br> 
Note: The library is implemented using the <a href="https://developer.android.com/topic/libraries/architecture/workmanager" target="_blank">Work Manager API</a> provided by Google.</br>

<h2>How to integrate the library in your app?</h2>

<b>Gradle Dependecy</b></br>

```gradle
dependencies {
        implementation 'com.an.optimize:optimize:0.1.0'
}
```

<b>Maven Dependecy</b></br>
```xml
<dependency>
  <groupId>com.an.optimize</groupId>
  <artifactId>optimize</artifactId>
  <version>0.1.0</version>
  <type>pom</type>
</dependency>
```

<h2>Usage</h2>
<b>Step 1: Add the internet permission in the Android Manifest file</b></br>

```xml
<uses-permission android:name="android.permission.INTERNET" />
```
</br>
<b>Step 2: Add the following code to your Application class or your Main Activity file</b></br>

```java
Optimize.getInstance().init(getApplicationContext(), "<Add the url of the json file>");
```
</br>
<b>Step 3: All you have to do now to display a value from the json file is to call the below code</b></br>

```java
String stringData = Optimize.getInstance().getStringValue("<the name of the param key>", "<Default value to be displayed in case the backend data does not contain this key>");

//The same can be applied for Integers:
Optimize.getInstance().getIntegerValue("<the name of the param key>", <Default value in case the backend data does not contain this key>);

//The same can be applied for Double:
Double doubleData = Optimize.getInstance().getDoubleValue("<the name of the param key>", 0.00);

//The same can be applied for Boolean:
Boolean booleanData = Optimize.getInstance().getBooleanValue("boolean_data", false);

//The same can be applied for Number:
Number floatData = Optimize.getInstance().getNumberValue("float_data", -1.1);

//The same can be applied for a List:
List<String> listData = Optimize.getInstance().getList("list_data", Collections.emptyList());
```
 
 
 And that's it! It's super simple.
