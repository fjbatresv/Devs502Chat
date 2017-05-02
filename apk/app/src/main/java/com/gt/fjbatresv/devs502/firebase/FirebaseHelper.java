package com.gt.fjbatresv.devs502.firebase;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.gt.fjbatresv.devs502.BuildConfig;
import com.gt.fjbatresv.devs502.R;
import com.gt.fjbatresv.devs502.entities.Message;
import com.gt.fjbatresv.devs502.lib.base.EventBus;
import com.gt.fjbatresv.devs502.main.events.MainEvent;
import com.gt.fjbatresv.devs502.utils.ConfigKeys;
import com.gt.fjbatresv.devs502.utils.GeneralUtil;

/**
 * Created by javie on 28/03/2017.
 */

public class FirebaseHelper {
    private FirebaseAuth mAuth;
    private Context context;
    private FirebaseRemoteConfig config;
    private DatabaseReference database;
    private FirebaseDatabase data;
    private FirebaseAnalytics analytics;
    private boolean conectado;
    private EventBus bus;
    private ChildEventListener mesagesListener;


    public FirebaseHelper(Context context, EventBus bus) {
        this.context = context;
        this.bus = bus;
        this.mAuth = FirebaseAuth.getInstance();
        this.data = FirebaseDatabase.getInstance();
        this.analytics = FirebaseAnalytics.getInstance(context);
        this.database = this.data.getReference();
        this.data.getReference(".info/connected").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean connected = dataSnapshot.getValue(Boolean.class);
                if (connected) {
                    conectado = true;
                }else{
                    conectado = false;
                }
                Log.e("conectadoList", String.valueOf(conectado));
                posConection(conectado);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                conectado = false;
                Log.e("conectadoList", String.valueOf(conectado));
            }
        });
        buildConfig();
    }

    private void posConection(boolean conectado) {
        this.bus.post(new MainEvent(MainEvent.connection, conectado));
    }

    private void buildConfig() {
        this.config = FirebaseRemoteConfig.getInstance();
        sendLog("config instanciado");
        this.config.setConfigSettings(new FirebaseRemoteConfigSettings.Builder()
                .setDeveloperModeEnabled(BuildConfig.DEBUG).build());
        sendLog("config en debug mode");
        config.setDefaults(R.xml.config_default);
        sendLog("config default done");
        config.fetch(10).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(Task<Void> task) {
                if (task.isSuccessful()) {
                    config.activateFetched();
                    sendLog("config cargado");
                } else {
                    sendError("Exception: " + task.getException().getLocalizedMessage());
                }
            }
        });
    }

    public String getParameterString(String nombre) {
        sendLog("get param " + nombre);
        return config.getString(nombre);
    }

    public void sendError(String error) {
        if (error != null && !error.isEmpty() && conectado) {
            try{
                FirebaseCrash.report(new Exception(error));}catch(Exception ex){}
            Log.e("sendError", error);
        }
    }

    public void sendLog(String log) {
        if (log != null && !log.isEmpty() && conectado) {
            try{FirebaseCrash.log(log);}catch(Exception ex){}
            Log.e("sendLog", log);
        }
    }

    public void logOut(FirebaseListener listener){
        this.mAuth.signOut();
        sendLog("log out");
        listener.onSuccess();
    }

    public void getUser(FirebaseSearchListener listener){
        FirebaseUser user = this.mAuth.getCurrentUser();
        if (user != null){
            listener.onSuccess(user);
        }else{
            sendLog("No user");
            listener.onError("none");
        }
    }

    public void sendMessage(String message, final FirebaseListener listener){
        FirebaseUser user = this.mAuth.getCurrentUser();
        Message mensaje = new Message();
        mensaje.setRemitente(user.getDisplayName());
        if(user.getPhotoUrl() != null){
            mensaje.setRemitentePhoto(user.getPhotoUrl().toString());
        }
        mensaje.setRemitenteUid(user.getUid());
        mensaje.setCui(GeneralUtil.uniqueCui());
        mensaje.setMensaje(message);
        mensaje.setTime(GeneralUtil.getTime());
        final String logSuccess = "Message send [" + mensaje.getCui() + "]";
        this.database.child("mensajes").child(mensaje.getCui()).setValue(mensaje, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError == null){
                    sendLog(logSuccess);
                    Bundle bundle = new Bundle();
                    bundle.putString(ConfigKeys.sendMessage, databaseReference.getKey());
                    analytics.logEvent(ConfigKeys.sendMessage, bundle);
                    listener.onSuccess();
                }else{
                    sendError(databaseError.toException().getLocalizedMessage());
                    listener.onError(databaseError.toException().getLocalizedMessage());
                }
            }
        });
    }

    public void subscribeMessages(){
        mesagesListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Message msg = dataSnapshot.getValue(Message.class);
                bus.post(new MainEvent(MainEvent.loadMessage, msg));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {}

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {}

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {}

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        this.database.child("mensajes").orderByChild("time").addChildEventListener(this.mesagesListener);
    }

    public void unSubscribeMessages(){
        this.database.child("mensajes").removeEventListener(this.mesagesListener);
    }
}
