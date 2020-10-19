package com.example.sina.sinta;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{

    LinearLayout Login_Banner_DialogBox_Alpha;
    LinearLayout Login_Banner_DialogBox_Main_Part;
    EditText Login_Password;
    LinearLayout New_Login_Banner_DialogBox_Alpha;
    LinearLayout New_Login_Banner_DialogBox_Main_Part_Parent;
    EditText New_Login_Password;
    EditText Re_New_Login_Password;
    LinearLayout Feedback_Banner_DialogBox_Alpha;
    LinearLayout Feedback_Banner_DialogBox_Main_Part_Parent;
    EditText Feedback_Input_Text;
    LinearLayout AboueUs_Banner_DialogBox_Alpha;
    LinearLayout AboutUs_Banner_DialogBox_Main_Part_Parent;




    private String ps;
    private boolean tr = true;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // ****************************************************************************************

        // notification
        try {
            NotificationManager notif = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            Notification notify = new Notification.Builder(getApplicationContext())
                    .setContentTitle("SinTa Encryptor")
                    .setContentText("Protect your files from anyone, any devices, any OSs and ...")
                    .setSmallIcon(R.drawable.sinta_notification)
                    .build();
            notify.flags |= Notification.FLAG_AUTO_CANCEL;
            notif.notify(0, notify);
        }
        catch (Exception e)
        {

        }
        // end notification

        PlayGifView p = (PlayGifView)findViewById(R.id.gif_play);
        p.setImageResource(R.drawable.encryption);

        // -----------
        Login_Password = (EditText)findViewById(R.id.Login_Input_Text);
        Login_Banner_DialogBox_Alpha = (LinearLayout)findViewById(R.id.Login_Banner_DialogBox_Alpha);
        Login_Banner_DialogBox_Main_Part = (LinearLayout)findViewById(R.id.Login_Banner_DialogBox_Main_Part_Parent);
        New_Login_Banner_DialogBox_Alpha = (LinearLayout)findViewById(R.id.New_Login_Banner_DialogBox_Alpha);
        New_Login_Banner_DialogBox_Main_Part_Parent = (LinearLayout)findViewById(R.id.New_Login_Banner_DialogBox_Main_Part_Parent);
        New_Login_Password = (EditText)findViewById(R.id.New_Login_Input_Text);
        Re_New_Login_Password = (EditText)findViewById(R.id.New_Re_Login_Input_Text);
        Feedback_Banner_DialogBox_Alpha = (LinearLayout)findViewById(R.id.Feedback_Banner_DialogBox_Alpha);
        Feedback_Banner_DialogBox_Main_Part_Parent = (LinearLayout)findViewById(R.id.Feedback_Banner_DialogBox_Main_Part_Parent);
        Feedback_Input_Text = (EditText)findViewById(R.id.Feedback_Input_Text);
        AboueUs_Banner_DialogBox_Alpha = (LinearLayout)findViewById(R.id.AboutUs_Banner_DialogBox_Alpha);
        AboutUs_Banner_DialogBox_Main_Part_Parent = (LinearLayout)findViewById(R.id.AboutUs_Banner_DialogBox_Main_Part_Parent);





        // checking user status --------------------------------
        int check_user_status = Check_User_Status("");
        if(check_user_status == -1)
        {
            New_Login_Banner_DialogBox_Alpha.setVisibility(LinearLayout.VISIBLE);
            New_Login_Banner_DialogBox_Main_Part_Parent.setVisibility(LinearLayout.VISIBLE);
        }
        else
        {
            Login_Banner_DialogBox_Alpha.setVisibility(LinearLayout.VISIBLE);
            Login_Banner_DialogBox_Main_Part.setVisibility(LinearLayout.VISIBLE);
        }
        //------------------------------------------------------

    }

    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(id == R.id.about_us)
        {
            AboueUs_Banner_DialogBox_Alpha.setVisibility(LinearLayout.VISIBLE);
            AboutUs_Banner_DialogBox_Main_Part_Parent.setVisibility(LinearLayout.VISIBLE);
            return true;
        }
        else if(id == R.id.exit)
        {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera)
        {
            // Handle the camera action
            Intent i = new Intent(MainActivity.this,file_safety_class.class);
            i.putExtra("ps",ps);
            startActivity(i);
        }
        else if(id == R.id.send_feedback)
        {
            Feedback_Banner_DialogBox_Alpha.setVisibility(LinearLayout.VISIBLE);
            Feedback_Banner_DialogBox_Main_Part_Parent.setVisibility(LinearLayout.VISIBLE);
        }
        else if (id == R.id.password)
        {
            New_Login_Password.setText("");
            Re_New_Login_Password.setText("");
            New_Login_Banner_DialogBox_Alpha.setVisibility(LinearLayout.VISIBLE);
            New_Login_Banner_DialogBox_Main_Part_Parent.setVisibility(LinearLayout.VISIBLE);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // my edition

    public void Just_Test(View v)
    {
        v.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                v.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                return false;
            }
        });
        v.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        NotificationManager notif = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notify = new Notification.Builder(getApplicationContext())
                .setContentTitle("Hello")
                .setContentText("Hello Sina Tamari")
                .setSmallIcon(android.R.drawable.ic_lock_idle_lock)
                .build();
        notify.flags |= Notification.FLAG_AUTO_CANCEL;
        notif.notify(0,notify);
    }

    public void tes(View b)
    {
        // Do nothing
    }

    public void Login_Button_Go(View v)
    {
        v.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                v.setBackgroundColor(getResources().getColor(R.color.gray));
                return false;
            }
        });
        v.setBackgroundColor(getResources().getColor(android.R.color.background_light));

        View view = this.getCurrentFocus();
        if( view != null)
        {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
        }

        int t = Check_User_Status(getMD5(Login_Password.getText().toString()));
        if( t == 1 )
        {
            ps = Login_Password.getText().toString();
            tr = false;
            Login_Banner_DialogBox_Alpha.setVisibility(LinearLayout.INVISIBLE);
            Login_Banner_DialogBox_Main_Part.setVisibility(LinearLayout.INVISIBLE);
            Snackbar.make(v, "You are loged in", Snackbar.LENGTH_LONG).setAction("Action", null).show();
        }
        else {
            Snackbar.make(v, "Wrong password !", Snackbar.LENGTH_LONG).setAction("Action", null).show();
        }
    }

    public void Close_Login_Banner(View v)
    {
        v.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                v.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                return false;
            }
        });
        v.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        if(tr)
            finish();
        else
        {
            New_Login_Password.setText("");
            Re_New_Login_Password.setText("");
            New_Login_Banner_DialogBox_Alpha.setVisibility(LinearLayout.INVISIBLE);
            New_Login_Banner_DialogBox_Main_Part_Parent.setVisibility(LinearLayout.INVISIBLE);
        }
    }

    public int Check_User_Status(String psa)
    {

        SQLiteDatabase mydatabase = openOrCreateDatabase("ups.db", MODE_PRIVATE, null);
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS PS(pass text);");
        Cursor resultset = mydatabase.rawQuery("select * from PS",null);
        resultset.moveToFirst();
        int c = resultset.getCount();
        if(c == 0)
        {
            mydatabase.close();
            resultset.close();
            return -1;
        }
        else
        {
            String s = resultset.getString(0);
            if(psa.equals(s))
            {
                mydatabase.close();
                resultset.close();
                return 1;
            }
            else
            {
                mydatabase.close();
                resultset.close();
                return 0;
            }
        }

    }

    public String getMD5(String txt)
    {
        try
        {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.reset();
            m.update(txt.getBytes());
            byte[] digest = m.digest();
            BigInteger bigInteger = new BigInteger(1,digest);
            String hashtext = bigInteger.toString(16);
            while (hashtext.length()<32){
                hashtext = "0"+hashtext;
            }
            return hashtext;
        }
        catch (Exception e)
        {
            return "";
        }
    }

    public void Insert_This_User(String up)
    {
        if(tr) {
            SQLiteDatabase mydatabase = openOrCreateDatabase("ups.db", MODE_PRIVATE, null);
            mydatabase.execSQL("Insert into PS values ('" + up + "')");
            mydatabase.close();
        }
        else
        {
            SQLiteDatabase mydatabase = openOrCreateDatabase("ups.db", MODE_PRIVATE, null);
            mydatabase.execSQL("Drop table PS;");
            mydatabase.execSQL("CREATE TABLE IF NOT EXISTS PS(pass text);");
            mydatabase.execSQL("Insert into PS values ('" + up + "')");
            mydatabase.close();
        }
    }

    public void New_Login_Button_Go(View v)
    {
        v.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                v.setBackgroundColor(getResources().getColor(R.color.gray));
                return false;
            }
        });
        v.setBackgroundColor(getResources().getColor(android.R.color.background_light));
        View view = this.getCurrentFocus();
        if( view != null)
        {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
        if(tr) {
            String p1 = New_Login_Password.getText().toString();
            String p2 = Re_New_Login_Password.getText().toString();

            if (p1.length() == 0 || p2.length() == 0 || !password_is_correct(p1) || !password_is_correct(p2)) {
                Snackbar.make(v, "Please give me a correct password !", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            } else {
                if (!p1.equals(p2)) {
                    Snackbar.make(v, "Passwords are not matched !", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                } else {
                    if (p1.length() < 8) {
                        Snackbar.make(v, "Minimum length of password is 8", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    } else {
                        ps = p1;
                        tr = false;
                        Insert_This_User(getMD5(ps));
                        New_Login_Banner_DialogBox_Alpha.setVisibility(LinearLayout.INVISIBLE);
                        New_Login_Banner_DialogBox_Main_Part_Parent.setVisibility(LinearLayout.INVISIBLE);
                        Snackbar.make(v, "Welcome to SinTa Encryptor", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    }

                }
            }
        }
        else
        {
            String p1 = New_Login_Password.getText().toString();
            String p2 = Re_New_Login_Password.getText().toString();

            if (p1.length() == 0 || p2.length() == 0 || !password_is_correct(p1) || !password_is_correct(p2)) {
                Snackbar.make(v, "Please give me a correct password !", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            } else {
                if (!p1.equals(p2)) {
                    Snackbar.make(v, "Passwords are not matched !", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                } else {
                    if (p1.length() < 8) {
                        Snackbar.make(v, "Minimum length of password is 8", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    } else {
                        ps = p1;
                        tr = false;
                        Insert_This_User(getMD5(ps));
                        New_Login_Banner_DialogBox_Alpha.setVisibility(LinearLayout.INVISIBLE);
                        New_Login_Banner_DialogBox_Main_Part_Parent.setVisibility(LinearLayout.INVISIBLE);
                        Snackbar.make(v, "Your password changed !", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    }

                }
            }
        }
    }

    public boolean password_is_correct(String p)
    {
        int c = 0;
        for(char ch : p.toCharArray())
        {
            c = (int)ch;
            if((c >= 48 && c <= 57) || (c >= 65 && c <= 90) || (c >= 97 && c <= 122))
            {

            }
            else
            {
                return false;
            }
        }
        return true;
    }

    public void Close_Feedback_Banner(View v)
    {
        v.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                v.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                return false;
            }
        });
        v.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        Feedback_Banner_DialogBox_Main_Part_Parent.setVisibility(LinearLayout.INVISIBLE);
        Feedback_Banner_DialogBox_Alpha.setVisibility(LinearLayout.INVISIBLE);
        Feedback_Input_Text.setText("");
    }

    public void Feedback_Button_Go(View v)
    {
        v.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                v.setBackgroundColor(getResources().getColor(R.color.gray));
                return false;
            }
        });
        v.setBackgroundColor(getResources().getColor(android.R.color.background_light));

        View view = this.getCurrentFocus();
        if( view != null)
        {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
        }

        Intent Email = new Intent(Intent.ACTION_SEND);
        Email.setType("text/email");
        Email.putExtra(Intent.EXTRA_EMAIL,new String[] { "cissp.it@gmail.com" });
        Email.putExtra(Intent.EXTRA_SUBJECT,"Feedback of SinTa");
        Email.putExtra(Intent.EXTRA_TEXT,Feedback_Input_Text.getText().toString()+"");
        startActivity(Intent.createChooser(Email,"Send Feedback with"));

        Feedback_Banner_DialogBox_Main_Part_Parent.setVisibility(LinearLayout.INVISIBLE);
        Feedback_Banner_DialogBox_Alpha.setVisibility(LinearLayout.INVISIBLE);
        Feedback_Input_Text.setText("");
    }

    public void Close_AboutUs_Banner(View v)
    {
        v.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                v.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                return false;
            }
        });
        v.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        AboutUs_Banner_DialogBox_Main_Part_Parent.setVisibility(LinearLayout.INVISIBLE);
        AboueUs_Banner_DialogBox_Alpha.setVisibility(LinearLayout.INVISIBLE);

    }
}
