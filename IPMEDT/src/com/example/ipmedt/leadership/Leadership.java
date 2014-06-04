package com.example.ipmedt.leadership;

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
 
public class Leadership extends ListActivity {
 
    // Progress Dialog
    private ProgressDialog pDialog;
    public static int leadershipnamesID;
    
    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();
 
    ArrayList<HashMap<String, String>> leadershipnamesList;
 
    // url to get all products list
    private static String url_all_leadershipnames = "http://jellescheer.nl/williebrordardus/get_all_leadershipnames.php";
 
    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_NAMESLEADERSHIP = "namesleadership";
    private static final String TAG_PID = "pid";
    private static final String TAG_MODELLEN = "modellen";
  
    
    
 
    // products JSONArray
    JSONArray leadershipnames = null;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.models);
 
        // Hashmap for ListView
        leadershipnamesList = new ArrayList<HashMap<String, String>>();
 
        // Loading products in Background Thread
        new LoadAllleadershipnames().execute();
 
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
             		leadershipnamesID = 1;
             		Intent leadershipname1 = new Intent(getApplicationContext(), Leadership_model1.class);
             		startActivity(leadershipname1);
             		break;
             	case 1:
             		leadershipnamesID = 2;
             		Intent leadershipname2 = new Intent(getApplicationContext(), Leadership_model2.class);
             		startActivity(leadershipname2);
             		break;
             	case 2:
             		leadershipnamesID = 3;
             		Intent leadershipname3 = new Intent(getApplicationContext(), Leadership_model3.class);
             		startActivity(leadershipname3);
             		break;
             	case 3:
             		leadershipnamesID = 4;
             		Intent leadershipname4 = new Intent(getApplicationContext(), Leadership_model4.class);
             		startActivity(leadershipname4);
             		break;
             	case 4:
             		leadershipnamesID = 5;
             		Intent leadershipname5 = new Intent(getApplicationContext(), Leadership_model5.class);
             		startActivity(leadershipname5);
             		break;
             	case 5:
             		leadershipnamesID = 6;
             		Intent leadershipname6 = new Intent(getApplicationContext(), Leadership_model6.class);
             		startActivity(leadershipname6);
             		break;
             	case 6:
             		leadershipnamesID = 7;
             		Intent leadershipname7 = new Intent(getApplicationContext(), Leadership_model7.class);
             		startActivity(leadershipname7);
             		break;
             	case 7:
             		leadershipnamesID = 8;
             		Intent leadershipname8 = new Intent(getApplicationContext(), Leadership_model8.class);
             		startActivity(leadershipname8);
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
    class LoadAllleadershipnames extends AsyncTask<String, String, String> {
 
        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Leadership.this);
            pDialog.setMessage("Loading leadership models. Please wait...");
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
            JSONObject json = jParser.makeHttpRequest(url_all_leadershipnames, "GET", params);
 
            // Check your log cat for JSON reponse
            Log.d("All leadership: ", json.toString());
 
            try {
                // Checking for SUCCESS TAG
                int success = json.getInt(TAG_SUCCESS);
 
                if (success == 1) {
                    // products found
                    // Getting Array of Products
                	leadershipnames = json.getJSONArray(TAG_NAMESLEADERSHIP);
 
                    // looping through All Products
                    for (int i = 0; i < leadershipnames.length(); i++) {
                        JSONObject c = leadershipnames.getJSONObject(i);
 
                        // Storing each json item in variable
                        String id = c.getString(TAG_PID);
                        String naam = c.getString(TAG_MODELLEN);
                        
 
                        // creating new HashMap
                        HashMap<String, String> map = new HashMap<String, String>();
 
                        // adding each child node to HashMap key => value
                        map.put(TAG_PID, id);
                        map.put(TAG_MODELLEN, naam);
                        
 
                        // adding HashList to ArrayList
                        leadershipnamesList.add(map);
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
                            Leadership.this, leadershipnamesList,
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