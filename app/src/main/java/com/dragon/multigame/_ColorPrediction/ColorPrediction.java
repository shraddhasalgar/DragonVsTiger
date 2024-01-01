package com.dragon.multigame._ColorPrediction;

import static com.dragon.multigame.Activity.Homepage.MY_PREFS_NAME;
import static com.dragon.multigame.Utils.Functions.ANIMATION_SPEED;

import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.dragon.multigame.Activity.BuyChipsList;
import com.dragon.multigame.Activity.Homepage;
import com.dragon.multigame.BaseActivity;
import com.dragon.multigame.ChipsPicker;
import com.dragon.multigame.Interface.Callback;
import com.dragon.multigame.R;
import com.dragon.multigame.SampleClasses.Const;
import com.dragon.multigame.Utils.Animations;
import com.dragon.multigame.Utils.Functions;
import com.dragon.multigame.Utils.SharePref;
import com.dragon.multigame._ColorPrediction.menu.DialogRulesColorPrediction;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class ColorPrediction extends BaseActivity {

    private final int ANDHAR = 0;
    private final int BAHAR = 1;
    LinearLayout lnrfollow, lnrfollowColr, lnrfollowColr2, lnrfollowColr3;
    LinearLayout lnrandarpatte,lnrbaharpatte;
    RelativeLayout rltline;
    Context context =this;
    CountDownTimer counttimerforstartgame;
    CountDownTimer counttimerforcards;
    CountDownTimer counttimerforcardsforAnimation;
    int count = 0;
    private MediaPlayer mp;
    String tagamountselected = "", coloramountselected="";
    int betcountandar = 1;
    int betcountbahar = 1;
    int countvaue = 0;
    int countvaueforani = 0;
    boolean isConfirm = false;
    boolean IsFirsttimeCall = true;
    String betvalue = "";
    int widthandar = -430;
    int countwidhtbahar = 41;
    int widthbahar = -430;
    int countwidhtanadar = 41;
    int firstcount = 0;
    TextView txtGameFinish,txtName,txtBallence,txtGameRunning,txtGameBets,txt_catander,txt_catbahar,txt_room,txt_gameId,txt_online,txt_min_max,txt_many_cards;
    ImageView imgmaincard;
    Typeface helvatikaboldround;

    ArrayList<String> aaraycards;
    boolean isGameBegning = false;
    int timertime = 4000;
    int cardAnimationIntervel = 200;
    public String game_id;
    public String room_id;
    public String min_coin;
    public String max_coin;
    public String main_card;
    public String winning;
    public String status = "";
    String bet_id = "";
    private String added_date;
    int time_remaining;
    private int timer_interval = 1000;
    private String user_id,name,wallet;
    Timer timerstatus;
    ImageView imgCardsandar,imgCardsbahar;
    Animation setanimation;
    ImageView imgmaincardsvaluehiostory;
    String betplace = "";
    boolean canbet = false;
    boolean isInPauseState = false;
    boolean isCardsDisribute = false;
    Button btnCANCEL,btnRepeat,btnDouble,btnconfirm;
    RelativeLayout rltandarbet,rltbaharbet,rltmainviewander,rltmainviewbahar,rlt41more,rlt1to5,rlt6to10,rlt11to15,rlt16to25,rlt26to30,rlt31to35,rlt36to40;

    ImageView imgpl1circle;
    LinearLayout lnrOnlineUser;
    View ChipstoUser;
    private boolean isCountDownStart;
    RelativeLayout rlt_centercard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_prediction);

//        Toast.makeText(context, "CP", Toast.LENGTH_SHORT).show();

        initSoundPool();
        initview();
        initiAnimation();
        initDisplayMetrics();
    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //  builder.setTitle("");
        builder.setMessage("Do you want to exit the game  ");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                DestroyGames();
                releaseSoundpoll();
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder.show();
    }
    Animation blinksAnimation;
    boolean isBlinkStart = false;
    private void BlinkAnimation(final View view) {

        if(isBlinkStart)
            return;

        isBlinkStart = true;
        view.startAnimation(blinksAnimation);
    }

    private void restartGame(){
        canbet = true;
        txtBallence.setText(wallet);
        firstcount = 0;
        count = 0;
        countwidhtanadar = 41;
        widthandar = -430;
        countwidhtbahar = 41;
        widthbahar = -430;
        isConfirm = false;
        bet_id = "";
        betplace="";
        aaraycards.clear();
        countvaue = 0;
        betcountandar = 1;
        isGameBegning = true;
        betvalue = "";
        VisiblePleasewaitforNextRound(false);
        removeBetChips();
        Log.v("startgame" ,"start");
        removeChips();
        lnrandarpatte.removeAllViews();
        lnrbaharpatte.removeAllViews();
        andharPutBetVisiblity(false);
        txt_catander.setText("0");
        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.remove("tag").commit();
        setSelectedType("");
        setSelectedTypeColor("");

        baharPutBetVisiblity(false);
        txt_catbahar.setText("1");//0chnage
        rlt1to5.setBackground(ContextCompat.getDrawable(context, R.drawable.background_border));
        rlt6to10.setBackground(ContextCompat.getDrawable(context, R.drawable.background_border));
        rlt11to15.setBackground(ContextCompat.getDrawable(context, R.drawable.background_border));
        rlt16to25.setBackground(ContextCompat.getDrawable(context, R.drawable.background_border));
        rlt26to30.setBackground(ContextCompat.getDrawable(context, R.drawable.background_border));
        rlt31to35.setBackground(ContextCompat.getDrawable(context, R.drawable.background_border));
        rlt36to40.setBackground(ContextCompat.getDrawable(context, R.drawable.background_border));
        rlt41more.setBackground(ContextCompat.getDrawable(context, R.drawable.background_border));
        restartRuleView();
    }

    private void removeBetChips() {
        andharPutBetVisiblity(false);
        baharPutBetVisiblity(false);
    }

    private void andharPutBetVisiblity(boolean visible){
        rltmainviewander.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    private void baharPutBetVisiblity(boolean visible){
        rltmainviewbahar.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    private void initiAnimation() {
        blinksAnimation = AnimationUtils.loadAnimation(context,R.anim.blink);
        blinksAnimation.setDuration(1000);
        blinksAnimation.setRepeatCount(Animation.INFINITE);
        blinksAnimation.setStartOffset(700);
    }

    private void VisiblePleasewaitforNextRound(boolean visible){

        if(blinksAnimation != null)
        {
            isBlinkStart = false;
            txtGameRunning.clearAnimation();
            blinksAnimation.cancel();
        }

        txtGameRunning.setVisibility(visible ? View.VISIBLE : View.GONE);

        if(visible)
        {
//            if(!Functions.checkisStringValid(((TextView) findViewById(R.id.txtcountdown)).getText().toString().trim()))
//                pleasewaintCountDown.start();

            BlinkAnimation(txtGameRunning);
        }
        else {
//            pleasewaintCountDown.cancel();
//            pleasewaintCountDown.onFinish();
        }


    }

    private String profile_pic;
    View rtllosesymble1;
    public void initview(){

        // setanimation= AnimationUtils.loadAnimation(this,R.anim.set);

        rl_AnimationView = ((RelativeLayout)findViewById(R.id.sticker_animation_layout));

        rltwinnersymble1=findViewById(R.id.rltwinnersymble1);
        rtllosesymble1=findViewById(R.id.rtllosesymble1);

        imgpl1circle = findViewById(R.id.imgpl1circle);
        lnrOnlineUser = findViewById(R.id.lnrOnlineUser);
        ChipstoUser = findViewById(R.id.ChipstoUser);
        profile_pic = SharePref.getInstance().getString(SharePref.u_pic);
        Picasso.get().load(Const.IMGAE_PATH + profile_pic).into(imgpl1circle);

        imgmaincardsvaluehiostory = findViewById(R.id.imgmaincardsvaluehiostory);


        imgCardsandar = findViewById(R.id.imgCardsandar);
        imgCardsbahar = findViewById(R.id.imgCardsbahar);
        lnrfollow = findViewById(R.id.lnrfollow);
        lnrfollowColr = findViewById(R.id.lnrfollowColr);
        lnrfollowColr2 = findViewById(R.id.lnrfollowColr2);
        lnrfollowColr3 = findViewById(R.id.lnrfollowColr3);
        lnrandarpatte = findViewById(R.id.lnrandarpatte);
        // lnrpaate = findViewById(R.id.lnrpaate);
        rltline = findViewById(R.id.rltline);
        lnrbaharpatte = findViewById(R.id.lnrbaharpatte);
        // setBackground(context,findViewById(R.id.activity_main),R.mipmap.src_gameboard);
        txt_room = findViewById(R.id.txt_room);
        txt_gameId = findViewById(R.id.txt_gameId);
        txt_online = findViewById(R.id.txt_online);
        txt_min_max = findViewById(R.id.txt_min_max);
        txt_many_cards = findViewById(R.id.txt_many_cards);
        txtGameFinish = findViewById(R.id.tvStartTimer);
        txtGameBets = findViewById(R.id.txtGameBets);
        imgmaincard = findViewById(R.id.imgmaincard);
        rltandarbet = findViewById(R.id.rltandarbet);
        rlt_centercard = findViewById(R.id.rlt_centercard);
        rltmainviewander = findViewById(R.id.rltmainviewander);
        rltmainviewbahar = findViewById(R.id.rltmainviewbahar);
        rlt1to5 = findViewById(R.id.rlt1to5);
        rlt6to10 = findViewById(R.id.rlt6to10);
        rlt11to15 = findViewById(R.id.rlt11to15);
        rlt16to25 = findViewById(R.id.rlt16to25);

        rlt26to30 = findViewById(R.id.rlt26to30);
        rlt31to35 = findViewById(R.id.rlt31to35);
        rlt36to40 = findViewById(R.id.rlt36to40);
        rlt41more = findViewById(R.id.rlt41more);
        btnRepeat = findViewById(R.id.btnRepeat);

        min_coin= getIntent().getStringExtra("min_coin");
        max_coin= getIntent().getStringExtra("max_coin");
        room_id= getIntent().getStringExtra("room_id");


        txt_room.setText("ROOM ID "+room_id);

        txt_min_max.setText("Min-Max: "+min_coin+" - "+max_coin);

//        imgCards.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                imgCards.startAnimation(setanimation);
//            }
//        });

        rlt_centercard.setVisibility(View.GONE);
        btnRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //finish();
                //  Functions.showToast(context, "Need to Discuss");

                if (betplace.length() > 0 ){

                    Functions.showToast(context, "You Already Placed Bet");

                }else {

//                    repeatBet();

                }




            }
        });

        btnDouble = findViewById(R.id.btnDouble);
        btnDouble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // finish();
                // Functions.showToast(context, "Need to Discuss");

                if (betplace.length() > 0){
                    float valedou = Float.parseFloat(betvalue);
                    float dobff=valedou*2;
                    betvalue=dobff+"";
//                    txt_catander.setText(""+betvalue);
//                    txt_catbahar.setText(""+betvalue);
                }else {
                    Functions.showToast(context, "You have not Bet yet. ");

                }


            }
        });


        btnconfirm = findViewById(R.id.btnconfirm);
        btnconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  finish();
                //Functions.showToast(context, "Need to Discuss");

//                putbetonView();
            }
        });

        btnCANCEL = findViewById(R.id.btnCANCEL);
        btnCANCEL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                betvalue = "";
                betplace = "";
                if (bet_id.length() > 0){

                    cancelbet();

                }else {

                    Functions.showToast(context, "You have not Bet yet. ");
                    removeBetChips();


                }

            }
        });

        txt_catander = findViewById(R.id.txt_catander);
        txt_catbahar = findViewById(R.id.txt_catbahar);
        rltandarbet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isConfirm) {

                    Functions.showToast(context, "Bet Already Confirmed So Not Allowed to Put again");


                } else {

                    if (canbet) {
                        if (tagamountselected.length() > 0) {

//                            baharPutBetVisiblity(false);
//                            txt_catbahar.setText("");

                            if (betplace.equals("1")){

                                betvalue = "";

                            }else {


                            }

                            betplace = "0";
                            float valedou = 0;
                            if (betvalue.length() > 0) {
                                valedou = Float.parseFloat(betvalue);
                            } else {

                                valedou = 0;
                            }
                            float valedoutagamountselected = 0;
                            if (tagamountselected.length() > 0) {
                                valedoutagamountselected = Float.parseFloat(tagamountselected);
                            } else {

                                valedoutagamountselected = 0;
                            }


                            float betvalueint = valedou + valedoutagamountselected;


                            betvalue = betvalueint + "";
                            andharPutBetVisiblity(true);
//                            txt_catander.setText("" + betvalue);

//                            putbetonView();


                        } else {

                            Functions.showToast(context, "Please Select Bet amount First");

                        }
                    } else {

                        Functions.showToast(context, "Game Already Started You can not Bet");

                    }
                }



            }
        });
        rltbaharbet = findViewById(R.id.rltbaharbet);
        rltbaharbet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (isConfirm) {

                    Functions.showToast(context, "Bet Already Confirmed So Not Allowed to Put again");



                } else {


                    if (canbet) {

                        if (tagamountselected.length() > 0) {


//                            andharPutBetVisiblity(false);
//                            txt_catander.setText("");

                            if (betplace.equals("0")){

                                betvalue = "";

                            }else {


                            }

                            float valedou = 0;
                            if (betvalue.length() > 0) {
                                valedou = Float.parseFloat(betvalue);
                            } else {

                                valedou = 0;
                            }
                            float valedoutagamountselected = 0;
                            if (tagamountselected.length() > 0) {
                                valedoutagamountselected = Float.parseFloat(tagamountselected);
                            } else {

                                valedoutagamountselected = 0;
                            }

                            float betvalueint = valedou + valedoutagamountselected;
                            betvalue = betvalueint + "";
                            baharPutBetVisiblity(true);
//                            txt_catbahar.setText("" + betvalue);
                            //putbet("1");
                            betplace = "1";

//                            putbetonView();

                        } else {

                            Functions.showToast(context, "Please Select Bet amount First");

                        }

                    } else {

                        Functions.showToast(context, "Game Already Started You can not Bet");

                    }
                }

            }
        });
        txtName = findViewById(R.id.txtName);
        txtGameRunning = findViewById(R.id.txtGameRunning);
        txtBallence = findViewById(R.id.txtBallence);
        helvatikaboldround = Typeface.createFromAsset(getAssets(), "fonts/helvetica-rounded-bold-5871d05ead8de.otf");
        txtGameFinish.setTypeface(helvatikaboldround);
        txtGameRunning.setTypeface(helvatikaboldround);
        txtGameBets.setTypeface(helvatikaboldround);
        aaraycards  = new ArrayList<>();
        addChipsonView();
        addChipsonViewColor();
        addChipsonViewColor2();
        addChipsonViewColor3();
        timerstatus = new Timer();
        timerstatus.scheduleAtFixedRate(new TimerTask() {

                                            @Override
                                            public void run() {

                                                // if (table_id.trim().length() > 0) {

                                                if (isCardsDisribute){



                                                }else {

                                                    getStatus();

                                                }



                                                // }

                                            }

                                        },
//Set how long before to start calling the TimerTask (in milliseconds)
                200,
//Set the amount of time between each execution (in milliseconds)
                timertime);


        findViewById(R.id.imgback).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }


    private void addCardinLayout(String cat , int countnumber , ViewGroup lnrLayout){

        View view = LayoutInflater.from(context).inflate(R.layout.ab_cards_layout,  null);
        ImageView imgcards = view.findViewById(R.id.cards);
        view.setTag(cat+"");
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tag = (String) view.getTag();
                Functions.showToast(context , tag);
            }
        });
        String uri1 = "@drawable/" + cat.toLowerCase();  // where myresource " +
        int imageResource1 = getResources().getIdentifier(uri1, null,
                getPackageName());
        Picasso.get().load(imageResource1).into(imgcards);


        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        layoutParams.setMargins(0,
                0,
                -35, 0);

        lnrLayout.addView(view,layoutParams);
        playSound(CARD_SOUND,false);


    }

    boolean animationon = false;
    RelativeLayout rl_AnimationView;
    int coins_count = 10;
    private void ChipsAnimations(View mfromView,View mtoView,ViewGroup rl_AnimationView){

        animationon = true;


        final View fromView, toView;

        fromView = mfromView;
        toView = mtoView;


        int fromLoc[] = new int[2];
        fromView.getLocationOnScreen(fromLoc);
        float startX = fromLoc[0];
        float startY = fromLoc[1];

//        int toLoc[] = new int[2];
//        toView.getLocationOnScreen(toLoc);
//        float destX = toLoc[0];
//        float destY = toLoc[1];

        Rect myViewRect = new Rect();
        toView.getGlobalVisibleRect(myViewRect);
        float destX = myViewRect.left;
        float destY = myViewRect.top;

        rl_AnimationView.setVisibility(View.VISIBLE);

        ImageView image_chips = creatDynamicChips();

        rl_AnimationView.addView(image_chips);

        if(chips_width <= 0)
        {
            chips_width = 96;
        }

        int centreX = (int) (andharX +  andharWidth  / 2) - (chips_width  / 2);
        int centreY = (int) (andharY +  andharHeight / 2) - (chips_width  / 2);

        if(chips_width > 0)
        {
            destX  = destX + new Random().nextInt(andharWidth - chips_width);
        }
        else
            destX += centreX;

        destY += centreY;

        Animations anim = new Animations();
        float finalDestX = destX;
        float finalDestY = destY;

        Animation a = anim.fromAtoB(startX, startY, destX, destY, null, ANIMATION_SPEED, new Callback() {
            @Override
            public void Responce(String resp, String type, Bundle bundle) {
                animationon = false;


//                image_chips.setX(finalDestX);
//                image_chips.setY(finalDestY);

//                if(andharWidth > 0 && andharHeight > 0)
//                {
//
//                }
//
//                rl_AnimationView.removeView(dummyUserleft);
//                rl_AnimationView.removeView(dummyUserright);

            }
        });
        anim.setAnimationView(image_chips);
        image_chips.setAnimation(a);
        a.startNow();

        playSound(CHIPS_SOUND,false);

    }

    private void ChipsAnimations(View mfromView,View mtoView){

        animationon = true;


        final View fromView, toView, shuttleView;

        fromView = mfromView;
        toView = mtoView;


        int fromLoc[] = new int[2];
        fromView.getLocationOnScreen(fromLoc);
        float startX = fromLoc[0];
        float startY = fromLoc[1];

        int toLoc[] = new int[2];
        toView.getLocationOnScreen(toLoc);
        float destX = toLoc[0];
        float destY = toLoc[1];

        rl_AnimationView.setVisibility(View.VISIBLE);
//        rl_AnimationView.removeAllViews();
        final ImageView sticker = new ImageView(this);

        int stickerId = Functions.GetResourcePath("ic_dt_chips",context);

        int chips_size = (int) getResources().getDimension(R.dimen.chips_size);

        if(stickerId > 0 && Functions.isActivityExist(context))
            LoadImage().load(stickerId).into(sticker);

        sticker.setLayoutParams(new ViewGroup.LayoutParams(chips_size, chips_size));
        rl_AnimationView.addView(sticker);

        shuttleView = sticker;

        Animations anim = new Animations();
        Animation a = anim.fromAtoB(startX, startY, destX, destY-100, null, ANIMATION_SPEED, new Callback() {
            @Override
            public void Responce(String resp, String type, Bundle bundle) {
                shuttleView.setVisibility(View.GONE);
                fromView.setVisibility(View.VISIBLE);
                animationon = false;
                sticker.setVisibility(View.GONE);
                if(coins_count <= 0)
                {
                    removeChips();
                    rl_AnimationView.removeAllViews();
//                    if(!iswin)
//                        makeLosstoPlayer(SharePref.getU_id());
//                    else
//                        makeWinnertoPlayer(SharePref.getU_id());
                }

            }
        });
        sticker.setAnimation(a);
        a.startNow();


        Rect fromRect = new Rect();
        Rect toRect = new Rect();
        fromView.getGlobalVisibleRect(fromRect);
        toView.getGlobalVisibleRect(toRect);

        playSound(CHIPS_SOUND,false);


    }

    private RequestManager LoadImage()
    {
        return  Glide.with(context);
    }


    private void removeChips(){
        rl_AnimationView.removeAllViews();
    }


    public void PlaySaund(int saund) {

        if(!SharePref.getInstance().isSoundEnable())
            return;

        if (!isInPauseState) {
//            final MediaPlayer mp = MediaPlayer.create(this,
//                    saund);
//            mp.start();
//            try {
//                if (mp.isPlaying()) {
//                    mp.stop();
//                    mp.release();
//                    mp = MediaPlayer.create(context, saund);
//                }
//                mp.start();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }


            stopPlaying();
            mp = MediaPlayer.create(this, saund);
            mp.start();

        }


    }

    private void stopPlaying() {
        if (mp != null) {
            mp.stop();
            mp.release();
            mp = null;
        }
    }


    private void addCategoryInView(String cat) {
        View view = LayoutInflater.from(context).inflate(R.layout.cat_txtview,  null);
        TextView txtview = view.findViewById(R.id.txt_cat);
        ImageView ivChips = view.findViewById(R.id.ivChips);
        ivChips.setBackground(Functions.getDrawable(context, ChipsPicker.getInstance().getChip()));
        txtview.setText(cat+"");
        view.setTag(cat+"");


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                // SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
                //  String Tag = prefs.getString("tag", "");
                // if (Tag.equals("")) {
//                    Functions.showToast(context ,"Tag=" +Tag);
                tagamountselected = (String) view1.getTag();
                TextView txt = view1.findViewById(R.id.txt_cat);
                txt.setTextColor(Color.parseColor("#ffffff"));
                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putString("tag", tagamountselected);
                editor.apply();
//                      Functions.showToast(context ,"tag="+ tag);
                setSelectedType(tagamountselected);

            }
        });


        //  Log.d("101010","title :"+cat+" "+view.getHeight()+" "+view.getWidth());
        lnrfollow.addView(view);


    }

    private void addChipsonView() {
        lnrfollow.removeAllViews();
        chipsDefaultSelection = false;
        addCategoryInView("10", R.drawable.coin_10);
        addCategoryInView("50", R.drawable.coin_50);
        addCategoryInView("100", R.drawable.coin_100);
//        addCategoryInView("500", R.drawable.coin_500);
        addCategoryInView("1000", R.drawable.coin_1000);
        addCategoryInView("5000", R.drawable.coin_5000);
//        addCategoryInView("7500");
    }

    private void addChipsonViewColor() {
        lnrfollowColr.removeAllViews();
        addCategoryInViewColor("10", R.drawable.join_green);
        addCategoryInViewColor("11", R.drawable.join_violet);
        addCategoryInViewColor("12", R.drawable.join_red);
//        addCategoryInView("7500");
    }
    private void addChipsonViewColor2() {
        lnrfollowColr2.removeAllViews();
        addRulesColorinViewColor("0", R.drawable.join_zero_zero,lnrfollowColr2);

        addRulesColorinViewColor("1", R.drawable.join_one,lnrfollowColr2);
        addRulesColorinViewColor("2", R.drawable.join_two,lnrfollowColr2);
        addRulesColorinViewColor("3", R.drawable.join_three,lnrfollowColr2);
        addRulesColorinViewColor("4", R.drawable.join_four,lnrfollowColr2);

//        addCategoryInView("7500");
    }
    private void addChipsonViewColor3() {
        lnrfollowColr3.removeAllViews();
        addRulesColorinViewColor("5", R.drawable.join_five,lnrfollowColr3);
        addRulesColorinViewColor("6", R.drawable.join_six,lnrfollowColr3);
        addRulesColorinViewColor("7", R.drawable.join_sevn,lnrfollowColr3);
        addRulesColorinViewColor("8", R.drawable.join_eight,lnrfollowColr3);
        addRulesColorinViewColor("9", R.drawable.join_nine,lnrfollowColr3);
//        addCategoryInView("7500");
    }

    boolean chipsDefaultSelection = false;
    private void addCategoryInView(String cat, int img) {
        View view = LayoutInflater.from(context).inflate(R.layout.cat_txtview_chip_bg, null);
        TextView txtview = view.findViewById(R.id.txt_cat);
//        txtview.setVisibility(View.INVISIBLE);
        txtview.setText(cat + "");
        txtview.setBackgroundResource(img);
        view.setTag(cat + "");
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                tagamountselected = (String) view1.getTag();
                TextView txt = view1.findViewById(R.id.txt_cat);
//                txt.setTextColor(Color.parseColor("#ffffff"));
                SharedPreferences.Editor editor = getSharedPreferences(Homepage.MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putString("tag", tagamountselected);
                editor.apply();
                setSelectedType(tagamountselected);
//                GameAmount = Integer.parseInt(tagamountselected);
            }
        });

        if(!chipsDefaultSelection)
        {
            chipsDefaultSelection = true;
            tagamountselected = (String) view.getTag();
            SharedPreferences.Editor editor = getSharedPreferences(Homepage.MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putString("tag", tagamountselected);
            editor.apply();
            setSelectedType(tagamountselected);
        }
        lnrfollow.addView(view);
    }

    private void addCategoryInViewColor(String cat, int img) {
        View view = LayoutInflater.from(context).inflate(R.layout.cat_txtview_chip_bg_clr, null);
        TextView txtview = view.findViewById(R.id.txt_cat);
//        txtview.setVisibility(View.INVISIBLE);
        txtview.setText(cat + "");
        txtview.setBackgroundResource(img);
        view.setTag(cat + "");
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {

                if(betValidation())
                {
                    coloramountselected = (String) view1.getTag();
                    TextView txt = view1.findViewById(R.id.txt_cat);
                    putbet(coloramountselected);
                    setSelectedTypeColor(coloramountselected);
                    Toast.makeText(context, ""+coloramountselected, Toast.LENGTH_SHORT).show();
                }



            }
        });
        lnrfollowColr.addView(view);
    }

    TextView winnerView = null;


    private void addRulesColorinViewColor(String cat, int img,ViewGroup parent){


        Log.d("msg","error");
        View view = LayoutInflater.from(context).inflate(R.layout.cat_txtview_chip_bg_btn, null);
        RelativeLayout rltAddedChpisview = view.findViewById(R.id.rltAddedChpisview);
        rltAddedChpisview.setVisibility(View.GONE);
        TextView txtview = view.findViewById(R.id.txt_cat);
        txtview.clearAnimation();
//        txtview.setVisibility(View.INVISIBLE);
        txtview.setText(cat + "");
        txtview.setBackgroundResource(img);
        view.setTag(cat + "");
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {

                if(betValidation())


                {
                    Log.d("Debug","");
                    rltAddedChpisview.setVisibility(View.VISIBLE);
                    coloramountselected = (String) view1.getTag();
                    TextView txt = view1.findViewById(R.id.txt_cat);
                    lnrfollowColr.removeAllViews();
                    addChipsonViewColor();
                    putbet(coloramountselected);
                    Toast.makeText(context, ""+coloramountselected, Toast.LENGTH_SHORT).show();
                }

            }
        });
        parent.addView(view);

    }

    private boolean betValidation(){

        if (isConfirm) {

            Functions.showToast(context, "Bet Already Confirmed So Not Allowed to Put again");
            return false;

        } else if (!canbet) {
            Functions.showToast(context, "Game Already Started You can not Bet");
            return false;

        }

        return true;
    }

    private boolean isWine(String cat) {
        return Functions.isStringValid(winning) && winning.equalsIgnoreCase(cat);
    }

    private void setSelectedType(String type) {
        LinearLayout lnrfollow = findViewById(R.id.lnrfollow);
        for (int i = 0; i < lnrfollow.getChildCount(); i++) {
            View view = lnrfollow.getChildAt(i);
            TextView txtview = view.findViewById(R.id.txt_cat);
            RelativeLayout relativeLayout = view.findViewById(R.id.relativeChip);
            if (txtview.getText().toString().equalsIgnoreCase(type)) {
                relativeLayout.setBackgroundResource(R.drawable.glow_circle_bg);
//                txtview.setTextColor(Color.parseColor("#ffffff"));
                ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) relativeLayout.getLayoutParams();
                int _20 = (int) getResources().getDimension(R.dimen.chip_up);
                mlp.setMargins(0, _20, 0, 0);
            } else {
                relativeLayout.setBackgroundResource(R.drawable.glow_circle_bg_transparent);
                ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) relativeLayout.getLayoutParams();
                mlp.setMargins(0, 0, 0, 0);
//                txtview.setTextColor(ContextCompat.getColor(this,R.color.colorPrimary));
            }
        }
    }

    private void setSelectedTypeColor(String type) {
        LinearLayout lnrfollow = findViewById(R.id.lnrfollowColr);
        for (int i = 0; i < lnrfollow.getChildCount(); i++) {
            View view = lnrfollow.getChildAt(i);
            TextView txtview = view.findViewById(R.id.txt_cat);
            RelativeLayout relativeLayout = view.findViewById(R.id.relativeChip);
            if (txtview.getText().toString().equalsIgnoreCase(type)) {
                relativeLayout.setBackgroundResource(R.drawable.glow_circle_bg);
//                txtview.setTextColor(Color.parseColor("#ffffff"));
                ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) relativeLayout.getLayoutParams();
                int _20 = (int) getResources().getDimension(R.dimen.chip_up);
                mlp.setMargins(0, _20, 0, 0);
            } else {
                relativeLayout.setBackgroundResource(R.drawable.glow_circle_bg_transparent);
                ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) relativeLayout.getLayoutParams();
                mlp.setMargins(0, 0, 0, 0);
//                txtview.setTextColor(ContextCompat.getColor(this,R.color.colorPrimary));
            }
        }
    }


    private void getStatus() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Const.ColorPrediction,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // progressDialog.dismiss();
                        try {
                            Functions.LOGE("AndharBahar",""+response);
                            Log.v("responce" , response);

                            JSONObject jsonObject = new JSONObject(response);
                            String code = jsonObject.getString("code");
                            String message = jsonObject.getString("message");

                            if (code.equalsIgnoreCase("200")) {

                                JSONArray arraygame_dataa = jsonObject.getJSONArray("game_data");
                                JSONArray online_users = jsonObject.getJSONArray("online_users");
                                int my_ander_bet = jsonObject.optInt("my_ander_bet");
                                int my_bahar_bet = jsonObject.optInt("my_bahar_bet");
                                txt_catander.setText(""+my_ander_bet);
                                txt_catbahar.setText(""+my_bahar_bet);
                                int online = jsonObject.getInt("online");
                                ((TextView) findViewById(R.id.tvonlineuser))
                                        .setText(""+ online);

                                for (int i = 0; i < arraygame_dataa.length() ; i++) {
                                    JSONObject welcome_bonusObject = arraygame_dataa.getJSONObject(i);

                                    //  GameStatus model = new GameStatus();
                                    game_id  = welcome_bonusObject.getString("id");
                                    txt_gameId.setText("GAME ID "+game_id);



//                                    main_card  = welcome_bonusObject.getString("main_card");
                                    // txt_min_max.setText("Min-Max: "+main_card);
                                    status  = welcome_bonusObject.getString("status");
                                    winning  = welcome_bonusObject.getString("winning");
                                    String end_datetime  = welcome_bonusObject.getString("end_datetime");
                                    added_date  = welcome_bonusObject.getString("added_date");
                                    time_remaining  = welcome_bonusObject.optInt("time_remaining");
                                    //  updated_date  = welcome_bonusObject.getString("updated_date");


//                                    String uri1 = "@drawable/" + main_card.toLowerCase();  // where myresource " +
//                                    int imageResource1 = getResources().getIdentifier(uri1, null,
//                                            getPackageName());
//                                    Picasso.get().load(imageResource1).into(imgmaincard);
//                                    Picasso.get().load(imageResource1).into(imgmaincardsvaluehiostory);

                                    Random r = new Random();
                                    int randomNumber = r.nextInt(100);

                                }
                                String onlineuSer = jsonObject.getString("online");
                                txt_online.setText("Online User "+onlineuSer);
                                JSONArray arrayprofile = jsonObject.getJSONArray("profile");

                                for (int i = 0; i < arrayprofile.length() ; i++) {
                                    JSONObject profileObject = arrayprofile.getJSONObject(i);

                                    //  GameStatus model = new GameStatus();
                                    user_id  = profileObject.getString("id");
                                    user_id_player1 = user_id;
                                    name  = profileObject.getString("name");
                                    wallet  = profileObject.getString("wallet");

                                    profile_pic  = profileObject.getString("profile_pic");

                                    Picasso.get().load(Const.IMGAE_PATH + profile_pic).into(imgpl1circle);

                                    //  txtBallence.setText(wallet);
                                    txtName.setText(name);

                                }


                                JSONArray arraypgame_cards = jsonObject.getJSONArray("game_cards");

                                for (int i = 0; i < arraypgame_cards.length() ; i++) {
                                    JSONObject cardsObject = arraypgame_cards.getJSONObject(i);

                                    //  GameStatus model = new GameStatus();
                                    String card  = cardsObject.getString("card");


                                    Log.d("Debug","card"+ card  );
                                    aaraycards.add(card);

                                }
//New Game Started here ------------------------------------------------------------------------
                                Functions.LOGE("GameActivity","status : "+status+" isGameBegning : "+isGameBegning);
                                if (status.equals("0") && !isGameBegning){

                                    VisiblePleasewaitforNextRound(false);
                                    if (counttimerforcards != null){
                                        counttimerforcards.cancel();
                                    }

                                    if (counttimerforcardsforAnimation != null){
                                        counttimerforcardsforAnimation.cancel();
                                    }

                                    restartGame();
                                    Functions.LOGE("GameActivity","added_date : "+added_date);
                                    startGameCountDown();

                                }
                                else if (status.equals("0") && isGameBegning){

                                }

//Game Started here
                                if (status.equals("1") && !isGameBegning){
                                    VisiblePleasewaitforNextRound(true);


                                }

                                if (status.equals("1") && isGameBegning){


                                    isGameBegning = false;
                                    Log.v("game" ,"stoped");
                                    if (aaraycards.size() > 0){
                                        if (counttimerforcardsforAnimation != null){
                                            counttimerforcardsforAnimation.cancel();
                                        }

                                        if (counttimerforcards != null){
                                            counttimerforcards.cancel();
                                        }


                                        counttimerforcards = new CountDownTimer(aaraycards.size()*cardAnimationIntervel, cardAnimationIntervel) {

                                            @Override
                                            public void onTick(long millisUntilFinished) {
                                                isCardsDisribute = true;
                                                int y = countvaue % 2;

                                                makeWinnertoPlayer("");
                                                makeLosstoPlayer("");


                                                if (y == 0) {



                                                    int chieldcount =   lnrandarpatte.getChildCount();
                                                    View view = null;
                                                    if (chieldcount > 0){
                                                        view = lnrandarpatte.getChildAt(chieldcount-1);
                                                    }else {
                                                        view = lnrandarpatte.getChildAt(chieldcount);
                                                    }


                                                    int[] locationimgandar = new int[2];
                                                    int[] locationlnrandar = new int[2];
                                                    imgCardsandar.getLocationOnScreen(locationimgandar);
                                                    lnrandarpatte.getLocationOnScreen(locationlnrandar);

                                                    //----------------------For Width ----------------
                                                    int[] locationimgandarwidth = new int[2];
                                                    int[] locationlnrandarwidth = new int[2];
                                                    imgCardsandar.getLocationOnScreen(locationimgandarwidth);
                                                    if (view != null){
                                                        view.getLocationOnScreen(locationlnrandarwidth);
                                                    }else {

                                                    }

                                                    TranslateAnimation animation = null;
                                                    int finalwidht = 0;

                                                    if (locationimgandarwidth[0] < 2){

                                                    }else {
                                                        finalwidht = locationlnrandarwidth[0]-locationimgandarwidth[0] ;
                                                        animation = new TranslateAnimation(0, finalwidht,0 , locationlnrandar[1] - locationimgandar[1]);
                                                        animation.setRepeatMode(0);
                                                        animation.setDuration(300);
                                                        animation.setFillAfter(false);
                                                        imgCardsandar.setVisibility(View.VISIBLE);
//
                                                        imgCardsandar.startAnimation(animation);

                                                        firstcount++;
                                                        //  Log.v("width-1" , locationlnrandarwidth[0] +" / "+locationimgandarwidth[0]);


                                                    }

                                                    //   Log.v("width-1" , locationlnrandarwidth[0] +" / "+locationimgandarwidth[0]);
//                                                    addCardinLayout(aaraycards.get(countvaue),countvaue,lnrandarpatte);



                                                }else {


                                                    int finalwidhtbahar = 0;
                                                    int chieldcount =   lnrbaharpatte.getChildCount();
                                                    View view1 = null;
                                                    if (chieldcount > 0){
                                                        view1 = lnrbaharpatte.getChildAt(chieldcount-1);
                                                    }else {
                                                        view1 = lnrbaharpatte.getChildAt(chieldcount);
                                                    }

                                                    int[] locationimgbaharwidth = new int[2];
                                                    int[] locationlnrbaharwidth = new int[2];
                                                    imgCardsbahar.getLocationOnScreen(locationimgbaharwidth);
                                                    if (view1 != null){
                                                        view1.getLocationOnScreen(locationlnrbaharwidth);
                                                    }else {

                                                    }

                                                    //----------------------Close ---------------------


                                                    int[] locationimgbahar = new int[2];
                                                    int[] locationlnrbahar = new int[2];
                                                    imgCardsbahar.getLocationOnScreen(locationimgbahar);
                                                    lnrbaharpatte.getLocationOnScreen(locationlnrbahar);


                                                    if (locationimgbaharwidth[0] < 2) {


                                                    }else {

                                                        finalwidhtbahar = locationlnrbaharwidth[0]-locationimgbaharwidth[0] ;
                                                        TranslateAnimation animation = new TranslateAnimation(0, finalwidhtbahar,0 , locationlnrbahar[1]-locationimgbahar[1]);
                                                        animation.setRepeatMode(0);
                                                        animation.setDuration(300);
                                                        animation.setFillAfter(false);
                                                        imgCardsbahar.startAnimation(animation);
                                                    }

                                                    //   Log.v("width" , locationimgbaharwidth +" / "+locationlnrbaharwidth[0]);
//                                                    addCardinLayout(aaraycards.get(countvaue),countvaue,lnrbaharpatte);
                                                }


                                                if (countvaue > 0 && countvaue < 6){
                                                    rlt1to5.setBackground(ContextCompat.getDrawable(context, R.drawable.background_border_withcolor));

                                                }

                                                if (countvaue > 5 && countvaue < 11){
                                                    rlt6to10.setBackground(ContextCompat.getDrawable(context, R.drawable.background_border_withcolor));

                                                }

                                                if (countvaue > 10 && countvaue < 16){
                                                    rlt11to15.setBackground(ContextCompat.getDrawable(context, R.drawable.background_border_withcolor));

                                                }

                                                if (countvaue > 15 && countvaue < 26){
                                                    rlt16to25.setBackground(ContextCompat.getDrawable(context, R.drawable.background_border_withcolor));

                                                }

                                                if (countvaue > 25 && countvaue < 31){
                                                    rlt26to30.setBackground(ContextCompat.getDrawable(context, R.drawable.background_border_withcolor));

                                                }

                                                if (countvaue > 30 && countvaue < 36){
                                                    rlt31to35.setBackground(ContextCompat.getDrawable(context, R.drawable.background_border_withcolor));

                                                }

                                                if (countvaue > 35 && countvaue < 41){
                                                    rlt36to40.setBackground(ContextCompat.getDrawable(context, R.drawable.background_border_withcolor));

                                                }

                                                if (countvaue > 41){
                                                    rlt41more.setBackground(ContextCompat.getDrawable(context, R.drawable.background_border_withcolor));

                                                }

                                                countvaue++;
                                            }

                                            @Override
                                            public void onFinish() {


                                                VisiblePleasewaitforNextRound(true);
                                                isCardsDisribute = false;

                                                makeWinnertoPlayer(user_id_player1);


                                            }


                                        };

                                        counttimerforcards.start();


                                    }



                                }else {


                                }

                            } else {
                                if (jsonObject.has("message")) {

                                    Functions.showToast(context, message);


                                }


                            }

                            if (status.equals("1")) {
                                //  txtGameRunning.setVisibility(View.VISIBLE);
                                txtGameBets.setVisibility(View.GONE);
                            } else {
                                // txtGameRunning.setVisibility(View.GONE);
                                txtGameBets.setVisibility(View.VISIBLE);

                                makeWinnertoPlayer("");
                                makeLosstoPlayer("");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //  progressDialog.dismiss();
                Functions.showToast(context, "Something went wrong");

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
                params.put("user_id", prefs.getString("user_id", ""));
                params.put("token", prefs.getString("token", ""));
                params.put("room_id", "1");
                Log.d("params_clr_pred", String.valueOf(params));
                // params.put("token", "9959cbfce148e91db099d4936b1a9b66");
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

    private void startGameCountDown() {
        if (counttimerforstartgame != null) {
            counttimerforstartgame.cancel();
        }

        Functions.LOGE("GameActivity","time_remaining : "+time_remaining);
        counttimerforstartgame = new CountDownTimer((time_remaining * timer_interval),timer_interval) {

            @Override
            public void onTick(long millisUntilFinished) {
                isCountDownStart = true;
                count--;
                txtGameFinish.setVisibility(View.VISIBLE);
                txtGameFinish.setText("" + millisUntilFinished/timer_interval);

                playSound(COUNTDOWN_SOUND,false);

//                addDummyChipsonAndharBahar();
//                addDummyChipsonAndharBahar();
//                addDummyChipsonAndharBahar();
//                addDummyChipsonAndharBahar();
//                addDummyChipsonAndharBahar();
            }

            @Override
            public void onFinish() {
                isCountDownStart = false;
                stopSound(COUNTDOWN_SOUND);
                canbet = false;

                txtGameFinish.setVisibility(View.INVISIBLE);
                getStatus();
                counttimerforstartgame.cancel();
            }


        };

        counttimerforstartgame.start();
    }

    DisplayMetrics metrics;
    int andharWidth = 0,andharHeight = 0;
    View lnrAndharBoard,lnrBaharBoard;
    float andharX,andharY;
    private void initDisplayMetrics(){
        lnrAndharBoard = findViewById(R.id.lnrAndharBoard);
        lnrBaharBoard = findViewById(R.id.lnrBaharBoard);
        metrics = new DisplayMetrics();

        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        lnrAndharBoard.post(new Runnable() {
            @Override
            public void run() {
                andharWidth = lnrAndharBoard.getWidth();
                andharHeight = lnrAndharBoard.getHeight();

                andharX = lnrAndharBoard.getX();
                andharY = lnrAndharBoard.getY();
            }
        });
    }

    int chips_width  = 0;
    private ImageView creatDynamicChips() {
        ImageView chips = new ImageView(this);

        int chips_size = (int) getResources().getDimension(R.dimen.chips_size);

        chips.setImageDrawable(Functions.getDrawable(context,ChipsPicker.getInstance().getChip()));

        chips.setLayoutParams(new ViewGroup.LayoutParams(chips_size, chips_size));

        chips.post(new Runnable() {
            @Override
            public void run() {
                chips_width = chips.getWidth();
            }
        });

        return chips;
    }

    private void addDummyChipsonAndharBahar() {

        if(!isCountDownStart)
            return;

        View AndharFromView = lnrOnlineUser;
        View AndharToView = lnrAndharBoard;

        View BaharFromView = lnrOnlineUser;
        View BaharToView = lnrBaharBoard;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ChipsAnimations(AndharFromView,AndharToView,rl_AnimationView);
                ChipsAnimations(BaharFromView,BaharToView,rl_AnimationView);
            }
        },500);

    }

    ImageView  dummyUserleft,dummyUserright;
    public View leftaddView() {
        dummyUserleft = new ImageView(this);
//        dummyUserleft.setImageResource(R.drawable.ic_user_male);
        int user_size = (int) getResources().getDimension(R.dimen.user_size);
        dummyUserleft.setLayoutParams(new ViewGroup.LayoutParams(user_size, user_size));
        rl_AnimationView.addView(dummyUserleft);


        int leftMargin = 0;
//        leftMargin = new Random().nextInt(metrics.widthPixels - dummyUser.getWidth());;
        int topMargin = new Random().nextInt(metrics.heightPixels - 2*dummyUserleft.getHeight());;

        Functions.setMargins(dummyUserleft, leftMargin, topMargin, 0, 0);

        return dummyUserleft;
    }
    public View rightaddView() {
        dummyUserright = new ImageView(this);
//        dummyUserright.setImageResource(R.drawable.ic_user_male);
        int user_size = (int) getResources().getDimension(R.dimen.user_size);
        dummyUserright.setLayoutParams(new RelativeLayout.LayoutParams(user_size, user_size));

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)dummyUserright.getLayoutParams();
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        dummyUserright.setLayoutParams(params);

        rl_AnimationView.addView(dummyUserright);


        int leftMargin = 0;
//        leftMargin = new Random().nextInt(metrics.widthPixels - dummyUser.getWidth());;
        int topMargin = new Random().nextInt(metrics.heightPixels - 2*dummyUserright.getHeight());;

        Functions.setMargins(dummyUserleft, leftMargin, topMargin, 0, 0);

        return dummyUserleft;
    }

    private void putbet(final String type) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Const.CP_PUTBET,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        // progressDialog.dismiss();
                        Functions.LOGE("putbet",Const.CP_PUTBET+"\n"+response);
                        try {


                            JSONObject jsonObject = new JSONObject(response);
                            String code = jsonObject.getString("code");
                            String message = jsonObject.getString("message");


                            if (code.equalsIgnoreCase("200")) {
                                bet_id = jsonObject.getString("bet_id");
                                wallet = jsonObject.getString("wallet");
                                txtBallence.setText(wallet);
                                Functions.showToast(context, ""+message);

                                if (type.equals("0")){
                                    andharPutBetVisiblity(true);
//                                    txt_catander.setText(""+betvalue);

                                }else {
                                    baharPutBetVisiblity(true);
//                                    txt_catbahar.setText(""+betvalue);
                                }

                                betvalue = "";


                            } else {
                                if (jsonObject.has("message")) {

                                    Functions.showToast(context, message);


                                }


                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //  progressDialog.dismiss();
                Functions.showToast(context, "Something went wrong");

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
                params.put("user_id", prefs.getString("user_id", ""));
                params.put("token", prefs.getString("token", ""));
                params.put("game_id", game_id);
                params.put("bet", type);
//                params.put("amount", betvalue);
                params.put("amount", tagamountselected);    //new
                //  params.put("token", "9959cbfce148e91db099d4936b1a9b66");
                Functions.LOGE("putbet",Const.CP_PUTBET+"\n"+params);
                Log.d( "getParams_place_bet:", String.valueOf(params));
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

    private void cancelbet() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Const.CP_CENCEL_BET,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        // progressDialog.dismiss();
                        try {


                            JSONObject jsonObject = new JSONObject(response);
                            String code = jsonObject.getString("code");
                            String message = jsonObject.getString("message");

                            if (code.equals("200")){

                                wallet = jsonObject.getString("wallet");
                                txtBallence.setText(wallet);
                                removeBetChips();


                            }
                            Functions.showToast(context, message);



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //  progressDialog.dismiss();
                Functions.showToast(context, "Something went wrong");

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
                params.put("user_id", prefs.getString("user_id", ""));
                params.put("token", prefs.getString("token", ""));

                params.put("game_id", game_id);
                params.put("bet_id", bet_id);
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

    private void repeatBet() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Const.CP_REPEAT_BET,
                new Response.Listener<String>() {


                    @Override
                    public void onResponse(String response) {

                        Log.v("Repeat Responce" , response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String code = jsonObject.getString("code");
                            String message = jsonObject.getString("message");

                            if (code.equals("200")){

                                wallet = jsonObject.getString("wallet");
                                String  bet = jsonObject.getString("bet");
                                // bet_id = jsonObject.getString("bet_id");
                                String amount = jsonObject.getString("amount");
                                txtBallence.setText(wallet);
                                betvalue = amount;
                                betplace = bet;
                                if (bet.equals("0")){

                                    andharPutBetVisiblity(true);

//                                    txt_catander.setText(""+amount);
                                }else {
//                                    txt_catbahar.setText(""+amount);

                                    baharPutBetVisiblity(true);
                                }

                            }
                            Functions.showToast(context, message);



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //  progressDialog.dismiss();
                Functions.showToast(context, "Something went wrong");

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
                params.put("user_id", prefs.getString("user_id", ""));
                params.put("token", prefs.getString("token", ""));

                params.put("game_id", game_id);
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
    protected void onDestroy() {
        DestroyGames();
        releaseSoundpoll();
        super.onDestroy();
    }

    private void DestroyGames(){

        if (counttimerforstartgame != null){
            counttimerforstartgame.cancel();
        }

        if (counttimerforcards != null){
            counttimerforcards.cancel();
        }

        if (timerstatus !=null ){
            timerstatus.cancel();
        }

        stopPlaying();
        releaseSoundpoll();
    }

    @Override
    protected void onResume() {
        super.onResume();
        isInPauseState = false;
    }

    @Override
    protected void onPause() {
        super.onPause();

        isInPauseState = true;
        if (mp !=null ){
            mp.stop();
            mp.release();
        }
        pauseSoundPool();
    }

    void pauseSoundPool(){
        if(mSoundPool != null)
        {
            mSoundPool.pause(COUNTDOWN_SOUND);
            mSoundPool.pause(CHIPS_SOUND);
            mSoundPool.pause(CARD_SOUND);
        }
    }

    String user_id_player1 = "";
    RelativeLayout rltwinnersymble1;
    public void makeWinnertoPlayer(String chaal_user_id) {

        rltwinnersymble1.setVisibility(View.GONE);
        restartWiningAnimation();

        if (chaal_user_id.equals(user_id_player1)) {
            if(Functions.isStringValid(betplace) && betplace.equalsIgnoreCase(winning))
                PlaySaund(R.raw.tpb_battle_won);

            restartRuleView();
            highlightWinView();
        }

    }

    private void restartWiningAnimation() {
        if(winnerView != null)
        {
            Log.d("Debug","winner");
            winnerView.setText("");
            winnerView.setBackground(null);
            winnerView.setVisibility(View.GONE);
        }
    }

    public void makeLosstoPlayer(String chaal_user_id) {

        rltwinnersymble1.setVisibility(View.GONE);
        rtllosesymble1.setVisibility(View.GONE);

        if (chaal_user_id.equals(user_id_player1)) {
            PlaySaund(R.raw.tpb_battle_won);
//            rtllosesymble1.setVisibility(View.VISIBLE);
        }

    }

    public static final int COUNTDOWN_SOUND = 0;
    public static final int CHIPS_SOUND = 1;
    public static final int CARD_SOUND = 2;

    SoundPool mSoundPool;
    HashMap<Integer, Integer> mSoundMap;
    private void initSoundPool() {
        mSoundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 100);
        mSoundMap = new HashMap<Integer, Integer>();

        if(mSoundPool != null){
            mSoundMap.put(COUNTDOWN_SOUND, mSoundPool.load(this, R.raw.teenpattitick, 1));
            mSoundMap.put(CHIPS_SOUND, mSoundPool.load(this, R.raw.teenpattichipstotable, 1));
            mSoundMap.put(CARD_SOUND, mSoundPool.load(this, R.raw.teenpatticardflip_android, 1));
        }
    }
    /*
     *Call this function from code with the sound you want e.g. playSound(SOUND_1);
     */
    boolean isTimerStar ;
    public void playSound(int sound,boolean loop) {

        if(!SharePref.getInstance().isSoundEnable())
            return;

        AudioManager mgr = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
        float streamVolumeCurrent = mgr.getStreamVolume(AudioManager.STREAM_MUSIC);
        float streamVolumeMax = mgr.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        float volume = streamVolumeCurrent / streamVolumeMax;

        if(mSoundPool != null){
            mSoundPool.play(mSoundMap.get(sound), volume, volume, 1, loop ? -1 : 0, 1.0f);
        }
    }



    public void stopSound(int sound){
        if(mSoundMap != null && mSoundMap.size() > 0 && mSoundPool != null)
            mSoundPool.stop(mSoundMap.get(sound));
    }

    public void releaseSoundpoll(){
        stopSound(COUNTDOWN_SOUND);
        stopSound(CARD_SOUND);
        stopSound(CHIPS_SOUND);


        if(mSoundPool != null)
        {
            mSoundPool.unload(COUNTDOWN_SOUND);
            mSoundPool.unload(CARD_SOUND);
            mSoundPool.unload(CHIPS_SOUND);
            mSoundMap.clear();
            mSoundPool.release();
            mSoundPool = null;
        }
    }

    public void openBuyChipsActivity(View view) {
        openBuyChipsListActivity();
    }

    private void openBuyChipsListActivity() {
        startActivity(new Intent(context, BuyChipsList.class));
    }

    public void openGameRules(View view) {
        DialogRulesColorPrediction.getInstance(context).show();
    }

    private void highlightWinView(){
        int winnerview = Integer.parseInt(winning);

        ViewGroup viewtype = winnerview <= 4 ? lnrfollowColr2 : lnrfollowColr3;
        for (int i = 0; i < viewtype.getChildCount() ; i++) {
            View ruleview = viewtype.getChildAt(i);

            TextView txt_cat = ruleview.findViewById(R.id.txt_cat);
            TextView txtWinnerview = ruleview.findViewById(R.id.txtWinnerview);

            if(isWine(String.valueOf(txt_cat.getText())))
            {
                txtWinnerview.clearAnimation();
                txtWinnerview.setBackground(Functions.getDrawable(context,R.drawable.background_border_highlight));
                txtWinnerview.startAnimation(blinksAnimation);
                txt_cat.setVisibility(View.INVISIBLE);
                txtWinnerview.setText(txt_cat.getText().toString().trim());
                winnerView = txtWinnerview;
                boolean iswin = Functions.isStringValid(betplace) && betplace.equalsIgnoreCase(winning) ? true : false;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        AnimationUtils(iswin);
                    }
                },500);
                break;
            }

        }

    }

    private void restartRuleView(){
        addChipsonViewColor();
        addChipsonViewColor2();
        addChipsonViewColor3();
    }

    private void AnimationUtils(boolean iswin) {

        coins_count =10;

        boolean isbetonAndhar = betplace.equals(""+ANDHAR) ? true : false;
        boolean isbetonBahar = betplace.equals(""+BAHAR) ? true : false;

//        View fromView = null;
        View toView = null;

        if(iswin)
        {
            toView = ChipstoUser;
        }
        else {
            toView = lnrOnlineUser;
        }

//        View finalFromView = fromView;
        View finalToView = toView;
        new CountDownTimer(2000,200) {
            @Override
            public void onTick(long millisUntilFinished) {
                coins_count--;
                ChipsAnimations(winnerView, finalToView);
//                ChipsAnimations(rltbaharbet, finalToView);
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }

}