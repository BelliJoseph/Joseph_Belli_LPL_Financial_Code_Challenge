# LPL Financial Challenge

## Design
This project uses the MVVM + clean architecture using a repository layer for more abstraction. 
This project uses Compose LazyColumn component to display the list of Posts. 
The builder pattern was used to create Room Database, Gson, OkHttp, and Retrofit.

## Portrait Screenshot
![image](https://github.com/user-attachments/assets/28520dd0-ce23-4c48-87bd-88b5b43154d7)

## Landscape Screenshot
![image](https://github.com/user-attachments/assets/25ee3bfd-2bbd-4ded-aabd-6d64d65d2e82)

## Libraries
OkHttp, LoggingInterceptor, and Retrofit were used for network calls for ease of use and reliability.
```
implementation 'com.squareup.retrofit2:retrofit:2.11.0'
implementation 'com.squareup.okhttp3:okhttp:4.12.0'
implementation 'com.squareup.okhttp3:logging-interceptor:4.12.0'
```

Hilt was used to follow SOLID Principle of Dependency Inversion to provide abstractions to classes.
This reduces boilerplate code while making the codebase easier to test by being able to mock injected objects.
```
implementation 'com.google.dagger:hilt-android:2.55'
implementation 'com.google.dagger:hilt-android-compiler:2.55'
```

Gson was used for converting JSON to Kotlin data class objects.
Gson was used for it's speed of converting and ease of use.
```
implementation 'com.squareup.retrofit2:converter-gson:2.11.0'
implementation 'com.google.code.gson:gson:2.12.1'
```

Room Database was used for data persistence, for type safety, and efficiency.
```
implementation 'androidx.room:room-runtime:2.6.1'
implementation 'androidx.room:room-compiler:2.6.1'
implementation 'androidx.room:room-ktx:2.6.1'
```

Coroutines was used for long running tasks for network calls and database queries.
Coroutines provides a lightweight method of aasynchronous execution by offloading heavy work from the Main thread.
