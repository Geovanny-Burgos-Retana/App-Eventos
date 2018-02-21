package com.example.geovanny.myfirstapp.complements;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.geovanny.myfirstapp.R;
import com.example.geovanny.myfirstapp.model.Event;

import java.util.ArrayList;

/**
 * Created by geovanny on 20/02/18.
 */

public class ListAdapterEvents extends BaseAdapter implements Filterable {

    private Context context;
    //private ArrayList<DatosPlaces> DatosPlaces;
    private ArrayList<Event> dataEvents;
    private LayoutInflater inflater;
    private CustomFilter filter;
    private ArrayList<Event> filterList;
    private int REQUEST_CODE;

    public ListAdapterEvents(Context context, ArrayList<Event> dataEvents, int REQUEST_CODE) {
        this.context = context;
        this.dataEvents = dataEvents;
        this.filterList = dataEvents;
        this.REQUEST_CODE = REQUEST_CODE;
    }

    @Override
    public int getCount() {
        return dataEvents.size();
    }

    @Override
    public Object getItem(int position) {
        return dataEvents.get(position);
    }

    @Override
    public long getItemId(int position) {
        return dataEvents.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        // Declare Variables
        TextView txtTitle;
        ImageView imgImg;

        //http://developer.android.com/intl/es/reference/android/view/LayoutInflater.html
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(view == null){
            view = inflater.inflate(R.layout.list_format, parent, false);
        }


        // Locate the TextViews in listview_item.xml
        txtTitle = (TextView) view.findViewById(R.id.listFormat_itemTitle);
        imgImg = (ImageView) view.findViewById(R.id.listFormat_itemIcon);

        // Capture position and set to the TextViews
        txtTitle.setText(dataEvents.get(position).getName());
        imgImg.setImageResource(dataEvents.get(position).getImagen());

        return view;
    }


    @Override
    public Filter getFilter() {
        if(filter == null){
            filter = new CustomFilter();
        }
        return filter;
    }

    class CustomFilter extends Filter{

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            FilterResults resulst = new FilterResults();
            if(constraint != null && constraint.length()>0){
                //pasamos a mayusculas
                constraint = constraint.toString().toUpperCase();

                ArrayList<Event> filter = new ArrayList<Event>();

                for(Integer i=0;i<filterList.size();i++){
                    if(filterList.get(i).getName().toUpperCase().contains(constraint)){
                        filter.add(filterList.get(i));
                    }
                }
                resulst.count= filter.size();
                resulst.values = filter;
            }else{
                resulst.count= filterList.size();
                resulst.values = filterList;
            }

            return resulst;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            dataEvents = (ArrayList<Event>) results.values;
            notifyDataSetChanged();

        }
    }
}