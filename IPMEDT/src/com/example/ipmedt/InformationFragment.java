package com.example.ipmedt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
 
public class InformationFragment extends ListActivity {
	
	
	
	 
    // Progress Dialog
    private ProgressDialog pDialog;
   
    
    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();
 
    ArrayList<HashMap<String, String>> informationList;
 
    // url to get all products list
    private static String url_all_info = "http://www.jellescheer.nl/williebrordardus/get_all_information.php";
 
    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_informationpage = "informationpage";
    private static final String TAG_PID = "pid";
    private static final String TAG_INFO = "info";
    private static final String TAG_AFB = "afb";
    
 
    // products JSONArray
    JSONArray information = null;
 
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.models);
        
               
        
 
        // Hashmap for ListView
        informationList = new ArrayList<HashMap<String, String>>();
 
        // Loading products in Background Thread
        new LoadAllInfo().execute();
 
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
 
//    // Response from Edit Product Activity
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        // if result code 100
//        if (resultCode == 100) {
//            // if result code 100 is received
//            // means user edited/deleted product
//            // reload this screen again
//            Intent intent = getIntent();
//            finish();
//            startActivity(intent);
//        }
// 
//    }
 
    /**
     * Background Async Task to Load all product by making HTTP Request
     * */
    class LoadAllInfo extends AsyncTask<String, String, String> {
 
        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(InformationFragment.this);
            pDialog.setMessage("Loading Information. Please wait...");
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
            JSONObject json = jParser.makeHttpRequest(url_all_info, "GET", params);
 
            // Check your log cat for JSON reponse
            Log.d("All Info: ", json.toString());
 
            try {
                // Checking for SUCCESS TAG
                int success = json.getInt(TAG_SUCCESS);
 
                if (success == 1) {
                    // products found
                    // Getting Array of Products
                    information = json.getJSONArray(TAG_informationpage);
 
                    // looping through All Products
                    for (int i = 0; i < information.length(); i++) {
                        JSONObject c = information.getJSONObject(i);
 
                        // Storing each json item in variable
                        String id = c.getString(TAG_PID);
                        String info = c.getString(TAG_INFO);
                        String afb = c.getString(TAG_AFB);
 
                        // creating new HashMap
                        HashMap<String, String> map = new HashMap<String, String>();
 
                        // adding each child node to HashMap key => value
                        map.put(TAG_PID, id);
                        map.put(TAG_INFO, info);
                        map.put(TAG_AFB, afb);
 
                        // adding HashList to ArrayList
                        informationList.add(map);
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
                            InformationFragment.this, informationList,
                            R.layout.info_list, new String[] {TAG_PID,
                                    TAG_INFO, TAG_AFB},
                            new int[] { R.id.pid, R.id.info, R.id.afb });
                    // updating listview
                    setListAdapter(adapter);
                }
            });
 
        }
 
    }
}