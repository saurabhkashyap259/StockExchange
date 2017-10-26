package com.mobile.stockexchange;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private AdapterCompanyList adapter;
    private List<ModelCompanyItem> companyItemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Local variables
        Toolbar toolbar;

        //get the ids
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        //Toolbar
        setSupportActionBar(toolbar);

        //Layout manager
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        //Adapter
        adapter = new AdapterCompanyList(this, companyItemList);
        recyclerView.setAdapter(adapter);

        //Get company list
        getCompanyList();

        //Interface
        adapter.setInterfaceAdapterCompanyList(new AdapterCompanyList.InterfaceAdapterCompanyList() {
            @Override
            public void onItemClick(ModelCompanyItem modelCompanyItem) {
                openActivitySingleStock(modelCompanyItem);
            }
        });
    }

    private void openActivitySingleStock(ModelCompanyItem modelCompanyItem) {
        Intent intent = new Intent(this, ActivitySingleStock.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("model", modelCompanyItem);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void getCompanyList() {

        companyItemList.clear();
        String[] stockNameList = {"Apple", "Reliance", "Axis Bank", "Bharti Airtel", "Maruti Suzuki"};
        String[] stockTickerNameList = {"APL", "RIL", "AXS", "AIR", "MSZ"};

        for(int i=0; i<5; i++) {
            ModelCompanyItem modelCompanyItem = new ModelCompanyItem();
            modelCompanyItem.setStockName(stockNameList[i]);
            modelCompanyItem.setStockTickerName(stockTickerNameList[i]);
            ArrayList<String> stockValues = new ArrayList<>();
            stockValues.addAll(getRandomStockValues());
            modelCompanyItem.setStockValues(stockValues);
            modelCompanyItem.setStockCurrentValue(stockValues.get(stockValues.size()-1));
            if(Integer.parseInt(stockValues.get(stockValues.size()-1)) > Integer.parseInt(stockValues.get(stockValues.size()-2))) {
                modelCompanyItem.setIncreased(true);
            }else {
                modelCompanyItem.setIncreased(false);
            }

            companyItemList.add(modelCompanyItem);
        }
        adapter.notifyDataSetChanged();
    }

    private ArrayList<String> getRandomStockValues() {
        int min = 0;
        int max = 999;
        ArrayList<String> stockValues = new ArrayList<>();

        for(int i=0; i<8; i++) {
            int random = min + (int)(Math.random() * ((max - min) + 1));
            stockValues.add(String.valueOf(random));
        }
        return stockValues;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_replay) {
            getCompanyList();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
