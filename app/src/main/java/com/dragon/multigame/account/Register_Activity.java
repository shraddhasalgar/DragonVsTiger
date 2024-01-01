package com.dragon.multigame.account;

import com.dragon.multigame.Activity.Homepage;
import com.dragon.multigame.BaseActivity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.dragon.multigame.Interface.ApiRequest;
import com.dragon.multigame.Interface.Callback;
import com.dragon.multigame.R;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dragon.multigame.SampleClasses.CommonFunctions;
import com.dragon.multigame.SampleClasses.Const;
import com.dragon.multigame.Utils.Functions;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Register_Activity extends BaseActivity {
    private static final String MY_PREFS_NAME = "Login_data";
    EditText edtPhone, edtname, edtReferalCode;
    EditText edtPassword;
    TextView tv;
    int pStatus = 0;
    private Handler handler = new Handler();
    TextView imglogin;
    AlertDialog dialog;
    EditText edit_OTP;
    String verificationID;

    RadioGroup radioGroup;
    boolean isSelected = false;
    RadioButton genderradioButton;
    ImageView imgBackground, imgBackgroundlogin;
    Activity context = this;
    public BottomSheetBehavior sheetBehavior;
    public View bottom_sheet;


    private final String LOGIN = "login";
    private final String REGISTER = "register";
    private Bitmap panBitmap;
    LinearLayout lnrThirdScreen;
    LinearLayout lnrSeconScreen;
    LinearLayout lnrFirstScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION| View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        setContentView(R.layout.activity_register);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        edtPhone = findViewById(R.id.edtPhone);
        edtPassword = findViewById(R.id.edtPassword);
        edtname = findViewById(R.id.edtname);
        edtReferalCode = findViewById(R.id.edtReferalCode);
        imglogin = findViewById(R.id.imglogin);

        lnrFirstScreen = findViewById(R.id.lnrFirstScreen);
        lnrSeconScreen = findViewById(R.id.lnrSeconScreen);
        lnrThirdScreen = findViewById(R.id.lnrSeconScreen);


        EditText passwordEditText = findViewById(R.id.edtPassword);

        passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Not needed for this example
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Not needed for this example
            }

            @Override
            public void afterTextChanged(Editable editable) {
                validatePassword(editable.toString());
            }
        });


        imglogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (CommonFunctions.isNetworkConnected(context)) {
                    if (isSelected) {

                        RadioButton rb = (RadioButton) radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());

                        if (rb != null) {
                            login(rb.getText() + "");
                        } else {
                            login("male");
                        }

                    } else {

                        Functions.showToast(context, "Please select Gender first ?");

                    }


                } else {
                    CommonFunctions.showNoInternetDialog(context);
                }


            }
        });


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    isSelected = true;
                }

            }
        });

    }

    private void validatePassword(String password) {
        if (password.length() < 8 || !containsOnlyDigits(password)) {
            // Password does not meet the criteria, handle accordingly
            edtPassword.setError("Password must be numeric");
        } else {
            // Password is valid, clear any error messages
            edtPassword.setError(null);
        }
    }

    private boolean containsOnlyDigits(String s) {
        for (char c : s.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    private void login(final String value) {

        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Logging in..");
        progressDialog.show();
        Functions.setDialogParams(dialog);

        HashMap params = new HashMap<String, String>();
        params.put("mobile", edtPhone.getText().toString());
        params.put("type", REGISTER);
        ApiRequest.Call_Api(context, Const.SEND_OTP, params, new Callback() {
            @Override
            public void Responce(String resp, String type, Bundle bundle) {
                progressDialog.dismiss();
                handleResponse(resp, value);
            }

        });

    }

    private void handleResponse(String response, String value) {

        try {
            JSONObject jsonObject = new JSONObject(response);

            String code = jsonObject.getString("code");


            if (code.equalsIgnoreCase("200")) {

                String otp_id = jsonObject.getString("otp_id");
                phoneLogin(otp_id, value);

            } else {

                if (jsonObject.has("message")) {
                    String message = jsonObject.getString("message");
                    Functions.showToast(context, message);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    public void phoneLogin(final String otp_id, final String value) {
        // String phoneNumber= "+91"+edtPhone.getText().toString().trim();
        //SendVerificationCode(phoneNumber);
        final Dialog dialog = Functions.DialogInstance(context);
        dialog.setContentView(R.layout.dialog_subimt);
        dialog.setTitle("Title...");
        dialog.setCancelable(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        edit_OTP = (EditText) dialog.findViewById(R.id.edit_OTP);

        dialog.findViewById(R.id.imgclose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        dialog.findViewById(R.id.imglogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edit_OTP.getText().toString().length() > 0) {
                    String verify_code = edit_OTP.getText().toString();
                    VerifyCode(verify_code, otp_id, value);
                } else {
                    Functions.showToast(getApplicationContext(), "Please Enter OTP");

                }

            }
        });

        Window window = dialog.getWindow();
        window.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();
        Functions.setDialogParams(dialog);

    }


    private void VerifyCode(final String code, final String otp_id, final String value) {

        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Logging in..");
        progressDialog.show();
        Functions.setDialogParams(dialog);


        StringRequest stringRequest = new StringRequest(Request.Method.POST, Const.REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {

                            JSONObject jsonObject = new JSONObject(response);

                            String code = jsonObject.getString("code");


                            if (code.equalsIgnoreCase("201")) {

                                String token = jsonObject.getString("token");

                                if (jsonObject.has("user")) {
                                    JSONObject jsonObject1 = jsonObject.getJSONArray("user").getJSONObject(0);
                                    String id = jsonObject1.getString("id");
                                    String name = jsonObject1.getString("name");
                                    String mobile = jsonObject1.getString("mobile");


                                    SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                                    editor.putString("user_id", id);
                                    editor.putString("name", name);
                                    editor.putString("mobile", mobile);
                                    editor.putString("token", token);
                                    editor.apply();

                                    Intent i = new Intent(context, Homepage.class);
                                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(i);
                                    Functions.showToast(context, "Login Successful");
                                } else {

                                    if (jsonObject.has("message")) {
                                        String message = jsonObject.getString("message");
                                        Functions.showToast(context, "Wrong mobile number or password");
                                    }

                                }


                            } else if (code.equalsIgnoreCase("200")) {
                                String token = jsonObject.getString("token");
                                String user_id = jsonObject.getString("user_id");

                                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                                editor.putString("user_id", user_id);
                                editor.putString("name", edtname.getText().toString());
                                editor.putString("mobile", edtPhone.getText().toString());
                                editor.putString("token", token);

                                editor.apply();

                                Intent i = new Intent(context, Homepage.class);
                                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(i);
                                Functions.showToast(context, "Login Successful");
//
                            } else {

                                if (jsonObject.has("message")) {
                                    String message = jsonObject.getString("message");
                                    Functions.showToast(context, message);
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        //  handleResponse(response);
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Functions.showToast(context, "Something went wrong");
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("otp", edit_OTP.getText().toString());
                params.put("otp_id", otp_id.trim());

                params.put("mobile", edtPhone.getText().toString());
                params.put("name", edtname.getText().toString());
                params.put("pan_card_no", "");
                params.put("dob", "");
                params.put("state", "");
                params.put("password", edtPassword.getText().toString());
                params.put("gender", value.trim());
                params.put("referral_code", edtReferalCode.getText().toString().trim());
                if (panBitmap != null)
                    params.put("pan_card", Functions.Bitmap_to_base64(context, panBitmap));


                params.put("type", REGISTER);


                Log.e("LoginScreen", "Register : " + params);

                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("token", Const.TOKEN);
                return headers;
            }
        };

        Volley.newRequestQueue(this).add(stringRequest);

    }

    public void LoginBtnClick(View view) {
        startActivity(new Intent(getApplicationContext(), LoginWithPasswordScreen_A.class));
    }
}