package com.proj.kurir;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class BantuanActivity extends Activity {
	
	TextView textView060,textView061,textView062,textView063,textView064,textView065,textView066,textView067,textView068,textView069,textView070,textView071,textView072,textView073,textView074;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bantuan);
		ActionBar mActionBar=getActionBar();
		mActionBar.setDisplayShowHomeEnabled(false);
		mActionBar.setDisplayShowTitleEnabled(false);
		LayoutInflater mInflater=LayoutInflater.from(this);
		
		View mCustomView=mInflater.inflate(R.layout.custom_layout,null);
		TextView mTitleTextView=(TextView) mCustomView.findViewById(R.id.textViewcustom);
		mTitleTextView.setText("Bantuan");
		
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
		
		textView060=(TextView) findViewById(R.id.textView060);
		textView061=(TextView) findViewById(R.id.textView061);
		textView062=(TextView) findViewById(R.id.textView062);
		textView063=(TextView) findViewById(R.id.textView063);
		textView064=(TextView) findViewById(R.id.textView064);
		textView065=(TextView) findViewById(R.id.textView065);
		textView066=(TextView) findViewById(R.id.textView066);
		textView067=(TextView) findViewById(R.id.textView067);
		textView068=(TextView) findViewById(R.id.textView068);
		textView069=(TextView) findViewById(R.id.textView069);
		textView070=(TextView) findViewById(R.id.textView070);
		textView071=(TextView) findViewById(R.id.textView071);
		textView072=(TextView) findViewById(R.id.textView072);
		textView073=(TextView) findViewById(R.id.textView073);
		textView074=(TextView) findViewById(R.id.textView074);
		Typeface myCustomFont=Typeface.createFromAsset(getAssets(), "fonts/Xerox Serif Narrow.ttf");
		textView060.setTypeface(myCustomFont);
		textView061.setTypeface(myCustomFont);
		textView062.setTypeface(myCustomFont);
		textView063.setTypeface(myCustomFont);
		textView064.setTypeface(myCustomFont);
		textView065.setTypeface(myCustomFont);
		textView066.setTypeface(myCustomFont);
		textView067.setTypeface(myCustomFont);
		textView068.setTypeface(myCustomFont);
		textView069.setTypeface(myCustomFont);
		textView070.setTypeface(myCustomFont);
		textView071.setTypeface(myCustomFont);
		textView072.setTypeface(myCustomFont);
		textView073.setTypeface(myCustomFont);
		textView074.setTypeface(myCustomFont);
		
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
