package edu.temple.browserapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;


public class PageViewer extends Fragment {

    WebView webView;
    String URL;

    private static final String URL_KEY = "url_key";

    public PageViewer() {
        // Required empty public constructor
    }

    public static PageViewer newInstance(String startingURL) {
        PageViewer fragment = new PageViewer();
        Bundle args = new Bundle();
        args.putString(URL_KEY, startingURL);
        fragment.setArguments(args);
        return fragment;
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
        View V = inflater.inflate(R.layout.fragment_page_viewer, container, false);
        webView = V.findViewById(R.id.webView);
        webView.loadUrl(URL);
        return V;
    }

    public void changeURL(String url){
        webView.loadUrl(url);
    }
}