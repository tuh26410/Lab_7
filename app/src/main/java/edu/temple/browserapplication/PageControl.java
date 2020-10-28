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

    TextView url;
    ImageButton go;
    ImageButton back;
    ImageButton forward;
    buttonClick parentActivity;
    String URL;

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
        url  = V.findViewById(R.id.url);
        url.setText(URL);
        go = V.findViewById(R.id.go);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parentActivity.goButtonClick(URL);
            }
        });
        back = V.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parentActivity.backButtonClick(URL);
            }
        });
        forward = V.findViewById(R.id.forward);
        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parentActivity.forwardButtonClick(URL);
            }
        });
        return V;
    }


    public interface buttonClick{
        void backButtonClick(String url);
        void forwardButtonClick(String url);
        void goButtonClick(String url);
    }

}