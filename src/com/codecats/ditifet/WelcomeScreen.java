package com.codecats.ditifet;

import java.util.List;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.TextView;
import android.os.Vibrator;

public class WelcomeScreen extends Activity{
	private TextView mTextView;

	//iBeacon 
	private BeaconManager beaconManager;
	private Beacon beacon;
	

	TextView UserTextView;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		
		//we could download all the data about the business and the person here - but we don't have an 
		//api to do so atm.
		Vibrator v = (Vibrator) this.getApplicationContext().getSystemService(getApplicationContext().VIBRATOR_SERVICE);
		// Vibrate for 500 milliseconds
		v.vibrate(500);		
	    //delay in ms
	    int DELAY = 5000;

	    Handler handler = new Handler();
	    handler.postDelayed(new Runnable() {            
	        @Override
	        public void run() {
	    		Intent t = new Intent(getApplicationContext(),TicketScreen.class); 
	    		startActivity(t);
	    		closeActivity();

	        }
	    }, DELAY);
	    //
	
	}
	//go go go 
	@Override
	protected void onStart() {
		super.onStart();
/*
		beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
			@Override public void onServiceReady() {
				try {
					beaconManager.startRanging(ALL_ESTIMOTE_BEACONS);
				} catch (RemoteException e) {
					Log.e(TAG, "Cannot start ranging", e);
				}
			}
		});*/           

	}

	//halt! ain selekta!
	@Override
	protected void onStop() {
/*		try {
			beaconManager.stopRanging(ALL_ESTIMOTE_BEACONS);
		} catch (RemoteException e) {
			Log.e(TAG, "Cannot stop but it does not matter now", e);
		}*/
		super.onStop();
	}    

	//za warudo
	@Override
	protected void onDestroy() {

		// When no longer needed. Should be invoked in #onDestroy.
//		beaconManager.disconnect();        
		super.onDestroy();
	}   
	protected void closeActivity() {
		// TODO Auto-generated method stub
		finish();
	}	
	
}