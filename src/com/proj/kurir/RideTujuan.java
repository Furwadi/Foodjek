package com.proj.kurir;

import java.io.IOException;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

















import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;




public class RideTujuan extends Activity {

	protected static final String MY_PREFS_NAME = null;
	EditText loktujuan;
	Button setlok;
	SharedPreferences sharedpreferences;
	public static final String DatPref="alamattujuan";
	// Google Map
    private GoogleMap googleMap;
    TextView lat2,lon2;
    
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ridetujuan);
        
        ActionBar mActionBar=getActionBar();
		mActionBar.setDisplayShowHomeEnabled(false);
		mActionBar.setDisplayShowTitleEnabled(false);
		LayoutInflater mInflater=LayoutInflater.from(this);
		
		View mCustomView=mInflater.inflate(R.layout.custom_layout,null);
		TextView mTitleTextView=(TextView) mCustomView.findViewById(R.id.textViewcustom);
		mTitleTextView.setText("Lokasi Tujuan");
		
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
     
 
        try {
            // Loading map
            initilizeMap();
 
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        loktujuan=(EditText) findViewById(R.id.txtset2);
        setlok=(Button) findViewById(R.id.btnset2);
        lat2=(TextView) findViewById(R.id.txtset4);
        lon2=(TextView) findViewById(R.id.txtset5);
        
        setlok.setOnClickListener(new View.OnClickListener(){
        	
        	@Override
			public void onClick(View v) {
        		
        		if(loktujuan.getText().toString().equals(""))
        		{
        			Toast.makeText(getApplicationContext(), "Anda belum memilih lokasi", Toast.LENGTH_LONG).show();
        		}
        		else
        		{
				// TODO Auto-generated method stub
        		SharedPreferences.Editor editor=sharedpreferences.edit();
            	editor.putString(DatPref, loktujuan.getText().toString());
            	editor.putString("lat2", lat2.getText().toString());
            	editor.putString("lon2", lon2.getText().toString());
            	editor.commit();
				Intent loktujuana=new Intent(RideTujuan.this,RideActivity.class);
				//loktujuana.putExtra("loktujuanb", loktujuan.getText().toString());
				startActivity(loktujuana);
        		}
				
			}
        	
        		
        });

 
  
    }
    
    
 
    /**
     * function to load map. If map is not created it will create it for you
     * */
    private void initilizeMap() {
        if (googleMap == null) {
            googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map2)).getMap();
 
            // check if map is created successfully or not
            if (googleMap == null) {
                Toast.makeText(getApplicationContext(),
                        "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                        .show();
            }
        }
        
       
            //getMap().setOnMarkerClickListener(this);
            //getMap().setOnMapLongClickListener(this);
            //getMap().setOnInfoWindowClickListener( this );
            //googleMap.getMap.setOnMapClickListener(this);
        
        //googleMap.getMyLocation(). 
            
        CameraPosition cameraPosition = new CameraPosition.Builder().target(
                new LatLng(-2.1293, 106.1096)).zoom(13).build();
 
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        googleMap.setMyLocationEnabled(true); // false to disable
        googleMap.addTileOverlay(new CachingUrlTileProvider(this, 256, 256) {
            @Override
            public String getTileUrl(int x, int y, int z) {
                return String.format("https://a.tile.openstreetmap.org/%3$s/%1$s/%2$s.png",x,y,z);
            }
        }.createTileOverlayOptions());	
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        googleMap.getUiSettings().setRotateGesturesEnabled(true);
        googleMap.getUiSettings().setCompassEnabled(true);
        googleMap.setOnCameraChangeListener(new OnCameraChangeListener() {
            public void onCameraChange(CameraPosition arg0) {   
            		
            	  //Toast.makeText(getApplicationContext(), arg0.latitude+" "+arg0.longitude+" ", Toast.LENGTH_SHORT).show();
                loktujuan.setText(getAddressFromLatLng(arg0.target)+"");
                lat2.setText(arg0.target.latitude+"");
                lon2.setText(arg0.target.longitude+"");
                
                
                MarkerOptions options = new MarkerOptions().position( arg0.target );
                
                if(options==null)
                {
                	 
                	 options.title( getAddressFromLatLng( arg0.target ) );
                     
                     options.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_iconrumah));
                     googleMap.addMarker( options );
                }
                else
                {
                 
                }
            		
                   
            }
        });
        
    }
    
    private String getAddressFromLatLng( LatLng latLng ) {
        Geocoder geocoder = new Geocoder( getApplicationContext() );
     
        String address = "";
        try {
            address = geocoder
              .getFromLocation( latLng.latitude, latLng.longitude, 1 )
              .get( 0 ).getAddressLine( 0 );
        } catch (IOException e ) {
        }
     
        return address;
    }
     
    public boolean onMarkerClick(Marker marker) {
        marker.showInfoWindow();
        return true;
    }
 
    @Override
    protected void onResume() {
        super.onResume();
        initilizeMap();
    }
 
}
