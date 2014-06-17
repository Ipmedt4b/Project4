package com.example.ipmedt.customer;

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
 
public class Customer extends ListActivity {
 
    // Progress Dialog
    private ProgressDialog pDialog;
    public static int customernamesID;
    
    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();
 
    ArrayList<HashMap<String, String>> customernamesList;
 
    // url to get all products list
    private static String url_all_customernames = "http://jellescheer.nl/williebrordardus/get_all_customernames.php";
 
    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_NAMESCUSTOMER = "namescustomer";
    private static final String TAG_PID = "pid";
    private static final String TAG_MODELLEN = "modellen";
  
    
    
 
    // products JSONArray
    JSONArray customernames = null;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.models);
 
        // Hashmap for ListView
        customernamesList = new ArrayList<HashMap<String, String>>();
 
        // Loading products in Background Thread
        new LoadAllcustomernames().execute();
 
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
            	// stuurt je naar de juiste pagina voor het model
             	switch (position)
             	{
             	case 0:
             		customernamesID = 1;
             		Intent customername1 = new Intent(getApplicationContext(), Customer_model1.class);
             		startActivity(customername1);
             		break;
             	case 1:
             		customernamesID = 2;
             		Intent customername2 = new Intent(getApplicationContext(), Customer_model2.class);
             		startActivity(customername2);
             		break;
             	case 2:
             		customernamesID = 3;
             		Intent customername3 = new Intent(getApplicationContext(), Customer_model3.class);
             		startActivity(customername3);
             		break;
             	case 3:
             		customernamesID = 4;
             		Intent customername4 = new Intent(getApplicationContext(), Customer_model4.class);
             		startActivity(customername4);
             		break;
             	case 4:
             		customernamesID = 5;
             		Intent customername5 = new Intent(getApplicationContext(), Customer_model5.class);
             		startActivity(customername5);
             		break;
             	case 5:
             		customernamesID = 6;
             		Intent customername6 = new Intent(getApplicationContext(), Customer_model6.class);
             		startActivity(customername6);
             		break;
             	case 6:
             		customernamesID = 7;
             		Intent customername7 = new Intent(getApplicationContext(), Customer_model7.class);
             		startActivity(customername7);
             		break;
             	case 7:
             		customernamesID = 8;
             		Intent customername8 = new Intent(getApplicationContext(), Customer_model8.class);
             		startActivity(customername8);
             		break;
             	case 8:
             		customernamesID = 9;
             		Intent customername9 = new Intent(getApplicationContext(), Customer_model9.class);
             		startActivity(customername9);
             		break;
             	case 9:
             		customernamesID = 10;
             		Intent customername10 = new Intent(getApplicationContext(), Customer_model10.class);
             		startActivity(customername10);
             		break;
             	
             	}
            }
        });
 
    }
 

 
    /**
     * Background Async Task to Load all product by making HTTP Request
     * */
    class LoadAllcustomernames extends AsyncTask<String, String, String> {
 
        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Customer.this);
            pDialog.setMessage("Loading customer models. Please wait...");
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
            JSONObject json = jParser.makeHttpRequest(url_all_customernames, "GET", params);
 
            // Check your log cat for JSON reponse
            Log.d("All customer: ", json.toString());
 
            try {
                // Checking for SUCCESS TAG
                int success = json.getInt(TAG_SUCCESS);
 
                if (success == 1) {
                    // products found
                    // Getting Array of Products
                	customernames = json.getJSONArray(TAG_NAMESCUSTOMER);
 
                    // looping through All Products
                    for (int i = 0; i < customernames.length(); i++) {
                        JSONObject c = customernames.getJSONObject(i);
 
                        // Storing each json item in variable
                        String id = c.getString(TAG_PID);
                        String naam = c.getString(TAG_MODELLEN);
                        
 
                        // creating new HashMap
                        HashMap<String, String> map = new HashMap<String, String>();
 
                        // adding each child node to HashMap key => value
                        map.put(TAG_PID, id);
                        map.put(TAG_MODELLEN, naam);
                        
 
                        // adding HashList to ArrayList
                        customernamesList.add(map);
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
                            Customer.this, customernamesList,
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