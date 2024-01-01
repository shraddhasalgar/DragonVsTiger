package com.dragon.multigame.Activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dragon.multigame.VolleyMultipartRequest;
import com.example.sms_readpayment.InitiatePayment;
import com.dragon.multigame.Comman.CommonAPI;

import com.dragon.multigame.Comman.PaymentGetway_CashFree;
import com.dragon.multigame.Comman.PaymentGetway_Paymt;

import com.dragon.multigame.Comman.PaymentGetway_PayuMoney;
import com.dragon.multigame.Interface.Callback;
import com.dragon.multigame.SampleClasses.Const;
import com.dragon.multigame.R;
import com.dragon.multigame.Utils.Functions;
import com.dragon.multigame.Utils.SharePref;
import com.dragon.multigame.Utils.Variables;
import com.rahman.dialog.Activity.SmartDialog;
import com.rahman.dialog.ListenerCallBack.SmartDialogClickListener;
import com.rahman.dialog.Utilities.SmartDialogBuilder;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import com.dragon.multigame.BaseActivity;
import com.squareup.picasso.Picasso;




public class BuyChipsDetails extends BaseActivity implements PaymentResultListener {
    private static final String ROOT_URL = "http://143.110.186.117/letscard/api/Plan/send_payment";
    private static final int REQUEST_PERMISSIONS = 100;
    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int REQUEST_IMAGE_CAPTURE = 4;

    private static final int PERMISSION_CAMERA = 5;
    private Bitmap bitmap;
    private String filePath,user_id;
    private ImageView selectedImage;
    private Button selectImageButton;

    String image_Path, mobile_no;

    ImageView Qr_code, copy;

    TextView abc;

    static String prefs = "codegente";

    TextView whatsappFooterText;
    private Button downloadButton;
    Activity context = this;
    private static final String MY_PREFS_NAME = "Login_data";
    Button btnPaynow;
    TextView txtChipsdetails;
    String plan_id = "";

    String chips_details = "";
    String amount = "";
    String RazorPay_ID = "";
    private String order_id;
    ImageView imgback, imgPaynow;
    String county_code = "+91 ";
    String whats_no = "";

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    String selected_payment = "";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set fullscreen
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_chips_details);

        imgPaynow = findViewById(R.id.imgPaynow);
        selectedImage = findViewById(R.id.selectedImage);
        selectImageButton = findViewById(R.id.selectImageButton);


//        selectImageButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                openImagePicker();
//                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                startActivityForResult(intent, PICK_IMAGE_REQUEST);
//
//            }
//        });

        selectImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((ContextCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)) {
                    if ((ActivityCompat.shouldShowRequestPermissionRationale(BuyChipsDetails.this,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)) && (ActivityCompat.shouldShowRequestPermissionRationale(BuyChipsDetails.this,
                            Manifest.permission.READ_EXTERNAL_STORAGE))) {


                    } else {
                        ActivityCompat.requestPermissions(BuyChipsDetails.this,
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                                REQUEST_PERMISSIONS);
                    }
                } else {
                    Log.e("Else", "Else");
                    Log.d("123", "456");
                    ActivityCompat.requestPermissions(BuyChipsDetails.this, new String[]{Manifest.permission.CAMERA}, PERMISSION_CAMERA);
                    showFileChooser();
                }
            }
        });

        Intent intent = getIntent();
        plan_id = intent.getStringExtra("plan_id");
        user_id = SharePref.getInstance().getString("user_id", "");
        chips_details = intent.getStringExtra("chips_details");
        amount = intent.getStringExtra("amount");


        imgPaynow = findViewById(R.id.imgPaynow);
        txtChipsdetails = findViewById(R.id.txtChipsdetails);
        txtChipsdetails.setText("Buy " + chips_details + " Pay now " + Variables.CURRENCY_SYMBOL + amount);


        PaymentGateWayInit();


        copy = findViewById(R.id.copy);
        abc = findViewById(R.id.abc);


        downloadButton = findViewById(R.id.downloadButton);

        Qr_code = findViewById(R.id.qrcodeimg1);
        abc = findViewById(R.id.abc);

        imgback = findViewById(R.id.imgback);

        Context contextCompact = this; // 'this' refers to the current activity

        getQrcode_Img();

        String prefs = "codegente";

        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveImageToGallery();
            }
        });


        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("Copy", abc.getText().toString());
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(BuyChipsDetails.this, "Copied", Toast.LENGTH_SHORT).show();

            }
        });

        imgPaynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("uuuuu", "aaaaaaaaaaaaaaaa");

                if (isImageSelected()) {

                    callPUTDataMethod(user_id, plan_id);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Please select an image", Toast.LENGTH_SHORT).show();

                }


//                sendPayment(40, 1, selectedImage);
//                sendApiRequest(mobileNo, userId, screenImg);
            }
//                place_order();
            //    SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);

                /*if (SharePref.getInstance().getBoolean(SharePref.isPaymentGateShow, true)) {


                    DialogSelectPaymentType dialogSelectPaymentType = new DialogSelectPaymentType(context, new Callback() {
                        @Override
                        public void Responce(String resp, String type, Bundle bundle) {


                        }
                    });

//                    dialogSelectPaymentType.showSelectPaymentType();


//                    paymentGetway_payuMoney.startPayment(plan_id);
//
//                    if(true)
//                        return;


                    selected_payment = SharePref.getInstance().getString(SharePref.PaymentType);
                    if (selected_payment.equalsIgnoreCase(Variables.RAZOR_PAY)) {
                        place_order();
                    } else if (selected_payment.equalsIgnoreCase(Variables.PAYTM)) {
//                                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                _paymentGetway_paymt.startPayment(plan_id, amount);
                            }
                        }, 1000);
                    } else if (selected_payment.equalsIgnoreCase(Variables.PAYUMONEY)) {
                        paymentGetway_payuMoney.startPayment(plan_id);
                    } else if (selected_payment.equalsIgnoreCase(Variables.UPI_FREE_WAY)) {
                        PlaceOrderUPI();
                    }
                    else if(selected_payment.equalsIgnoreCase(Variables.ATOM_PAY))
                    {
                        String url = Const.BSE_URL + "atompay/index?user_id=" + prefs.getString("user_id", "") +
                                "&token=" + prefs.getString("token", "") + "&plan_id=" + plan_id;
                        Log.e("payment_url", "onClick: " + url);
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);
                    }
                    else {
                        _paymentGetwayCashFree.startPayment(plan_id);
                    }


                } else {
                    whats_no = prefs.getString("whats_no", "");

                    Functions.showToast(BuyChipsDetails.this, "" + whats_no);

                    if (!whats_no.equals(""))
                        Functions.openWhatsappContact(BuyChipsDetails.this, county_code + whats_no);
                }*/


//
        });


        imgback = findViewById(R.id.imgback);
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    // Define the isImageSelected() method in your class
    private boolean isImageSelected() {
        // Check if an image is selected based on your app's logic.
        // For example, you can check if an ImageView has a valid image resource.
        ImageView selectedImage = findViewById(R.id.selectedImage); // Replace with your actual ImageView ID
        if (selectedImage.getDrawable() != null) {
            // An image is selected (ImageView has a drawable)
            return true;
        } else {
            // No image is selected
            return false;
        }
    }


    private void showFileChooser() {
        // Create an array of options for the user to choose from
        String[] options = {"Capture Photo", "Select Photo"};

        // Create a dialog to display the options
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Selcet 1 Option");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    // Option to take a new photo with the camera
                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                    }
                } else if (which == 1) {
                    // Option to select a photo from the file manager
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(Intent.createChooser(intent, "Select Photo"), PICK_IMAGE_REQUEST);
                }
            }
        });
        builder.show();
    }

//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//
//        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
//            try {
//                // Get the image captured by the camera
//                Bundle extras = data.getExtras();
//                bitmap = (Bitmap) extras.get("data");
//                // Display the image in an ImageView or do something else with it
//                selectedImage.setImageBitmap(bitmap);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//
//        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
//            Uri picUri = data.getData();
//            filePath = getPath(picUri);
//            if (filePath != null) {
//                try {
//
//                    Log.d("filePath", String.valueOf(filePath));
//                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), picUri);
////                    uploadBitmap(bitmap);
//                    selectedImage.setImageBitmap(bitmap);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            } else {
//                Toast.makeText(BuyChipsDetails.this, "no image selected", Toast.LENGTH_LONG).show();
//            }
//        }
//    }
//
//    public String getPath(Uri uri) {
//        String result = null;
//        String[] proj = {MediaStore.Images.Media.DATA};
//        Cursor cursor = getContentResolver().query(uri, proj, null, null, null);
//        if (cursor != null) {
//            if (cursor.moveToFirst()) {
//                int column_index = cursor.getColumnIndexOrThrow(proj[0]);
//                result = cursor.getString(column_index);
//            }
//            cursor.close();
//        }
//        if (result == null) {
//            result = "Not found";
//        }
//        return result;
//    }


    //    private void sendPayment(int planId, int userId, ImageView screenImg) {
//        String apiUrl = "http://143.110.186.117/letscard/api/Plan/send_payment";
//        Log.d("lllllllllll", "Success: ");
//        try {
//
//            // Create a JSONObject to represent the request body
//            JSONObject jsonBody = new JSONObject();
//            jsonBody.put("plan_id", planId);
//            jsonBody.put("user_id", userId);
//            jsonBody.put("screen_img", screenImg);
//
//            Log.d("ttttttttt", "Success: " + jsonBody);
//
//            // Create a request for sending the JSON data
//            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, apiUrl, jsonBody,
//                    new Response.Listener<JSONObject>() {
//                        @Override
//                        public void onResponse(JSONObject response) {
//                            try {
//                                String message = response.getString("message");
//                                int code = response.getInt("code");
//                                Log.d("eeeeee", "Success: ");
//
//                                if (message.equals("Success")) {
//                                    // Handle success scenario
//                                    Log.d("qqqqqqqqqqq", "Success: " + message);
//                                    // You can handle the code or other actions here for success
//
//                                } else {
//                                    // Handle other response scenarios if needed
//                                    Log.d("sssss", "Message: " + message);
//                                    Log.d("API Response", "Code: " + code);
//                                }
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                                Log.d("API Response", "JSON parsing error");
//                                Toast.makeText(getApplicationContext(), "JSON parsing error", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    },
//                    new Response.ErrorListener() {
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//                            // Handle API error responses here
//                            Log.d("API Response", "API Error: " + error.toString());
//                            Toast.makeText(getApplicationContext(), "API Error", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//            );
//
//            // Add the request to the request queue
//            RequestQueue requestQueue = Volley.newRequestQueue(this);
//            requestQueue.add(request);
//        } catch (JSONException e) {
//            e.printStackTrace();
//            Log.d("API Response", "JSON creation error");
//            Toast.makeText(getApplicationContext(), "JSON creation error", Toast.LENGTH_SHORT).show();
//        }
//    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
//            Uri imageUri = data.getData();
//            selectedImage.setImageURI(imageUri);
//        }


        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            try {
                // Get the image captured by the camera
                Bundle extras = data.getExtras();
                bitmap = (Bitmap) extras.get("data");
                // Display the image in an ImageView or do something else with it
                selectedImage.setImageBitmap(bitmap);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri picUri = data.getData();
            filePath = getPath(picUri);
            if (filePath != null) {
                try {

                    Log.d("filePath", String.valueOf(filePath));
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), picUri);
//                    uploadBitmap(bitmap);
                    selectedImage.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(BuyChipsDetails.this, "no image selected", Toast.LENGTH_LONG).show();
            }
        }
        return;

    }

    public  String getPath(Uri uri ) {
        String result = null;
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver( ).query( uri, proj, null, null, null );
        if(cursor != null){
            if ( cursor.moveToFirst( ) ) {
                int column_index = cursor.getColumnIndexOrThrow( proj[0] );
                result = cursor.getString( column_index );
            }
            cursor.close( );
        }
        if(result == null) {
            result = "Not found";
        }
        return result;
    }


    public byte[] getFileDataFromDrawable(Bitmap bitmap) {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    private void callPUTDataMethod(String user_id, String plan_id){

//        final ProgressDialog progressDialog = new ProgressDialog(CreateTickets.this);
//        progressDialog.setCancelable(false);
//        progressDialog.setMessage("Uploading...");
//        progressDialog.show();


            final ProgressDialog progressDialog = new ProgressDialog(BuyChipsDetails.this);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Your Request Is Sending...");
            progressDialog.show();

            VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, ROOT_URL,
                    new Response.Listener<NetworkResponse>() {
                        @Override
                        public void onResponse(NetworkResponse response) {

//                            compdescription.setText("");

                            progressDialog.dismiss();
//                            Intent i = new Intent(BuyChipsDetails.this, ThanksPage.class);
//                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                            startActivity(i);

                            Toast.makeText(BuyChipsDetails.this, "Request Send Successfully..", Toast.LENGTH_SHORT).show();

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressDialog.dismiss();
                            Toast.makeText(BuyChipsDetails.this, "Request Not Send..", Toast.LENGTH_SHORT).show();
                        }
                    }) {

                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();

                    Log.d("aaaaaaaaaaaaa",""+user_id);
//                    Log.d("comSub", " " + compSub);
//
//                    params.put("subject", compSubj);
//                    params.put("ticket_description", compDescription);
                    params.put("user_id", user_id);
                    params.put("plan_id", plan_id);
//                    params.put("location", addressLone);

                    return params;
                }


                @Override
                protected Map<String, DataPart> getByteData() {
                    Map<String, DataPart> params = new HashMap<>();
                    long imagename = System.currentTimeMillis();
                    // if (mVideoUri != null) {
                    //     params.put("vedio", new DataPart("file_avatar.mp4", UploadHelper.getFileDataFromDrawable(getApplicationContext(), mVideoUri)));
                    // }
                    // if (mAudioUri != null) {
                    //     params.put("audio", new DataPart("file_avatars.mp3", UploadHelper.getFileDataFromDrawable(getApplicationContext(), mAudioUri)));
                    // }
                    if (bitmap != null) {

                        Log.d("eeeee",""+bitmap);
                        params.put("screen_img", new DataPart(imagename + ".png", getFileDataFromDrawable(bitmap)));
                    }
                    return params;
                }
            };
            Volley.newRequestQueue(this).add(volleyMultipartRequest);
    }




    private void saveImageToGallery() {
        // Get the image from the ImageView
        BitmapDrawable drawable = (BitmapDrawable) Qr_code.getDrawable();
        Bitmap imageBitmap = drawable.getBitmap();

        // Define the image file's metadata
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.DISPLAY_NAME, "QRCodeImage");
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/png");

        // Insert the image into the MediaStore
        ContentResolver resolver = getContentResolver();
        Uri imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        try {
            // Open an OutputStream to the image file and save the image
            if (imageUri != null) {
                OutputStream imageOutputStream = resolver.openOutputStream(imageUri);
                imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, imageOutputStream);
                imageOutputStream.close();

                // Notify the user that the image has been saved
                Toast.makeText(this, "Image saved to gallery", Toast.LENGTH_SHORT).show();

                // Optional: Open the gallery app to view the saved image
                // Intent intent = new Intent(Intent.ACTION_VIEW, imageUri);
                // intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                // startActivity(intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error saving image", Toast.LENGTH_SHORT).show();
        }
    }


    private void getQrcode_Img() {

        String apiUrl = "http://143.110.186.117/letscard/api/User/qr_code";
        String imageUrlBase = "http://143.110.186.117/letscard/uploads/images/";
        TextView abc = findViewById(R.id.abc); // Get the text from the TextView


        StringRequest stringRequest = new StringRequest(Request.Method.POST, Const.get_Add_Cash_QR,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Functions.LOGE("putbet", Const.get_Add_Cash_QR + "\n" + response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String code = jsonObject.getString("code");
                            String message = jsonObject.getString("message");
                            String abc1 = jsonObject.getString("upi");

//                            String upi = jsonObject.getString("upi");

                            if (code.equalsIgnoreCase("200")) {
                                image_Path = jsonObject.getString("coin");
                                mobile_no = "7218655000";

                                abc.setText(abc1);

                                Log.d("dhdhd", "dhdhd" + image_Path);

                                Picasso.get().load(imageUrlBase + image_Path).into(Qr_code);


                                Log.d("image_Path_cash", "" + image_Path);

                            } else {
                                if (jsonObject.has("message")) {
                                    Functions.showToast(BuyChipsDetails.this, message);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(BuyChipsDetails.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
                Log.d("user_id_1", "" + prefs.getString("user_id", ""));
                params.put("user_id", prefs.getString("user_id", ""));
//                params.put("user_id", prefs.getString("upi", ""));
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


    @Override
    protected void onResume() {
        super.onResume();

        CommonAPI.CALL_API_UserDetails(context, new Callback() {
            @Override
            public void Responce(String resp, String type, Bundle bundle) {

            }
        });

    }

    private void PaymentGateWayInit() {
        _paymentGetway_paymt = new PaymentGetway_Paymt(context, new Callback() {
            @Override
            public void Responce(String resp, String type, Bundle bundle) {

                if (resp.equalsIgnoreCase(Variables.SUCCESS)) {
                    dialog_payment_success();
                } else {

                }

            }
        });

        _paymentGetwayCashFree = new PaymentGetway_CashFree(context, new Callback() {
            @Override
            public void Responce(String resp, String type, Bundle bundle) {

                if (resp.equalsIgnoreCase(Variables.SUCCESS)) {
                    dialog_payment_success();
                } else {

                }

            }
        });

        paymentGetway_payuMoney = new PaymentGetway_PayuMoney(context, new Callback() {
            @Override
            public void Responce(String resp, String type, Bundle bundle) {

                if (resp.equalsIgnoreCase(Variables.SUCCESS)) {
                    dialog_payment_success();
                } else {

                }

            }
        });
    }


    public void place_order() {

        String url1 = "https://goldwin.fun/api/plan/initiatePayment";

        // Create an Intent to open a web browser
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url1));


        // Replace your URL with the base URL
        Uri.Builder uriBuilder = Uri.parse(Const.PLCE_ORDER).buildUpon();

        // Add query parameters to the URL
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String user_id = prefs.getString("user_id", "");
        String token = prefs.getString("token", "");
        String planId = plan_id;

        uriBuilder.appendQueryParameter("user_id", user_id);
        uriBuilder.appendQueryParameter("token", token);
        uriBuilder.appendQueryParameter("plan_id", planId);
        Log.d("jjfjfj", "ffjjfjf" + user_id);
        Log.d("jjfkjkjfj", "ffjjfjf" + token);
        Log.d("jdfdjfjfj", "ffjjfjf" + planId);


        // Build the final URL
        String finalUrl = uriBuilder.build().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, finalUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            Log.d("res12", "" + response);


                            startActivity(intent);


//                            JSONObject jsonObject = new JSONObject(response);
//                            String code = jsonObject.getString("code");
//                            String message = jsonObject.getString("message");
//
//
//                            if (code.equals("200")) {
//
//                            } else if (code.equals("404")) {
//                                Functions.showToast(BuyChipsDetails.this, "" + message);
//                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                // Handle network errors here
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> header = new HashMap<>();
                header.put("token", Const.TOKEN);
                return header;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(BuyChipsDetails.this);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(50000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(stringRequest);
    }

   /* private String buildUrlWithParams() {

        Log.d("sidd","" );
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String userId = prefs.getString("user_id", "");
        String token = prefs.getString("token", "");
        String planId = plan_id;

        // Build the URL with query parameters
        Uri.Builder builder = Uri.parse(Const.PLCE_ORDER).buildUpon();
        builder.appendQueryParameter("user_id", userId);
        builder.appendQueryParameter("token", token);
        builder.appendQueryParameter("plan_id", planId);

        return builder.toString();
    }*/

    PaymentGetway_CashFree _paymentGetwayCashFree;
    PaymentGetway_PayuMoney paymentGetway_payuMoney;
    PaymentGetway_Paymt _paymentGetway_paymt;

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//
//        if (selected_payment.equalsIgnoreCase(Variables.CASH_FREE)) {
//            if (_paymentGetwayCashFree != null)
//                _paymentGetwayCashFree.onActivityResult(requestCode, resultCode, data);
//
//        } else if (selected_payment.equalsIgnoreCase(Variables.PAYTM)) {
//            if (_paymentGetway_paymt != null)
//                _paymentGetway_paymt.onActivityResult(requestCode, resultCode, data);
//
//        } else if (selected_payment.equalsIgnoreCase(Variables.PAYUMONEY))
//            if (paymentGetway_payuMoney != null)
//                paymentGetway_payuMoney.onActivityResult(requestCode, resultCode, data);
//
//
//    }


    public void startPayment(String ticket_id, String total_Amount, String razorPay_ID) {



        /*
          You need to pass current activity in order to let Razorpay create CheckoutActivity
         */
        final Activity activity = this;

        final Checkout co = new Checkout();

        String key = SharePref.getInstance().getString(SharePref.RAZOR_PAY_KEY);

        if (Functions.checkisStringValid(key)) {
            co.setKeyID(key);
        }

        try {
            SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);

            JSONObject options = new JSONObject();
            options.put("name", prefs.getString("name", ""));
            options.put("description", "chips payment");
            //You can omit the image option to fetch the image from dashboard
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            options.put("currency", "INR");
            options.put("amount", total_Amount);
            options.put("order_id", razorPay_ID);

            JSONObject preFill = new JSONObject();
            preFill.put("email", "support@androappstech.com");
            preFill.put("contact", prefs.getString("mobile", ""));
            options.put("prefill", preFill);

            co.open(activity, options);
        } catch (Exception e) {
            Functions.showToast(activity, "Error in payment: " + e.getMessage());
            e.printStackTrace();
        }
    }


    @Override
    public void onPaymentSuccess(String razorpayPaymentID) {
        try {
            payNow(razorpayPaymentID);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPaymentError(int i, String s) {
        try {
            //Funtions.showToast(this, "Payment failed: " + code + " " + response, Toast
            // .LENGTH_SHORT).show();
        } catch (Exception e) {
            //Log.e(TAG, "Exception in onPaymentError", e);
        }
    }


    public void payNow(final String payment_id) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Const.PY_NOW,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            ParseSuccessPayment(jsonObject);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                // NoInternet(listTicket.this);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> header = new HashMap<>();
                header.put("token", Const.TOKEN);

                return header;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
                params.put("user_id", prefs.getString("user_id", ""));
                params.put("token", prefs.getString("token", ""));
                params.put("order_id", order_id);
                params.put("payment_id", payment_id);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(BuyChipsDetails.this);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(50000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(stringRequest);


    }

    private void ParseSuccessPayment(JSONObject jsonObject) throws JSONException {


        String code = jsonObject.getString("code");
        String message = jsonObject.getString("message");

        if (code.equals("200")) {
            Functions.showToast(BuyChipsDetails.this, "" + message);
            dialog_payment_success();
        } else if (code.equals("404")) {
            Functions.showToast(BuyChipsDetails.this, "" + message);
        }

    }

    private void dialog_payment_success() {

        new SmartDialogBuilder(BuyChipsDetails.this)
                .setTitle("Your Payment has been done Successfully!")
                .setSubTitle("Your Payment has been done Successfully!")
                .setCancalable(false)

                //.setTitleFont("Do you want to Logout?") //set title font
                // .setSubTitleFont(subTitleFont) //set sub title font
                .setNegativeButtonHide(true) //hide cancel button
                .setPositiveButton("Ok", new SmartDialogClickListener() {
                    @Override
                    public void onClick(SmartDialog smartDialog) {
                        smartDialog.dismiss();
                        finish();
                    }
                }).setNegativeButton("Cancel", new SmartDialogClickListener() {
                    @Override
                    public void onClick(SmartDialog smartDialog) {
                        // Funtions.showToast(context,"Cancel button Click");
                        smartDialog.dismiss();

                    }
                }).build().show();

    }

    private void PlaceOrderUPI() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Const.callback_place,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("callback_place", "onResponse: " + response);
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            String code = jsonObject.getString("code");
                            String message = jsonObject.getString("message");

                            if (code.equals("200")) {
                                SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
                                Log.e("SharePref_tag_", "onClick: " + SharePref.getInstance().getString(SharePref.MerchantID));
                                Intent intent1 = new Intent(getApplicationContext(), InitiatePayment.class);
                                intent1.putExtra("amt", amount);
                                intent1.putExtra("upi", "");
                                intent1.putExtra("merchant_id", SharePref.getInstance().getString(SharePref.MerchantID));
                                intent1.putExtra("merchant_secret", SharePref.getInstance().getString(SharePref.MerchantSecret));
                                intent1.putExtra("user_id", prefs.getString("user_id", ""));
                                intent1.putExtra("name", prefs.getString("name", ""));
                                intent1.putExtra("param1", jsonObject.getString("order_id"));
                                startActivity(intent1);
                            } else if (code.equals("404")) {
                                Functions.showToast(BuyChipsDetails.this, "" + message);
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                // NoInternet(listTicket.this);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> header = new HashMap<>();
                header.put("token", Const.TOKEN);

                return header;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
                params.put("user_id", prefs.getString("user_id", ""));
                params.put("token", prefs.getString("token", ""));
                params.put("plan_id", plan_id);
                Functions.LOGE("BuyChipsDetails", Const.PLCE_ORDER + "\n" + params);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(BuyChipsDetails.this);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(50000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(stringRequest);

    }

}

//support