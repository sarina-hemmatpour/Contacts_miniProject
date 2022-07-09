package com.example.recyclerviewproject_contact;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class MainActivity extends AppCompatActivity  implements ContactAdaptor.ItemClicked, DialerSheet.OnEventListener {

    RecyclerView recyclerView;
    ContactAdaptor adapter;
    RecyclerView.LayoutManager layoutManager;

    ImageView imgAdd;
    EditText etPersonName;

    private boolean editMode=false;
    private int editingItemIndex=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();

        recyclerView.setHasFixedSize(false);

        adapter=new ContactAdaptor(this , ApplicationClass.contacts);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this , RecyclerView.VERTICAL , false));


        /* *when we want to use a vector asset we have to edit gradle file
         => vectorDrawables.useSupportLibrary=true
         chon hameye version haye android az vector support nemikonan
         *ye chize dige ham bayad be img vector ezafe konim
         => app:srcCompat="@drawable/ic_pus_add_24"
         */

        // //////////////////////////////////////////////////
        personAdder();

    }

    @Override
    public void onItemClicked(int index) {

//        Toast.makeText(this, ApplicationClass.contacts.get(index).getFullName(),
//                Toast.LENGTH_SHORT).show();

        imgAdd.setImageResource(R.drawable.ic_baseline_done_24);
        etPersonName.setText(ApplicationClass.contacts.get(index).getFullName());

        editingItemIndex=index;
        editMode=true;


        //bottom sheet
        DialerSheet dialerSheet=DialerSheet.newInstance(index);
        dialerSheet.show(getSupportFragmentManager() , null);

    }

    @Override
    public void onItemLongClicked(int index) {
        adapter.removeContact(index);
    }

    public void findViews()
    {
        recyclerView=findViewById(R.id.rvList);

        imgAdd=findViewById(R.id.imgAdd);
        etPersonName=findViewById(R.id.etPersonName);
    }
    public void personAdder()
    {
        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etPersonName.getText().toString().trim().equals(""))
                {
                    Toast.makeText(MainActivity.this, "Please enter person's name.",
                            Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(editMode)
                    {
                        adapter.editContact(editingItemIndex , etPersonName.getText().toString().trim()
                                , "0");
                        recyclerView.scrollToPosition(editingItemIndex);
                        editingItemIndex=-1;
                        imgAdd.setImageResource(R.drawable.ic_pus_add_24);
                        editMode=false;
                    }
                    else
                    {
                        adapter.addContact(etPersonName.getText().toString().trim() , "0");
                        recyclerView.scrollToPosition(0);
                    }

                    etPersonName.setText("");

                }
            }
        });
    }

    @Override
    public void onCallClicked(int index) {
        Intent callIntent=new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:"+ApplicationClass.contacts.get(index).getPhoneNumber()));
        startActivity(callIntent);
    }
}