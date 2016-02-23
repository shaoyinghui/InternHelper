package com.coding.willlegend.internhelper.fragment;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;


import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.coding.willlegend.internhelper.MainActivity;
import com.coding.willlegend.internhelper.R;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.SearchSuggestionsAdapter;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.arlib.floatingsearchview.util.view.BodyTextView;
import com.arlib.floatingsearchview.util.view.IconImageView;
import com.coding.willlegend.internhelper.adapter.JobsAdapter;


public class FragmentHome extends Fragment {

    private ListView home_list;
    private FloatingSearchView mSearchView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.content_fragment_home,null);
        home_list = (ListView) getActivity().findViewById(R.id.home_list);
        //home_list.setAdapter(new JobsAdapter(getActivity()));
        mSearchView = (FloatingSearchView)getActivity().findViewById(R.id.floating_search_view);



        mSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, final String newQuery) {

                if (!oldQuery.equals("") && newQuery.equals("")) {
                    mSearchView.clearSuggestions();
                } else {

                    //this shows the top left circular progress
                    //you can call it where ever you want, but
                    //it makes sense to do it when loading something in
                    //the background.
                    mSearchView.showProgress();
                    Toast.makeText(getActivity(), "change",
                            Toast.LENGTH_SHORT).show();
                    //simulates a query call to a data source
                    //with a new query.
//                    DataHelper.find(MainActivity.this, newQuery, new DataHelper.OnFindResultsListener() {
//
//                        @Override
//                        public void onResults(List<ColorSuggestion> results) {
//
//                            //this will swap the data and
//                            //render the collapse/expand animations as necessary
//                            mSearchView.swapSuggestions(results);
//
//                            //let the users know that the background
//                            //process has completed
//                            mSearchView.hideProgress();
//                        }
//                    });
                }


            }
        });

        mSearchView.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
            @Override
            public void onSuggestionClicked(SearchSuggestion searchSuggestion) {

                Toast.makeText(getActivity(), "onSuggestionClicked",
                        Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onSearchAction() {

                Toast.makeText(getActivity(), "onSearchAction",
                        Toast.LENGTH_SHORT).show();
            }
        });

        mSearchView.setOnFocusChangeListener(new FloatingSearchView.OnFocusChangeListener() {
            @Override
            public void onFocus() {

                Toast.makeText(getActivity(), "onFocus",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFocusCleared() {

                Toast.makeText(getActivity(), "onFocusCleared",
                        Toast.LENGTH_SHORT).show();
            }
        });

        //handle menu clicks the same way as you would
        //in a regular activity
        mSearchView.setOnMenuItemClickListener(new FloatingSearchView.OnMenuItemClickListener() {
            @Override
            public void onActionMenuItemSelected(MenuItem item) {


                Toast.makeText(getActivity(), item.getTitle(),
                        Toast.LENGTH_SHORT).show();
            }



        });




















        return view;


    }
}
