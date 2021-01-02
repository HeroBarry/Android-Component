package com.vogue.mock.mocker;


import com.vogue.mock.DataConfig;
import com.vogue.mock.Mocker;
import com.vogue.mock.util.RandomUtils;
import com.vogue.mock.util.StringUtils;

/**
 * 模拟Long对象
 */
public class LongMocker implements Mocker<Long> {

  @Override
  public Long mock(DataConfig mockConfig) {
    /**
     * 若根据正则模拟
     */
    if(StringUtils.isNotEmpty(mockConfig.numberRegex())){
      return RandomUtils.nextNumberFromRegex(mockConfig.numberRegex()).longValue();
    }
    return RandomUtils.nextLong(mockConfig.longRange()[0], mockConfig.longRange()[1]);
  }

}
