package com.vogue.adapter;

import androidx.databinding.ViewDataBinding;

/**
 * 对外提供 ViewDataBinding和数据对象 暴露接口
 * @param <T>
 * @param <B>
 */
public interface ViewHolderDelegate<T,B extends ViewDataBinding> {

    void onBindingView(B binding, T data, int position);

}

