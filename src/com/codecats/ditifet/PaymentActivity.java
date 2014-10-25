package com.codecats.ditifet;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Vibrator;

public class PaymentActivity extends Activity{
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rect_payment);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		
		Button angry_button = (Button) findViewById(R.id.angry_btn);
		
		angry_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
        		Intent t = new Intent(getApplicationContext(),ServerConnectActivity.class);   
        		startActivity(t);
        		finish();
            }
    });
	}
}
