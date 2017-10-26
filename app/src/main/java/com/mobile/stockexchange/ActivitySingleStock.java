package com.mobile.stockexchange;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by saurabhkashyap on 26/10/17.
 */

public class ActivitySingleStock extends AppCompatActivity {

    private ModelCompanyItem modelCompanyItem;
    private DataPoint[] dataPoints;
    private ArrayList<Date> dateList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_stock);

        //Bundle
        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            modelCompanyItem = (ModelCompanyItem) bundle.getSerializable("model");
        }else {
            Toast.makeText(this, "Some error occurred", Toast.LENGTH_SHORT).show();
            finish();
        }

        //Local variables
        Toolbar toolbar;
        GraphView graphView;

        //Get the ids
        toolbar = (Toolbar) findViewById(R.id.single_stock_toolbar);
        graphView = (GraphView) findViewById(R.id.graph_view);

        //Toolbar
        toolbar.setTitle(modelCompanyItem.getStockName());
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //Graph
        dateList.addAll(getLastSevenDates());
        getDataPoints();
        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(dataPoints);
        graphView.addSeries(series);

        series.setSpacing(10);
        series.setDrawValuesOnTop(true);
        series.setValuesOnTopColor(Color.RED);
    }

    private DataPoint[] getDataPoints() {
        dataPoints = new DataPoint[8];
        for(int i=0; i<8; i++) {
            int value = Integer.parseInt(modelCompanyItem.getStockValues().get(i));
            int valuePercentage = (value*100)/1000;
            DataPoint dataPoint = new DataPoint(i, valuePercentage);
            dataPoints[i] = dataPoint;
        }
        return dataPoints;
    }

    private ArrayList<Date> getLastSevenDates() {
        ArrayList<Date> dateList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Calendar cal = Calendar.getInstance();
        // get starting date
        cal.add(Calendar.DAY_OF_YEAR, -6);

        // loop adding one day in each iteration
        for(int i = 0; i< 8; i++){
            cal.add(Calendar.DAY_OF_YEAR, 1);
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.AM_PM, 0);
            cal.set(Calendar.HOUR, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            try {
                dateList.add(sdf.parse(sdf.format(cal.getTime())));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return dateList;
    }
}
