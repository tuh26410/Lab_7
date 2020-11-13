package edu.temple.browserapplication;

import android.content.Context;
import android.database.DataSetObserver;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class PageListFragment extends Fragment {

    private ListView pageList;
    private pageListInterface parentActivity;
    private ArrayList<String> tabs;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public PageListFragment() {
        // Required empty public constructor
    }

    public static PageListFragment newInstance(String param1, String param2) {
        PageListFragment fragment = new PageListFragment();
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
        if(context instanceof PageListFragment.pageListInterface){
            parentActivity = (PageListFragment.pageListInterface) context;
        }else{
            throw new RuntimeException("Implement the pageListInterface interface methods.");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_page_list, container, false);
         tabs = new ArrayList<>();
        pageList = v.findViewById(R.id.pageList);
        pageList.setAdapter(new ListAdapter() {
            @Override
            public boolean areAllItemsEnabled() {
                return false;
            }

            @Override
            public boolean isEnabled(int i) {
                return false;
            }

            @Override
            public void registerDataSetObserver(DataSetObserver dataSetObserver) {

            }

            @Override
            public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

            }

            @Override
            public int getCount() {
                return tabs.size();
            }

            @Override
            public Object getItem(int i) {
                return tabs.get(i);
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }

            @Override
            public boolean hasStableIds() {
                return false;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                return null;
            }

            @Override
            public int getItemViewType(int i) {
                return 0;
            }

            @Override
            public int getViewTypeCount() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                if(tabs.size() > 0)
                    return false;
                return true;
            }
        });
        return v;
    }

    public interface pageListInterface{
        void updatePageList();
    }
}