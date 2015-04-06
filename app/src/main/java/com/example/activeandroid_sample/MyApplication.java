package com.example.activeandroid_sample;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.app.Application;

public class MyApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		
		// ActiveAndroidの初期化を行う
		ActiveAndroid.initialize(this);
	}
}
