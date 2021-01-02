package com.vogue.mock.mocker;


import android.os.Build;

import androidx.annotation.RequiresApi;

import com.vogue.mock.DataConfig;
import com.vogue.mock.Mocker;
import com.vogue.mock.util.RandomUtils;

import java.time.LocalTime;

/**
 * LocalTime对象模拟器
 */
@RequiresApi(api = Build.VERSION_CODES.O)
public class LocalTimeMocker implements Mocker<LocalTime> {

  @Override
  public LocalTime mock(DataConfig mockConfig) {
      int[] timeRange = mockConfig.timeRange();
      int randomHour = RandomUtils.nextInt(timeRange[0],timeRange[1]);
      int randomMinute = RandomUtils.nextInt(timeRange[2],timeRange[3]);
      int randomSecond = RandomUtils.nextInt(timeRange[4],timeRange[5]);
     return  LocalTime.of(randomHour,randomMinute,randomSecond);
  }
}
