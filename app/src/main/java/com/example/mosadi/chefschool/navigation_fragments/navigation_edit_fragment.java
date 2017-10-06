package com.example.mosadi.chefschool.navigation_fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mosadi.chefschool.MainActivity;
import com.example.mosadi.chefschool.R;
import com.example.mosadi.chefschool.userinformation.Profile;

/**
 * Created by Mosadi on 2017/09/02.
 */

public class navigation_edit_fragment extends Fragment {
    Button back;
    Button save;
    FragmentTransaction ft;
    ActionBar bar;
    Profile user;

    String name, surname, phone, email;
    EditText name_edit, surname_edit, phone_edit, email_edit, edit_id, edit_class;
    TextView text;
    Boolean editid=true,editclass=true;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.navigation_edit_profile, container, false);
        user = ((MainActivity) this.getActivity()).getUser();
        text = (TextView) v.findViewById(R.id.error_message);
        edit_class= (EditText) v.findViewById(R.id.edit_class);
        edit_id = (EditText) v.findViewById(R.id.edit_id);
        bar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        save = (Button) v.findViewById(R.id.savebutton);
        back = (Button) v.findViewById(R.id.cancel);
        // Inflate the layout for this fragment
        initialiseValues(v);
        if(edit_id.getText().toString().length()>1){
            edit_id.setFocusable(false);
            edit_id.setLongClickable(false);
            editid= false;
        }
        if(edit_class.getText().toString().length()>0){
            edit_class.setFocusable(false);
            edit_class.setLongClickable(false);
            editclass=false;
        }

        back.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        cancelChanges();
                        makeToast("cancelled changes");
                    }
                });
        save.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        View focusView = null;
                        edit_class.setError(null);
                        edit_id.setError(null);
                            if (TextUtils.isEmpty(edit_id.getText().toString())) {
                                edit_id.setError("Enter your id number");
                                focusView = edit_id;
                                focusView.requestFocus();
                                return;
                            }
                            if(TextUtils.isEmpty(edit_class.getText().toString())){
                                edit_class.setError("Enter your class number");
                                focusView = edit_class;
                                focusView.requestFocus();
                                return;
                            }


                            onSave();//you have to create some form of a user model to store data

                        //makeToast("Changes saved");
                    }
                });

        return v;
    }

    public void makeToast(String text) {
        Context context = getActivity().getApplicationContext();
        Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        toast.show();
        toast.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
    }

    public void initialiseValues(View v) {
        //initialise
        name_edit = (EditText) v.findViewById(R.id.edit_name);
        surname_edit = (EditText) v.findViewById(R.id.edit_surname);
        phone_edit = (EditText) v.findViewById(R.id.edit_phone);
        email_edit = (EditText) v.findViewById(R.id.edit_email);
        // dob_edit=(EditText) v.findViewById(R.id.edit_dob);
        //reset the edit values to the user's values
        name_edit.setText(user.getName());
        surname_edit.setText(user.getSurname());
        phone_edit.setText(user.getPhone());
        email_edit.setText(user.getEmail());
        edit_class.setText(user.getClass_number());
        edit_id.setText(user.getDob());
        name = user.getName();
        surname = user.getSurname();
        phone = user.getPhone();
        email = user.getEmail();
        // dob=user.getDob();

    }

    public void cancelChanges() {
        name_edit.setText(name);
        surname_edit.setText(surname);
        phone_edit.setText(phone);
        email_edit.setText(email);
        // dob_edit.setText(dob);
    }
    public boolean isDigit(String value){
        for(int i=0;i<value.length();i++){
            if(!Character.isDigit(value.charAt(i))){
                return false; //ther eis a missing number
            }
        }
        return true;
    }

    public void onSave() {
        //when Write to the db

        ((MainActivity) this.getActivity()).updateProfile(name_edit.getText().toString(),
                surname_edit.getText().toString(),
                user.getImage(),
                email_edit.getText().toString(),
                phone_edit.getText().toString(),edit_id.getText().toString(),edit_class.getText().toString());
        //  dob_edit.getText().toString());
    }
}
