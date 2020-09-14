# loyalty-station-android-sdk

[Gamiphy SDK Jitpack Link](https://github.com/gamiphy/loyalty-station-android-sdk)

## Introduction 

Gamiphy Loyalty & Rewards, is a gamified loyalty program plugin/widget for eCommerce. You will be able to reward users with points for completing pre defined "challenges" within your store. In addition to that users can compete with each other in compeitions reflected on a leaderboard, receive badges and invite their freinds, among other gamified features.


## Requirements

- Android Studio 3.1+
- Java 1.8
- Androidx

## Installation

Gamiphy Loyalty & Rewards is available through [JitPack](https://github.com/gamiphy/loyalty-station-android-sdk). To install
it, simply add the dependency for the Gamiphy SDK in your module (app-level) Gradle file (usually app/build.gradle):

```gradle
    implementation ''
```

and make sure you have jitpack in your root-level (project-level) Gradle file (build.gradle), 
```gradle
allprojects {
    repositories {
        google()
        jcenter()
        // add jitpack if it's not added
        maven { url 'https://jitpack.io' }
    }
}
```

## Getting started

Gamiphy SDK needs to be initialized in Application class, you can do that by calling the init methid as shown below, and pass some required data / parameters that 
you can get after you signup for an account at Gamiphy. Kindly note the initilize method below. 

```kotlin
      GamiBot.getInstance().init(applicationContext, config = CoreConfig(app = botId))
```
And you can set Production environment mode.
```kotlin
      GamiBot.getInstance().init(applicationContext, config = CoreConfig(app = botId))
      .setEnvironment(GamiphyEnvironment.PROD)
```
To open the bot, use the following line.
```kotlin
    GamiBot.getInstance().open(applicationContext)
```
## Widget visitor flow 

Gamiphy Loyalty Station support the ability for the end users to navigate the different features available, without even being logged in. But whenever 
the users trying to perform actions they will be redirected to either login or signup to the application. 

You need to specify the Activity where the users can login / register in your application. You should implement OnAuthTrigger by doing as the following: 

```Kotlin
        GamiBot.getInstance().registerGamiphyOnAuthTrigger(object : OnAuthTrigger {
            override fun onAuthTrigger(signUp: Boolean) {
                // make your action here, you may start login activity
                Log.d("onAuthTrigger", "$signUp")
            }
        })
```
OnAuthTrigger method called when click signUp/login in the widget. isSignUp true for signup redirection, isSignUp false for login redirection.

In login activity, after the user logged in, set user name and email and start gamiphy view
```kotlin
    GamiphySDK.getInstance().login(User(firstName,lastName,email,hash))
```




