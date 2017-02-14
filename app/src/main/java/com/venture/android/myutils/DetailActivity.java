package com.venture.android.myutils;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    MainActivity activity;

    List<String> datas = new ArrayList<>();

    public void setActivity(MainActivity activity){
        this.activity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView imageView = (ImageView) findViewById(R.id.imageView);


        // 1. 리스트에서 넘어온 인텐트를 꺼낸다.
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        //2 . 넘어온 인텐트에서 특정 값을 타입에 맞춰서 꺼낸다
        String imageUri = bundle.getString("imageUri");
        Integer width   = bundle.getInt("width");
        Integer height  = bundle.getInt("height");
        Log.d("test===================",imageUri);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize=2;


        Bitmap bitmap = BitmapFactory.decodeFile(imageUri, options);
        imageView.setImageBitmap(bitmap);



        imageView.setOnClickListener(clickListener);
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            activity.backToList();

        }
    };
}
