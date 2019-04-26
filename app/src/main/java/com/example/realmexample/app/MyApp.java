package com.example.realmexample.app;

import android.app.Application;

import com.example.realmexample.Models.Board;
import com.example.realmexample.Models.Note;

import java.util.concurrent.atomic.AtomicInteger;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class MyApp extends Application {
    public static AtomicInteger BoardID = new AtomicInteger();
    public static AtomicInteger NoteID = new AtomicInteger();
    @Override
    public void onCreate() {
        super.onCreate();
        Realm realm = Realm.getDefaultInstance();
        BoardID = getIDbyTable(realm,Board.class);
        NoteID = getIDbyTable(realm, Note.class);
        realm.close();
    }
    private <T extends RealmObject> AtomicInteger getIDbyTable(Realm realm, Class<T> anyClass){
        RealmResults<T> results = realm.where(anyClass).findAll();
        return (results.size() > 0 ) ? new AtomicInteger(results.max("id").intValue()):  new AtomicInteger();
    }
}
