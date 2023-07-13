package com.hossain.qrcodescanner;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.hossain.dto.PasswordChangeResponse;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class Dashboard extends AppCompatActivity implements View.OnClickListener{

    private ImageView imageVerify;
    private ImageView imageScan;
    private IntentIntegrator qrScan;
    private boolean isVerify = false;
    private Button btnTextScan;
    private EditText referenceNumber;
    private ImageButton changePassword;

    private EditText oldPassword;
    private EditText newPassword;
    private Button submit;
    private Button cancel;

    private RequestQueue queue;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        imageScan = findViewById(R.id.imageScan);
        imageVerify = findViewById(R.id.imageVerify);
        btnTextScan = findViewById(R.id.idTextScan);
        referenceNumber = findViewById(R.id.idReferenceNumber);
        qrScan = new IntentIntegrator(this);
        imageScan.setOnClickListener(this::onClick);
        imageVerify.setOnClickListener(this::onClick);
        changePassword =  findViewById(R.id.idChangePassword);
        queue = Volley.newRequestQueue(this);
        gson = new Gson();

        changePassword.setOnClickListener(this::onClick);
        btnTextScan.setOnClickListener(e->{
            if(Utils.isEmpty(referenceNumber)) {
                Utils.showAlert(this,"Reference number is required!","Required field missing!");
                return;
            }

            Intent intent = new Intent(this, LoadApplication.class);
            intent.putExtra(Defs.REFERENCE_NUMBER_EXTRA, referenceNumber.getText().toString());
            startActivity(intent);

        });



    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.imageVerify) {
            isVerify = true;
            qrScan.initiateScan();
        } else if(v.getId() == R.id.idChangePassword) {
            final AlertDialog dialog = new AlertDialog.Builder(this).create();
            LayoutInflater inflater = this.getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.change_password,null);
            oldPassword = dialogView.findViewById(R.id.idOldPassword);
            newPassword = dialogView.findViewById(R.id.idNewPassword);
            submit = dialogView.findViewById(R.id.idSubmit);
            cancel = dialogView.findViewById(R.id.idCancel);

            dialog.setView(dialogView);
            dialog.show();

            cancel.setOnClickListener(e->{
                dialog.hide();
            });

            submit.setOnClickListener(e->{
                if(Utils.isEmpty(oldPassword)) {
                    Toast.makeText(Dashboard.this,"Old password is required!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(Utils.isEmpty(newPassword)) {
                    Toast.makeText(Dashboard.this,"New password is required!",Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    JSONObject passwordChangeRequest = new JSONObject();
                    passwordChangeRequest.put("userName", Defs.userName);
                    passwordChangeRequest.put("oldPassword",oldPassword.getText().toString().trim());
                    passwordChangeRequest.put("newPassword",newPassword.getText().toString().trim());

                    JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, Defs.PASSWORD_CHANGE_URL, passwordChangeRequest,
                            response -> {
                                Log.d("Response", "" + response);
                                PasswordChangeResponse passwordChangeResponse = gson.fromJson(response.toString(), PasswordChangeResponse.class);
                                if(passwordChangeResponse != null && passwordChangeResponse.isOperationStatus()) {
                                    Toast.makeText(Dashboard.this,"Password has been changed successfully",Toast.LENGTH_SHORT).show();
                                    dialog.hide();
                                    return;
                                } else {
                                    Toast.makeText(Dashboard.this,"Failed to change password!",Toast.LENGTH_SHORT).show();
                                    dialog.hide();
                                    return;
                                }
                            },
                            error -> {
                                error.printStackTrace();
                                Utils.showAlert(this, "User Name or Password is invalid!", "Login Error!");
                            }
                    ) {

                        @Override
                        public Map getHeaders() {
                            HashMap headers = new HashMap();
                            headers.put("Content-Type", "application/json");
                            headers.put("Authorization",Defs.tokenType+" "+Defs.token);
                            return headers;
                        }

                    };

                    queue.add(postRequest);

                } catch (Throwable t)  {
                    t.printStackTrace();
                }
            });
        }

        else {
            isVerify = false;
            qrScan.initiateScan();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
            } else {
                byte[] ptext = result.getContents().getBytes(StandardCharsets.ISO_8859_1);
                String value = new String(ptext, StandardCharsets.UTF_8);
                Log.i("value",value);
                value =value.replaceAll("[^a-zA-Z0-9[-]]", "");
                Log.i("After Value:",value);
                if(!Utils.isEmpty(value)) {
                    if(isVerify) {
                        Intent intent = new Intent(this, LoadApplication.class);
                        intent.putExtra(Defs.REFERENCE_NUMBER_EXTRA, value);
                        startActivity(intent);
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Dashboard.this);
                        builder.setMessage(result.getContents());
                        builder.setPositiveButton("OK",(dialog, which) -> {
                           dialog.dismiss();  
                        });
                        builder.show();
                    }
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
