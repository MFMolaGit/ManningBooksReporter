package com.gevapps.manningreporter;

import android.app.*;
import android.os.*;
import android.widget.*;
import java.text.*;
import java.util.*;
import android.util.*;
import android.view.*;

public class MainActivity extends Activity {

	private EditText dateFilter, textFilter;
	private ScrollView offersView;
	private ToggleButton searchOption;
	private Button searchButton;
	private Character option;
	final SimpleDateFormat SDF = new SimpleDateFormat("dd-MM-yyyy");
	final SimpleDateFormat SDF_SEARCH = new SimpleDateFormat("dd/MM/yyyy");
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

		textFilter = (EditText) findViewById(R.id.textFilter);
		dateFilter = (EditText) findViewById(R.id.dateFilter);
		offersView = (ScrollView) findViewById(R.id.offersView);
		searchOption = (ToggleButton) findViewById(R.id.searchOption);
		searchButton = (Button) findViewById(R.id.searchButton);
		
		getAllOffers();
		getOfferByDate(new Date());
		option = 'A';
    }

	public void switchSearchOption(View v) {
		option = searchOption.isChecked()?'D':'T';
	}
	
	public void searchOffers(View View) {
		switch(option) {
			case 'A': getAllOffers();
				break;
			case 'D': 
				try {
					getOfferByDate(SDF_SEARCH.parse(dateFilter.getText().toString()));
				} catch (ParseException e) {
					Log.e("MainActivity", e.getMessage());
				}
				break;
			case 'T': getOffersByTitle(textFilter.getText().toString());
				break;
		}
	}
	
	private void getOfferByDate(Date date) {
		HttpRequestGetOfferByDate getOffer = new HttpRequestGetOfferByDate(MainActivity.this);        	
			getOffer.execute(SDF.format(date));
	}

	private void getAllOffers() {
		HttpRequestGetAllOffers allOffers = new HttpRequestGetAllOffers(MainActivity.this);        	
			allOffers.execute();
	}
	
	private void getOffersByTitle(String title) {
		HttpRequestGetOffersByTitle offers = new HttpRequestGetOffersByTitle(MainActivity.this);        	
			offers.execute(title);
	}
}
