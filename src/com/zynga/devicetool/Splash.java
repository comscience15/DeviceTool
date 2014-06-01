package com.zynga.devicetool;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Splash extends Activity{

	@Override
	protected void onCreate(Bundle ZyngaBundle) {
		// TODO Auto-generated method stub
		super.onCreate(ZyngaBundle);
		setContentView(R.layout.splash);
		
		Thread timer = new Thread(){
			public void run(){
				try{
					sleep(5000);
				} catch (InterruptedException e){
					e.printStackTrace();
				} finally {
					Intent openMainIntent = new Intent("com.zynga.devicetool.MAINMENU");
					startActivity(openMainIntent);
				}
			}
		};
		timer.start();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();    //terminate splash screen
	}
	
}
