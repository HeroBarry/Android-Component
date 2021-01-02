package com.vogue.mock.mocker;


import com.vogue.mock.DataConfig;
import com.vogue.mock.Mocker;
import com.vogue.mock.util.RandomUtils;

/**
 * Character对象模拟器
 */
public class CharacterMocker implements Mocker<Character> {

  @Override
  public Character mock(DataConfig mockConfig) {
    char[] charSeed = mockConfig.charSeed();
    return charSeed[RandomUtils.nextInt(0, charSeed.length)];
  }

}
