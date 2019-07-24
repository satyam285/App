package com.example.hcl.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {

    Button b1;
    EditText user, pass;
    private static final String url = "jdbc:mysql://localhost:3306/test";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = (Button) findViewById(R.id.button);
        user = (EditText) findViewById(R.id.username);
        pass = (EditText) findViewById(R.id.password);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1 = user.getText().toString();
                String s2 = pass.getText().toString();
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection(url, "root", "");
                    Statement st = con.createStatement();
                    String query = "SELECT * FROM user WHERE username = '" + s1 + "' AND password = '" + s2 + "'";

                    final ResultSet rs = st.executeQuery(query);
                    if (rs.getString(2).length() > 0) {
                        Toast.makeText(MainActivity.this, rs.getString(0), Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(MainActivity.this, Main2Activity.class);
                        startActivity(i);
                        finish();
                    } else {
                        Toast.makeText(MainActivity.this, "Can't Login Buddy", Toast.LENGTH_SHORT).show();
                    }
                    con.close();
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Can't connect to Database" + e, Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }

        });

    }
}