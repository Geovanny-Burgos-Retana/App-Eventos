package com.example.geovanny.myfirstapp.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.geovanny.myfirstapp.R;
import com.example.geovanny.myfirstapp.database.EventDB;
import com.example.geovanny.myfirstapp.model.Event;

import java.nio.file.Files;

public class EventActivity extends AppCompatActivity {
    private Button eventActivity_btnBack;
    private Button eventActivity_btnDelete;
    private EditText eventActivity_edtName;
    private EditText eventActivity_edtPhoneContact;
    private EditText eventActivity_edtDayhour;
    private EditText eventActivity_edtPrice;
    private EditText eventActivity_edtPlace;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        //Extramos el evento que pasamos por parametro de la ventana principal a esta
        final Event event = (Event) getIntent().getSerializableExtra("Event");

        eventActivity_edtDayhour = findViewById(R.id.activityEvent_edtdayhour);
        eventActivity_edtName = findViewById(R.id.activityEvent_edtName);
        eventActivity_edtPhoneContact = findViewById(R.id.activityEvent_edtPhoneContact);
        eventActivity_edtPlace = findViewById(R.id.activityEvent_edtPlace);
        eventActivity_edtPrice = findViewById(R.id.activityEvent_edtPrice);

        eventActivity_edtName.setText(event.getName());
        eventActivity_edtPhoneContact.setText(event.getContactPhone());
        eventActivity_edtDayhour.setText(String.valueOf(event.getDayhour()));
        eventActivity_edtPrice.setText(String.valueOf(event.getPrice()));
        eventActivity_edtPlace.setText(event.getPlace());

        eventActivity_btnBack = findViewById(R.id.activityEvent_btnBack);
        eventActivity_btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentEventActivity1 = new Intent(EventActivity.this, MainActivity.class);
                startActivity(intentEventActivity1);
            }
        });

        eventActivity_btnDelete = findViewById(R.id.activityEvent_btnDelete);
        eventActivity_btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Llamanos al metodo de la base de datos que elimina el evento que se muestra en la interfaz
                EventDB eventDB = new EventDB(getBaseContext());
                eventDB.delete(event.getId());
                Intent intentEventActivity1 = new Intent(EventActivity.this, MainActivity.class);
                startActivity(intentEventActivity1);
            }
        });
    }
}
