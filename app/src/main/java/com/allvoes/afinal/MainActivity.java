package com.allvoes.afinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username,password;
    Button btlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GetWidget();
    }
    public void GetWidget(){
        username = (EditText)findViewById(R.id.txtUsername);
        password = (EditText)findViewById(R.id.txtPassword);
        btlogin = (Button)findViewById(R.id.btnLogin);
    }
    public void login_Click(View view){
        DB_Handler db = new DB_Handler(this,null,null,1);
        if( db.Login(username.getText().toString(), password.getText().toString())){
            Intent intent = new Intent(MainActivity.this, ControllerActivity.class);
            startActivity(intent);
        }else {
            Toast.makeText(MainActivity.this, "Login Fail",Toast.LENGTH_LONG).show();
            username.requestFocus();
        }



    }
}
