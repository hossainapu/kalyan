package com.example.polls.util;

import org.springframework.util.DigestUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static final boolean isEmpty(String value) {
        return value == null || value == "" || value.isEmpty();
    }

    public static final boolean isNull(Integer value) {
        return value == null || value.intValue() == 0;
    }

    public static final Timestamp getCurrentTimeStamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    public static final Date getDateFromTimeStamp(Timestamp value) {
        return new Date(value.getTime());
    }



    public static final boolean isValidMobile(String mobileNumber) {
        final String regex = "^(?:\\+88|01)?(?:\\d{11}|\\d{13})$";
        final Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(mobileNumber);
        return m.matches();
    }

    public static final Integer getInteger(Object value) {
        if(value instanceof Integer) {
            return (Integer)value;
        }
        if(value instanceof BigDecimal) {
            return ((BigDecimal)value).intValue();
        }

        if(value instanceof BigInteger) {
            return ((BigInteger)value).intValue();
        }
        if(value instanceof Long) {
            return ((Long)value).intValue();
        }
        if(value instanceof Float) {
            return ((Float)value).intValue();
        }
        if(value instanceof Number) {
            return ((Number)value).intValue();
        }

        return 0;

    }

    public static final Integer getIntegerFromString(String value) {
        try {
            return Integer.parseInt(value);
        } catch (Throwable t) {
            return null;
        }
    }


    public static final Date getDateFromLocalDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }

    public static final String dateToString(Date date) {
        if(date == null) {
            return null;
        }

        DateFormat df = new SimpleDateFormat(AppConstants.DATE_FORMAT);
        return df.format(date);
    }

    public static final String dateToString(Date date,String format) {
        if(date == null) {
            return null;
        }

        DateFormat df = new SimpleDateFormat(AppConstants.BIRTH_DATE_FORMAT);
        return df.format(date);
    }

    public static final List<String> getBloodGroups() {
        return Arrays.asList("এ+","এ-","বি+","বি-","এবি+","এবি-","ও+","ও-");
    }

    public static final List<String> getMaritalStatus() {
        return Arrays.asList("বিবাহিত","অবিবাহিত","তালাকপ্রাপ্ত","বিধবা","অনন্যা");
    }

    public static final List<String> getApplicationTypes() {
        return Arrays.asList("6 SEATER AUTO","2 SEATER AUTO","MANUAL RICKSHAW","BATTERY RICKSHAW");
    }

    public static final LocalDate getLocalDate(Date dateToConvert) {
        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
    }

    public static final boolean isInList(String value,List<String> values) {
        if(isEmpty(value) || values == null || values.isEmpty()) {
            return false;
        }

        return values.stream().filter(v-> !isEmpty(v) && v.equalsIgnoreCase(value)).count() >0;
    }

    public static final List<String> getGender(boolean isSearch) {
        return Arrays.asList("পুরুষ","মহিলা","তৃতীয়");
    }

    public static final List<String> getGender() {
        return Arrays.asList("পুরুষ","মহিলা","তৃতীয়");
    }


    public static final Date getDateParam(Date date,boolean isMaximum) {
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+6"));
        c.setTime(date);
        if(isMaximum) {
            c.set(Calendar.HOUR_OF_DAY,23);
            c.set(Calendar.MINUTE,59);
            c.set(Calendar.MILLISECOND,999);
        } else {
            c.set(Calendar.HOUR_OF_DAY,0);
            c.set(Calendar.MINUTE,0);
            c.set(Calendar.MILLISECOND,0);
        }

        return c.getTime();
    }

    public static final boolean isInList(Integer value,List<Integer> values) {
        if(isNull(value) || values == null || values.isEmpty()) {
            return false;
        }

        return values.stream().filter(v-> !isNull(v) && v.intValue() == value.intValue()).count() >0;
    }

    public static final Date getDateFromString(String value) {
        if(isEmpty(value)) {
            return null;
        }
        DateFormat df = new SimpleDateFormat(AppConstants.DATE_FORMAT);
        try {
            return df.parse(value);
        } catch (Throwable t) {
            return null;
        }
    }

    public static final BufferedImage resizeImage(final Image image, int width, int height) {
        final BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        final Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.setComposite(AlphaComposite.Src);
        //below three lines are for RenderingHints for better image quality at cost of higher processing time
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.drawImage(image, 0, 0, width, height, null);
        graphics2D.dispose();
        return bufferedImage;
    }


    public static final Object getValue(Object[] values,int position) {
        if(values == null) {
            return null;
        }
        if(values.length <= position) {
            return null;
        }
        return values[position];
    }

    public static final String getMd5(String value) {
        return DigestUtils.md5DigestAsHex(value.getBytes());
    }
}
