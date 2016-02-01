package com.example.msdhainizam.loginschoolapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

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

    public HomeAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    public void setData(ArrayList<Data> mDataList) {
        this.mDataList = mDataList;
        notifyDataSetChanged();
    }

    public ViewHolderData onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.announce_itemlist, parent, false);
        ViewHolderData dataObjectHolder = new ViewHolderData(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolderData holder, int position) {

        holder.mTitle.setText(mDataList.get(position).getTitle());
        holder.mContent.setText(mDataList.get(position).getContent());
        holder.mCircleImageView.setImageResource(mDataList.get(position).getSchoolImage());

        Glide.with(holder.mCircleImageView.getContext())
                .load(mDataList.get(position).getSchoolImage())
                .fitCenter()
                .into(holder.mCircleImageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, Message_detail.class);
                context.startActivity(intent);
            }
        });
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
