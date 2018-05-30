package com.allvoes.afinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText names,dobs,emails;
    TextView ids;
    Button btupdate,btcencel;
    String empids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        empids = getIntent().getStringExtra("EmpID");
        GetWidget();
        setEmployee();
    }

    public void setEmployee(){
        DB_Handler db = new DB_Handler(this,null,null,1);
        int id = Integer.parseInt(empids);
        Employee employee = db.findEmployee(id);

        String empid = employee.getEmpID()+"";
        String empname = employee.getEmpName();
        String empdob = employee.getEmpBirth();
        String empemail = employee.getEmail();

        ids.setText(empid);
        names.setText(empname);
        dobs.setText(empdob);
        emails.setText(empemail);
    }
    public void GetWidget(){
        names = (EditText)findViewById(R.id.Update_txtName);
        dobs = (EditText)findViewById(R.id.Update_txtDoB);
        emails = (EditText)findViewById(R.id.Update_txtEmail);
        ids = (TextView)findViewById(R.id.Update_txtID);
        btupdate = (Button)findViewById(R.id.Update_btnUpdate);
        btcencel = (Button)findViewById(R.id.Update_btnCancel);
    }
    public void Update_btnCancel_Click(View view){
        names.setText("");
        dobs.setText("");
        emails.setText("");
        names.requestFocus();
    }
    public void Update_btnUpdate_Click(View view){
        DB_Handler db = new DB_Handler(this,null,null,1);
        //empids
        String empids = getIntent().getStringExtra("EmpID");
        int id = Integer.parseInt(empids);
        Employee emp = db.findEmployee(id);
        emp.setEmpName(names.getText().toString());
        emp.setEmpBirth(dobs.getText().toString());
        emp.setEmail(emails.getText().toString());
        try {
            db.updateEmployee(emp);
            Toast.makeText(UpdateActivity.this, "Cập nhật thành công",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(UpdateActivity.this, ListViewActivity.class);
            startActivity(intent);

        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(UpdateActivity.this, "Cập nhật không thành công",Toast.LENGTH_SHORT).show();
        }

    }
}
