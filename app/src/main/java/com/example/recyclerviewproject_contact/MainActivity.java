package com.example.recyclerviewproject_contact;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class MainActivity extends AppCompatActivity  implements ContactAdaptor.ItemClicked {

    RecyclerView recyclerView;
    ContactAdaptor adapter;
    RecyclerView.LayoutManager layoutManager;

    ImageView imgAdd;
    EditText etPersonName;

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

        Toast.makeText(this, ApplicationClass.contacts.get(index).getFullName(), Toast.LENGTH_SHORT).show();

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
                    adapter.addContact(etPersonName.getText().toString().trim() , "0");
                    etPersonName.setText("");
                    recyclerView.scrollToPosition(0);
                }
            }
        });
    }

}