package com.logicbuild.tic_tac_toe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Roompopup extends AppCompatActivity {
    String Room;
    EditText getroom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roompopup);

        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.85),(int)(height*0.50));
        getroom=(EditText)findViewById(R.id.editText);
    }

    public void Create(View view) {
        Room=getroom.getText().toString();
        if(Room.equalsIgnoreCase("")){
            Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_LONG).show();
        }else {
        Intent i=new Intent(this,Waitingscreen.class);
        i.putExtra("Room",Room);
        startActivity(i);}

    }
}
