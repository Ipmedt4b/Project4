package com.example.ipmedt;

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
		setContentView(R.layout.detail_modelst);	
		
		detailinformatie = (TextView) findViewById(R.id.textView1);
		setInfo(modelID);
	}
	
	public void setInfo(int opdrachtgeverID)
	{
		if (InformationFragment.modelID == 1)
			{
		 		detailinformatie.setText("model voor A");
			}
		if (InformationFragment.modelID == 2)
			{
	     		detailinformatie.setText("model voor B");
			}
		if (InformationFragment.modelID == 3)
			{
	     		detailinformatie.setText("model voor C");   	
			}
		if (InformationFragment.modelID == 4)
			{
		 		detailinformatie.setText("model voor D");
			}
		if (InformationFragment.modelID == 5)
			{
	     		detailinformatie.setText("model voor E");
			}
		if (InformationFragment.modelID == 6)
			{
	     		detailinformatie.setText("model voor F");   	
			}
		if (InformationFragment.modelID == 7)
			{
		 		detailinformatie.setText("Opdrachtgever voor G");
			}
		if (InformationFragment.modelID == 8)
			{
	     		detailinformatie.setText("Opdrachtgever voor H");
			}
		
	}
}