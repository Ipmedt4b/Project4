package com.example.ipmedt.diversity;

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
 
public class Diversity extends ListActivity {
 
    // Progress Dialog
    private ProgressDialog pDialog;
    public static int diversitynamesID;
    
    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();
 
    ArrayList<HashMap<String, String>> diversitynamesList;
 
    // url to get all products list
    private static String url_all_diversitynames = "http://jellescheer.nl/williebrordardus/get_all_diversitynames.php";
 
    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_NAMESDIVERSITY = "namesdiversity";
    private static final String TAG_PID = "pid";
    private static final String TAG_MODELLEN = "modellen";
  
    
    
 
    // products JSONArray
    JSONArray diversitynames = null;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.models);
 
        // Hashmap for ListView
        diversitynamesList = new ArrayList<HashMap<String, String>>();
 
        // Loading products in Background Thread
        new LoadAlldiversitynames().execute();
 
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
            	// stuurt je naar de juiste pagina van de modellen
             	switch (position)
             	{
             	case 0:
             		diversitynamesID = 1;
             		Intent diversityname1 = new Intent(getApplicationContext(), Diversity_model1.class);
             		startActivity(diversityname1);
             		break;
             	case 1:
             		diversitynamesID = 2;
             		Intent diversityname2 = new Intent(getApplicationContext(), Diversity_model2.class);
             		startActivity(diversityname2);
             		break;
             	case 2:
             		diversitynamesID = 3;
             		Intent diversityname3 = new Intent(getApplicationContext(), Diversity_model3.class);
             		startActivity(diversityname3);
             		break;
             	case 3:
             		diversitynamesID = 4;
             		Intent diversityname4 = new Intent(getApplicationContext(), Diversity_model4.class);
             		startActivity(diversityname4);
             		break;
             	case 4:
             		diversitynamesID = 5;
             		Intent diversityname5 = new Intent(getApplicationContext(), Diversity_model5.class);
             		startActivity(diversityname5);
             		break;
             	case 5:
             		diversitynamesID = 6;
             		Intent diversityname6 = new Intent(getApplicationContext(), Diversity_model6.class);
             		startActivity(diversityname6);
             		break;
             	case 6:
             		diversitynamesID = 7;
             		Intent diversityname7 = new Intent(getApplicationContext(), Diversity_model7.class);
             		startActivity(diversityname7);
             		break;
             	case 7:
             		diversitynamesID = 8;
             		Intent diversityname8 = new Intent(getApplicationContext(), Diversity_model8.class);
             		startActivity(diversityname8);
             		break;
             	case 8:
             		diversitynamesID = 9;
             		Intent diversityname9 = new Intent(getApplicationContext(), Diversity_model9.class);
             		startActivity(diversityname9);
             		break;
             	case 9:
             		diversitynamesID = 10;
             		Intent diversityname10 = new Intent(getApplicationContext(), Diversity_model10.class);
             		startActivity(diversityname10);
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
    class LoadAlldiversitynames extends AsyncTask<String, String, String> {
 
        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Diversity.this);
            pDialog.setMessage("Loading diversity models. Please wait...");
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
            JSONObject json = jParser.makeHttpRequest(url_all_diversitynames, "GET", params);
 
            // Check your log cat for JSON reponse
            Log.d("All diversity: ", json.toString());
 
            try {
                // Checking for SUCCESS TAG
                int success = json.getInt(TAG_SUCCESS);
 
                if (success == 1) {
                    // products found
                    // Getting Array of Products
                	diversitynames = json.getJSONArray(TAG_NAMESDIVERSITY);
 
                    // looping through All Products
                    for (int i = 0; i < diversitynames.length(); i++) {
                        JSONObject c = diversitynames.getJSONObject(i);
 
                        // Storing each json item in variable
                        String id = c.getString(TAG_PID);
                        String naam = c.getString(TAG_MODELLEN);
                        
 
                        // creating new HashMap
                        HashMap<String, String> map = new HashMap<String, String>();
 
                        // adding each child node to HashMap key => value
                        map.put(TAG_PID, id);
                        map.put(TAG_MODELLEN, naam);
                        
 
                        // adding HashList to ArrayList
                        diversitynamesList.add(map);
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
                            Diversity.this, diversitynamesList,
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