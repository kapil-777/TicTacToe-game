package com.dev3.tictactoe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;


public class MainActivity extends AppCompatActivity {
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9,ngame;
    String b1, b2, b3, b4, b5, b6, b7, b8, b9;
    TextView result ,cX,cDraw,cY;
    Random random;
   private  int ran;
    int win,twoPlayerStart;   //these variables for intent getting
    int flag = 0,count = 0;   //these variables for the game
    int cx=0,cdraw=0,cy=0;     //these variables for the score counter

    Toolbar toolmain;
    ImageButton mback;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//==================================toolbar code==========================================================================
        toolmain = findViewById(R.id.toolmain);
        setSupportActionBar(toolmain);

        mback = findViewById(R.id.mback);
        mback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });




//==================================toolbar code end==========================================================================

 //-----------------------------------random new-------------------------------------

          random  = new Random();

 //----------end----------------------random new-------------------------------------

        // winning score added on 9/9/22
        Intent winner = getIntent();
        win = winner.getIntExtra("wins",0);//get data from the perivos activity
        twoPlayerStart = winner.getIntExtra("two",0);
        Log.d("getting two",String.valueOf(twoPlayerStart));
     //   Toast.makeText(this, String.valueOf(win), Toast.LENGTH_SHORT).show();
        // winning score added on 9/9/22



        //newgame code
        ngame =findViewById(R.id.ngame);
        ngame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              newGame();
              cx=0;
              cX.setText(String.valueOf(cx));
              cy=0;
              cY.setText(String.valueOf(cy));
              cdraw=0;
              cDraw.setText(String.valueOf(cdraw));
            }
        });
        //findid method calling
        init();
        //calling winner method



    }



    public void init() {
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        // result id fetch textview
         result =findViewById(R.id.result);
         // for score
        cX=findViewById(R.id.cX);
        cDraw=findViewById(R.id.cDraw);
        cY=findViewById(R.id.cY);
    }//find all views id's

    public void check(View view) {
        Button btnCurrent = (Button) view;
        if(btnCurrent.getText().toString().equals("")){
        count++;
        //set X or O to the buttons odd turns for X and even for O ;
            Log.d("count ",String.valueOf(count));
        if (twoPlayerStart==1){
            if (flag == 0) {
                btnCurrent.setText("x");
                flag = 1;
                result.setText("Turn : O");
            } else {
                btnCurrent.setText("o");
                flag = 0;
                result.setText("Turn : X");
            }
        }
        else{

        ///===========================================================new==============================================================================================
        if(flag == 0){
            btnCurrent.setText("x");
            result.setText("Turn : O");
            flag = 1;
            // computer player
            count++;
            if ((count < 10) && (flag == 1)){

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //////////////////Random class for the random blutton generator ;
                        singleAi(btnCurrent);
                        result.setText("Turn : X");
                        winss();
                        flag = 0;

                    }
                },560);

            }///if count

            }
        }

          //  /======================new=============end================
        }
        //getting values for checking
        if (count > 4) {
            b1 = btn1.getText().toString();
            b2 = btn2.getText().toString();
            b3 = btn3.getText().toString();
            b4 = btn4.getText().toString();
            b5 = btn5.getText().toString();
            b6 = btn6.getText().toString();
            b7 = btn7.getText().toString();
            b8 = btn8.getText().toString();
            b9 = btn9.getText().toString();
            Log.d("winning condition",String.valueOf(count));

            //conditions for winning the game ;

            // for horizontal conditions
            if (b1.equals(b2) && b2.equals(b3) && !b1.equals("")) {
                Log.d("winner : ",b1);
                result.setText(b1.toUpperCase(Locale.ROOT)+ " : WIN");
                //
                scoreCount(b1);
                //
                Toast.makeText(this, "winnner  " + b1, Toast.LENGTH_SHORT).show();
                //newgame afer some delay
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        newGame();

                    }
                },750);

            } else if (b4.equals(b5) && b5.equals(b6) && !b4.equals("")) {
                Log.d("winner : ",b4);
                result.setText(b4.toUpperCase(Locale.ROOT)+ " : WIN");
                //
                scoreCount(b4);
                //
                Toast.makeText(this, "winnner  " + b4, Toast.LENGTH_SHORT).show();
                //newgame afer some delay
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        newGame();
                    }
                },750);
            } else if (b7.equals(b8) && b8.equals(b9) && !b7.equals("")) {
                Log.d("winner : ",b7);
                result.setText(b7.toUpperCase(Locale.ROOT)+ " : WIN");
                //
                scoreCount(b7);
                //
                Toast.makeText(this, "Winner" + b7, Toast.LENGTH_SHORT).show();

                //newgame afer some delay
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        newGame();
                    }
                },750);
            }
            //for vertical conditions
            else if (b1.equals(b4) && b4.equals(b7) && !b1.equals("")) {
                Log.d("winner : ",b1);
                result.setText(b1.toUpperCase(Locale.ROOT)+ " : WIN");
                //
                scoreCount(b1);
                //
                Toast.makeText(this, "Winner  " + b1, Toast.LENGTH_SHORT).show();
                //newgame afer some delay
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        newGame();
                    }
                },750);
            } else if (b2.equals(b5) && b5.equals(b8) && !b2.equals("")) {
                Log.d("winner : ",b2);
                result.setText(b2.toUpperCase(Locale.ROOT)+ " : WIN");
                //
                scoreCount(b2);
                //
                Toast.makeText(this, "winner  " + b2, Toast.LENGTH_SHORT).show();
                //newgame afer some delay
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        newGame();
                    }
                },750);
            } else if (b3.equals(b6) && b6.equals(b9) && !b3.equals("")) {
                Log.d("winner : ",b3);
                result.setText(b3.toUpperCase(Locale.ROOT)+ " : WIN");
                //
                scoreCount(b3);
                //
                Toast.makeText(this, "Winner  " + b3, Toast.LENGTH_SHORT).show();
                //newgame afer some delay
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        newGame();
                    }
                },750);
            }
            //conditions for diagonal checking s
            else if (b1.equals(b5) && b5.equals(b9) && !b1.equals("")) {
                Log.d("winner : ",b1);
                result.setText(b1.toUpperCase(Locale.ROOT)+ " : WIN");
                //
                scoreCount(b1);
                //
                Toast.makeText(this, "Winner  " + b1, Toast.LENGTH_SHORT).show();
                //newgame afer some delay
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        newGame();
                    }
                },750);
            } else if (b3.equals(b5) && b5.equals(b7) && !b1.equals("")) {
                Log.d("winner : ",b3);
                result.setText(b3.toUpperCase(Locale.ROOT)+ " : WIN");
                //
                scoreCount(b3);
                //
                Toast.makeText(this, "Winner  " + b3, Toast.LENGTH_SHORT).show();
                //newgame afer some delay
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        newGame();
                    }
                },750);
            }

           else if(count==9 || count == 10){

               Toast.makeText(this, "draw   " + b3, Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        newGame();
                    }
                },750);
                cdraw++;
                cDraw.setText(String.valueOf(cdraw));

            }

        }
        }


    //onclick method end
    //score counter method
    public  void scoreCount(String b){
        if (b.equals("x"))
        {
            cx++;
            cX.setText(String.valueOf(cx));
            // winner code
            if(cx==win){
                //dialogcode--------------------------------------------
                Dialog dialog =new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.win_dialog);
                Button newgame =dialog.findViewById(R.id.newgame);
                newgame.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        newGame();
                        cx=0;
                        cX.setText(String.valueOf(cx));
                        cy=0;
                        cY.setText(String.valueOf(cy));
                        cdraw=0;
                        cDraw.setText(String.valueOf(cdraw));
                        dialog.dismiss();
                    }
                });
                Button back =dialog.findViewById(R.id.back);
                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onBackPressed();
                    }
                });

                TextView player = dialog.findViewById(R.id.player);
                player.setText("player 1");
                dialog.setCancelable(false);
                dialog.show();
                //dialogcode--------------------------------------------

                //Toast.makeText(this, "x winner  main winner", Toast.LENGTH_LONG).show();
            }

           // winner code

        }
        else if(b.equals("o")){

            cy++;
            cY.setText(String.valueOf(cy));
            // winner code
            if (cy==win){
                //dialogcode--------------------------------------------
                Dialog dialog =new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.win_dialog);
                Button newgame =dialog.findViewById(R.id.newgame);
                newgame.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        newGame();
                        cx=0;
                        cX.setText(String.valueOf(cx));
                        cy=0;
                        cY.setText(String.valueOf(cy));
                        cdraw=0;
                        cDraw.setText(String.valueOf(cdraw));
                        dialog.dismiss();
                    }
                });
                Button back =dialog.findViewById(R.id.back);
                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onBackPressed();
                    }
                });
                TextView player = dialog.findViewById(R.id.player);

                dialog.setCancelable(false);
                player.setText("player 2");

                dialog.show();
                //dialogcode--------------------------------------------
            //    Toast.makeText(this, " 0 is winner main", Toast.LENGTH_SHORT).show();
            }

            // winner code


        }
    }


    //start new game method
    public void newGame(){

        btn1.setText("");
        btn2.setText("");
        btn3.setText("");
        btn4.setText("");
        btn5.setText("");
        btn6.setText("");
        btn7.setText("");
        btn8.setText("");
        btn9.setText("");
        count=0;
        flag=0;
        result.setText("Turn : X");


    }


     // on back press show alert box  and this is OVERRIDDEN METHOD
    public void onBackPressed() {


        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setIcon(R.drawable.ic_exit);
        alert.setTitle("Back");
        alert.setMessage("Are you sure want to Back ?");
        // button first
        alert.setPositiveButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, " welcome agian ", Toast.LENGTH_SHORT).show();
            }

        });
        // btton for yes mean for exit ;
        alert.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MainActivity.super.onBackPressed();
            }
        });
        alert.show();
    }

    void singleAcc(Button btnCurrent){

        if(btn1.getText().toString().equalsIgnoreCase("x") && btn2.getText().toString().equalsIgnoreCase("x") &&(btnCurrent != btn1)){
            btn3.setText("o");
        }

    }

    //  single player method
    void singleAi(Button btnCurrent){

        ran = random.nextInt(9)+1;
        switch (ran){

            case 1:
                if(btn1.getText().toString().equals("") && (btnCurrent != btn1)){
                    btn1.setText("o");
                }
                else {singleAi(btnCurrent);}
                break;

            case 2:
                if(btn2.getText().toString().equals("") && (btnCurrent != btn2)){
                    btn2.setText("o");
                }
                else {singleAi(btnCurrent);}
                break;

            case 3:
                if(btn3.getText().toString().equals("") && (btnCurrent != btn3)){
                    btn3.setText("o");
                }
                else {singleAi(btnCurrent);}
                break;

            case 4:
                if(btn4.getText().toString().equals("") && (btnCurrent != btn4)){
                    btn4.setText("o");
                }
                else {singleAi(btnCurrent);}
                break;

            case 5:
                if(btn5.getText().toString().equals("") && (btnCurrent != btn5)){
                    btn5.setText("o");
                }
                else {singleAi(btnCurrent);}
                break;

            case 6:
                if(btn6.getText().toString().equals("") && (btnCurrent != btn6)){
                    btn6.setText("o");
                }
                else { singleAi(btnCurrent); }
                break;


            case 7:

                if(btn7.getText().toString().equals("") && (btnCurrent != btn7)){
                    btn7.setText("o");
                }
                else {singleAi(btnCurrent);}
                break;

            case 8:

                if(btn8.getText().toString().equals("") && (btnCurrent != btn8)){
                    btn8.setText("o");
                }
                else {singleAi(btnCurrent);}
                break;

            case 9:

                if(btn9.getText().toString().equals("") && (btnCurrent != btn9)){
                    btn9.setText("o");
                }
                else {singleAi(btnCurrent);}
                break;

            default:



        }
    }


    ///////////////////////////////////////////////////////////////////////////////
    void winss(){
        if (count > 4) {
            b1 = btn1.getText().toString();
            b2 = btn2.getText().toString();
            b3 = btn3.getText().toString();
            b4 = btn4.getText().toString();
            b5 = btn5.getText().toString();
            b6 = btn6.getText().toString();
            b7 = btn7.getText().toString();
            b8 = btn8.getText().toString();
            b9 = btn9.getText().toString();
            Log.d("winning condition", String.valueOf(count));

            //conditions for winning the game ;

            // for horizontal conditions

//            if ((b1.equals("o")) && (b2.equals("o")) &&
//                    (b3.equals("o")) && (b4.equals("o")) &&
//                    (b5.equals("o")) && (b6.equals("o")) &&
//                    (b7.equals("o")) && (b8.equals("o")) &&
//                    (b9.equals("o"))) {
                if (b1.equals(b2) && b2.equals(b3) && !b1.equals("") && b1.equalsIgnoreCase("o")) {
                    Log.d("winner : ", b1);
                    result.setText(b1.toUpperCase(Locale.ROOT) + " : WIN");
                    //
                    scoreCount(b1);
                    //
                    Toast.makeText(this, "winnner  " + b1, Toast.LENGTH_SHORT).show();
                    //newgame afer some delay
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            newGame();

                        }
                    }, 750);

                } else if (b4.equals(b5) && b5.equals(b6) && !b4.equals("") && b4.equalsIgnoreCase("o")) {
                    Log.d("winner : ", b4);
                    result.setText(b4.toUpperCase(Locale.ROOT) + " : WIN");
                    //
                    scoreCount(b4);
                    //
                    Toast.makeText(this, "winnner  " + b4, Toast.LENGTH_SHORT).show();
                    //newgame afer some delay
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            newGame();
                        }
                    }, 750);
                } else if (b7.equals(b8) && b8.equals(b9) && !b7.equals("") && b7.equalsIgnoreCase("o")) {
                    Log.d("winner : ", b7);
                    result.setText(b7.toUpperCase(Locale.ROOT) + " : WIN");
                    //
                    scoreCount(b7);
                    //
                    Toast.makeText(this, "Winner" + b7, Toast.LENGTH_SHORT).show();

                    //newgame afer some delay
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            newGame();
                        }
                    }, 750);
                }
                //for vertical conditions
                else if (b1.equals(b4) && b4.equals(b7) && !b1.equals("") && b1.equalsIgnoreCase("o")) {
                    Log.d("winner : ", b1);
                    result.setText(b1.toUpperCase(Locale.ROOT) + " : WIN");
                    //
                    scoreCount(b1);
                    //
                    Toast.makeText(this, "Winner  " + b1, Toast.LENGTH_SHORT).show();
                    //newgame afer some delay
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            newGame();
                        }
                    }, 750);
                } else if (b2.equals(b5) && b5.equals(b8) && !b2.equals("") && b2.equalsIgnoreCase("o")) {
                    Log.d("winner : ", b2);
                    result.setText(b2.toUpperCase(Locale.ROOT) + " : WIN");
                    //
                    scoreCount(b2);
                    //
                    Toast.makeText(this, "winner  " + b2, Toast.LENGTH_SHORT).show();
                    //newgame afer some delay
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            newGame();
                        }
                    }, 750);
                } else if (b3.equals(b6) && b6.equals(b9) && !b3.equals("") && b3.equalsIgnoreCase("o")) {
                    Log.d("winner : ", b3);
                    result.setText(b3.toUpperCase(Locale.ROOT) + " : WIN");
                    //
                    scoreCount(b3);
                    //
                    Toast.makeText(this, "Winner  " + b3, Toast.LENGTH_SHORT).show();
                    //newgame afer some delay
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            newGame();
                        }
                    }, 750);
                }
                //conditions for diagonal checking s
                else if (b1.equals(b5) && b5.equals(b9) && !b1.equals("") && b1.equalsIgnoreCase("o")) {
                    Log.d("winner : ", b1);
                    result.setText(b1.toUpperCase(Locale.ROOT) + " : WIN");
                    //
                    scoreCount(b1);
                    //
                    Toast.makeText(this, "Winner  " + b1, Toast.LENGTH_SHORT).show();
                    //newgame afer some delay
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            newGame();
                        }
                    }, 750);
                } else if (b3.equals(b5) && b5.equals(b7) && !b1.equals("") && b3.equalsIgnoreCase("o")) {
                    Log.d("winner : ", b3);
                    result.setText(b3.toUpperCase(Locale.ROOT) + " : WIN");
                    //
                    scoreCount(b3);
                    //
                    Toast.makeText(this, "Winner  " + b3, Toast.LENGTH_SHORT).show();
                    //newgame afer some delay
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            newGame();
                        }
                    }, 750);
                } else if (count == 9 || count == 10) {

                    Toast.makeText(this, "draw   " + b3, Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            newGame();
                        }
                    }, 750);
                    cdraw++;
                    cDraw.setText(String.valueOf(cdraw));

                }
//            }
        }
        }

        ///end
    }

