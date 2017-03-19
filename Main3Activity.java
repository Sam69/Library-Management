package com.example.sameerwagh.sql;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Main3Activity extends AppCompatActivity {


    NotificationManager notificationManager;
    TextView tt1;
    Button b1,b2;
Context context;
    String s1="Button 1";
  String  s2="Button 2";
    int mId=33;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        tt1 = (TextView)findViewById(R.id.textView10);
        b1=(Button)findViewById(R.id.button);
        b2=(Button)findViewById(R.id.button3);
        Timestamp t1;

        context=this;

        Bundle b = getIntent().getExtras();

        final String user = b.getString("key1");
      //  final String pass = b.getString("key2");
       // final int UserID = b.getInt("key3");
        //final int Book1 = b.getInt("key4");
        //final String bookname=b.getString("key5");
        //final String DueDate1=b.getString("key6");
        final boolean admin_flag=b.getBoolean("admin_flag");


String kk=" false";
        if(admin_flag)
        {
            kk=" true";
        }

        String z=getString(R.string.Choose);

        z="hello";

        String z2=getString(R.string.Choose);

        tt1.setText("Welcome "+user+z2);


    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(Main3Activity.this, MainActivity.class);
        startActivity(intent);
        finish();


    }


    @Override
    protected void onStart() {
        super.onStart();
        Bundle b = getIntent().getExtras();

        final String user = b.getString("key1");
        final String pass = b.getString("key2");
        final int UserID = b.getInt("key3");
        final int Book1 = b.getInt("key4");
        final String bookname1=b.getString("key5");
        final String DueDate1=b.getString("key6");
        final boolean admin_flag=b.getBoolean("admin_flag");


        final Context c=this;
        b1=(Button)findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener()

        {


            public void onClick(View v)
            {
                tt1.setText(bookname1);



                Calendar c = Calendar.getInstance();
                System.out.println("Current time => " + c.getTime());

                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                String formattedDate = df.format(c.getTime());



                if (true) {

                    NotificationCompat.Builder mBuilder =
                            new NotificationCompat.Builder(context)
                                    .setSmallIcon(R.drawable.ic_menu_camera)
                                    .setContentTitle("DueDate for: "+bookname1)
                                    .setContentText(""+DueDate1+" ");
// Creates an explicit intent for an Activity in your app
                    Intent resultIntent = new Intent(context, Main6Activity.class);

// The stack builder object will contain an artificial back stack for the
// started Activity.
// This ensures that navigating backward from the Activity leads out of
// your application to the Home screen.
                    TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
// Adds the back stack for the Intent (but not the Intent itself)
                    stackBuilder.addParentStack(Main6Activity.class);
// Adds the Intent that starts the Activity to the top of the stack
                    stackBuilder.addNextIntent(resultIntent);
                    PendingIntent resultPendingIntent =
                            stackBuilder.getPendingIntent(
                                    0,
                                    PendingIntent.FLAG_UPDATE_CURRENT
                            );
                    mBuilder.setContentIntent(resultPendingIntent);
                    NotificationManager mNotificationManager =
                            (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
// mId allows you to update the notification later on.
                    mNotificationManager.notify(mId, mBuilder.build());

                }


                //===========================================

                if(admin_flag)
                {
                    Intent intent = new Intent(Main3Activity.this, Main7Activity.class);
                    intent.putExtra("user", user);
                    intent.putExtra("Book1", bookname1);
                    intent.putExtra("DueDate1", DueDate1);
                    intent.putExtra("admin_flag", admin_flag);
                    startActivity(intent);
                    finish();

                }
                else {
                    Intent intent = new Intent(Main3Activity.this, Main6Activity.class);
                    intent.putExtra("user", user);
                    intent.putExtra("Book1", bookname1);
                    intent.putExtra("DueDate1", DueDate1);
                    intent.putExtra("admin_flag", admin_flag);
                    startActivity(intent);
                    finish();
                }
            }
        });


        b2=(Button)findViewById(R.id.button3);
        b2.setOnClickListener(new View.OnClickListener() {


            public void onClick(View v)
            {
                Intent intent = new Intent(Main3Activity.this, Main4Activity.class);
                startActivity(intent);
                finish();
            }
        });
    }


    public void MyAccount(View view)
    {

    }




}


