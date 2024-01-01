package com.dragon.multigame.Activity;

import static com.dragon.multigame.Activity.Homepage.MY_PREFS_NAME;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dragon.multigame.R;
import com.dragon.multigame.SampleClasses.Const;
import com.dragon.multigame.Utils.Functions;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class Add_Cash_Manual extends AppCompatActivity {

    String image_Path,mobile_no;

    ImageView Qr_code,copy;

    TextView abc;

    ImageView imgback;

    static String prefs = "codegente";

    TextView whatsappFooterText;
    private Button downloadButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cash_manual);
        copy=findViewById(R.id.copy);
        abc=findViewById(R.id.abc);


        downloadButton = findViewById(R.id.downloadButton);

        Qr_code = findViewById(R.id.qrcodeimg1);

        imgback = findViewById(R.id.imgback);


        getQrcode_Img();

        String prefs = "codegente";

        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveImageToGallery();
            }
        });


        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboardManager=(ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData=ClipData.newPlainText("Copy",abc.getText().toString());
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(Add_Cash_Manual.this,"Copied",Toast.LENGTH_SHORT).show();

            }
        });
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




//        whatsappFooterText = findViewById(R.id.whatsapp_footer_text);
//
//        whatsappFooterText.setText(getSharedPreferences(prefs, Context.MODE_PRIVATE).getString("whatsapp", mobile_no));
//
//        whatsappFooterText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String url = getWhatsapp(getApplicationContext());
//
//                Log.d("whatsaap","images"+url);
//                Uri uri = Uri.parse(url);
//                Intent sendIntent = new Intent(Intent.ACTION_VIEW, uri);
//                startActivity(sendIntent);
//            }
//        });






    public String getWhatsapp(Context context){

        String number = context.getSharedPreferences(prefs,Context.MODE_PRIVATE).getString("whatsapp",mobile_no);
        if (number.contains("+91")){
            return  "http://wa.me/"+context.getSharedPreferences(prefs,Context.MODE_PRIVATE).getString("whatsapp",mobile_no);
        } else {
            return  "http://wa.me/+91"+context.getSharedPreferences(prefs,Context.MODE_PRIVATE).getString("whatsapp",mobile_no);
        }

    }


    private void getQrcode_Img() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Const.get_Add_Cash_QR,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Functions.LOGE("putbet", Const.get_Add_Cash_QR + "\n" + response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String code = jsonObject.getString("code");
                            String message = jsonObject.getString("message");

                            if (code.equalsIgnoreCase("200")) {
                                image_Path = jsonObject.getString("coin");
                                mobile_no = "7218655000";

                                Picasso.get().load(Const.get_Cash_QR_Code + image_Path).into(Qr_code);


                                Log.d("image_Path_cash", "" + image_Path);

                            } else {
                                if (jsonObject.has("message")) {
                                    Functions.showToast(Add_Cash_Manual.this, message);
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
                        Toast.makeText(Add_Cash_Manual.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
                Log.d("user_id_1",""+ prefs.getString("user_id", ""));
                params.put("user_id", prefs.getString("user_id", ""));
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

}