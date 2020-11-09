package edu.temple.browserapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class PageViewer extends Fragment {

    private WebView webView;
    private String URL;
    private updateWebView parentActivity;

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

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof updateWebView){
            parentActivity = (updateWebView) context;
        }else{
            throw new RuntimeException("Implement the updateWebView interface methods.");
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
        View V = inflater.inflate(R.layout.fragment_page_viewer, container, false);
        webView = V.findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon){
                parentActivity.updateURL(url);
            }
        });
        if(savedInstanceState != null){
            webView.restoreState(savedInstanceState);
        }
        return V;
    }

    public void onSaveInstanceState(@NonNull Bundle outState){
        super.onSaveInstanceState(outState);
        webView.saveState(outState);
    }

    public void go(String url){
        webView.loadUrl(url);
    }
    public void forward(){
        webView.goForward();
    }
    public void back(){
        webView.goBack();
    }


    public interface updateWebView{
        void updateURL(String url);
    }
}