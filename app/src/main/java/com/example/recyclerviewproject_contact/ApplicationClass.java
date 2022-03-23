package com.example.recyclerviewproject_contact;

import android.app.Application;

import java.util.ArrayList;

public class ApplicationClass extends Application {

    public static ArrayList<Contact> contacts;

    @Override
    public void onCreate() {
        super.onCreate();

        contacts=new ArrayList<>();

        contacts.add(new Contact("Sarina Hemmat pour" , "09140465914"));
        contacts.add(new Contact("zohre ali pour" , "09370776007"));
        contacts.add(new Contact("Niloofar Hemmat pour" , "09140468287"));
        contacts.add(new Contact("Arshia atabak" , "09140465915"));
        contacts.add(new Contact("Sarina Hemmat pour" , "09140465914"));
        contacts.add(new Contact("zohre ali pour" , "09370776007"));
        contacts.add(new Contact("Niloofar Hemmat pour" , "09140468287"));
        contacts.add(new Contact("Arshia atabak" , "09140465915"));
        contacts.add(new Contact("Sarina Hemmat pour" , "09140465914"));
        contacts.add(new Contact("zohre ali pour" , "09370776007"));
        contacts.add(new Contact("Niloofar Hemmat pour" , "09140468287"));
        contacts.add(new Contact("Arshia atabak" , "09140465915"));
        contacts.add(new Contact("Sarina Hemmat pour" , "09140465914"));
        contacts.add(new Contact("zohre ali pour" , "09370776007"));
        contacts.add(new Contact("Niloofar Hemmat pour" , "09140468287"));
        contacts.add(new Contact("Arshia atabak" , "09140465915"));
        contacts.add(new Contact("Sarina Hemmat pour" , "09140465914"));
        contacts.add(new Contact("zohre ali pour" , "09370776007"));
        contacts.add(new Contact("Niloofar Hemmat pour" , "09140468287"));
        contacts.add(new Contact("Arshia atabak" , "09140465915"));

    }
}
