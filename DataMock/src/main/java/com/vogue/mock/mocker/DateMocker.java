package com.vogue.mock.mocker;



import com.vogue.mock.DataConfig;
import com.vogue.mock.MockException;
import com.vogue.mock.Mocker;
import com.vogue.mock.util.DateTool;
import com.vogue.mock.util.RandomUtils;

import java.text.ParseException;
import java.util.Date;

/**
 * Date对象模拟器
 */
public class DateMocker implements Mocker<Date> {
  @Override
  public Date mock(DataConfig mockConfig) {
    try {
      long startTime = DateTool.getString2DateAuto(mockConfig.dateRange()[0]).getTime();
      long endTime = DateTool.getString2DateAuto(mockConfig.dateRange()[1]).getTime();
      return new Date(RandomUtils.nextLong(startTime,endTime));
    } catch (ParseException e) {
      throw new MockException("不支持的日期格式，或者使用了错误的日期", e);
    }
  }

}
