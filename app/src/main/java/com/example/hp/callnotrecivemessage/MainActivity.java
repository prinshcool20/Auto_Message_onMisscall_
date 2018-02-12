package com.example.hp.callnotrecivemessage;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int READ_PHONE_STATE=1;
    EditText mMessage;
    Button mSave,mGet,mClear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMessage=findViewById(R.id.message);
        mSave=findViewById(R.id.save);
        mGet=findViewById(R.id.get);
        mClear=findViewById(R.id.clear);
//Checking for required permissions
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.READ_PHONE_STATE )
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_PHONE_STATE,Manifest.permission.SEND_SMS},
                    10);
        }else{
            Toast.makeText(MainActivity.this, "All Permission is Already Granted" , Toast.LENGTH_LONG).show();
        }
        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp=getSharedPreferences("myfile1", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sp.edit();
                editor.putString("message",mMessage.getText().toString());
                editor.commit();
               // Intent intent = new Intent(MainActivity.this,MyReceiver.class);
                //intent.setAction("com.example.hp.callnotrecivemessage");
               // intent.putExtra("extra",mMessage.getText().toString());
                //sendBroadcast(intent);
               // Intent intent=new Intent(MainActivity.this,MyReceiver.class);
                //intent.putExtra("Message",mMessage.getText().toString());
               // startActivity(intent);
            }
        });
        mGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences gp=getSharedPreferences("myfile1",Context.MODE_PRIVATE);
                if(gp.contains("message")){
                    mMessage.setText(gp.getString("message",""));
                }
            }
        });
        mClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMessage.setText("");
            }
        });



    }
    //asking for permissions if not granted
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==10){
            if(grantResults.length>0&& grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(MainActivity.this, "All Permission is Granted" , Toast.LENGTH_LONG).show();
            }
        }
    }

    }


