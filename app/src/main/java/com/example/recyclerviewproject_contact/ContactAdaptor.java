package com.example.recyclerviewproject_contact;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactAdaptor extends RecyclerView.Adapter<ContactAdaptor.ContactViewHolder> {

    //list of contacts

    private ArrayList<Contact> contacts;
    private static final String TAG = "ContactAdaptor";

    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }

    // ////////////////////////////////

    ItemClicked activity;

    public ContactAdaptor(Context context , ArrayList<Contact> list)
    {
        contacts=list;
        activity= (ItemClicked) context;
    }

    interface ItemClicked{
        void onItemClicked(int index);
        void onItemLongClicked(int index);
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Log.i(TAG, "onCreateViewHolder: ");
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact , parent,false);

        return new ContactViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {

        Log.i(TAG, "onBindViewHolder: position => "+position);
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

            //view to java code
            tvContactFirst=itemView.findViewById(R.id.tvSheetFirst);
            tvContactFull=itemView.findViewById(R.id.tvSheettFull);

        }

        public void bindItems(int i)
        {
            tvContactFull.setText(getContacts().get(i).getFullName());
            tvContactFirst.setText(getContacts().get(i).getFullName().substring(0,1));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // or we could easily use getAdapterPosition() instead of tags
                    activity.onItemClicked(contacts.indexOf((Contact) itemView.getTag()));
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    activity.onItemLongClicked(getAdapterPosition());

                    //agar bekhaym jaye dige i ham long listener set konim bayad bezarim false bargardune
                    return false;
                }
            });
        }

    }

    public void addContact(String fullName , String number)
    {
        contacts.add(0, new Contact(fullName , number));
        notifyItemInserted(0);
    }
    public void editContact(int index , String fullName , String number)
    {
        contacts.get(index).setFullName(fullName);
        notifyItemChanged(index);
        //************************* number
    }
    public void removeContact(int index)
    {
        contacts.remove(index);
        notifyItemRemoved(index);
    }

}
