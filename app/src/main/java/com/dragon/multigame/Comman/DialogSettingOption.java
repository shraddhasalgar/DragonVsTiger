package com.dragon.multigame.Comman;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.dragon.multigame.Activity.Homepage;
import com.dragon.multigame.Activity.Privacy_Policy;
import com.dragon.multigame.Activity.Terms_Conditions;
import com.dragon.multigame.account.LoginScreen;
import com.dragon.multigame.Interface.ClassCallback;
import com.dragon.multigame.R;
import com.dragon.multigame.Utils.Functions;
import com.dragon.multigame.Utils.SharePref;
import com.dragon.multigame.Utils.Variables;


public class DialogSettingOption {

    Context context;
    ClassCallback callback;
    private volatile static DialogSettingOption mInstance;


    public static DialogSettingOption getInstance(Context context) {
        if (null == mInstance) {
            synchronized (DialogSettingOption.class) {
                if (null == mInstance) {
                    mInstance = new DialogSettingOption(context);
                }
            }
        }

        if(mInstance != null)
            mInstance.init(context);

        return mInstance;
    }

    /**
     * initialization of context, use only first time later it will use this again and again
     *
     * @param context app context: first time
     */
    public void init(Context context) {
        try {

            if (context != null) {
                this.context = context;
            }

        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }

    }


    public DialogSettingOption(Context context) {
        this.context = context;
    }

    public interface DealerInterface{

        void onClick(int pos);

    }

    public void showDialogSetting() {
        final Dialog dialog = Functions.DialogInstance(context);
        dialog.setContentView(R.layout.custom_dialog_setting_home);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme; //style id
        dialog.setTitle("Title...");
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View imgclose = (View) dialog.findViewById(R.id.imgclosetop);
//
        imgclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();

            }
        });


//        (dialog.findViewById(R.id.btnReport)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                new DialogReport(context).showReportDialog();
//
//            }
//        });

        ((TextView) dialog.findViewById(R.id.tv_playerid)).setText("#"+ SharePref.getU_id());
        ((TextView) dialog.findViewById(R.id.tv_playername)).setText( SharePref.getInstance().getString(SharePref.u_name));

        (dialog.findViewById(R.id.btnPrivacy)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context,Privacy_Policy.class);
                context.startActivity(intent);

            }
        });

        (dialog.findViewById(R.id.btnHowtoplay)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new DialogHelpSupport(context).showHelpDialog();

                dialog.dismiss();
            }
        });

        (dialog.findViewById(R.id.btnTermscond)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                new DialogWebviewContents(context).showDialog(Variables.TERMS_CONDITION);

                Intent intent = new Intent(context, Terms_Conditions.class);
                context.startActivity(intent);

            }
        });



        (dialog.findViewById(R.id.btnHelpandsupport)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new DialogWebviewContents(context).showDialog(Variables.SUPPORT);

            }
        });

        (dialog.findViewById(R.id.lnrlogoutdia)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences.Editor editor = context.getSharedPreferences(Homepage.MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putString("user_id", "");
                editor.putString("name", "");
                editor.putString("mobile", "");
                editor.putString("token", "");
                editor.apply();
                Intent intent = new Intent(context, LoginScreen.class);
                context.startActivity(intent);
                ((Activity)context).finish();

                dialog.dismiss();
            }
        });

        dialog.show();
        Functions.setDialogParams(dialog);

        Switch switchd = (Switch) dialog.findViewById(R.id.switch1);
        SharedPreferences prefs = context.getSharedPreferences(Homepage.MY_PREFS_NAME, MODE_PRIVATE);
        String value = prefs.getString("issoundon", "1");

        if (value.equals("0")) {

            switchd.setChecked(true);

        } else {

            switchd.setChecked(false);
        }

        switchd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    SharedPreferences.Editor editor = context.getSharedPreferences(Homepage.MY_PREFS_NAME, MODE_PRIVATE).edit();
                    editor.putString("issoundon", "0");
                    editor.apply();


                    // Funtions.showToast(PublicTable.this, "On");

                } else {
                    // Funtions.showToast(PublicTable.this, "Off");
                    SharedPreferences.Editor editor = context.getSharedPreferences(Homepage.MY_PREFS_NAME, MODE_PRIVATE).edit();
                    editor.putString("issoundon", "1");
                    editor.apply();

                }

                playstopSound();
            }
        });


        LinearLayout lnr_language = dialog.findViewById(R.id.lnr_language);
        lnr_language.removeAllViews();

        AddViewToLanguage(lnr_language,"English");

    }

    private void openWeblink(Context context, String str_intent_web){
        Uri webpage = Uri.parse(str_intent_web);

        if (!str_intent_web.startsWith("http://") && !str_intent_web.startsWith("https://")) {
            webpage = Uri.parse("http://" + str_intent_web);
        }

        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        }

    }

    private void AddViewToLanguage(ViewGroup viewGroup, String text){

        View view = Functions.CreateDynamicViews(R.layout.item_language,viewGroup,context);

        TextView textView = view.findViewById(R.id.tv_language);
        textView.setText(""+text);
        textView.setTag(text);


    }

    public void playstopSound(){
    }



}
