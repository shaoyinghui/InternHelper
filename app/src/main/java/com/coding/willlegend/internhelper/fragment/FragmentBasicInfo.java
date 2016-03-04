package com.coding.willlegend.internhelper.fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.coding.willlegend.internhelper.MainActivity;
import com.coding.willlegend.internhelper.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FragmentBasicInfo extends Fragment implements View.OnClickListener{

    final int IMAGE_REQUEST_CODE = 0;
    final int CAMERA_REQUEST_CODE = 1;
    final int RESIZE_REQUEST_CODE = 2;
    private static final String IMAGE_FILE_NAME = "header.jpg";
    String imageUri;
    Button btn_save;
    ImageView profile_image;
    TextView tv_name,tv_chooseGender,tv_degree,et_phone,et_email;
    EditText et_name,et_school,et_major;
    View view;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.fragment_basicinfo, null);

        initView();
        initListener();

        preferences=getActivity().getSharedPreferences("personalInfo", Context.MODE_PRIVATE);
        editor=preferences.edit();

        et_name.setText(preferences.getString("name", ""));
        et_school.setText(preferences.getString("school", ""));
        et_phone.setText(preferences.getString("phone", ""));
        et_email.setText(preferences.getString("email", ""));
        tv_chooseGender.setText(preferences.getString("gender", ""));
        tv_degree.setText(preferences.getString("degree", ""));
        et_major.setText(preferences.getString("major", ""));

        Bitmap bitmap= BitmapFactory.decodeFile("mnt/sdcard/profile.bmp");
        profile_image.setImageBitmap(bitmap);


        return view;
    }

    private void initListener() {
        tv_chooseGender.setOnClickListener(this);
        profile_image.setOnClickListener(this);
        btn_save.setOnClickListener(this);
        tv_degree.setOnClickListener(this);
    }

    private void initView() {
        tv_chooseGender= (TextView) view.findViewById(R.id.tv_chooseGender);
        tv_name= (TextView) view.findViewById(R.id.tv_name);
        btn_save= (Button) view.findViewById(R.id.btn_saveInfo);
        profile_image= (ImageView) view.findViewById(R.id.profile_image);
        et_name= (EditText) view.findViewById(R.id.et_name);
        et_school= (EditText) view.findViewById(R.id.et_school);
        tv_degree= (TextView) view.findViewById(R.id.tv_degree);
        et_phone=(EditText) view.findViewById(R.id.et_phone);
        et_email=(EditText) view.findViewById(R.id.et_email);
        et_major=(EditText) view.findViewById(R.id.et_major);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.profile_image:

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
                                editor.putString("imageUri", imageUri);
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
                builder.setNegativeButton("取消", null);
                builder.show();
                break;
            case R.id.tv_chooseGender:
                builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("请选择性别");
                builder.setSingleChoiceItems(new String[]{"男", "女"}, 0,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                if (which == 0) {
                                    tv_chooseGender.setText("男");
                                } else {
                                    tv_chooseGender.setText("女");
                                }
                                dialog.dismiss();
                            }});
                builder.show();
                break;
            case R.id.tv_degree:
                builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("请选择最高学历");
                builder.setSingleChoiceItems(new String[]{"中专生", "大专生","本科生","本科生","博士生"}, 0,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                if (which == 0) {
                                    tv_degree.setText("中专");
                                } else if(which==1){
                                    tv_degree.setText("大专生");
                                }else if(which==2){
                                    tv_degree.setText("本科生");
                                }else if(which==3){
                                    tv_degree.setText("本科生");
                                }else {
                                    tv_degree.setText("博士生");
                                }
                                dialog.dismiss();
                            }});
                builder.show();
                break;



            case R.id.btn_saveInfo:
                Toast.makeText(getActivity(),"信息保存成功!",Toast.LENGTH_SHORT).show();
                editor.putString("name", String.valueOf(et_name.getText()));
                editor.putString("school", String.valueOf(et_school.getText()));
                editor.putString("phone", String.valueOf(et_phone.getText()));
                editor.putString("email", String.valueOf(et_email.getText()));
                editor.putString("gender", String.valueOf(tv_chooseGender.getText()));
                editor.putString("degree", String.valueOf(tv_degree.getText()));
                editor.putString("major", String.valueOf(et_major.getText()));

                editor.commit();
                break;
        }
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
            try {
                File f = new File("mnt/sdcard/profile.bmp");
                FileOutputStream fileOutputStream=new FileOutputStream(f);
                if (photo != null) {
                    photo.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                    fileOutputStream.flush();
                    fileOutputStream.close();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

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
