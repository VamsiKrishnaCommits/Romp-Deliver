package com.app.rompdeliver.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.rompdeliver.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class salehis extends AppCompatActivity {
    public static ArrayList<hist> hists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int size=0;
        setContentView(R.layout.activity_salehis);
        hists = new ArrayList<>();
        try {
                    PlaceholderFragment.databaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                String date = dataSnapshot1.getRef().getKey();
                                Long totalcost = dataSnapshot1.getValue(Long.class);
                                hists.add(0, new hist(date, totalcost + ""));
                            }
                            RecyclerView recyclerView=findViewById(R.id.recycle);
                            LinearLayoutManager linearLayout=new LinearLayoutManager(getApplicationContext());
                            recyclerView.setLayoutManager(linearLayout);
                            custom12 custom12=new custom12(getApplicationContext(),hists.size());
                            recyclerView.setAdapter(custom12);
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }
                    });

                }
                catch (Exception e){
Log.i("hey",e.toString());
                }
               
               
              
            }
        }
        class custom12 extends RecyclerView.Adapter{
            int size;
            Context context;
            public custom12(Context context, int size) {
                this.context=context;
                this.size=size;
            }

            @NonNull
            @Override
            public myr1 onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                Log.i("hey","hey");
                View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rowforsum,viewGroup,false);
                myr1 myr1=new myr1(v);
                return myr1;
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                myr1 m=(myr1)viewHolder;
                m.item.setText(salehis.hists.get(i).date.substring(6,8)+"-"+salehis.hists.get(i).date.substring(4,6)+"-"+salehis.hists.get(i).date.substring(0,4));
                m.cost.setText(salehis.hists.get(i).sales);
              
            }

            @Override
            public int getItemCount() {
                return size;
            }
        }
        class myr1 extends RecyclerView.ViewHolder{
            TextView item;
            TextView cost;
            public myr1(@NonNull View itemView) {
                super(itemView);
                item=itemView.findViewById(R.id.itemname);
                cost=itemView.findViewById(R.id.totalcost);
            }
        }

