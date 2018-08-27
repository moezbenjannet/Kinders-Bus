package com.supming.test.KinderGarten;

/**
 * Created by Omar Bouhamed on 19/11/2017.
 */

import android.app.Notification;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;


import com.supming.test.AssignmentListAdapter.Assignment;
import com.supming.test.EventsListAdapter.Events;
import com.supming.test.EventsListAdapter.Schedule;
import com.supming.test.PartyListAdapter.Party;
import com.supming.test.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "LUCK.db";
    private static final String TAG = "DatabaseHelper";
    public static String  DB_PATH = null     ;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public DatabaseHelper(Context context) {
        super(context, DBNAME, null, 1);
        this.mContext = context;
        this.DB_PATH = "/data/data/" + context.getPackageName() + "/" + "databases/";
        Log.e("Path 1", DB_PATH);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Favorites");
        onCreate(db);
    }

    public void openDatabase() {
        String dbPath = mContext.getDatabasePath(DBNAME).getPath();
        if(mDatabase != null && mDatabase.isOpen()) {
            return;
        }
        mDatabase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void closeDatabase() {
        if(mDatabase!=null) {
            mDatabase.close();
        }
    }

    public ArrayList<Gartens> getListGartens() {
        Gartens gartens = null;
        ArrayList<Gartens> gartensList = new ArrayList<>();
        openDatabase();
        gartensList.clear();
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM LUCK", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            gartens = new Gartens(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getLong(3),cursor.getBlob(4),cursor.getInt(5));
            gartensList.add(gartens);
          cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();

        return gartensList;
    }

    public ArrayList<Gartens> getFavoriteListGartens() {
        Gartens gartens = null;
        ArrayList<Gartens> gartensList = new ArrayList<>();
        openDatabase();
        gartensList.clear();
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM LUCK", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            if (cursor.getInt(5)==1) {
                gartens = new Gartens(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getLong(3), cursor.getBlob(4), cursor.getInt(5));
                gartensList.add(gartens);
            }
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();

        return gartensList;
    }

    public ArrayList<Events> getListEvents(int gid, Calendar c) {
        Events events = null;
        ArrayList<Events> eventsArrayList = new ArrayList<>();
        openDatabase();
        eventsArrayList.clear();
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM EVENT"+gid, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
                events = new Events(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3), cursor.getInt(4), cursor.getInt(5), cursor.getInt(6), cursor.getString(7), cursor.getBlob(8));
                eventsArrayList.add(events);
                cursor.moveToNext();
                //Toast.makeText(mContext,""+c.getTime().getDate()+ " : " + events.geteventjour() + " : " +c.getTime().getMonth()+" : " +events.geteventmois() + " : " +c.getTime().getYear()+" : " +events.geteventannee(),Toast.LENGTH_SHORT).show();
                if(c.getTime().getDate()==events.geteventjour() && c.getTime().getMonth()==events.geteventmois() && c.getTime().getYear()==events.geteventannee())
                     showNotify(events.geteventtitle(),events.geteventheure(),events.geteventminute());
        }
        cursor.close();
        closeDatabase();

        return eventsArrayList;
    }

    public Schedule getSchedule(int data) {
        Schedule schedule = null;
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM LUCK", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            if (cursor.getInt(0)==data) {
                schedule = new Schedule(cursor.getString(9), cursor.getString(10), cursor.getString(11), cursor.getString(12), cursor.getString(13), cursor.getString(14));
            }
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();

        return schedule;
    }

    public String getGartendescription(int gid){
        openDatabase();
        String textView="" ;
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM LUCK", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            if (cursor.getInt(0)==gid) {
                textView =cursor.getString(6);
            }
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();

        return textView;
    }

    public String getEventdescription(int gid,int eid){
        openDatabase();
        String textView="" ;
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM EVENT"+gid, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            if (cursor.getInt(0)==eid) {
                textView = cursor.getString(9);
            }
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();

        return textView;
    }

    public String getGartencontact(int gid){
        openDatabase();
        String textView="" ;
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM LUCK", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            if (cursor.getInt(0)==gid) {
                textView =cursor.getString(7);
            }
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();

        return textView;
    }

    public ArrayList<Party> getListParty(int data) {
        Party party = null;
        ArrayList<Party> partyArrayList = new ArrayList<>();
        openDatabase();
        partyArrayList.clear();
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM PARTY"+data, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            party = new Party( cursor.getString(0) ,cursor.getInt(1), cursor.getInt(2), cursor.getInt(3),cursor.getBlob(4));
            partyArrayList.add(party);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();

        return partyArrayList;
    }

    public ArrayList<Assignment> getListAssignment(int data) {
        Assignment assignment = null;
        ArrayList<Assignment> assignmentArrayList = new ArrayList<>();
        openDatabase();
        assignmentArrayList.clear();
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM ASSIGNMENT"+data, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            assignment = new Assignment( cursor.getString(0), cursor.getString(1),cursor.getString(2),cursor.getString(3));
            assignmentArrayList.add(assignment);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();

        return assignmentArrayList;
    }

    public void updateData(int id, int favorite) {
        this.getWritableDatabase().execSQL("UPDATE LUCK SET FAVORITE='" + favorite + "'WHERE ID='"+id+"'");
    }

    //get favortie from data base
    public int updatefavorite(int id) {
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM LUCK", null);
        int x = 0;
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            if (cursor.getInt(0)==id)
                x=cursor.getInt(5);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return x ;
    }

    public void showNotify(String eventstitle,int he, int min)
    {
        Intent intent =new Intent();
        PendingIntent pIntent=PendingIntent.getActivities(mContext,0, new Intent[]{intent},0);
        NotificationCompat.Builder notificationBuilder= (NotificationCompat.Builder) new NotificationCompat.Builder(mContext)
                .setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.logo))
                .setSmallIcon(R.drawable.logo)
                .setContentTitle("don't forget about "+eventstitle)
                .setContentText("it will start at "+he+" : "+min)
                .setContentIntent(pIntent).addAction(R.mipmap.ic_launcher,"Confirmer",pIntent);
        notificationBuilder.setDefaults(Notification.DEFAULT_SOUND|Notification.DEFAULT_LIGHTS|Notification.DEFAULT_VIBRATE|Notification.FLAG_AUTO_CANCEL);
        NotificationManagerCompat notificationManager=NotificationManagerCompat.from(mContext);
        notificationManager.notify(1,notificationBuilder.build());
    }

}


