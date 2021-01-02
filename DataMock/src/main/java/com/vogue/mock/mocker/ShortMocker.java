package com.vogue.mock.mocker;


import com.vogue.mock.DataConfig;
import com.vogue.mock.Mocker;
import com.vogue.mock.util.RandomUtils;
import com.vogue.mock.util.StringUtils;

/**
 * 模拟Short对象
 */
public class ShortMocker implements Mocker<Short> {

  @Override
  public Short mock(DataConfig mockConfig) {
    /**
     * 若根据正则模拟
     */
    if(StringUtils.isNotEmpty(mockConfig.numberRegex())){
      return RandomUtils.nextNumberFromRegex(mockConfig.numberRegex()).shortValue();
    }
    return (short) RandomUtils.nextInt(mockConfig.shortRange()[0], mockConfig.shortRange()[1]);
  }

}
