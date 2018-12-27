package com.proj.kurir;

import java.text.DecimalFormat;
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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class PemesananActivity extends Activity {

	SharedPreferences sharedpreferences;
	SharedPreferences sharedpreferences2;
	SharedPreferences sharedpreferences3;
	String data_login,id,latitude,longitude,harga,nama_pesanan,gambar,nama_toko;
	TextView nama_menu,hargamenu,titletoko;
	ImageView lihat_menu;
	EditText textPeta;
	SharedPreferences sharedpreferences4;
	String alamattujuan,koor1,koor2;
	double hargaakhir1,hargaakhir2,hargaakhir3;
	int hargai;
	TextView jarakpeta,biayapeta;
	private static final String REGISTER_URL="http://ubbitclub.com/good/register_food.php";
	Button buttonKeranjang;
	Spinner spinnerPesanan;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pemesanan);
		
		ActionBar mActionBar=getActionBar();
		mActionBar.setDisplayShowHomeEnabled(false);
		mActionBar.setDisplayShowTitleEnabled(false);
		LayoutInflater mInflater=LayoutInflater.from(this);
		
		View mCustomView=mInflater.inflate(R.layout.custom_layout,null);
		TextView mTitleTextView=(TextView) mCustomView.findViewById(R.id.textViewcustom);
		mTitleTextView.setText("Pesan Menu");
		
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
		sharedpreferences=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		data_login=sharedpreferences.getString("dataloginpengguna", "");
		sharedpreferences2=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		nama_toko=sharedpreferences2.getString("tnama", "");
		id=sharedpreferences2.getString("tid", "");
		latitude=sharedpreferences2.getString("tlatitude", "");
		longitude=sharedpreferences2.getString("tlongitude", "");
		sharedpreferences3=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		harga=sharedpreferences3.getString("menuharga", "");
		nama_pesanan=sharedpreferences3.getString("menunama", "");
		gambar=sharedpreferences3.getString("menugambar", "");
		textPeta=(EditText) findViewById(R.id.textPeta);
		sharedpreferences4=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		alamattujuan=sharedpreferences4.getString("ealamattujuan", "");
		jarakpeta=(TextView) findViewById(R.id.hasiltempuhPeta);
		biayapeta=(TextView) findViewById(R.id.biayaPeta);
		buttonKeranjang=(Button) findViewById(R.id.buttonKeranjang);
		spinnerPesanan=(Spinner) findViewById(R.id.spinnerPesanan);

		
		koor1=sharedpreferences4.getString("elon2", "");
	
		koor2=sharedpreferences4.getString("elat2", "");
		
		if(latitude=="")
		{
			latitude="0.0";
		}
		
		if(longitude=="")
		{
			longitude="0.0";
		}
		if(koor1=="")
		{
			koor1="0.0";
		}
		if(koor2=="")
		{
			koor2="0.0";
			
			jarakpeta.setText("0");
			biayapeta.setText("0");
		}
		else
		{
			jarakpeta.setText(String.format("%.1f", CalculationByDistance()));
			biayapeta.setText(CalculatePrice()+"");
		}
		
	
		textPeta.setText(alamattujuan);
		
		
		//Toast.makeText(getApplicationContext(), alamattujuan, Toast.LENGTH_SHORT).show();
		
		nama_menu=(TextView) findViewById(R.id.textnamapesanan);
		hargamenu=(TextView) findViewById(R.id.textHarga);
		titletoko=(TextView) findViewById(R.id.titleToko);
		
		lihat_menu=(ImageView) findViewById(R.id.gambarmenu);
		
		nama_menu.setText("Nama Menu : "+nama_pesanan);
		hargamenu.setText(harga);
		titletoko.setText(nama_toko);
		
		lihat_menu.setScaleType(ScaleType.FIT_XY);
		
		new DownloadImageTask(lihat_menu).execute(gambar);
		
		textPeta.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent gotopeta=new Intent(PemesananActivity.this,MapPeta.class);
				startActivity(gotopeta);
			}
		});
		
		buttonKeranjang.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(v==buttonKeranjang)
				{
					loginUser();
				}
			}
		});
		
		//Toast.makeText(getApplicationContext(), nama_pesanan, Toast.LENGTH_LONG).show();
	}
	
	private void loginUser()
	{
		String akoordinat1=latitude;
		String akoordinat2=longitude;
		String akoordinat3=koor2;
		String akoordinat4=koor1;
		String awal=alamattujuan;
		String tujuan=alamattujuan;
		String keteranganawal=alamattujuan;
		String keterangantujuan=alamattujuan;
		String ahasiltempuh=jarakpeta.getText().toString();
		String abiaya=biayapeta.getText().toString();
		String apengiriman=nama_pesanan;
		String jumlahpesanan=spinnerPesanan.getSelectedItem().toString();
		
		login(akoordinat1,akoordinat2,akoordinat3,akoordinat4,awal,tujuan,keteranganawal,keterangantujuan,data_login,ahasiltempuh,abiaya,apengiriman,jumlahpesanan);
		
		
	}
	
	
	
	private void login(String akoordinat1,String akoordinat2,String akoordinat3,String akoordinat4,String awal,String tujuan,String keteranganawal,String keterangantujuan,String username,String ahasiltempuh,String abiaya,String apengiriman,String jumlahpesanan)
	{
		class RegisterUser extends AsyncTask<String, Void, String>
		{
			ProgressDialog loading;
			RegisterUserClass ruc=new RegisterUserClass();
			
			@Override
			protected void onPreExecute()
			{
				super.onPreExecute();
				loading=ProgressDialog.show(PemesananActivity.this, "Harap tunggu, permintaan anda sedang diproses.....", null,true,true);
			}
			
			@Override
			protected void onPostExecute(String s)
			{
				super.onPostExecute(s);
				loading.dismiss();
				//Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
				if(s.equals("Berhasil, pesanan anda akan segera kami proses."))
				{
					Intent i=new Intent(PemesananActivity.this,MainActivity.class);
					startActivity(i);
					SharedPreferences.Editor editor=sharedpreferences3.edit();
				
					editor.putString("ealamattujuan", "");
					editor.putString("elon2", "");
					editor.putString("elat2", "");

					
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
				data.put("latitude",params[0]);
				data.put("longitude", params[1]);
				data.put("lat", params[2]);
				data.put("long", params[3]);
				data.put("posisiawal", params[4]);
				data.put("posisitujuan",params[5]);
				data.put("keteranganawal", params[6]);
				data.put("keterangantujuan", params[7]);
				data.put("username", params[8]);
				data.put("hasiltempuh", params[9]);
				data.put("biaya", params[10]);
				data.put("pengiriman", params[11]);
				data.put("jumlahpengiriman", params[12]);
				
								
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
		ru.execute(akoordinat1,akoordinat2,akoordinat3,akoordinat4,awal,tujuan,keteranganawal,keterangantujuan,username,ahasiltempuh,abiaya,apengiriman,jumlahpesanan);
	}
	

	public double CalculationByDistance() {
        int Radius = 6371;// radius of earth in Km
        double lat1 = Double.parseDouble(latitude);
        double lat2 = Double.parseDouble(koor2);
        double lon1 = Double.parseDouble(longitude);
        double lon2 = Double.parseDouble(koor1);
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
			hargai=10000;
		}
		else
		{
			hargaakhir1=CalculationByDistance()-5.0;
			hargaakhir2=Math.floor(hargaakhir1);
			hargaakhir3=hargaakhir2+1.0;
			
			hargai=(int) (hargaakhir3*2000);
			hargai=hargai+10000;
			
		}		

		return hargai;
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
