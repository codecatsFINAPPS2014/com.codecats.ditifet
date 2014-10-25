package com.codecats.ditifet;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ServerConnectActivity extends Activity {
	TextView loadingText;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rect_serverpayment);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		
		loadingText = (TextView) findViewById (R.id.loadingText);
		
	    //The onClick method has to be present and must take the above parameter.
	    StartLoading();

	    //This will delay the stop for 5 seconds
	    //Normally you would want to actually have this run based on some other input/data.
	    Handler handler = new Handler(); 
	    handler.postDelayed(new Runnable() { 
	        public void run() {
	          StopLoading(); 
	        } 
	    }, 5000); 
		
	}
	
	public void RunAnimation(View v)
	{

	}

	public void StartLoading() {
	    ImageView refreshImage = (ImageView) this.findViewById(R.id.anim_loading);
	    refreshImage.setImageDrawable(getResources().getDrawable(R.drawable.loading_circle));
	    Animation rotateLoading = AnimationUtils.loadAnimation(this, R.anim.rotate);
	    refreshImage.clearAnimation();
	    refreshImage.setAnimation(rotateLoading);
	}

	public void StopLoading() 
	{
	    ImageView refreshImage = (ImageView) this.findViewById(R.id.anim_loading);
	    if (refreshImage.getAnimation() != null)
	    {
	        refreshImage.clearAnimation();
	        refreshImage.setImageDrawable(getResources().getDrawable(R.drawable.loading_circle));
	    }
	    //thank you activity
		Intent t = new Intent(getApplicationContext(),ThankYouActivity.class);   
		startActivity(t);
		finish();
	}	
	
}
