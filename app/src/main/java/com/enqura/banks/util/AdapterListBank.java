package com.enqura.banks.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.recyclerview.widget.RecyclerView;

import com.enqura.banks.R;
import com.enqura.banks.data.remote.response.BankResponse;
import com.enqura.banks.data.remote.response.BankResponseItem;
import com.enqura.banks.ui.view.details.DetailsActivity;

public class AdapterListBank extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private BankResponse items;

    private Context ctx;

    @LayoutRes
    private int layout_id;

    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, BankResponseItem obj, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    public AdapterListBank(Context context, BankResponse items, @LayoutRes int layout_id) {
        this.items = items;
        ctx = context;
        this.layout_id = layout_id;
    }

    public class OriginalViewHolder extends RecyclerView.ViewHolder {
        public TextView bank_branch;
        public TextView bank_type;
        public TextView address;
        public View lyt_parent;

        public OriginalViewHolder(View v) {
            super(v);
            bank_branch = v.findViewById(R.id.bank_branch);
            bank_type = v.findViewById(R.id.bank_type);
            address = v.findViewById(R.id.address);
            lyt_parent = v.findViewById(R.id.lyt_parent);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v = LayoutInflater.from(parent.getContext()).inflate(layout_id, parent, false);
        vh = new OriginalViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        if (holder instanceof OriginalViewHolder) {
            OriginalViewHolder view = (OriginalViewHolder) holder;

            BankResponseItem bankResponseItems = items.get(position);
            if (bankResponseItems.getDc_BANKA_SUBE().isEmpty())
                view.bank_branch.setText(bankResponseItems.getDc_ILCE() + "/" + bankResponseItems.getDc_SEHIR());
            else
                view.bank_branch.setText(bankResponseItems.getDc_BANKA_SUBE());
            view.bank_type.setText(bankResponseItems.getDc_BANKA_TIPI());
            view.address.setText(bankResponseItems.getDc_ADRES());
            view.lyt_parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemClickListener == null) return;
                    mOnItemClickListener.onItemClick(view, items.get(position), position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}