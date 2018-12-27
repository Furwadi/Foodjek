package com.proj.kurir;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ApplicationAdapter3 extends ArrayAdapter<Application3> {
	
	private List<Application3> items;
	private ArrayList<String> selectedStrings = new ArrayList<String>();
	SharedPreferences sharedpreferences;
	Context context;
	
	public ApplicationAdapter3(Context context, List<Application3> items) {
		super(context, R.layout.app_custom_list3,items);
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
			v=li.inflate(R.layout.app_custom_list3, null);
			
		}
		
		final Application3 app=items.get(position);
		
		if(app!=null)
		{
			final TextView icon= (TextView) v.findViewById(R.id.textViewTanggal);
			final TextView titleText=(TextView) v.findViewById(R.id.titleTxt);
			TextView dlText=(TextView) v.findViewById(R.id.dlTxt);
			ImageView gambardriver=(ImageView) v.findViewById(R.id.gambardriver);
	
			
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
			if(gambardriver!=null)
			{
				new DownloadImageTask(gambardriver).execute(app.getGambar());
			}
			
			
			v.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					// TODO Auto-generated method stub
					//Toast.makeText(parent.getContext(), app.getTitle(), Toast.LENGTH_SHORT).show();
					sharedpreferences=PreferenceManager.getDefaultSharedPreferences(parent.getContext());
					SharedPreferences.Editor editor=sharedpreferences.edit();
					editor.putString("menunama",titleText.getText().toString());
			    	editor.putString("menuharga",icon.getText().toString());
			    	editor.putString("menugambar",app.getGambar().toString());
			

			    	editor.commit();
			    	
			    	Intent gotomapping =new Intent(parent.getContext(),PemesananActivity.class);
			    	context.startActivity(gotomapping);
					
				}
			});

		}
		
		return v;
		
	}

}
