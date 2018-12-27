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

public class ApplicationAdapter2 extends ArrayAdapter<Application2> {
	private List<Application2> items;
	private ArrayList<String> selectedStrings = new ArrayList<String>();
	SharedPreferences sharedpreferences;
	Context context;
	
	public ApplicationAdapter2(Context context, List<Application2> items) {
		super(context, R.layout.app_custom_list2,items);
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
			v=li.inflate(R.layout.app_custom_list2, null);
			
		}
		
		Application2 app=items.get(position);
		
		if(app!=null)
		{
			TextView icon= (TextView) v.findViewById(R.id.textViewTanggal);
			final TextView titleText=(TextView) v.findViewById(R.id.titleTxt);
			TextView dlText=(TextView) v.findViewById(R.id.dlTxt);
			TextView dlHari=(TextView) v.findViewById(R.id.textViewHari);
			final TextView titleId=(TextView) v.findViewById(R.id.dlId);
			final TextView dlLatitude=(TextView) v.findViewById(R.id.dlLatitude);
			final TextView dlLongitude=(TextView) v.findViewById(R.id.dlLongitude);
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
			if(dlHari!=null)
			{
				dlHari.setText(app.getHari());
			}
			if(titleId!=null)
			{
				titleId.setText(app.getId());
			}
			if(dlLatitude!=null)
			{
				dlLatitude.setText(app.getLatitude());
			}
			if(dlLongitude!=null)
			{
				dlLongitude.setText(app.getLongitude());
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
					editor.putString("tnama",titleText.getText().toString());
			    	editor.putString("tid",titleId.getText().toString());
			    	editor.putString("tlatitude",dlLatitude.getText().toString());
			     	editor.putString("tlongitude",dlLongitude.getText().toString());

			    	editor.commit();
			    	
			    	Intent gotomapping =new Intent(parent.getContext(),ItemActivity.class);
			    	context.startActivity(gotomapping);
					
				}
			});

		}
		
		return v;
		
	}
	


}
