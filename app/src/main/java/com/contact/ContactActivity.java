package com.contact;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.contact.Adapters.ContactSpinner_Adapter;
import com.contact.DataBaseHelper.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

import gr.escsoft.michaelprimez.searchablespinner.SearchableSpinner;
import gr.escsoft.michaelprimez.searchablespinner.interfaces.IStatusListener;
import gr.escsoft.michaelprimez.searchablespinner.interfaces.OnItemSelectedListener;

public class ContactActivity extends AppCompatActivity {

    Context context;
    DatabaseHelper myDb;
    public static ArrayList<String> contactIdList = new ArrayList<>();
    public static ArrayList<String> dataList = new ArrayList<>();
    private SearchableSpinner spinner_ContactID;
    String SelectedContactID;
    TextView txt_stagingId,txt_context,txt_status,txt_userId;
   LinearLayout Lv_cardview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = this;
        myDb = new DatabaseHelper(this);
        findViewById();
        dbcalling();

    }


    private void findViewById() {
        FloatingActionButton fab = findViewById(R.id.fab);
        txt_stagingId = (TextView)findViewById(R.id.txt_stagingId);
        txt_context = (TextView)findViewById(R.id.txt_context);
        txt_status = (TextView)findViewById(R.id.txt_status);
        txt_userId = (TextView)findViewById(R.id.txt_userId);
        Lv_cardview = (LinearLayout)findViewById(R.id.Lv_cardview);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ContactActivity.this, AddContactActivity.class));
            }
        });

        spinner_ContactID = (SearchableSpinner) findViewById(R.id.spinner_ContactID);
        spinner_ContactID.setOnItemSelectedListener(mOnItemSelected_contactID);
        spinner_ContactID.setStatusListener(new IStatusListener() {
            @Override
            public void spinnerIsOpening() {

            }

            @Override
            public void spinnerIsClosing() {

            }
        });

    }


    private void dbcalling() {

        Cursor res = myDb.getAllData();
        if (res.getCount() == 0) {
            Toast.makeText(ContactActivity.this, "Nothing found", Toast.LENGTH_LONG).show();
            return;
        }
        StringBuffer buffer = new StringBuffer();
        contactIdList.clear();
        while (res.moveToNext()) {
            contactIdList.add(res.getString(1));
            //Toast.makeText(getApplicationContext(),""+contactIdList.toString(),Toast.LENGTH_LONG).show();
        }

        ContactSpinner_Adapter contactIDlist_Adapter = new ContactSpinner_Adapter(ContactActivity.this, contactIdList, contactIdList);
        spinner_ContactID.setAdapter(contactIDlist_Adapter);
    }

    private OnItemSelectedListener mOnItemSelected_contactID = new OnItemSelectedListener() {
        @Override
        public void onItemSelected(View view, int position, long id) {

            if (position == 0) {
                Toast.makeText(ContactActivity.this, " Nothing Selected", Toast.LENGTH_SHORT).show();
            } else {
                SelectedContactID = contactIdList.get(position - 1);
                dataList.clear();
                Cursor res = myDb.getsearchdata(SelectedContactID);
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {

                    //contactId, stagingId,context,phoneContactId,status,userID
                    //stagingId, context, status, userId
                    dataList.add(res.getString(2));//stagingId, ,

                    dataList.add(res.getString(3));//context,
                    dataList.add(res.getString(5));//status
                    dataList.add(res.getString(6));//userID

                    String data = dataList.toString();
                    data = data.substring(1, data.length() - 1);
                    String[] datalist = data.split(",");
                    String stagingId = datalist[0];
                    String context = datalist[1];
                    String status = datalist[2];
                    String userID = datalist[3];

                    Lv_cardview.setVisibility(View.VISIBLE);
                    txt_stagingId.setText(stagingId);
                    txt_context.setText(context);
                    txt_status.setText(status);
                    txt_userId.setText(userID);
                }


            }
        }

        @Override
        public void onNothingSelected() {
            Toast.makeText(ContactActivity.this, " Nothing Selected", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public void onBackPressed() {
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory(Intent.CATEGORY_HOME);
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
        finish();

    }
}
