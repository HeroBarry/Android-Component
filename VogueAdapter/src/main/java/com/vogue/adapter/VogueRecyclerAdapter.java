package com.vogue.adapter;

import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.collection.SparseArrayCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 *  Recycler基础适配器
 *  时间：2020年10月16日06:07:06
 */
public class VogueRecyclerAdapter<T> extends RecyclerView.Adapter<VogueViewHolder> {

    protected List<T> list;

    private SparseArrayCompat<ViewHolderDelegate> delegates = new SparseArrayCompat();

    /**
     * 初始化有数据 构造方法
     * @param list
     */
    public VogueRecyclerAdapter(List<T> list) {
        this.list = list;
    }

    /**
     * 暴露接口，下拉刷新时，通过暴露方法将数据源置为空
     * @param newData 新的数据
     */
    public void refreshData(List<T> newData) {
        list.clear();
        list.addAll(newData);
        notifyDataSetChanged();
    }

    /**
     * 暴露接口，上拉加载时，通过暴露方法将数据添加进来
     * @param newData 下一页数据
     */
    public void loadMoreData(List<T> newData) {
        int positionStart=list.size();
        if (newData != null) {
            list.addAll(newData);
        }

        notifyItemRangeChanged(positionStart,newData.size());
    }

    /**
     * 获取列表中某位置的对象
     * @param position
     * @return
     */
    public T getItemByPosition(int position){
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItemByPosition(position).hashCode();
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size():0;
    }

    @NonNull
    @Override
    public VogueViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return VogueViewHolder.createViewHolder(parent,viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull VogueViewHolder holder, int position, @NonNull List<Object> payloads) {
        //TODO 怎么解决刷新闪烁？？？
        if (payloads.isEmpty()){
            onBindViewHolder(holder,position);
        }else {
            super.onBindViewHolder(holder, position, payloads);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull VogueViewHolder holder, int position) {
        onBindingView(holder,getItemByPosition(position),position);
    }

    @Override
    public int getItemViewType(int position) {
        T o=getItemByPosition(position);
        int delegatesCount = delegates.size();
        for (int i = delegatesCount - 1; i >= 0; i--) {
            ViewHolderDelegate holder=delegates.valueAt(i);

            if (GenericUtil.getInterfaceGeneric(holder,0).getName().equals(o.getClass().getName())) {
                return delegates.keyAt(i);
            }
        }
        return super.getItemViewType(position);
    }

    /**
     *
     * @param viewType 视图类型 必须是 Layout ID
     * @param holder ViewHolder 委托 将对象和ViewDataBinding传进来
     * @return 适配器自己
     */
    public VogueRecyclerAdapter addViewHolder(@LayoutRes int viewType, ViewHolderDelegate holder) {
        if (delegates.get(viewType) != null) {
            throw new IllegalArgumentException("这个视图类型已经添加过 检查一下适配器的相关代码");
        }
        delegates.put(viewType, holder);
        return this;
    }

    /**
     * 视图和数据绑定
     * @param holder 适配器的holder
     * @param item 数据对象
     * @param position 数据对象在列表中的位置
     */
    private void onBindingView(@NonNull VogueViewHolder holder, T item, int position) {

        int delegatesCount = delegates.size();

        for (int i = 0; i < delegatesCount; i++) {
            ViewHolderDelegate delegate = delegates.valueAt(i);

            if (GenericUtil.getInterfaceGeneric(delegate,0).getName().equals(item.getClass().getName())) {
                delegate.onBindingView(holder.binding, item, position);
                return;
            }
        }

        throw new IllegalArgumentException("ViewHolderDelegate泛型没有找到合适的对象 与 ViewDataBinding 进行绑定 再检查一下");
    }
}
