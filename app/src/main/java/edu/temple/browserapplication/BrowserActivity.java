package edu.temple.browserapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class BrowserActivity extends AppCompatActivity implements PageControl.buttonClick, PageViewer.updateWebView{

    PageViewer viewer;
    PageControl controller;
    FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = getSupportFragmentManager();

        Fragment temp;

        if((temp = manager.findFragmentById(R.id.controler)) instanceof PageControl){
            controller = (PageControl) temp;
        }else{
            controller = new PageControl();
            manager.beginTransaction()
                    .add(R.id.controler, controller)
                    .commit();
        }

        if((temp = manager.findFragmentById(R.id.viewer)) instanceof PageViewer){
            viewer = (PageViewer) temp;
        }else{
            viewer = new PageViewer();
            manager.beginTransaction()
                    .add(R.id.viewer, viewer)
                    .commit();
        }

    }

    public void backButtonClick(){
        viewer.back();
    }

    public void forwardButtonClick(){
        viewer.forward();
    }

    public void goButtonClick(String url){
        viewer.go(url);
    }

    public void updateURL(String url){
        controller.updateURL(url);
    }

}