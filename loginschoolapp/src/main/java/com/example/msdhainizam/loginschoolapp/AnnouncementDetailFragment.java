package com.example.msdhainizam.loginschoolapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by IGWMobileTeam on 02/02/2016.
 */
public class AnnouncementDetailFragment extends Fragment {

    private String mTitle, mContent, mUrlImage;
    private ImageView imageHeader;
    private TextView detailTitle, detailContent;
    private CollapsingToolbarLayout collapsingToolbarLayout;

    public AnnouncementDetailFragment() {
        setRetainInstance(true);
    }

    public static AnnouncementDetailFragment newInstance(String title, String content, String bitmap) {

        Bundle args = new Bundle();

        /*ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] bytes = stream.toByteArray();*/

        AnnouncementDetailFragment fragment = new AnnouncementDetailFragment();
        args.putString("title", title);
        args.putString("content", content);
        args.putString("bitmapUrl", bitmap);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.announcement_detail_fragment, container, false);

        imageHeader = (ImageView) view.findViewById(R.id.backdrop);
        detailTitle = (TextView) view.findViewById(R.id.detailTitle);
        detailContent = (TextView) view.findViewById(R.id.detailContent);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mTitle = getArguments().getString("title");
        mContent = getArguments().getString("content");
        mUrlImage = getArguments().getString("bitmapUrl");

        Glide.with(this).load(mUrlImage)
                .thumbnail(0.25f)
                .error(R.drawable.no_image_available)
                .into(imageHeader);

        collapsingToolbarLayout = (CollapsingToolbarLayout)
                view.findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("Message Detail");
        collapsingToolbarLayout.setExpandedTitleColor(ContextCompat.getColor(
                MyApplication.getAppContext(), android.R.color.transparent));

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getActivity().getMenuInflater().inflate(R.menu.menu_message_detail, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
