package com.vogue.mock.mocker;


import com.vogue.mock.DataConfig;
import com.vogue.mock.Mocker;
import com.vogue.mock.util.RandomUtils;
import com.vogue.mock.util.StringUtils;

/**
 * Byte对象模拟器
 */
public class ByteMocker implements Mocker<Byte> {

  @Override
  public Byte mock(DataConfig mockConfig) {
    /**
     * 若根据正则模拟
     */
    if(StringUtils.isNotEmpty(mockConfig.numberRegex())){
      return RandomUtils.nextNumberFromRegex(mockConfig.numberRegex()).byteValue();
    }
    return (byte) RandomUtils.nextInt(mockConfig.byteRange()[0], mockConfig.byteRange()[1]);
  }

}
