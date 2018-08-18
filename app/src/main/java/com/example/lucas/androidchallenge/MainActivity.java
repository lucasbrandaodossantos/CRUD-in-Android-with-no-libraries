package com.example.lucas.androidchallenge;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
    DBOpenHelper db = new DBOpenHelper(this, null, null, 1);
    User u;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> arrayList;
    ListView lv;
    EditText edtName, edtPhone, edtEmail, edtAge, edtID;
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
        //listUsers();


        /*Teste do CRUD*/
        //insert ok
        /*
        db.insertUser(new User("Lucas",951752490,"lucasondionisia@hotmail.com"));
        db.insertUser(new User("Maria",959348933,"maria@hotmail.com"));
        db.insertUser(new User("Carlos",953435430,"carlos@hotmail.com"));
        db.insertUser(new User("Jose",951798778,"jose@hotmail.com"));
        db.insertUser(new User("Carlos",953435430,"carlos@hotmail.com"));
        db.insertUser(new User("Jose",951798778,"jose@hotmail.com"));
        db.insertUser(new User("Carlos",953435430,"carlos@hotmail.com"));
        db.insertUser(new User("Jose",951798778,"jose@hotmail.com"));

        Toast.makeText(MainActivity.this, "Cadastrado com sucesso",Toast.LENGTH_SHORT).show();*/
        //delete ok
//       User u = new User();
//        u.setId(0);
//        db.deleteUser(u);
//        Toast.makeText(MainActivity.this, "Apagado com sucesso",Toast.LENGTH_SHORT).show();
//
//
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String data = (String) lv.getItemAtPosition(i);
                String id = data.substring(0, data.indexOf("-"));
                User u = db.showUser(Integer.parseInt(id));

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


                if (name.isEmpty()){
                    edtName.setText("Please fill this field!");
                }else if (id.isEmpty()){
                    //insert
                    db.insertUser(new User(name,Integer.parseInt(phone),email));
                    Toast.makeText(MainActivity.this, "Registered user successfully",Toast.LENGTH_SHORT).show();
                    cleanall();
                    listUsers();
                }else {
                    //update
                    db.updateUser(new User (Integer.parseInt(id),name,Integer.parseInt(phone),email));
                    Toast.makeText(MainActivity.this, "Updated user successfully",Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(MainActivity.this, "No user selected",Toast.LENGTH_SHORT).show();
                } else{
                    User u = new User();
                    u.setId(Integer.parseInt(id));
                    db.deleteUser(u);
                    Toast.makeText(MainActivity.this, "User deleted successfully",Toast.LENGTH_SHORT).show();
                    cleanall();
                    listUsers();
                }
            }
        });
    }
/*****************************************************************************/
void cleanall (){
    edtName.setText("");
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
        Log.d("Lista", "\nID:" +u.getId() + "Name: "+u.getName());
//        arrayList.add(u.getId()+ "-" + u.getName());
//        arrayAdapter.notifyDataSetChanged();
    }
}
}
