    package com.logicbuild.tic_tac_toe;

    import android.content.Intent;
    import android.graphics.Color;
    import android.support.annotation.NonNull;
    import android.support.v7.app.AppCompatActivity;
    import android.os.Bundle;
    import android.view.View;
    import android.view.WindowManager;
    import android.widget.Button;
    import android.widget.TextView;
    import android.widget.Toast;

    import com.google.firebase.database.DataSnapshot;
    import com.google.firebase.database.DatabaseError;
    import com.google.firebase.database.DatabaseReference;
    import com.google.firebase.database.FirebaseDatabase;
    import com.google.firebase.database.ValueEventListener;


    public class game extends AppCompatActivity {
        int x1=0,x2=0,x3=0,x4=0,x5=0,x6=0,x7=0,x8=0,x9=0,x,m,r,com=0,i,stepone=0,X;boolean databas=true,finish=false;
        Button b1,b2,b3,b4,b5,b6,b7,b8,b9,play;
        TextView win,turn;
        DatabaseReference ref,rev,room;
        String roomno="",currentstate,source="https://tic-tac-toe-2e6c2.firebaseio.com/",send="0000000000",co="";
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_game);
            initial();
            Bundle getmode=getIntent().getExtras();
            m=getmode.getInt("mode");
            if(m==-2){
                roomno=getmode.getString("Room");
                X=getmode.getInt("x");
                source=source+roomno+"/"+roomno;
                ref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://tic-tac-toe-2e6c2.firebaseio.com/"+roomno);
                room=ref.child(roomno);
                if(X==0)
                {
                    room.setValue("0000000000");
                    ref.child("user").setValue("3");

                }
                rev=FirebaseDatabase.getInstance().getReferenceFromUrl("https://tic-tac-toe-2e6c2.firebaseio.com/"+roomno+"/"+roomno);
                rev.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(databas) {
                          currentstate = dataSnapshot.getValue(String.class);
                          divide();

                      }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
            x=0;
        }
        public void initial(){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            b1=(Button)findViewById(R.id.button1);
            b2=(Button)findViewById(R.id.button2);
            b3=(Button)findViewById(R.id.button3);
            b4=(Button)findViewById(R.id.button4);
            b5=(Button)findViewById(R.id.button5);
            b6=(Button)findViewById(R.id.button6);
            b7=(Button)findViewById(R.id.button7);
            b8=(Button)findViewById(R.id.button8);
            b9=(Button)findViewById(R.id.button9);
            play=(Button)findViewById(R.id.playagain);
            win=(TextView)findViewById((R.id.win));
            turn=(TextView)findViewById(R.id.textView3);
            play.setVisibility(View.INVISIBLE);
            win.setVisibility(View.VISIBLE);

        }
        @Override
        public void onBackPressed(){
            if(m!=-2)
                startActivity(new Intent(this,mode.class));
        }
        public void check(){
            int z=0;
            //Log.d("BUGS", "onCreate:STOP ONDATACHANGE ");
                if ((x1 == 2 && x2 == 2 && x3 == 2) || (x4 == 2 && x5 == 2 && x6 == 2) || (x7 == 2 && x8 == 2 && x9 == 2) || (x1 == 2 && x4 == 2 && x7 == 2) || (x2 == 2 && x5 == 2 && x8 == 2) | (x3 == 2 && x6 == 2 && x9 == 2) || (x1 == 2 && x5 == 2 && x9 == 2) || (x3 == 2 && x5 == 2 && x7 == 2)) {
                    Intent i = new Intent(this, pop.class);
                    startActivity(i);
                    disable();
                    if(m>0)
                    {
                        win.setText("You Lose");Toast.makeText(this, "You Lose", Toast.LENGTH_LONG).show();
                    }
                    else
                    {win.setText("Player O WON");
                        Toast.makeText(this, "Player O won", Toast.LENGTH_LONG).show();}
                    databas=false;if(X==0&&m==-2)del();
                    win.setTextSize(40);
                    play.setVisibility(View.VISIBLE);
                    win.setVisibility(View.VISIBLE);
                    finish=true;
                    z=1;
                }
                if ((x1 == 1 && x2 == 1 && x3 == 1) || (x4 == 1 && x5 == 1 && x6 == 1) || (x7 == 1 && x8 == 1 && x9 == 1) || (x1 == 1 && x4 == 1 && x7 == 1) || (x2 == 1 && x5 == 1 && x8 == 1) | (x3 == 1 && x6 == 1 && x9 == 1) || (x1 == 1 && x5 == 1 && x9 == 1) || (x3 == 1 && x5 == 1 && x7 == 1)) {
                    Intent i = new Intent(this, pop.class);
                    startActivity(i);
                    disable();
                    if(m>0)
                    {
                        win.setText("You Won");Toast.makeText(this, "You won", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        win.setText("Player X WON");Toast.makeText(this, "Player X won", Toast.LENGTH_LONG).show();
                    }
                    databas=false;if(X==0&&m==-2)del();
                    win.setTextSize(40);
                    finish=true;
                    play.setVisibility(View.VISIBLE);
                    win.setVisibility(View.VISIBLE);
                    z=1;
                }
            if (x % 2 == 1 &&m<0)
            {
                turn.setText("Player O turn");
                turn.setTextSize(40);
            }
            else {
                turn.setText("Player X turn");
                turn.setTextSize(40);
                if(x==9)
                    turn.setText("");
            }
            if(x==9&&z!=1)
            {
                win.setText("It is a draw");databas=false;if(X==0&&m==-2)del();
                win.setTextSize(40);
                play.setVisibility(View.VISIBLE);
                win.setVisibility(View.VISIBLE);
                finish=true;
                Intent i = new Intent(this, pop.class);
                startActivity(i);
            }
        }
        public void b1(View view) {

            if (x1 == 0) {x++;
                if (x % 2 == 0) {
                    x1 = 2;
                    b1.setText("O");

                } else {
                    x1 = 1;
                    b1.setText("X");
                }
                b1.setEnabled(false);i=1;
                if(x1==1)
                b1.setTextColor(Color.parseColor("#669900"));
                else b1.setTextColor(Color.parseColor("#D81B60"));
                b1.setTextSize(60);
                check();
                chance();
                if(m==-2)combine();
            }
            else Toast.makeText(this, "Already marked, Cant be pressed twice", Toast.LENGTH_SHORT).show();

        }
        public void b2(View view) {

            if (x2 == 0) {x++;
                if (x % 2 == 0) {
                    x2 = 2;
                    b2.setText("O");
                } else {
                    x2 = 1;
                    b2.setText("X");
                }
                b2.setEnabled(false);i=2;
                if(x2==1)
                    b2.setTextColor(Color.parseColor("#669900"));
                if(x2==2) b2.setTextColor(Color.parseColor("#D81B60"));
                b2.setTextSize(60);
                check();
                chance();
                if(m==-2)combine();
            }
            else Toast.makeText(this, "Already marked, Cant be pressed twice", Toast.LENGTH_SHORT).show();

        }
        public void b3(View view) {

            if (x3 == 0) {x++;
                if (x % 2 == 0) {
                    x3 = 2;
                    b3.setText("O");
                } else {
                    x3 = 1;
                    b3.setText("X");
                }
                b3.setEnabled(false);i=3;
                if(x3==1)
                    b3.setTextColor(Color.parseColor("#669900"));
                else b3.setTextColor(Color.parseColor("#D81B60"));
                b3.setTextSize(60);
                check();
                chance();
                if(m==-2)combine();
            }
            else Toast.makeText(this, "Already marked, Cant be pressed twice", Toast.LENGTH_SHORT).show();

        }
        public void b4(View view) {

            if (x4 == 0) {x++;
                if (x % 2 == 0) {
                    x4 = 2;
                    b4.setText("O");
                } else {
                    x4 = 1;
                    b4.setText("X");
                }
                b4.setEnabled(false);i=4;
                if(x4==1)
                    b4.setTextColor(Color.parseColor("#669900"));
                else b4.setTextColor(Color.parseColor("#D81B60"));b4.setTextSize(60);
                check();
                chance();
                if(m==-2)combine();
            }
            else Toast.makeText(this, "Already marked, Cant be pressed twice", Toast.LENGTH_SHORT).show();

        }
        public void b5(View view) {

            if (x5 == 0) {x++;
                if (x % 2 == 0) {
                    x5 = 2;
                    b5.setText("O");
                } else {
                    x5 = 1;
                    b5.setText("X");
                }
                b5.setEnabled(false);i=5;
                if(x5==1)
                    b5.setTextColor(Color.parseColor("#669900"));
                else b5.setTextColor(Color.parseColor("#D81B60"));
                b5.setTextSize(60);
                check();
                chance();
                if(m==-2)combine();
            }
            else Toast.makeText(this, "Already marked, Cant be pressed twice", Toast.LENGTH_SHORT).show();

        }
        public void b6(View view) {

            if (x6 == 0) {x++;
                if (x % 2 == 0) {
                    x6 = 2;
                    b6.setText("O");
                } else {
                    x6 = 1;
                    b6.setText("X");
                }
                b6.setEnabled(false);i=6;
                if(x6==1)
                    b6.setTextColor(Color.parseColor("#669900"));
                else b6.setTextColor(Color.parseColor("#D81B60"));
                b6.setTextSize(60);
                check();
                chance();
                if(m==-2)combine();
            }
            else Toast.makeText(this, "Already marked, Cant be pressed twice", Toast.LENGTH_SHORT).show();

        }
        public void b7(View view) {

            if (x7 == 0) {x++;
                if (x % 2 == 0) {
                    x7 = 2;
                    b7.setText("O");
                } else {
                    x7 = 1;
                    b7.setText("X");
                }
                b7.setEnabled(false);i=7;
                if(x7==1)
                    b7.setTextColor(Color.parseColor("#669900"));
                else b7.setTextColor(Color.parseColor("#D81B60"));
                b7.setTextSize(60);
                check();
                chance();
                if(m==-2)combine();
            }
            else Toast.makeText(this, "Already marked, Cant be pressed twice", Toast.LENGTH_SHORT).show();

        }
        public void b8(View view) {

            if (x8 == 0) {x++;
                if (x % 2 == 0) {
                    x8 = 2;
                    b8.setText("O");
                } else {
                    x8 = 1;
                    b8.setText("X");
                }
                b8.setEnabled(false);i=8;
                if(x8==1)
                    b8.setTextColor(Color.parseColor("#669900"));
                else b8.setTextColor(Color.parseColor("#D81B60"));
                b8.setTextSize(60);
                check();
                chance();
                if(m==-2)combine();
            }
            else Toast.makeText(this, "Already marked, Cant be pressed twice", Toast.LENGTH_SHORT).show();

        }
        public void b9(View view) {

            if (x9 == 0) {x++;
                if (x % 2 == 0) {
                    x9 = 2;
                    b9.setText("O");
                } else {
                    x9 = 1;
                    b9.setText("X");
                }
                b9.setEnabled(false);i=9;
                if(x9==1)
                    b9.setTextColor(Color.parseColor("#669900"));
                else b9.setTextColor(Color.parseColor("#D81B60"));
                b9.setTextSize(60);
                check();
                chance();
                if(m==-2)combine();
            }
            else Toast.makeText(this, "Already marked, Cant be pressed twice", Toast.LENGTH_SHORT).show();

        }
        public void playagain(View view) {
            Intent i=new Intent(this,mode.class);
            startActivity(i);
        }
        public void rand() {
            r=(int)(Math.random()*10);
            com=1;
            switch(r)
            {
                case 1:if (x1!=0) rand();
                else b1(null);
                    break;
                case 2:if (x2!=0) rand();
                else b2(null);
                    break;
                case 3:if (x3!=0) rand();
                else b3(null);
                    break;
                case 4:if (x4!=0) rand();
                else b4(null);
                    break;
                case 5:if (x5!=0) rand();
                else b5(null);
                    break;
                case 6:if (x6!=0) rand();
                else b6(null);
                    break;
                case 7:if (x7!=0) rand();
                else b7(null);
                    break;
                case 8:if (x8!=0) rand();
                else b8(null);
                    break;
                case 9:if (x9!=0) rand();
                else b9(null);
                    break;
                default :rand();
                    break;
            }


        }
        public void chance(){
            if(!finish) {
                if (m == 1 && x < 9) {
                    if (com == 0)
                        rand();
                    else
                        com = 0;
                }
                if (m == 2 && x < 9) {
                    if (com == 0)
                        if (x == 1 || x == 3)
                            impossible();
                        else if (x == 5)
                            stepwin();
                        else
                            rand();
                    else
                        com = 0;
                }
                if (m == 3 && x < 9) {
                    if (com == 0)
                        impossible();
                    else
                        com = 0;
                }
                if (m == 4 && x < 9) {
                    if (com == 0) {
                        if (x == 1) hard();
                        else if ((x5 == 0)) {
                            com = 1;
                            if (x1 == 1 && x9 == 1) b5(null);
                            else if (x3 == 1 && x7 == 1) b5(null);
                            else if (x2 == 1 && x8 == 1) b5(null);
                            else if (x4 == 1 && x6 == 1) b5(null);
                            else stepwin();
                        } else
                            stepwin();

                    } else
                        com = 0;
                }
            }
        }
        public void impossible(){ int one;com=1;
            if(x==1)
            {
                if(x5==0)
                {   if(i%2==0)
                    stepone=1;
                    b5(null);
                }
                else
                {  one=(int)(Math.random()*10);
                    r=one%4;
                    switch(r) {
                        case 1: b3(null);
                            break;
                        case 2: b7(null);
                            break;
                        case 3:b9(null);
                            break;
                        default:
                            b1(null);
                            break;
                    }
                }
            }
            else if(x==3)
            {
                if(stepone==0)
                {
                    if (x5 == 1) {
                        com = 1;
                        switch (i) {
                            case 1:
                                if (x9 == 0) b9(null);
                                else {
                                    one = (int) (Math.random() * 10);
                                    if (one % 2 == 0)
                                        b3(null);
                                    else
                                        b7(null);
                                }
                                break;
                            case 2:
                                b8(null);
                                break;
                            case 3:
                                if (x7 == 0) b7(null);
                                else {
                                    one = (int) (Math.random() * 10);
                                    if (one % 2 == 0)
                                        b1(null);
                                    else
                                        b9(null);
                                }
                                break;
                            case 4:
                                b6(null);
                                break;
                            case 6:
                                b4(null);
                                break;
                            case 7:
                                if (x3 == 0) b3(null);
                                else {
                                    one = (int) (Math.random() * 10);
                                    if (one % 2 == 0)
                                        b1(null);
                                    else
                                        b9(null);
                                }
                                break;
                            case 8:
                                b2(null);
                                break;
                            case 9:
                                if (x1 == 0) b1(null);
                                else {
                                    one = (int) (Math.random() * 10);
                                    if (one % 2 == 0)
                                        b3(null);
                                    else
                                        b7(null);
                                }
                                break;
                        }
                    }
                    else {
                        com = 1;
                        if (x1 == 1) {
                            switch (i) {
                                case 1:
                                    if(x3==1)b2(null);
                                    else if (x7==1)b4(null);
                                    else {
                                        do {
                                            one = (int) (Math.random() * 10);
                                        } while (one % 2 == 1);
                                        switch (one) {
                                            case 2:
                                                b2(null);
                                                break;
                                            case 4:
                                                b4(null);
                                                break;
                                            case 6:
                                                b6(null);
                                                break;
                                            case 8:
                                                b8(null);
                                                break;
                                            default:
                                                b4(null);
                                                break;
                                        }
                                    }
                                    break;
                                case 2:
                                    b3(null);
                                    break;
                                case 3:
                                    b2(null);
                                    break;
                                case 4:
                                    b7(null);
                                    break;
                                case 6:
                                    one=(int)(Math.random()*10);
                                    r=one%4;
                                    switch(r) {
                                        case 1: b2(null);
                                            break;
                                        case 2: b3(null);
                                            break;
                                        case 3:b8(null);
                                            break;
                                        default:
                                            b9(null);
                                            break;
                                    }
                                    break;
                                case 7:
                                    b4(null);
                                    break;
                                case 8:
                                    one=(int)(Math.random()*10);
                                    r=one%4;
                                    switch(r) {
                                        case 1: b4(null);
                                            break;
                                        case 2: b7(null);
                                            break;
                                        case 3:b9(null);
                                            break;
                                        default:
                                            b6(null);
                                            break;
                                    }
                                    break;
                                case 9:
                                    do {
                                        one = (int) (Math.random() * 10);
                                    } while (one % 2 == 1);
                                    switch (one) {
                                        case 2:
                                            b2(null);
                                            break;
                                        case 4:
                                            b4(null);
                                            break;
                                        case 6:
                                            b6(null);
                                            break;
                                        case 8:
                                            b8(null);
                                            break;
                                        default:
                                            b2(null);
                                            break;
                                    }
                                    break;
                            }
                        }
                        else if (x3 == 1) {
                            switch (i) {
                                case 1:
                                    b2(null);
                                    break;
                                case 2:
                                    b1(null);
                                    break;
                                case 3:
                                    if(x9==1)b6(null);
                                    else{
                                    do {
                                        one = (int) (Math.random() * 10);
                                    } while (one % 2 == 1);
                                    switch (one) {
                                        case 2:
                                            b2(null);
                                            break;
                                        case 4:
                                            b4(null);
                                            break;
                                        case 6:
                                            b6(null);
                                            break;
                                        case 8:
                                            b8(null);
                                            break;
                                        default:
                                            b4(null);
                                            break;
                                    }
                                    }
                                    break;
                                case 4:
                                    one=(int)(Math.random()*10);
                                    r=one%4;
                                    switch(r) {
                                        case 1: b1(null);
                                            break;
                                        case 2: b2(null);
                                            break;
                                        case 3:b7(null);
                                            break;
                                        default:
                                            b8(null);
                                            break;
                                    }
                                    break;
                                case 6:
                                    b9(null);
                                    break;
                                case 7:
                                    do {
                                        one = (int) (Math.random() * 10);
                                    } while (one % 2 == 1);
                                    switch (one) {
                                        case 2:
                                            b2(null);
                                            break;
                                        case 4:
                                            b4(null);
                                            break;
                                        case 6:
                                            b6(null);
                                            break;
                                        case 8:
                                            b8(null);
                                            break;
                                        default:
                                            b4(null);
                                            break;
                                    }
                                    break;
                                case 8:
                                    one=(int)(Math.random()*10);
                                    r=one%4;
                                    switch(r) {
                                        case 1: b4(null);
                                            break;
                                        case 2: b6(null);
                                            break;
                                        case 3:b9(null);
                                            break;
                                        default:
                                            b7(null);
                                            break;
                                    }
                                    break;
                                case 9:
                                    b6(null);
                                    break;
                            }
                        }
                        else if (x7 == 1) {
                            switch (i) {
                                case 1:
                                    b4(null);
                                    break;
                                case 2:
                                    one=(int)(Math.random()*10);
                                    r=one%4;
                                    switch(r) {
                                        case 1: b1(null);
                                            break;
                                        case 2: b3(null);
                                            break;
                                        case 3:b4(null);
                                            break;
                                        default:
                                            b6(null);
                                            break;
                                    }
                                    break;
                                case 4:
                                    b1(null);
                                    break;
                                case 6:
                                    one=(int)(Math.random()*10);
                                    r=one%4;
                                    switch(r) {
                                        case 1: b2(null);
                                            break;
                                        case 2: b3(null);
                                            break;
                                        case 3:b9(null);
                                            break;
                                        default:
                                            b8(null);
                                            break;
                                    }
                                    break;
                                case 7:if(x9==1)b8(null);
                                break;
                                case 8:
                                    b9(null);
                                    break;
                                case 9:
                                    b8(null);
                                    break;
                            }
                        }
                        else if (x9 == 1) {
                            switch (i) {
                                case 2:
                                    one=(int)(Math.random()*10);
                                    r=one%4;
                                    switch(r) {
                                        case 1: b1(null);
                                            break;
                                        case 2: b3(null);
                                            break;
                                        case 3:b4(null);
                                            break;
                                        default:
                                            b6(null);
                                            break;
                                    }
                                    break;
                                case 3:
                                    b6(null);
                                    break;
                                case 4:
                                    one=(int)(Math.random()*10);
                                    r=one%4;
                                    switch(r) {
                                        case 1: b1(null);
                                            break;
                                        case 2: b2(null);
                                            break;
                                        case 3:b7(null);
                                            break;
                                        default:
                                            b8(null);
                                            break;
                                    }
                                    break;
                                case 6:
                                    b3(null);
                                    break;
                                case 7:
                                    b8(null);
                                    break;
                                case 8:
                                    b7(null);
                                    break;
                            }
                        }

                    }
                }
                else
                {
                    if(x2==1)
                    {
                        switch (i)
                        {
                            case 1:b3(null);
                            break;
                            case 2: if(x4==1) b1(null);
                            else if(x6==1)b3(null);
                            else b4(null);
                            break;
                            case 3:b1(null);
                            break;
                            case 4:b1(null);
                            break;
                            case 6:b3(null);
                            break;
                            case 7:b4(null);
                            break;
                            case 8:b4(null);
                            break;
                            case 9:b3(null);
                            break;
                        }
                    }
                    else if(x4==1)
                    {
                        switch (i)
                        {
                            case 1:b7(null);
                                break;
                            case 2:b1(null);
                                break;
                            case 3:b1(null);
                                break;
                            case 4:if(x6==1)b2(null);
                            else b7(null);
                                break;
                            case 6:b2(null);
                                break;
                            case 7:b1(null);
                                break;
                            case 8:b7(null);
                                break;
                            case 9:b7(null);
                                break;
                        }
                    }
                    else if(x6==1)
                    {
                        switch (i) {
                            case 1:
                                b3(null);
                                break;
                            case 2:b3(null);
                                break;
                            case 3:
                                b9(null);
                                break;
                            case 4:
                                b8(null);
                                break;
                            case 6:b9(null);
                                break;
                            case 7:
                                b9(null);
                                break;
                            case 8:
                                b9(null);
                                break;
                            case 9:
                                b3(null);
                                break;
                        }
                    }
                    else
                    {
                        switch (i)
                        {
                            case 1:b7(null);
                                break;
                            case 2:
                                b6(null);
                                break;
                            case 3:b9(null);
                                break;
                            case 4:b7(null);
                                break;
                            case 6:b9(null);
                                break;
                            case 7:b9(null);
                                break;
                            case 9:b7(null);
                                break;
                        }
                    }
                }
            }
            else if(x==5)stepwin();
            else stepwin();
        }
        public void stepwin(){
            if(x5==2){com=1;
                if(x1==2&&x9==0)b9(null);
                else if(x2==2&&x8==0)b8(null);
                else if(x3==2&&x7==0)b7(null);
                else if(x4==2&&x6==0)b6(null);
                else if(x6==2&&x4==0)b4(null);
                else if(x7==2&&x3==0)b3(null);
                else if(x8==2&&x2==0)b2(null);
                else if(x9==2&&x1==0)b1(null);
            }
            else
            {
                if(x1==2){
                    if(x2==2&&x3==0)b3(null);
                    else if(x3==2&&x2==0)b2(null);
                    else if(x4==2&&x7==0)b7(null);
                    else if(x7==2&&x4==0)b4(null);
                }
                if(x3==2&&x%2==1)
                {
                    if(x2==2&&x1==0)b1(null);
                    else if(x9==2&&x6==0)b6(null);
                    else if(x6==2&&x9==0)b9(null);
                }
                if(x7==2&&x%2==1)
                {
                    if(x4==2&&x1==0)b1(null);
                    else if(x9==2&&x8==0)b8(null);
                    else if(x8==2&&x9==0)b9(null);
                }
                if(x9==2&&x%2==1)
                {
                    if(x6==2&&x3==0)b3(null);
                    else if(x8==2&&x7==0)b7(null);
                }
            }
            if(x==5&&m==2) rand();
            else if(x==5&&m==3)blockwin();
            else if(x==7&&m==3)blockwin();

            if(m==4&&x%2==1)
                blockwin();
        }
        public void blockwin(){
            if(x5==1){com=1;
                if(x1==1&&x9==0)b9(null);
                else if(x2==1&&x8==0)b8(null);
                else if(x3==1&&x7==0)b7(null);
                else if(x4==1&&x6==0)b6(null);
                else if(x6==1&&x4==0)b4(null);
                else if(x7==1&&x3==0)b3(null);
                else if(x8==1&&x2==0)b2(null);
                else if(x9==1&&x1==0)b1(null);
            }
            else
            {
                if(x1==1){
                    if(x2==1&&x3==0)b3(null);
                    else if(x3==1&&x2==0)b2(null);
                    else if(x4==1&&x7==0)b7(null);
                    else if(x7==1&&x4==0)b4(null);
                }
                if(x3==1&&x%2==1)
                {
                    if(x2==1&&x1==0)b1(null);
                    else if(x9==1&&x6==0)b6(null);
                    else if(x6==1&&x9==0)b9(null);
                }
                if(x7==1&&x%2==1)
                {
                    if(x4==1&&x1==0)b1(null);
                    else if(x9==1&&x8==0)b8(null);
                    else if(x8==1&&x9==0)b9(null);
                }
                if(x9==1&&x%2==1)
                {
                    if(x6==1&&x3==0)b3(null);
                    else if(x8==1&&x7==0)b7(null);
                }
            }
            if(x%2==1)
                rand();
            else{
            }
        }
        public void hard() {
            r=((int)(Math.random()*10))%5;
            com=1;
            switch(r)
            {
                case 0:if (x1!=0) hard();
                else b1(null);
                    break;
                case 1:if (x3!=0) hard();
                else b3(null);
                    break;
                case 2:if (x5!=0) hard();
                else b5(null);
                    break;
                case 3:if (x7!=0) hard();
                else b7(null);
                    break;
                case 4:if (x9!=0) hard();
                else b9(null);
                    break;
                default :hard();
                    break;
            }

        }
        public void combine(){
            send="";
            send+=Integer.toString(x);
            send+=Integer.toString(x1);
            send+=Integer.toString(x2);
            send+=Integer.toString(x3);
            send+=Integer.toString(x4);
            send+=Integer.toString(x5);
            send+=Integer.toString(x6);
            send+=Integer.toString(x7);
            send+=Integer.toString(x8);
            send+=Integer.toString(x9);
            room.setValue(send);
        }
        public void divide(){
            int temp;
            temp=Character.getNumericValue(currentstate.charAt(1));
            if(temp!=0&&x1==0)b1(null);
            temp=Character.getNumericValue(currentstate.charAt(2));
            if(temp!=0&&x2==0)b2(null);
            temp=Character.getNumericValue(currentstate.charAt(3));
            if(temp!=0&&x3 ==0)b3(null);
            temp=Character.getNumericValue(currentstate.charAt(4));
            if(temp!=0&&x4==0)b4(null);
            temp=Character.getNumericValue(currentstate.charAt(5));
            if(temp!=0&&x5==0)b5(null);
            temp=Character.getNumericValue(currentstate.charAt(6));
            if(temp!=0&&x6 ==0)b6(null);
            temp=Character.getNumericValue(currentstate.charAt(7));
            if(temp!=0&&x7==0)b7(null);
            temp=Character.getNumericValue(currentstate.charAt(8));
            if(temp!=0&&x8==0)b8(null);
            temp=Character.getNumericValue(currentstate.charAt(9));
            if(temp!=0&&x9==0)b9(null);
            temp=Character.getNumericValue(currentstate.charAt(0));
            if(X==1) {
                if (temp % 2 == 0){
                    enable();
                    turn.setText("Your turn");
                }
                else {
                    disable();
                    turn.setText("Opponent's Turn");
                }
            }
            if(X==0){
                if(temp%2==1){
                    enable();
                    turn.setText("Your turn");
                }
                else{
                    disable();
                    turn.setText("Opponent's Turn");
                }
            }

        }
        public void disable(){
            b1.setEnabled(false);
            b2.setEnabled(false);
            b3.setEnabled(false);
            b4.setEnabled(false);
            b5.setEnabled(false);
            b6.setEnabled(false);
            b7.setEnabled(false);
            b8.setEnabled(false);
            b9.setEnabled(false);
        }
        public void enable() {int temp;
            temp=Character.getNumericValue(currentstate.charAt(1));
            if(temp==0)b1.setEnabled(true);
            temp=Character.getNumericValue(currentstate.charAt(2));
            if(temp==0)b2.setEnabled(true);
            temp=Character.getNumericValue(currentstate.charAt(3));
            if(temp==0)b3.setEnabled(true);
            temp=Character.getNumericValue(currentstate.charAt(4));
            if(temp==0)b4.setEnabled(true);
            temp=Character.getNumericValue(currentstate.charAt(5));
            if(temp==0)b5.setEnabled(true);
            temp=Character.getNumericValue(currentstate.charAt(6));
            if(temp==0)b6.setEnabled(true);
            temp=Character.getNumericValue(currentstate.charAt(7));
            if(temp==0)b7.setEnabled(true);
            temp=Character.getNumericValue(currentstate.charAt(8));
            if(temp==0)b8.setEnabled(true);
            temp=Character.getNumericValue(currentstate.charAt(9));
            if(temp==0)b9.setEnabled(true);

        }
        public void del(){
            ref.removeValue();
        }
    }
