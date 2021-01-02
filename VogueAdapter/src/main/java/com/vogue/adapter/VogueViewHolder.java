package com.vogue.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

/**
 *  时间：2020年10月16日06:07:06
 * @param <T>
 * @param <B>
 */
public class VogueViewHolder<T, B extends ViewDataBinding> extends RecyclerView.ViewHolder{

    public final B binding;

    public VogueViewHolder(@NonNull View itemView) {
        super(itemView);
        //绑定视图
        binding = DataBindingUtil.getBinding(itemView);
        itemView.setTag(this);
    }

    public VogueViewHolder(ViewGroup viewGroup, int layoutId) {
        super(DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), layoutId, viewGroup, false).getRoot());
        //绑定视图
        binding = DataBindingUtil.getBinding(itemView);
        itemView.setTag(this);
    }

    /**
     * 静态构造器
     * @param itemView 视图对象
     * @return
     */
    @NonNull
    public static VogueViewHolder createViewHolder(@NonNull View itemView) {
        return new VogueViewHolder(itemView);
    }

    /**
     * 静态构造器
     * @param viewGroup 视图组
     * @param layoutId 视图Layout ID
     * @return
     */
    @NonNull
    public static VogueViewHolder createViewHolder(@NonNull ViewGroup viewGroup,@LayoutRes int layoutId) {
        return new VogueViewHolder(viewGroup,layoutId);
    }

}


