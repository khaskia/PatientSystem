package com.example.moham.patientsystem;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PatientList extends AppCompatActivity {
    // Set UP Lists
    List<String> list = new ArrayList<String>();
    ListView mylist;


////////////////////////////// ***** Data Base *****//////////////////////////////
    DatabaseHelper mydb  ;
    Button show;
    public void viewall(){
        mylist = (ListView) findViewById(R.id.mylist);
        //show = (Button) findViewById(R.id.Show) ;
        Cursor res;
       res = mydb.getAllData();
       // res = mydb.getbyUsername("ibraheem");
        if (res.getCount() == 0) {
            return;
        } else {
            //StringBuffer buffer = new StringBuffer();
            while (res.moveToNext()) {
                list.add(res.getString(0));
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
            setAdapter();
        }
    }

    public  void  setAdapter(){
        ListAdapter theadapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        mylist.setAdapter(theadapter);
    }

    public void listListners(){
        final Intent intent=new Intent(this, DetailsOfPatient.class);

        mylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {


                    String message =String.valueOf(adapterView.getItemAtPosition(position));
                   // print(message);
                    intent.putExtra("username",message);
                     startActivity(intent);



            }
        });
    }


















    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF000000")));

        // Set Logo
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setLogo(R.drawable.logo);
        ab.setDisplayUseLogoEnabled(true);
        ab.setDisplayShowHomeEnabled(true);

        mydb = new DatabaseHelper(this);
        viewall();
        listListners();
    }






    //****************************************** Toast ******************************************//

    void print(String s){
        Toast.makeText(PatientList.this,s,Toast.LENGTH_SHORT).show();
    }

    //****************************************** On_Create   ************************************//
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.addandsearch,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.AddID:
                startActivity(new Intent(this,AddPatent.class));
                break;
            case  R.id.SearchID :
                startActivity(new Intent(this,Search.class));

                ;break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);


    }

}
