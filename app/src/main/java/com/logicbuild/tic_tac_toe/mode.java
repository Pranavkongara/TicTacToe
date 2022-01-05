package com.logicbuild.tic_tac_toe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class mode extends AppCompatActivity {
int m=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode);
    }

    public void local(View view) {
        m=-1;
        Intent i=new Intent(this,game.class);
        i.putExtra("mode",m);
        startActivity(i);
    }

    public void easy(View view) {
        m=1;
        Intent i=new Intent(this,game.class);
        i.putExtra("mode",m);
        startActivity(i);
    }

    public void medium(View view) {
        m=2;
        Intent i=new Intent(this,game.class);
        i.putExtra("mode",m);
        startActivity(i);
    }

    public void imp(View view) {
        m=3;
        Intent i=new Intent(this,game.class);
        i.putExtra("mode",m);
        startActivity(i);
    }

    public void hard(View view) {
        m=4;
        Intent i=new Intent(this,game.class);
        i.putExtra("mode",m);
        startActivity(i);
    }

    public void Network(View view) {
        Intent i=new Intent(this,Roompopup.class);
        startActivity(i);
    }
    @Override
    public void onBackPressed(){
        startActivity(new Intent(this,MainActivity.class));
    }
}
