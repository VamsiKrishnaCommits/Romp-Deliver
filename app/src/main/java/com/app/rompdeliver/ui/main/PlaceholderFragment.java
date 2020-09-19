package com.app.rompdeliver.ui.main;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.app.rompdeliver.Main2Activity;
import com.app.rompdeliver.MySingleton;
import com.app.rompdeliver.R;
import com.app.rompdeliver.item;
import com.app.rompdeliver.king;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;



/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {
    public static HashMap<String ,Integer>hm=new HashMap<>();
    private static final String ARG_SECTION_NUMBER = "section_number";
    static FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    public static DatabaseReference databaseReference = firebaseDatabase.getReference(Main2Activity.orders);
    public static DatabaseReference databaseReference2 = firebaseDatabase.getReference(Main2Activity.orders+"sales");
    Boolean process=true;
    NotificationChannel notificationChannel;
    ValueEventListener valueEventListener = null;
    int i = 0;
    int j=0;
    int y=0;
    static ArrayList<item> disp;
    static String CHANNEL_ID = "00";
    static ArrayList<String> phone=new ArrayList<>();
    static ArrayList<String> ids=new ArrayList<>();
    static ArrayList<String>tokens=new ArrayList<>();
    static String CHANNEL_ITEM = null;
    static String CHANNEL_ORDER = "New Order!!!";
    static String token;
    private static final int PERMISSION_REQUEST = 101;
    static String TOPIC;
    static String serverkey="AAAA0CLXF3M:APA91bGaO-SeqG9L6eKBKYf-iJCISvHx3znFRqym_qLaGTSkRFO4hQL9S4Lbb7x8jLx18amsfnXuPFd0S8o3nzc0UAuk70kAt4btKlMArPjVRqXr-OPrmmyl6W76Vspuc0Dx23lMX7bA";
    static  String FCM = "https://fcm.googleapis.com/fcm/send";
    static String TITLER = "Ready";
    static custom custom;
    static String TEXTR = "Your order is ready";
    static  String TITLEC="Confirmation";
    static String TEXTC="Waiting for confirmation";
    static  String TITLECO="Confirmed!";
    static String TEXTCO="It usually takes 10 mins to get ready,will notify";
    static String TEXTCAN="Your order is cancelled,your money will be refunded shortly";
    static  String TITLECAN="Cancelled :(";
    static   RecyclerView recyclerView;
    private PageViewModel pageViewModel;
    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        return fragment;
    }

    @Override
    public View onCreateView(

            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        int permissionCheck = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.SEND_SMS);
        if (permissionCheck == PackageManager.PERMISSION_GRANTED){

        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.SEND_SMS},
                    PERMISSION_REQUEST);
        }
        View root = inflater.inflate(R.layout.fragment_main2, container, false);
        disp = new ArrayList<item>();
        Log.i("heyy",i+"");
        super.onCreate(savedInstanceState);
        final Context context = getContext();
        hm.put("Total Sales",0);
        recyclerView = root.findViewById(R.id.recyc);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        databaseReference.orderByChild("state").equalTo(null).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (process) {
                    if (dataSnapshot.getValue() != null){
                        Vibrator vibrator=(Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);

                        process=false;
                        for (final DataSnapshot snapshot : dataSnapshot.getChildren()){
                            final king k = snapshot.getValue(king.class);
                            databaseReference2.child( new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime())).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    try {
                                        int saleup = dataSnapshot.getValue(Integer.class);
                                        databaseReference2.child( new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime())).setValue(k.getTotal()+saleup);
                                    }
                                    catch (Exception e){
                                        databaseReference2.child( new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime())).setValue(k.getTotal());
                                    }
                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                }
                            });
                            databaseReference.child(snapshot.getRef().getKey()).child("state").setValue("0").addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(k.a!=null) {
                                        PlaceholderFragment.TOPIC = "/topics/"+k.tok;
                                        JSONObject notification = new JSONObject();
                                        JSONObject notificationBody = new JSONObject();
                                        try {
                                            notificationBody.put("title",PlaceholderFragment.TITLEC);
                                            notificationBody.put("message", PlaceholderFragment.TEXTC);
                                            notification.put("to", PlaceholderFragment.TOPIC);
                                            notification.put("data", notificationBody);
                                        } catch (JSONException e1) {
                                            e1.printStackTrace();
                                        }
                                        sendNotification(notification);
                                        for (i = 0; i < k.a.size(); i++) {
                                            ids.add(0, snapshot.getRef().getKey().substring(16, 20));
                                            tokens.add(0, k.getTok());
                                            phone.add(0, k.s);
                                            try {
                                                hm.put(k.a.get(i).name, hm.get(k.a.get(i).name) + k.a.get(i).cost);
                                                hm.put("Total Sales", hm.get("Total Sales") + k.a.get(i).cost);
                                            } catch (Exception e) {
                                                hm.put(k.a.get(i).name, k.a.get(i).cost);
                                                hm.put("Total Sales", hm.get("Total Sales") + k.a.get(i).cost);
                                            }
                                        }
                                        disp.addAll(0, k.a);
                                        custom = new custom(context);
                                        recyclerView.setAdapter(custom);
                                        Vibrator v = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                            v.vibrate(VibrationEffect.createOneShot(3000, VibrationEffect.DEFAULT_AMPLITUDE));
                                        } else {
                                            v.vibrate(3000);
                                        }
                                        CHANNEL_ITEM = k.a.get(0).name;

                                    }
                                    else{
                                        final String push=snapshot.getRef().getKey();
                                        final String UID=k.tok;
                                        DatabaseReference databaseReference123=firebaseDatabase.getReference(Main2Activity.orders+"cancelled");
                                        databaseReference123.child(push).setValue(new cancelled(k.s,k.getTotal()));
                                        databaseReference.child(push).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                final DatabaseReference databaseReference=firebaseDatabase.getReference();
                                                databaseReference.child("total orders").child(push).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        databaseReference.child(UID).child(push).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                PlaceholderFragment.TOPIC = "/topics/"+k.tok;
                                                                JSONObject notification = new JSONObject();
                                                                JSONObject notificationBody = new JSONObject();
                                                                try {
                                                                    notificationBody.put("title",PlaceholderFragment.TITLECAN);
                                                                    notificationBody.put("message", PlaceholderFragment.TEXTCAN);
                                                                    notification.put("to", PlaceholderFragment.TOPIC);
                                                                    notification.put("data", notificationBody);
                                                                } catch (JSONException e1) {
                                                                    e1.printStackTrace();
                                                                }
                                                                sendNotification(notification);
                                                            }
                                                        });
                                                    }
                                                });
                                            }
                                        });
                                    }
                                }
                            });
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                                //create notification channel if the api level is greater than oreo
                                notificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_ITEM, NotificationManager.IMPORTANCE_HIGH);
                                notificationChannel.setDescription(CHANNEL_ORDER);
                                try {
                                    NotificationManager manager = getActivity().getSystemService(NotificationManager.class);
                                    manager.createNotificationChannel(notificationChannel);
                                } catch (Exception e) {
                                }
                            }
                            try {
                                NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID).setSmallIcon(R.drawable.clock).setContentTitle(CHANNEL_ORDER).setContentText(CHANNEL_ITEM).setPriority(NotificationCompat.PRIORITY_MAX).setDefaults(NotificationCompat.DEFAULT_ALL);
                                NotificationManagerCompat manager = NotificationManagerCompat.from(context);
                                manager.notify(0, builder.build());
                            } catch (Exception e){
                            }
                        }
                        databaseReference.child("hey").setValue(null);
                    }


                    process=true;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        return root;
    }
    void sendNotification(JSONObject notification) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(PlaceholderFragment.FCM, notification,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("hryy", "onResponse: " + response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("hryuu", "onErrorResponse: Didn't work");
                    }
                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("Authorization","key="+PlaceholderFragment.serverkey );
                params.put("Content-Type", "application/json");
                return params;
            }
        };
        MySingleton.getInstance(getContext()).addToRequestQueue(jsonObjectRequest);
    }
}
class custom extends RecyclerView.Adapter{
    long DURATION = 500;
    private boolean on_attach = true;
    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                Log.d("hey", "onScrollStateChanged: Called " + newState);
                on_attach = false;
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
        super.onAttachedToRecyclerView(recyclerView);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }
    Context context;
    public custom(Context con){
        context=con;
    }
    @NonNull
    @Override
    public My onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.row,viewGroup,false);
        My my=new My(view);
        return my;
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        final My m=(My)viewHolder;
        Log.i("hhhs",PlaceholderFragment.disp.size()+"");
        if(PlaceholderFragment.disp.get(i).confirm){
            m.card.getBackground().setTint(Color.GREEN);
            m.cancel.setVisibility(View.INVISIBLE);
            m.confirm.setVisibility(View.INVISIBLE);
            m.orderid.setText(PlaceholderFragment.ids.get(i));
            m.total.setText(PlaceholderFragment.disp.get(i).cost * PlaceholderFragment.disp.get(i).counter + "");
            m.counter.setText(PlaceholderFragment.disp.get(i).counter + "");
            m.cost.setText(PlaceholderFragment.disp.get(i).cost + "");
            m.textView.setText(PlaceholderFragment.disp.get(i).name);
            m.phone.setText(PlaceholderFragment.phone.get(i));
        }
        else {
            m.orderid.setText(PlaceholderFragment.ids.get(i));
            m.total.setText(PlaceholderFragment.disp.get(i).cost * PlaceholderFragment.disp.get(i).counter + "");
            m.counter.setText(PlaceholderFragment.disp.get(i).counter + "");
            m.cost.setText(PlaceholderFragment.disp.get(i).cost + "");
            m.textView.setText(PlaceholderFragment.disp.get(i).name);
            m.phone.setText(PlaceholderFragment.phone.get(i));
            m.confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    PlaceholderFragment.disp.get(i).confirm=true;
                    m.card.getBackground().setTint(Color.GREEN);
                    m.cancel.setVisibility(View.INVISIBLE);
                    m.confirm.setVisibility(View.INVISIBLE);
                    PlaceholderFragment.TOPIC = "/topics/"+PlaceholderFragment.tokens.get(i);
                    JSONObject notification = new JSONObject();
                    JSONObject notificationBody = new JSONObject();
                    try {
                        notificationBody.put("title",PlaceholderFragment.TITLECO);
                        notificationBody.put("message", PlaceholderFragment.TEXTCO);
                        notification.put("to", PlaceholderFragment.TOPIC);
                        notification.put("data", notificationBody);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    sendNotification(notification);

                }
            });
        }
        m.ready.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlaceholderFragment.disp.remove(i);
                PlaceholderFragment.tokens.remove(i);
                PlaceholderFragment.phone.remove(i);
                PlaceholderFragment.ids.remove(i);
                PlaceholderFragment.recyclerView.setAdapter(PlaceholderFragment.custom );
                JSONObject notification = new JSONObject();
                JSONObject notificationBody = new JSONObject();
                try {
                    notificationBody.put("title",PlaceholderFragment.TITLER);
                    notificationBody.put("message", PlaceholderFragment.TEXTR);
                    notification.put("to", PlaceholderFragment.TOPIC);
                    notification.put("data", notificationBody);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                sendNotification(notification);
            }
        });
        m.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlaceholderFragment.TOPIC = "/topics/"+PlaceholderFragment.tokens.get(i);
                JSONObject notification = new JSONObject();
                JSONObject notificationBody = new JSONObject();
                try {
                    notificationBody.put("title", PlaceholderFragment.TITLECAN);
                    notificationBody.put("message", PlaceholderFragment.TEXTCAN);
                    notification.put("to", PlaceholderFragment.TOPIC);
                    notification.put("data", notificationBody);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                PlaceholderFragment.disp.remove(i);
                PlaceholderFragment.tokens.remove(i);
                PlaceholderFragment.phone.remove(i);
                PlaceholderFragment.ids.remove(i);
                PlaceholderFragment.recyclerView.setAdapter(PlaceholderFragment.custom );
                sendNotification(notification);
            }
        });
    }
    @Override
    public int getItemCount() {
        return PlaceholderFragment.disp.size();
    }
    void sendNotification(JSONObject notification) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(PlaceholderFragment.FCM, notification,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("hryy", "onResponse: " + response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("hryuu", "onErrorResponse: Didn't work");
                    }
                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("Authorization","key="+PlaceholderFragment.serverkey );
                params.put("Content-Type", "application/json");
                return params;
            }
        };
        MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }
}
class My extends  RecyclerView.ViewHolder{
    TextView textView;
    TextView counter;
    TextView cost;
    TextView orderid;
    boolean cofirm=false;
    TextView total;
    Button cancel;
    Button confirm;
    Button ready;
    CardView card;
    TextView phone;
    View item;
    public My(@NonNull View itemView) {
        super(itemView);
        this.item=itemView;
        orderid=itemView.findViewById(R.id.orderid);
        textView=itemView.findViewById(R.id.foodame);
        counter=itemView.findViewById(R.id.counter);
        cost=itemView.findViewById(R.id.rate);
        total=itemView.findViewById(R.id.total);
        cancel=itemView.findViewById(R.id.cancel);
        confirm=itemView.findViewById(R.id.confirm);
        ready=itemView.findViewById(R.id.ready);
        card=itemView.findViewById(R.id.cardv);
        phone=itemView.findViewById(R.id.phone);
    }
}