package com.hossain.qrcodescanner;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.hossain.dto.ApplicationInfo;
import com.hossain.dto.GetApplicationResponse;

import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;

public class LoadApplication extends AppCompatActivity {

    private ImageView valuePhoto;
    private ImageView valueSignature;
    private EditText valueReferenceNumber;
    private EditText valueApplicationType;
    private EditText valueStatus;
    private Button buttonOne;
    private Button buttonTwo;
    private Button buttonThree;
    private Button buttonFour;
    private EditText valueName;
    private EditText valueFather;
    private EditText valueSpouse;
    private EditText valueDateOfBirth;
    private EditText valueNID;
    private EditText valueGender;
    private EditText valueBloodGroup;
    private EditText valueMaritalStatus;
    private EditText valueContactNumber;
    private EditText valueEmergencyName;
    private EditText valueEmergencyContact;
    private EditText valueEmergencyRelation;
    private EditText valueCurrentAddress;
    private EditText valuePermanentAddress;
    private Button goBack;
    private RequestQueue queue;
    private Gson gson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_application);
        queue = Volley.newRequestQueue(this);
        gson = new Gson();
        valuePhoto = findViewById(R.id.valuePhoto);
        valueSignature = findViewById(R.id.valueSignature);
        valueReferenceNumber = findViewById(R.id.valueReferenceNumber);
        valueApplicationType = findViewById(R.id.valueApplicationType);
        valueStatus = findViewById(R.id.valueStatus);
        buttonOne = findViewById(R.id.buttonOne);
        buttonTwo = findViewById(R.id.buttonTwo);
        buttonThree = findViewById(R.id.buttonThree);
        buttonFour = findViewById(R.id.buttonFour);
        valueName = findViewById(R.id.valueName);
        valueFather = findViewById(R.id.valueFather);
        valueSpouse = findViewById(R.id.valueSpouse);
        valueDateOfBirth = findViewById(R.id.valueDateOfBirth);
        valueNID = findViewById(R.id.valueNID);
        valueGender = findViewById(R.id.valueGender);
        valueBloodGroup = findViewById(R.id.valueBloodGroup);
        valueMaritalStatus = findViewById(R.id.valueMaritalStatus);
        valueContactNumber = findViewById(R.id.valueContactNumber);
        valueEmergencyName = findViewById(R.id.valueEmergencyName);
        valueEmergencyContact = findViewById(R.id.valueEmergencyContact);
        valueEmergencyRelation = findViewById(R.id.valueEmergencyRelation);
        valueCurrentAddress = findViewById(R.id.valueCurrentAddress);
        valuePermanentAddress = findViewById(R.id.valuePermanentAddress);
        goBack = findViewById(R.id.goBack);

        String referenceNumber = "";
        if (savedInstanceState != null) {
            referenceNumber = (String) savedInstanceState.getSerializable(Defs.REFERENCE_NUMBER_EXTRA);
        } else {
            referenceNumber = getIntent().getExtras().getString(Defs.REFERENCE_NUMBER_EXTRA);
        }

        if(Utils.isEmpty(referenceNumber)) {
            return;
        } else {

            JSONObject applicationRequest = new JSONObject();

            try {
                applicationRequest.put("referenceNumber", referenceNumber);

                JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, Defs.APPLICATION_URL, applicationRequest,
                        response -> {
                            Log.d("Response", "" + response);
                            GetApplicationResponse applicationResponse = gson.fromJson(response.toString(), GetApplicationResponse.class);
                            if (applicationResponse != null && applicationResponse.isOperationStatus()) {
                                setData(applicationResponse.getApplicationInfo());
                            } else {
                                Utils.showAlert(this, applicationResponse.getError().getErrorMessage(), "Operation failed");
                            }
                        },
                        error -> {
                            error.printStackTrace();
                            Utils.showAlert(this, "Invalid Reference Number!", "Data not found!");
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
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }

        goBack.setOnClickListener(e->{
            this.finish();
        });

    }

    public void setData(ApplicationInfo info) {
        if(info != null) {
            if(info.getPhoto() != null) {
                this.valuePhoto.setImageBitmap(BitmapFactory.decodeStream(new ByteArrayInputStream(Base64.decode(info.getPhoto().getBytes(),Base64.DEFAULT))));
            }
            if(info.getSignature() != null) {
                this.valueSignature.setImageBitmap(BitmapFactory.decodeStream(new ByteArrayInputStream(Base64.decode(info.getSignature().getBytes(),Base64.DEFAULT))));
            }

            if(!Utils.isEmpty(info.getReferenceNumber())) {
                this.valueReferenceNumber.setText(info.getReferenceNumber());
            }
            if(!Utils.isEmpty(info.getApplicationType())) {
                this.valueApplicationType.setText(info.getApplicationType());
            }

            if(!Utils.isNull(info.getStatus())) {
                String status ="";
                if(info.getStatus() ==1) {
                    status = "ACTIVE";
                }
                if(info.getStatus() == 2) {
                    status = "REJECTED";
                }
                if(info.getStatus() ==3) {
                    status = "CANCELLED";
                }
                this.valueStatus.setText(status);
            }
            if(info.getNumberOfEmiPaid() ==0) {
                buttonOne.setBackgroundColor(Color.RED);
                buttonTwo.setBackgroundColor(Color.RED);
                buttonThree.setBackgroundColor(Color.RED);
                buttonFour.setBackgroundColor(Color.RED);
            } else if(info.getNumberOfEmiPaid() ==1) {
                buttonOne.setBackgroundColor(Color.GREEN);
                buttonTwo.setBackgroundColor(Color.RED);
                buttonThree.setBackgroundColor(Color.RED);
                buttonFour.setBackgroundColor(Color.RED);
            } else if(info.getNumberOfEmiPaid() ==2) {
                buttonOne.setBackgroundColor(Color.GREEN);
                buttonTwo.setBackgroundColor(Color.GREEN);
                buttonThree.setBackgroundColor(Color.RED);
                buttonFour.setBackgroundColor(Color.RED);
            } else if(info.getNumberOfEmiPaid() ==3) {
                buttonOne.setBackgroundColor(Color.GREEN);
                buttonTwo.setBackgroundColor(Color.GREEN);
                buttonThree.setBackgroundColor(Color.GREEN);
                buttonFour.setBackgroundColor(Color.RED);
            } else {
                buttonOne.setBackgroundColor(Color.GREEN);
                buttonTwo.setBackgroundColor(Color.GREEN);
                buttonThree.setBackgroundColor(Color.GREEN);
                buttonFour.setBackgroundColor(Color.GREEN);
            }

            if(!Utils.isEmpty(info.getNameEn())) {
                valueName.setText(info.getNameEn());
            }
            if(!Utils.isEmpty(info.getFatherEn())) {
                valueFather.setText(info.getFatherEn());
            }
            if(!Utils.isEmpty(info.getSpouseEn())) {
                valueSpouse.setText(info.getSpouseEn());
            }
            if(!Utils.isEmpty(info.getDateOfBirth())) {
                valueDateOfBirth.setText(info.getDateOfBirth());
            }
            if(!Utils.isEmpty(info.getNid())){
                valueNID.setText(info.getNid());
            }
            if(!Utils.isEmpty(info.getGender())) {
                valueGender.setText(info.getGender());
            }
            if(!Utils.isEmpty(info.getBloodGroup())) {
                valueBloodGroup.setText(info.getBloodGroup());
            }
            if(!Utils.isEmpty(info.getMaritalStatus())) {
                valueMaritalStatus.setText(info.getMaritalStatus());
            }
            if(!Utils.isEmpty(info.getContactNumber())) {
                valueContactNumber.setText(info.getContactNumber());
            }
            if(!Utils.isEmpty(info.getEmergencyName())) {
                valueEmergencyName.setText(info.getEmergencyName());
            }
            if(!Utils.isEmpty(info.getEmergencyContact())) {
                valueEmergencyContact.setText(info.getEmergencyContact());
            }
            if(!Utils.isEmpty(info.getEmergencyRelation())) {
                valueEmergencyRelation.setText(info.getEmergencyRelation());
            }

            String currentAddress = "";
            if(!Utils.isEmpty(info.getPresentAddressLine())) {
                currentAddress += info.getPresentAddressLine() + ",";
            }
            if(!Utils.isEmpty(info.getPresentThana())) {
                currentAddress += info.getPresentThana()+",";
            }
            if(!Utils.isEmpty(info.getPresentDistrict())) {
                currentAddress += info.getPresentDistrict()+",";
            }
            if(!Utils.isEmpty(info.getPresentDivision())) {
                currentAddress += info.getPresentDivision()+",";
            }

            currentAddress = currentAddress.substring(0,currentAddress.lastIndexOf(','));
            valueCurrentAddress.setText(currentAddress);

            String permanentAddress = "";

            if(!Utils.isEmpty(info.getPermanentAddressLine())) {
                permanentAddress += info.getPermanentAddressLine()+",";
            }
            if(!Utils.isEmpty(info.getPermanentThana())) {
                permanentAddress += info.getPermanentThana()+",";
            }
            if(!Utils.isEmpty(info.getPermanentDistrict())) {
                permanentAddress += info.getPermanentDistrict()+",";
            }
            if(!Utils.isEmpty(info.getPermanentDivision())) {
                permanentAddress += info.getPermanentDivision()+",";
            }

            permanentAddress = permanentAddress.substring(0,permanentAddress.lastIndexOf(','));
            valuePermanentAddress.setText(permanentAddress);

        }
    }
}
