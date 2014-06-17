package com.example.ipmedt;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Main extends Activity {
	
	
	Button information;
	Button book;
	Button models;
	
	
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
		//de knop voor de informatie
		information = (Button) findViewById(R.id.information);
		information.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0) 
			{
				Intent intent = new Intent(context, InformationFragment.class);
                startActivity(intent); 
			}
		});
		//de knop om het boek te kopen
<<<<<<< HEAD

		
		book = (Button) findViewById(R.id.button_sales);

=======
		book = (Button) findViewById(R.id.sale);
>>>>>>> f7ecedffe41e9daa455d4889c43eeed4f858f596
		book.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View arg0) 
			{
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.amazon.com/100-Management-Models-understand-powerful/dp/1908984228/"));
                startActivity(intent); 
			}		
		});	
		
		
		//de knop voor de modellen
		models = (Button) findViewById(R.id.models);
		models.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View arg0) 
			{
				Intent intent = new Intent(context, SeModelFragment.class);
                startActivity(intent); 
			}
		});	

		
		
		
		
		
		
		
	}
}
