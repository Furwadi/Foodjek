package com.proj.kurir;

import android.app.Activity;
import android.app.ActivityGroup;
import android.app.LocalActivityManager;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

public class ProgressActivity extends TabActivity {
	private TabHost mTabHost;
	LocalActivityManager mlam;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_progress);
		
		Resources res = getResources();
		mTabHost = getTabHost();
	
		TabHost.TabSpec spec;
		Intent intent;
		mlam = new LocalActivityManager(this, false);
	    mlam.dispatchCreate(savedInstanceState); // state will be bundle your activity state which you get in onCreate
	    mTabHost.setup(mlam);
		
		intent = new Intent(this,ProcessActivity.class);
		spec = mTabHost.newTabSpec("process")
				.setIndicator("Dalam Proses")
				.setContent(intent);
		mTabHost.addTab(spec);
		
		intent = new Intent(this,CompleteActivity.class);
		spec = mTabHost.newTabSpec("complete")
				.setIndicator("Selesai")
				.setContent(intent);
		mTabHost.addTab(spec);
		
		mTabHost.setCurrentTab(0);
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
	
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	@Override
	protected void onResume()
	{
		mlam.dispatchResume(); 
		super.onResume();
	}
	@Override
	protected void onPause()
	{
		mlam.dispatchPause(isFinishing());
		super.onPause();
	}

}
