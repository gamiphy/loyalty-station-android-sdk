# loyalty-station-android-sdk

## Introduction 

Gamiphy Loyalty & Rewards, is a gamified loyalty program plugin/widget for eCommerce. You will be able to reward users with points for completing pre defined "challenges" within your store. In addition to that users can compete with each other in compeitions reflected on a leaderboard, receive badges and invite their freinds, among other gamified features.


## Requirements

- Android Studio 3.1+
- Java 1.8
- Androidx

## Installation

Gamiphy Loyalty & Rewards is available through JitPack. To install it, simply add the dependency for the Gamiphy SDK in your module (app-level) Gradle file (usually app/build.gradle):

```gradle
    implementation 'com.github.gamiphy:loyalty-station-android-sdk:<latest>'
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
    LoyaltyStation
        .setApp("5f71e34bdbaa0b0019df9c58")
        .setAgent("floward")
        .setLanguage("en_US")
        .setUser(User(
            id = "test-id",
            country = "ps",
            firstName = "Riyad",
            lastName = "Yahya",
            hash = "237ccb1812cf2c893e341788921ec62515ca6d0507d7e4577055b25b794f831c"
        ))
        .setOnAuthTriggerListener(object: OnAuthTriggerListener {
            override fun onAuthTrigger(isSignUp: Boolean) {
                // make your action here, you may start login activity
                Log.d("onAuthTrigger", "$isSignUp")
            }
        })
        .initialize(this)
```
To open the bot, use the following line.
```kotlin
    LoyaltyStation.open(applicationContext)
```


## Widget visitor flow 

Gamiphy Loyalty Station support the ability for the end users to navigate the different features available, without even being logged in. But whenever the users trying to perform actions they will be redirected to either login or signup to the application. You need to specify the Activity where the users can login / register in your application. OnAuthTrigger method called when click signUp/login in the widget. isSignUp true for signup redirection, isSignUp false for login redirection.

In login activity, after the user logged in, set user name and email and start gamiphy view
```kotlin
    LoyaltyStation.login(User(firstName,lastName,email,hash))
```


## Referral program integration

Loyalty station supports users referrals through firebase dynamic links. Gamiphy system generates a dynamic link for every user. This link includes the referrer id of the original user.
To get the benefit of the referral system, you need to pass the dynamic link parameter to the Loyalty station sdk. The SDK will handle it from there.

### Parse dynamic link
Follow the firebase official doc to parse the dynamic link and read the required parameter. You can check [here](https://firebase.google.com/docs/dynamic-links/android/receive).

### Pass referrer parameter
You need to read the `ls-referrer` parameter from the dynamic link and pass it to the Loyalty station under user.referral.referrer