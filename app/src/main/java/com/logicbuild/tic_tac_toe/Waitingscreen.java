package com.logicbuild.tic_tac_toe;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Waitingscreen extends AppCompatActivity {
DatabaseReference a,c,d;
int x=0;boolean create=false,datachange=true;
TextView count;
String room;
ProgressBar load;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waitingscreen);
        Bundle getmode=getIntent().getExtras();
        room=getmode.getString("Room");
        count=(TextView)findViewById(R.id.textView6);
        load=findViewById(R.id.progressBar);
        rec();
    }
    private void set2(){
        a= FirebaseDatabase.getInstance().getReferenceFromUrl("https://tic-tac-toe-2e6c2.firebaseio.com/"+room);
        c=a.child("user");
        c.setValue("2");
        //Log.d("WS", "Waitingscreen :users to 2");
    }
    private void CreateRoom(){
        a= FirebaseDatabase.getInstance().getReferenceFromUrl("https://tic-tac-toe-2e6c2.firebaseio.com/"+room);
        create=true;
        c=a.child("user");
        c.setValue("1");
        //Log.d("WS", "Waitingscreen : room created ");
    }
    public void next(){
        count.setText("Game Started");
        //Log.d("WS", "next: "+create);
        int m=-2;
        Intent i=new Intent(this,game.class);
        i.putExtra("mode",m);
        i.putExtra("Room",room);
        if(create)
            i.putExtra("x",1);
        if(!create)
            i.putExtra("x",0);
        datachange=false;
        startActivity(i);
    }
    public void extrau(){
        Intent qwe=new Intent(this,mode.class);
        Toast.makeText(this,"Room filled , try another room number",Toast.LENGTH_LONG).show();
        startActivity(qwe);
    }
    public void rec(){
        d=FirebaseDatabase.getInstance().getReferenceFromUrl("https://tic-tac-toe-2e6c2.firebaseio.com/"+room+"/user");
        d.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {//Log.d("WS", "onDataChange:before data pull");
            if(datachange) {
                String n = dataSnapshot.getValue(String.class);
                //Log.d("WS", "onDataChange:after data pull " + n);
                if (n == null) {
                    CreateRoom();
                    //Log.d("WS", "onDataChange: Value is null");
                } else {
                    x = Integer.parseInt(n);
                    if (create && x == 1) ;//Log.d("WS", "create is true and x is 1");
                    else if (x == 1) {
                        //Log.d("WS", "onDataChange: error");
                        set2();
                        //Log.d("WS", "set users to 2");
                    } else if (x == 2)
                    {   d.removeEventListener(this);
                        next();
                    }
                    else if(!create&&x == 3){
                        extrau();
                    }
                    else {//Log.d("WS", "onDataChange: ERROR")
                         }
                }
            }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    @Override
    public void onBackPressed(){}

    public void back(View view) {
        finish();
        Intent i =new Intent(this,mode.class);
        datachange=false;
        d.removeValue();
        startActivity(i);
    }
}
