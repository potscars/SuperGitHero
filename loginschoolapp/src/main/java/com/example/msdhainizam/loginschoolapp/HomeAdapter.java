package com.example.msdhainizam.loginschoolapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by MSD Hainizam on 1/26/2016.
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolderData>{

    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private ArrayList<Data> mDataList;
    private static MyClickListener myClickListener;
    private LayoutInflater mInflater;
    private Context context;
    private ImageLoader mImageLoader;
    private VolleySingleTon volleySingleTon;

    public HomeAdapter(Context context) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
        volleySingleTon = VolleySingleTon.getsInstance();
        mImageLoader = volleySingleTon.getmImageLoader();
    }

    public void setData(ArrayList<Data> mDataList) {
        this.mDataList = mDataList;
        notifyDataSetChanged();
    }

    public ViewHolderData onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.announcement_itemlist, parent, false);
        ViewHolderData dataObjectHolder = new ViewHolderData(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolderData holder, int position) {

        final String title = mDataList.get(position).getTitle();
        final String content = mDataList.get(position).getContent();
        final String urlImage = mDataList.get(position).getSchoolImage();

        holder.mTitle.setText(mDataList.get(position).getTitle());
        holder.mContent.setText(mDataList.get(position).getContent());

        //take the url string and pass to loadimages method
        //loadImages(urlImage, holder);

        Glide.with(holder.mCircleImageView.getContext())
                .load(urlImage)
                .error(R.drawable.no_image_available)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter()
                .centerCrop()
                .into(holder.mCircleImageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnnouncementDetailFragment announcementDetailFragment =
                        new AnnouncementDetailFragment().newInstance(title, content, urlImage);
                ((HomeActivity)context).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame, announcementDetailFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    private void loadImages(String urlImage, final ViewHolderData holder) {
        if(!urlImage.equals("Nothing available") && !urlImage.isEmpty()) {
            mImageLoader.get(urlImage, new ImageLoader.ImageListener() {
                @Override
                public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                    holder.mCircleImageView.setImageBitmap(response.getBitmap());
                }

                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
        } else {
            holder.mCircleImageView.setImageResource(R.drawable.yui_small2);
        }
    }

    public void addItem(Data dataObj, int index) {
        mDataList.add(dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mDataList.remove(index);
        notifyItemRemoved(index);
    }

    public int getItemCount() {
        return mDataList.size();
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }

    static class ViewHolderData extends RecyclerView.ViewHolder implements View.OnClickListener {

        public View itemView;
        public TextView mTitle;
        public TextView mContent;
        public CircleImageView mCircleImageView;

        public ViewHolderData(View itemView) {
            super(itemView);

            this.itemView = itemView;
            mTitle = (TextView)itemView.findViewById(R.id.tvTitle);
            mContent = (TextView) itemView.findViewById(R.id.tvContent);
            mCircleImageView = (CircleImageView) itemView.findViewById(R.id.circleImageView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);

        }
    }
}
