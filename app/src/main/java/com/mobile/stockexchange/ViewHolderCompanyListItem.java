package com.mobile.stockexchange;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by saurabhkashyap on 26/10/17.
 */

public class ViewHolderCompanyListItem extends RecyclerView.ViewHolder {

    public TextView stockName;
    public TextView stockTickerName;
    public TextView stockValue;
    public ImageView stockValueImage;
    public LinearLayout rootLayout;

    public ViewHolderCompanyListItem(View itemView) {
        super(itemView);

        stockName = (TextView) itemView.findViewById(R.id.stock_name);
        stockTickerName = (TextView) itemView.findViewById(R.id.stock_ticker_name);
        stockValue = (TextView) itemView.findViewById(R.id.stock_value);
        stockValueImage = (ImageView) itemView.findViewById(R.id.stock_value_image);
        rootLayout = (LinearLayout) itemView.findViewById(R.id.root_layout);
    }
}
