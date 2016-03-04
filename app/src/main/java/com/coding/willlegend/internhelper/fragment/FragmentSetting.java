package com.coding.willlegend.internhelper.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import com.coding.willlegend.internhelper.DataCleanManager;
import com.coding.willlegend.internhelper.R;
import com.coding.willlegend.internhelper.activity.Login;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FragmentSetting extends Fragment {

    ListView listView;
    SimpleAdapter simpleAdapter;
    List<Map<String,String>> list;
    Button btn_quit;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_setting,null);

        btn_quit= (Button) view.findViewById(R.id.btn_quit);
        btn_quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),Login.class);
                startActivity(intent);
            }
        });

        list= new ArrayList<>();
        listView= (ListView) view.findViewById(R.id.listView_setting);
        simpleAdapter=new SimpleAdapter(getActivity(),getData(),R.layout.item_setting,
                new String[]{"text"},new int[]{R.id.text_setting});
        listView.setAdapter(simpleAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        Toast.makeText(getActivity(), "Designed by 怪兽公司", Toast.LENGTH_SHORT).show();
//                        Intent intent=new Intent();
//                        startActivity(intent);
                        break;
                    case 1:
                        try {
                            DataCleanManager dataCleanManager=new DataCleanManager();
                            String message= null;
                            message = dataCleanManager.getTotalCacheSize(getActivity());
                            Toast.makeText(getActivity(), "已清除" + message + "缓存", Toast.LENGTH_SHORT).show();
                            dataCleanManager.clearAllCache(getActivity());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 2:
                        Toast.makeText(getActivity(),"当前版本号:  1.0.0",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        return view;
    }

    public List<Map<String,String>> getData(){
        Map<String,String> map1=new HashMap<>();
        map1.put("text","关于");
        list.add(map1);
        Map<String,String> map2=new HashMap<>();
        map2.put("text","清除缓存");
        list.add(map2);
        Map<String,String> map3=new HashMap<>();
        map3.put("text", "当前版本号");
        list.add(map3);
        return list;
    }
}
