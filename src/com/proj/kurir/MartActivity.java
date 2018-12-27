package com.proj.kurir;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.google.android.gcm.GCMRegistrar;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Geocoder;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MartActivity extends ListActivity implements FetchDataListener2 {
	
	private ProgressDialog dialog;
	private ApplicationAdapter2 adapter;
	String holder;
	String nama;
	SharedPreferences datfrommasuk;
	Button button1;
	List<Application2> apps;
	

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mart);
		ActionBar mActionBar=getActionBar();
		mActionBar.setDisplayShowHomeEnabled(false);
		mActionBar.setDisplayShowTitleEnabled(false);
		LayoutInflater mInflater=LayoutInflater.from(this);
		
		View mCustomView=mInflater.inflate(R.layout.custom_layout,null);
		TextView mTitleTextView=(TextView) mCustomView.findViewById(R.id.textViewcustom);
		mTitleTextView.setText("Daftar Restoran dan Cafe");
		
		ImageButton imageButton=(ImageButton) mCustomView.findViewById(R.id.imageViewcustom);
		
		imageButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//new action
				finish();
			}
		});
		
		mActionBar.setCustomView(mCustomView);
		mActionBar.setDisplayShowCustomEnabled(true);
		
		datfrommasuk=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		nama=datfrommasuk.getString("dataloginpengguna", "");
	
		apps = new ArrayList<Application2>();

		initView();
		
		
		
	
	}
	
	private void initView()
	{
		//dialog=ProgressDialog.show(this, "", "Sedang memuat informasi....");
		String url="http://ubbitclub.com/good/list_toko.php";
		FetchDataTask2 task=new FetchDataTask2(MartActivity.this);
    	task.execute(url,nama);
		
	  
		
	}
	
	@Override
	public void onFetchComplete(List<Application2> data) {
		// TODO Auto-generated method stub
		//if(dialog!=null)
		//{
		//	dialog.dismiss();
		//}
		ProgressBar spinner = (ProgressBar) findViewById(R.id.progressBarProcess);
		spinner.setVisibility(View.GONE);
		// save index and top position
		
		
		apps.addAll(data);
		
		adapter=new ApplicationAdapter2(this,data);
		setListAdapter(adapter);
		
	

		// ...

		// restore index and position
		
		
		//apps.addAll(data);
		//adapter.notifyDataSetChanged();
		
		//Toast.makeText(getApplicationContext(), adapter.getItem(0).getTitle().toString(), Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onFetchFailure(String msg) {
		// TODO Auto-generated method stub
		//if(dialog!=null)
		//{
			//dialog.dismiss();
			
		//}
		ProgressBar spinner = (ProgressBar) findViewById(R.id.progressBarProcess);
		spinner.setVisibility(View.GONE);
		
		//Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
		
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
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
