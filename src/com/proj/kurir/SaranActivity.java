package com.proj.kurir;

import java.util.HashMap;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class SaranActivity extends Activity {
	
	SharedPreferences sharedpreferences2;
	String dataemailpengguna;
	Button btnsaran;
	EditText editsaran;
	private static final String REGISTER_URL="http://ubbitclub.com/good/insert_saran.php";
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_saran);
		
		ActionBar mActionBar=getActionBar();
		mActionBar.setDisplayShowHomeEnabled(false);
		mActionBar.setDisplayShowTitleEnabled(false);
		LayoutInflater mInflater=LayoutInflater.from(this);
		
		View mCustomView=mInflater.inflate(R.layout.custom_layout,null);
		TextView mTitleTextView=(TextView) mCustomView.findViewById(R.id.textViewcustom);
		mTitleTextView.setText("Saran");
		
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
		
		sharedpreferences2=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		dataemailpengguna=sharedpreferences2.getString("dataloginpengguna", "");
		
		btnsaran=(Button) findViewById(R.id.btnsaran);
		editsaran=(EditText) findViewById(R.id.editsaran);
		
		btnsaran.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(v==btnsaran)
				{
					loginUser();
				}
				
			}
		});
		
		
		
	}
	
	private void loginUser()
	{
	
		String bemail=dataemailpengguna;
		String saranku=editsaran.getText().toString();

		login(bemail,saranku);
    	
	}
	
	
	
	private void login(String bemail,String saranku)
	{
		class RegisterUser extends AsyncTask<String, Void, String>
		{
			ProgressDialog loading;
			RegisterUserClass ruc=new RegisterUserClass();
			
			@Override
			protected void onPreExecute()
			{
				super.onPreExecute();
				loading=ProgressDialog.show(SaranActivity.this, "Harap tunggu, permintaan anda sedang diproses.....", null,true,true);
			}
			
			@Override
			protected void onPostExecute(String s)
			{
				super.onPostExecute(s);
				loading.dismiss();

				Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
				if(s.equals("Berhasil, terima kasih atas saran anda untuk membangun Good-Jek menjadi lebih baik."))
				{
					Intent i=new Intent(SaranActivity.this,MainActivity.class);
					startActivity(i);
	
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
				data.put("saran",params[1]);
				
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
		ru.execute(bemail,saranku);
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
