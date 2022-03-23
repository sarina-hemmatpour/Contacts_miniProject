package com.example.recyclerviewproject_contact;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactAdaptor extends RecyclerView.Adapter<ContactAdaptor.ContactViewHolder> {

    private ArrayList<Contact> contacts;

    public ContactAdaptor(Context context , ArrayList<Contact> list)
    {
        contacts=list;
        activity= (ItemClicked) context;
    }

    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }

    interface ItemClicked{
        void onItemClicked(int index);
    }
    ItemClicked activity;

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact , parent,false);

        return new ContactViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {

        holder.itemView.setTag(contacts.get(position));
        holder.bindItems(position);

    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder{

        TextView tvContactFirst , tvContactFull;

        public ContactViewHolder(@NonNull View itemView ) {
            super(itemView);

            tvContactFirst=itemView.findViewById(R.id.tvContactFirst);
            tvContactFull=itemView.findViewById(R.id.tvContactFull);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    activity.onItemClicked(contacts.indexOf((Contact) itemView.getTag()));
                }
            });

        }

        public void bindItems(int i)
        {
            tvContactFull.setText(getContacts().get(i).getFullName());
            tvContactFirst.setText(getContacts().get(i).getFullName().substring(0,1));
        }

    }

}
