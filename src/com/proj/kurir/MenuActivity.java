package com.proj.kurir;


import java.util.ArrayList; 









import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageView.ScaleType;

public class MenuActivity extends Activity {

    ImageView mart,food,ride,about;
    SharedPreferences sharedpreferences;
    String user_dat;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        
        sharedpreferences=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		user_dat=sharedpreferences.getString("dataloginpengguna", "");
        
		mart=(ImageView) findViewById(R.id.imageMart);
        food=(ImageView) findViewById(R.id.imageFood);
        ride=(ImageView) findViewById(R.id.imageRide);
        about=(ImageView) findViewById(R.id.imageAbout);
        
        mart.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(user_dat.equals("") || user_dat.equals(null))
        		{
        			Intent intent = new Intent(MenuActivity.this,LogonActivity.class);
            		startActivity(intent);
        		}
        		else
        		{
        			Intent intent = new Intent(MenuActivity.this,FoodActivity.class);
            		startActivity(intent);
        		}
				
			}
		});
        food.setOnClickListener(new View.OnClickListener() {
			
     			@Override
     			public void onClick(View v) {
     				// TODO Auto-generated method stub
     				if(user_dat.equals("") || user_dat.equals(null))
            		{
            			Intent intent = new Intent(MenuActivity.this,LogonActivity.class);
                		startActivity(intent);
            		}
            		else
            		{
            			Intent intent = new Intent(MenuActivity.this,MartActivity.class);
                		startActivity(intent);
            		}
     				
     			}
        });
        ride.setOnClickListener(new View.OnClickListener() {
			
     			@Override
     			public void onClick(View v) {
     				// TODO Auto-generated method stub
     				if(user_dat.equals("") || user_dat.equals(null))
            		{
            			Intent intent = new Intent(MenuActivity.this,LogonActivity.class);
                		startActivity(intent);
            		}
            		else
            		{
            			Intent intent = new Intent(MenuActivity.this,RideActivity.class);
                		startActivity(intent);
            		}
     				
     			}
        });
        about.setOnClickListener(new View.OnClickListener() {
			
     			@Override
     			public void onClick(View v) {
     				// TODO Auto-generated method stub
     				
     				Intent intent = new Intent(MenuActivity.this,TentangActivity.class);
            		startActivity(intent);
     			}
        });
        
    }

      
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        return true;
    }

} 