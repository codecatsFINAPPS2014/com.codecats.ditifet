package com.codecats.ditifet;


import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat.*;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;

public class ThankYouActivity extends Activity{
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rect_thanks);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

	    int DELAY = 5000;

	    Handler handler = new Handler();
	    handler.postDelayed(new Runnable() {            
	        @Override
	        public void run() {
	    		createNotification();
	        }
	    }, DELAY);

		
		RelativeLayout a = (RelativeLayout) findViewById(R.id.allContainer);

		a.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				//finish();
			}
		});
	}
	
	private void createNotification() {
		// TODO Auto-generated method stub
		int notificationId = 001;
		final NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

		Vibrator v = (Vibrator) this.getApplicationContext().getSystemService(getApplicationContext().VIBRATOR_SERVICE);
		// Vibrate for 500 milliseconds
		v.vibrate(500);		

	    // This notification will be shown only on watch
	    final NotificationCompat.Builder wearableNotificationBuilder = new NotificationCompat.Builder(this)
	        .setSmallIcon(R.drawable.ic_launcher)
	        .setContentTitle("Points summary")
	        .setContentText("After your purchase, your total points are 80085")
	        .setOngoing(false)
	        .setOnlyAlertOnce(true)
	        .setGroup("GROUP")
	        .extend(new WearableExtender().setBackground(BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.sfback)))
	        .setGroupSummary(false);
	    	
	   
	    notificationManager.notify(1, wearableNotificationBuilder.build());
	}
	
	

}
