package com.allvoes.afinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class ControllerActivity extends AppCompatActivity {

    Button btDanhsach,btThem,btSearch,btWrite,btRead;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controller);
        getWidget();
    }

    public void getWidget(){
        btDanhsach = (Button)findViewById(R.id.Controller_btnDanhsach);
        btThem = (Button)findViewById(R.id.Controller_btnThem);
        btSearch = (Button)findViewById(R.id.Controller_btnSearch);
        btWrite = (Button)findViewById(R.id.Controller_btnWrite);
        btRead = (Button)findViewById(R.id.Controller_btnRead);
    }
    public void Controller_btnDanhsach_Click(View view){
        Intent intent = new Intent(ControllerActivity.this, ListViewActivity.class);
        startActivity(intent);
    }
    public void Controller_btnThem_Click(View view){
        Intent intent = new Intent(ControllerActivity.this, ThemActivity.class);
        startActivity(intent);
    }
    public void Controller_btnSearch_Click(View view){
        Intent intent = new Intent(ControllerActivity.this, SearchActivity.class);
        startActivity(intent);
    }
    public void Controller_btnWrite_Click(View view) {
        writeToFile("myfile.txt");
    }

    public void writeToFile(String filename){
        DB_Handler db = new DB_Handler(this,null,null,1);
        ArrayList<Employee> list = db.listEmployee();

        for (Employee emp: list) {
            String informations = "";
            informations += "ID: " + emp.getEmpID()+"";
            informations += "Name: " + emp.getEmpName().toString();
            informations += "DoB: " + emp.getEmpBirth().toString();
            informations += "Email: " + emp.getEmail().toString() + "\n";
            try{
                FileOutputStream out = openFileOutput(filename,MODE_APPEND); //MODE_PRIVATE 1 mode mở ra để ghi 1 dối tượng hoặc MODE_APPEND ghi chồng
                OutputStreamWriter writed = new OutputStreamWriter(out);
                writed.write(informations);
                writed.close();
                Toast.makeText(ControllerActivity.this,"Write thanh cong",Toast.LENGTH_SHORT).show();


            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void Controller_btnRead_Click(View view){
        Intent intent = new Intent(ControllerActivity.this,ReadTextActivity.class);
        startActivity(intent);
    }
}
