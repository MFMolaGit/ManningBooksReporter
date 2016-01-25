package com.gevapps.manningreporter;

	import android.app.*;
	import android.os.*;
	import android.util.*;
	import android.widget.*;
	import java.util.*;
	import org.springframework.http.*;
	import org.springframework.http.converter.json.*;
	import org.springframework.web.client.*;
	import org.springframework.core.*;
	import android.graphics.*;

public class HttpRequestGetOfferByDate extends AsyncTask<String, Void, ManningOffer> {

	private Activity activity;

	public HttpRequestGetOfferByDate(Activity activity) {
			this.activity = activity;
	}

		@Override
		protected ManningOffer doInBackground(String... date) {
			ManningOffer manningOffer = null;
			
			try {
				final String url = "http://manningcatcherws-gevapps.rhcloud.com/gevapp/manningCatcher/offer/" + date[0];
				RestTemplate restTemplate = new RestTemplate();
					restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

				ResponseEntity<ManningOffer> manningOfferResp = restTemplate.getForEntity(url, ManningOffer.class);
					manningOffer = manningOfferResp.getBody();
			} catch (Exception e) {
				Log.e("HttpRequestGetOfferByDate", e.getMessage(), e);
			}

			return manningOffer;
		}

		@Override
		protected void onPostExecute(ManningOffer manningOffer) {
			TextView currentOfferLabelTv = (TextView) activity.findViewById(R.id.currentOfferLabel);
			TextView currentOfferDateTv = (TextView) activity.findViewById(R.id.currentOfferDate);
			TextView currentOfferCodeTv = (TextView) activity.findViewById(R.id.currentOfferCode);
			TextView currentOfferBook1Tv = (TextView) activity.findViewById(R.id.currentOfferBook1);
			TextView currentOfferBook2Tv = (TextView) activity.findViewById(R.id.currentOfferBook2);
			TextView currentOfferBook3Tv = (TextView) activity.findViewById(R.id.currentOfferBook3);
			
			if(manningOffer != null) {
				currentOfferCodeTv.setText(manningOffer.getOffercode());
				
				String offerDate = manningOffer.getReadDate();
				
				currentOfferDateTv.setText(offerDate);
				currentOfferLabelTv.setText("Oferta del " + offerDate);
				
				List<Book> books = manningOffer.getPack().getBooks();

				String book = "";
			
				if(books.size() > 0) {
					book = books.get(0).getTitle();
				}
				currentOfferBook1Tv.setText(book);
			
				book = "";
				if(books.size() > 1) {
					book = books.get(1).getTitle();
				}
				currentOfferBook2Tv.setText(book);
			
				book = "";
				if(books.size() > 2) {
					book = books.get(2).getTitle();
				}
				currentOfferBook3Tv.setText(book);
			} else {
				currentOfferCodeTv.setText("Aún no hay oferta hoy");
			}
		}

	}
