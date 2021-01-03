package com.vogue.component.adapter.ui.home;

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
import androidx.recyclerview.widget.SimpleItemAnimator;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.vogue.adapter.ViewHolderDelegate;
import com.vogue.adapter.VogueRecyclerAdapter;
import com.vogue.component.R;
import com.vogue.component.adapter.callback.IRecyclerViewItemClickListener;
import com.vogue.component.adapter.mock.NoMoreData;
import com.vogue.component.adapter.mock.Video;
import com.vogue.component.databinding.FragmentHomeBinding;
import com.vogue.component.databinding.NoMoreDataBinding;
import com.vogue.component.databinding.TryCardItemCardBinding;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements OnRefreshLoadMoreListener, IRecyclerViewItemClickListener {

    private HomeViewModel viewModel;
    private FragmentHomeBinding binding;
    protected boolean refresh=true;
    private VogueRecyclerAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel =new ViewModelProvider(this).get(HomeViewModel.class);

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        binding.rvList.setLayoutManager(layoutManager);

        adapter=new VogueRecyclerAdapter(new ArrayList());

        adapter.addViewHolder(R.layout.try_card_item_card, new ViewHolderDelegate<Video, TryCardItemCardBinding>() {
            @Override
            public void onBindingView(TryCardItemCardBinding binding, Video data, int position) {
                binding.setData(data);

                binding.setRecyclerViewItemClickListener(HomeFragment.this);
            }
        });

        adapter.addViewHolder(R.layout.no_more_data, new ViewHolderDelegate<NoMoreData, NoMoreDataBinding>() {
            @Override
            public void onBindingView(NoMoreDataBinding binding, NoMoreData data, int position) {
                binding.tvNoMoreMsg.setText(data.getMsg());
            }
        });
        ((SimpleItemAnimator)binding.rvList.getItemAnimator()).setSupportsChangeAnimations(false);
        binding.rvList.getItemAnimator().setChangeDuration(0);
        binding.rvList.setAdapter(adapter);

        binding.srlList.autoRefresh();
        binding.srlList.setNestedScrollingEnabled(true);
        binding.srlList.setOnRefreshListener(this);
        binding.srlList.setOnLoadMoreListener(this);

        viewModel.getErrorMsg().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getActivity(),""+s, Toast.LENGTH_LONG).show();
            }
        });

        viewModel.getList().observe(getViewLifecycleOwner(), new Observer<List<Object>>() {
            @Override
            public void onChanged(List<Object> list) {
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
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        loadData(refresh=false);
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        loadData(refresh=true);
    }

    @Override
    public void onRecyclerViewItemClick(String url, String title) {
        Toast.makeText(requireContext(),title,Toast.LENGTH_SHORT).show();
    }
    private void loadData(boolean refresh){
        RxPermissions rxPermissions=new RxPermissions(requireActivity());
        rxPermissions.request(Manifest.permission.INTERNET).subscribe(havePermission-> {
            if (havePermission){
                viewModel.loadData(refresh);
            }else {
                Toast.makeText(requireContext(),"权限不足，请检查权限",Toast.LENGTH_SHORT).show();
            }
        });
    }
}