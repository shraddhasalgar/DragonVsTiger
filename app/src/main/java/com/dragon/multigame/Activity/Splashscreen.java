package com.dragon.multigame.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.WindowManager;

import com.dragon.multigame.BaseActivity;
import com.dragon.multigame.R;
import com.dragon.multigame.Utils.SharePref;
import com.dragon.multigame.account.LoginScreen;
//import com.splunk.mint.Mint;

    public class Splashscreen extends BaseActivity {
    private static final String MY_PREFS_NAME = "Login_data" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
      //  Mint.initAndStartSession(this.getApplication(), "cc552ad8");
        // Set fullscreen
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

//        RelativeLayout relativeLayout = findViewById(R.id.rlt_parent);

//        SetBackgroundImageAsDisplaySize(this,relativeLayout,R.drawable.splash);

        SharePref.getInstance().init(getApplicationContext());

        SharePref.getInstance().putBoolean(SharePref.isDragonTigerVisible,true);
        SharePref.getInstance().putBoolean(SharePref.isTeenpattiVisible,true);
        SharePref.getInstance().putBoolean(SharePref.isPrivateVisible,true);
        SharePref.getInstance().putBoolean(SharePref.isCustomVisible,true);
        SharePref.getInstance().putBoolean(SharePref.isRummyVisible,true);
        SharePref.getInstance().putBoolean(SharePref.isAndharBaharVisible,true);
        SharePref.getInstance().putBoolean(SharePref.isLoginWithPassword,true);

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
                    String user_id = prefs.getString("user_id", "");
                    if (user_id.trim().length() > 0) {
                        startActivity(new Intent(Splashscreen.this, Homepage.class));
                    }else {
                        startActivity(new Intent(Splashscreen.this, LoginScreen.class));

                    }

                }

            }
        }).start();


    }


    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
