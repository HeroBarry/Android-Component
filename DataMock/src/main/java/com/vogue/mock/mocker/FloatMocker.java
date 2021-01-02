package com.vogue.mock.mocker;


import com.vogue.mock.DataConfig;
import com.vogue.mock.Mocker;
import com.vogue.mock.util.RandomUtils;
import com.vogue.mock.util.StringUtils;

import java.math.BigDecimal;

/**
 * Float对象模拟器
 */
public class FloatMocker implements Mocker<Float> {

  @Override
  public Float mock(DataConfig mockConfig) {
    /**
     * 若根据正则模拟
     */
    if(StringUtils.isNotEmpty(mockConfig.numberRegex())){
      return RandomUtils.nextNumberFromRegex(mockConfig.numberRegex()).setScale(mockConfig.decimalScale(), BigDecimal.ROUND_FLOOR).floatValue();
    }
    return new BigDecimal(RandomUtils.nextFloat(mockConfig.floatRange()[0], mockConfig.floatRange()[1])).setScale(mockConfig.decimalScale(),BigDecimal.ROUND_FLOOR).floatValue();
  }
}
