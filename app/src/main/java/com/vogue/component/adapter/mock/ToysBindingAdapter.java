package com.vogue.component.adapter.mock;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BaseObservable;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.pnikosis.materialishprogress.ProgressWheel;

/**
 * MVVM 统一适配器：图片、开关等view的数据绑定
 */
public class ToysBindingAdapter extends BaseObservable {

    /**
     * 图片加载绑定器
     * @param articleImage
     * @param url
     * @param progressWheel
     */
    @BindingAdapter(value = {"src", "progressIndicator"})
    public static void bindArticleImage(ImageView articleImage, String url, final ProgressWheel progressWheel) {
        if (!TextUtils.isEmpty(url)) {
            Glide.with(articleImage.getContext()).load(url).into(articleImage);
            progressWheel.setVisibility(View.GONE);
        }
    }




}