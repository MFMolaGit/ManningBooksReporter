package com.gevapps.manningreporter;

import android.app.*;
import android.os.*;
import android.widget.EditText;
import android.widget.*;

public class MainActivity extends Activity {

	private EditText dateFilter, textFilter;
	private ScrollView offersView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

		textFilter = (EditText) findViewById(R.id.textFilter);
		dateFilter = (EditText) findViewById(R.id.dateFilter);
		offersView = (ScrollView) findViewById(R.id.offersView);


    }
}
