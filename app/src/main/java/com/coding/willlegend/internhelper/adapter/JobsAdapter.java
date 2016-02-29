package com.coding.willlegend.internhelper.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.coding.willlegend.internhelper.R;
import com.coding.willlegend.internhelper.activity.JobDesActivity;
import com.coding.willlegend.internhelper.bean.Job_Information;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;


import android.net.Uri;
import android.widget.Toast;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by user on 2016/1/18.
 */
public class JobsAdapter extends BaseAdapter {

    private List<Job_Information> jobs;
    private Context context;
    private Bitmap cachedImage;
    private Intent intent;
    public JobsAdapter(Context context, List jobs)
    {
        super();
        this.context = context;
        this.jobs = jobs;
        Fresco.initialize(context);
    }

    @Override
    public int getCount() {

        return jobs.size();
    }

    @Override
    public Object getItem(int position) {

        return jobs.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.home_listview, null);
            ItemViewCache itemViewCache = new ItemViewCache();

            itemViewCache.position_image = (SimpleDraweeView) convertView
                    .findViewById(R.id.position_image);
            itemViewCache.position_name = (TextView) convertView
                    .findViewById(R.id.position_name);
            itemViewCache.company_name = (TextView) convertView
                    .findViewById(R.id.company_name);
            itemViewCache.company_address = (TextView) convertView
                    .findViewById(R.id.company_address);
            itemViewCache.publish_date = (TextView) convertView
                    .findViewById(R.id.publish_date);
            itemViewCache.salary = (TextView) convertView
                    .findViewById(R.id.salary);

            convertView.setTag(itemViewCache);

        }

        ItemViewCache cache = (ItemViewCache) convertView.getTag();
        Job_Information jobitem = (Job_Information) this.jobs.get(position);

        //NewsTask nt = new NewsTask();
        //cachedImage = nt.loadDrawableFromNet(cache.news_image,news.getNews_image());
        //cache.position_image.setImageBitmap(cachedImage);
        //SimpleDraweeView draweeView = (SimpleDraweeView) findViewById(R.id.position_image);
        Uri uri = Uri.parse(jobitem.getPosition_image());
        cache.position_image.setImageURI(uri);

        cache.position_name.setText(jobitem.getPosition_name());
        cache.company_name.setText(jobitem.getCompany_name());
        cache.company_address.setText(jobitem.getCompany_address());
        cache.publish_date.setText(jobitem.getPublish_date());
        cache.publish_date.append("更新");
        cache.salary.setText(String.valueOf(jobitem.getMinsalary()));
        cache.salary.append("-");
        cache.salary.append(String.valueOf(jobitem.getMaxsalary()));
        final Job_Information jobdata= jobitem;

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();

                bundle.putSerializable("jobdata",jobdata);

                intent = new Intent(context, JobDesActivity.class);
                intent.putExtras(bundle);
                 context.startActivity(intent);

            }
        });
        return convertView;

    }
    private static class ItemViewCache {
        private SimpleDraweeView position_image;
        private TextView position_name;
        private TextView company_name;
        private TextView company_address;
        private TextView publish_date;
        private TextView salary;

    }


    public void updateList(List<Job_Information> newList) {
        List<Job_Information> listTemp = new ArrayList<Job_Information>();
        listTemp.addAll(newList);
        jobs.clear();
        jobs.addAll(listTemp);
    }
    public void addJobs(List<Job_Information> newlist) {
        jobs.addAll(newlist);
    }



}



