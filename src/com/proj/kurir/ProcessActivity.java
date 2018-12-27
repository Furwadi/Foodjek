package com.proj.kurir;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;


public class ProcessActivity extends ListActivity implements FetchDataListener {
	
	private ProgressDialog dialog;
	private ApplicationAdapter adapter;
	String holder;
	String nama;
	SharedPreferences datfrommasuk;
	Button button1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_process);
		
		datfrommasuk=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		nama=datfrommasuk.getString("dataloginpengguna", "");
		
		initView();
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
	                    	String url="http://ubbitclub.com/good/message.php";
	                		FetchDataTask task=new FetchDataTask(ProcessActivity.this);
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
	public void onFetchComplete(List<Application> data) {
		// TODO Auto-generated method stub
		//if(dialog!=null)
		//{
		//	dialog.dismiss();
		//}
		ProgressBar spinner = (ProgressBar) findViewById(R.id.progressBarProcess);
		spinner.setVisibility(View.GONE);
		int index = getListView().getFirstVisiblePosition();
		View v = getListView().getChildAt(0);
		int top = (v == null) ? 0 : (v.getTop() - getListView().getPaddingTop());
		adapter=new ApplicationAdapter(this,data);
		setListAdapter(adapter);
		getListView().setSelectionFromTop(index, top);
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
}
