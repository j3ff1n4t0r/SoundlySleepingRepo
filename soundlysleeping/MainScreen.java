package com.example.soundlysleeping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.soundlysleeping.R;
import com.example.soundlysleeping.R.drawable;
import com.example.soundlysleeping.R.id;
import com.example.soundlysleeping.R.layout;
import com.example.soundlysleeping.R.menu;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

public class MainScreen extends Activity {

	ArrayAdapter adapter;
	ImageView mainScreenPic;
	ListView mainScreenList;
	List<String> themeList;
	HashMap<String, Theme> themeListData;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mainScreenPic = (ImageView)findViewById(R.id.mainScreenPic);
		mainScreenList = (ListView)findViewById(R.id.mainScreenList);
		
		setupThemes();
		setupListView();

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
	
	public void setupThemes(){
		/* ********************************************************************
		 * To add new themes, create a new theme with pic, track 1 and track 2
		 * Add pic to /drawable and tracks to /assets
		 * Add theme to both themeList and themeListData
		 * *********************************************************************/
		Theme jungleTheme = new Theme("jungle", R.drawable.jungle_pic, "jungle_track_1", "jungle_track_2");
		Theme riverTheme = new Theme("river", R.drawable.river_pic, "river_track_1", "river_track_2");
		Theme oceanTheme = new Theme("ocean", R.drawable.ocean_pic, "ocean_track_1", "ocean_track_2");
		
		themeList = new ArrayList<String>();		//exists for easy ListView conversion while I figure out Adapters
		themeList.add(jungleTheme.themeName);
		themeList.add(riverTheme.themeName);
		themeList.add(oceanTheme.themeName);
		
		themeListData = new HashMap<String, Theme>();
		themeListData.put(jungleTheme.themeName, jungleTheme);
		themeListData.put(riverTheme.themeName, riverTheme);
		themeListData.put(oceanTheme.themeName, oceanTheme);
	}//setupThemes

	public void setupListView(){
		adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1 , themeList);
		
		mainScreenList.setAdapter(adapter);
		mainScreenList.setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView<?> av, View v, int listItem, long unsureWhatThisIsCurrently){
				
				String themeName = themeList.get(listItem);	
				Log.d("Clicked", themeName);
				
				Intent musicIntent = new Intent(v.getContext(), MusicPlayer.class);
				musicIntent.putExtra("Theme", themeListData.get(themeName));
				startActivity(musicIntent);
			}
		});
	}
}
