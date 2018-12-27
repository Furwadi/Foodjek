package com.proj.kurir;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CustomAdapter extends BaseAdapter{

	SharedPreferences sharedpreferences;
    String [] result;
    Context context;
    int [] imageId;
    String user_dat;
    private static LayoutInflater inflater=null;
    public CustomAdapter(MenuActivity mainActivity, String[] prgmNameList, int[] prgmImages) {
        // TODO Auto-generated constructor stub
        result=prgmNameList;
        context=mainActivity;
        imageId=prgmImages;
         inflater = ( LayoutInflater )context.
                 getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView tv;
        ImageView img;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;

             rowView = inflater.inflate(R.layout.program_list, null);
             holder.tv=(TextView) rowView.findViewById(R.id.textView1);
             holder.img=(ImageView) rowView.findViewById(R.id.imageView1);

         holder.tv.setText(result[position]);
         holder.img.setImageResource(imageId[position]);
         sharedpreferences=PreferenceManager.getDefaultSharedPreferences(context);
 		 user_dat=sharedpreferences.getString("dataloginpengguna", "");

         rowView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
            	
            	if(result[position]=="Good-Food")
            	{
            		if(user_dat.equals("") || user_dat.equals(null))
            		{
            			Intent intent = new Intent(context,LogonActivity.class);
                		context.startActivity(intent);
            		}
            		else
            		{
            			Intent intent = new Intent(context,MartActivity.class);
                		context.startActivity(intent);
            		}
            		
            	
            	}
            	else if(result[position]=="Good-Mart")
            	{
            		if(user_dat.equals("") || user_dat.equals(null))
            		{
            			Intent intent = new Intent(context,LogonActivity.class);
                		context.startActivity(intent);
            		}
            		else
            		{
            			Intent intent = new Intent(context,FoodActivity.class);
                		context.startActivity(intent);
            		}
            	}
            	else if(result[position]=="Good-Ride")
            	{
            		if(user_dat.equals("") || user_dat.equals(null))
            		{
            			Intent intent = new Intent(context,LogonActivity.class);
                		context.startActivity(intent);
            		}
            		else
            		{
            			Intent intent = new Intent(context,RideActivity.class);
                		context.startActivity(intent);
            		}
            	}
                   	else if(result[position]=="Tentang")
            	{
            		Intent intent = new Intent(context,TentangActivity.class);
            		context.startActivity(intent);
            	}
            	else
            	{
            		
            	}
            	
            }
        });

        return rowView;
    }

} 