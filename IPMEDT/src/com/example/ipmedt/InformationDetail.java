package com.example.ipmedt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class InformationDetail extends Activity
{	
	public TextView detailinformatie;
	int modelID;

	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail_models);	
		
		detailinformatie = (TextView) findViewById(R.id.textView1);
		setInfo(modelID);
	}
	
	public void setInfo(int opdrachtgeverID)
	{
		if (SeModelFragment.modelID == 1)
			{
		 		detailinformatie.setText("model voor A");
			}
		if (SeModelFragment.modelID == 2)
			{
	     		detailinformatie.setText("model voor B");
			}
		if (SeModelFragment.modelID == 3)
			{
	     		detailinformatie.setText("model voor C");   	
			}
		if (SeModelFragment.modelID == 4)
			{
		 		detailinformatie.setText("model voor D");
			}
		if (SeModelFragment.modelID == 5)
			{
	     		detailinformatie.setText("model voor E");
			}
		if (SeModelFragment.modelID == 6)
			{
	     		detailinformatie.setText("model voor F");   	
			}
		if (SeModelFragment.modelID == 7)
			{
		 		detailinformatie.setText("Opdrachtgever voor G");
			}
		if (SeModelFragment.modelID == 8)
			{
	     		detailinformatie.setText("Opdrachtgever voor H");
			}
		
	}
}