package com.codecats.ditifet;

import java.util.List;

//estimote beacons
import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.os.RemoteException;

import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Vibrator;

//weareable
import android.support.wearable.view.WatchViewStub;

//notifications
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.NotificationCompat.WearableExtender;

public class MainActivity extends Activity {

	private TextView mTextView;

	//iBeacon 
	private BeaconManager beaconManager;
	private Beacon beacon;
    String ESTIMOTE_PROXIMITY_UUID = "B9407F30-F5F8-466E-AFF9-25556B57FE6D";
    String ENTRANCE_MAC = "D2:5E:46:19:8B:1F";
    String PROMOTION_MAC = "FD:EB:9B:B7:96:69";
    
    private Region ALL_ESTIMOTE_BEACONS = new Region("regionId", ESTIMOTE_PROXIMITY_UUID, null, null);	
    final String TAG = "TEST";	
	
    TextView UserTextView;
    TextView scanningText;
    //weareable
	WatchViewStub stub;
	
	//Notifications 
	
	//ImageView
	ImageView logo;
	ImageView logo2;
	
    boolean activated=false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rect_black);
		/*getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		PowerManager pm = (PowerManager) getSystemService(getApplicationContext().POWER_SERVICE);
		WakeLock wl = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK, "DoNotDimScreen");
		WindowManager.LayoutParams layout = getWindow().getAttributes();
		layout.screenBrightness = 1F;
		getWindow().setAttributes(layout);*/
		//setting up the beaconManager
        beaconManager = new BeaconManager(this);
        logo = (ImageView) findViewById(R.id.displayImage);
        logo2 = (ImageView) findViewById(R.id.displayImage2);

        // Should be invoked in #onCreate.
        beaconManager.setRangingListener(new BeaconManager.RangingListener() {
          @Override public void onBeaconsDiscovered(Region region, List<Beacon> beacons) {
            Log.d(TAG, "Ranged beacons: " + beacons);
    		
            /*stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
    		stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
    		stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
    			@Override
    			public void onLayoutInflated(WatchViewStub stub) {
    				mTextView = (TextView) stub.findViewById(R.id.text);
            		UserTextView = (TextView) findViewById(R.id.welcomeString);

    				
    				
    			}
    		});*/    
            
    		scanningText = (TextView) findViewById(R.id.scanningtext);
            for (int foo=0;foo<beacons.size()-1;foo++)
            {        
    			//checking if store beacon is in range - actually checking for the entrance beacon
            	if (beacons.get(foo).getMacAddress().equalsIgnoreCase(ENTRANCE_MAC) && !activated)
            	{
            		changeVISIBILITY();

            		activated = true;
            		//sendInfoToClerk(); //
            		scanningText.setText("BEACON FOUND!");
            	    int DELAY = 3000;

            	    Handler handler = new Handler();
            	    handler.postDelayed(new Runnable() {            
            	        @Override
            	        public void run() {
            	        	Intent t = new Intent(getApplicationContext(),WelcomeScreen.class);   
                    		
                    		startActivity(t);
                    		closeActivity();
            	        }
            	    }, DELAY);
            		

            		//UserTextView.setText("NOT FAIL!");
            		//welcomeToStore();
            		/*System.out.println("OMFG WINZ!");
            		System.out.println("WTFBBQ beacon my bacon "+beacons.get(foo));*/
            	}
            	else 
            		if (beacons.get(foo).getMacAddress().equalsIgnoreCase(PROMOTION_MAC) && !activated)
            		{
            			vibrateOut();

            				// TODO Auto-generated method stub
            				int notificationId = 001;
            				final NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
            				activated = true;

            			    // This notification will be shown only on watch
            			    final NotificationCompat.Builder wearableNotificationBuilder = new NotificationCompat.Builder(getApplicationContext())
            			        .setSmallIcon(R.drawable.ic_launcher)
            			        .setContentTitle("2x1")
            			        .setContentText("If you order today before 2 p.m.")
            			        .setOngoing(false)
            			        .setOnlyAlertOnce(true)
            			        .setGroup("GROUP")
            			        .extend(new WearableExtender().setBackground(BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.sfback)))
            			        .setGroupSummary(false);
            			    	
            			   
            			    notificationManager.notify(1, wearableNotificationBuilder.build());
            			    
            		}
            }
            
          }
        });				
        
        
        //createNotification();

		//Toast.makeText(getApplicationContext(), "TEST de TOAST", Toast.LENGTH_SHORT).show();

	}

	protected void vibrateOut() {
		// TODO Auto-generated method stub
		Vibrator v = (Vibrator) this.getApplicationContext().getSystemService(getApplicationContext().VIBRATOR_SERVICE);
		// Vibrate for 500 milliseconds
		v.vibrate(500);		
	}

	protected void changeVISIBILITY() {
		// TODO Auto-generated method stub
	    int DELAY = 2000;

	    Handler handler = new Handler();
	    handler.postDelayed(new Runnable() {            
	        @Override
	        public void run() {
	    		logo.setVisibility(View.GONE);
	    		logo2.setVisibility(View.VISIBLE);
	        }
	    }, DELAY);

	}

	private void createNotification() {
		// TODO Auto-generated method stub
		int notificationId = 001;
		final NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);



	    // This notification will be shown only on watch
	    final NotificationCompat.Builder wearableNotificationBuilder = new NotificationCompat.Builder(this)
	        .setSmallIcon(R.drawable.ic_launcher)
	        .setContentTitle("Title wearable")
	        .setContentText("Text wearable")
	        .setOngoing(false)
	        .setOnlyAlertOnce(true)
	        .setGroup("GROUP")
	        .setGroupSummary(false);
	    	
	   
	    notificationManager.notify(1, wearableNotificationBuilder.build());
	}

	protected void closeActivity() {
		// TODO Auto-generated method stub
		finish();
	}

	//go go go 
    @Override
    protected void onStart() {
      super.onStart();

      beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
        @Override public void onServiceReady() {
          try {
            beaconManager.startRanging(ALL_ESTIMOTE_BEACONS);
          } catch (RemoteException e) {
            Log.e(TAG, "Cannot start ranging", e);
          }
        }
      });           
      
    }
    
    //halt! ain selekta!
    @Override
    protected void onStop() {
        try {
            beaconManager.stopRanging(ALL_ESTIMOTE_BEACONS);
          } catch (RemoteException e) {
            Log.e(TAG, "Cannot stop but it does not matter now", e);
          }
      super.onStop();
    }    
    
    //za warudo
    @Override
    protected void onDestroy() {

      // When no longer needed. Should be invoked in #onDestroy.
      beaconManager.disconnect();        
      super.onDestroy();
    }    	
	
}
