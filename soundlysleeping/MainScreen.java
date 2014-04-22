package com.example.soundlysleeping;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainScreen extends Activity {

	TextView alarmButton;
	TextView themeButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		alarmButton = (TextView)findViewById(R.id.alarm_button);
		
		alarmButton.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				Intent alarmIntent = new Intent(v.getContext(), AlarmScreen.class);
				startActivity(alarmIntent);
			}
		});
		
		themeButton = (TextView)findViewById(R.id.theme_button);
		
		themeButton.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				Intent themeIntent = new Intent(v.getContext(), ThemeScreen.class);
				startActivity(themeIntent);
			}
		});
	}
	
	@Override
	public void onConfigurationChanged(Configuration config){
		super.onConfigurationChanged(config);
		setContentView(R.layout.activity_main);					//automatically looks in layout-land if landscape?
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	

}
