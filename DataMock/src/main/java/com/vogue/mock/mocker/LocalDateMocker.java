package com.vogue.mock.mocker;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.vogue.mock.DataConfig;
import com.vogue.mock.Mocker;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * LocalDate对象模拟器
 */
@RequiresApi(api = Build.VERSION_CODES.O)
public class LocalDateMocker implements Mocker<LocalDate> {
  private LocalDateTimeMocker localDateTimeMocker = new LocalDateTimeMocker();

  @Override
  public LocalDate mock(DataConfig mockConfig) {
      LocalDateTime dateTime = localDateTimeMocker.mock(mockConfig);
     return LocalDate.of(dateTime.getYear(),dateTime.getMonth(),dateTime.getDayOfMonth());
  }
}
