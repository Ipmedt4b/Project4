package com.example.ipmedt.results;

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

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
 
public class Results extends ListActivity {
 
    // Progress Dialog
    private ProgressDialog pDialog;
    public static int resultsnamesID;
    
    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();
 
    ArrayList<HashMap<String, String>> resultsnamesList;
 
    // url to get all products list
    private static String url_all_resultsnames = "http://jellescheer.nl/williebrordardus/get_all_resultsnames.php";
 
    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_NAMESRESULTS = "namesresults";
    private static final String TAG_PID = "pid";
    private static final String TAG_MODELLEN = "modellen";
  
    
    
 
    // products JSONArray
    JSONArray resultsnames = null;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.models);
 
        // Hashmap for ListView
        resultsnamesList = new ArrayList<HashMap<String, String>>();
 
        // Loading products in Background Thread
        new LoadAllresultsnames().execute();
 
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
            	// link naar juiste model pagina
             	switch (position)
             	{
             	case 0:
             		resultsnamesID = 1;
             		Intent resultsname1 = new Intent(getApplicationContext(), Results_model1.class);
             		startActivity(resultsname1);
             		break;
             	case 1:
             		resultsnamesID = 2;
             		Intent resultsname2 = new Intent(getApplicationContext(), Results_model2.class);
             		startActivity(resultsname2);
             		break;
             	case 2:
             		resultsnamesID = 3;
             		Intent resultsname3 = new Intent(getApplicationContext(), Results_model3.class);
             		startActivity(resultsname3);
             		break;
             	case 3:
             		resultsnamesID = 4;
             		Intent resultsname4 = new Intent(getApplicationContext(), Results_model4.class);
             		startActivity(resultsname4);
             		break;
             	case 4:
             		resultsnamesID = 5;
             		Intent resultsname5 = new Intent(getApplicationContext(), Results_model5.class);
             		startActivity(resultsname5);
             		break;
             	case 5:
             		resultsnamesID = 6;
             		Intent resultsname6 = new Intent(getApplicationContext(), Results_model6.class);
             		startActivity(resultsname6);
             		break;
             	case 6:
             		resultsnamesID = 7;
             		Intent resultsname7 = new Intent(getApplicationContext(), Results_model7.class);
             		startActivity(resultsname7);
             		break;
             	case 7:
             		resultsnamesID = 8;
             		Intent resultsname8 = new Intent(getApplicationContext(), Results_model8.class);
             		startActivity(resultsname8);
             		break;
             	
             	
             	}
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
    class LoadAllresultsnames extends AsyncTask<String, String, String> {
 
        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Results.this);
            pDialog.setMessage("Loading results models. Please wait...");
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
            JSONObject json = jParser.makeHttpRequest(url_all_resultsnames, "GET", params);
 
            // Check your log cat for JSON reponse
            Log.d("All results: ", json.toString());
 
            try {
                // Checking for SUCCESS TAG
                int success = json.getInt(TAG_SUCCESS);
 
                if (success == 1) {
                    // products found
                    // Getting Array of Products
                	resultsnames = json.getJSONArray(TAG_NAMESRESULTS);
 
                    // looping through All Products
                    for (int i = 0; i < resultsnames.length(); i++) {
                        JSONObject c = resultsnames.getJSONObject(i);
 
                        // Storing each json item in variable
                        String id = c.getString(TAG_PID);
                        String naam = c.getString(TAG_MODELLEN);
                        
 
                        // creating new HashMap
                        HashMap<String, String> map = new HashMap<String, String>();
 
                        // adding each child node to HashMap key => value
                        map.put(TAG_PID, id);
                        map.put(TAG_MODELLEN, naam);
                        
 
                        // adding HashList to ArrayList
                        resultsnamesList.add(map);
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
                     * Updating parsed JSON data into ListView
                     * */
                    ListAdapter adapter = new SimpleAdapter(
                            Results.this, resultsnamesList,
                            R.layout.sustainmodel_list, new String[] {TAG_PID,
                                    TAG_MODELLEN, },
                            new int[] { R.id.pid, R.id.naam,});
                    // updating listview
                    setListAdapter(adapter);
                }
            });
 
        }
 
    }
}