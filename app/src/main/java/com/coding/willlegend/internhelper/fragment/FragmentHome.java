package com.coding.willlegend.internhelper.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.coding.willlegend.internhelper.R;
import com.coding.willlegend.internhelper.activity.SearchActivity;
import com.coding.willlegend.internhelper.adapter.JobsAdapter;
import com.coding.willlegend.internhelper.bean.Job_Information;
import com.coding.willlegend.internhelper.bean.Job_Json;
import com.coding.willlegend.internhelper.helper.NetworkHelper;
import com.google.gson.Gson;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class FragmentHome extends Fragment implements SwipeRefreshLayout.OnRefreshListener,AbsListView.OnScrollListener{

    private ListView home_list;
    private FloatingSearchView mSearchView;
    private List datalist;
    private SwipeRefreshLayout swipeRefreshLayout;
    private int page = 1;
    private JobsAdapter adapter;
    private int lastVisibleIndex=1;
    private int maxVisibleIndex = 800;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        home_list = (ListView) view.findViewById(R.id.home_list);
        mSearchView = (FloatingSearchView)view.findViewById(R.id.floating_search_view);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        home_list.setOnScrollListener(this);

        mSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, final String newQuery) {

                if (!oldQuery.equals("") && newQuery.equals("")) {
                    mSearchView.clearSuggestions();
                    //mSearchView.hideProgress();
                } else {

                    //this shows the top left circular progress
                    //you can call it where ever you want, but
                    //it makes sense to do it when loading something in
                    //the background.
                    //mSearchView.showProgress();


                  }


            }
        });

//        mSearchView.setOnSearchListener(new OnSearchListener() {
//            @Override
//            public void onSuggestionClicked(SearchSuggestion searchSuggestion) {
//
//                Toast.makeText(getActivity(), "onSuggestionClicked",
//                        Toast.LENGTH_SHORT).show();
//
//            }
//
//            @Override
//            public void onSearchAction() {
//
//                Toast.makeText(getActivity(), "onSearchAction",
//                        Toast.LENGTH_SHORT).show();
//            }
//        });

        mSearchView.setOnFocusChangeListener(new FloatingSearchView.OnFocusChangeListener() {
            @Override
            public void onFocus() {
//                mSearchView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
//                        RelativeLayout.LayoutParams.MATCH_PARENT));
                mSearchView.setBackgroundResource(R.color.main_back);
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);

            }

            @Override
            public void onFocusCleared() {
                mSearchView.hideProgress();

            }
        });
        swipeRefreshLayout.setProgressViewOffset(false, 0,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));

        swipeRefreshLayout.setRefreshing(true);
        new MyTask().execute(new String[]{"1","1"});

        return view;


    }



    @Override
    public void onRefresh() {
        new MyTask().execute(new String[]{"1","1"});

    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {

        if (adapter.getCount() < maxVisibleIndex) {
            if (i == AbsListView.OnScrollListener.SCROLL_STATE_IDLE
                    && lastVisibleIndex == adapter.getCount() - 1) {
                page++;
                new MyTask().execute(new String[]{String.valueOf(page),"2"});
                adapter.notifyDataSetChanged();
                home_list.setSelection(lastVisibleIndex);

            }
        }



    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {
        lastVisibleIndex  = i + i1 - 1;

    }

    private class MyTask extends AsyncTask<String, Integer, List> {
        //onPreExecute方法用于在执行后台任务前做一些UI操作
        @Override
        protected void onPreExecute() {


        }

        //doInBackground方法内部执行后台任务,不可在此方法内修改UI
        @Override
        protected List doInBackground(String... params) {
            try {
                datalist = new ArrayList<Job_Information>() ;
                List<Job_Json.Msg> list = new ArrayList<Job_Json.Msg>();
                String jsonresult = NetworkHelper.request("http://www.shixiseng.com/app/interns/search", "c=&page="+params[0]);
                Gson gson = new Gson();
                Job_Json jsonJobs = gson.fromJson(jsonresult, Job_Json.class);
                list.addAll(jsonJobs.getMsg());
                if(jsonJobs.getCode()==100){
                    for(int i=0; i<list.size(); i++){
                        Job_Information job = new Job_Information();
                        job.setPosition_image(list.get(i).getUrl());
                        job.setPosition_name(list.get(i).getName());
                        job.setCompany_name(list.get(i).getCname());
                        job.setCompany_address(list.get(i).getCity());
                        job.setPublish_date(list.get(i).getRefresh());
                        job.setSalary(String.valueOf(list.get(i).getMinsal())+"-"+String.valueOf(list.get(i).getMaxsal()));
//                        job.setMinsalary(list.get(i).getMinsal());
//                        job.setMaxsalary(list.get(i).getMaxsal());
                        job.setUuid(list.get(i).getUuid());
                        datalist.add(job);
                    }
                    datalist.add(params[1]);
                    return datalist;
                }
                else{
                    return null;
                }

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        }


        @Override
        protected void onProgressUpdate(Integer... progresses) {
            super.onProgressUpdate();
        }


        @Override
        protected void onPostExecute(List result) {
            swipeRefreshLayout.setRefreshing(false);
            if(result==null){
                Toast.makeText(getActivity(),"连接出错了，稍后试试吧~",Toast.LENGTH_SHORT).show();
            }
            else{
                String execute = result.get(result.size()-1).toString();
                result.remove(result.size()-1);
                //情况一：初始化职位列表
                if(execute.equals("1")){
                    adapter = new JobsAdapter(getActivity(),result);
                    home_list.setAdapter(adapter);
                }
                //情况二：加载下一页职位列表
                else if(execute.equals("2")){
                    adapter.addJobs(result);
                    adapter.notifyDataSetChanged();
                }


            }

        }

        @Override
        protected void onCancelled() {
            swipeRefreshLayout.setRefreshing(false);
        }
    }











}
