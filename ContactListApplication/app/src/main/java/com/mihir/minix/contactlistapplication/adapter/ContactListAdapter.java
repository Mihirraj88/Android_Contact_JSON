package com.mihir.minix.contactlistapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.mihir.minix.contactlistapplication.R;
import com.mihir.minix.contactlistapplication.model.Address;
import com.mihir.minix.contactlistapplication.model.Contact;
import com.mihir.minix.contactlistapplication.model.Geo;
import com.mihir.minix.contactlistapplication.utils.RecyclerViewAnimator;

import java.util.ArrayList;
import java.util.List;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ViewHolder> {

    Context context;
    private List<Contact> itemViewArrayList;
     private LayoutInflater mInflator;
    private RecyclerViewAnimator mAnimator;
    public ContactListAdapter(Context context, List<Contact> itemViewArrayList,RecyclerView recyclerView) {

        this.context = context;
        this.itemViewArrayList = itemViewArrayList;
        mInflator = LayoutInflater.from(context);
        mAnimator = new RecyclerViewAnimator(recyclerView);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        mAnimator.onCreateViewHolder(v);
        return vh;
       // return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String Lat=itemViewArrayList.get(position).getAddress().getGeo().getLat();
        //holder.mAmcStatus.setText(itemViewArrayList.get(position).getmAmcStatus());
        holder.mName.setText(itemViewArrayList.get(position).getName());
        holder.mCompanyName.setText(itemViewArrayList.get(position).getCompany().getName());
        mAnimator.onBindViewHolder(holder.itemView, position);
    }


    @Override
    public int getItemCount() {
        return itemViewArrayList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mName,mCompanyName;
        //public TextView mAmcStatus;


        public ViewHolder(View itemView) {
            super(itemView);
            mName = (TextView) itemView.findViewById(R.id.Name);
            mCompanyName = (TextView) itemView.findViewById(R.id.com_name);


        }
    }

}
