package com.example.geovanny.myfirstapp.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TabHost;
import android.widget.Toast;

import com.example.geovanny.myfirstapp.R;
import com.example.geovanny.myfirstapp.complements.ListAdapterEvents;
import com.example.geovanny.myfirstapp.database.EventDB;
import com.example.geovanny.myfirstapp.model.Event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends Activity {
    private TabHost mainActivity_tabHost;

    private EventDB eventDB;

    private ListAdapterEvents listAdapterEvents;
    private ListAdapterEvents listAdapterEventsPlace;
    private ListAdapterEvents listAdapterEventsDate;

    private SearchView mainActivityName_search;
    private SearchView mainActivityPlace_search;

    private ListView mainActivityName_listView;
    private ListView mainActivityPlace_listView;
    private ListView mainActivityDates_listView;

    private Button mainActivityDates_btnFind;

    private EditText mainActivityDates_edtStart;
    private EditText mainActivityDates_edtEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configuramos el TabHost
        this.mainActivity_tabHost = findViewById(R.id.mainActivity_tabHost);
        //TAB 1
        this.mainActivity_tabHost.setup();
        //El findForName es solo para identificarlo
        TabHost.TabSpec tabSpec1 = mainActivity_tabHost.newTabSpec("findForName");
        tabSpec1.setIndicator("Nombre");
        tabSpec1.setContent(R.id.activityMain_name);
        this.mainActivity_tabHost.addTab(tabSpec1);

        //TAB 2
        TabHost.TabSpec tabSpec2 = mainActivity_tabHost.newTabSpec("findForPlace");
        tabSpec2.setIndicator("Lugar");
        tabSpec2.setContent(R.id.activityMain_place);
        this.mainActivity_tabHost.addTab(tabSpec2);

        //TAB 1
        TabHost.TabSpec tabSpec3 = mainActivity_tabHost.newTabSpec("findForDateRange");
        tabSpec3.setIndicator("Rango Fechas");
        tabSpec3.setContent(R.id.mainActivity_datesRange);
        this.mainActivity_tabHost.addTab(tabSpec3);

        //Creamos la instancia para consultar los eventos
        eventDB = new EventDB(getBaseContext());

        //Asiganamos los valores de las listas y buscadores en la variables locales
        mainActivityName_listView = findViewById(R.id.activityMainName_listView);
        mainActivityPlace_listView = findViewById(R.id.activityMainPlace_listView);
        mainActivityDates_listView = findViewById(R.id.activityMainDates_listView);

        mainActivityName_search = findViewById(R.id.activityMainName_search);
        mainActivityPlace_search = findViewById(R.id.activityMainPlace_search);




        // Cargar lista de nombres y activar el search por nombre
        //listAdapterEvents = new ListAdapterEvents(this, eventos,0);
        listAdapterEvents = new ListAdapterEvents(this, eventDB.readAll(),0);
        mainActivityName_listView.setAdapter(listAdapterEvents);
        mainActivityName_search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                listAdapterEvents.getFilter().filter(s);
                return false;
            }
        });

        mainActivityName_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Se pasa a una nueva ventana para observar todos los detalles del evento
                Toast.makeText(getApplicationContext(), "Click "+i, Toast.LENGTH_SHORT).show();
                Event event = (Event) listAdapterEvents.getItem(i);


                Intent intentMainActivity1 = new Intent(MainActivity.this, EventActivity.class);
                intentMainActivity1.putExtra("Event", event);
                startActivity(intentMainActivity1);
            }
        });

        // Cargar lista de nombres con lugar y activar el search por lugar
        listAdapterEventsPlace = new ListAdapterEvents(this, eventDB.readAll(),1);
        //listAdapterEvents = new ListAdapterEvents(this, eventDB.readAll(),0);
        mainActivityPlace_listView.setAdapter(listAdapterEventsPlace);
        mainActivityPlace_search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                listAdapterEventsPlace.getFilter().filter(s);
                return false;
            }
        });

        mainActivityPlace_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Se pasa a una nueva ventana para observar todos los detalles del evento
                Toast.makeText(getApplicationContext(), "Click "+i, Toast.LENGTH_SHORT).show();
                Event event = (Event) listAdapterEventsPlace.getItem(i);


                Intent intentMainActivity1 = new Intent(MainActivity.this, EventActivity.class);
                intentMainActivity1.putExtra("Event", event);
                startActivity(intentMainActivity1);
            }
        });

        // Cargar lista de nombres con lugar y activar el search por lugar
        listAdapterEventsDate = new ListAdapterEvents(this, eventDB.readAll(),2);
        //listAdapterEvents = new ListAdapterEvents(this, eventDB.readAll(),0);
        mainActivityDates_listView.setAdapter(listAdapterEventsDate);

        mainActivityDates_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Se pasa a una nueva ventana para observar todos los detalles del evento
                Toast.makeText(getApplicationContext(), "Click "+i, Toast.LENGTH_SHORT).show();
                Event event = (Event) listAdapterEventsDate.getItem(i);


                Intent intentMainActivity1 = new Intent(MainActivity.this, EventActivity.class);
                intentMainActivity1.putExtra("Event", event);
                startActivity(intentMainActivity1);
            }
        });

        mainActivityDates_edtStart = findViewById(R.id.activityMainDates_edtStart);
        mainActivityDates_edtEnd = findViewById(R.id.activityMainDates_edtEnd);

        mainActivityDates_btnFind = findViewById(R.id.activityMainDates_btnFind);
        mainActivityDates_btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Event> events = new ArrayList<>();
                ArrayList<Event> eventsDB = eventDB.readAll();
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                for (int i=0; i<eventsDB.size(); i++) {
                    try {
                        if ((formatter.parse(eventsDB.get(i).getDayhour().toString())).compareTo(formatter.parse(mainActivityDates_edtStart.getText().toString()))>=0 && formatter.parse(eventsDB.get(i).getDayhour().toString()).compareTo(formatter.parse(mainActivityDates_edtEnd.getText().toString())) <= 0) {
                            events.add(eventsDB.get(i));
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                listAdapterEventsDate.setDataEvents(events);
                mainActivityDates_listView.setAdapter(listAdapterEventsDate);
            }
        });
    }

}
