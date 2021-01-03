package com.vogue.component.swipemenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.SimpleItemAnimator;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.listener.OnResultCallbackListener;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.vogue.adapter.ViewHolderDelegate;
import com.vogue.adapter.VogueRecyclerAdapter;
import com.vogue.component.R;
import com.vogue.component.adapter.mock.NoMoreData;
import com.vogue.component.adapter.mock.Video;
import com.vogue.component.databinding.ActivityEasySwipeMenuBinding;
import com.vogue.component.databinding.ItemSwipemenuBinding;
import com.vogue.component.databinding.NoMoreDataBinding;
import com.vogue.component.databinding.TryCardItemCardBinding;

import java.util.ArrayList;
import java.util.List;

public class EasySwipeMenuActivity extends AppCompatActivity  implements OnRefreshLoadMoreListener {

    private EasySwipeMenuModel viewModel;
    private ActivityEasySwipeMenuBinding binding;

    protected boolean refresh=true;
    private VogueRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel =new ViewModelProvider(this).get(EasySwipeMenuModel.class);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_easy_swipe_menu);
        StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        binding.rvList.setLayoutManager(layoutManager);

        adapter=new VogueRecyclerAdapter(new ArrayList());

        adapter.addViewHolder(R.layout.item_swipemenu, new ViewHolderDelegate<Video, ItemSwipemenuBinding>() {
            @Override
            public void onBindingView(ItemSwipemenuBinding binding, Video data, int position) {
                binding.setData(data);
                binding.rightMenu2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        PictureSelector.create(EasySwipeMenuActivity.this)
                                .openGallery(PictureMimeType.ofAll())
                                .loadImageEngine(GlideEngine.createGlideEngine())
                                .forResult(new OnResultCallbackListener<LocalMedia>() {
                                    @Override
                                    public void onResult(List<LocalMedia> result) {
                                        // onResult Callback
                                        Toast.makeText(EasySwipeMenuActivity.this,"----"+result.size(), Toast.LENGTH_LONG).show();
                                    }

                                    @Override
                                    public void onCancel() {
                                        // onCancel Callback
                                    }
                                });
                    }
                });
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

        viewModel.getErrorMsg().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(EasySwipeMenuActivity.this,""+s, Toast.LENGTH_LONG).show();

            }
        });

        viewModel.getList().observe(this, new Observer<List<Object>>() {
            @Override
            public void onChanged(List<Object> list) {
                if (list.isEmpty()) {
                    Toast.makeText(EasySwipeMenuActivity.this, "没有获取到数据", Toast.LENGTH_LONG).show();
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

    private void loadData(boolean refresh){
        RxPermissions rxPermissions=new RxPermissions(this);
        rxPermissions.request(Manifest.permission.INTERNET).subscribe(havePermission-> {
            if (havePermission){
                viewModel.loadData(refresh);
            }else {
                Toast.makeText(this,"权限不足，请检查权限",Toast.LENGTH_SHORT).show();
            }
        });
    }
}