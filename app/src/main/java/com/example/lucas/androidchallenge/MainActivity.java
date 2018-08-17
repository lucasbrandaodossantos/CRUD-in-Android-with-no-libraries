package com.example.lucas.androidchallenge;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    DBOpenHelper db = new DBOpenHelper(this, null, null, 1);
    User u;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> arrayList;
    ListView lv;
    EditText edtName, edtPhone, edtEmail, edtAge;
    Button btnInsert, btnDelete,btnUpdate, btnShow;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        btnShow = (Button) findViewById(R.id.btnShow);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnInsert = (Button) findViewById(R.id.btnInsert);
        edtAge = (EditText) findViewById(R.id.edtAge);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPhone = (EditText) findViewById(R.id.edtPhone);
        edtName = (EditText) findViewById(R.id.edtName);
        lv = (ListView) findViewById(R.id.lv);
        //listUsers();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String data = (String) lv.getItemAtPosition(i);
                String name = data.substring(0, data.indexOf(""));
                final User u = db.showUser(name);

                edtName.setText(u.getName());
                edtPhone.setText(u.getPhone());
                edtEmail.setText(u.getEmail());
                edtAge.setText(u.getDateofbirth());

            }
        });
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString();
                int phone = Integer.parseInt(edtPhone.getText().toString());
                String email = edtEmail.getText().toString();
                String date_of_birth = edtAge.getText().toString();

                if (name.isEmpty()){
                    db.insertUser(new User(name,phone,email,date_of_birth));
                    cleanall();
                    listUsers();
                } else {
                    db.updateUser(new User(name,phone,email,date_of_birth));
                    cleanall();
                    listUsers();
                }

            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBOpenHelper db = new DBOpenHelper(MainActivity.this, null, null, 1);
                db.updateUser(u);
            }
        });

    }
/*****************************************************************************/
void cleanall (){
    edtName.setText("");
    edtAge.setText("");
    edtPhone.setText("");
    edtEmail.setText("");
    edtName.requestFocus();
}
public void listUsers() {

    List<User> users = db.listAllUsers();

    arrayList = new ArrayList<String>();

    arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, arrayList);

    lv.setAdapter(arrayAdapter);

    for (User u: users){

        arrayList.add(u.getName()+""+ u.getPhone()+""+u.getEmail());
        arrayAdapter.notifyDataSetChanged();
    }
}
}
