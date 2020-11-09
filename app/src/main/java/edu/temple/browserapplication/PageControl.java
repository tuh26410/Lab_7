package edu.temple.browserapplication;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;


public class PageControl extends Fragment {

    private TextView urlTextView;
    private ImageButton go;
    private ImageButton back;
    private ImageButton forward;
    private buttonClick parentActivity;
    private String URL;

    private static final String URL_KEY = "url_key";

    public PageControl() {

    }

    public static PageControl newInstance(String startingURL) {
        PageControl fragment = new PageControl();
        Bundle args = new Bundle();
        args.putString(URL_KEY, startingURL);
        fragment.setArguments(args);
        return fragment;
    }

    public void onAttach(@NonNull Context context){
        super.onAttach(context);
        if(context instanceof  buttonClick){
            parentActivity = (buttonClick) context;
        } else {
            throw new RuntimeException("Implement gridInterface on the Activity that the fragment is attached to.");
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            URL = getArguments().getString(URL_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View V = inflater.inflate(R.layout.fragment_page_control, container, false);
        urlTextView  = V.findViewById(R.id.url);
        urlTextView.setText(URL);
        go = V.findViewById(R.id.go);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parentActivity.goButtonClick(fixURL(urlTextView.getText().toString()));
            }
        });
        back = V.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parentActivity.backButtonClick();
            }
        });
        forward = V.findViewById(R.id.forward);
        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parentActivity.forwardButtonClick();
            }
        });
        return V;
    }

    public String fixURL(String url){
        if(!url.startsWith("http://") && !url.startsWith("https://")){
            return "https://" + url;
        }else {
            return url;
        }
    }

    public void updateURL(String url){
        urlTextView.setText(url);
    }

    public interface buttonClick{
        void backButtonClick();
        void forwardButtonClick();
        void goButtonClick(String url);
    }

}