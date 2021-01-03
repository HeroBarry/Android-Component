package com.vogue.component.swipemenu;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.vogue.component.adapter.mock.MockData;

import java.util.List;

public class EasySwipeMenuModel extends AndroidViewModel {

    protected MutableLiveData<List<Object>> list;
    protected MutableLiveData<String> errorMsg;
    protected int page=1;

    public EasySwipeMenuModel(@NonNull Application application) {
        super(application);
        errorMsg=new MutableLiveData<>();
        list=new MutableLiveData<>();
    }

    public MutableLiveData<List<Object>> getList() {
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
        list.setValue(MockData.generateSingleVideoData(page>2));
    }
}
