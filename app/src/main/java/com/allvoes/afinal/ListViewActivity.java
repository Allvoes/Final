package com.allvoes.afinal;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ListViewActivity extends AppCompatActivity {

    ListView lvDanhsach;
    Button btnIndanhsach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        lvDanhsach = (ListView)findViewById(R.id.ListDanhSach);
        btnIndanhsach = (Button)findViewById(R.id.ListView_btnInDanhSach);
        loadData();
    }

    public void loadData(){
        final DB_Handler db = new DB_Handler(this,null,null,1);
        ArrayList<Employee> list = db.listEmployee();
        final ArrayAdapter<Employee> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list);
        lvDanhsach.setAdapter(adapter);

        lvDanhsach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, final int position, long id) {
                AlertDialog.Builder adb=new AlertDialog.Builder(ListViewActivity.this);
                adb.setTitle("Delete?");
                adb.setMessage("Are you sure you want to delete " + position);
                final int positionToRemove = position;
                adb.setNegativeButton("Cancel", null);
                adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Employee value = (Employee) adapter.getItem(position);
                        db.deleteEmployee(value.getEmpID());
                        loadData();
                    }});
                adb.show();
            }
        });
        lvDanhsach.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder adb=new AlertDialog.Builder(ListViewActivity.this);
                adb.setTitle("Update");
                adb.setMessage("Are you sure you want to update " + position);

                final int positionToRemove = position;
                adb.setNegativeButton("Cancel", null);
                adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        Employee value = (Employee) adapter.getItem(positionToRemove);
                        //truyền id qua cho UpdateActivite
                        Bundle localBundle = new Bundle();
                        localBundle.putString("EmpID", value.getEmpID()+"");

                        Intent localIntent = new Intent(ListViewActivity.this, UpdateActivity.class);
                        localIntent.putExtras(localBundle);
                        startActivity(localIntent);

                    }});
                adb.show();
                return true;
            }
        });

    }


}
