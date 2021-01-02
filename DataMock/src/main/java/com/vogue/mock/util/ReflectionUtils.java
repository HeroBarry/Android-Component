package com.vogue.mock.util;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 反射工具类
 */
@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public final class ReflectionUtils {

  private static ConcurrentHashMap<Class<?>, List<Field>> fieldCache = new ConcurrentHashMap<>();
  private static ConcurrentHashMap<Class<?>, List<Method>> methodCache = new ConcurrentHashMap<>();

  private ReflectionUtils() {
  }

  /**
   * 反射设置值
   *
   * @param object 对象
   * @param method 方法
   * @param args 方法参数对象
   * @throws ReflectiveOperationException 反射操作异常
   */
  public static void setRefValue(Object object, Method method, Object... args) throws ReflectiveOperationException {
    method.invoke(object, args);
  }

  /**
   * 反射设置值
   *
   * @param object 对象
   * @param field 属性
   * @param value 属性值
   */
  public static void setFieldValue(Object object, Field field, Object value) {

    try {
      field.setAccessible(true);
      field.set(object, value);
    }catch (ReflectiveOperationException e){
    }

  }

  /**
   * 获取全部属性
   * @param clazz c
   * @return g
   */
  public static List<Field> getFields(Class<?> clazz) {
    List<Field> fields = fieldCache.get(clazz);

    if (fields == null) {
      fields = new ArrayList<>();
      Class<?> superClass = clazz;
      while (superClass != Object.class) {
        List<Field> fieldList=new ArrayList<>();

        for (Field field:superClass.getDeclaredFields()){
          if (!field.toString().contains(" static ")){

            fieldList.add(field);
          }
        }

        fields.addAll(fieldList);
        superClass = superClass.getSuperclass();
      }
      fieldCache.put(clazz, fields);
    }
    return fields;
  }

  /**
   * 获取全部方法
   * @param clazz c
   * @return g
   */
  public static List<Method> getMethods(Class<?> clazz) {
    List<Method> methods = methodCache.get(clazz);

    if (methods == null) {
      methods = new ArrayList<>();
      Class<?> superClass = clazz;
      while (superClass != Object.class) {
        methods.addAll(Arrays.asList(superClass.getDeclaredMethods()));
        superClass = superClass.getSuperclass();
      }
      methodCache.put(clazz, methods);
    }
    return methods;
  }


  /**
   * 有setter方法的字段及其setter方法
   *
   * @param clazz Class对象
   * @return 有setter方法的 字段及其setter方法
   */
  public static Map<Field, Method> fieldAndSetterMethod(Class clazz) {

    Map<Field, Method> map = new LinkedHashMap<>();

    List<Field> fields =getFields(clazz);
    List<Method> methods =getMethods(clazz);

    for (Field field:fields){

      for (Method method:methods){
        if (method.getName().toLowerCase().equals("set"+field.getName().toLowerCase())){
          map.put(field,method);
        }
      }
    }

    return map;
  }

}
