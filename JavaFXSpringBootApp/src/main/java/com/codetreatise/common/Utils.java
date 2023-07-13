package com.codetreatise.common;

import com.codetreatise.dto.District;
import com.codetreatise.dto.Division;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import org.springframework.util.DigestUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.List;
import java.util.function.UnaryOperator;
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
        Optional<Division> div = Defs.divisionsLookup.stream().filter(d->d != null && d.getNameEn() != null && d.getNameEn().equalsIgnoreCase(name)).findFirst();
        if(div.isPresent()) {
            return getDistricts(div.get().getId());
        }
        return new ArrayList<>();
    }

    public static final List<SelectItem> getThanas(String name) {
        if(isEmpty(name)) {
            return new ArrayList<>();
        }
        Optional<District> dis = Defs.districtsLookup.stream().filter(d->d != null && d.getNameEn() != null && d.getNameEn().equalsIgnoreCase(name)).findFirst();
        if(dis.isPresent()) {
            return getThanas(dis.get().getId());
        }
        return new ArrayList<>();
    }

    public static final SelectItem getSelectedItem(List<SelectItem> items,String value){
        if(items != null&& value != null) {
            return items.stream().filter(item-> item != null && item.getName().equalsIgnoreCase(value)).findFirst().get();
        }
        return null;
    }


    public static void missingAlert(String field){
        if(!isEmpty(field)) {
            field = Character.toUpperCase(field.charAt(0)) + field.substring(1);
        }
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Required Field Missing!");
        alert.setHeaderText(null);
        alert.setContentText("Required Field:"+field+" is missing!");
        alert.showAndWait();
    }

    public static final void invalidFormat(String field) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid Argument value!");
        alert.setHeaderText(null);
        alert.setContentText("Invalid value for Field:"+field+"!");
        alert.showAndWait();
    }

    public static final void successAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Operation Successful!");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static final void failedAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Operation failed!");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
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

        DateFormat df = new SimpleDateFormat(Defs.DATE_FORMAT);
        return df.format(date);
    }

    public static final List<String> getBloodGroups() {
        return Arrays.asList("A+","B+","AB+","O+","A-","AB-","O-");
    }

    public static final List<String> getMaritalStatus() {
        return Arrays.asList("MARRIED","SINGLE");
    }

    public static final List<String> getApplicationTypes() {
        return Arrays.asList("6 SEATER AUTO","2 SEATER AUTO","MANUAL RICKSHAW","BATTERY RICKSHAW");
    }

    public static final List<String> getApplicationTypes(boolean isSearch) {
        return Arrays.asList(Defs.ALL,"6 SEATER AUTO","2 SEATER AUTO","MANUAL RICKSHAW","BATTERY RICKSHAW");
    }

    public static final List<String> getGender(boolean isSearch) {
        return Arrays.asList(Defs.ALL,"MALE","FEMALE","THIRD");
    }

    public static final List<String> getGender() {
        return Arrays.asList("MALE","FEMALE","THIRD");
    }

    public static final List<Integer> getInstallmentPaid() {
        return Arrays.asList(1,2,3,4);
    }

    public static final List<Integer> getInstallmentPaid(boolean isSearch) {
        return Arrays.asList(0,1,2,3,4);
    }

    public static final LocalDate getLocalDate(Date dateToConvert) {
        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
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


    public static void addTextLimiter(final TextField textField, final int maxLength) {
        textField.setOnKeyTyped(event -> {
            if(textField.getText().length() >= maxLength) event.consume();
        });

    }

    public static final UnaryOperator<TextFormatter.Change> getDigitFilter() {
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String text = change.getText();
            if (text.matches("[0-9]*")) {
                return change;
            }

            return null;
        };

        return filter;
    }

    public static final Date getDateParam(Date date,boolean isMaximum) {
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+6"));
        c.setTime(date);
        if(isMaximum) {
            c.set(Calendar.HOUR_OF_DAY,23);
            c.set(Calendar.MINUTE,59);
            c.set(Calendar.SECOND,59);
        } else {
            c.set(Calendar.HOUR_OF_DAY,0);
            c.set(Calendar.MINUTE,0);
            c.set(Calendar.SECOND,0);
        }
        return c.getTime();
    }

    public static final String getMd5(String value) {
        if(isEmpty(value)) {
            return null;
        }
        return DigestUtils.md5DigestAsHex(value.getBytes());
    }

    public static final Date getDateFromString(String value) {
        if(value == null) {
            return null;
        }
        try {
            DateFormat df = new SimpleDateFormat(Defs.DATE_FORMAT);
            return df.parse(value);
        } catch (ParseException t) {
            return null;
        }
    }

    //70b4269b412a8af42b1f7b0d26eceff2

//    public static void main(String args[]) {
//        System.out.println("---MD5:"+getMd5("Abc@123"));
//    }
}
