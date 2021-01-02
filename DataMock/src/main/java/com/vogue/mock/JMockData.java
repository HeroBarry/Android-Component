package com.vogue.mock;

import com.vogue.mock.mocker.BaseMocker;

/**
 * 模拟对象门面类
 */
@SuppressWarnings("unchecked")
public class JMockData {


  /**
   *
   * 模拟数据
   *
   * @param clazz 模拟数据类型
   * @param <T> v
   * @return 模拟数据对象
   */
  public static <T> T mock(Class<T> clazz) {
    return mock(clazz, new MockConfig());
  }

  /**
   * 模拟数据
   *
   * @param clazz 模拟数据类型
   * @param mockConfig 模拟数据配置
   * @param <T> t
   * @return 模拟数据对象
   */
  public static <T> T mock(Class<T> clazz, MockConfig mockConfig) {
    DataConfig config=mockConfig.getDataConfig(clazz,null);
    return new BaseMocker<T>(clazz).mock(config);
  }

  /**
   * 模拟数据
   * 注意typeReference必须以{}结尾
   * @param typeReference 模拟数据包装类型
   * @param <T> t
   * @return 模拟数据对象
   */
  public static <T> T mock(TypeReference<T> typeReference) {
    return mock(typeReference, new MockConfig());
  }

  /**
   * 模拟数据
   * <pre>
   * 注意typeReference必须以{}结尾
   * </pre>
   *
   * @param typeReference 模拟数据类型
   * @param mockConfig 模拟数据配置
   * @param <T> t
   * @return 模拟数据对象
   */
  public static <T> T mock(TypeReference<T> typeReference, MockConfig mockConfig) {
    mockConfig.init(typeReference.getType());
    DataConfig config=mockConfig.globalDataConfig();
    return new BaseMocker<T>(typeReference.getType()).mock(config);
  }

}
