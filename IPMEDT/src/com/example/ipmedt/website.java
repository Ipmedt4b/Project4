package com.example.ipmedt;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;

public class website extends Activity {

	Button button;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_information);

		addListenerOnButton();

	}

	public void addListenerOnButton() {

		button = (Button) findViewById(R.id.button1);

		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.mkyong.com"));
				startActivity(browserIntent);

			}

		});

	}

}