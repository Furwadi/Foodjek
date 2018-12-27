package com.proj.kurir;


import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Geocoder;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class FoodActivity extends Activity {
	
	
	ImageButton tambah,tambah2,tambah3,tambah4,tambah5,tambah6,tambah7,tambah8,tambah9,tambah10;
	EditText pesanan,pesanan2,pesanan3,pesanan4,pesanan5,pesanan6,pesanan7,pesanan8,pesanan9,pesanan10,posisiawal,posisitujuan,keawal,ketujuan,kekirim;
	Spinner spinner1,spinner2,spinner3,spinner4,spinner5,spinner6,spinner7,spinner8,spinner9,spinner10;
	Button proses;
	TextView koordinat1,koordinat2,koordinat3,koordinat4,distance,hasiltempuh,biaya;
	String TAG = "GCM Tutorial::Activity";
	SharedPreferences sharedpreferences;
	SharedPreferences datfromawal;
	SharedPreferences sharedpreferences2;
	SharedPreferences sharedpreferences3;
	SharedPreferences sharedpreferences4;
	SharedPreferences sharedpreferences5;
	double hargaakhir1,hargaakhir2,hargaakhir3;
	int harga;
	
	String dataemailpengguna;
	String namaalamatawal,namalamattujuan,koor1,koor2,koor3,koor4,datkeawal,datkeakhir,datkekirim;
	public static final String DatPref="dataprogress";
	
		
	
	private static final String REGISTER_URL="http://ubbitclub.com/good/register.php";
	private static final String MY_PREFS_NAME = null;
	public static final String DEFAULT="N/A";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_food);
		ActionBar mActionBar=getActionBar();
		mActionBar.setDisplayShowHomeEnabled(false);
		mActionBar.setDisplayShowTitleEnabled(false);
		LayoutInflater mInflater=LayoutInflater.from(this);
		
		View mCustomView=mInflater.inflate(R.layout.custom_layout,null);
		TextView mTitleTextView=(TextView) mCustomView.findViewById(R.id.textViewcustom);
		mTitleTextView.setText("Good-Pick");
		
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
		GCMRegistrar.checkDevice(this);
		GCMRegistrar.checkManifest(this);
		sharedpreferences=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		sharedpreferences4=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

		pesanan=(EditText) findViewById(R.id.txtpesanan);
		pesanan2=(EditText) findViewById(R.id.txtpesanan2);
		pesanan3=(EditText) findViewById(R.id.txtpesanan3);
		pesanan4=(EditText) findViewById(R.id.txtpesanan4);
		pesanan5=(EditText) findViewById(R.id.txtpesanan5);
		pesanan6=(EditText) findViewById(R.id.txtpesanan6);
		pesanan7=(EditText) findViewById(R.id.txtpesanan7);
		pesanan8=(EditText) findViewById(R.id.txtpesanan8);
		pesanan9=(EditText) findViewById(R.id.txtpesanan9);
		pesanan10=(EditText) findViewById(R.id.txtpesanan10);
		spinner1=(Spinner) findViewById(R.id.spinner1);
		spinner2=(Spinner) findViewById(R.id.spinner2);
		spinner3=(Spinner) findViewById(R.id.spinner3);
		spinner4=(Spinner) findViewById(R.id.spinner4);
		spinner5=(Spinner) findViewById(R.id.spinner5);
		spinner6=(Spinner) findViewById(R.id.spinner6);
		spinner7=(Spinner) findViewById(R.id.spinner7);
		spinner8=(Spinner) findViewById(R.id.spinner8);
		spinner9=(Spinner) findViewById(R.id.spinner9);
		spinner10=(Spinner) findViewById(R.id.spinner10);
		tambah=(ImageButton) findViewById(R.id.btnadd1);
		tambah2=(ImageButton) findViewById(R.id.btnadd2);
		tambah3=(ImageButton) findViewById(R.id.btnadd3);
		tambah4=(ImageButton) findViewById(R.id.btnadd4);
		tambah5=(ImageButton) findViewById(R.id.btnadd5);
		tambah6=(ImageButton) findViewById(R.id.btnadd6);
		tambah7=(ImageButton) findViewById(R.id.btnadd7);
		tambah8=(ImageButton) findViewById(R.id.btnadd8);
		tambah9=(ImageButton) findViewById(R.id.btnadd9);
		
		posisiawal=(EditText) findViewById(R.id.lokasiawal);
		posisitujuan=(EditText) findViewById(R.id.lokasitujuan);
		proses=(Button) findViewById(R.id.buttonProses);
		koordinat1=(TextView) findViewById(R.id.koordinatawal);
		koordinat2=(TextView) findViewById(R.id.koordinattujuan);
		koordinat3=(TextView) findViewById(R.id.koordinatawal1);
		koordinat4=(TextView) findViewById(R.id.koordinattujuan2);
		distance=(TextView) findViewById(R.id.distance);
		hasiltempuh=(TextView) findViewById(R.id.hasiltempuh);
		biaya=(TextView) findViewById(R.id.biaya);
		keawal=(EditText) findViewById(R.id.detlokawal);
		ketujuan=(EditText) findViewById(R.id.detlokakhir);
		kekirim=(EditText) findViewById(R.id.detketkirim);
				
		GCMRegistrar.register(FoodActivity.this,
				 GCMIntentService.SENDER_ID);
		
		
		
		tambah.setOnClickListener(new View.OnClickListener() {
			
			LinearLayout layone =(LinearLayout) findViewById(R.id.tabel2);
			ImageButton btn =(ImageButton) findViewById(R.id.btnadd1);
			
			@Override
			public void onClick(View v) {
				
				layone.setVisibility(View.VISIBLE);
				btn.setVisibility(View.GONE);
				
			}
		});
		
		tambah2.setOnClickListener(new View.OnClickListener() {
			
			LinearLayout layone =(LinearLayout) findViewById(R.id.tabel3);
			ImageButton btn =(ImageButton) findViewById(R.id.btnadd2);
			
			@Override
			public void onClick(View v) {
				
				layone.setVisibility(View.VISIBLE);
				btn.setVisibility(View.GONE);
				
			}
		});
		
		tambah3.setOnClickListener(new View.OnClickListener() {
			
			LinearLayout layone =(LinearLayout) findViewById(R.id.tabel4);
			ImageButton btn =(ImageButton) findViewById(R.id.btnadd3);
			
			@Override
			public void onClick(View v) {
				
				layone.setVisibility(View.VISIBLE);
				btn.setVisibility(View.GONE);
				
			}
		});
		
		tambah4.setOnClickListener(new View.OnClickListener() {
			
			LinearLayout layone =(LinearLayout) findViewById(R.id.tabel5);
			ImageButton btn =(ImageButton) findViewById(R.id.btnadd4);
			
			@Override
			public void onClick(View v) {
				
				layone.setVisibility(View.VISIBLE);
				btn.setVisibility(View.GONE);
				
			}
		});
		
		tambah5.setOnClickListener(new View.OnClickListener() {
			
			LinearLayout layone =(LinearLayout) findViewById(R.id.tabel6);
			ImageButton btn =(ImageButton) findViewById(R.id.btnadd5);
			
			@Override
			public void onClick(View v) {
				
				layone.setVisibility(View.VISIBLE);
				btn.setVisibility(View.GONE);
				
			}
		});
		
		tambah6.setOnClickListener(new View.OnClickListener() {
			
			LinearLayout layone =(LinearLayout) findViewById(R.id.tabel7);
			ImageButton btn =(ImageButton) findViewById(R.id.btnadd6);
			
			@Override
			public void onClick(View v) {
				
				layone.setVisibility(View.VISIBLE);
				btn.setVisibility(View.GONE);
				
			}
		});
		
		tambah7.setOnClickListener(new View.OnClickListener() {
			
			LinearLayout layone =(LinearLayout) findViewById(R.id.tabel8);
			ImageButton btn =(ImageButton) findViewById(R.id.btnadd7);
			
			@Override
			public void onClick(View v) {
				
				layone.setVisibility(View.VISIBLE);
				btn.setVisibility(View.GONE);
				
			}
		});
		
		tambah8.setOnClickListener(new View.OnClickListener() {
			
			LinearLayout layone =(LinearLayout) findViewById(R.id.tabel9);
			ImageButton btn =(ImageButton) findViewById(R.id.btnadd8);
			
			@Override
			public void onClick(View v) {
				
				layone.setVisibility(View.VISIBLE);
				btn.setVisibility(View.GONE);
				
			}
		});
		
		tambah9.setOnClickListener(new View.OnClickListener() {
			
			LinearLayout layone =(LinearLayout) findViewById(R.id.tabel10);
			ImageButton btn =(ImageButton) findViewById(R.id.btnadd9);
			
			@Override
			public void onClick(View v) {
				
				layone.setVisibility(View.VISIBLE);
				btn.setVisibility(View.GONE);
				
			}
		});



		posisiawal.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent posawal=new Intent(FoodActivity.this,MapAwal.class);
				startActivity(posawal);
				
				SharedPreferences.Editor editor=sharedpreferences4.edit();
				editor.putString("keteranganaw", keawal.getText().toString());
				editor.putString("keterangantu", ketujuan.getText().toString());
				editor.putString("keteranganpe", kekirim.getText().toString());
				editor.commit();
			}
		});
		

		posisitujuan.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent postujuan=new Intent(FoodActivity.this,MapTujuan.class);
				startActivity(postujuan);
				
				SharedPreferences.Editor editor=sharedpreferences4.edit();
				editor.putString("keteranganaw", keawal.getText().toString());
				editor.putString("keterangantu", ketujuan.getText().toString());
				editor.putString("keteranganpe", kekirim.getText().toString());
				editor.commit();
			}
		});
		
		
		proses.setOnClickListener(new View.OnClickListener() {
			
						
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// TODO Auto-generated method stub
				if(v==proses)
				{
					loginUser();
				}
			}
		});
	
		
		//Intent a=getIntent();
		//String name=a.getStringExtra("lokawalb");
		//posisiawal.setText(name);
		
		//Intent b=getIntent();
		//String nameb=b.getStringExtra("loktujuanb");
		//posisitujuan.setText(nameb);
		sharedpreferences3=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		sharedpreferences2=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		sharedpreferences5=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		datkeawal=sharedpreferences5.getString("keteranganaw", "");
		datkeakhir=sharedpreferences5.getString("keterangantu", "");
		datkekirim=sharedpreferences5.getString("keteranganpe", "");
		
		
		if(!datkeawal.equals("") || !datkeawal.equals(null))
		{
			keawal.setText(datkeawal);
		}
		
		if(!datkeakhir.equals("") || !datkeakhir.equals(null))
		{
			ketujuan.setText(datkeakhir);
		}
		
		if(!datkekirim.equals("") || !datkekirim.equals(null))
		{
			kekirim.setText(datkekirim);
		}
		dataemailpengguna=sharedpreferences2.getString("dataloginpengguna", "");
		
		datfromawal=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		namaalamatawal=datfromawal.getString("alamat", "");
	
		namalamattujuan=datfromawal.getString("alamattujuan", "");
		
		koor1=datfromawal.getString("lon1", "");
	
		koor2=datfromawal.getString("lat1", "");
	
		koor3=datfromawal.getString("lon2", "");
	
		koor4=datfromawal.getString("lat2", "");
	
		posisiawal.setText(namaalamatawal);
		posisitujuan.setText(namalamattujuan);
		koordinat1.setText(koor1);
		if(koordinat1.getText().toString()=="")
		{
			koordinat1.setText("0.0");
		}
		koordinat2.setText(koor2);
		if(koordinat2.getText().toString()=="")
		{
			koordinat2.setText("0.0");
		}
		koordinat3.setText(koor3);
		if(koordinat3.getText().toString()=="")
		{
			koordinat3.setText("0.0");
		}
		koordinat4.setText(koor4);
		if(koordinat4.getText().toString()=="")
		{
			koordinat4.setText("0.0");
		}
		
		if(koor1=="" || koor3=="")
		{
			hasiltempuh.setText("0");
			distance.setText("0");
			biaya.setText("0");
		}
		else
		{
			hasiltempuh.setText(String.format("%.1f", CalculationByDistance()));
			distance.setText( CalculationByDistance()+"");
			biaya.setText(CalculatePrice()+"");
		}
		
		
		
		
					
	}
	
	public double CalculationByDistance() {
        int Radius = 6371;// radius of earth in Km
        double lat1 = Double.parseDouble(koordinat2.getText().toString());
        double lat2 = Double.parseDouble(koordinat4.getText().toString());
        double lon1 = Double.parseDouble(koordinat1.getText().toString());
        double lon2 = Double.parseDouble(koordinat3.getText().toString());
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double valueResult = Radius * c;
        double km = valueResult / 1;
        DecimalFormat newFormat = new DecimalFormat("####");
        int kmInDec = Integer.valueOf(newFormat.format(km));
        double meter = valueResult % 1000;
        int meterInDec = Integer.valueOf(newFormat.format(meter));
        Log.i("Radius Value", "" + valueResult + "   KM  " + kmInDec
                + " Meter   " + meterInDec);

        return Radius * c;
        
    }
	
	public int CalculatePrice()
	{
		
		
			
		if(CalculationByDistance()<=5.0)
		{
			harga=10000;
		}
		else
		{
			hargaakhir1=CalculationByDistance()-5.0;
			hargaakhir2=Math.floor(hargaakhir1);
			hargaakhir3=hargaakhir2+1.0;
			
			harga=(int) (hargaakhir3*2000);
			harga=harga+10000;
			
		}		

		return harga;
	}
	
	public void load(View view){
		
		SharedPreferences sharedPreferences = getSharedPreferences("Mydata", Context.MODE_PRIVATE);
		String lokawalb=sharedPreferences.getString("lokawalb", DEFAULT);
		
	}

	private void loginUser()
	{
	
		String awal=posisiawal.getText().toString();
		String tujuan=posisitujuan.getText().toString();
		String pesanane=pesanan.getText().toString();
		String pesanane2=pesanan2.getText().toString();
		String pesanane3=pesanan3.getText().toString();
		String pesanane4=pesanan4.getText().toString();
		String pesanane5=pesanan5.getText().toString();
		String pesanane6=pesanan6.getText().toString();
		String pesanane7=pesanan7.getText().toString();
		String pesanane8=pesanan8.getText().toString();
		String pesanane9=pesanan9.getText().toString();
		String pesanane10=pesanan10.getText().toString();
		
		String spinnere1=spinner1.getSelectedItem().toString();
		String spinnere2=spinner2.getSelectedItem().toString();
		String spinnere3=spinner3.getSelectedItem().toString();
		String spinnere4=spinner4.getSelectedItem().toString();
		String spinnere5=spinner5.getSelectedItem().toString();
		String spinnere6=spinner6.getSelectedItem().toString();
		String spinnere7=spinner7.getSelectedItem().toString();
		String spinnere8=spinner8.getSelectedItem().toString();
		String spinnere9=spinner9.getSelectedItem().toString();
		String spinnere10=spinner10.getSelectedItem().toString();
		
		String akoordinat1=koordinat1.getText().toString();
		String akoordinat2=koordinat2.getText().toString();
		String akoordinat3=koordinat3.getText().toString();
		String akoordinat4=koordinat4.getText().toString();
		String ahasiltempuh=hasiltempuh.getText().toString();
		String abiaya=biaya.getText().toString();
		String keteranganawal=keawal.getText().toString();
		String keterangantujuan=ketujuan.getText().toString();
		String keterangankirim=kekirim.getText().toString();
		
		login(pesanane,pesanane2,pesanane3,pesanane4,pesanane5,pesanane6,pesanane7,pesanane8,pesanane9,pesanane10
				,spinnere1,spinnere2,spinnere3,spinnere4,spinnere5,spinnere6,spinnere7,spinnere8,spinnere9,spinnere10
				,akoordinat1,akoordinat2,akoordinat3,akoordinat4,ahasiltempuh,abiaya,awal,tujuan,dataemailpengguna,keteranganawal,keterangantujuan,keterangankirim);

    	
	}
	
	
	
	private void login(String pesanane, String pesanane2, String pesanane3, String pesanane4, String pesanane5,String pesanane6, String pesanane7, String pesanane8, String pesanane9, String pesanane10
			, String spinnere1, String spinnere2, String spinnere3, String spinnere4, String spinnere5, String spinnere6, String spinnere7, String spinnere8, String spinnere9, String spinnere10
			,String akoordinat1,String akoordinat2, String akoordinat3,String akoordinat4,String ahasiltempuh,String abiaya,String awal,String tujuan,String dataemailpengguna,String keteranganawal,String keterangantujuan,String keterangankirim)
	{
		class RegisterUser extends AsyncTask<String, Void, String>
		{
			ProgressDialog loading;
			RegisterUserClass ruc=new RegisterUserClass();
			
			@Override
			protected void onPreExecute()
			{
				super.onPreExecute();
				loading=ProgressDialog.show(FoodActivity.this, "Harap tunggu, permintaan anda sedang diproses.....", null,true,true);
			}
			
			@Override
			protected void onPostExecute(String s)
			{
				super.onPostExecute(s);
				loading.dismiss();
				//Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
				if(s.equals("Berhasil, pesanan anda akan segera kami proses."))
				{
					
				
					Intent i=new Intent(FoodActivity.this,MainActivity.class);
					startActivity(i);
					SharedPreferences.Editor editor=sharedpreferences3.edit();
					editor.putString("alamat", "");
					editor.putString("alamattujuan", "");
					editor.putString("lon1", "");
					editor.putString("lat1", "");
					editor.putString("lon2", "");
					editor.putString("lat2", "");
					editor.putString("keteranganaw", "");
					editor.putString("keterangantu", "");
					editor.putString("keteranganpe", "");
					
					editor.commit();
					Toast.makeText(getApplicationContext(), "Berhasil, pesanan anda akan segera kami proses.", Toast.LENGTH_LONG).show();
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
			
				data.put("pesanan", params[0]);
				data.put("pesanan2",params[1]);
				data.put("pesanan3", params[2]);
				data.put("pesanan4", params[3]);
				data.put("pesanan5", params[4]);
				data.put("pesanan6", params[5]);
				data.put("pesanan7",params[6]);
				data.put("pesanan8", params[7]);
				data.put("pesanan9", params[8]);
				data.put("pesanan10", params[9]);
				data.put("jumlah1", params[10]);
				data.put("jumlah2", params[11]);
				data.put("jumlah3", params[12]);
				data.put("jumlah4", params[13]);
				data.put("jumlah5", params[14]);
				data.put("jumlah6", params[15]);
				data.put("jumlah7", params[16]);
				data.put("jumlah8", params[17]);
				data.put("jumlah9", params[18]);
				data.put("jumlah10", params[19]);
				data.put("latitude", params[20]);
				data.put("longitude", params[21]);
				data.put("lat", params[22]);
				data.put("long", params[23]);
				data.put("biaya", params[24]);
				data.put("jarak", params[25]);
				data.put("awal", params[26]);
				data.put("tujuan", params[27]);
				data.put("email", params[28]);
				data.put("keteranganawal", params[29]);
				data.put("keterangantujuan", params[30]);
				data.put("keteranganpengiriman", params[31]);
				
				
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
		ru.execute(pesanane,pesanane2,pesanane3,pesanane4,pesanane5,pesanane6,pesanane7,pesanane8,pesanane9,pesanane10
				,spinnere1,spinnere2,spinnere3,spinnere4,spinnere5,spinnere6,spinnere7,spinnere8,spinnere9,spinnere10
				,akoordinat1,akoordinat2,akoordinat3,akoordinat4,ahasiltempuh,abiaya,awal,tujuan,dataemailpengguna,keteranganawal,keterangantujuan,keterangankirim);
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
