package com.example.msdhainizam.loginschoolapp;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by IGWMobileTeam on 19/01/2016.
 */
public class VolleySingleTon {

    private static VolleySingleTon mInstance = null;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    private VolleySingleTon() {
        mRequestQueue = Volley.newRequestQueue(MyApplication.getAppContext());
        mImageLoader = new ImageLoader(mRequestQueue, new ImageLoader.ImageCache() {
            private LruCache<String, Bitmap> cache = new LruCache<>((int)(Runtime.getRuntime()
                    .maxMemory()/1024/8));

            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                cache.put(url, bitmap);
            }
        });
    }

    public static  VolleySingleTon getsInstance() {
        if (mInstance == null) {
            mInstance = new VolleySingleTon();
        }

        return mInstance;
    }

    public RequestQueue getmRequestQueue () {
        return mRequestQueue;
    }

    public ImageLoader getmImageLoader() {
        return mImageLoader;
    }
}
