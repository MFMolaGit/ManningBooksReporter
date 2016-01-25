package com.gevapps.manningreporter;

import android.app.*;
import android.graphics.*;
import android.os.*;
import com.jjoe64.graphview.*;
import com.jjoe64.graphview.series.*;

public class StatisticsActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistics);
		
		GraphView graph = (GraphView) findViewById(R.id.graph);
		BarGraphSeries<DataPoint> series = 
			new BarGraphSeries<DataPoint>(new DataPoint[] {
											new DataPoint(0, 1),
											new DataPoint(1, 5),
											new DataPoint(2, 3),
											new DataPoint(3, 2),
											new DataPoint(4, 6),
											new DataPoint(5, 10),
											new DataPoint(6, 4),
											new DataPoint(7, 7)
										});
		graph.addSeries(series);

		// styling
		series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
				@Override
				public int get(DataPoint data) {
					return Color.rgb((int) data.getX()*255/4, (int) Math.abs(data.getY()*255/6), 100);
				}
			});

		series.setSpacing(20);

		// draw values on top
		series.setDrawValuesOnTop(true);
		series.setValuesOnTopColor(Color.RED);
		//series.setValuesOnTopSize(50);
	}
	
}


