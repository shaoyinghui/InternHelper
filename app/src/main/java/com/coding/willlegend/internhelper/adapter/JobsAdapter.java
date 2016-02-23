package com.coding.willlegend.internhelper.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.coding.willlegend.internhelper.R;

import java.util.List;


/**
 * Created by user on 2016/1/18.
 */
public class JobsAdapter extends BaseAdapter {

    private List news;
    private Context context;
    private Bitmap cachedImage;
    public JobsAdapter(Context context)
    {
        super();
        this.context = context;
        //this.news = news;
    }




    @Override
    public int getCount() {

        return 20;
        }

    @Override
    public Object getItem(int position) {

        return position;
        }

    @Override
    public long getItemId(int position) {

        return position;
        }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

            convertView = LayoutInflater.from(context).inflate(
                    R.layout.home_listview, null);
//            ItemViewCache itemViewCache = new ItemViewCache();
//
//            itemViewCache.news_title = (TextView) convertView
//                    .findViewById(R.id.news_title);
//            itemViewCache.news_image = (ImageView) convertView
//                    .findViewById(R.id.news_image);
//            itemViewCache.news_date = (TextView) convertView
//                    .findViewById(R.id.news_date);
//            convertView.setTag(itemViewCache);


//        ItemViewCache cache = (ItemViewCache) convertView.getTag();
//        News news = (News) this.news.get(position);
//
//        //NewsTask async=new NewsTask(cache.news_image);
//       // async.execute(news.getNews_image().toString());
//        NewsTask nt = new NewsTask();
//        cachedImage = nt.loadDrawableFromNet(cache.news_image,news.getNews_image());
//        cache.news_image.setImageBitmap(cachedImage);
//        cache.news_title.setText(news.getNews_title());
//
//        cache.news_date.setText(news.getNews_date());
        return convertView;

    }
//    private static class ItemViewCache {
//        private TextView news_title;
//        private ImageView news_image;
//        private TextView news_date;
//
//    }


//    public void updateList(List<News> newList) {
//        List<News> listTemp = new ArrayList<News>();
//        listTemp.addAll(newList);
//        news.clear();
//        news.addAll(listTemp);
//    }
//    public void addNews(News item) {
//        news.add(item);
//    }



}



