package com.example.mosadi.chefschool.navigation_fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.mosadi.chefschool.R;

/**
 * Created by Mosadi on 2017/09/02.
 */

public class navigation_meeting_request_fragment extends Fragment
        implements AdapterView.OnItemSelectedListener
{
    String reason="";
    int when=-1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.navigation_meetin_request, container, false);
                Spinner dropdown = (Spinner)v.findViewById(R.id.spinner);
        //create a list of items for the spinner.
        String[] items = new String[]{"ASAP","This week", "Nex week", "Whatever works for you",};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        dropdown.setAdapter(adapter);
        // Inflate the layout for this fragment
        return v;
    }
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        when=pos;
        String choice = parent.getItemAtPosition(pos).toString();
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

}
