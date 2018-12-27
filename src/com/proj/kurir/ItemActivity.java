package com.proj.kurir;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ListActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class ItemActivity extends ListActivity implements FetchDataListener3 {

	private ApplicationAdapter3 adapter;
	SharedPreferences datfromnamatoko;
	String tid,tnama,tlatitude,tlongitude;
	TextView warung;
	ImageView imageMakanan,imageMinuman,imageMakananMinuman;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item);
		
		ActionBar mActionBar=getActionBar();
		mActionBar.setDisplayShowHomeEnabled(false);
		mActionBar.setDisplayShowTitleEnabled(false);
		LayoutInflater mInflater=LayoutInflater.from(this);
		
		View mCustomView=mInflater.inflate(R.layout.custom_layout,null);
		TextView mTitleTextView=(TextView) mCustomView.findViewById(R.id.textViewcustom);
		mTitleTextView.setText("Daftar Menu");
		
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
		datfromnamatoko=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		tid=datfromnamatoko.getString("tid", "");
		tnama=datfromnamatoko.getString("tnama", "");
		tlatitude=datfromnamatoko.getString("tlatitude", "");
		tlongitude=datfromnamatoko.getString("tlongitude", "");
		warung=(TextView) findViewById(R.id.titlewarung);
		warung.setText(tnama);
		
		
		imageMakanan=(ImageView) findViewById(R.id.imageMakanan);
		imageMinuman=(ImageView) findViewById(R.id.imageMinuman);
		imageMakananMinuman=(ImageView) findViewById(R.id.imageMakananMinuman);
		
		imageMakanan.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String url="http://ubbitclub.com/good/listmenumakanan.php";
				FetchDataTask3 task=new FetchDataTask3(ItemActivity.this);
		    	task.execute(url,tid);
				
			}
		});
		
		imageMinuman.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String url="http://ubbitclub.com/good/listmenuminuman.php";
				FetchDataTask3 task=new FetchDataTask3(ItemActivity.this);
		    	task.execute(url,tid);
				
			}
		});
		
		imageMakananMinuman.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String url="http://ubbitclub.com/good/list_menu.php";
				FetchDataTask3 task=new FetchDataTask3(ItemActivity.this);
		    	task.execute(url,tid);
			}
		});
		
		initView();
	}
	
	private void initView()
	{
		//dialog=ProgressDialog.show(this, "", "Sedang memuat informasi....");
		
		
		String url="http://ubbitclub.com/good/list_menu.php";
		FetchDataTask3 task=new FetchDataTask3(ItemActivity.this);
    	task.execute(url,tid);
	    
	    
		
	}
	
	@Override
	public void onFetchComplete(List<Application3> data) {
		// TODO Auto-generated method stub
		//if(dialog!=null)
		//{
		//	dialog.dismiss();
		//}
		ProgressBar spinner = (ProgressBar) findViewById(R.id.progressBarProcess);
		spinner.setVisibility(View.GONE);
		
		LinearLayout layoutmenu =(LinearLayout) findViewById(R.id.tabelmenu);
		layoutmenu.setVisibility(View.VISIBLE);
		
		adapter=new ApplicationAdapter3(this,data);
		setListAdapter(adapter);
		
		//Toast.makeText(getApplicationContext(), Toast.LENGTH_SHORT).show();
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
	
	
}
