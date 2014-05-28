package com.example.ipmedt.strategy;

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
 
public class Strategy extends ListActivity {
 
    // Progress Dialog
    private ProgressDialog pDialog;
    public static int strategynamesID;
    
    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();
 
    ArrayList<HashMap<String, String>> strategynamesList;
 
    // url to get all products list
    private static String url_all_strategynames = "http://jellescheer.nl/williebrordardus/get_all_strategynames.php";
 
    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_NAMESSTRATEGY = "namesstrategy";
    private static final String TAG_PID = "pid";
    private static final String TAG_MODELLEN = "modellen";
  
    
    
 
    // products JSONArray
    JSONArray strategynames = null;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.models);
 
        // Hashmap for ListView
        strategynamesList = new ArrayList<HashMap<String, String>>();
 
        // Loading products in Background Thread
        new LoadAllstrategynames().execute();
 
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

             	switch (position)
             	{
             	case 0:
             		strategynamesID = 1;
             		Intent strategyname1 = new Intent(getApplicationContext(), Strategy_model1.class);
             		startActivity(strategyname1);
             		break;
             	case 1:
             		strategynamesID = 2;
             		Intent strategyname2 = new Intent(getApplicationContext(), Strategy_model2.class);
             		startActivity(strategyname2);
             		break;
             	case 2:
             		strategynamesID = 3;
             		Intent strategyname3 = new Intent(getApplicationContext(), Strategy_model3.class);
             		startActivity(strategyname3);
             		break;
             	case 3:
             		strategynamesID = 4;
             		Intent strategyname4 = new Intent(getApplicationContext(), Strategy_model4.class);
             		startActivity(strategyname4);
             		break;
             	case 4:
             		strategynamesID = 5;
             		Intent strategyname5 = new Intent(getApplicationContext(), Strategy_model5.class);
             		startActivity(strategyname5);
             		break;
             	case 5:
             		strategynamesID = 6;
             		Intent strategyname6 = new Intent(getApplicationContext(), Strategy_model6.class);
             		startActivity(strategyname6);
             		break;
             	case 6:
             		strategynamesID = 7;
             		Intent strategyname7 = new Intent(getApplicationContext(), Strategy_model7.class);
             		startActivity(strategyname7);
             		break;
             	case 7:
             		strategynamesID = 8;
             		Intent strategyname8 = new Intent(getApplicationContext(), Strategy_model8.class);
             		startActivity(strategyname8);
             		break;
             	case 8:
             		strategynamesID = 9;
             		Intent strategyname9 = new Intent(getApplicationContext(), Strategy_model9.class);
             		startActivity(strategyname9);
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
    class LoadAllstrategynames extends AsyncTask<String, String, String> {
 
        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Strategy.this);
            pDialog.setMessage("Loading strategy models. Please wait...");
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
            JSONObject json = jParser.makeHttpRequest(url_all_strategynames, "GET", params);
 
            // Check your log cat for JSON reponse
            Log.d("All strategy: ", json.toString());
 
            try {
                // Checking for SUCCESS TAG
                int success = json.getInt(TAG_SUCCESS);
 
                if (success == 1) {
                    // products found
                    // Getting Array of Products
                	strategynames = json.getJSONArray(TAG_NAMESSTRATEGY);
 
                    // looping through All Products
                    for (int i = 0; i < strategynames.length(); i++) {
                        JSONObject c = strategynames.getJSONObject(i);
 
                        // Storing each json item in variable
                        String id = c.getString(TAG_PID);
                        String naam = c.getString(TAG_MODELLEN);
                        
 
                        // creating new HashMap
                        HashMap<String, String> map = new HashMap<String, String>();
 
                        // adding each child node to HashMap key => value
                        map.put(TAG_PID, id);
                        map.put(TAG_MODELLEN, naam);
                        
 
                        // adding HashList to ArrayList
                        strategynamesList.add(map);
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
                            Strategy.this, strategynamesList,
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