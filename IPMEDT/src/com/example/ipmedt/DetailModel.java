package com.example.ipmedt;

import com.example.ipmedt.customer.Customer;
import com.example.ipmedt.diversity.Diversity;
import com.example.ipmedt.humanresources.Humanresources;
import com.example.ipmedt.innovation.Innovation;
import com.example.ipmedt.leadership.Leadership;
import com.example.ipmedt.results.Results;
import com.example.ipmedt.strategy.Strategy;
import com.example.ipmedt.sustainability.Sustainability;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailModel extends Activity
{	
	public TextView detailinformatie;
	int modelID;

	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.models);	
		
		detailinformatie = (TextView) findViewById(R.id.textView1);
		setInfo(modelID);
	}
	
	public void setInfo(int opdrachtgeverID)
	{
		if (SeModelFragment.modelID == 1)
			{
			Intent model1 = new Intent(getApplicationContext(), Sustainability.class);
     		startActivity(model1);
			}
		if (SeModelFragment.modelID == 2)
			{
			Intent model2 = new Intent(getApplicationContext(), Innovation.class);
     		startActivity(model2);
			}
		if (SeModelFragment.modelID == 3)
			{
			Intent model3 = new Intent(getApplicationContext(), Strategy.class);
     		startActivity(model3); 	
			}
		if (SeModelFragment.modelID == 4)
			{
			Intent model4 = new Intent(getApplicationContext(), Diversity.class);
     		startActivity(model4); 
			}
		if (SeModelFragment.modelID == 5)
			{
			Intent model5 = new Intent(getApplicationContext(), Customer.class);
     		startActivity(model5); 
			}
		if (SeModelFragment.modelID == 6)
			{
			Intent model6 = new Intent(getApplicationContext(), Humanresources.class);
     		startActivity(model6);
			}
		if (SeModelFragment.modelID == 7)
			{
			Intent model7 = new Intent(getApplicationContext(), Results.class);
     		startActivity(model7);
			}
		if (SeModelFragment.modelID == 8)
			{
			Intent model8 = new Intent(getApplicationContext(), Leadership.class);
     		startActivity(model8);
			}
		
	}
}