package edu.temple.browserapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Toast;

import java.util.LinkedList;

public class BrowserActivity extends AppCompatActivity implements PageControl.buttonClick{

    LinkedList<String> URLs;
    PageViewer viewer;
    PageControl controller;
    String currentURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String startingURL = "https://www.google.com";
        currentURL = startingURL;
        URLs = new LinkedList<>();
        URLs.add(startingURL);

         controller = PageControl.newInstance(startingURL);
         viewer = PageViewer.newInstance(startingURL);

        FragmentManager manger = getSupportFragmentManager();
        FragmentTransaction transaction = manger.beginTransaction();
        transaction.add(R.id.controler, controller)
                   .add(R.id.viewer, viewer)
                   .commit();
    }


    @Override
    public void backButtonClick(String url) {
        if(URLs.getFirst().equals(currentURL)){
            Toast.makeText(this, "Cannot perform that operation", Toast.LENGTH_SHORT).show();
        }else{
            int i = URLs.indexOf(url);
            viewer.changeURL(URLs.get(i - 1));
            currentURL = url;
        }
    }

    @Override
    public void forwardButtonClick(String url) {
        if(URLs.getLast().equals(currentURL)){
            Toast.makeText(this, "Cannot perform that operation", Toast.LENGTH_SHORT).show();
            return;
        }else{
            int i = URLs.indexOf(url);
            viewer.changeURL(URLs.get(i + 1));
            currentURL = url;
        }
    }

    public void goButtonClick(String url){
        if(URLs.getLast().equals(currentURL)){
            viewer.changeURL(url);
            URLs.add(url);
            currentURL = url;
        }else if(URLs.size() > 1){
            for (int i = URLs.indexOf(currentURL); i < URLs.indexOf(URLs.getLast()); i++) {
                if (URLs.get(i).equals(URLs.getLast())) {
                    URLs.removeLast();
                    break;
                }
                URLs.remove(i);
            }
            viewer.changeURL(url);
            currentURL = url;
        }else{
            viewer.changeURL(url);
            URLs.add(url);
            currentURL = url;
        }
    }

}