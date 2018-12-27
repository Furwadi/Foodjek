package com.proj.kurir;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;



public class ApplicationAdapter extends ArrayAdapter<Application>  {
	private List<Application> items;
	private ArrayList<String> selectedStrings = new ArrayList<String>();
	SharedPreferences sharedpreferences;
	Context context;
	private LayoutInflater mInflate;
	private static final String REGISTER_URL="http://ubbitclub.com/good/batal_pesanan.php";
	Dialog dialog;
	SharedPreferences datfromnamatoko;
	String getInvoice,nama;
	SharedPreferences datfrommasuk;
	
	public ApplicationAdapter(Context context, List<Application> items) {
		super(context, R.layout.app_custom_list,items);
		// TODO Auto-generated constructor stub
		this.items=items;
		this.context=context;
	}
	
	
	
	@Override
	public int getCount()
	{
		return items.size();
	}
	
	
	
	
	
	@Override
	public View getView(int position, View convertView, final ViewGroup parent)
	{
		View v=convertView;
		if(v==null)
		{
			LayoutInflater li = LayoutInflater.from(getContext());
		
			v=li.inflate(R.layout.app_custom_list, null);
			
		}
		
		Application app=items.get(position);
		
		if(app!=null)
		{
			TextView icon= (TextView) v.findViewById(R.id.textViewTanggal);
			TextView titleText=(TextView) v.findViewById(R.id.titleTxt);
			TextView dlText=(TextView) v.findViewById(R.id.dlTxt);
			final TextView dlInvoice=(TextView) v.findViewById(R.id.dlInvoice);
			TextView dlbn=(TextView) v.findViewById(R.id.textViewbn);
			TextView dlhp=(TextView) v.findViewById(R.id.textViewhp);
			ImageView gambardriver=(ImageView) v.findViewById(R.id.gambardriver);
			Button batalpesanan=(Button) v.findViewById(R.id.buttonbatalpesanan);
			
	
			
			if(icon!=null)
			{
				icon.setText(app.getIcon());
			}
			
			if(titleText!=null)
			{
				titleText.setText(app.getTitle());
			}
			
			if(dlText!=null)
			{
				dlText.setText(app.getDl());
			}
			if(dlInvoice!=null)
			{
				dlInvoice.setText(app.getInvoice());
			}
			if(dlbn!=null)
			{
				dlbn.setText(app.getBn());
			}
			if(dlInvoice!=null)
			{
				dlhp.setText(app.getHp());
			}
			if(gambardriver!=null)
			{
				new DownloadImageTask(gambardriver).execute(app.getGambar());
			}
			
			
			batalpesanan.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					sharedpreferences=PreferenceManager.getDefaultSharedPreferences(parent.getContext());
					SharedPreferences.Editor editor=sharedpreferences.edit();
					editor.putString("tinv",dlInvoice.getText().toString());

					editor.commit();
					
					
		    	
	                dialog = new Dialog(v.getRootView().getContext());
	                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
	                dialog.setCancelable(false);
	                dialog.setContentView(R.layout.dialog);

	                

	                //Button dialogButton = (Button) dialog.findViewById(R.id.btn_dialog);
	                //dialogButton.setOnClickListener(new View.OnClickListener() {
	                    //@Override
	                    //public void onClick(View v) {
	                        //dialog.dismiss();
	                    //}
	                //});
	                
	                
	                final Button buttonok=(Button) dialog.findViewById(R.id.buttonok);
	                Button buttoncancel=(Button) dialog.findViewById(R.id.buttoncancel);
	                
	                
	                buttonok.setOnClickListener(new View.OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							
							if(v==buttonok)
							{
								loginUser();
							}
							
						}
					});
	                
	                
	                buttoncancel.setOnClickListener(new View.OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							dialog.dismiss();
						}
					});

	                dialog.show();
	                 
	    
					
				}
			});
			
			

		}
		
		return v;
		
	}
	
	private void loginUser()
	{
		
		datfromnamatoko=PreferenceManager.getDefaultSharedPreferences(context);
		getInvoice=datfromnamatoko.getString("tinv", "");
		//Toast.makeText(context, getInvoice, Toast.LENGTH_SHORT).show();
		
		String bemail=getInvoice;
		

		login(bemail);
		

    	
	}
	
	
	
	private void login(String bemail)
	{
		class RegisterUser extends AsyncTask<String, Void, String>
		{
			
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
			
		
				Toast.makeText(context, s, Toast.LENGTH_LONG).show();
				if(s.equals("Berhasil, pesanan anda telah dibatalkan."))
				{
					//Intent i=new Intent(context,MainActivity.class);
					//context.startActivity(i);
					dialog.dismiss();

					datfrommasuk=PreferenceManager.getDefaultSharedPreferences(context);
					nama=datfrommasuk.getString("dataloginpengguna", "");
					String url="http://ubbitclub.com/good/message.php";
					FetchDataTask task=new FetchDataTask((FetchDataListener) context);
			    	task.execute(url,nama);
	
				}
				else if(s.equals("<html>"))
				{
					Toast.makeText(context, "Mohon periksa kembali koneksi internet anda.", Toast.LENGTH_SHORT).show();
				}

				else
				{
					Toast.makeText(context, s, Toast.LENGTH_LONG).show();
				}

			}
			
			@Override
			protected String doInBackground(String... params) {
				// TODO Auto-generated method stub
				HashMap<String,String> data=new HashMap<String,String>();

				data.put("invoice",params[0]);
			
				
				String result=ruc.sendPostRequest(REGISTER_URL, data);
				
				if(isNetworkAvailable()==false)
				{
					result="Maaf, anda sedang tidak  terhubung ke jaringan.";
				}
				return result;
			}
			
			public boolean isNetworkAvailable()
			{
				
				ConnectivityManager cm=(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
				
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


}
