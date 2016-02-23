package com.coding.willlegend.internhelper.fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.coding.willlegend.internhelper.MainActivity;
import com.coding.willlegend.internhelper.R;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FragmentBasicInfo extends Fragment {

    final int IMAGE_REQUEST_CODE = 0;
    final int CAMERA_REQUEST_CODE = 1;
    final int RESIZE_REQUEST_CODE = 2;
    private static final String IMAGE_FILE_NAME = "header.jpg";

    Button btn_save;
    ImageView profile_image;
    ListView listView_basicInfo;
    List<Map<String,Object>> list;
    SimpleAdapter simpleAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_basicinfo, null);

        btn_save= (Button) view.findViewById(R.id.btn_saveInfo);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"信息保存成功!",Toast.LENGTH_SHORT).show();
            }
        });

        list=new ArrayList<>();
        listView_basicInfo= (ListView) view.findViewById(R.id.listView_basicInfo);
        simpleAdapter=new SimpleAdapter(getActivity(),getData(),R.layout.item_personalinfo,
                new String[]{"text","edit"},new int[]{R.id.textView_personalInfo,R.id.editText_personalInfo});
        listView_basicInfo.setAdapter(simpleAdapter);


        profile_image= (ImageView) view.findViewById(R.id.profile_image);
        profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("请选择");
                builder.setIcon(R.mipmap.yanggou);
                builder.setSingleChoiceItems(new String[]{"拍照", "从图库中选择"}, 0, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            if (isSdcardExisting()) {
                                Intent cameraIntent = new Intent(
                                        "android.media.action.IMAGE_CAPTURE");
                                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, getImageUri());
                                cameraIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 0);
                                startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
                            } else {
                                Toast.makeText(getActivity(), "请插入sd卡", Toast.LENGTH_LONG)
                                        .show();
                            }
                        } else if (which == 1) {
                            Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                            galleryIntent.addCategory(Intent.CATEGORY_OPENABLE);
                            galleryIntent.setType("image/*");
                            startActivityForResult(galleryIntent, IMAGE_REQUEST_CODE);
                        }
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("Cancel", null);
                builder.show();
            }
        });
        return view;
    }

    private List<Map<String, Object>> getData() {
        Map<String,Object> map1=new HashMap<>();
        map1.put("text","姓名");
        map1.put("edit","周洋");
        list.add(map1);
        Map<String,Object> map2=new HashMap<>();
        map2.put("text","学校名称");
        map2.put("edit","XX妓校");
        list.add(map2);
        Map<String,Object> map3=new HashMap<>();
        map3.put("text","毕业时间");
        map3.put("edit","2016");
        list.add(map3);
        Map<String,Object> map4=new HashMap<>();
        map4.put("text","最高学历");
        map4.put("edit","小学");
        list.add(map4);
        Map<String,Object> map5=new HashMap<>();
        map5.put("text","专业");
        map5.put("edit","撸管");
        list.add(map5);
        Map<String,Object> map6=new HashMap<>();
        map6.put("text","电话号码");
        map6.put("edit","110");
        list.add(map6);
        Map<String,Object> map7=new HashMap<>();
        map7.put("text","邮箱地址");
        map7.put("edit","110@jj.mlgb");
        list.add(map7);
        return list;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

            switch (requestCode) {
                case IMAGE_REQUEST_CODE:
                    if (data != null){
                        resizeImage(data.getData());
                    }
                    break;
                case CAMERA_REQUEST_CODE:
                    if (isSdcardExisting()) {
                        resizeImage(getImageUri());
                    } else {
                        Toast.makeText(getActivity(), "未找到存储卡，无法存储照片！",
                                Toast.LENGTH_LONG).show();
                    }
                    break;
                case RESIZE_REQUEST_CODE:
                    if (data != null) {
                        showResizeImage(data);
                    }
                    break;

        }
        super.onActivityResult(requestCode, resultCode, data);

    }
    public void resizeImage(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, RESIZE_REQUEST_CODE);
    }

    private void showResizeImage(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            Drawable drawable = new BitmapDrawable(photo);
            profile_image.setImageDrawable(drawable);
        }
    }

    private Uri getImageUri() {
        return Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
                IMAGE_FILE_NAME));
    }

    private boolean isSdcardExisting() {
        final String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }
}
