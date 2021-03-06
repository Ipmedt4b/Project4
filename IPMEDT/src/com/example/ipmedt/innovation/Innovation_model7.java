package com.example.ipmedt.innovation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.ipmedt.JSONParser;
import com.example.ipmedt.R;
import com.example.ipmedt.R.id;
import com.example.ipmedt.R.layout;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
 
public class Innovation_model7 extends ListActivity {
	
	
	
	 
    // Progress Dialog
    private ProgressDialog pDialog;
   
    
    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();
 
    ArrayList<HashMap<String, String>> innovation1List;
 
    // url to get all products list
    private static String url_all_innovation1 = "http://jellescheer.nl/williebrordardus/get_all_innovation_m5.php";
 
    // JSON Node names
    private static final String TAG_SUCCESS = "success";  
    private static final String TAG_INNOVATION1 = "innovation";
    private static final String TAG_PID = "pid";
    private static final String TAG_NAAMSUBMODEL = "naamsubmodel";
    private static final String TAG_SUBMODEL = "submodel";
    private static final String TAG_SUBBESCHRIJVING = "subbeschrijving";
    
 
    // products JSONArray
    JSONArray innovation1 = null;
 
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sustainmodels);
        
               
        
 
        // Hashmap for ListView
        innovation1List = new ArrayList<HashMap<String, String>>();
 
        // Loading products in Background Thread
        new LoadAllinnovation1().execute();
 
//         Get listview
        ListView lv = getListView();
      
        // on seleting single product
        // launching Edit Product Screen
        lv.setOnItemClickListener(new OnItemClickListener() {
 
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) 
            {
                 //getting values from selected ListItem
                 //String pid = ((TextView) view.findViewById(R.id.pid)).getText().toString();

             	
           
            }
        });
 
    }
 

 
    /**
     * Background Async Task to Load all product by making HTTP Request
     * */
    class LoadAllinnovation1 extends AsyncTask<String, String, String> {
 
        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Innovation_model7.this);
            pDialog.setMessage("Loading model. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }
 
        /**
         * getting All products from url
         * */
        protected String doInBackground(String... args) {
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            // getting JSON string from URL
            JSONObject json = jParser.makeHttpRequest(url_all_innovation1, "GET", params);
 
            // Check your log cat for JSON reponse
            Log.d("All models: ", json.toString());
 
            try {
                // Checking for SUCCESS TAG
                int success = json.getInt(TAG_SUCCESS);
 
                if (success == 1) {
                    // products found
                    // Getting Array of Products
                	innovation1 = json.getJSONArray(TAG_INNOVATION1);
 
                    // looping through All Products
                    for (int i = 0; i < innovation1.length(); i++) {
                        JSONObject c = innovation1.getJSONObject(i);
 
                        // Storing each json item in variable
                        String id = c.getString(TAG_PID);
                        String naam = c.getString(TAG_NAAMSUBMODEL);
                        String auteur = c.getString(TAG_SUBMODEL);
                        String beschrijving = c.getString(TAG_SUBBESCHRIJVING);
                        
                        // creating new HashMap
                        HashMap<String, String> map = new HashMap<String, String>();
 
                        // adding each child node to HashMap key => value
                        map.put(TAG_PID, id);
                        map.put(TAG_NAAMSUBMODEL, naam);
                        map.put(TAG_SUBMODEL, auteur);
                        map.put(TAG_SUBBESCHRIJVING, beschrijving);
 
                        // adding HashList to ArrayList
                        innovation1List.add(map);
                    }
                }}
                    
            catch (JSONException e) {
                e.printStackTrace();
            }
 
            return null;
        }
 
        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after getting all products
            pDialog.dismiss();
            // updating UI from Background Thread
            runOnUiThread(new Runnable() {
                public void run() {
                    /**
                     * Updating parsed JSON data into ListViews
                     * */
                	 ListAdapter adapter = new SimpleAdapter(
                             Innovation_model7.this, innovation1List,
                             R.layout.sustain_list, new String[] {TAG_PID,
                                     TAG_NAAMSUBMODEL, TAG_SUBMODEL, TAG_SUBBESCHRIJVING},
                             new int[] { R.id.pid,  R.id.naamsubmodel, R.id.auteur,  R.id.naamsubbeschrijving});
                     // updating listview
                     setListAdapter(adapter);
                }
            });
 
        }
 
    }
}