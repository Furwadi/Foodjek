package com.proj.kurir;


import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class TentangActivity extends Activity {

	TextView textView60,textView61,textView62,textView63,textView64,textView65,textView66,textView67,textView68,textView69,textView70,textView71,textView72,textView73,textView74,textView75,textView76,textView77,textView78,textView79,textView80,textView81,textView82,textView83,textView84,textView85,textView86,textView87,textView88,textView89,textView90,textView91,textView92,textView93,textView94,textView95,textView96,textView97,textView98,textView99,textView100,textView101,textView102,textView103,textView104,textView105,textView106,textView107,textView108,textView109,textView110,textView111,textView112,textView113,textView114,textView115;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tentang);
		ActionBar mActionBar=getActionBar();
		mActionBar.setDisplayShowHomeEnabled(false);
		mActionBar.setDisplayShowTitleEnabled(false);
		LayoutInflater mInflater=LayoutInflater.from(this);
		
		View mCustomView=mInflater.inflate(R.layout.custom_layout,null);
		TextView mTitleTextView=(TextView) mCustomView.findViewById(R.id.textViewcustom);
		mTitleTextView.setText("Tentang Good-Jek");
		
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
		
		textView60=(TextView) findViewById(R.id.textView60);
		textView61=(TextView) findViewById(R.id.textView61);
		textView62=(TextView) findViewById(R.id.textView62);
		textView63=(TextView) findViewById(R.id.textView63);
		textView64=(TextView) findViewById(R.id.textView64);
		textView65=(TextView) findViewById(R.id.textView65);
		textView66=(TextView) findViewById(R.id.textView66);
		textView67=(TextView) findViewById(R.id.textView57);
		textView68=(TextView) findViewById(R.id.textView68);
		textView69=(TextView) findViewById(R.id.textView69);
		textView70=(TextView) findViewById(R.id.textView70);
		textView71=(TextView) findViewById(R.id.textView71);
		textView72=(TextView) findViewById(R.id.textView72);
		textView73=(TextView) findViewById(R.id.textView73);
		textView74=(TextView) findViewById(R.id.textView74);
		textView75=(TextView) findViewById(R.id.textView75);
		textView76=(TextView) findViewById(R.id.textView76);
		textView77=(TextView) findViewById(R.id.textView77);
		textView78=(TextView) findViewById(R.id.textView78);
		textView79=(TextView) findViewById(R.id.textView79);
		textView80=(TextView) findViewById(R.id.textView80);
		textView81=(TextView) findViewById(R.id.textView81);
		textView82=(TextView) findViewById(R.id.textView82);
		textView83=(TextView) findViewById(R.id.textView83);
		textView84=(TextView) findViewById(R.id.textView84);
		textView85=(TextView) findViewById(R.id.textView85);
		textView86=(TextView) findViewById(R.id.textView86);
		textView87=(TextView) findViewById(R.id.textView87);
		textView88=(TextView) findViewById(R.id.textView88);
		textView89=(TextView) findViewById(R.id.textView89);
		textView90=(TextView) findViewById(R.id.textView90);
		textView91=(TextView) findViewById(R.id.textView91);
		textView92=(TextView) findViewById(R.id.textView92);
		textView93=(TextView) findViewById(R.id.textView93);
		textView94=(TextView) findViewById(R.id.textView94);
		textView95=(TextView) findViewById(R.id.textView95);
		textView96=(TextView) findViewById(R.id.textView96);
		textView97=(TextView) findViewById(R.id.textView97);
		textView98=(TextView) findViewById(R.id.textView98);
		textView99=(TextView) findViewById(R.id.textView99);
		textView100=(TextView) findViewById(R.id.textView100);
		textView101=(TextView) findViewById(R.id.textView101);
		textView102=(TextView) findViewById(R.id.textView102);
		textView103=(TextView) findViewById(R.id.textView103);
		textView104=(TextView) findViewById(R.id.textView104);
		textView105=(TextView) findViewById(R.id.textView105);
		textView106=(TextView) findViewById(R.id.textView106);
		textView107=(TextView) findViewById(R.id.textView107);
		textView108=(TextView) findViewById(R.id.textView108);
		textView109=(TextView) findViewById(R.id.textView109);
		textView110=(TextView) findViewById(R.id.textView110);
		textView111=(TextView) findViewById(R.id.textView111);
		textView112=(TextView) findViewById(R.id.textView112);
		textView113=(TextView) findViewById(R.id.textView113);
		textView114=(TextView) findViewById(R.id.textView114);
		textView115=(TextView) findViewById(R.id.textView115);
		
		Typeface myCustomFont=Typeface.createFromAsset(getAssets(), "fonts/calibri.ttf");
		textView60.setTypeface(myCustomFont);
		textView61.setTypeface(myCustomFont);
		textView62.setTypeface(myCustomFont);
		textView63.setTypeface(myCustomFont);
		textView64.setTypeface(myCustomFont);
		textView65.setTypeface(myCustomFont);
		textView66.setTypeface(myCustomFont);
		textView67.setTypeface(myCustomFont);
		textView68.setTypeface(myCustomFont);
		textView69.setTypeface(myCustomFont);
		textView70.setTypeface(myCustomFont);
		textView71.setTypeface(myCustomFont);
		textView72.setTypeface(myCustomFont);
		textView73.setTypeface(myCustomFont);
		textView74.setTypeface(myCustomFont);
		textView75.setTypeface(myCustomFont);
		textView76.setTypeface(myCustomFont);
		textView77.setTypeface(myCustomFont);
		textView78.setTypeface(myCustomFont);
		textView79.setTypeface(myCustomFont);
		textView80.setTypeface(myCustomFont);
		textView81.setTypeface(myCustomFont);
		textView82.setTypeface(myCustomFont);
		textView83.setTypeface(myCustomFont);
		textView84.setTypeface(myCustomFont);
		textView85.setTypeface(myCustomFont);
		textView86.setTypeface(myCustomFont);
		textView87.setTypeface(myCustomFont);
		textView88.setTypeface(myCustomFont);
		textView89.setTypeface(myCustomFont);
		textView90.setTypeface(myCustomFont);
		textView91.setTypeface(myCustomFont);
		textView92.setTypeface(myCustomFont);
		textView93.setTypeface(myCustomFont);
		textView94.setTypeface(myCustomFont);
		textView95.setTypeface(myCustomFont);
		textView96.setTypeface(myCustomFont);
		textView97.setTypeface(myCustomFont);
		textView98.setTypeface(myCustomFont);
		textView99.setTypeface(myCustomFont);
		textView100.setTypeface(myCustomFont);
		textView101.setTypeface(myCustomFont);
		textView102.setTypeface(myCustomFont);
		textView103.setTypeface(myCustomFont);
		textView104.setTypeface(myCustomFont);
		textView105.setTypeface(myCustomFont);
		textView106.setTypeface(myCustomFont);
		textView107.setTypeface(myCustomFont);
		textView108.setTypeface(myCustomFont);
		textView109.setTypeface(myCustomFont);
		textView110.setTypeface(myCustomFont);
		textView111.setTypeface(myCustomFont);
		textView112.setTypeface(myCustomFont);
		textView113.setTypeface(myCustomFont);
		textView114.setTypeface(myCustomFont);
		textView115.setTypeface(myCustomFont);
		
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
