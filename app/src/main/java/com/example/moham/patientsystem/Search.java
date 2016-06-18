package com.example.moham.patientsystem;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class Search extends AppCompatActivity {

    // View Variables

    EditText Date_from;
    EditText Date_To;
    EditText search_name;
    EditText search_disease;
    EditText search_medication;


//******************************** Go To The Result ******************************************/

public  void  result(View v){
    Intent i = new Intent(this,SearchResult.class);
    i.putExtra("searchName", search_name.getText().toString());
    i.putExtra("from",Date_from.getText().toString());
    i.putExtra("to",Date_To.getText().toString());
    i.putExtra("di",search_disease.getText().toString());
    i.putExtra("med",search_medication.getText().toString());
    startActivity(i);
}

// ***********************   Cancel Button  *************************//
    public  void  CancelSearchButton(View v){
       // startActivity(new Intent(this,PatientList.class));
        this.finish();
    }

//******************************** Go To The Clear ******************************************/

public  void  clear(View v){
    Date_from.setText("");
    Date_To.setText("");
    search_name.setText("");
    search_disease.setText("");
    search_medication.setText("");
}



    //******************************** (( From ))Add Date_From_Dailog ******************************************/
    int F_xyear;
    int F_xmonth;
    int F_xday;
    static final int From_Date_Dialog_ID = 0;
    public void From_DatePickerDialog(){
        final Calendar cal = Calendar.getInstance();
        F_xyear=cal.get(Calendar.YEAR);
        F_xmonth = cal.get(Calendar.MONTH);
        F_xday = cal.get(Calendar.DAY_OF_MONTH);
        Date_from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(From_Date_Dialog_ID);
                // print("clicced");
            }
        });

    }

    protected DatePickerDialog.OnDateSetListener From_DateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            F_xyear=year;
            F_xmonth=monthOfYear+1;
            F_xday=dayOfMonth;
            Date_from.setText(F_xyear + "-" + F_xmonth + "-" + F_xday);
        }
    };


//******************************** ((To)) Add Date_From_Dailog ******************************************/
    int T_xyear;
    int T_xmonth;
    int T_xday;
    static final int To_Date_Dialog_ID = 1;
    public void To_DatePickerDialog(){
        final Calendar cal = Calendar.getInstance();
        T_xyear=cal.get(Calendar.YEAR);
        T_xmonth = cal.get(Calendar.MONTH);
        T_xday = cal.get(Calendar.DAY_OF_MONTH);
        Date_To.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(To_Date_Dialog_ID);
                // print("clicced");
            }
        });

    }

    protected DatePickerDialog.OnDateSetListener To_DateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            T_xyear=year;
            T_xmonth=monthOfYear+1;
            T_xday=dayOfMonth;
            Date_To.setText(T_xyear + "-" + T_xmonth + "-" + T_xday);
        }
    };


//******************************** On Create Dialog  ******************************************/


    @Override
    protected Dialog onCreateDialog(int id) {
     //   if(id == Time_dialog_ID)
       //     return new TimePickerDialog(this,mTimepickerlistner,hours_x,minute_x,false);
        if (id == From_Date_Dialog_ID)
            return new DatePickerDialog(this,From_DateListener,F_xyear,F_xmonth,F_xday);
        if (id == To_Date_Dialog_ID)
            return new DatePickerDialog(this,To_DateListener,T_xyear,T_xmonth,T_xday);

        return null;
    }

//******************************** On_Create******************************************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF000000")));

        // Set Logo
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setLogo(R.drawable.logo);
        ab.setDisplayUseLogoEnabled(true);
        ab.setDisplayShowHomeEnabled(true);

        // Casting
        Date_from = (EditText) findViewById(R.id.Search_Arrival_from);
        Date_To=(EditText) findViewById(R.id.Search_Arrival_to);
        search_name = (EditText) findViewById(R.id.search_Name);
        search_disease = (EditText) findViewById(R.id.Search_Dis);
        search_medication = (EditText) findViewById(R.id.Search_med);

        // Calling Methods
        From_DatePickerDialog();
        To_DatePickerDialog();
    }
//******************************** Toast ******************************************/

    void print(String s){
        Toast.makeText(Search.this, s, Toast.LENGTH_SHORT).show();
    }
//******************************** xxxxxxxx ******************************************/

}
