package com.party.giftzzle.service.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by grahul on 15-05-2018.
 */
public class CommonUtil {

  public static boolean birthDateValidation(Date birthDay) throws ParseException {
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date today = dateFormat.parse(dateFormat.format(new Date()));
    return birthDay.compareTo(today) >= 0;
  }

  public static Date stringToDate(String inputDate, String pattern) throws ParseException {
    SimpleDateFormat sdf = new SimpleDateFormat(pattern);
    return sdf.parse(inputDate);
  }

}
