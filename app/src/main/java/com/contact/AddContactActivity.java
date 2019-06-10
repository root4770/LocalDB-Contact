package com.contact;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.contact.DataBaseHelper.DatabaseHelper;

public class AddContactActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText inputcontactId, inputstagingId, inputcontext, inputphoneContactId, inputstatus, inputuserID;
    Button btn_adddata;
    String contactId, stagingId, context, phoneContactId, status, userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        myDb = new DatabaseHelper(this);
        findViewById();
    }

    private void findViewById() {

        inputcontactId = (EditText) findViewById(R.id.inputcontactId);
        inputstagingId = (EditText) findViewById(R.id.inputstagingId);
        inputcontext = (EditText) findViewById(R.id.inputcontext);
        inputphoneContactId = (EditText) findViewById(R.id.inputphoneContactId);
        inputstatus = (EditText) findViewById(R.id.inputstatus);
        inputuserID = (EditText) findViewById(R.id.inputuserID);

        btn_adddata = (Button) findViewById(R.id.btn_adddata);
        btn_adddata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                contactId = inputcontactId.getText().toString();
                stagingId = inputstagingId.getText().toString();
                context = inputcontext.getText().toString();
                phoneContactId = inputphoneContactId.getText().toString();
                status = inputstatus.getText().toString();
                userID = inputuserID.getText().toString();

                if (contactId.equals("")) {
                    inputcontactId.setError("Required !");
                } else if (stagingId.equalsIgnoreCase("")) {
                    inputstagingId.setError("Required !");
                } else if (context.equalsIgnoreCase("")) {
                    inputcontext.setError("Required !");
                } else if (phoneContactId.equalsIgnoreCase("")) {
                    inputphoneContactId.setError("Required !");
                } else if (status.equalsIgnoreCase("")) {
                    inputstatus.setError("Required !");
                } else if (userID.equalsIgnoreCase("")) {
                    inputuserID.setError("Required !");
                } else {

                    //ID, contactId, stagingId,context,phoneContactId,status,userID
                    boolean isInserted = myDb.insertData(contactId, stagingId, context, phoneContactId, status, userID);
                    inputcontactId.setText("");
                    inputstagingId.setText("");
                    inputcontext.setText("");
                    inputphoneContactId.setText("");
                    inputstatus.setText("");
                    inputuserID.setText("");
                }

            }
        });


    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(AddContactActivity.this, ContactActivity.class));
    }
}
