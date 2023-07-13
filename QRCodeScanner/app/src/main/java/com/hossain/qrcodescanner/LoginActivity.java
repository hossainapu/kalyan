package com.hossain.qrcodescanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.hossain.dto.LoginResponse;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextUserName;
    private EditText editTextPassword;
    private Button cirLoginButton;
    private RequestQueue queue;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        queue = Volley.newRequestQueue(this);
        gson = new Gson();
        editTextUserName = findViewById(R.id.editTextUserName);
        editTextPassword = findViewById(R.id.editTextPassword);
        cirLoginButton = findViewById(R.id.cirLoginButton);

        cirLoginButton.setOnClickListener(e->{
            if(Utils.isEmpty(editTextUserName)) {
                Utils.showAlert(LoginActivity.this,"User name is required!","User Name missing!");
            }
            if(Utils.isEmpty(editTextPassword)) {
                Utils.showAlert(LoginActivity.this,"Password is required!","Password missing!");
            }

            JSONObject loginRequest = new JSONObject();

            try {
                loginRequest.put("userName", editTextUserName.getText().toString().trim());
                loginRequest.put("password",editTextPassword.getText().toString().trim());

                JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, Defs.LOGIN_URL, loginRequest,
                        response -> {
                            Log.d("Response", "" + response);
                            LoginResponse loginResponse = gson.fromJson(response.toString(), LoginResponse.class);
                            if (loginResponse != null && !Utils.isEmpty(loginResponse.getAccessToken())) {
                                Defs.token = loginResponse.getAccessToken();
                                Defs.userName = editTextUserName.getText().toString();
                                Intent intent = new Intent(this, Dashboard.class);
                                startActivity(intent);
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
                        return headers;
                    }

                };
                queue.add(postRequest);
            } catch (Throwable t) {
                t.printStackTrace();
            }


        });

    }
}
