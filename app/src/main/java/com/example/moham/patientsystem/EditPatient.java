package com.example.moham.patientsystem;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class EditPatient extends AppCompatActivity {

    // View Variables
    DatabaseHelper myDb;

    EditText Name;
    EditText Phone;
    EditText Email;
    EditText Date;
    EditText Time;
    EditText Disease;
    EditText Medication;
    EditText Cost;
    Button Edit;
    Button Cancel;

    //******************************** Get And Set Data ******************************************/

    public void GetAndSetData(){
        Intent i = this.getIntent();
    Name.setText(i.getExtras().getString("name"));
    Phone.setText(i.getExtras().getString("Phone"));
    Email.setText(i.getExtras().getString("Email"));
    Date.setText(i.getExtras().getString("Date"));
    Time.setText(i.getExtras().getString("Time"));
    Disease.setText(i.getExtras().getString("Disease"));
    Medication.setText(i.getExtras().getString("Medication"));
    Cost.setText(i.getExtras().getString("Cost"));
}

//******************************** Update Data ******************************************/
    public  void UpdateData(View v){
        Intent i = this.getIntent();

            if (!Name.getText().toString().equals( i.getExtras().getString("name")) ) {
                Name.setError("UserName Not Permitted To be updated ");
            }
            else  if (Name.getText().toString().length() < 1) {
                Name.setError("Name is Required");
            } else if (Phone.getText().toString().length() != 11) {
                Phone.setError("Phone is Required and must be 11 digits");
            } else if (Email.getText().toString().length() < 1) {
                Email.setError("Email is Required");
            } else if (!Email.getText().toString().matches("^\\S+@\\S+$")) {
                Email.setError("Please Enter A vaild Email");
            } else if (Date.getText().toString().length() < 1) {
                Date.setError("Date is Required");
            } else if (Time.getText().toString().length() < 1) {
                Time.setError("Time is Required");
            } else if (Disease.getText().toString().length() < 1) {
                Disease.setError("Disease is Required");
            } else if (Medication.getText().toString().length() < 1) {
                Medication.setError("Medication is Required");
            } else if (Cost.getText().toString().length() < 1) {
                Cost.setError("Cost is Required");
            } else if (Cost.getText().toString().length() >= 5) {
                Cost.setError("Sorry That is Too long for Coast");
            } else {
                boolean result = myDb.update(
                        Name.getText().toString(),
                        Phone.getText().toString(),
                        Email.getText().toString(),
                        Date.getText().toString(),
                        Time.getText().toString(),
                        Disease.getText().toString(),
                        Medication.getText().toString(),
                        Cost.getText().toString()
                );
                if (result = true) {
                    print("DataUpdated");
                    startActivity(new Intent(this, PatientList.class));
                } else {
                    print("not Updated");
                }


            }

    }



    // ***********************   Cancel Button  *************************//
    public  void  CancelEditButton(View v){
       // startActivity(new Intent(this,DetailsOfPatient.class));
        this.finish();
    }
//******************************** Add Time_Dailog ******************************************/
    static final int Time_dialog_ID=0;
    int hours_x;
    int minute_x;

    public void showTimePickerDialog(){
        Time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(Time_dialog_ID);
            }
        });
    }


    protected TimePickerDialog.OnTimeSetListener mTimepickerlistner = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            hours_x=hourOfDay;
            minute_x=minute;
            Time.setText(hours_x + ":" + minute_x);
        }
    };

    //******************************** Add Date_Dailog ******************************************/
    int xyear;
    int xmonth;
    int xday;
    static final int Date_Dialog_ID = 1;
    public void DatePickerDialog(){
        final Calendar cal = Calendar.getInstance();
        xyear=cal.get(Calendar.YEAR);
        xmonth = cal.get(Calendar.MONTH);
        xday = cal.get(Calendar.DAY_OF_MONTH);
        Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(Date_Dialog_ID);
                // print("clicced");
            }
        });

    }

    protected DatePickerDialog.OnDateSetListener DateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            xyear=year;
            xmonth=monthOfYear+1;
            xday=dayOfMonth;
            Date.setText(xyear + "-" + xmonth + "-" + xday);
        }
    };


//******************************** On Create Dialog  ******************************************/


    @Override
    protected Dialog onCreateDialog(int id) {
        if(id == Time_dialog_ID)
            return new TimePickerDialog(this,mTimepickerlistner,hours_x,minute_x,false);
        if (id == Date_Dialog_ID)
            return new DatePickerDialog(this,DateListener,xyear,xmonth,xday);

        return null;
    }

//******************************** On_Create******************************************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_patient);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF000000")));
        // Set Logo
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setLogo(R.drawable.logo);
        ab.setDisplayUseLogoEnabled(true);
        ab.setDisplayShowHomeEnabled(true);

        //DataBase
        myDb = new DatabaseHelper(this);
        // Casting
        Time = (EditText) findViewById(R.id.UP_Time);
        Date = (EditText) findViewById(R.id.UP_Date);
        Name = (EditText) findViewById(R.id.UP_Name);
        Phone = (EditText) findViewById(R.id.UP_tel);
        Email = (EditText) findViewById(R.id.UP_Email);
        Disease = (EditText) findViewById(R.id.UP_Disease);
        Medication = (EditText) findViewById(R.id.UP_Medication);
        Cost = (EditText) findViewById(R.id.UP_Cost);



        // Calling Methods
        showTimePickerDialog();
        DatePickerDialog();
        GetAndSetData();



    }
//******************************** Toast ******************************************/

    void print(String s){
        Toast.makeText(EditPatient.this, s, Toast.LENGTH_SHORT).show();
    }
//******************************** xxxxxxxx ******************************************/

}
