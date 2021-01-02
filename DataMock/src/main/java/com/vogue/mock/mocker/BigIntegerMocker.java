package com.vogue.mock.mocker;


import com.vogue.mock.DataConfig;
import com.vogue.mock.Mocker;

import java.math.BigInteger;

/**
 * BigInteger对象模拟器
 */
public class BigIntegerMocker implements Mocker<BigInteger> {
  @Override
  public BigInteger mock(DataConfig mockConfig) {
   return BigInteger.valueOf(mockConfig.globalConfig().getMocker(Long.class).mock(mockConfig));
  }

}
