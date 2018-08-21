package com.example.lucas.androidchallenge;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    DBOpenHelper db = new DBOpenHelper(this);
    User u;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> arrayList;
    ListView lv;
    EditText edtName, edtPhone, edtEmail, edtID;
    Button btnInsert, btnDelete;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnInsert = (Button) findViewById(R.id.btnInsert);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPhone = (EditText) findViewById(R.id.edtPhone);
        edtName = (EditText) findViewById(R.id.edtName);
        edtID = (EditText) findViewById(R.id.edtID);
        lv = (ListView) findViewById(R.id.lv);
        listUsers();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String content = (String) lv.getItemAtPosition(i);

                String id = content.substring(0, content.indexOf("-"));

                User u = db.selectUser(Integer.parseInt(id));

                edtID.setText(String.valueOf(u.getId()));
                edtName.setText(u.getName());
                edtPhone.setText(u.getPhone());
                edtEmail.setText(u.getEmail());
            }
        });


        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = edtID.getText().toString();
                String name = edtName.getText().toString();
                String phone = edtPhone.getText().toString();
                String email = edtEmail.getText().toString();


                if (name.isEmpty()) {
                    edtName.setHint("Fill this field");
                } else if (phone.isEmpty()) {
                    edtPhone.setHint("Fill this field");
                } else if (email.isEmpty()) {
                    edtEmail.setHint("Fill this field");
                } else if (id.isEmpty()) {
                    //insert
                    db.insertUser(new User(name,phone,email));
                    Toast.makeText(MainActivity.this, "Registered user successfully", Toast.LENGTH_SHORT).show();
                    cleanall();
                    listUsers();
                } else if (!id.isEmpty()) {
                    //update
                    db.updateUser(new User(Integer.parseInt(id), name, phone, email));
                    Toast.makeText(MainActivity.this, "Updated user successfully", Toast.LENGTH_SHORT).show();
                    cleanall();
                    listUsers();
                }
            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = edtID.getText().toString();

                if (id.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please select a user", Toast.LENGTH_SHORT).show();
                } else {
                    User user = new User();
                    user.setId(Integer.parseInt(id));
                    db.deleteUser(user);
                    Toast.makeText(MainActivity.this, "User deleted", Toast.LENGTH_SHORT).show();
                    cleanall();
                    listUsers();
                }

            }
        });
    }


    /*****************************************************************************/


    void cleanall() {
        edtName.setText("");
        edtPhone.setText("");
        edtEmail.setText("");

        edtName.requestFocus();
    }


    public void listUsers() {

        List<User> users = db.listAllUsers();

        arrayList = new ArrayList<String>();

        arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_multiple_choice, arrayList);

        lv.setAdapter(arrayAdapter);

        for (User u : users) {
            arrayList.add(u.getId()+ "-" +u.getName());
            arrayAdapter.notifyDataSetChanged();
        }
    }
}



