package com.example.soundlysleeping;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.widget.ImageView;

public class MusicPlayer extends Activity {
	Theme theme;
	MediaPlayer mp;
	ImageView themePicture;
	PendingIntent pIntent;
	BroadcastReceiver bReceiver;
	AlarmManager alarm;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.music_player);

		//find Views
		themePicture = (ImageView)findViewById(R.id.music_player_image);
		
		alarmSetup();
		playerSetup();
		
	}
	
	@Override
	public void onResume(){
		super.onResume();
		mp.start();
	}
	
	@Override
	public void onDestroy(){
		super.onDestroy();
		//release mediaplayer resources
		mp.stop();
		mp.release();
		
		//release alarmManager resources
		alarm.cancel(pIntent);
		unregisterReceiver(bReceiver);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.music_player, menu);
		return true;
	}
	
	public void alarmSetup(){
		bReceiver = new BroadcastReceiver() {
			@Override
        	public void onReceive(Context c, Intent i) {
				try{
					mp.reset();
					AssetFileDescriptor descriptor = getAssets().openFd(theme.themeTrack2 + ".mp3");
					mp.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
					mp.prepare();
					mp.setLooping(true);
					mp.start();
				}catch(Exception e){
					
				}
        	}
		};
		registerReceiver(bReceiver, new IntentFilter("com.example.soundlysleeping") );
		pIntent = PendingIntent.getBroadcast(this, 0, new Intent("com.example.soundlysleeping"), 0);
		alarm = (AlarmManager)(this.getSystemService(Context.ALARM_SERVICE ));
		
		alarm.set( AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + 5000, pIntent );	//check to see if track 2 plays after 5 seconds
	}//alarmSetup

	public void playerSetup(){
		mp = new MediaPlayer();	

		
		//get information from Intent
		Bundle extras = getIntent().getExtras();
		if(extras != null)
		{
			theme = extras.getParcelable("Theme");
		}
		
		themePicture.setImageResource(theme.themePicId);
		
		try{
			mp.reset();
			AssetFileDescriptor descriptor = getAssets().openFd(theme.themeTrack1 + ".mp3");
			mp.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
			mp.prepare();
			mp.setLooping(true);
			mp.start();
		}catch(Exception e){
			
		}
	}//playerSetup
	
}//MusicPlayer
