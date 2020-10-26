package edu.temple.browserapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;

public class BrowserActivity extends AppCompatActivity {

    FrameLayout controler;
    FrameLayout viewer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controler = findViewById(R.id.controler);
        viewer = findViewById(R.id.viewer);

        FragmentManager manger = getSupportFragmentManager();
        FragmentTransaction transaction = manger.beginTransaction();


    }
}