package com.example.ipmedt;

//
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);
		
		Thread splashscreen = new Thread()
		{
			public void run()
			{
				try
				{
					sleep(2000);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				finally
				{
					startActivity(new Intent(getApplicationContext(), Main.class));
					finish();
				}
			}
		};
		splashscreen.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	

}
