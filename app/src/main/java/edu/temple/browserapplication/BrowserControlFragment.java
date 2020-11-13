package edu.temple.browserapplication;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class BrowserControlFragment extends Fragment {

    private ImageButton newTabButton;
    private browserControlInterface parentActivity;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public BrowserControlFragment() {
        // Required empty public constructor
    }

    public static BrowserControlFragment newInstance(String param1, String param2) {
        BrowserControlFragment fragment = new BrowserControlFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof BrowserControlFragment.browserControlInterface){
            parentActivity = (BrowserControlFragment.browserControlInterface) context;
        }else{
            throw new RuntimeException("Implement the browserControlInterface interface methods.");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_browser_control, container, false);
        newTabButton = v.findViewById(R.id.newPageButton);
        newTabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parentActivity.newPageButton();
            }
        });
        return v;
    }

    public interface browserControlInterface{
        void newPageButton();
    }
}

