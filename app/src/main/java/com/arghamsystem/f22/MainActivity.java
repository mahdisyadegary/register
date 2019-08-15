package com.arghamsystem.f22;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView textView;
    ImageView circle;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        textView = findViewById( R.id.textView );
        circle = findViewById( R.id.circle );
        textView.setOnClickListener( this );
        dl = (DrawerLayout) findViewById( R.id.activity_main );
        NavigationView navigationView = (NavigationView) findViewById( R.id.nv );
        t = new ActionBarDrawerToggle( this, dl, R.string.Open, R.string.Close );

        dl.addDrawerListener( t );
        t.syncState();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull( getSupportActionBar() ).setDisplayHomeAsUpEnabled( true );
        }

        nv = (NavigationView) findViewById( R.id.nv );
        nv.setNavigationItemSelectedListener( new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.account:
                        Intent intent = new Intent( MainActivity.this , account.class );
                        startActivity( intent );
                        break;
                    case R.id.profile:
                        Toast.makeText( MainActivity.this, "Settings", Toast.LENGTH_SHORT ).show();
                        break;
                    case R.id.call:
                        Toast.makeText( MainActivity.this, "My Cart", Toast.LENGTH_SHORT ).show();
                        break;
                    default:
                        return true;
                }
                return true;

            }
        } );



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (t.onOptionsItemSelected( item ))
            return false;

        return super.onOptionsItemSelected( item );
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {

            case R.id.textView:
                randomColor();
                DrawerLayout drawerLayout = (DrawerLayout) findViewById( R.id.activity_main );
                drawerLayout.openDrawer( GravityCompat.START );
                int COLOR_DISPLAY_LENGTH = 500;
                new Handler(  ).postDelayed( new Runnable() {
                    @Override
                    public void run() {
                        circle.setColorFilter(getBaseContext().getResources().getColor(R.color.colorPrimaryDark) );
                    }
                }, COLOR_DISPLAY_LENGTH );

        }
    }

    private void randomColor() {
        Random random = new Random();
        circle.setColorFilter( Color.rgb( random.nextInt( 256 ), random.nextInt( 256 ), random.nextInt( 256 ) ) );
    }
}