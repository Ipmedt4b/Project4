package com.example.ipmedt;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class Main extends Activity {
	
	ImageButton informationButton;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		buttonListener();
		
	}
	
	//de buttonListener methode zorgt ervoor dat er een nieuwe activity (pagina in de app) wordt aangeroepen wanneer
	//op een van de knoppen op de menu pagina wordt gedrukt.
	public void buttonListener()  
	{
		final Context context = this;
		//de knop voor de betrokken opdrachtgevers
		informationButton = (ImageButton) findViewById(R.id.buyBookButton1);
		informationButton.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0) 
			{
				Intent intent = new Intent(context, InformationFragment.class);
                startActivity(intent); 
			}
		});
		
	}
}
