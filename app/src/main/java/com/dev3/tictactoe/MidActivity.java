package com.dev3.tictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.zip.Inflater;

public class MidActivity extends AppCompatActivity {
      Button btnSp,btnDp;

      Toolbar  toolbar;
//      ImageButton back;
      ImageButton share;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mid);


 //==============================================================================

        toolbar = findViewById(R.id.toolbar);
     //   back = findViewById(R.id.back);
        share = findViewById(R.id.share);
        setSupportActionBar(toolbar);
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//              onBackPressed();
//            }
//        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ishare = new Intent(Intent.ACTION_SEND);
                ishare.setType("text/plain");
                ishare.putExtra(Intent.EXTRA_TEXT,"Download this awosome game ->link of game");
                startActivity(Intent.createChooser(ishare,"share via"));
                Toast.makeText(MidActivity.this, " shared ", Toast.LENGTH_SHORT).show();
            }
        });







//==============================================================================



      //this is two player button
       btnDp= findViewById(R.id.btnDp);
       btnDp.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent tp= new Intent(MidActivity.this,TwoPlayerS.class);
               tp.putExtra("two",1);
               startActivity(tp);
           }
       });
       //this is the single player button
        btnSp = findViewById(R.id.btnSp);
        btnSp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sp= new Intent(MidActivity.this,TwoPlayerS.class);
                startActivity(sp);
            }
        });
    }


    //this method is overriden for setting up
    // set custom created menu options  in the toolbar as 3 dots


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

    // this method is overriden for back press alert
    public void onBackPressed() {


        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setIcon(R.drawable.ic_exit);
        alert.setTitle("Exit");
        alert.setMessage("Are you sure want to exit ?");
        // button first
        alert.setPositiveButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MidActivity.this, " welcome agian ", Toast.LENGTH_SHORT).show();
            }

        });
        // btton for yes mean for exit ;
        alert.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MidActivity.super.onBackPressed();
            }
        });
        alert.show();
    }



}