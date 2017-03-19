package com.example.sameerwagh.sql;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText usernameField,passwordField;
    private TextView status,role,method,t1,tt1,tt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameField = (EditText)findViewById(R.id.username);
        passwordField = (EditText)findViewById(R.id.passwd);

       // status = (TextView)findViewById(R.id.textView6);
       // role = (TextView)findViewById(R.id.textView7);
       // method = (TextView)findViewById(R.id.textView9);
        t1 = (TextView)findViewById(R.id.user_TF);






    }

    @Override
    public void onBackPressed()
    {
       // android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void login(View view){
        String username = usernameField.getText().toString();
        String password = passwordField.getText().toString();
        //role.setText(username+password);
        //tt1.setText(username);
        //tt2.setText(password);
            String nn="";
        if((username.matches(nn))) {
          //  method.setText("Get Method");
            //new Main2Activity(this,status,role,t1,0).execute(username,password);
            new Main2Activity(this, role, t1, 0, username, password).execute(username, password);
            //role.setText("null");


        }
        else
        {

            new Main2Activity(this, role, t1, 0, username, password).execute(username, password);
            //role.setText("not null");
            //finish();
        }

    }

    public void loginPost(View view){
        String username = usernameField.getText().toString();
        String password = passwordField.getText().toString();
       // method.setText("Post Method");
        //new Main2Activity(this,status,role,t1,tt1,tt2,1).execute(username,password);
    }
}
