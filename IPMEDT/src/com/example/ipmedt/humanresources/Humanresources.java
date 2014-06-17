package com.example.ipmedt.humanresources;

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
 
public class Humanresources extends ListActivity {
 
    // Progress Dialog
    private ProgressDialog pDialog;
    public static int humanresourcesnamesID;
    
    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();
 
    ArrayList<HashMap<String, String>> humanresourcesnamesList;
 
    // url to get all products list
    private static String url_all_humanresourcesnames = "http://jellescheer.nl/williebrordardus/get_all_humanresourcesnames.php";
 
    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_NAMESHUMANRESOURCES = "nameshumanresources";
    private static final String TAG_PID = "pid";
    private static final String TAG_MODELLEN = "modellen";
  
    
    
 
    // products JSONArray
    JSONArray humanresourcesnames = null;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.models);
 
        // Hashmap for ListView
        humanresourcesnamesList = new ArrayList<HashMap<String, String>>();
 
        // Loading products in Background Thread
        new LoadAllhumanresourcesnames().execute();
 
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
            	// linkt naar de juiste pagina van het model
             	switch (position)
             	{
             	case 0:
             		humanresourcesnamesID = 1;
             		Intent humanresourcesname1 = new Intent(getApplicationContext(), Humanresources_model1.class);
             		startActivity(humanresourcesname1);
             		break;
             	case 1:
             		humanresourcesnamesID = 2;
             		Intent humanresourcesname2 = new Intent(getApplicationContext(), Humanresources_model2.class);
             		startActivity(humanresourcesname2);
             		break;
             	case 2:
             		humanresourcesnamesID = 3;
             		Intent humanresourcesname3 = new Intent(getApplicationContext(), Humanresources_model3.class);
             		startActivity(humanresourcesname3);
             		break;
             	case 3:
             		humanresourcesnamesID = 4;
             		Intent humanresourcesname4 = new Intent(getApplicationContext(), Humanresources_model4.class);
             		startActivity(humanresourcesname4);
             		break;
             	case 4:
             		humanresourcesnamesID = 5;
             		Intent humanresourcesname5 = new Intent(getApplicationContext(), Humanresources_model5.class);
             		startActivity(humanresourcesname5);
             		break;
             	case 5:
             		humanresourcesnamesID = 6;
             		Intent humanresourcesname6 = new Intent(getApplicationContext(), Humanresources_model6.class);
             		startActivity(humanresourcesname6);
             		break;
             	case 6:
             		humanresourcesnamesID = 7;
             		Intent humanresourcesname7 = new Intent(getApplicationContext(), Humanresources_model7.class);
             		startActivity(humanresourcesname7);
             		break;
             	case 7:
             		humanresourcesnamesID = 8;
             		Intent humanresourcesname8 = new Intent(getApplicationContext(), Humanresources_model8.class);
             		startActivity(humanresourcesname8);
             		break;
             	case 8:
             		humanresourcesnamesID = 9;
             		Intent humanresourcesname9 = new Intent(getApplicationContext(), Humanresources_model9.class);
             		startActivity(humanresourcesname9);
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
    class LoadAllhumanresourcesnames extends AsyncTask<String, String, String> {
 
        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Humanresources.this);
            pDialog.setMessage("Loading humanresources models. Please wait...");
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
            JSONObject json = jParser.makeHttpRequest(url_all_humanresourcesnames, "GET", params);
 
            // Check your log cat for JSON reponse
            Log.d("All humanresources: ", json.toString());
 
            try {
                // Checking for SUCCESS TAG
                int success = json.getInt(TAG_SUCCESS);
 
                if (success == 1) {
                    // products found
                    // Getting Array of Products
                	humanresourcesnames = json.getJSONArray(TAG_NAMESHUMANRESOURCES);
 
                    // looping through All Products
                    for (int i = 0; i < humanresourcesnames.length(); i++) {
                        JSONObject c = humanresourcesnames.getJSONObject(i);
 
                        // Storing each json item in variable
                        String id = c.getString(TAG_PID);
                        String naam = c.getString(TAG_MODELLEN);
                        
 
                        // creating new HashMap
                        HashMap<String, String> map = new HashMap<String, String>();
 
                        // adding each child node to HashMap key => value
                        map.put(TAG_PID, id);
                        map.put(TAG_MODELLEN, naam);
                        
 
                        // adding HashList to ArrayList
                        humanresourcesnamesList.add(map);
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
                            Humanresources.this, humanresourcesnamesList,
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