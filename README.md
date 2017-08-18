# Api Startup Android

This project created as an API library for Laravel API Startup. Library includes OAuth 2.0, Login, Register, One Signal Push Notification, Simple Feeds and Settings features. 

## Getting Started

1. Clone repo
2. Prepare settings in project's build.gradle file
3. Build and done

### Prerequisites and Dependencies

1. In order to use this library you have to have a backend server. We created backend service. You can clone and start using from here. github.com/yedincisenol/laravel-api-startup 

2. This project uses 26.0.1 version and gradle 3.0.0 beta2 in order to use it update your Android Studio.


```
 compile 'com.android.support:appcompat-v7:26.0.1'
    compile 'com.android.support:design:26.0.1'
    compile 'com.android.support.constraint:constraint-layout:1.0.2'
    compile 'com.android.support:support-vector-drawable:26.0.1'
    compile 'com.android.support:support-v4:26.0.1'

    //Service Request Library
    compile 'com.squareup.retrofit2:retrofit:2.2.0'
    compile 'com.squareup.retrofit2:converter-gson:2.2.0'
    compile 'com.squareup.okhttp3:logging-interceptor:3.7.0'

    //OkHTTTP
    compile 'com.squareup.okhttp:okhttp:2.7.5'

    //Event Bus
    compile 'com.squareup:otto:1.3.8'

    //One Signal Push Notification
    compile 'com.onesignal:OneSignal:3.+@aar'

    // Required for OneSignal, even if you have added FCM.
    compile 'com.google.android.gms:play-services-gcm:+'

    // Required for geotagging
    compile "com.google.android.gms:play-services-location:+"

    // Material Design Components
    compile 'com.github.rey5137:material:1.2.4'

    // Card View
    compile 'com.android.support:cardview-v7:26.0.1'

    // App Drawer
    compile('com.mikepenz:materialdrawer:5.3.2@aar') {
        transitive = true
    }
```

### Installing

A step by step series of examples that tell you have to get a development env running

1. Clone Repo

```
git clone https://github.com/salyangoz/Api-Startup-Android.git
```

2. Prepare Settings in project's build.gradle file. There are two flavors you can define. If you have beta backend service url use it for pre and for the production URL define in prod section. These parameters will be given by your backend developer from our Laravel API Startup android package.

```
		pre {
            dimension "default"
            buildConfigField "String", 'API_URL', '"http://api-startup.yedincisenol.com"'
            buildConfigField "String", 'API_VERSION', '"v1.0"'
            buildConfigField "String", 'CLIENT_ID', '"6"'
            buildConfigField "String", 'CLIENT_KEY', '"VqDWlRQ8V8D30v61QdhguvQg54NyldALVhop0d7I"'
        }
        
    	prod {
            dimension "default"
            buildConfigField "String", 'API_URL', '"http://api-startup.yedincisenol.com"'
            buildConfigField "String", 'API_VERSION', '"v1.0"'
            buildConfigField "String", 'CLIENT_ID', '"6"'
            buildConfigField "String", 'CLIENT_KEY', '"VqDWlRQ8V8D30v61QdhguvQg54NyldALVhop0d7I"'
        }
```

3. Prepare One Signal push notification. Change one signal app id and one signal google project number that you get from One Signal dashboard. To use OneSignal visit onesignal.com  

```
		 manifestPlaceholders = [onesignal_app_id               : "92260ed3-4349-4586-9228-a7b4eb8c333f",
                                // Project number pulled from dashboard, local value is ignored.
                                onesignal_google_project_number: "529221966238"]
```

End with an example of getting some data out of the system or using it for a little demo



## Built With

* [Laravel API Startup](http://github.com/yedincisenol/laravel-api-startup) - Laravel API Startup Backend Library by yedincisenol

## Contributing

- Integrate an new service
- Open issue any bug on the project
- Add more document about the project

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags). 

## Authors

* **Salyangoz Co.** - *Initial work* - [Salyangoz Co.](https://github.com/salyangoz)


See also the list of [contributors](https://github.com/salyangoz/api-startup-android/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details




