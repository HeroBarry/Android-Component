package com.vogue.mock.mocker;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.vogue.mock.DataConfig;
import com.vogue.mock.Mocker;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * LocalDateTime对象模拟器
 */

@RequiresApi(api = Build.VERSION_CODES.O)
public class LocalDateTimeMocker implements Mocker<LocalDateTime> {
  private DateMocker dateMocker = new DateMocker();
  @Override
  public LocalDateTime mock(DataConfig mockConfig) {
     Date date = dateMocker.mock(mockConfig);
     return LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
  }
}
