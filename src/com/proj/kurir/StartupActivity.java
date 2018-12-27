package com.proj.kurir;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

public class StartupActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_startup);
		
		LocationManager manager=(LocationManager) getSystemService(Context.LOCATION_SERVICE);
		if(!manager.isProviderEnabled(LocationManager.GPS_PROVIDER))
		{
			
			showSettingsAlert();
		}
		else
		{
			Intent gotomain=new Intent(StartupActivity.this,MainActivity.class);
			startActivity(gotomain);
		}
		
	}
	
	void showSettingsAlert()
	{
		AlertDialog.Builder alertDialog=new AlertDialog.Builder(StartupActivity.this);
		
		alertDialog.setTitle("Pengaturan GPS");
		
		alertDialog.setMessage("GPS tidak aktif, aktifkan?");
		
		alertDialog.setPositiveButton("Aktifkan", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
				startActivity(intent);
			}
		});
		
		alertDialog.setNegativeButton("Tutup", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.cancel();
				System.exit(0);
				
			}
		});
		
		alertDialog.show();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.startup, menu);
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
}
