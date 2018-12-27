package com.proj.kurir;

import java.util.HashMap;

import com.google.android.gcm.GCMRegistrar;

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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LogonActivity extends Activity {

	Button gotodaftar,buttonlogin;
	TextView gotopass;
	EditText username,password;
	private static final String REGISTER_URL="http://ubbitclub.com/good/login_user.php";
	SharedPreferences sharedpreferences;
	String user_dat;
	String TAG = "GCM Tutorial::Activity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_logon);
		sharedpreferences=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

		
		gotodaftar=(Button) findViewById(R.id.btndaftarto);
		gotopass=(TextView) findViewById(R.id.txtsetpass);
		username=(EditText) findViewById(R.id.txtusername);
		password=(EditText) findViewById(R.id.txtpassword);
		buttonlogin=(Button) findViewById(R.id.btnlogin);
		
		GCMRegistrar.checkDevice(this);
		GCMRegistrar.checkManifest(this);
		
		GCMRegistrar.register(LogonActivity.this,
				 GCMIntentService.SENDER_ID);
		
		gotodaftar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent gotodaftar=new Intent(LogonActivity.this,RegisterActivity.class);
				startActivity(gotodaftar);
				
			}
		});
		
		
		
		gotopass.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent gotodaftar=new Intent(LogonActivity.this,PasswordActivity.class);
				startActivity(gotodaftar);
				
			}
		});
		
		buttonlogin.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(v==buttonlogin)
				{
					loginUser();
				}
			}
		});
	}
	
	private void loginUser()
	{
		String busername=username.getText().toString();
		String bpassword=password.getText().toString();
		String regid=GCMRegistrar.getRegistrationId(getApplicationContext());
		login(busername,bpassword,regid);
		

    	
	}
	
	
	
	private void login(String busername, String bpassword, String regid)
	{
		class RegisterUser extends AsyncTask<String, Void, String>
		{
			ProgressDialog loading;
			RegisterUserClass ruc=new RegisterUserClass();
			
			@Override
			protected void onPreExecute()
			{
				super.onPreExecute();
				loading=ProgressDialog.show(LogonActivity.this, "Harap tunggu, permintaan anda sedang diproses.....", null,true,true);
			}
			
			@Override
			protected void onPostExecute(String s)
			{
				super.onPostExecute(s);
				loading.dismiss();
				Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
				if(s.equals("Anda telah berhasil masuk, selamat menggunakan aplikasi Food-Jek."))
				{
					SharedPreferences.Editor editor=sharedpreferences.edit();
			    	editor.putString("dataloginpengguna", username.getText().toString());
			    	editor.commit();
			    	
					Intent i=new Intent(LogonActivity.this,MainActivity.class);
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
				
				data.put("username",params[0]);
				data.put("password", params[1]);
				data.put("regid", params[2]);
								
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
		ru.execute(busername,bpassword,regid);
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
