package com.example.ipmedt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
 
import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
 
public class SeModelFragment extends ListActivity {
 
    // Progress Dialog
    private ProgressDialog pDialog;
    public static int modelID;
    
    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();
 
    ArrayList<HashMap<String, String>> modelsList;
 
    // url to get all products list
    private static String url_all_models = "http://www.jellescheer.nl/williebrordardus/get_all_models1.php";
 
    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MODELS = "models";
    private static final String TAG_PID = "pid";
    private static final String TAG_NAAM = "naam";
    private static final String TAG_BESCHRIJVING = "beschrijving";
    private static final String TAG_MODELS_AFB = "models_afb";
    
 
    // products JSONArray
    JSONArray models = null;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.models);
 
        // Hashmap for ListView
        modelsList = new ArrayList<HashMap<String, String>>();
 
        // Loading products in Background Thread
        new LoadAllModels().execute();
 
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
             		modelID = 1;
             		Intent model1 = new Intent(getApplicationContext(), DetailModel.class);
             		startActivity(model1);
             		break;
             	case 1:
             		modelID = 2;
             		Intent model2 = new Intent(getApplicationContext(), DetailModel.class);
             		startActivity(model2);
             		break;
             	case 2:
             		modelID = 3;
             		Intent model3 = new Intent(getApplicationContext(), DetailModel.class);
             		startActivity(model3);
             		break;
             	case 3:
             		modelID = 4;
             		Intent model4 = new Intent(getApplicationContext(), DetailModel.class);
             		startActivity(model4);
             		break;
             	case 4:
             		modelID = 5;
             		Intent model5 = new Intent(getApplicationContext(), DetailModel.class);
             		startActivity(model5);
             		break;
             	case 5:
             		modelID = 6;
             		Intent model6 = new Intent(getApplicationContext(), DetailModel.class);
             		startActivity(model6);
             		break;
             	case 6:
             		modelID = 7;
             		Intent model7 = new Intent(getApplicationContext(), DetailModel.class);
             		startActivity(model7);
             		break;
             	case 7:
             		modelID = 8;
             		Intent model8 = new Intent(getApplicationContext(), DetailModel.class);
             		startActivity(model8);
             		break;
             	case 8:
             		modelID = 9;
             		Intent model9 = new Intent(getApplicationContext(), DetailModel.class);
             		startActivity(model9);
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
    class LoadAllModels extends AsyncTask<String, String, String> {
 
        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(SeModelFragment.this);
            pDialog.setMessage("Loading Models. Please wait...");
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
            JSONObject json = jParser.makeHttpRequest(url_all_models, "GET", params);
 
            // Check your log cat for JSON reponse
            Log.d("All Models: ", json.toString());
 
            try {
                // Checking for SUCCESS TAG
                int success = json.getInt(TAG_SUCCESS);
 
                if (success == 1) {
                    // products found
                    // Getting Array of Products
                    models = json.getJSONArray(TAG_MODELS);
 
                    // looping through All Products
                    for (int i = 0; i < models.length(); i++) {
                        JSONObject c = models.getJSONObject(i);
 
                        // Storing each json item in variable
                        String id = c.getString(TAG_PID);
                        String naam = c.getString(TAG_NAAM);
                        String beschrijving = c.getString(TAG_BESCHRIJVING);
 
                        // creating new HashMap
                        HashMap<String, String> map = new HashMap<String, String>();
 
                        // adding each child node to HashMap key => value
                        map.put(TAG_PID, id);
                        map.put(TAG_NAAM, naam);
                        map.put(TAG_BESCHRIJVING, beschrijving);
 
                        // adding HashList to ArrayList
                        modelsList.add(map);
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
                            SeModelFragment.this, modelsList,
                            R.layout.model_list, new String[] {TAG_PID,
                                    TAG_NAAM, TAG_BESCHRIJVING},
                            new int[] { R.id.pid, R.id.naam, R.id.logo });
                    // updating listview
                    setListAdapter(adapter);
                }
            });
 
        }
 
    }
}