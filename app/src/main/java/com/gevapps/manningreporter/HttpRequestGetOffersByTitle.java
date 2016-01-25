package com.gevapps.manningreporter;

import android.app.*;
import android.graphics.*;
import android.os.*;
import android.util.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import com.gevapps.manningreporter.*;
import java.util.*;
import org.springframework.core.*;
import org.springframework.http.*;
import org.springframework.http.converter.json.*;
import org.springframework.web.client.*;

public class HttpRequestGetOffersByTitle extends AsyncTask<String, Void, List<ManningOffer>>
 {

	private Activity activity;
	private static int lastOfferView;

	public HttpRequestGetOffersByTitle(Activity activity) {
		this.activity = activity;
		this.lastOfferView = 0;
	}

	@Override
	protected List<ManningOffer> doInBackground(String... title) {
		
		List<ManningOffer> manningOffers = null;
		
		try {
			final String url = "http://manningcatcherws-gevapps.rhcloud.com/gevapp/manningCatcher/list/" + title[0];
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

			ParameterizedTypeReference<List<ManningOffer>> responseType = new ParameterizedTypeReference<List<ManningOffer>>() {};
			ResponseEntity<List<ManningOffer>> resp = restTemplate.exchange(url, HttpMethod.GET, null, responseType);
			manningOffers = resp.getBody();
		} catch (Exception e) {
			Log.e("HttpRequestGetAllOffers", e.getMessage(), e);
		}

		return manningOffers;
	}

	@Override
	protected void onPostExecute(List<ManningOffer> manningOffers) {
		LinearLayout manningOffersLayout = (LinearLayout) activity.findViewById(R.id.offersList);

		manningOffersLayout.removeAllViews();
		
		for(final ManningOffer offer:manningOffers) {
			TextView manningOfferItem = new TextView(activity.getApplicationContext());
			manningOfferItem.setTextColor(Color.BLACK);
			manningOfferItem.setText(offer.getReadDate());
			manningOfferItem.setId(++lastOfferView);
			manningOfferItem.setOnClickListener(new OnClickListener() {			
					@Override
					public void onClick(View v) {
						HttpRequestGetOfferByDate getOffer = new HttpRequestGetOfferByDate(activity);        	
						getOffer.execute(offer.getReadDate().replace("/","-"));
						TextView lastOfferItemSelected =  (TextView) activity.findViewById(lastOfferView);

						if(lastOfferItemSelected != null) {
							lastOfferItemSelected.setBackgroundColor(Color.GRAY);
						}

						lastOfferView = v.getId();
						v.setBackgroundColor(Color.LTGRAY);
					}
				});
			manningOffersLayout.addView(manningOfferItem);
		}

	}

}
