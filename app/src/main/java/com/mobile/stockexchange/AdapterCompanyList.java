package com.mobile.stockexchange;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by saurabhkashyap on 26/10/17.
 */

public class AdapterCompanyList extends RecyclerView.Adapter<ViewHolderCompanyListItem>{

    private Context context;
    private List<ModelCompanyItem> companyItemList;
    private LayoutInflater layoutInflater;
    private InterfaceAdapterCompanyList interfaceAdapterCompanyList;

    public AdapterCompanyList(Context context, List<ModelCompanyItem> companyItemList) {
        this.context = context;
        this.companyItemList = companyItemList;
    }

    @Override
    public ViewHolderCompanyListItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View row;
        if(layoutInflater == null){
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        row = layoutInflater.inflate(R.layout.layout_company_list_item, parent, false);
        ViewHolderCompanyListItem viewHolder = new ViewHolderCompanyListItem(row);
        row.setTag(viewHolder);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolderCompanyListItem holder, int position) {
        final ModelCompanyItem modelCompanyItem = companyItemList.get(holder.getAdapterPosition());

        if(modelCompanyItem != null) {
            holder.stockName.setText(modelCompanyItem.getStockName());
            holder.stockTickerName.setText(modelCompanyItem.getStockTickerName());
            holder.stockValue.setText(modelCompanyItem.getStockCurrentValue());

            if(modelCompanyItem.isIncreased()) {
                holder.stockValue.setTextColor(Color.parseColor("#4CAF50"));
                holder.stockValueImage.setImageResource(R.drawable.ic_arrow_up_green);
            }else {
                holder.stockValue.setTextColor(Color.parseColor("#F44336"));
                holder.stockValueImage.setImageResource(R.drawable.ic_arrow_down_red);
            }

            holder.rootLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    interfaceAdapterCompanyList.onItemClick(modelCompanyItem);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return companyItemList.size();
    }

    public interface InterfaceAdapterCompanyList {
        void onItemClick(ModelCompanyItem modelCompanyItem);
    }

    public void setInterfaceAdapterCompanyList(InterfaceAdapterCompanyList listener) {
        this.interfaceAdapterCompanyList = listener;
    }
}
