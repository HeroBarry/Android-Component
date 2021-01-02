package com.vogue.mock.mocker;


import android.os.Build;

import androidx.annotation.RequiresApi;

import com.vogue.mock.DataConfig;
import com.vogue.mock.Mocker;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Timestamp对象模拟器
 */

@RequiresApi(api = Build.VERSION_CODES.O)
public class TimestampMocker implements Mocker<Timestamp> {
  private DateMocker dateMocker = new DateMocker();
  @Override
  public Timestamp mock(DataConfig mockConfig) {
     Date date = dateMocker.mock(mockConfig);
     return Timestamp.valueOf(date.toString());
  }
}
