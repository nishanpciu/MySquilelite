package com.nishan.mysquilelite;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etxtId,etxtName,etxtCell;
    Button btnSave,btnView,btnDelete,btnUpdate;
    squliteDB myBD;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etxtCell=findViewById(R.id.etxtCell);
        etxtId=findViewById(R.id.etxtId);
        etxtName=findViewById(R.id.etxtName);

        btnDelete=findViewById(R.id.btnDelete);
        btnSave=findViewById(R.id.btnSave);
        btnUpdate=findViewById(R.id.btnUpdate);
        btnView=findViewById(R.id.btnView);
        myBD=new squliteDB(MainActivity.this);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id=etxtId.getText().toString().trim();
                String name=etxtName.getText().toString().trim();
                String cell=etxtCell.getText().toString().trim();
                if(id.isEmpty()){
                    etxtId.setError("Please enter ur id");
                    etxtId.requestFocus();
                }
               else if(name.isEmpty()){
                    etxtName.setError("Please enter ur Name");
                    etxtName.requestFocus();
                }
                else if(cell.length()!=11){
                    etxtCell.setError("Please enter ur Correct Cell");
                    etxtCell.requestFocus();
                }
                else{
                    boolean check=myBD.insetData(id,name,cell);
                    if (check=true){
                        Toast.makeText(MainActivity.this,"Dta insert Succesfully",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(MainActivity.this,"Dta not insert Succesfully",Toast.LENGTH_SHORT).show();

                    }
                }

            }
        });
    }
    //for show informatiom
    public void ViewData( View V){
        Cursor result=myBD.ViewData();
        if (result.getCount()==1){
            Toast.makeText(this,"Data Not Found",Toast.LENGTH_SHORT).show();
        }
        else {
            StringBuffer buffer=new StringBuffer();
            result.moveToFirst();//for pointing first row
            do{
                buffer.append("ID:"+result.getString(0));
                buffer.append("Name:"+result.getString(1));
                buffer.append("Cell:"+result.getString(2));

            }
            while (result.moveToNext());
            displayData("Information",buffer.toString());
        }

    }

    private void displayData(String information, String s) {
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setTitle(information);
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setCancelable(true);
        dialog.setMessage(s);
        dialog.show();

    }

}
