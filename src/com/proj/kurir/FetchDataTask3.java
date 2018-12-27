package com.proj.kurir;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;

public class FetchDataTask3 extends AsyncTask<String,Void,String> {
	private final FetchDataListener3 listener;
    private String msg;
     
    public FetchDataTask3(FetchDataListener3 listener) {
        this.listener = listener;
    }
     
    @Override
    protected String doInBackground(String... params) {
        if(params == null) return null;
         
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("username",params[1]));
        
        // get url from params
        String url = params[0];
         
        try {
            // create http connection
            HttpClient client = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(url);
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
     
            // connect
            HttpResponse response = client.execute(httppost);
             
            // get response
            HttpEntity entity = response.getEntity();
             
            if(entity == null) {
                msg = "Tidak ada respon dari server.";
                return null;       
            }
          
            // get response content and convert it to json string
            InputStream is = entity.getContent();
            return streamToString(is);
        }
        catch(IOException e){
            msg = "Tidak dapat menghubungkan ke jaringan.";
        }
         
        return null;
    }
     
    @Override
    protected void onPostExecute(String sJson) {
        if(sJson == null) {
            if(listener != null) listener.onFetchFailure(msg);
            return;
        }       
         
        try {
            // convert json string to json array
            JSONArray aJson = new JSONArray(sJson);
            // create apps list
            List<Application3> apps = new ArrayList<Application3>();
             
            for(int i=0; i<aJson.length(); i++) {
                JSONObject json = aJson.getJSONObject(i);
                Application3 app = new Application3();
                app.setTitle(json.getString("nama"));
                app.setDl("Jenis : "+json.getString("jenis"));
                app.setIcon("Harga : Rp. "+json.getString("harga"));
                app.setGambar(json.getString("foto"));
                // add the app to apps list
                apps.add(app);
            }
             
            //notify the activity that fetch data has been complete
            if(listener != null) listener.onFetchComplete(apps);
        } catch (JSONException e) {
            msg = "Server tidak merespon.";
            if(listener != null) listener.onFetchFailure(msg);
            return;
        }       
    }
     
  
    public String streamToString(final InputStream is) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
         
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        }
        catch (IOException e) {
            throw e;
        }
        finally {          
            try {
                is.close();
            }
            catch (IOException e) {
                throw e;
            }
        }
         
        return sb.toString();
    }

}
