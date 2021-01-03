package com.vogue.fragment;

import android.Manifest;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.vogue.adapter.VogueRecyclerAdapter;
import com.vogue.adapter.R;
import com.vogue.adapter.databinding.FragmentListBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView 列表公共页面
 * @param <T>
 * @param <V>
 */
public abstract class VogueListFragment<T,V extends VogueListViewModel> extends Fragment implements OnRefreshLoadMoreListener {

    protected List<T> list;
    protected VogueRecyclerAdapter adapter;
    protected FragmentListBinding binding;
    private V viewModel;

    protected boolean refresh=true;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_list,container,false);

        initView();

        return binding.getRoot();

    }

    protected abstract VogueRecyclerAdapter getAdapter();


    protected void initView() {
        //TODO 开放接口 layoutManager和spanCount
        list = new ArrayList<>();
        adapter = getAdapter();
        adapter.setHasStableIds(true);
        binding.rvList.setAdapter(adapter);

        binding.srlList.setNestedScrollingEnabled(true);

        binding.srlList.setOnRefreshListener(this);
        binding.srlList.setOnLoadMoreListener(this);

        initModelView();
    }
    protected void initModelView() {
        Class<V> viewModelClass = ObtainGenericClassUtil.getViewModel(this);
        if (viewModelClass != null) {
            this.viewModel = new ViewModelProvider(this).get(viewModelClass);
        }

        viewModel.getErrorMsg().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getActivity(),""+s, Toast.LENGTH_LONG).show();
            }
        });
        viewModel.getList().observe(getViewLifecycleOwner(), new Observer<List<T>>() {
            @Override
            public void onChanged(List<T> list) {
                if (list.isEmpty()) {
                    Toast.makeText(getActivity(), "没有获取到数据", Toast.LENGTH_LONG).show();
                    binding.srlList.finishRefresh();
                    binding.srlList.finishLoadMore();
                } else {
                    if (list.size()<10){
                        binding.srlList.finishLoadMoreWithNoMoreData();
                        binding.srlList.finishRefreshWithNoMoreData();
                    }else {
                        binding.srlList.finishRefresh();
                        binding.srlList.finishLoadMore();
                    }

                    if (refresh) {
                        adapter.refreshData(list);

                    } else {
                        adapter.loadMoreData(list);
                    }
                }
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (!isLazyLoad()){
            loadData(refresh);
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

        refresh=false;
        refreshLayout.getLayout().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadData(refresh);
            }
        }, 50);
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        refresh=true;
        refreshLayout.getLayout().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadData(refresh);
            }
        }, 50);
    }

    /**
     * @param refresh true 参数重置到初始值 false 加载更多数据
     */
    protected void loadData(boolean refresh) {
        RxPermissions rxPermissions = new RxPermissions(requireActivity());
        rxPermissions.request(Manifest.permission.INTERNET).subscribe(aBoolean -> {
            if (aBoolean) {
                viewModel.loadData(refresh);
            } else {
                Toast.makeText(getActivity(), "权限不够，检查是否提供了相应权限", Toast.LENGTH_LONG).show();
            }
        });
    }
    /**
     * 是否懒加载
     */
    protected boolean isLazyLoad() {
        return false;
    }
}

