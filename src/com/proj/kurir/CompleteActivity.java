package com.proj.kurir;


import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;


public class CompleteActivity extends ListActivity implements FetchDataListener4 {

	private ProgressDialog dialog;
	private ApplicationAdapter4 adapter;
	String holder;
	String nama;
	SharedPreferences datfrommasuk;
	Button button1;
	Button hapus;
	private static final String REGISTER_URL="http://ubbitclub.com/good/hapus_riwayat.php";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_complete);
		
		hapus=(Button) findViewById(R.id.buttonhapusriwayat);
		
		datfrommasuk=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		nama=datfrommasuk.getString("dataloginpengguna", "");
		
		initView();
		
		
		hapus.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(v==hapus)
				{
					loginUser();
				}
			}
		});
	}
	
	private void loginUser()
	{
	
		String bemail=nama;


		login(bemail);
		

    	
	}
	
	
	
	private void login(String bemail)
	{
		class RegisterUser extends AsyncTask<String, Void, String>
		{
			ProgressDialog loading;
			RegisterUserClass ruc=new RegisterUserClass();
			
			@Override
			protected void onPreExecute()
			{
				super.onPreExecute();
				
			}
			
			@Override
			protected void onPostExecute(String s)
			{
				super.onPostExecute(s);
			
				Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
				if(s.equals("Berhasil, riwayat pesanan anda telah dihapus."))
				{
					String url="http://ubbitclub.com/good/message_mart.php";
            		FetchDataTask4 task=new FetchDataTask4(CompleteActivity.this);
            		task.execute(url,nama);
	
				}
				else if(s.equals("<html>"))
				{
					Toast.makeText(getApplicationContext(), "Mohon periksa kembali koneksi internet anda.", Toast.LENGTH_SHORT).show();
				}

				else
				{
					Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
				}

			}
			
			@Override
			protected String doInBackground(String... params) {
				// TODO Auto-generated method stub
				HashMap<String,String> data=new HashMap<String,String>();

				data.put("email",params[0]);
			
				
				String result=ruc.sendPostRequest(REGISTER_URL, data);
				
				if(isNetworkAvailable()==false)
				{
					result="Maaf, anda sedang tidak  terhubung ke jaringan.";
				}
				return result;
			}
			
			public boolean isNetworkAvailable()
			{
				ConnectivityManager cm=(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
				
				NetworkInfo netInfo=cm.getActiveNetworkInfo();
				
				if(netInfo!=null&&netInfo.isConnectedOrConnecting())
				{
					return true;
				}
				return false;
			}
			
		}
		
		RegisterUser ru=new RegisterUser();
		ru.execute(bemail);
	}
	
	private void initView()
	{
		//dialog=ProgressDialog.show(this, "", "Sedang memuat informasi....");
		
		final Handler handler = new Handler();
	    Timer timer = new Timer();
	    TimerTask doAsynchronousTask = new TimerTask() {
	        @Override
	        public void run() {
	            handler.post(new Runnable() {
	                public void run() {
	                    try {
	                    	
	                		String url="http://ubbitclub.com/good/message_mart.php";
	                		FetchDataTask4 task=new FetchDataTask4(CompleteActivity.this);
	                		task.execute(url,nama);
	                		
	                    } catch (Exception e) {
	                        e.printStackTrace();
	                    }
	                }
	            });
	        }
	    };
	    timer.schedule(doAsynchronousTask, 0, 60000); //execute in every 10 sec
	
	}

	@Override
	public void onFetchComplete(List<Application4> data) {
		// TODO Auto-generated method stub
		//if(dialog!=null)
		//{
		//	dialog.dismiss();
		//}
		ProgressBar spinner = (ProgressBar) findViewById(R.id.progressBarProcess2);
		spinner.setVisibility(View.GONE);
		int index = getListView().getFirstVisiblePosition();
		View v = getListView().getChildAt(0);
		int top = (v == null) ? 0 : (v.getTop() - getListView().getPaddingTop());
		
		

	
		adapter=new ApplicationAdapter4(this,data);
		
		if(adapter.getCount()==0)
		{
			hapus.setVisibility(View.INVISIBLE);
		}
		else
		{
			hapus.setVisibility(View.VISIBLE);
		}
		setListAdapter(adapter);
		getListView().setSelectionFromTop(index, top);
	}

	@Override
	public void onFetchFailure(String msg) {
		// TODO Auto-generated method stub
		//if(dialog!=null)
		//{
			//dialog.dismiss();
			
		//}
		ProgressBar spinner = (ProgressBar) findViewById(R.id.progressBarProcess2);
		spinner.setVisibility(View.GONE);
		
		//Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
		
	}
}
