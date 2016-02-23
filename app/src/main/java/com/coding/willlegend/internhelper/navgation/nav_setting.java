package com.coding.willlegend.internhelper.navgation;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.coding.willlegend.internhelper.DataCleanManager;
import com.coding.willlegend.internhelper.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class nav_setting extends Activity {

    ListView listView;
    SimpleAdapter simpleAdapter;
    List<Map<String,String>>list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_setting);

        list= new ArrayList<>();
        listView= (ListView) findViewById(R.id.listView_setting);
        simpleAdapter=new SimpleAdapter(this,getData(),R.layout.item_setting,
                new String[]{"text"},new int[]{R.id.text_setting});
        listView.setAdapter(simpleAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        Toast.makeText(nav_setting.this,"Designed by 怪兽公司",Toast.LENGTH_SHORT).show();
//                        Intent intent=new Intent();
//                        startActivity(intent);
                        break;
                    case 1:
                        try {
                            DataCleanManager dataCleanManager=new DataCleanManager();
                            String message= null;
                            message = dataCleanManager.getTotalCacheSize(nav_setting.this);
                            Toast.makeText(nav_setting.this, "已清除" + message + "缓存", Toast.LENGTH_SHORT).show();
                            dataCleanManager.clearAllCache(nav_setting.this);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 2:
                        Toast.makeText(nav_setting.this,"当前版本号:  1.0.0",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }
    public List<Map<String,String>> getData(){
        Map<String,String> map1=new HashMap<>();
        map1.put("text","关于");
        list.add(map1);
        Map<String,String> map2=new HashMap<>();
        map2.put("text","清除缓存");
        list.add(map2);
        Map<String,String> map3=new HashMap<>();
        map3.put("text","当前版本号");
        list.add(map3);
        return list;
    }
}
