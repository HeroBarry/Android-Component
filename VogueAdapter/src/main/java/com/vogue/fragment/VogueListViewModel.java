package com.vogue.fragment;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class VogueListViewModel<T> extends AndroidViewModel {

    protected MutableLiveData<List<T>> list;
    protected MutableLiveData<String> errorMsg;

    protected int page=1;

    public VogueListViewModel(@NonNull Application application) {
        super(application);
        errorMsg=new MutableLiveData<>();
        list=new MutableLiveData<>();
    }

    public MutableLiveData<List<T>> getList() {
        return list;
    }

    public MutableLiveData<String> getErrorMsg() {
        return errorMsg;
    }


    public void loadData(boolean refresh){

        if (refresh){
            //刷新参数初始化
            page=1;
        }else {
            //加载更多参数赋值
            page++;
        }
    }

}
