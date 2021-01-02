package com.vogue.mock.mocker;


import com.vogue.mock.DataConfig;
import com.vogue.mock.Mocker;
import com.vogue.mock.util.RandomUtils;

/**
 * Boolean对象模拟器
 */
public class BooleanMocker implements Mocker<Boolean> {

  @Override
  public Boolean mock(DataConfig mockConfig) {
    boolean[] booleanSeed = mockConfig.booleanSeed();
    return booleanSeed[RandomUtils.nextInt(0, booleanSeed.length)];
  }

}
