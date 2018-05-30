package com.allvoes.afinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class ThemActivity extends AppCompatActivity {

    EditText Empname,Empdob,Empemail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them);
        getWidget();
    }
    public void getWidget(){
        Empname = (EditText)findViewById(R.id.txtName);
        Empdob = (EditText)findViewById(R.id.txtDoB);
        Empemail = (EditText)findViewById(R.id.txtEmail);
    }
    public void setNull(){
        Empname.setText("");
        Empdob.setText("");
        Empemail.setText("");
    }
    public void Them_btnThem_Click(View view){
        DB_Handler db = new DB_Handler(this , null,null,1);
        String name = Empname.getText().toString();
        String dob = Empdob.getText().toString();
        String email = Empemail.getText().toString();
        Employee emp = new Employee(name,dob,email);
        db.addEmployee(emp);
        Toast.makeText(ThemActivity.this, "Đã thêm thành công",Toast.LENGTH_LONG).show();
        setNull();

    }
}
