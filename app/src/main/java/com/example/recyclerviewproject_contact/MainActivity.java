package com.example.recyclerviewproject_contact;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class MainActivity extends AppCompatActivity  implements ContactAdaptor.ItemClicked {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.rvList);
        recyclerView.setHasFixedSize(false);

        adapter=new ContactAdaptor(this , ApplicationClass.contacts);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this , RecyclerView.VERTICAL , false));




    }

    @Override
    public void onItemClicked(int index) {

        Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show();

    }
}