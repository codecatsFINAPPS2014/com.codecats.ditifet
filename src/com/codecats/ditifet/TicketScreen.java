package com.codecats.ditifet;
import java.util.List;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Vibrator;

public class TicketScreen extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ticket);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		
	    //delay in ms
	    int DELAY = 5000;

	    Handler handler = new Handler();
	    handler.postDelayed(new Runnable() {            
	        @Override
	        public void run() {
	    		Intent t = new Intent(getApplicationContext(),PaymentActivity.class);   
	    		startActivity(t);   
	    		finish();
	        }
	    }, DELAY);

		
	}
}
