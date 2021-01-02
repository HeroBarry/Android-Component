package com.vogue.mock.mocker;

import com.vogue.mock.DataConfig;
import com.vogue.mock.Mocker;

import java.lang.reflect.ParameterizedType;

/**
 * 模拟泛型
 */
public class GenericMocker implements Mocker<Object> {

  private ParameterizedType type;

  GenericMocker(ParameterizedType type) {
    this.type = type;
  }

  @Override
  public Object mock(DataConfig mockConfig) {
    return new BaseMocker(type.getRawType(), type.getActualTypeArguments()).mock(mockConfig);
  }

}
