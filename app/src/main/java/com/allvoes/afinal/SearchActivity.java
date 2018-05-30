package com.allvoes.afinal;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SearchActivity extends AppCompatActivity {

    Button btnSearch;
    TextView names,dobs,emails;
    EditText idsearch;
    Employee emp = new Employee();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getWidget();
    }

    public void getWidget(){
        btnSearch = (Button)findViewById(R.id.Search_btnsearch);
        names = (TextView)findViewById(R.id.Search_editname);
        dobs = (TextView)findViewById(R.id.Search_editdob);
        emails = (TextView)findViewById(R.id.Search_editemail);
        idsearch = (EditText)findViewById(R.id.Search_txtid);
    }
    public void setNull(){
        names.setText("");
        dobs.setText("");
        emails.setText("");
    }
    public void Search_btnsearch_Click(View view){
        DB_Handler db = new DB_Handler(this,null,null,1);
        try{
            int id = Integer.parseInt(idsearch.getText().toString());
            //tìm kiếm
            emp = db.findEmployee(id);
            if(emp != null){
                names.setText(emp.getEmpName());
                dobs.setText(emp.getEmpBirth());
                emails.setText(emp.getEmail());
            }else {
                //thông báo nếu không tìm thấy
                AlertDialog.Builder b = new AlertDialog.Builder(this);
                b.setTitle("Thông báo");
                b.setMessage("không tìm thấy mã sách [ "+ id +" ]");
                b.setPositiveButton("Đóng",new AlertDialog.OnClickListener(){
                            public void onClick(DialogInterface dialog, int which){
                                dialog.cancel();
                            }
                        }
                );
                b.create().show();
                setNull();
            }

            /*

             */
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(SearchActivity.this, "Vui lòng nhập ID cần tìm",Toast.LENGTH_SHORT).show();
            setNull();
        }



    }
}
