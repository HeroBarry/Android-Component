package com.vogue.mock.mocker;


import com.vogue.mock.DataConfig;
import com.vogue.mock.Mocker;
import com.vogue.mock.util.RandomUtils;
import com.vogue.mock.util.StringUtils;

/**
 * Integer对象模拟器
 */
public class IntegerMocker implements Mocker<Integer> {

  @Override
  public Integer mock(DataConfig mockConfig) {
    /**
     * 若根据正则模拟
     */
    if(StringUtils.isNotEmpty(mockConfig.numberRegex())){
      return RandomUtils.nextNumberFromRegex(mockConfig.numberRegex()).intValue();
    }
    return RandomUtils.nextInt(mockConfig.intRange()[0], mockConfig.intRange()[1]);
  }

}
