package com.proj.kurir;



import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.PowerManager;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.android.gcm.GCMBaseIntentService;

public class GCMIntentService extends GCMBaseIntentService {
	int m;
	SharedPreferences share;
	String shareval;
	Uri Newsd;
	
	private static final String TAG = "GCM Tutorial::Service";

	// Use your PROJECT ID from Google API into SENDER_ID
	public static final String SENDER_ID = "603380359604 "; 

	public GCMIntentService() {
		super(SENDER_ID);
	}

	@Override
	protected void onRegistered(Context context, String registrationId) {

		Log.i(TAG, "onRegistered: registrationId=" + registrationId);
	}

	@Override
	protected void onUnregistered(Context context, String registrationId) {

		Log.i(TAG, "onUnregistered: registrationId=" + registrationId);
	}

	@Override
	protected void onMessage(Context context, Intent data) {
		Random random=new Random();
	    m=random.nextInt(999-100) + 100;
		String message;
		String mata_kuliah;
		// Message from PHP server
		message = data.getStringExtra("message");
		mata_kuliah=data.getStringExtra("mata_kuliah");
		// Open a new activity called GCMMessageView
		Intent intent = new Intent(this, MainActivity.class);
		// Pass data to the new activity
		intent.putExtra("message", message);
		intent.putExtra("mata_kuliah", mata_kuliah);
		// Starts the activity on notification click
		PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent,
				PendingIntent.FLAG_UPDATE_CURRENT);
		// Create the notification with a notification builder
		Notification notification = new Notification.Builder(this)
				.setSmallIcon(R.drawable.ic_bag)
				.setWhen(System.currentTimeMillis())
				.setContentTitle("Notifikasi FOOD-JEK")
				.setContentText(message).setContentIntent(pIntent)
				.getNotification();
		// Remove the notification on click
		Uri alarmSound=RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
  	   
  	   if(alarmSound==null)
  	   {
  		   alarmSound=RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
  		   if(alarmSound==null)
  		   {
  			   alarmSound=RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
  		   }
  	   }
  	   else
  	   {
  		   alarmSound=RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
  	   }
  	 
  	   Newsd=alarmSound;

  	   
  	    notification.sound= Newsd;
		notification.flags |= Notification.FLAG_AUTO_CANCEL;

		NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		manager.notify(m, notification);

		{
			// Wake Android Device when notification received
			PowerManager pm = (PowerManager) context
					.getSystemService(Context.POWER_SERVICE);
			final PowerManager.WakeLock mWakelock = pm.newWakeLock(
					PowerManager.FULL_WAKE_LOCK
							| PowerManager.ACQUIRE_CAUSES_WAKEUP, "GCM_PUSH");
			mWakelock.acquire();

			// Timer before putting Android Device to sleep mode.
			Timer timer = new Timer();
			TimerTask task = new TimerTask() {
				public void run() {
					mWakelock.release();
				}
			};
			timer.schedule(task, 5000);
		}

	}

	@Override
	protected void onError(Context arg0, String errorId) {

		Log.e(TAG, "onError: errorId=" + errorId);
	}

}