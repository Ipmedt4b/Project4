package com.example.ipmedt;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Main extends Activity {
	
	
	
	
	
		private Button NewPage;

		/** Called when the activity is first created. */

		@Override

		public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		this.NewPage = (Button)this.findViewById(R.id.close);

		this.NewPage.setOnClickListener(new OnClickListener() {

		@Override

		public void onClick(View v) {

		Intent i = new Intent(Main.this, New.class);

		startActivity(i);

		}

		});

		}

		}
	
	
	
	//de buttonListener methode zorgt ervoor dat er een nieuwe activity (pagina in de app) wordt aangeroepen wanneer
	//op een van de knoppen op de menu pagina wordt gedrukt.
//	public void buttonListener()  
//	{
	//	final Context context = this;
		//de knop voor de betrokken opdrachtgevers
//	Button Button1 = (Button) findViewById(R.id.button_information);
//	Button1.setOnClickListener(new OnClickListener()
//	{
//		@Override
	//		public void onClick(View arg0) 
	//	{
	//		Intent intent = new Intent(context, InformationFragment.class);
    //          startActivity(intent); 
	//	}
	//	});
		
//	}
//}