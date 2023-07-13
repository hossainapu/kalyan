package com.hellokoding.auth.common;

import com.hellokoding.auth.model.dto.District;
import com.hellokoding.auth.model.dto.Division;
import com.hellokoding.auth.model.dto.Thana;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static final String regex = "\\+?(88)?01[3-9][0-9]{8}\\b";

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

    public static final boolean isValidDivision(String name) {
        if(isEmpty(name)) {
            return false;
        }
        if(Defs.divisionsLookup != null && Defs.divisionsLookup.size() >0) {
            Optional<Division> div = Defs.divisionsLookup.stream().filter(division -> division != null && !Utils.isEmpty(division.getNameBn()) && division.getNameBn().equalsIgnoreCase(name)).findFirst();
            return div.isPresent();
        }
        return false;
    }

    public static final boolean isValidDistrict(String name) {
        if(isEmpty(name)) {
            return false;
        }

        if(Defs.districtsLookup != null && Defs.districtsLookup.size() >0) {
            Optional<District> dis = Defs.districtsLookup.stream().filter(district -> district != null && !Utils.isEmpty(district.getNameBn()) && district.getNameBn().equalsIgnoreCase(name.trim())).findFirst();
            return dis.isPresent();
        }
        return false;
    }

    public static final boolean isValidThana(String name) {
        if(isEmpty(name)) {
            return false;
        }
        if(Defs.thanasLookup != null && Defs.thanasLookup.size() >0) {
            Optional<Thana> tha = Defs.thanasLookup.stream().filter(thana -> thana != null && !Utils.isNull(thana.getId()) && !thana.getNameBn().equalsIgnoreCase(name.trim())).findFirst();
            return tha.isPresent();
        }
        return false;
    }

    public static final List<SelectItem> getDivisions() {
        List<SelectItem> items = new ArrayList<>();
        if(Defs.divisionsLookup != null && Defs.divisionsLookup.size() >0) {
            Defs.divisionsLookup.stream().forEach(division -> {
                items.add(new SelectItem(division));
            });
        }

        return items;
    }

    public static final List<SelectItem> getDistricts(Integer divisionId) {
        List<SelectItem> items = new ArrayList<>();
        if(!isNull(divisionId) && !Defs.districtsLookup.isEmpty()) {
            Defs.districtsLookup.stream().filter(district -> district != null && district.getDivisionId() == divisionId).forEach(district -> {
                items.add(new SelectItem(district));
            });
        }
        return items;
    }

    public static final List<SelectItem> getThanas(Integer districtId) {
        List<SelectItem> items = new ArrayList<>();
        if(!isNull(districtId) && !Defs.thanasLookup.isEmpty()) {
            Defs.thanasLookup.stream().filter(thana -> thana != null && thana.getDistrictId() == districtId).forEach(thana -> {
                items.add(new SelectItem(thana));
            });
        }
        return items;
    }

    public static final List<SelectItem> getDistricts(String name) {
        if(isEmpty(name)) {
            return new ArrayList<>();
        }
        Optional<Division> div = Defs.divisionsLookup.stream().filter(d->d != null && d.getNameBn() != null && d.getNameBn().equalsIgnoreCase(name)).findFirst();
        if(div.isPresent()) {
            return getDistricts(div.get().getId());
        }
        return new ArrayList<>();
    }

    public static final List<SelectItem> getThanas(String name) {
        if(isEmpty(name)) {
            return new ArrayList<>();
        }
        Optional<District> dis = Defs.districtsLookup.stream().filter(d->d != null && d.getNameBn() != null && d.getNameBn().equalsIgnoreCase(name)).findFirst();
        if(dis.isPresent()) {
            return getThanas(dis.get().getId());
        }
        return new ArrayList<>();
    }

    public static final boolean isInList(Integer value,Integer ...values) {
        if(value == null || values == null || values.length ==0) {
            return false;
        }

        for (Integer val : values) {
            if(val == null) {
                continue;
            }
            if(val.intValue() == value.intValue()) {
                return true;
            }
        }

        return false;

    }

    public static final boolean isInList(String value,String ...values) {
        if(isEmpty(value) || values == null || values.length ==0) {
            return false;
        }
        for (String val : values) {
            if(Utils.isEmpty(val)) {
                continue;
            }
            if(val.equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
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

        if(value instanceof String) {
            try {
                return Integer.parseInt((String) value);
            } catch (NumberFormatException ne) {
                return null;
            }
        }

        return 0;

    }

    public static final String dateToString(Date date) {
        if(date == null) {
            return null;
        }

        DateFormat df = new SimpleDateFormat(Defs.DATE_FORMAT);
        return df.format(date);
    }

    public static final boolean isValidMobile(String mobile) {
        if(isEmpty(mobile)) {
            return false;
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher result = pattern.matcher(mobile);
        return result.matches();

    }

    public static final Date getDateFromString(String date) {
        if(isEmpty(date)) {
            return null;
        }

        DateFormat df = new SimpleDateFormat(Defs.DATE_FORMAT);
        try {
            return df.parse(date);
        } catch (ParseException pe) {
            return null;
        }
    }

    public static final String getStringDate(String date,String format) {
        if(isEmpty(date)) {
            return null;
        }

        DateFormat df = new SimpleDateFormat(Defs.DATE_FORMAT);
        DateFormat dfs = new SimpleDateFormat(format);
        try {
            return dfs.format(df.parse(date));
        } catch (ParseException pe) {
            return null;
        }
    }

    public static final String buildMessage(String msg, int type) {
        String finalString = "";
        if (type == 1) {
            finalString = "<label style=\"color:green;\">&#10004; " + msg + "</label>";
        } else if (type == 2) {
            finalString = "<label style=\"color:red;\">&#10006; " + msg + "</label>";
        } else if (type == 3) {
            finalString = "<label style=\"color:yellow;\">&#9432; " + msg + "</label>";
        }

        return finalString;
    }

    public static synchronized final String getUUID(String dateOfBirth) {
        String value = dateOfBirth.replaceAll("/","");
        return System.currentTimeMillis()+value;
    }

    public static final boolean isValidLength(String value,int len) {
        if(isEmpty(value)) {
            return true;
        }
        if(value.length() >len) {
            return false;
        }
        return true;
    }

    public static final Date getDateParam(String value,boolean isMax) {
        Date date = getDateFromString(value);
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+6"));
        cal.setTime(date);
        if (isMax) {
            cal.set(Calendar.HOUR_OF_DAY,23);
            cal.set(Calendar.MINUTE,59);
            cal.set(Calendar.MILLISECOND,999);
        } else {
            cal.set(Calendar.HOUR_OF_DAY,0);
            cal.set(Calendar.MINUTE,0);
            cal.set(Calendar.MILLISECOND,1);
        }

        return cal.getTime();
    }

    public static final String getMd5(String value) {
        return DigestUtils.md5DigestAsHex(value.getBytes());
    }

    public static final boolean indexValueExists(Object[] values,int index) {
        if(values == null || values.length <index+1) {
            return false;
        }

        if(values[index] == null) {
            return false;
        }

        return true;
    }

    public static final List<String> getRoles() {
        return Arrays.asList("ROLE_ADMIN","ROLE_MOBILE","ROLE_ENROLLMENT");
    }


    public static final String getUUID() {
        return UUID.randomUUID().toString();
    }


    /*
    public static void main(String args[]) {
        System.out.println(getMd5("Abc@123"));
    }
    */

    public static final String convertBanglatoUnicode(String banglaText) {

        StringBuilder sb = new StringBuilder();

        for(char c : banglaText.toCharArray()){
            String value = Integer.toHexString((int)c);

            sb.append(StringUtils.leftPad(value, 4, '0'));
        }

        String unicode = sb.toString().toUpperCase();

        return unicode;

    }

}
