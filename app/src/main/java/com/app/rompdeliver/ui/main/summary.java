package com.app.rompdeliver.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.app.rompdeliver.R;
import com.app.rompdeliver.orderhist;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class summary extends AppCompatActivity {
    public  static String[] a;
    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();

    public static  int size=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        try {
          a= PlaceholderFragment.hm.keySet().toArray(new String[PlaceholderFragment.hm.keySet().size()] );
            size = a.length;
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(),"You do not have any sales yet",Toast.LENGTH_LONG).show();
        }
        final TextView textView=findViewById(R.id.totalsales);
        PlaceholderFragment.databaseReference2.child( new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime())).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Long s=dataSnapshot.getValue(Long.class);
                textView.setText(s+"");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Button button=findViewById(R.id.salehistory);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(summary.this,salehis.class);
                startActivity(intent);
            }
        });
        Button button1=findViewById(R.id.orderhis);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(summary.this, orderhist.class);
                startActivity(intent);
            }
        });
        RecyclerView recyclerView=findViewById(R.id.recycle);
        LinearLayoutManager linearLayout=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayout);
        custom1 custom1=new custom1(getApplicationContext(),size);
        recyclerView.setAdapter(custom1);
    }
}
class custom1 extends RecyclerView.Adapter{
int size;
    Context context;
    public custom1(Context context, int size) {
        this.context=context;
        this.size=size;
    }

    @NonNull
    @Override
    public myr onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Log.i("hey","hey");

        View v=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rowforsum,viewGroup,false);
        myr myr=new myr(v);
        return myr;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        myr m=(myr)viewHolder;
        Log.i("hey","hey");
        m.item.setText(summary.a[i]);
        try {
            m.cost.setText(PlaceholderFragment.hm.get(summary.a[i])+"");
        }
        catch (Exception e){
            m.cost.setText("-");
        }
    }

    @Override
    public int getItemCount() {
        return size;
    }
}
class myr extends RecyclerView.ViewHolder{
    TextView item;
    TextView cost;
    public myr(@NonNull View itemView) {
        super(itemView);
        item=itemView.findViewById(R.id.itemname);
        cost=itemView.findViewById(R.id.totalcost);
    }
}