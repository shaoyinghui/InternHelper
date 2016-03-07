package com.coding.willlegend.internhelper.fragment;

import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
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

                        AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                        builder1.setTitle("关于实习了么");
                        builder1.setIcon(R.mipmap.intern_icon);

                        builder1.setMessage("感谢您的使用，实习了么致力于为您提供最全面的实习信息\n\n客服电话：123456789\n\nDesigned by monster公司");
                        builder1.setPositiveButton("确定",null);
                        //builder.setMessage("客服电话：123456789");
                        //builder.setNegativeButton("取消", null);
                        AlertDialog dialog1 = builder1.create();
                        dialog1.show();

                        WindowManager.LayoutParams layoutParams1 = dialog1.getWindow().getAttributes(); ;
                        layoutParams1.width = WindowManager.LayoutParams.MATCH_PARENT;
                        layoutParams1.height = WindowManager.LayoutParams.WRAP_CONTENT;
                        dialog1.getWindow().setAttributes(layoutParams1);

                        break;
                    case 1:
                        try {
                            final DataCleanManager dataCleanManager=new DataCleanManager();
                            String message= null;
                            message = dataCleanManager.getTotalCacheSize(getActivity());

                            AlertDialog.Builder builder2 = new AlertDialog.Builder(getActivity());
                            builder2.setTitle("清除缓存");
                            builder2.setIcon(R.mipmap.intern_icon);
                            builder2.setMessage("确认清除共"+message+"的缓存吗？");
                            builder2.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Toast.makeText(getActivity(), "缓存清除成功！", Toast.LENGTH_SHORT).show();
                                    dataCleanManager.clearAllCache(getActivity());
                                }
                            });
                            builder2.setNegativeButton("取消", null);
                            AlertDialog dialog2 = builder2.create();
                            dialog2.show();

                            WindowManager.LayoutParams layoutParams2 = dialog2.getWindow().getAttributes(); ;
                            layoutParams2.width = WindowManager.LayoutParams.MATCH_PARENT;
                            layoutParams2.height = WindowManager.LayoutParams.WRAP_CONTENT;
                            dialog2.getWindow().setAttributes(layoutParams2);





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
