package com.gt.fjbatresv.devs502.lib;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.gt.fjbatresv.devs502.lib.base.ImageLoader;

/**
 * Created by javie_000 on 8/29/2016.
 */
public class GlideImageLoader implements ImageLoader {
    private RequestManager glideRequestManager;

    public GlideImageLoader(RequestManager glideRequestManager) {
        this.glideRequestManager = glideRequestManager;
    }

    public GlideImageLoader(Context context){
        this.glideRequestManager = Glide.with(context);
    }

    @Override
    public void load(ImageView image, String url) {
        this.glideRequestManager
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(image);
    }
}
