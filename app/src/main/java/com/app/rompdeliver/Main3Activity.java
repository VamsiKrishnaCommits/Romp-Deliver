package com.app.rompdeliver;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        final EditText editText=findViewById(R.id.pass);
        Button button=findViewById(R.id.butt);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(editText.getText().toString().equals("7894")){
                   Log.i("hey","heyyyyyyyy");
                   Intent intent=new Intent(Main3Activity.this,Main2Activity.class);
                   intent.putExtra("avail","ranjeethaavail");
                   intent.putExtra("reference","ordersforranjeetha");
                   intent.putExtra("menu","ranjeetha");
                   intent.putExtra("realtime","ranjeetharealtime");
                   intent.putExtra("phonenumber","7002924664");
                   intent.putExtra("wait","45 minutes");
                   startActivity(intent);
                   finish();
               }
               else if(editText.getText().toString().equals("1254")){
                   Intent intent=new Intent(Main3Activity.this,Main2Activity.class);
                   intent.putExtra("avail","foodtruckavail");
                   intent.putExtra("reference","ordersforfoodtruck");
                   intent.putExtra("menu","foodtruck");
                   intent.putExtra("realtime","foodtruckrealtime");
                   intent.putExtra("phonenumber","9678890063");
                   intent.putExtra("wait","20 minutes");
                   startActivity(intent);
                   finish();
               }
               else if(editText.getText().toString().equals("2589")){
                   Intent intent=new Intent(Main3Activity.this,Main2Activity.class);
                   intent.putExtra("avail","canteenavail");
                   intent.putExtra("menu","iiitgmenu");
                   intent.putExtra("reference","ordersforcanteen");
                   intent.putExtra("realtime","realiiitgmenu");
                   intent.putExtra("wait","15 minutes");
                   startActivity(intent);
                    finish();
               }
               else if(editText.getText().toString().equals("1234")){
                   Intent intent=new Intent(Main3Activity.this,Main2Activity.class);
                   intent.putExtra("avail","canteenacad");
                   intent.putExtra("menu","canteen");
                   intent.putExtra("reference","ordersforacad");
                   intent.putExtra("realtime","canteenrealtime");
                   startActivity(intent);
                   finish();
               }
            }
        });
    }
}
