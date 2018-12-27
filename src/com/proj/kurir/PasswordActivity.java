package com.proj.kurir;

import java.util.HashMap;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PasswordActivity extends Activity {

	EditText namelupa;
	Button btnreset;
	private static final String REGISTER_URL="http://ubbitclub.com/good/forgot_user.php";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_password);
		
		namelupa=(EditText) findViewById(R.id.txtnamelupa);
		btnreset=(Button) findViewById(R.id.btnreset);
		
		btnreset.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(v==btnreset)
				{
					loginUser();
				}
			}
		});
	}
	private void loginUser()
	{
	
		String bemail=namelupa.getText().toString();


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
				loading=ProgressDialog.show(PasswordActivity.this, "Harap tunggu, permintaan anda sedang diproses.....", null,true,true);
			}
			
			@Override
			protected void onPostExecute(String s)
			{
				super.onPostExecute(s);
				loading.dismiss();
				for (int i=0; i < 15; i++)
				{
					Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
				}
				Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
				if(s.equals("Berhasil, periksa alamat email anda untuk mengetahui password. Apabila email tidak masuk, periksa juga folder spam anda."))
				{
					Intent i=new Intent(PasswordActivity.this,MainActivity.class);
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
