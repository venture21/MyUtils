package com.venture.android.myutils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.venture.android.myutils.FiveFragment.OnListFragmentInteractionListener;
import com.venture.android.myutils.dummy.DummyContent.DummyItem;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final Context context;
    private final List<String> datas = new ArrayList<>();
    private final OnListFragmentInteractionListener mListener;

    public MyItemRecyclerViewAdapter(Context context, OnListFragmentInteractionListener listener) {
        // 폰에서 이미지를 가져온후 datas에 셋팅한다.
        this.context = context;

        ContentResolver resolver = context.getContentResolver();
        Uri target = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String projection[] = {MediaStore.Images.Thumbnails.DATA}; // DATA: image 경로가 있는 컬럼명
        Cursor cursor = resolver.query(target, projection, null, null, null);

        if(cursor!=null) {
            while(cursor.moveToNext()) {
                String uriString = cursor.getString(0);
                //Log.d(TAG."uri String==========================="+uriString);
                datas.add(uriString);
            }
            cursor.close();
        }


        mListener = listener;

    }

    // 앨범 Uri 생성
    private static Uri getImageSimple(String album_id) {
        return Uri.parse("content://media/external//albumart/" + album_id);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.imageUri = datas.get(position);
        //holder.imageView.setImageURI((holder.imageUri));
        Glide.with(context)
                .load(holder.imageUri)
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public String imageUri;


        public ViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.id);
            imageUri = null;
            imageView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                //    Intent intent = (context, DetailActivity.class);
                //    context.startActivity(intent);
                }
            });
        }



    }
}
