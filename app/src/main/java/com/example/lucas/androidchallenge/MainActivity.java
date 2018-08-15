package com.example.lucas.androidchallenge;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.lucas.androidchallenge.CRUD.Create;
import com.example.lucas.androidchallenge.CRUD.Delete;
import com.example.lucas.androidchallenge.CRUD.Read;
import com.example.lucas.androidchallenge.CRUD.Update;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button btndelete = (Button) findViewById(R.id.btn_delete);
        Button btnupdate = (Button) findViewById(R.id.btn_update);
        Button btnread = (Button) findViewById(R.id.btn_read);
        Button btncreate = (Button) findViewById(R.id.btn_create);

        //OPEN CONSULT SCREEN
        btncreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Create.class);
                startActivity(i);
            }
        });


        //OPEN READ SCREEN
        btnread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Read.class);
                startActivity(i);
            }
        });


        //OPEN UPDATE SCREEN
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Update.class);
                startActivity(i);
            }
        });


        //OPEN DELETE SCREEN
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Delete.class);
                startActivity(i);
            }
        });
    }
}
