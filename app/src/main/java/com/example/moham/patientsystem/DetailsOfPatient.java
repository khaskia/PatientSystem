package com.example.moham.patientsystem;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DetailsOfPatient extends AppCompatActivity {
    TextView name;
    TextView Name;
    TextView Phone;
    TextView Email;
    TextView Date;
    TextView Time;
    TextView Disease;
    TextView Medication;
    TextView Cost;

    Button Update;
    Button Delete;
    Button Cancel;
//DataBase
DatabaseHelper mydb  ;

// ***********************   get The Data from the dataBase  *************************//
   public  void getdata(){
        Intent i = this.getIntent();
        name= (TextView) findViewById(R.id.View_Name);
        String Username = i.getExtras().getString("username");
        name.setText(Username);

        Cursor res;
        // res = mydb.getAllData();
        res = mydb.getbyUsername(Username);
        if (res.getCount() == 0) {
            return;
        } else {
            StringBuffer buffer = new StringBuffer();
            while (res.moveToNext()) {
                 Name.setText(res.getString(0));
                 Phone.setText(res.getString(1));
                 Email.setText(res.getString(2));
                 Date.setText(res.getString(3));
                 Time.setText(res.getString(4));
                 Disease.setText(res.getString(5));
                 Medication.setText(res.getString(6));
                 Cost.setText(res.getString(7));

               // list.add(res.getString(0));
                // buffer.append("Name :" + res.getString(0) + "\n");
                // buffer.append("Phone :" + res.getString(1) + "\n");
                //  buffer.append("Email :" + res.getString(2) + "\n");
                //  buffer.append("Arrival_date :" + res.getString(3) + "\n");
                //  buffer.append("Arrival_Time :" + res.getString(4) + "\n");
                //   buffer.append("Disease :" + res.getString(5) + "\n");
                //   buffer.append("Medication :" + res.getString(6) + "\n");
                //  buffer.append("Cost :" + res.getString(7) + "\n\n");
            }
            //print(list.toString());
           // setAdapter();
        }
    }

// ***********************   Send Data To Update Activity  *************************//
    public void Send_data(View v){
        Intent Edit = new Intent(this,EditPatient.class);
        Edit.putExtra("name",Name.getText().toString());
        Edit.putExtra("Phone",Phone.getText().toString());
        Edit.putExtra("Email",Email.getText().toString());
        Edit.putExtra("Date",Date.getText().toString());
        Edit.putExtra("Time",Time.getText().toString());
        Edit.putExtra("Disease",Disease.getText().toString());
        Edit.putExtra("Medication", Medication.getText().toString());
        Edit.putExtra("Cost",Cost.getText().toString());

        startActivity(Edit);

    }
// ***********************   Delete Record  *************************//
    public void  DeleteRecord(View v){
    final Intent intent = new Intent(this,PatientList.class);
        AlertDialog.Builder builder = new AlertDialog.Builder(DetailsOfPatient.this);
        builder.setMessage("Are You Sure !!?");
        // If Yes
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int result = mydb.delete(name.getText().toString());
                if (result > 0) {
                    print("Date Deleted");
                    startActivity(intent);
                } else {
                    print("not Deleted");
                }
            }
        });
        // If No
        builder.setNegativeButton("noo", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alerting = builder.create();
        alerting.show();


    }
// ***********************   Cancel Button  *************************//
public  void  CancelDetailButton(View v){
    //startActivity(new Intent(this,PatientList.class));
    this.finish();
}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_of_patient);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF000000")));

        // Set Logo
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setLogo(R.drawable.logo);
        ab.setDisplayUseLogoEnabled(true);
        ab.setDisplayShowHomeEnabled(true);

        // Casting

        Name = (TextView) findViewById(R.id.View_Name);
        Phone = (TextView) findViewById(R.id.View_Tel);
        Email = (TextView) findViewById(R.id.View_Email);
        Date = (TextView) findViewById(R.id.View_date);
        Time = (TextView) findViewById(R.id.View_Time);
        Disease = (TextView) findViewById(R.id.View_Disease);
        Medication = (TextView) findViewById(R.id.View_Medication);
        Cost = (TextView) findViewById(R.id.View_Cost);

        Update = (Button) findViewById(R.id.btn_Edit);
        Delete = (Button) findViewById(R.id.btn_Delete);
        Cancel = (Button) findViewById(R.id.btn_Cancell);


        mydb = new DatabaseHelper(this);

        getdata();

    }
    //****************************************** Toast ******************************************//

    void print(String s){
        Toast.makeText(DetailsOfPatient.this, s, Toast.LENGTH_SHORT).show();
    }
}
