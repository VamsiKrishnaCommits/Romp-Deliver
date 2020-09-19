package com.app.rompdeliver.ui.main;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.app.rompdeliver.Main2Activity;
import com.app.rompdeliver.R;
import com.app.rompdeliver.item;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
class update{
    int i;
    boolean update;
    update(int i, boolean update){
        this.i=i;
        this.update=update;
    }

    //if true..then remove
    //if false...then add
}

public class BlankFragment extends PlaceholderFragment {
 public static ArrayList<item> items = new ArrayList<>();
    static ArrayList<update> updateitems = new ArrayList<>();
    static View root = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_blank, container, false);

        return root;
    }
}

class CustomAdap extends RecyclerView.Adapter {
    ArrayList<item> itemm;
    Context context;

    CustomAdap(ArrayList<item> items, Context context1) {
        itemm = items;
        context = context1;
    }
    @NonNull
    @Override
    public My1 onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rowupdate, viewGroup, false);
        My1 my = new My1(view);
        return my;
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder,  int i) {
        final My1 my = (My1) viewHolder;
        my.name.setText(itemm.get(i).name);
        my.name.setTextSize(25);
final int j=i;
if(itemm.get(i).availability.equals("0")){
    my.flag=true;
    my.cardView.getBackground().setTint(Color.RED);
    my.unavailable.setText("Available");
}
        my.unavailable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!my.flag) {
                    BlankFragment.root.findViewById(R.id.update).setVisibility(View.VISIBLE);
                    BlankFragment.updateitems.add(new update(j,true));
                    Log.i("hey", itemm.size() + "");
                    my.flag=true;
                    my.cardView.getBackground().setTint(Color.RED);
                    my.unavailable.setText("Available");
                }
                else{
                    BlankFragment.root.findViewById(R.id.update).setVisibility(View.VISIBLE);
                    BlankFragment.updateitems.add(new update(j,false));
                    my.flag=false;
                    my.cardView.getBackground().setTint(Color.WHITE);
                    my.unavailable.setText("Unavailable");
                }
            }
        });
    }
    @Override
    public int getItemCount () {
        return itemm.size();
    }
    @Override
    public int getItemViewType ( int position){
        return position;
    }
    @Override
    public long getItemId ( int position){
        return position;
    }
}

class My1 extends RecyclerView.ViewHolder{
    TextView name;
    Boolean flag=false;
    CardView cardView;
    Button unavailable;
    public My1(@NonNull View itemView) {
        super(itemView);
        name= itemView.findViewById(R.id.textfood);
        unavailable=itemView.findViewById(R.id.unavailable);
        cardView=itemView.findViewById(R.id.cardid);
    }
}
