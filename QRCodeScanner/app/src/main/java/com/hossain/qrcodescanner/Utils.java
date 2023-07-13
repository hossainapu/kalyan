package com.hossain.qrcodescanner;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.EditText;

public class Utils {

    public static final boolean isEmpty(EditText text) {
        if(text == null || text.getText() == null || text.getText().toString() == null || "".equalsIgnoreCase(text.getText().toString())) {
           return true;
        }
        return false;
    }

    public static final boolean isEmpty(String value) {
        return value == null || value.isEmpty();
    }

    public static final void showAlert(Context ctx,String message,String title) {
        AlertDialog alertDialog = new AlertDialog.Builder(ctx).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",(dialog,which)->{
            dialog.dismiss();
            });
        alertDialog.show();
    }

    public static final boolean isNull(Integer status) {

        return status == null || status.intValue() == 0;
    }
}
