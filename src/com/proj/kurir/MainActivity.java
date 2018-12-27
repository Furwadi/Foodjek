package com.proj.kurir;


import java.util.ArrayList;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.LocalActivityManager;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;

public class MainActivity extends TabActivity {
	
	SharedPreferences sharedpreferences2;
	String dataemailpengguna;
	private TabHost mTabHost;
	LocalActivityManager mlam;
	private static String TAG = MainActivity.class.getSimpleName();
	TextView userName;
	 
	ListView mDrawerList;
	RelativeLayout mDrawerPane;
	private ActionBarDrawerToggle mDrawerToggle;
	private DrawerLayout mDrawerLayout;
	private Handler mHandler;
	 
	ArrayList<NavItem> mNavItems = new ArrayList<NavItem>();


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mHandler=new Handler();
		userName=(TextView) findViewById(R.id.userName);
		sharedpreferences2=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		dataemailpengguna=sharedpreferences2.getString("dataloginpengguna", "");
		if(dataemailpengguna.equals("") || dataemailpengguna.equals(null))
		{
			dataemailpengguna="Good-Jek";
		}
	
		 mNavItems.add(new NavItem("Layanan", "", R.drawable.ic_launcher));
		 //mNavItems.add(new NavItem("Good-Jek", "Layanan Antar-Jemput Penumpang", R.drawable.ic_ridee));
		 mNavItems.add(new NavItem("Food-Jek", "Layanan Pesan-Antar Makanan", R.drawable.ic_foodd));
		 //mNavItems.add(new NavItem("Good-Pick", "Layanan Antar Dokumen, Barang, Makanan, Dll", R.drawable.ic_pickk));
		 mNavItems.add(new NavItem("Info", "", R.drawable.ic_launcher));
		 mNavItems.add(new NavItem("Tentang", "Tentang Food-Jek dan Kebijakan Privasi", R.drawable.ic_how));
		 mNavItems.add(new NavItem("Saran", "Kritik dan Saran Anda Untuk Food-Jek", R.drawable.ic_how));
		 // DrawerLayout
	    mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
	 
	    // Populate the Navigtion Drawer with options
	    mDrawerPane = (RelativeLayout) findViewById(R.id.drawerPane);
	    mDrawerList = (ListView) findViewById(R.id.navList);
	    DrawerListAdapter adapter = new DrawerListAdapter(this, mNavItems);
	    mDrawerList.setAdapter(adapter);
	    
	    
	    mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	        @Override
	        public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
	            
	            mHandler.postDelayed(new Runnable() {
	                @Override
	                public void run() {
	                	selectItemFromDrawer(position);
	                }
	              }, 300);  
	            mDrawerLayout.closeDrawer(mDrawerPane);
	        }
	    });

	    mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,R.drawable.ic_drawermu, R.string.drawer_open, R.string.drawer_close) {
	        @Override
	        public void onDrawerOpened(View drawerView) {
	            super.onDrawerOpened(drawerView);
	     
	            invalidateOptionsMenu();
	        }
	     
	        @Override
	        public void onDrawerClosed(View drawerView) {
	            super.onDrawerClosed(drawerView);
	            Log.d(TAG, "onDrawerClosed: " + getTitle());
	     
	            invalidateOptionsMenu();
	        }
	    };
	     
	    mDrawerLayout.setDrawerListener(mDrawerToggle);
	    getActionBar().setHomeButtonEnabled(true);
		getActionBar().setDisplayHomeAsUpEnabled(true);
	    
		
		ActionBar mActionBar = getActionBar();
		mActionBar.setDisplayShowHomeEnabled(false);
		mActionBar.setDisplayShowTitleEnabled(false);
		LayoutInflater mInflater = LayoutInflater.from(this);
		
		View mCustomView = mInflater.inflate(R.layout.custom_actionbar,null);

		mActionBar.setCustomView(mCustomView);
		mActionBar.setDisplayShowCustomEnabled(true);
		//mActionBar.setDisplayHomeAsUpEnabled(true);
		
		Resources res = getResources();
		mTabHost = getTabHost();
		TabHost.TabSpec spec;
		Intent intent;
		
		mlam = new LocalActivityManager(this, false);
	    mlam.dispatchCreate(savedInstanceState); // state will be bundle your activity state which you get in onCreate
	    mTabHost.setup(mlam);

		
		intent = new Intent(this,MenuActivity.class);
		spec = mTabHost.newTabSpec("main")
				.setIndicator("",res.getDrawable(R.layout.tab_icon))
				.setContent(intent);
		mTabHost.addTab(spec);
		
		intent = new Intent(this,ProgressActivity.class);
		spec = mTabHost.newTabSpec("progress")
				.setIndicator("",res.getDrawable(R.layout.tab_icon_history))
				.setContent(intent);
		mTabHost.addTab(spec);
		
		mTabHost.setCurrentTab(0);
		
		userName.setText(dataemailpengguna);
		
		

		
	}
	/*
	* Called when a particular item from the navigation drawer
	* is selected.
	* */
	private void selectItemFromDrawer(int position) {

	 
	    // Close the drawer
	  
	   
	    if(position==1)
	    {
			if(dataemailpengguna.equals("Good-Jek"))
    		{
				Intent jek=new Intent(MainActivity.this,LogonActivity.class);
    	    	startActivity(jek);
    		}
    		else
    		{
    			Intent jek=new Intent(MainActivity.this,MartActivity.class);
    	    	startActivity(jek);
    		}
	    
	    }
	    //if(position==2)
	    //{
			//if(dataemailpengguna.equals("Good-Jek"))
    		//{
				//Intent jek=new Intent(MainActivity.this,LogonActivity.class);
    	    	//startActivity(jek);
    		//}
    		//else
    		//{
    			//Intent jek=new Intent(MainActivity.this,MartActivity.class);
    	    	//startActivity(jek);
    		//}
	    
	    //}
	    //if(position==3)
	    //{
			//if(dataemailpengguna.equals("Good-Jek"))
    		//{
				//Intent jek=new Intent(MainActivity.this,LogonActivity.class);
    	    	//startActivity(jek);
    		//}
    		//else
    		//{
    			//Intent jek=new Intent(MainActivity.this,FoodActivity.class);
    	    	//startActivity(jek);
    		//}
	    //}

	    if(position==3)
	    {
	  
    			Intent jek=new Intent(MainActivity.this,TentangActivity.class);
    	    	startActivity(jek);
    		
	    }
	    if(position==4)
	    {
			if(dataemailpengguna.equals("Good-Jek"))
    		{
				Intent jek=new Intent(MainActivity.this,LogonActivity.class);
    	    	startActivity(jek);
    		}
    		else
    		{
    			Intent jek=new Intent(MainActivity.this,SaranActivity.class);
    	    	startActivity(jek);
    		}
	    }
	
	}
	
	class NavItem {
	    String mTitle;
	    String mSubtitle;
	    int mIcon;
	 
	    public NavItem(String title, String subtitle, int icon) {
	        mTitle = title;
	        mSubtitle = subtitle;
	        mIcon = icon;
	    }
	}
	class DrawerListAdapter extends BaseAdapter {
		 
	    Context mContext;
	    ArrayList<NavItem> mNavItems;
	 
	    public DrawerListAdapter(Context context, ArrayList<NavItem> navItems) {
	        mContext = context;
	        mNavItems = navItems;
	    }
	 
	    @Override
	    public int getCount() {
	        return mNavItems.size();
	    }
	 
	    @Override
	    public Object getItem(int position) {
	        return mNavItems.get(position);
	    }
	 
	    @Override
	    public long getItemId(int position) {
	        return 0;
	    }
	 
	    @Override
	    public View getView(int position, View convertView, ViewGroup parent) {
	        View view;
	 
	        if (convertView == null) {
	            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	            
	            if(position==0)
	            {
	            	view = inflater.inflate(R.layout.drawer_item2, null);
	            }
	            else if(position==2)
	            {
	            	view = inflater.inflate(R.layout.drawer_item2, null);
	            }
	            else
	            {
	            	view = inflater.inflate(R.layout.drawer_item, null);
	            }
	            
	        }
	        else {
	            view = convertView;
	        }
	 
	        TextView titleView = (TextView) view.findViewById(R.id.title);
	        TextView subtitleView = (TextView) view.findViewById(R.id.subTitle);
	        ImageView iconView = (ImageView) view.findViewById(R.id.icon);
	 
	        titleView.setText( mNavItems.get(position).mTitle );
	        subtitleView.setText( mNavItems.get(position).mSubtitle );
	        iconView.setImageResource(mNavItems.get(position).mIcon);
	 
	        return view;
	    }
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
		if (mDrawerToggle.onOptionsItemSelected(item)) {
		      return true;
		    }
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
	    super.onPostCreate(savedInstanceState);
	    mDrawerToggle.syncState();
	}
	
	@Override
	protected void onResume()
	{
		mlam.dispatchResume(); 
		super.onResume();
	}
	@Override
	protected void onPause()
	{
		mlam.dispatchPause(isFinishing());
		super.onPause();
	}
}
