package com.vogue.fragment;

import androidx.lifecycle.AndroidViewModel;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 泛型类获取器
 */
public class ObtainGenericClassUtil {

    /**
     * 获取泛型ViewModel的class对象
     */
    public static <T> Class<T> getViewModel(Object obj) {
        Class<?> currentClass = obj.getClass();
        Class<T> tClass = getGenericClass(currentClass, AndroidViewModel.class);
        if (tClass == null || tClass == AndroidViewModel.class) {
            //获取父级类的泛型类
            Class<T> classS = getGenericClass(currentClass.getSuperclass(), AndroidViewModel.class);
            if (classS == null || classS == AndroidViewModel.class) {
                return null;
            }else {
                tClass=classS;
            }

        }
        return tClass;
    }

    private static <T> Class<T> getGenericClass(Class<?> klass, Class<?> filterClass) {
        Type type = klass.getGenericSuperclass();
        if (type == null || !(type instanceof ParameterizedType)) return null;
        ParameterizedType parameterizedType = (ParameterizedType) type;
        Type[] types = parameterizedType.getActualTypeArguments();
        for (Type t : types) {
            Class<T> tClass = (Class<T>) t;
            if (filterClass.isAssignableFrom(tClass)) {
                return tClass;
            }
        }
        return null;
    }
}
