package com.logicbuild.tic_tac_toe;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Play(View view) {
        Intent i=new Intent(this,mode.class);
        startActivity(i);
    }

    public void stats(View view) {
        Toast.makeText(this,"Functionality not yet given",Toast.LENGTH_LONG).show();

    }

    public void Exit(View view) {
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }
    @Override
    public void onBackPressed(){
        System.exit(1);
    }

    public void privacy_policy(View view) {
        Uri uri = Uri.parse("https://docs.google.com/document/d/1eWRsbZpIvC5N-GIpbq4O8JvGseIrMQpImemsc8TmAqs/edit?usp=sharing");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
