package com.example.integratesocial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    ImageButton google,facebook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        google=(ImageButton)findViewById(R.id.google);
        facebook=(ImageButton)findViewById(R.id.facebook);

        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                openGap();
            }
        });
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFB();
            }
        });

    }
    public void openFB()
    {
        Intent intent=new Intent(this,fab.class);
        startActivity(intent);
    }
    public void openGap()
    {
        Intent intent =new Intent(this,g2.class);
        startActivity(intent);
    }
}