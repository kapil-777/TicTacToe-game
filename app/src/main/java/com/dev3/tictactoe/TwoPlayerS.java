package com.dev3.tictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class TwoPlayerS extends AppCompatActivity {
Button btnTp;
Spinner spinner;
    int winS;
    int twoPlayerStart;
    ArrayList<String> win = new ArrayList<>();

    //+++++++++++++++++++++++++++++++++++++++++tool variables+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    Toolbar toolbar;
    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_player_s);

        //==============================================================================

        toolbar = findViewById(R.id.toolbar);
        back = findViewById(R.id.back);
        setSupportActionBar(toolbar);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });





        //---------------intent send from the mid to main activity -----------------------------------------------------------------
        Intent two =getIntent();
        twoPlayerStart=two.getIntExtra("two",0);

 //-----------end---------intent send from the mid to main activity -----------------------------------------------------------------




        spinner = (Spinner) findViewById(R.id.spinner);
        //data of the spinner
        win.add("1");
        win.add("2");
        win.add("3");
        win.add("4");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item
                ,win);
        spinner.setAdapter(adapter);


     spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
         @Override
         public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
             winS= Integer.parseInt(win.get(i));
           //  Toast.makeText(TwoPlayerS.this, win.get(i), Toast.LENGTH_SHORT).show();
         }

         @Override
         public void onNothingSelected(AdapterView<?> adapterView) {

         }
     });

        //data end of spinner


        btnTp= findViewById(R.id.btnTp);
        btnTp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main = new Intent(TwoPlayerS.this,MainActivity.class);
                main.putExtra("wins",winS);
                main.putExtra("two",twoPlayerStart);
                startActivity(main);


            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        new MenuInflater(this).inflate(R.menu.opt_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override///  working of on the selection of the 3dots buttons or options
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int itemId = item.getItemId();

        if (itemId==R.id.call){

            // calll implicit call  or impicit intent call means here we cannot specify  the target intent or
            //class name instead that , we specify type of the ACTION  then ANDROID OS  automatically
            // suggest the intent and make call impicitly(internally) based upon the ACTION

            Intent iDial = new Intent(Intent.ACTION_DIAL);
            iDial.setData(Uri.parse("tel: +919592145918")); //this method needs the uri format data so , we parse string to uri format
            startActivity(iDial);

            Toast.makeText(this, "call is made", Toast.LENGTH_SHORT).show();

        }

        else if (itemId == R.id.msg){

            Intent iMsg = new Intent(Intent.ACTION_SENDTO);
            iMsg.setData(Uri.parse("smsto:"+Uri.encode("+919592145918")));
            iMsg.putExtra("sms_body","please solve this issue asap");
            startActivity(iMsg);

            Toast.makeText(this, "message is sent", Toast.LENGTH_SHORT).show();
        }

        else if (itemId == R.id.email){

            Intent imail = new Intent(Intent.ACTION_SEND);
            imail.setType("message/rfc822");
            imail.putExtra(Intent.EXTRA_EMAIL,new String[]{"kapss043@gmail.com"});  //this method for : To  and in the string array we can specify no of emain id's for
            imail.putExtra(Intent.EXTRA_SUBJECT,"Feedback"); //this method for :subject
            imail.putExtra(Intent.EXTRA_TEXT,"Hi i like your awosome TIC TAC TOE  game ");  //this method for : body of email
            startActivity(Intent.createChooser(imail," Email via"));
            Toast.makeText(this, "Email is sent ", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);


    }

}