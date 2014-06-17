<<<<<<< HEAD
package com.example.ipmedt;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SaleFragment extends Fragment implements OnClickListener
	 {
		
			@Override
			public View onCreateView(LayoutInflater inflater, ViewGroup container,
					Bundle savedInstanceState) {
				View rootView = inflater.inflate(R.layout.salefragment, container,
						false);
			
				//Knop defineren
				Button buynow = (Button) rootView.findViewById(R.id.buynow);
				
				return rootView;
	}

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
}

=======
package com.example.ipmedt;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SaleFragment extends Fragment implements OnClickListener
	 {
		
			@Override
			public View onCreateView(LayoutInflater inflater, ViewGroup container,
					Bundle savedInstanceState) {
				View rootView = inflater.inflate(R.layout.salefragment, container,
						false);
			
				//Knop defineren
				Button buynow = (Button) rootView.findViewById(R.id.buynow);
				
				return rootView;
	}

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
}

>>>>>>> 2cdfeb227231faba62911a395a6a643c189b9b3e
