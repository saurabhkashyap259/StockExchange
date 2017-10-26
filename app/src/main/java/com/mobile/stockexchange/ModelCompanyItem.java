package com.mobile.stockexchange;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by saurabhkashyap on 26/10/17.
 */

public class ModelCompanyItem implements Serializable {

    private static final long serialVersionUID = -7806885414570971602L;
    private String stockName, stockTickerName, stockCurrentValue;
    private ArrayList<String> stockValues;
    private boolean isIncreased;

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getStockTickerName() {
        return stockTickerName;
    }

    public void setStockTickerName(String stockTickerName) {
        this.stockTickerName = stockTickerName;
    }

    public String getStockCurrentValue() {
        return stockCurrentValue;
    }

    public void setStockCurrentValue(String stockCurrentValue) {
        this.stockCurrentValue = stockCurrentValue;
    }

    public ArrayList<String> getStockValues() {
        return stockValues;
    }

    public void setStockValues(ArrayList<String> stockValues) {
        this.stockValues = stockValues;
    }

    public boolean isIncreased() {
        return isIncreased;
    }

    public void setIncreased(boolean increased) {
        isIncreased = increased;
    }
}
