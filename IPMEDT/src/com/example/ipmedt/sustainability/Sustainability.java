package com.example.ipmedt.sustainability;

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
 
public class Sustainability extends ListActivity {
 
    // Progress Dialog
    private ProgressDialog pDialog;
    public static int sustainabilitynamesID;
    
    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();
 
    ArrayList<HashMap<String, String>> sustainabilitynamesList;
 
    // url to get all products list
    private static String url_all_sustainabilitynames = "http://jellescheer.nl/williebrordardus/get_all_subtainabilitynames.php";
 
    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_NAMESSUSTAINABILITY = "namessustainability";
    private static final String TAG_PID = "pid";
    private static final String TAG_MODELLEN = "modellen";
  
    
    
 
    // products JSONArray
    JSONArray sustainabilitynames = null;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.models);
 
        // Hashmap for ListView
        sustainabilitynamesList = new ArrayList<HashMap<String, String>>();
 
        // Loading products in Background Thread
        new LoadAllSustainabilitynames().execute();
 
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
            	// linkt naar de juiste model pagina

             	switch (position)
             	{
             	case 0:
             		sustainabilitynamesID = 1;
             		Intent sustainabilityname1 = new Intent(getApplicationContext(), Sustainability_model1.class);
             		startActivity(sustainabilityname1);
             		break;
             	case 1:
             		sustainabilitynamesID = 2;
             		Intent sustainabilityname2 = new Intent(getApplicationContext(), Sustainability_model2.class);
             		startActivity(sustainabilityname2);
             		break;
             	case 2:
             		sustainabilitynamesID = 3;
             		Intent sustainabilityname3 = new Intent(getApplicationContext(), Sustainability_model3.class);
             		startActivity(sustainabilityname3);
             		break;
             	case 3:
             		sustainabilitynamesID = 4;
             		Intent sustainabilityname4 = new Intent(getApplicationContext(), Sustainability_model4.class);
             		startActivity(sustainabilityname4);
             		break;
             	case 4:
             		sustainabilitynamesID = 5;
             		Intent sustainabilityname5 = new Intent(getApplicationContext(), Sustainability_model5.class);
             		startActivity(sustainabilityname5);
             		break;
             	case 5:
             		sustainabilitynamesID = 6;
             		Intent sustainabilityname6 = new Intent(getApplicationContext(), Sustainability_model6.class);
             		startActivity(sustainabilityname6);
             		break;
             	case 6:
             		sustainabilitynamesID = 7;
             		Intent sustainabilityname7 = new Intent(getApplicationContext(), Sustainability_model6.class);
             		startActivity(sustainabilityname7);
             		break;
             	
             	}
            }
        });
 
    }
 

 
    /**
     * Background Async Task to Load all product by making HTTP Request
     * */
    class LoadAllSustainabilitynames extends AsyncTask<String, String, String> {
 
        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Sustainability.this);
            pDialog.setMessage("Loading sustainability models. Please wait...");
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
            JSONObject json = jParser.makeHttpRequest(url_all_sustainabilitynames, "GET", params);
 
            // Check your log cat for JSON reponse
            Log.d("All Sustainability: ", json.toString());
 
            try {
                // Checking for SUCCESS TAG
                int success = json.getInt(TAG_SUCCESS);
 
                if (success == 1) {
                    // products found
                    // Getting Array of Products
                	sustainabilitynames = json.getJSONArray(TAG_NAMESSUSTAINABILITY);
 
                    // looping through All Products
                    for (int i = 0; i < sustainabilitynames.length(); i++) {
                        JSONObject c = sustainabilitynames.getJSONObject(i);
 
                        // Storing each json item in variable
                        String id = c.getString(TAG_PID);
                        String naam = c.getString(TAG_MODELLEN);
                        
 
                        // creating new HashMap
                        HashMap<String, String> map = new HashMap<String, String>();
 
                        // adding each child node to HashMap key => value
                        map.put(TAG_PID, id);
                        map.put(TAG_MODELLEN, naam);
                        
 
                        // adding HashList to ArrayList
                        sustainabilitynamesList.add(map);
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
                            Sustainability.this, sustainabilitynamesList,
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