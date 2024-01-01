//package com.dragon.multigame.Activity;
//
//import static com.dragon.multigame.GAMES.ANDHAR_BAHAR;
//import static com.dragon.multigame.GAMES.ANIMAL_ROULETTE;
//import static com.dragon.multigame.GAMES.CAR_ROULETTE;
//import static com.dragon.multigame.GAMES.COLOUR_PREDICTION;
//import static com.dragon.multigame.GAMES.CUSTOM_TABLE;
//import static com.dragon.multigame.GAMES.DEAL_RUMMY;
//import static com.dragon.multigame.GAMES.DRAGON_TIGER;
//import static com.dragon.multigame.GAMES.HEAD_TAIL;
//import static com.dragon.multigame.GAMES.JACKPOT_3PATTI;
//import static com.dragon.multigame.GAMES.POINT_RUMMY;
//import static com.dragon.multigame.GAMES.POKER_GAME;
//import static com.dragon.multigame.GAMES.POKER_GAME;
//import static com.dragon.multigame.GAMES.POOL_RUMMY;
//import static com.dragon.multigame.GAMES.PRIVATE_RUMMY;
//import static com.dragon.multigame.GAMES.PRIVATE_TABLE;
//import static com.dragon.multigame.GAMES.SEVEN_UP_DOWN;
//import static com.dragon.multigame.GAMES.TEENPATTI;
//import static com.dragon.multigame.LocationManager.GpsUtils.ENABLE_LOCATION_CODE;
//import static com.dragon.multigame.Utils.Functions.convertDpToPixel;
////import static com.dragon.multigame.Utils.Variables.POKER_GAME;
//
//import android.Manifest;
//import android.app.Activity;
//import android.app.AlertDialog;
//import android.app.Dialog;
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.content.pm.PackageInfo;
//import android.content.pm.PackageManager;
//import android.graphics.Typeface;
//import android.graphics.drawable.ColorDrawable;
//import android.location.Address;
//import android.location.Geocoder;
//import android.location.LocationManager;
//import android.media.MediaPlayer;
//import android.net.Uri;
//import android.os.Build;
//import android.os.Bundle;
//import android.os.Handler;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.Window;
//import android.view.WindowManager;
//import android.view.animation.Animation;
//import android.view.animation.AnimationUtils;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.FrameLayout;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.RelativeLayout;
//import android.widget.SeekBar;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.ActionBar;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.GridLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.android.volley.AuthFailureError;
//import com.android.volley.Request;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
//import com.dragon.multigame.Adapter.HomegameListAdapter;
//import com.dragon.multigame.BaseActivity;
//import com.dragon.multigame.Comman.DialogRestrictUser;
//import com.dragon.multigame.Comman.DialogSettingOption;
//import com.dragon.multigame.Details.GameDetails_A;
//import com.dragon.multigame.Fragments.ActiveTables_BF;
//import com.dragon.multigame.Fragments.UserInformation_BT;
//import com.dragon.multigame.Interface.ApiRequest;
//import com.dragon.multigame.Interface.Callback;
//import com.dragon.multigame.Interface.ClassCallback;
//import com.dragon.multigame.LocationManager.GetLocationlatlong;
//import com.dragon.multigame.LocationManager.GpsUtils;
//import com.dragon.multigame.Menu.DialogReferandEarn;
//import com.dragon.multigame.MyFlowLayout;
//import com.dragon.multigame.R;
//import com.dragon.multigame.RedeemCoins.RedeemActivity;
//import com.dragon.multigame.SampleClasses.Const;
//import com.dragon.multigame.Utils.Animations;
//import com.dragon.multigame.Utils.Functions;
//import com.dragon.multigame.Utils.SharePref;
//import com.dragon.multigame.Utils.Sound;
//import com.dragon.multigame.Utils.Variables;
//import com.dragon.multigame._AdharBahar.Andhar_Bahar_NewUI;
//import com.dragon.multigame._AnimalRoulate.AnimalRoulette_A;
//import com.dragon.multigame._CarRoullete.CarRoullete_A;
//import com.dragon.multigame._CoinFlip.HeadTail_A;
//import com.dragon.multigame._ColorPrediction.ColorPrediction;
//import com.dragon.multigame._DragonTiger.DragonTiger_A;
//import com.dragon.multigame._LuckJackpot.LuckJackPot_A;
//import com.dragon.multigame._Poker.Fragment.PokerActiveTables_BF;
//import com.dragon.multigame._RummyDeal.DialogDealType;
//import com.dragon.multigame._RummyDeal.Fragments.RummyDealActiveTables_BF;
//import com.dragon.multigame._RummyDeal.RummyDealGame;
//import com.dragon.multigame._RummyPull.Fragments.RummyActiveTables_BF;
//import com.dragon.multigame._SevenUpGames.SevenUp_A;
//import com.dragon.multigame._TeenPatti.PublicTable_New;
//import com.dragon.multigame.account.LoginScreen;
//import com.dragon.multigame.model.HomescreenModel;
//import com.google.android.gms.maps.model.LatLng;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.messaging.FirebaseMessaging;
//import com.rahman.dialog.Activity.SmartDialog;
//import com.rahman.dialog.ListenerCallBack.SmartDialogClickListener;
//import com.rahman.dialog.Utilities.SmartDialogBuilder;
//import com.squareup.picasso.Picasso;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.text.NumberFormat;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Locale;
//import java.util.Map;
//import java.util.Random;
//import java.util.concurrent.TimeUnit;
//
//public class Homepage extends BaseActivity {
//
//    List<String> ban_states = new ArrayList<>();
//    Animation animBounce, animBlink;
//    public static final String MY_PREFS_NAME = "Login_data";
//    ImageView imgLogout, imgshare, imaprofile;
//    ImageView imgpublicGame, imgPrivategame, ImgCustomePage, ImgVariationGane, iv_andher;
//    private String user_id, name, mobile, profile_pic, referral_code, wallet, game_played, bank_detail, adhar_card, upi;
//    private TextView txtName, txtwallet, txtproname;
//    LinearLayout lnrbuychips, lnrinvite, lnrmail, lnrsetting, lnrvideo;
//    Typeface helvatikaboldround, helvatikanormal;
//    public String token = "";
//    private String game_for_private, app_version;
//    SeekBar sBar;
//    SeekBar sBarpri;
//    ImageView imgCreatetable, imgCreatetablecustom, imgclosetoppri, imgclosetop;
//    int pval = 1;
//    int pvalpri = 1;
//    Button btnCreatetable;
//    Button btnCreatetablepri;
//    TextView txtStart, txtLimit, txtwalletchips,
//            txtwalletchipspri, txtBootamount, txtPotLimit, txtNumberofBlind,
//            txtMaximumbetvalue;
//    TextView txtStartpri, txtLimitpri, txtBootamountpri, txtPotLimitpri, txtNumberofBlindpri, txtMaximumbetvaluepri;
//    RelativeLayout rltimageptofile;
//    int version = 0;
//
//    RelativeLayout rootView;
//
//    public static String str_colr_pred = "";
//    String base_64 = "";
//    ProgressDialog progressDialog;
//    Activity context = this;
//
//    String REFERRAL_AMOUNT = "referral_amount";
//    String JOINING_AMOUNT = "joining_amount";
//
//
//    Random random = new Random();
//
//    private void emitBubbles() {
//        // It will create a thread and attach it to
//        // the main thread
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//
//                int size = random.nextInt(250);
////                bubbleEmitter.emitBubble(size);
////                bubbleEmitter.setColors(android.R.color.transparent,
////                        android.R.color.holo_blue_light,
////                        android.R.color.holo_blue_bright);
////                emitBubbles();
//            }
//        }, random.nextInt(500));
//    }
//
//
////    BubbleEmitterView bubbleEmitter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_homepage);
//
//
//        //  View supportButton = findViewById(R.id.support_button);
//       // Button supportButton = findViewById(R.id.support_button);
//
///*
//       findViewById(R.id.lnr_support).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Display the support dialog
//                showSupportDialog();
//            }
//        });*/
//
//
//        findViewById(R.id.lnr_support).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                //openTelegramLink();
//                // Display the support dialog
//                // showSupportDialog();
//
//                String telegramLink = "https://t.me/Bikaaa_2023";
//
//                // Create an intent to open the Telegram link
//                Intent telegramIntent = new Intent(Intent.ACTION_VIEW);telegramIntent.setData(Uri.parse(telegramLink));
////
////               // Start the Telegram app
//                startActivity(telegramIntent);
//            }
//        });
//
//
//
//
//        soundMedia = new Sound();
//        initGamesTabs();
//
//        SharePref.getInstance().init(context);
//
//        findViewById(R.id.rltDragonTiger).setVisibility(SharePref.getIsDragonTigerVisible()
//                ? View.VISIBLE : View.GONE);
//
//        findViewById(R.id.rltTeenpatti).setVisibility(SharePref.getIsTeenpattiVisible()
//                ? View.VISIBLE : View.GONE);
//
//        findViewById(R.id.rltPrivate).setVisibility(SharePref.getIsPrivateVisible()
//                ? View.VISIBLE : View.GONE);
//
//        findViewById(R.id.rltCustom).setVisibility(SharePref.getIsCustomVisible()
//                ? View.VISIBLE : View.GONE);
//
//        findViewById(R.id.rltAndharbhar).setVisibility(SharePref.getIsAndharBaharVisible()
//                ? View.VISIBLE : View.GONE);
//
//        findViewById(R.id.rltRummy).setVisibility(SharePref.getIsRummyVisible()
//                ? View.VISIBLE : View.GONE);
//
//        findViewById(R.id.rltJackpot).setVisibility(Variables.JACKPOTGAME_SHOW ? View.VISIBLE : View.GONE);
//        findViewById(R.id.rltRummyDeal).setVisibility(Variables.RUMMYDEAL_SHOW ? View.VISIBLE : View.GONE);
//        findViewById(R.id.rltRummyPool).setVisibility(Variables.RUMMPOOL_SHOW ? View.VISIBLE : View.GONE);
//        findViewById(R.id.rltSeveUp).setVisibility(Variables.SEVENUPSDOWN_SHOW ? View.VISIBLE : View.GONE);
//        findViewById(R.id.rltCarRoullete).setVisibility(Variables.CARROULETE_SHOW ? View.VISIBLE : View.GONE);
//        findViewById(R.id.rltAnimalRoullete).setVisibility(Variables.ANIMALROULETE_SHOW ? View.VISIBLE : View.GONE);
//
//
//        imgLogout = findViewById(R.id.imgLogout);
//        initHomeScreenModel();
//
//        lnrbuychips = findViewById(R.id.lnrbuychips);
//        lnrmail = findViewById(R.id.lnrmail);
//        lnrinvite = findViewById(R.id.lnrinvite);
//        lnrsetting = findViewById(R.id.lnrsetting);
//        lnrvideo = findViewById(R.id.lnrvideo);
//
//
//        imaprofile = findViewById(R.id.imaprofile);
//
//
//        emitBubbles();
//
//        progressDialog = new ProgressDialog(context);
//        progressDialog.setCancelable(false);
//        progressDialog.setMessage("Loading...");
//
//
//
//
//        FrameLayout home_container = findViewById(R.id.home_container);
//        home_container.setVisibility(View.VISIBLE);
//
//
//        rootView = findViewById(R.id.rlt_animation_layout);
//        RelativeLayout relativeLayout = findViewById(R.id.rlt_parent);
////        SetBackgroundImageAsDisplaySize(context,relativeLayout,R.drawable.splash);
//
//
////        BonusView();
//
//
//
//        // Set fullscreen
//        context.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        helvatikaboldround = Typeface.createFromAsset(getAssets(),
//                "fonts/helvetica-rounded-bold-5871d05ead8de.otf");
//
//        helvatikanormal = Typeface.createFromAsset(getAssets(),
//                "fonts/Helvetica.ttf");
//
//        rltimageptofile = findViewById(R.id.rltimageptofile);
//
//        rltimageptofile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                UserInformation_BT userInformation_bt = new UserInformation_BT(new Callback() {
//                    @Override
//                    public void Responce(String resp, String type, Bundle bundle) {
//                        UserProfile();
//                    }
//                });
//                userInformation_bt.setCancelable(false);
//                userInformation_bt.show(getSupportFragmentManager(),userInformation_bt.getTag());
//            }
//        });
//
//        imgpublicGame = (ImageView) findViewById(R.id.imgpublicGame);
//        imgPrivategame = (ImageView) findViewById(R.id.imgPrivategame);
//        ImgCustomePage = (ImageView) findViewById(R.id.ImgCustomePage);
//        ImgVariationGane = (ImageView) findViewById(R.id.ImgVariationGane);
//        iv_andher = (ImageView) findViewById(R.id.iv_andher);
//        txtName = findViewById(R.id.txtName);
//        txtName.setTypeface(helvatikaboldround);
//        txtwallet = findViewById(R.id.txtwallet);
//        txtwallet.setTypeface(helvatikanormal);
//        txtproname = findViewById(R.id.txtproname);
//        txtproname.setTypeface(helvatikaboldround);
//        TextView txtMail = findViewById(R.id.txtMail);
//
//
//
//
//        // load the animation
//        animBounce = AnimationUtils.loadAnimation(getApplicationContext(),
//                R.anim.bounce);
//
//        animBlink = AnimationUtils.loadAnimation(getApplicationContext(),
//                R.anim.blink);
//        imgpublicGame.startAnimation(animBlink);
//        imgpublicGame.startAnimation(animBounce);
//
//
//        imgPrivategame.startAnimation(animBlink);
//        imgPrivategame.startAnimation(animBounce);
//
//
//        ImgCustomePage.startAnimation(animBlink);
//        ImgCustomePage.startAnimation(animBounce);
//        ImgCustomePage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                openCustomeTeenpatti(view);
//            }
//        });
//
//
//        ImgVariationGane.startAnimation(animBlink);
//        ImgVariationGane.startAnimation(animBounce);
//        clickTask();
//
//        FirebaseMessaging.getInstance().getToken()
//                .addOnCompleteListener(new OnCompleteListener<String>() {
//                    @Override
//                    public void onComplete(@NonNull Task<String> task) {
//                        if (!task.isSuccessful()) {
//                            Log.w("TAG", "Fetching FCM registration token failed", task.getException());
//                            return;
//                        }
//
//                        // Get new FCM registration token
//                        token = task.getResult();
//
//                        // Log and toast
//                        // String msg = getString(R.string.msg_token_fmt, token);
//                        // Log.d(TAG, msg);
//                        // Funtions.showToast(context, token);
//                        UserProfile();
//
//                    }
//                });
//
//
//        rotation_animation(((ImageView) findViewById(R.id.imgsetting)),R.anim.rotationback_animation);
//
//
//        findViewById(R.id.lnr_redeem).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(context, RedeemActivity.class));
//
////                LoadFragment(new WalletFragment());
//            }
//        });
//
////        findViewById(R.id.lnr_privacy).setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                startActivity(new Intent(context, Privacy_Policy.class));
////
//////                LoadFragment(new WalletFragment());
////            }
////        });
//
//        findViewById(R.id.lnrhistory).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                startActivity(new Intent(context,MyWinningAcitivity.class));
//                startActivity(new Intent(context, GameDetails_A.class));
//
////                showRedeemDailog();
//            }
//        });
//        findViewById(R.id.lnr_mail).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                showDailyWinCoins();
//            }
//        });
//
//
//        findViewById(R.id.iv_add).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                rotation_animation(findViewById(R.id.iv_add),R.anim.rotationback_animation);
//                UserProfile();
//            }
//        });
//
//
//
//    }
//
//    private void showSupportDialog() {
//
//
//
//
//            // Create a custom dialog layout
//            LayoutInflater inflater = LayoutInflater.from(this);
//            View dialogView = inflater.inflate(R.layout.dialog_support, null);
//
//            // Find the EditText and Button in the dialog layout
//            final EditText questionInput = dialogView.findViewById(R.id.question_input);
//            Button submitButton = dialogView.findViewById(R.id.submit_button);
//
//            // Build the dialog
//            AlertDialog.Builder builder = new AlertDialog.Builder(this);
//            builder.setView(dialogView);
//
//            // Create the dialog
//            final AlertDialog dialog = builder.create();
//
//            // Set a click listener for the "Submit" button
//            submitButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    // Get the user's question from the EditText
//                    String userQuestion = questionInput.getText().toString();
//
//                    // Handle the user's question (e.g., send it to a support service)
//                    // You can add your logic here
//
//                    // Clear the question input
//                    questionInput.setText("");
//
//                    // Dismiss the dialog
//                    dialog.dismiss();
//                }
//            });
//
//            // Show the dialog
//            dialog.show();
//        }
//
//
//
//    String[] gamelist = {
//            "All",
//            "Skills",
////            "Sports",
//    };
//    MyFlowLayout lnrGamesTabs;
//    int tabsCount = 0;
//    private void initGamesTabs() {
//        tabsCount = 0;
//        lnrGamesTabs = findViewById(R.id.lnrGamesTabs);
//        for (String tabs: gamelist) {
//            CreateTabsLayout(tabsCount,tabs);
//            tabsCount++;
//        }
//    }
//
//    private void CreateTabsLayout(final int position, String name) {
//        final View view = Functions.CreateDynamicViews(R.layout.item_gamehistory_tabs,lnrGamesTabs,context);
//        String strtitle = name;
//        view.setTag("" + strtitle);
//
//        TextView title = view.findViewById(R.id.tvGameRecord);
//
//        title.setText(strtitle);
//
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                setPagerPostion(strtitle);
//            }
//        });
//
//        if(position == 0)
//            setPagerPostion(strtitle);
//    }
//
//    String selectedTab = "";
//    private void setPagerPostion(String name) {
//        for (int i = 0; i < lnrGamesTabs.getChildCount() ; i++) {
//
//            View view  = lnrGamesTabs.getChildAt(i);
//            TextView title = view.findViewById(R.id.tvGameRecord);
//
//            if(Functions.getStringFromTextView(title).equalsIgnoreCase(name))
//            {
//                if(homegameListAdapter != null)
//                    homegameListAdapter.getFilter().filter(name);
//                title.setTextColor(context.getResources().getColor(R.color.black));
//                title.setBackground(context.getResources().getDrawable(R.drawable.btn_yellow_discard));
//            } else {
//                title.setTextColor(context.getResources().getColor(R.color.white));
//                title.setBackground(context.getResources().getDrawable(R.drawable.d_white_corner));
//            }
//
//        }
//    }
//
//    HomegameListAdapter homegameListAdapter;
//    private void initHomeScreenModel() {
//
//        RecyclerView recGamesList = findViewById(R.id.recGamesList);
//        int numberOfRows = 2; // For 2 rows.
//
//        int totalItems = getGameList().size();
//        int numberOfColumns = totalItems / numberOfRows;
//
//        if (numberOfColumns == 0) {
//            numberOfColumns = 1; // Set a minimum of 1 column to avoid division by zero error.
//        }
//
//        GridLayoutManager layoutManager = new GridLayoutManager(context, numberOfColumns);
//        recGamesList.setLayoutManager(layoutManager);
//        recGamesList.setAdapter(homegameListAdapter);
//
//        homegameListAdapter = new HomegameListAdapter(context);
//        homegameListAdapter.setArrayList(getGameList());
//        homegameListAdapter.setCallback(new ClassCallback() {
//            @Override
//            public void Response(View v, int position, Object object) {
//                Enum gametype = (Enum) object;
//                if (TEENPATTI.equals(gametype)) {
//                    openPublicTeenpatti(null);
//                }
//                else if(DRAGON_TIGER.equals(gametype))
//                {
//                    openDragonTiger(null);
//                }else if(ANDHAR_BAHAR.equals(gametype))
//                {
//                    openAndharbahar(null);
//                }else if(POINT_RUMMY.equals(gametype))
//                {
//                    openRummyGame(null);
//                }
//                else if(PRIVATE_RUMMY.equals(gametype))
//                {
//                    openPrivateRummyTable();
////                    DialogRummyCreateTable.getInstance(context).show();
//                }else if(POOL_RUMMY.equals(gametype))
//                {
//                    openRummyPullGame(null);
//                }else if(DEAL_RUMMY.equals(gametype))
//                {
//                    openRummyDealGame(null);
//                }else if(PRIVATE_TABLE.equals(gametype))
//                {
//                    openPrivateTeenpatti(null);
//                }else if(CUSTOM_TABLE.equals(gametype))
//                {
//                    openCustomeTeenpatti(null);
//                }else if(SEVEN_UP_DOWN.equals(gametype))
//                {
//                    openSevenup(null);
//                }else if(CAR_ROULETTE.equals(gametype))
//                {
//                    openCarRoulette(null);
//                }else if(JACKPOT_3PATTI.equals(gametype))
//                {
//                    openLuckJackpotActivity(null);
//                }else if(ANIMAL_ROULETTE.equals(gametype))
//                {
//                    openAnimalRoullete(null);
//                }else if(COLOUR_PREDICTION.equals(gametype))
//                {
//                    openColorPred(null);
//
//                }
////                else if(POKER_GAME.equals(gametype))
////                {
////                    openPokerGame(null);
////                }
//                else if(HEAD_TAIL.equals(gametype))
//                {
//                    openHeadTailGame(null);
//                }
//                else{
//                    Functions.showToast(context,"Coming soon!");
//                }
//            }
//        });
//        recGamesList.setAdapter(homegameListAdapter);
//    }
//
//    private void openHeadTailGame(View view) {
//        startActivity(new Intent(getApplicationContext(), HeadTail_A.class));
//    }
//
//    private void openPrivateRummyTable() {
//        LoadTableFragments(ActiveTables_BF.RUMMY_PRIVATE_TABLE);
//    }
//
//    private List<HomescreenModel> getGameList() {
//        List<HomescreenModel> arraylist = new ArrayList<>();
//       if(SharePref.getIsTeenpattiVisible())
//           arraylist.add(new HomescreenModel(1,TEENPATTI,R.drawable.home_public_img,"Skills"));
//        if(SharePref.getIsPrivateVisible())
//            arraylist.add(new HomescreenModel(7,PRIVATE_TABLE,R.drawable.home_private_table,"Skills"));
//       if(SharePref.getIsDragonTigerVisible())
//           arraylist.add(new HomescreenModel(2,DRAGON_TIGER,R.drawable.home_dragontiger,"All"));
//       if(SharePref.getIsAndharBaharVisible())
//             arraylist.add(new HomescreenModel(3,ANDHAR_BAHAR,R.drawable.home_andherbahar,"All"));
//       if(SharePref.getIsRummyVisible())
//            arraylist.add(new HomescreenModel(4,POINT_RUMMY,R.drawable.home_rummy_point,"Skills"));
////       if(Variables.PRIVATE_RUMMY_SHOW)
////            arraylist.add(new HomescreenModel(4,PRIVATE_RUMMY,R.drawable.home_private_rummy,"Skills"));
////       if(Variables.RUMMPOOL_SHOW)
////            arraylist.add(new HomescreenModel(5,POOL_RUMMY,R.drawable.home_rummy_pool,"Skills"));
////       if(Variables.RUMMYDEAL_SHOW)
////            arraylist.add(new HomescreenModel(6,DEAL_RUMMY,R.drawable.home_rummy_deal,"Skills"));
////       if(SharePref.getIsCustomVisible())
////          arraylist.add(new HomescreenModel(8,CUSTOM_TABLE,R.drawable.home_custom_table,"Skills"));
//
//        if(Variables.SEVENUPSDOWN_SHOW)
//            arraylist.add(new HomescreenModel(9,SEVEN_UP_DOWN,R.drawable.home_sevenupdown,"All"));
//        if(Variables.CARROULETE_SHOW)
//            arraylist.add(new HomescreenModel(10,CAR_ROULETTE,R.drawable.home_car_rouleti,"All"));
////        if(Variables.JACKPOTGAME_SHOW)
////            arraylist.add(new HomescreenModel(11,JACKPOT_3PATTI,R.drawable.home_jack_pot,"All"));
////        if(Variables.ANIMALROULETE_SHOW)
////            arraylist.add(new HomescreenModel(12,ANIMAL_ROULETTE,R.drawable.home_animal_roulette,"All"));
////      if(Variables.COLOR_PREDICTION)
// //           arraylist.add(new HomescreenModel(13,COLOUR_PREDICTION,R.drawable.home_colour_prediction,"All"));
//
////        if(POKER_GAME)
////            arraylist.add(new HomescreenModel(14,POKER_GAME,R.drawable.home_poker,"Skills"));
//
////        if(Variables.HEAD_TAIL)
////            arraylist.add(new HomescreenModel(15,HEAD_TAIL,R.drawable.tournament,"All"));
//
//
////        arraylist.add(new HomescreenModel(15,CRICKET_GAME,R.drawable.home_cricket,"Sports"));
////        arraylist.add(new HomescreenModel(16,BEST_OF_5,R.drawable.home_best_of_5,"All"));
////        arraylist.add(new HomescreenModel(17,LUDO_GAME,R.drawable.home_ludo,"Skills"));
//        return arraylist;
//    }
//
//    private void BonusView(){
//
//        if(SharePref.getInstance().getBoolean(SharePref.isBonusShow))
//            lnrmail.setVisibility(View.GONE);
//        else
//            lnrmail.setVisibility(View.GONE);
//
//        Date c = Calendar.getInstance().getTime();
//        System.out.println("Current time => " + c);
//
//
//        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
//        String datevalue = prefs.getString("cur_date4", "12/06/2020");
//
//
//        SimpleDateFormat df1 = new SimpleDateFormat("dd/MM/yyyy");
//        String formattedDate1 = df1.format(c);
//        int dateDifference = (int) getDateDiff(new SimpleDateFormat("dd/MM/yyyy"), datevalue,formattedDate1);
//
//
//        /*if (dateDifference > 0) {
//            // catalog_outdated = 1;
//
//            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//            String formattedDate = df.format(c);
//
//            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
//            editor.putString("cur_date4", formattedDate);
//            editor.apply();
//
//            if(SharePref.getInstance().getBoolean(SharePref.isBonusShow))
//                showDailyWinCoins();
//
//        }else {
//
//            System.out.println("");
//
//
//        }*/
//
////        lnrmail.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////
//////                Intent intent = new Intent(getApplicationContext(), MaiUserListingActivity.class);
//////                startActivity(intent);
//////                Funtions.showToast(context, "Coming Soon",
//////                        Toast.LENGTH_LONG).show();
////                showDailyWinCoins();
////
////            }
////        });
//    }
//
//   /* private void showDailyWinCoins() {
//        DialogDailyBonus.getInstance(context).returnCallback(new Callback() {
//            @Override
//            public void Responce(String resp, String type, Bundle bundle) {
//                UserProfile();
//            }
//        }).show();
//    }*/
//
//    private void LoadFragment(Fragment fragment)
//    {
//        getSupportFragmentManager().
//                beginTransaction().
//                replace(R.id.home_container,fragment).
//                addToBackStack(null).
//                commit();
//    }
//
//    private void BlinkAnimation(final View view) {
//        view.setVisibility(View.VISIBLE);
//        final Animation animationUtils = AnimationUtils.loadAnimation(context,R.anim.blink);
//        animationUtils.setDuration(1000);
//        animationUtils.setRepeatCount(1);
//        animationUtils.setStartOffset(700);
//        view.startAnimation(animationUtils);
//
//        animationUtils.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                view.setVisibility(View.GONE);
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });
//
//    }
//
//    private void shineAnimation(final View view) {
//        final Animation animationUtils = AnimationUtils.loadAnimation(context,R.anim.left_to_righ);
//        animationUtils.setDuration(1500);
//        view.startAnimation(animationUtils);
//
//        animationUtils.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                view.startAnimation(animationUtils);
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });
//
//    }
//
//
//
//    LinearLayout lnr_2player,lnr_5player;
//    TextView tv_2player,tv_5player;
//
//    int selected_type = 5;
//    public void Dialog_SelectPlayer() {
//        final Dialog dialog = Functions.DialogInstance(context);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setTitle("");
//        dialog.setContentView(R.layout.dialog_select_palyer);
//        lnr_2player = dialog.findViewById(R.id.lnr_2player);
//        lnr_5player = dialog.findViewById(R.id.lnr_5player);
//        tv_2player =  (TextView) dialog.findViewById(R.id.tv_2player);
//        tv_5player =  (TextView) dialog.findViewById(R.id.tv_5player);
//        imgclosetop=dialog.findViewById(R.id.imgclosetop) ;
//
//        Button btn_player = dialog.findViewById(R.id.btn_play);
//
//        ChangeTextviewColorChange(5);
//
//        lnr_2player.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ChangeTextviewColorChange(2);
//            }
//        });
//
//        lnr_5player.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ChangeTextviewColorChange(5);
//            }
//        });
//        imgclosetop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.dismiss();
//            }
//        });
//
//        btn_player.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//
//                if(selected_type == 2)
//                {
//                    LoadTableFragments(ActiveTables_BF.RUMMY2);
//
//                }
//                else {
//                    LoadTableFragments(ActiveTables_BF.RUMMY5);
//
//                }
//
//            }
//        });
//
//        dialog.setCancelable(true);
//        dialog.show();
//        Functions.setDialogParams(dialog);
//        Window window = dialog.getWindow();
//        window.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
//
//    }
//
//    public void Dialog_SelectPlayerPool() {
//        final Dialog dialog = Functions.DialogInstance(context);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setTitle("");
//        dialog.setContentView(R.layout.dialog_select_palyer);
//        lnr_2player = dialog.findViewById(R.id.lnr_2player);
//        lnr_5player = dialog.findViewById(R.id.lnr_5player);
//        tv_2player =  (TextView) dialog.findViewById(R.id.tv_2player);
//        tv_5player =  (TextView) dialog.findViewById(R.id.tv_5player);
//        tv_5player.setText("6 \nPlayer");
//
//        imgclosetop=dialog.findViewById(R.id.imgclosetop);
//
//        Button btn_player = dialog.findViewById(R.id.btn_play);
//
//        ChangeTextviewColorChange(5);
//
//        lnr_2player.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ChangeTextviewColorChange(2);
//            }
//        });
//
//        lnr_5player.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ChangeTextviewColorChange(5);
//            }
//        });
//
//
//        imgclosetop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.dismiss();
//            }
//        });
//
//        btn_player.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//
//                if(selected_type == 2)
//                {
//                    LoadPullRummyActiveTable(ActiveTables_BF.RUMMY2);
//
//                }
//                else {
//                    LoadPullRummyActiveTable(ActiveTables_BF.RUMMY5);
//                }
//
//
//            }
//        });
//
//        dialog.setCancelable(true);
//        dialog.show();
//        Functions.setDialogParams(dialog);
//        Window window = dialog.getWindow();
//        window.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
//
//    }
//
//
//    private void LoadTableFragments(String TAG){
//        ActiveTables_BF activeTables_bf = new ActiveTables_BF(TAG);
//        activeTables_bf.show(getSupportFragmentManager(),activeTables_bf.getTag());
//    }
//
//    private void LoadPointRummyActiveTable(String TAG){
//        RummyActiveTables_BF rummyActiveTables_bf = new RummyActiveTables_BF(TAG);
//        rummyActiveTables_bf.show(getSupportFragmentManager(),rummyActiveTables_bf.getTag());
//    }
//
//    private void ChangeTextviewColorChange(int colortype){
//
//        selected_type = colortype;
//
//        if(colortype == 2)
//        {
//            tv_2player.setTextColor(getResources().getColor(R.color.white));
//            lnr_2player.setBackgroundColor(getResources().getColor(R.color.new_colorPrimary));
//
//            tv_5player.setTextColor(getResources().getColor(R.color.black));
//            lnr_5player.setBackgroundColor(getResources().getColor(R.color.white));
//
//        }
//        else {
//            tv_2player.setTextColor(getResources().getColor(R.color.black));
//            lnr_2player.setBackgroundColor(getResources().getColor(R.color.white));
//
//            tv_5player.setTextColor(getResources().getColor(R.color.white));
//            lnr_5player.setBackgroundColor(getResources().getColor(R.color.new_colorPrimary));
//
//        }
//
//    }
//
//
//    private void rotation_animation(View view,int animation){
//
//        Animation circle =  Functions.AnimationListner(context, animation, new Callback() {
//            @Override
//            public void Responce(String resp, String type, Bundle bundle) {
//
//            }
//        });
//        view.setAnimation(circle);
//        circle.startNow();
//
//    }
//
//    int Counts = 100;
//    int postion = -100;
//    private void CenterAnimationView(String imagename,View imgcards,int Home_Page_Animation){
//
//
//        int AnimationSpeed = Counts + Home_Page_Animation;
//        Counts += 300;
//
//        final View fromView, toView;
//        rootView.setVisibility(View.VISIBLE);
////        rootView.removeAllViews();
////        View ivpickcard = findViewById(R.id.view_center);
//
//        fromView = rootView;
//        toView = imgcards;
//
//
//        int fromLoc[] = new int[2];
//        fromView.getLocationOnScreen(fromLoc);
//        float startX = fromLoc[0];
//        float startY = fromLoc[1];
//
//        int toLoc[] = new int[2];
//        toView.getLocationOnScreen(toLoc);
//        float destX = toLoc[0];
//        float destY = toLoc[1];
//
//        final ImageView sticker = new ImageView(context);
//
//        int stickerId = Functions.GetResourcePath(imagename,context);
//
//        int card_width = (int) getResources().getDimension(R.dimen.home_card_width);
//        int card_hieght = (int) getResources().getDimension(R.dimen.home_card_height);
//
//        Picasso.get().load(stickerId).into(sticker);
//
//        sticker.setLayoutParams(new ViewGroup.LayoutParams(card_width, card_hieght));
//        rootView.addView(sticker);
//
//
//        Animations anim = new Animations();
//        Animation a = anim.fromAtoB(0, 0, convertDpToPixel(postion,context), 0, null, AnimationSpeed, new Callback() {
//            @Override
//            public void Responce(String resp, String type, Bundle bundle) {
//                fromView.setVisibility(View.VISIBLE);
//                toView.setVisibility(View.VISIBLE);
//                sticker.setVisibility(View.GONE);
//            }
//        });
//        sticker.setAnimation(a);
//        a.startNow();
//
//        postion += 100;
//
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        str_colr_pred="";
////        EnableGPS();
//        UserProfile();
//        GameLeave();
//    }
//
//    public void clickTask() {
//
//        imgPrivategame.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //showDialoag();
//                openPrivateTeenpatti(null);
//
//            }
//        });
//
//        lnrsetting.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                DialogSettingOption dialogSettingOption = new DialogSettingOption(context)
//                {
//                    public void playstopSound() {
//
//                        playSound(R.raw.game_soundtrack,true);
//
//                    }
//                };
//
//                dialogSettingOption.showDialogSetting();
//            }
//        });
//
//        lnrvideo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Functions.showToast(context, "Coming Soon");
//            }
//        });
//
//        imgLogout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
////                new AlertDialog.Builder(context)
////                        .setIcon(android.R.drawable.ic_dialog_alert)
////                        .setTitle("Logout")
////                        .setMessage("Do you want to Logout?")
////                        .setPositiveButton("Yes", new DialogInterface.OnClickListener()
////                        {
////                            @Override
////                            public void onClick(DialogInterface dialog, int which) {
////                                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
////                                editor.putString("user_id", "");
////                                editor.putString("name", "");
////                                editor.putString("mobile", "");
////                                editor.putString("token", "");
////                                editor.apply();
////                                Intent intent = new Intent(context, LoginScreen.class);
////                                startActivity(intent);
////                                finish();
////                            }
////
////                        })
////                        .setNegativeButton("No", null)
////                        .show();
//
//
//                new SmartDialogBuilder(context)
//                        .setTitle("Do you want to Logout?")
//                        //.setSubTitle("context is the alert dialog to showing alert to user")
//                        .setCancalable(true)
//                        //.setTitleFont("Do you want to Logout?") //set title font
//                        // .setSubTitleFont(subTitleFont) //set sub title font
//                        .setNegativeButtonHide(true) //hide cancel button
//                        .setPositiveButton("Logout", new SmartDialogClickListener() {
//                            @Override
//                            public void onClick(SmartDialog smartDialog) {
//                                // Funtions.showToast(context,"Ok button Click",Toast.LENGTH_SHORT)
//                                // .show();
//                                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
//                                editor.putString("user_id", "");
//                                editor.putString("name", "");
//                                editor.putString("mobile", "");
//                                editor.putString("token", "");
//                                editor.apply();
//                                Intent intent = new Intent(context, LoginScreen.class);
//                                startActivity(intent);
//                                finish();
//
//                                smartDialog.dismiss();
//                            }
//                        }).setNegativeButton("Cancel", new SmartDialogClickListener() {
//                    @Override
//                    public void onClick(SmartDialog smartDialog) {
//                        // Funtions.showToast(context,"Cancel button Click");
//                        smartDialog.dismiss();
//
//                    }
//                }).build().show();
//
////                new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
////                        .setTitleText("Are you sure?")
////                        .setContentText("Won't be able to recover context file!")
////                        .setConfirmText("Yes,delete it!")
////                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
////                            @Override
////                            public void onClick(SweetAlertDialog sDialog) {
////                                sDialog.dismissWithAnimation();
////                            }
////                        })
////                        .show();
//            }
//        });
//
//        Log.d("YourTag123", "lnrinvite View clicked");
//
//        lnrinvite.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d("YourTag", "lnrinvite View clicked");
//
//                DialogReferandEarn.getInstance(context).show();
//
//            }
//        });
//    }
//
//    public void openBuyChipsActivity(View view){
//        Intent intent = new Intent(context, BuyChipsList.class);
//        intent.putExtra("homepage","homepage");
//        startActivity(intent);
//    }
//
//
//
//    private void UserProfile() {
//
//        Functions.showLoader(context,false,false);
//
//        HashMap<String, String> params = new HashMap<String, String>();
//        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
//        params.put("id", prefs.getString("user_id", ""));
//        params.put("fcm", token);
//
//        try {
//            PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
//            version = pInfo.versionCode;
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        params.put("app_version", version + "");
//        params.put("token", prefs.getString("token", ""));
//
//        ApiRequest.Call_Api(context, Const.PROFILE, params, new Callback() {
//            @Override
//            public void Responce(String resp, String type, Bundle bundle) {
//
//                if(resp != null)
//                {
//                    ParseResponse(resp);
//                }
//
//            }
//        });
//
//    }
//
//    private void ParseResponse(String response){
//        try {
//            JSONObject jsonObject = new JSONObject(response);
//            String code = jsonObject.getString("code");
//            if (code.equalsIgnoreCase("200")) {
//                JSONObject jsonObject0 = jsonObject.getJSONArray("user_data").getJSONObject(0);
//                user_id = jsonObject0.getString("id");
//                name = jsonObject0.optString("name");
//                mobile = jsonObject0.optString("mobile");
//                profile_pic = jsonObject0.optString("profile_pic");
//                referral_code = jsonObject0.optString("referral_code");
//                wallet = jsonObject0.optString("wallet");
//                String winning_wallet = jsonObject0.optString("winning_wallet");
//                game_played = jsonObject0.optString("game_played");
//                bank_detail = jsonObject0.optString("bank_detail");
//                upi = jsonObject0.optString("upi");
//                adhar_card = jsonObject0.optString("adhar_card");
//
//
//                // txtName.setText("Welcome Back "+name);
//
//                if(Functions.isStringValid(wallet))
//                {
//                    float f_wallet = Float.parseFloat(wallet);
////                    long numberamount =  Float.parseFloat(f_wallet);
////                    long numberamount = (long) f_wallet;
////                    txtwallet.setText("" + NumberFormat.getNumberInstance(Locale.US).format(f_wallet));
//                    txtwallet.setText("" + f_wallet);
//                }
//
//
//                txtproname.setText(name);
//                Picasso.get().load(Const.IMGAE_PATH + profile_pic).into(imaprofile);
//
//
//                String setting = jsonObject.optString("setting");
//                JSONObject jsonObjectSetting = new JSONObject(setting);
//                JSONArray avatar = jsonObject.getJSONArray("avatar");
//
//                game_for_private = jsonObjectSetting.optString("game_for_private");
//                app_version = jsonObjectSetting.optString("app_version");
//                String  referral_amount = jsonObjectSetting.optString("referral_amount");
//                String  referral_link = jsonObjectSetting.optString("referral_link");
//                String  joining_amount = jsonObjectSetting.optString("joining_amount");
//                String whats_no = jsonObjectSetting.optString("whats_no");
//                String mBan_states = jsonObjectSetting.optString("ban_states");
//
//                int admin_commission = jsonObjectSetting.optInt("admin_commission",2);
//
//                SharePref.getInstance().putInt(SharePref.ADMIN_COMMISSION,admin_commission);
//
//                ban_states = Arrays.asList(mBan_states.split(","));
////                checkForBanState();
//
//                String GodmodMobilenumber = jsonObjectSetting.optString("mobile");
//
//                if(mobile.equalsIgnoreCase(GodmodMobilenumber))
//                    SharePref.getInstance().putGodmodeValue(true);
//
//                // bonus = 0=no , 1=yes
//                // payment_gateway = 0=payment , 1=whatsapp
//                // symbol = 0=coin , 1=rupees,2 = dollar
//
//                String symbol = jsonObjectSetting.optString("symbol");
//                String payment_gateway = jsonObjectSetting.optString("payment_gateway");
//                String bonus = jsonObjectSetting.optString("bonus");
//                String razor_api_key = jsonObjectSetting.optString("razor_api_key");
//
//                String cashfree_client_id = jsonObjectSetting.optString("cashfree_client_id");
//                String cashfree_stage = jsonObjectSetting.optString("cashfree_stage");
//                String paytm_mercent_id = jsonObjectSetting.optString("paytm_mercent_id");
//                String payumoney_key = jsonObjectSetting.optString("payumoney_key");
//
//                String share_text = jsonObjectSetting.optString("share_text");
//
//                if(Functions.isStringValid(bonus) && !bonus.equalsIgnoreCase("0"))
//                    SharePref.getInstance().putBoolean(SharePref.isBonusShow,true);
//                else
//                    SharePref.getInstance().putBoolean(SharePref.isBonusShow,false);
//
//                if(Functions.isStringValid(payment_gateway)
//                        && !payment_gateway.equalsIgnoreCase("1"))
//                    SharePref.getInstance().putBoolean(SharePref.isPaymentGateShow,true);
//                else
//                    SharePref.getInstance().putBoolean(SharePref.isPaymentGateShow,false);
//
//
//
//                String payment_type= payment_gateway.equals("0") ? Variables.RAZOR_PAY
//                        : payment_gateway.equals("1") ? Variables.WHATS_APP
//                        : payment_gateway.equals("2") ? Variables.CASH_FREE
//                        : payment_gateway.equals("4") ? Variables.PAYUMONEY
//                        : payment_gateway.equals("5") ? Variables.UPI_FREE_WAY
//                        : payment_gateway.equals("6") ? Variables.ATOM_PAY
//                        :  Variables.PAYTM;
//
//                SharePref.getInstance().putString(SharePref.PaymentType,payment_type);
//
//
//
//                if(Functions.isStringValid(symbol) && symbol.equalsIgnoreCase("0"))
//                    Variables.CURRENCY_SYMBOL = Variables.COINS;
//                else if(Functions.isStringValid(symbol) && symbol.equalsIgnoreCase("1"))
//                {
//                    Variables.CURRENCY_SYMBOL = Variables.RUPEES;
//                }
//                else if(Functions.isStringValid(symbol) && symbol.equalsIgnoreCase("2"))
//                {
//                    Variables.CURRENCY_SYMBOL = Variables.DOLLAR;
//                }
//
//
//
//                BonusView();
//
//                SharePref.getInstance().putString(SharePref.SYMBOL_TYPE,Variables.CURRENCY_SYMBOL);
//                SharePref.getInstance().putString(SharePref.RAZOR_PAY_KEY,razor_api_key);
//                SharePref.getInstance().putString(SharePref.CASHFREE_CLIENT_ID,cashfree_client_id);
//                SharePref.getInstance().putString(SharePref.CASHFREE_STAGE,cashfree_stage);
//                SharePref.getInstance().putString(SharePref.PAYTM_MERCENT_ID,paytm_mercent_id);
//                SharePref.getInstance().putString(SharePref.PAYU_MERCENT_KEY,payumoney_key);
//                SharePref.getInstance().putString(SharePref.referral_link,referral_link);
//                SharePref.getInstance().putString(SharePref.avator,avatar.toString());
//
//                SharePref.getInstance().putString(SharePref.SHARE_APP_TEXT,share_text);
//
//                ((ImageView) findViewById(R.id.imgicon)).setImageDrawable(
//                        Variables.CURRENCY_SYMBOL.equalsIgnoreCase(Variables.RUPEES) ? Functions.getDrawable(context,R.drawable.ic_currency_dollar) :
//                                Variables.CURRENCY_SYMBOL.equalsIgnoreCase(Variables.DOLLAR) ? Functions.getDrawable(context,R.drawable.ic_currency_dollar) :
//                                        Functions.getDrawable(context,R.drawable.ic_currency_chip));
//
//                try {
//
////                    int app_versionint = Integer.parseInt(app_version);
////
////                    //if (version > app_versionint){
////                if (app_versionint > version) {
////
////                    showAppUpdateDialog("Update");
////
////                } else {
////
////
////                }
//                }
//                catch (NumberFormatException e)
//                {
//                    e.printStackTrace();
//                }
//
//
//
//
//
//                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
//                editor.putString("name", name);
//                editor.putString("profile_pic", profile_pic);
//                editor.putString("bank_detail", bank_detail);
//                editor.putString("upi", upi);
//                editor.putString("adhar_card", adhar_card);
//                editor.putString("mobile", mobile);
//                editor.putString("referal_code", referral_code);
//                editor.putString("img_name", profile_pic);
//                editor.putString("winning_wallet", winning_wallet);
//                editor.putString("wallet", wallet);
//                editor.putString("game_for_private", game_for_private);
//                editor.putString("app_version", app_version);
//                editor.putString("whats_no", whats_no);
//                editor.putString(REFERRAL_AMOUNT, referral_amount);
//                editor.putString(JOINING_AMOUNT, joining_amount);
//                editor.apply();
//
//
//            } else if (code.equals("411")) {
//
//                Intent intent = new Intent(context, LoginScreen.class);
//                startActivity(intent);
//                finishAffinity();
//                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
//                editor.putString("name", "");
//                editor.putString("referal_code", "");
//                editor.putString("img_name", "");
//                editor.putString("game_for_private", "");
//                editor.putString("app_version", "");
//                editor.apply();
//
//                Functions.showToast(context, "You are Logged in from another " +
//                        "device.");
//
//
//            } else {
//
//                if (jsonObject.has("message")) {
//                    String message = jsonObject.getString("message");
//                    Functions.showToast(context, message);
//                }
//
//
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        findViewById(R.id.iv_add).clearAnimation();
//        Functions.dismissLoader();
//
//    }
//
//    public void PlaySaund(int sound) {
//
//        if(!SharePref.getInstance().isSoundEnable())
//            return;
//
//        final MediaPlayer mp = MediaPlayer.create(context,
//                sound);
//        mp.start();
//    }
//
//    public void showDialoagPrivettable() {
//
//        // custom dialog
//        final Dialog dialog = Functions.DialogInstance(context);
//        dialog.setContentView(R.layout.custom_dialog_custon_boot);
//        dialog.setTitle("Title...");
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
//
//        sBarpri = (SeekBar) dialog.findViewById(R.id.seekBar1);
//        sBarpri.setProgress(0);
//        sBarpri.incrementProgressBy(10);
//        sBarpri.setMax(100);
//        txtStartpri = (TextView) dialog.findViewById(R.id.txtStart);
//        txtLimitpri = (TextView) dialog.findViewById(R.id.txtLimit);
//        txtwalletchipspri = (TextView) dialog.findViewById(R.id.txtwalletchips);
//        float numberamount = Float.parseFloat(wallet);
//        txtwalletchipspri.setText("" + NumberFormat.getNumberInstance(Locale.US).format(numberamount));
//
//        // txtwalletchipspri.setText(wallet);
//        txtBootamountpri = (TextView) dialog.findViewById(R.id.txtBootamount);
//        txtPotLimitpri = (TextView) dialog.findViewById(R.id.txtPotLimit);
//        txtNumberofBlindpri = (TextView) dialog.findViewById(R.id.txtNumberofBlind);
//        txtMaximumbetvaluepri = (TextView) dialog.findViewById(R.id.txtMaximumbetvalue);
//        imgclosetoppri = (ImageView) dialog.findViewById(R.id.imgclosetop);
//        imgclosetoppri.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.dismiss();
//            }
//        });
//        imgCreatetable = (ImageView) dialog.findViewById(R.id.imgCreatetable);
//        imgCreatetable.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Intent intent = new Intent(context, PrivateTablev2.class);
//                Intent intent = new Intent(context, PublicTable_New.class);
//                intent.putExtra("gametype", PublicTable_New.PRIVATE_TABLE);
//                intent.putExtra("bootvalue", pvalpri + "");
//                startActivity(intent);
//                dialog.dismiss();
//            }
//        });
//        // tView.setText(sBar.getProgress() + "/" + sBar.getMax());
//        sBarpri.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                progress = progress / 10;
//                if (progress == 1) {
//
//                    pvalpri = 50;
//
//                } else if (progress == 2) {
//                    pvalpri = 100;
//                } else if (progress == 3) {
//
//                    pvalpri = 500;
//                } else if (progress == 4) {
//
//                    pvalpri = 1000;
//
//                } else if (progress == 5) {
//
//                    pvalpri = 5000;
//
//                } else if (progress == 6) {
//
//                    pvalpri = 10000;
//                } else if (progress == 7) {
//
//                    pvalpri = 25000;
//                } else if (progress == 8) {
//
//                    pvalpri = 50000;
//                } else if (progress == 9) {
//
//                    pvalpri = 100000;
//                } else if (progress == 10) {
//
//                    pvalpri = 250000;
//                }
//
//                //progress = progress * 10;
//                // pvalpri = progress;
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//                //write custom code to on start progress
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//                txtBootamountpri.setText("Boot amount : " + kvalue(pvalpri) + "");
//
//                int valueforpot = pvalpri * 1024;
//                int valueformaxi = pvalpri * 128;
//
//                //long valueforpotlong= valueforpot;
//
//                txtPotLimitpri.setText("Pot limit : " + kvalue(valueforpot) + "");
//                txtMaximumbetvaluepri.setText("Maximumbet balue : " + kvalue(valueformaxi) + "");
//                txtNumberofBlindpri.setText("Number of Blinds : 4");
//                //tView.setText(pval + "/" + seekBar.getMax());
//            }
//        });
//
//
//        dialog.show();
//        Functions.setDialogParams(dialog);
//    }
//
//    public void showDialoagonBack() {
//
//        // custom dialog
//        final Dialog dialog = Functions.DialogInstance(context);
//        dialog.setContentView(R.layout.custom_dialog_custon_boot);
//        dialog.setTitle("Title...");
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
//
//        sBar = (SeekBar) dialog.findViewById(R.id.seekBar1);
//        sBar.setProgress(0);
//        sBar.incrementProgressBy(10);
//        sBar.setMax(100);
//        txtStart = (TextView) dialog.findViewById(R.id.txtStart);
//        txtLimit = (TextView) dialog.findViewById(R.id.txtLimit);
//        txtwalletchips = (TextView) dialog.findViewById(R.id.txtwalletchips);
//        float numberamount = Float.parseFloat(wallet);
//        txtwalletchips.setText("" + NumberFormat.getNumberInstance(Locale.US).format(numberamount));
//        txtBootamount = (TextView) dialog.findViewById(R.id.txtBootamount);
//        txtPotLimit = (TextView) dialog.findViewById(R.id.txtPotLimit);
//        txtNumberofBlind = (TextView) dialog.findViewById(R.id.txtNumberofBlind);
//        txtMaximumbetvalue = (TextView) dialog.findViewById(R.id.txtMaximumbetvalue);
//        imgclosetop = (ImageView) dialog.findViewById(R.id.imgclosetop);
//        imgclosetop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.dismiss();
//            }
//        });
//        imgCreatetablecustom = (ImageView) dialog.findViewById(R.id.imgCreatetable);
//        imgCreatetablecustom.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Intent intent = new Intent(context, CustomisedTablev2.class);
//                Intent intent = new Intent(context, PublicTable_New.class);
//                intent.putExtra("gametype", PublicTable_New.CUSTOME_TABLE);
//                intent.putExtra("bootvalue", pval + "");
//                startActivity(intent);
//                dialog.dismiss();
//            }
//        });
//        // tView.setText(sBar.getProgress() + "/" + sBar.getMax());
//        sBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                pval = progress;
//                progress = progress / 10;
//                if (progress == 1) {
//
//                    pval = 50;
//
//                } else if (progress == 2) {
//                    pval = 100;
//                } else if (progress == 3) {
//
//                    pval = 500;
//                } else if (progress == 4) {
//
//                    pval = 1000;
//
//                } else if (progress == 5) {
//
//                    pval = 5000;
//
//                } else if (progress == 6) {
//
//                    pval = 10000;
//                } else if (progress == 7) {
//
//                    pval = 25000;
//                } else if (progress == 8) {
//
//                    pval = 50000;
//                } else if (progress == 9) {
//
//                    pval = 100000;
//                } else if (progress == 10) {
//
//                    pval = 250000;
//                }
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//                //write custom code to on start progress
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//                txtBootamount.setText("Boot amount : " + kvalue(pval) + "");
//
//                int valueforpot = pval * 1024;
//                int valueformaxi = pval * 128;
//                txtPotLimit.setText("Pot limit : " + kvalue(valueforpot) + "");
//                txtMaximumbetvalue.setText("Maximumbet balue : " + kvalue(valueformaxi) + "");
//                txtNumberofBlind.setText("Number of Blinds : 4");
//                //tView.setText(pval + "/" + seekBar.getMax());
//            }
//        });
//
//
//        dialog.show();
//        Functions.setDialogParams(dialog);
//    }
//
//    private void GameLeave() {
//
//
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, Const.GAME_TABLE_LEAVE,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        //progressDialog.dismiss();
//                        System.out.println("" + response);
//                        // finish();
//
//                    }
//
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> params = new HashMap<String, String>();
//                SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
//                params.put("user_id", prefs.getString("user_id", ""));
//                params.put("token", prefs.getString("token", ""));
//                return params;
//            }
//
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                HashMap<String, String> headers = new HashMap<String, String>();
//                headers.put("token", Const.TOKEN);
//                return headers;
//            }
//        };
//
//        Volley.newRequestQueue(context).add(stringRequest);
//
//    }
//
//    public void openLuckJackpotActivity(View view) {
//        startActivity(new Intent(context, LuckJackPot_A.class));
//    }
//
//    public void openSevenup(View view) {
//        startActivity(new Intent(context, SevenUp_A.class));
//    }
//
//    public void openPublicTeenpatti(View view) {
//        PlaySaund(R.raw.buttontouchsound);
//        LoadTableFragments(ActiveTables_BF.TEENPATTI);
//    }
//
//    public void openPrivateTeenpatti(View view) {
//        PlaySaund(R.raw.buttontouchsound);
//        float gamecount = 0;
//        if(game_played != null && !game_played.equals(""))
//        {
//            gamecount  = Float.parseFloat(game_played);
//        }
//        float game_for_privatetemp = Float.parseFloat(game_for_private);
//        if (gamecount > game_for_privatetemp ){
//
//            showDialoagPrivettable();
//
//        }else {
//
//            Functions.showToast(context, "To Unblock Private Table you have to Play at least "+game_for_privatetemp+
//                    " Games.");
//
//        }
//    }
//
//    public void openCustomeTeenpatti(View view) {
//        PlaySaund(R.raw.buttontouchsound);
//        showDialoagonBack();
//    }
//
//    public void openRummyGame(View view) {
//        Dialog_SelectPlayer();
////        LoadPointRummyActiveTable(ActiveTables_BF.RUMMY5);
//    }
//
//    public void openRummyPullGame(View view) {
//        Dialog_SelectPlayerPool();
//    }
//
//    public void openPokerGame(View view) {
//        LoadPokerGameActiveTable(ActiveTables_BF.RUMMY5);
//    }
//
//
//    public void openRummyDealGame(View view) {
////        LoadDealRummyActiveTable(ActiveTables_BF.RUMMY2);
//        DialogDealType dialogDealType = new DialogDealType(this){
//            @Override
//            protected void startGame(Bundle bundle) {
//                openRummyGames(bundle);
//            }
//        };
//        dialogDealType.show();
//    }
//
//    private void openRummyGames(Bundle bundle){
//        Intent intent = new Intent(context, RummyDealGame.class);
//        if(bundle != null)
//            intent.putExtras(bundle);
//
//        if(context != null && !context.isFinishing())
//            context.startActivity(intent);
//    }
//
//
//    public void openAndharbahar(View view) {
////        startActivity(new Intent(context, AndharBahar_Home_A.class));
//        startActivity(new Intent(context, Andhar_Bahar_NewUI.class).putExtra("room_id","1"));
//    }
//
//    public void openColorPred(View view) {
////        str_colr_pred="1";
//        Intent intent = new Intent(context, ColorPrediction.class);
////        intent.putExtra("room_id" ,gameModelArrayList.get(position).getId());
////        intent.putExtra("min_coin" ,gameModelArrayList.get(position).getMin_coin());
////        intent.putExtra("max_coin" ,gameModelArrayList.get(position).getMax_coin());
//        context.startActivity(intent);
//    }
//
//
//    public void openDragonTiger(View view) {
//        startActivity(new Intent(context, DragonTiger_A.class));
//    }
//
//    public void openCarRoulette(View view) {
//        startActivity(new Intent(context, CarRoullete_A.class));
//    }
//
//    public void openAnimalRoullete(View view) {
//        startActivity(new Intent(context, AnimalRoulette_A.class));
//    }
//
//    public interface itemClick{
//        void OnDailyClick(String id);
//    }
//    TextView txtnotfound;
//
//    private void LoadPullRummyActiveTable(String TAG){
//        RummyActiveTables_BF rummyActiveTables_bf = new RummyActiveTables_BF(TAG);
//        rummyActiveTables_bf.show(getSupportFragmentManager(),rummyActiveTables_bf.getTag());
//    }
//
//    private void LoadPokerGameActiveTable(String TAG){
//        PokerActiveTables_BF pokerActiveTables_bf = new PokerActiveTables_BF(TAG);
//        pokerActiveTables_bf.show(getSupportFragmentManager(),pokerActiveTables_bf.getTag());
//    }
//
//    private void LoadDealRummyActiveTable(String TAG){
//        RummyDealActiveTables_BF rummyDealActiveTables_bf = new RummyDealActiveTables_BF(TAG);
//        rummyDealActiveTables_bf.show(getSupportFragmentManager(),rummyDealActiveTables_bf.getTag());
//    }
//
//
//    public static long getDateDiff(SimpleDateFormat format, String oldDate, String newDate) {
//        try {
//            return TimeUnit.DAYS.convert(format.parse(newDate).getTime() - format.parse(oldDate).getTime(), TimeUnit.MILLISECONDS);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return 0;
//        }
//    }
//
//
//    public String kvalue (int number){
//
//        String numberString = "";
//        if (Math.abs(number / 1000000) > 1) {
//            numberString = (number / 1000000) + "m";
//
//        } else if (Math.abs(number / 1000) > 1) {
//            numberString = (number / 1000) + "k";
//
//        } else {
//            numberString = number+"";
//
//        }
//        return numberString;
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        playSound(R.raw.game_soundtrack,true);
//    }
//
//    @Override
//    protected void onDestroy() {
//        stopPlaying();
//        super.onDestroy();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        stopPlaying();
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        stopPlaying();
//    }
//
//    Sound soundMedia;
//    public void playSound(int sound,boolean isloop) {
//
//        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
//        String value = prefs.getString("issoundon", "1");
//
//        if (value.equals("1")) {
//            soundMedia.PlaySong(sound,isloop,context);
//        }
//        else {
//            stopPlaying();
//        }
//
//
//    }
//
//    private void stopPlaying() {
//        soundMedia.StopSong();
//    }
//
//    private void checkForBanState() {
//        String user_state = "";
//        if(Variables.latitude > 0 && Variables.longitude > 0)
//        {
//            Address address = getAddressFromLatLong(Variables.latitude,Variables.longitude,context);
//            if(address != null)
//                user_state = address.getAdminArea();
//        }
//
//        for (String state: ban_states) {
//            if(state.trim().equalsIgnoreCase(user_state))
//            {
//                DialogRestrictUser.getInstance(context).show();
//                break;
//            }
//        }
//    }
//
//    public static Address getAddressFromLatLong(double lat, double long_temp, Context context) {
//
//        Address address = null;
//
//        if (lat <= 0 && long_temp  <= 0)
//            return address;
//
//        try {
//            Geocoder geocoder;
//            List<Address> addresses;
//            geocoder = new Geocoder(context, Locale.getDefault());
//
//            addresses = geocoder.getFromLocation(lat, long_temp, 1);
//            address = addresses.get(0);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            // new GetClass().execute();
//        }
//
//        return address;
//    }
//
//    private boolean beforeClickPermissionRat;
//    private boolean afterClickPermissionRat;
//    public void requestLocationPermissions(){
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            beforeClickPermissionRat = shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION);
//            requestPermissions(Functions.LOCATION_PERMISSIONS, Variables.REQUESTCODE_LOCATION);
//        }
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        switch (requestCode) {
//            case ENABLE_LOCATION_CODE: {
//                if (resultCode == RESULT_OK) {
//
//                    storeLatlong();
//
//                }
//                else {
//                    finishAffinity();
//                }
//            }
//            break;
//        }
//    }
//
//    // before after
//// FALSE  FALSE  =  Was denied permanently, still denied permanently --> App Settings
//// FALSE  TRUE   =  First time deny, not denied permanently yet --> Nothing
//// TRUE   FALSE  =  Just been permanently denied --> Changing my caption to "Go to app settings to edit permissions"
//// TRUE   TRUE   =  Wasn't denied permanently, still not denied permanently --> Nothing
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//
//        switch (requestCode) {
//            case Variables.REQUESTCODE_LOCATION:
//
//                for (int i = 0, len = permissions.length; i < len; i++) {
//                    String permission = permissions[i];
//                    if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
//                        // user rejected the permission
//
//                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
//                            afterClickPermissionRat = shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION);
//                        }
//                        if ((!afterClickPermissionRat && !beforeClickPermissionRat)) {
//                            // user also CHECKED "never ask again"
//                            // you can either enable some fall back,
//                            // disable features of your app
//                            // or open another dialog explaining
//                            // again the permission and directing to
//                            // the app setting
//
////                            showUserClearAppDataDialog();
//
////                            openMapActivity();
//                            finishAffinity();
//                            break;
//                        }
//                        else if((afterClickPermissionRat && !beforeClickPermissionRat))
//                        {
//
////                            if(!Functions.isAndroid11())
////                            {
////                                openMapActivity();
////                                break;
////                            }
//
//                        }else {
//                            showRationale(permission, R.string.permission_denied_contacts);
//                            // user did NOT check "never ask again"
//                            // context is a good place to explain the user
//                            // why you need the permission and ask if he wants
//                            // to accept it (the rationale)
//                        }
//                    }
//                }
//
//                try {
//
//                    if(isPermissionGranted(grantResults))
//                    {
//                        enable_permission();
//                    }
//                    else {
//
//                        if((afterClickPermissionRat && !beforeClickPermissionRat)
//                                || (afterClickPermissionRat && beforeClickPermissionRat))
//                        {
//                            EnableGPS();
//                        }
//                    }
//                }
//                catch (ArrayIndexOutOfBoundsException e)
//                {
//                    e.printStackTrace();
//                }
//
//                break;
//        }
//
//    }
//
//    public void EnableGPS() {
//
//        if (Functions.check_location_permissions(context)) {
//            if (!GpsUtils.isGPSENABLE(context)) {
//                requestLocationAccess();
////                showFragment();
//            } else {
//                enable_permission();
//            }
//        } else {
////            showFragment();
//            requestLocationAccess();
//        }
//    }
//
//    public void requestLocationAccess() {
//
//        if (Functions.check_location_permissions(context)) {
//            enable_permission();
//        } else {
//            requestLocationPermissions();
//        }
//    }
//
//
//    private void showRationale(String permission, int permission_denied_contacts) {
//    }
//
//
//    private boolean isPermissionGranted(int[] grantResults){
//        boolean isGranted = true;
//
//        for (int result: grantResults) {
//
//            if(result != PackageManager.PERMISSION_GRANTED)
//            {
//                isGranted = false;
//                break;
//            }
//
//        }
//
//        return isGranted;
//    }
//
//
//    private void storeLatlong() {
//        LatLng latLng = getLatLong();
//        Variables.latitude = latLng.latitude;
//        Variables.longitude = latLng.longitude;
//
//        checkForBanState();
//    }
//
//    public LatLng getLatLong(){
//        if(getLocationlatlong!=null)
//            return getLocationlatlong.getLatlong();
//        else
//        {
//            getLocationlatlong = new GetLocationlatlong(context);
//        }
//
//        return getLocationlatlong.getLatlong();
//    }
//
//    private void enable_permission() {
//
//        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        boolean GpsStatus = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
//        if (!GpsStatus) {
//
//            new GpsUtils(context).turnGPSOn(isGPSEnable -> {
//
//                if(isGPSEnable)
//                    enable_permission();
//
//            });
//        } else if (Functions.check_location_permissions(context)) {
////            dismissFragment();
//            storeLatlong();
//        }
//    }
//
//    GetLocationlatlong getLocationlatlong;
//    private void initilizeLocation(){
//        getLocationlatlong = new GetLocationlatlong(context);
//    }
//
//}
//
//
//
////login_box


package com.dragon.multigame.Activity;

import static com.dragon.multigame.GAMES.ANDHAR_BAHAR;
import static com.dragon.multigame.GAMES.ANIMAL_ROULETTE;
import static com.dragon.multigame.GAMES.CAR_ROULETTE;
import static com.dragon.multigame.GAMES.COLOUR_PREDICTION;
import static com.dragon.multigame.GAMES.CUSTOM_TABLE;
import static com.dragon.multigame.GAMES.DEAL_RUMMY;
import static com.dragon.multigame.GAMES.DRAGON_TIGER;
import static com.dragon.multigame.GAMES.HEAD_TAIL;
import static com.dragon.multigame.GAMES.JACKPOT_3PATTI;
import static com.dragon.multigame.GAMES.POINT_RUMMY;
import static com.dragon.multigame.GAMES.POKER_GAME;
import static com.dragon.multigame.GAMES.POKER_GAME;
import static com.dragon.multigame.GAMES.POOL_RUMMY;
import static com.dragon.multigame.GAMES.PRIVATE_RUMMY;
import static com.dragon.multigame.GAMES.PRIVATE_TABLE;
import static com.dragon.multigame.GAMES.SEVEN_UP_DOWN;
import static com.dragon.multigame.GAMES.TEENPATTI;
import static com.dragon.multigame.LocationManager.GpsUtils.ENABLE_LOCATION_CODE;
import static com.dragon.multigame.Utils.Functions.convertDpToPixel;
//import static com.dragon.multigame.Utils.Variables.POKER_GAME;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dragon.multigame.Adapter.HomegameListAdapter;
import com.dragon.multigame.BaseActivity;
import com.dragon.multigame.Comman.DialogRestrictUser;
import com.dragon.multigame.Comman.DialogSettingOption;
import com.dragon.multigame.Details.GameDetails_A;
import com.dragon.multigame.Fragments.ActiveTables_BF;
import com.dragon.multigame.Fragments.UserInformation_BT;
import com.dragon.multigame.Interface.ApiRequest;
import com.dragon.multigame.Interface.Callback;
import com.dragon.multigame.Interface.ClassCallback;
import com.dragon.multigame.LocationManager.GetLocationlatlong;
import com.dragon.multigame.LocationManager.GpsUtils;
import com.dragon.multigame.Menu.DialogReferandEarn;
import com.dragon.multigame.MyFlowLayout;
import com.dragon.multigame.R;
import com.dragon.multigame.RedeemCoins.RedeemActivity;
import com.dragon.multigame.SampleClasses.Const;
import com.dragon.multigame.Utils.Animations;
import com.dragon.multigame.Utils.Functions;
import com.dragon.multigame.Utils.SharePref;
import com.dragon.multigame.Utils.Sound;
import com.dragon.multigame.Utils.Variables;
import com.dragon.multigame._AdharBahar.Andhar_Bahar_NewUI;
import com.dragon.multigame._AnimalRoulate.AnimalRoulette_A;
import com.dragon.multigame._CarRoullete.CarRoullete_A;
import com.dragon.multigame._CoinFlip.HeadTail_A;
import com.dragon.multigame._ColorPrediction.ColorPrediction;
import com.dragon.multigame._DragonTiger.DragonTiger_A;
import com.dragon.multigame._LuckJackpot.LuckJackPot_A;
import com.dragon.multigame._Poker.Fragment.PokerActiveTables_BF;
import com.dragon.multigame._RummyDeal.DialogDealType;
import com.dragon.multigame._RummyDeal.Fragments.RummyDealActiveTables_BF;
import com.dragon.multigame._RummyDeal.RummyDealGame;
import com.dragon.multigame._RummyPull.Fragments.RummyActiveTables_BF;
import com.dragon.multigame._SevenUpGames.SevenUp_A;
import com.dragon.multigame._TeenPatti.PublicTable_New;
import com.dragon.multigame.account.LoginScreen;
import com.dragon.multigame.model.HomescreenModel;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.rahman.dialog.Activity.SmartDialog;
import com.rahman.dialog.ListenerCallBack.SmartDialogClickListener;
import com.rahman.dialog.Utilities.SmartDialogBuilder;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Homepage extends BaseActivity {

    List<String> ban_states = new ArrayList<>();
    Animation animBounce, animBlink;
    public static final String MY_PREFS_NAME = "Login_data";
    ImageView imgLogout, imgshare, imaprofile;
    ImageView imgpublicGame, imgPrivategame, ImgCustomePage, ImgVariationGane, iv_andher;
    private String user_id, name, mobile, profile_pic, referral_code, wallet, game_played, bank_detail, adhar_card, upi;
    private TextView txtName, txtwallet, txtproname;
    LinearLayout lnrbuychips, lnrinvite, lnrmail, lnrsetting, lnrvideo;
    Typeface helvatikaboldround, helvatikanormal;
    public String token = "";
    private String game_for_private, app_version;
    SeekBar sBar;
    SeekBar sBarpri;
    ImageView imgCreatetable, imgCreatetablecustom, imgclosetoppri, imgclosetop;
    int pval = 1;
    int pvalpri = 1;
    Button btnCreatetable;
    Button btnCreatetablepri;
    TextView txtStart, txtLimit, txtwalletchips,
            txtwalletchipspri, txtBootamount, txtPotLimit, txtNumberofBlind,
            txtMaximumbetvalue;
    TextView txtStartpri, txtLimitpri, txtBootamountpri, txtPotLimitpri, txtNumberofBlindpri, txtMaximumbetvaluepri;
    RelativeLayout rltimageptofile;
    int version = 0;

    RelativeLayout rootView;

    public static String str_colr_pred = "";
    String base_64 = "";
    ProgressDialog progressDialog;
    Activity context = this;

    String REFERRAL_AMOUNT = "referral_amount";
    String JOINING_AMOUNT = "joining_amount";


    Random random = new Random();

    private void emitBubbles() {
        // It will create a thread and attach it to
        // the main thread
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                int size = random.nextInt(250);
//                bubbleEmitter.emitBubble(size);
//                bubbleEmitter.setColors(android.R.color.transparent,
//                        android.R.color.holo_blue_light,
//                        android.R.color.holo_blue_bright);
//                emitBubbles();
            }
        }, random.nextInt(500));
    }


//    BubbleEmitterView bubbleEmitter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);


        //  View supportButton = findViewById(R.id.support_button);
        // Button supportButton = findViewById(R.id.support_button);

/*
       findViewById(R.id.lnr_support).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Display the support dialog
                showSupportDialog();
            }
        });*/


        findViewById(R.id.lnr_support).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //openTelegramLink();
                // Display the support dialog
                // showSupportDialog();

                String telegramLink = "https://t.me/Bikaaa_2023";

                // Create an intent to open the Telegram link
                Intent telegramIntent = new Intent(Intent.ACTION_VIEW);telegramIntent.setData(Uri.parse(telegramLink));
//
//               // Start the Telegram app
                startActivity(telegramIntent);
            }
        });




        soundMedia = new Sound();
        initGamesTabs();

        SharePref.getInstance().init(context);

        findViewById(R.id.rltDragonTiger).setVisibility(SharePref.getIsDragonTigerVisible()
                ? View.VISIBLE : View.GONE);

        findViewById(R.id.rltTeenpatti).setVisibility(SharePref.getIsTeenpattiVisible()
                ? View.VISIBLE : View.GONE);

        findViewById(R.id.rltPrivate).setVisibility(SharePref.getIsPrivateVisible()
                ? View.VISIBLE : View.GONE);

        findViewById(R.id.rltCustom).setVisibility(SharePref.getIsCustomVisible()
                ? View.VISIBLE : View.GONE);

        findViewById(R.id.rltAndharbhar).setVisibility(SharePref.getIsAndharBaharVisible()
                ? View.VISIBLE : View.GONE);

        findViewById(R.id.rltRummy).setVisibility(SharePref.getIsRummyVisible()
                ? View.VISIBLE : View.GONE);

        findViewById(R.id.rltJackpot).setVisibility(Variables.JACKPOTGAME_SHOW ? View.VISIBLE : View.GONE);
        findViewById(R.id.rltRummyDeal).setVisibility(Variables.RUMMYDEAL_SHOW ? View.VISIBLE : View.GONE);
        findViewById(R.id.rltRummyPool).setVisibility(Variables.RUMMPOOL_SHOW ? View.VISIBLE : View.GONE);
        findViewById(R.id.rltSeveUp).setVisibility(Variables.SEVENUPSDOWN_SHOW ? View.VISIBLE : View.GONE);
        findViewById(R.id.rltCarRoullete).setVisibility(Variables.CARROULETE_SHOW ? View.VISIBLE : View.GONE);
        findViewById(R.id.rltAnimalRoullete).setVisibility(Variables.ANIMALROULETE_SHOW ? View.VISIBLE : View.GONE);


        imgLogout = findViewById(R.id.imgLogout);
        initHomeScreenModel();

        lnrbuychips = findViewById(R.id.lnrbuychips);
        lnrmail = findViewById(R.id.lnrmail);
        lnrinvite = findViewById(R.id.lnrinvite);
        lnrsetting = findViewById(R.id.lnrsetting);
        lnrvideo = findViewById(R.id.lnrvideo);


        imaprofile = findViewById(R.id.imaprofile);


        emitBubbles();

        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");




        FrameLayout home_container = findViewById(R.id.home_container);
        home_container.setVisibility(View.VISIBLE);


        rootView = findViewById(R.id.rlt_animation_layout);
        RelativeLayout relativeLayout = findViewById(R.id.rlt_parent);
//        SetBackgroundImageAsDisplaySize(context,relativeLayout,R.drawable.splash);


//        BonusView();



        // Set fullscreen
        context.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        helvatikaboldround = Typeface.createFromAsset(getAssets(),
                "fonts/helvetica-rounded-bold-5871d05ead8de.otf");

        helvatikanormal = Typeface.createFromAsset(getAssets(),
                "fonts/Helvetica.ttf");

        rltimageptofile = findViewById(R.id.rltimageptofile);

        rltimageptofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UserInformation_BT userInformation_bt = new UserInformation_BT(new Callback() {
                    @Override
                    public void Responce(String resp, String type, Bundle bundle) {
                        UserProfile();
                    }
                });
                userInformation_bt.setCancelable(false);
                userInformation_bt.show(getSupportFragmentManager(),userInformation_bt.getTag());
            }
        });

        imgpublicGame = (ImageView) findViewById(R.id.imgpublicGame);
        imgPrivategame = (ImageView) findViewById(R.id.imgPrivategame);
        ImgCustomePage = (ImageView) findViewById(R.id.ImgCustomePage);
        ImgVariationGane = (ImageView) findViewById(R.id.ImgVariationGane);
        iv_andher = (ImageView) findViewById(R.id.iv_andher);
        txtName = findViewById(R.id.txtName);
        txtName.setTypeface(helvatikaboldround);
        txtwallet = findViewById(R.id.txtwallet);
        txtwallet.setTypeface(helvatikanormal);
        txtproname = findViewById(R.id.txtproname);
        txtproname.setTypeface(helvatikaboldround);
        TextView txtMail = findViewById(R.id.txtMail);




        // load the animation
        animBounce = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.bounce);

        animBlink = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.blink);
        imgpublicGame.startAnimation(animBlink);
        imgpublicGame.startAnimation(animBounce);


        imgPrivategame.startAnimation(animBlink);
        imgPrivategame.startAnimation(animBounce);


        ImgCustomePage.startAnimation(animBlink);
        ImgCustomePage.startAnimation(animBounce);
        ImgCustomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCustomeTeenpatti(view);
            }
        });


        ImgVariationGane.startAnimation(animBlink);
        ImgVariationGane.startAnimation(animBounce);
        clickTask();

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w("TAG", "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        token = task.getResult();

                        // Log and toast
                        // String msg = getString(R.string.msg_token_fmt, token);
                        // Log.d(TAG, msg);
                        // Funtions.showToast(context, token);
                        UserProfile();

                    }
                });


        rotation_animation(((ImageView) findViewById(R.id.imgsetting)),R.anim.rotationback_animation);


        findViewById(R.id.lnr_redeem).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, RedeemActivity.class));

//                LoadFragment(new WalletFragment());
            }
        });
        findViewById(R.id.lnrbuychips).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, BuyChipsList.class));

//                LoadFragment(new WalletFragment());
            }
        });



//        findViewById(R.id.lnr_privacy).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(context, Privacy_Policy.class));
//
////                LoadFragment(new WalletFragment());
//            }
//        });

        findViewById(R.id.lnrhistory).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(context,MyWinningAcitivity.class));
                startActivity(new Intent(context, GameDetails_A.class));

//                showRedeemDailog();
            }
        });
        findViewById(R.id.lnr_mail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                showDailyWinCoins();
            }
        });


        findViewById(R.id.iv_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rotation_animation(findViewById(R.id.iv_add),R.anim.rotationback_animation);
                UserProfile();
            }
        });



    }

    private void showSupportDialog() {




        // Create a custom dialog layout
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.dialog_support, null);

        // Find the EditText and Button in the dialog layout
        final EditText questionInput = dialogView.findViewById(R.id.question_input);
        Button submitButton = dialogView.findViewById(R.id.submit_button);

        // Build the dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);

        // Create the dialog
        final AlertDialog dialog = builder.create();

        // Set a click listener for the "Submit" button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the user's question from the EditText
                String userQuestion = questionInput.getText().toString();

                // Handle the user's question (e.g., send it to a support service)
                // You can add your logic here

                // Clear the question input
                questionInput.setText("");

                // Dismiss the dialog
                dialog.dismiss();
            }
        });

        // Show the dialog
        dialog.show();
    }



    String[] gamelist = {
            "All",
            "Skills",
//            "Sports",
    };
    MyFlowLayout lnrGamesTabs;
    int tabsCount = 0;
    private void initGamesTabs() {
        tabsCount = 0;
        lnrGamesTabs = findViewById(R.id.lnrGamesTabs);
        for (String tabs: gamelist) {
            CreateTabsLayout(tabsCount,tabs);
            tabsCount++;
        }
    }

    private void CreateTabsLayout(final int position, String name) {
        final View view = Functions.CreateDynamicViews(R.layout.item_gamehistory_tabs,lnrGamesTabs,context);
        String strtitle = name;
        view.setTag("" + strtitle);

        TextView title = view.findViewById(R.id.tvGameRecord);

        title.setText(strtitle);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPagerPostion(strtitle);
            }
        });

        if(position == 0)
            setPagerPostion(strtitle);
    }

    String selectedTab = "";
    private void setPagerPostion(String name) {
        for (int i = 0; i < lnrGamesTabs.getChildCount() ; i++) {

            View view  = lnrGamesTabs.getChildAt(i);
            TextView title = view.findViewById(R.id.tvGameRecord);

            if(Functions.getStringFromTextView(title).equalsIgnoreCase(name))
            {
                if(homegameListAdapter != null)
                    homegameListAdapter.getFilter().filter(name);
                title.setTextColor(context.getResources().getColor(R.color.black));
                title.setBackground(context.getResources().getDrawable(R.drawable.btn_yellow_discard));
            } else {
                title.setTextColor(context.getResources().getColor(R.color.white));
                title.setBackground(context.getResources().getDrawable(R.drawable.d_white_corner));
            }

        }
    }

    HomegameListAdapter homegameListAdapter;
    private void initHomeScreenModel() {

        RecyclerView recGamesList = findViewById(R.id.recGamesList);
        int numberOfRows = 2; // For 2 rows.

        int totalItems = getGameList().size();
        int numberOfColumns = totalItems / numberOfRows;

        if (numberOfColumns == 0) {
            numberOfColumns = 1; // Set a minimum of 1 column to avoid division by zero error.
        }

        GridLayoutManager layoutManager = new GridLayoutManager(context, numberOfColumns);
        recGamesList.setLayoutManager(layoutManager);
        recGamesList.setAdapter(homegameListAdapter);






        homegameListAdapter = new HomegameListAdapter(context);
        homegameListAdapter.setArrayList(getGameList());
        homegameListAdapter.setCallback(new ClassCallback() {
            @Override
            public void Response(View v, int position, Object object) {
                Enum gametype = (Enum) object;
                if (TEENPATTI.equals(gametype)) {
                    openPublicTeenpatti(null);
                }
                else if(DRAGON_TIGER.equals(gametype))
                {
                    openDragonTiger(null);
                }else if(ANDHAR_BAHAR.equals(gametype))
                {
                    openAndharbahar(null);
                }else if(POINT_RUMMY.equals(gametype))
                {
                    openRummyGame(null);
                }
                else if(PRIVATE_RUMMY.equals(gametype))
                {
                    openPrivateRummyTable();
//                    DialogRummyCreateTable.getInstance(context).show();
                }else if(POOL_RUMMY.equals(gametype))
                {
                    openRummyPullGame(null);
                }else if(DEAL_RUMMY.equals(gametype))
                {
                    openRummyDealGame(null);
                }else if(PRIVATE_TABLE.equals(gametype))
                {
                    openPrivateTeenpatti(null);
                }else if(CUSTOM_TABLE.equals(gametype))
                {
                    openCustomeTeenpatti(null);
                }else if(SEVEN_UP_DOWN.equals(gametype))
                {
                    openSevenup(null);
                }else if(CAR_ROULETTE.equals(gametype))
                {
                    openCarRoulette(null);
                }else if(JACKPOT_3PATTI.equals(gametype))
                {
                    openLuckJackpotActivity(null);
                }else if(ANIMAL_ROULETTE.equals(gametype))
                {
                    openAnimalRoullete(null);
                }else if(COLOUR_PREDICTION.equals(gametype))
                {
                    openColorPred(null);
                }else if(POKER_GAME.equals(gametype))
                {
                    openPokerGame(null);
                }
                else if(HEAD_TAIL.equals(gametype))
                {
                    openHeadTailGame(null);
                }
                else{
                    Functions.showToast(context,"Coming soon!");
                }
            }
        });
        recGamesList.setAdapter(homegameListAdapter);
    }

    private void openHeadTailGame(View view) {
        startActivity(new Intent(getApplicationContext(), HeadTail_A.class));
    }

    private void openPrivateRummyTable() {
        LoadTableFragments(ActiveTables_BF.RUMMY_PRIVATE_TABLE);
    }

    private List<HomescreenModel> getGameList() {
        List<HomescreenModel> arraylist = new ArrayList<>();
//        if(SharePref.getIsTeenpattiVisible())
//            arraylist.add(new HomescreenModel(1,TEENPATTI,R.drawable.home_public_img,"Skills"));
        if(SharePref.getIsDragonTigerVisible())
            arraylist.add(new HomescreenModel(2,DRAGON_TIGER,R.drawable.home_dragontiger,"All"));
//        if(SharePref.getIsAndharBaharVisible())
//            arraylist.add(new HomescreenModel(3,ANDHAR_BAHAR,R.drawable.home_andherbahar,"All"));
//        if(SharePref.getIsRummyVisible())
//            arraylist.add(new HomescreenModel(4,POINT_RUMMY,R.drawable.home_rummy_point,"Skills"));
//       if(Variables.PRIVATE_RUMMY_SHOW)
//            arraylist.add(new HomescreenModel(4,PRIVATE_RUMMY,R.drawable.home_private_rummy,"Skills"));
        //   if(Variables.RUMMPOOL_SHOW)
        //      arraylist.add(new HomescreenModel(5,POOL_RUMMY,R.drawable.home_rummy_pool,"Skills"));
        //    if(Variables.RUMMYDEAL_SHOW)
        //        arraylist.add(new HomescreenModel(6,DEAL_RUMMY,R.drawable.home_rummy_deal,"Skills"));
//        if(SharePref.getIsPrivateVisible())
//            arraylist.add(new HomescreenModel(7,PRIVATE_TABLE,R.drawable.home_private_table,"Skills"));
//       if(SharePref.getIsCustomVisible())
//           arraylist.add(new HomescreenModel(8,CUSTOM_TABLE,R.drawable.home_custom_table,"Skills"));

//        if(Variables.SEVENUPSDOWN_SHOW)
//            arraylist.add(new HomescreenModel(9,SEVEN_UP_DOWN,R.drawable.home_sevenupdown,"All"));
//        if(Variables.CARROULETE_SHOW)
//            arraylist.add(new HomescreenModel(10,CAR_ROULETTE,R.drawable.home_car_rouleti,"All"));
//        if(Variables.JACKPOTGAME_SHOW)
//            arraylist.add(new HomescreenModel(11,JACKPOT_3PATTI,R.drawable.home_jack_pot,"All"));
//        if(Variables.ANIMALROULETE_SHOW)
//            arraylist.add(new HomescreenModel(12,ANIMAL_ROULETTE,R.drawable.home_animal_roulette,"All"));
//        if(Variables.COLOR_PREDICTION)
//            arraylist.add(new HomescreenModel(13,COLOUR_PREDICTION,R.drawable.home_colour_prediction,"All"));
//
//        if(Variables.POKER_GAME)
//            arraylist.add(new HomescreenModel(14,POKER_GAME,R.drawable.home_poker,"Skills"));
//
//        if(Variables.HEAD_TAIL)
//            arraylist.add(new HomescreenModel(15,HEAD_TAIL,R.drawable.tournament,"All"));


//        arraylist.add(new HomescreenModel(15,CRICKET_GAME,R.drawable.home_cricket,"Sports"));
//        arraylist.add(new HomescreenModel(16,BEST_OF_5,R.drawable.home_best_of_5,"All"));
//        arraylist.add(new HomescreenModel(17,LUDO_GAME,R.drawable.home_ludo,"Skills"));
        return arraylist;
    }

    private void BonusView(){

        if(SharePref.getInstance().getBoolean(SharePref.isBonusShow))
            lnrmail.setVisibility(View.GONE);
        else
            lnrmail.setVisibility(View.GONE);

        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);


        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String datevalue = prefs.getString("cur_date4", "12/06/2020");



        SimpleDateFormat df1 = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate1 = df1.format(c);
        int dateDifference = (int) getDateDiff(new SimpleDateFormat("dd/MM/yyyy"), datevalue,formattedDate1);


        /*if (dateDifference > 0) {
            // catalog_outdated = 1;

            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            String formattedDate = df.format(c);

            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putString("cur_date4", formattedDate);
            editor.apply();

            if(SharePref.getInstance().getBoolean(SharePref.isBonusShow))
                showDailyWinCoins();

        }else {

            System.out.println("");


        }*/

//        lnrmail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
////                Intent intent = new Intent(getApplicationContext(), MaiUserListingActivity.class);
////                startActivity(intent);
////                Funtions.showToast(context, "Coming Soon",
////                        Toast.LENGTH_LONG).show();
//                showDailyWinCoins();
//
//            }
//        });
    }

   /* private void showDailyWinCoins() {
        DialogDailyBonus.getInstance(context).returnCallback(new Callback() {
            @Override
            public void Responce(String resp, String type, Bundle bundle) {
                UserProfile();
            }
        }).show();
    }*/

    private void LoadFragment(Fragment fragment)
    {
        getSupportFragmentManager().
                beginTransaction().
                replace(R.id.home_container,fragment).
                addToBackStack(null).
                commit();
    }

    private void BlinkAnimation(final View view) {
        view.setVisibility(View.VISIBLE);
        final Animation animationUtils = AnimationUtils.loadAnimation(context,R.anim.blink);
        animationUtils.setDuration(1000);
        animationUtils.setRepeatCount(1);
        animationUtils.setStartOffset(700);
        view.startAnimation(animationUtils);

        animationUtils.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    private void shineAnimation(final View view) {
        final Animation animationUtils = AnimationUtils.loadAnimation(context,R.anim.left_to_righ);
        animationUtils.setDuration(1500);
        view.startAnimation(animationUtils);

        animationUtils.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.startAnimation(animationUtils);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }




    LinearLayout lnr_2player,lnr_5player;
    TextView tv_2player,tv_5player;

    int selected_type = 5;
    public void Dialog_SelectPlayer() {
        final Dialog dialog = Functions.DialogInstance(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setTitle("");
        dialog.setContentView(R.layout.dialog_select_palyer);
        lnr_2player = dialog.findViewById(R.id.lnr_2player);
        lnr_5player = dialog.findViewById(R.id.lnr_5player);
        tv_2player =  (TextView) dialog.findViewById(R.id.tv_2player);
        tv_5player =  (TextView) dialog.findViewById(R.id.tv_5player);
        imgclosetop=dialog.findViewById(R.id.imgclosetop) ;

        Button btn_player = dialog.findViewById(R.id.btn_play);

        ChangeTextviewColorChange(5);

        lnr_2player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeTextviewColorChange(2);
            }
        });

        lnr_5player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeTextviewColorChange(5);
            }
        });
        imgclosetop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btn_player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

                if(selected_type == 2)
                {
                    LoadTableFragments(ActiveTables_BF.RUMMY2);

                }
                else {
                    LoadTableFragments(ActiveTables_BF.RUMMY5);


                }


            }
        });

        dialog.setCancelable(true);
        dialog.show();
        Functions.setDialogParams(dialog);
        Window window = dialog.getWindow();
        window.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));


    }

    public void Dialog_SelectPlayerPool() {
        final Dialog dialog = Functions.DialogInstance(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setTitle("");
        dialog.setContentView(R.layout.dialog_select_palyer);
        lnr_2player = dialog.findViewById(R.id.lnr_2player);
        lnr_5player = dialog.findViewById(R.id.lnr_5player);
        tv_2player =  (TextView) dialog.findViewById(R.id.tv_2player);
        tv_5player =  (TextView) dialog.findViewById(R.id.tv_5player);
        tv_5player.setText("6 \nPlayer");

        imgclosetop=dialog.findViewById(R.id.imgclosetop);

        Button btn_player = dialog.findViewById(R.id.btn_play);

        ChangeTextviewColorChange(5);

        lnr_2player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeTextviewColorChange(2);
            }
        });

        lnr_5player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeTextviewColorChange(5);
            }
        });


        imgclosetop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btn_player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

                if(selected_type == 2)
                {
                    LoadPullRummyActiveTable(ActiveTables_BF.RUMMY2);

                }
                else {
                    LoadPullRummyActiveTable(ActiveTables_BF.RUMMY5);
                }


            }
        });

        dialog.setCancelable(true);
        dialog.show();
        Functions.setDialogParams(dialog);
        Window window = dialog.getWindow();
        window.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));


    }


    private void LoadTableFragments(String TAG){
        ActiveTables_BF activeTables_bf = new ActiveTables_BF(TAG);
        activeTables_bf.show(getSupportFragmentManager(),activeTables_bf.getTag());
    }

    private void LoadPointRummyActiveTable(String TAG){
        RummyActiveTables_BF rummyActiveTables_bf = new RummyActiveTables_BF(TAG);
        rummyActiveTables_bf.show(getSupportFragmentManager(),rummyActiveTables_bf.getTag());
    }

    private void ChangeTextviewColorChange(int colortype){

        selected_type = colortype;

        if(colortype == 2)
        {
            tv_2player.setTextColor(getResources().getColor(R.color.white));
            lnr_2player.setBackgroundColor(getResources().getColor(R.color.new_colorPrimary));

            tv_5player.setTextColor(getResources().getColor(R.color.black));
            lnr_5player.setBackgroundColor(getResources().getColor(R.color.white));

        }
        else {
            tv_2player.setTextColor(getResources().getColor(R.color.black));
            lnr_2player.setBackgroundColor(getResources().getColor(R.color.white));

            tv_5player.setTextColor(getResources().getColor(R.color.white));
            lnr_5player.setBackgroundColor(getResources().getColor(R.color.new_colorPrimary));

        }


    }



    private void rotation_animation(View view,int animation){

        Animation circle =  Functions.AnimationListner(context, animation, new Callback() {
            @Override
            public void Responce(String resp, String type, Bundle bundle) {

            }
        });
        view.setAnimation(circle);
        circle.startNow();

    }

    int Counts = 100;
    int postion = -100;
    private void CenterAnimationView(String imagename,View imgcards,int Home_Page_Animation){


        int AnimationSpeed = Counts + Home_Page_Animation;
        Counts += 300;

        final View fromView, toView;
        rootView.setVisibility(View.VISIBLE);
//        rootView.removeAllViews();
//        View ivpickcard = findViewById(R.id.view_center);

        fromView = rootView;
        toView = imgcards;


        int fromLoc[] = new int[2];
        fromView.getLocationOnScreen(fromLoc);
        float startX = fromLoc[0];
        float startY = fromLoc[1];

        int toLoc[] = new int[2];
        toView.getLocationOnScreen(toLoc);
        float destX = toLoc[0];
        float destY = toLoc[1];

        final ImageView sticker = new ImageView(context);

        int stickerId = Functions.GetResourcePath(imagename,context);

        int card_width = (int) getResources().getDimension(R.dimen.home_card_width);
        int card_hieght = (int) getResources().getDimension(R.dimen.home_card_height);

        Picasso.get().load(stickerId).into(sticker);

        sticker.setLayoutParams(new ViewGroup.LayoutParams(card_width, card_hieght));
        rootView.addView(sticker);


        Animations anim = new Animations();
        Animation a = anim.fromAtoB(0, 0, convertDpToPixel(postion,context), 0, null, AnimationSpeed, new Callback() {
            @Override
            public void Responce(String resp, String type, Bundle bundle) {
                fromView.setVisibility(View.VISIBLE);
                toView.setVisibility(View.VISIBLE);
                sticker.setVisibility(View.GONE);
            }
        });
        sticker.setAnimation(a);
        a.startNow();

        postion += 100;

    }

    @Override
    protected void onResume() {
        super.onResume();
        str_colr_pred="";
//        EnableGPS();
        UserProfile();
        GameLeave();
    }

    public void clickTask() {

        imgPrivategame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //showDialoag();
                openPrivateTeenpatti(null);

            }
        });

        lnrsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogSettingOption dialogSettingOption = new DialogSettingOption(context)
                {
                    public void playstopSound() {

                        playSound(R.raw.game_soundtrack,true);

                    }
                };

                dialogSettingOption.showDialogSetting();
            }
        });

        lnrvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Functions.showToast(context, "Coming Soon");
            }
        });

        imgLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                new AlertDialog.Builder(context)
//                        .setIcon(android.R.drawable.ic_dialog_alert)
//                        .setTitle("Logout")
//                        .setMessage("Do you want to Logout?")
//                        .setPositiveButton("Yes", new DialogInterface.OnClickListener()
//                        {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
//                                editor.putString("user_id", "");
//                                editor.putString("name", "");
//                                editor.putString("mobile", "");
//                                editor.putString("token", "");
//                                editor.apply();
//                                Intent intent = new Intent(context, LoginScreen.class);
//                                startActivity(intent);
//                                finish();
//                            }
//
//                        })
//                        .setNegativeButton("No", null)
//                        .show();


                new SmartDialogBuilder(context)
                        .setTitle("Do you want to Logout?")
                        //.setSubTitle("context is the alert dialog to showing alert to user")
                        .setCancalable(true)
                        //.setTitleFont("Do you want to Logout?") //set title font
                        // .setSubTitleFont(subTitleFont) //set sub title font
                        .setNegativeButtonHide(true) //hide cancel button
                        .setPositiveButton("Logout", new SmartDialogClickListener() {
                            @Override
                            public void onClick(SmartDialog smartDialog) {
                                // Funtions.showToast(context,"Ok button Click",Toast.LENGTH_SHORT)
                                // .show();
                                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                                editor.putString("user_id", "");
                                editor.putString("name", "");
                                editor.putString("mobile", "");
                                editor.putString("token", "");
                                editor.apply();
                                Intent intent = new Intent(context, LoginScreen.class);
                                startActivity(intent);
                                finish();

                                smartDialog.dismiss();
                            }
                        }).setNegativeButton("Cancel", new SmartDialogClickListener() {
                            @Override
                            public void onClick(SmartDialog smartDialog) {
                                // Funtions.showToast(context,"Cancel button Click");
                                smartDialog.dismiss();

                            }
                        }).build().show();

//                new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
//                        .setTitleText("Are you sure?")
//                        .setContentText("Won't be able to recover context file!")
//                        .setConfirmText("Yes,delete it!")
//                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                            @Override
//                            public void onClick(SweetAlertDialog sDialog) {
//                                sDialog.dismissWithAnimation();
//                            }
//                        })
//                        .show();
            }
        });

        lnrinvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogReferandEarn.getInstance(context).show();
            }
        });
    }

    public void openBuyChipsActivity(View view){
        Intent intent = new Intent(context, Add_Cash_Manual.class);
        intent.putExtra("homepage","homepage");
        startActivity(intent);
    }



    private void UserProfile() {

        Functions.showLoader(context,false,false);

        HashMap<String, String> params = new HashMap<String, String>();
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        params.put("id", prefs.getString("user_id", ""));
        params.put("fcm", token);

        try {
            PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            version = pInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        params.put("app_version", version + "");
        params.put("token", prefs.getString("token", ""));

        ApiRequest.Call_Api(context, Const.PROFILE, params, new Callback() {
            @Override
            public void Responce(String resp, String type, Bundle bundle) {

                if(resp != null)
                {
                    ParseResponse(resp);
                }

            }
        });

    }

    private void ParseResponse(String response){
        try {
            JSONObject jsonObject = new JSONObject(response);
            String code = jsonObject.getString("code");
            if (code.equalsIgnoreCase("200")) {
                JSONObject jsonObject0 = jsonObject.getJSONArray("user_data").getJSONObject(0);
                user_id = jsonObject0.getString("id");
                name = jsonObject0.optString("name");
                mobile = jsonObject0.optString("mobile");
                profile_pic = jsonObject0.optString("profile_pic");
                referral_code = jsonObject0.optString("referral_code");
                wallet = jsonObject0.optString("wallet");
                String winning_wallet = jsonObject0.optString("winning_wallet");
                game_played = jsonObject0.optString("game_played");
                bank_detail = jsonObject0.optString("bank_detail");
                upi = jsonObject0.optString("upi");
                adhar_card = jsonObject0.optString("adhar_card");


                // txtName.setText("Welcome Back "+name);

                if(Functions.isStringValid(wallet))
                {
                    float f_wallet = Float.parseFloat(wallet);
//                    long numberamount =  Float.parseFloat(f_wallet);
//                    long numberamount = (long) f_wallet;
//                    txtwallet.setText("" + NumberFormat.getNumberInstance(Locale.US).format(f_wallet));
                    txtwallet.setText("" + f_wallet);
                }


                txtproname.setText(name);
                Picasso.get().load(Const.IMGAE_PATH + profile_pic).into(imaprofile);


                String setting = jsonObject.optString("setting");
                JSONObject jsonObjectSetting = new JSONObject(setting);
                JSONArray avatar = jsonObject.getJSONArray("avatar");

                game_for_private = jsonObjectSetting.optString("game_for_private");
                app_version = jsonObjectSetting.optString("app_version");
                String  referral_amount = jsonObjectSetting.optString("referral_amount");
                String  referral_link = jsonObjectSetting.optString("referral_link");
                String  joining_amount = jsonObjectSetting.optString("joining_amount");
                String whats_no = jsonObjectSetting.optString("whats_no");
                String mBan_states = jsonObjectSetting.optString("ban_states");

                int admin_commission = jsonObjectSetting.optInt("admin_commission",2);

                SharePref.getInstance().putInt(SharePref.ADMIN_COMMISSION,admin_commission);

                ban_states = Arrays.asList(mBan_states.split(","));
//                checkForBanState();

                String GodmodMobilenumber = jsonObjectSetting.optString("mobile");

                if(mobile.equalsIgnoreCase(GodmodMobilenumber))
                    SharePref.getInstance().putGodmodeValue(true);

                // bonus = 0=no , 1=yes
                // payment_gateway = 0=payment , 1=whatsapp
                // symbol = 0=coin , 1=rupees,2 = dollar

                String symbol = jsonObjectSetting.optString("symbol");
                String payment_gateway = jsonObjectSetting.optString("payment_gateway");
                String bonus = jsonObjectSetting.optString("bonus");
                String razor_api_key = jsonObjectSetting.optString("razor_api_key");

                String cashfree_client_id = jsonObjectSetting.optString("cashfree_client_id");
                String cashfree_stage = jsonObjectSetting.optString("cashfree_stage");
                String paytm_mercent_id = jsonObjectSetting.optString("paytm_mercent_id");
                String payumoney_key = jsonObjectSetting.optString("payumoney_key");

                String share_text = jsonObjectSetting.optString("share_text");

                if(Functions.isStringValid(bonus) && !bonus.equalsIgnoreCase("0"))
                    SharePref.getInstance().putBoolean(SharePref.isBonusShow,true);
                else
                    SharePref.getInstance().putBoolean(SharePref.isBonusShow,false);

                if(Functions.isStringValid(payment_gateway)
                        && !payment_gateway.equalsIgnoreCase("1"))
                    SharePref.getInstance().putBoolean(SharePref.isPaymentGateShow,true);
                else
                    SharePref.getInstance().putBoolean(SharePref.isPaymentGateShow,false);



                String payment_type= payment_gateway.equals("0") ? Variables.RAZOR_PAY
                        : payment_gateway.equals("1") ? Variables.WHATS_APP
                        : payment_gateway.equals("2") ? Variables.CASH_FREE
                        : payment_gateway.equals("4") ? Variables.PAYUMONEY
                        : payment_gateway.equals("5") ? Variables.UPI_FREE_WAY
                        : payment_gateway.equals("6") ? Variables.ATOM_PAY
                        :  Variables.PAYTM;

                SharePref.getInstance().putString(SharePref.PaymentType,payment_type);



                if(Functions.isStringValid(symbol) && symbol.equalsIgnoreCase("0"))
                    Variables.CURRENCY_SYMBOL = Variables.COINS;
                else if(Functions.isStringValid(symbol) && symbol.equalsIgnoreCase("1"))
                {
                    Variables.CURRENCY_SYMBOL = Variables.RUPEES;
                }
                else if(Functions.isStringValid(symbol) && symbol.equalsIgnoreCase("2"))
                {
                    Variables.CURRENCY_SYMBOL = Variables.DOLLAR;
                }



                BonusView();

                SharePref.getInstance().putString(SharePref.SYMBOL_TYPE,Variables.CURRENCY_SYMBOL);
                SharePref.getInstance().putString(SharePref.RAZOR_PAY_KEY,razor_api_key);
                SharePref.getInstance().putString(SharePref.CASHFREE_CLIENT_ID,cashfree_client_id);
                SharePref.getInstance().putString(SharePref.CASHFREE_STAGE,cashfree_stage);
                SharePref.getInstance().putString(SharePref.PAYTM_MERCENT_ID,paytm_mercent_id);
                SharePref.getInstance().putString(SharePref.PAYU_MERCENT_KEY,payumoney_key);
                SharePref.getInstance().putString(SharePref.referral_link,referral_link);
                SharePref.getInstance().putString(SharePref.avator,avatar.toString());

                SharePref.getInstance().putString(SharePref.SHARE_APP_TEXT,share_text);

                ((ImageView) findViewById(R.id.imgicon)).setImageDrawable(
                        Variables.CURRENCY_SYMBOL.equalsIgnoreCase(Variables.RUPEES) ? Functions.getDrawable(context,R.drawable.ic_currency_dollar) :
                                Variables.CURRENCY_SYMBOL.equalsIgnoreCase(Variables.DOLLAR) ? Functions.getDrawable(context,R.drawable.ic_currency_dollar) :
                                        Functions.getDrawable(context,R.drawable.ic_currency_chip));

                try {

//                    int app_versionint = Integer.parseInt(app_version);
//
//                    //if (version > app_versionint){
//                if (app_versionint > version) {
//
//                    showAppUpdateDialog("Update");
//
//                } else {
//
//
//                }
                }
                catch (NumberFormatException e)
                {
                    e.printStackTrace();
                }





                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putString("name", name);
                editor.putString("profile_pic", profile_pic);
                editor.putString("bank_detail", bank_detail);
                editor.putString("upi", upi);
                editor.putString("adhar_card", adhar_card);
                editor.putString("mobile", mobile);
                editor.putString("referal_code", referral_code);
                editor.putString("img_name", profile_pic);
                editor.putString("winning_wallet", winning_wallet);
                editor.putString("wallet", wallet);
                editor.putString("game_for_private", game_for_private);
                editor.putString("app_version", app_version);
                editor.putString("whats_no", whats_no);
                editor.putString(REFERRAL_AMOUNT, referral_amount);
                editor.putString(JOINING_AMOUNT, joining_amount);
                editor.apply();


            } else if (code.equals("411")) {

                Intent intent = new Intent(context, LoginScreen.class);
                startActivity(intent);
                finishAffinity();
                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putString("name", "");
                editor.putString("referal_code", "");
                editor.putString("img_name", "");
                editor.putString("game_for_private", "");
                editor.putString("app_version", "");
                editor.apply();

                Functions.showToast(context, "You are Logged in from another " +
                        "device.");


            } else {

                if (jsonObject.has("message")) {
                    String message = jsonObject.getString("message");
                    Functions.showToast(context, message);
                }


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        findViewById(R.id.iv_add).clearAnimation();
        Functions.dismissLoader();

    }

    public void PlaySaund(int sound) {

        if(!SharePref.getInstance().isSoundEnable())
            return;

        final MediaPlayer mp = MediaPlayer.create(context,
                sound);
        mp.start();
    }

    public void showDialoagPrivettable() {

        // custom dialog
        final Dialog dialog = Functions.DialogInstance(context);
        dialog.setContentView(R.layout.custom_dialog_custon_boot);
        dialog.setTitle("Title...");
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        sBarpri = (SeekBar) dialog.findViewById(R.id.seekBar1);
        sBarpri.setProgress(0);
        sBarpri.incrementProgressBy(10);
        sBarpri.setMax(100);
        txtStartpri = (TextView) dialog.findViewById(R.id.txtStart);
        txtLimitpri = (TextView) dialog.findViewById(R.id.txtLimit);
        txtwalletchipspri = (TextView) dialog.findViewById(R.id.txtwalletchips);
        float numberamount = Float.parseFloat(wallet);
        txtwalletchipspri.setText("" + NumberFormat.getNumberInstance(Locale.US).format(numberamount));

        // txtwalletchipspri.setText(wallet);
        txtBootamountpri = (TextView) dialog.findViewById(R.id.txtBootamount);
        txtPotLimitpri = (TextView) dialog.findViewById(R.id.txtPotLimit);
        txtNumberofBlindpri = (TextView) dialog.findViewById(R.id.txtNumberofBlind);
        txtMaximumbetvaluepri = (TextView) dialog.findViewById(R.id.txtMaximumbetvalue);
        imgclosetoppri = (ImageView) dialog.findViewById(R.id.imgclosetop);
        imgclosetoppri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        imgCreatetable = (ImageView) dialog.findViewById(R.id.imgCreatetable);
        imgCreatetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(context, PrivateTablev2.class);
                Intent intent = new Intent(context, PublicTable_New.class);
                intent.putExtra("gametype", PublicTable_New.PRIVATE_TABLE);
                intent.putExtra("bootvalue", pvalpri + "");
                startActivity(intent);
                dialog.dismiss();
            }
        });
        // tView.setText(sBar.getProgress() + "/" + sBar.getMax());
        sBarpri.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress = progress / 10;
                if (progress == 1) {

                    pvalpri = 50;

                } else if (progress == 2) {
                    pvalpri = 100;
                } else if (progress == 3) {

                    pvalpri = 500;
                } else if (progress == 4) {

                    pvalpri = 1000;

                } else if (progress == 5) {

                    pvalpri = 5000;

                } else if (progress == 6) {

                    pvalpri = 10000;
                } else if (progress == 7) {

                    pvalpri = 25000;
                } else if (progress == 8) {

                    pvalpri = 50000;
                } else if (progress == 9) {

                    pvalpri = 100000;
                } else if (progress == 10) {

                    pvalpri = 250000;
                }

                //progress = progress * 10;
                // pvalpri = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //write custom code to on start progress
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                txtBootamountpri.setText("Boot amount : " + kvalue(pvalpri) + "");

                int valueforpot = pvalpri * 1024;
                int valueformaxi = pvalpri * 128;

                //long valueforpotlong= valueforpot;

                txtPotLimitpri.setText("Pot limit : " + kvalue(valueforpot) + "");
                txtMaximumbetvaluepri.setText("Maximumbet balue : " + kvalue(valueformaxi) + "");
                txtNumberofBlindpri.setText("Number of Blinds : 4");
                //tView.setText(pval + "/" + seekBar.getMax());
            }
        });


        dialog.show();
        Functions.setDialogParams(dialog);
    }

    public void showDialoagonBack() {

        // custom dialog
        final Dialog dialog = Functions.DialogInstance(context);
        dialog.setContentView(R.layout.custom_dialog_custon_boot);
        dialog.setTitle("Title...");
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        sBar = (SeekBar) dialog.findViewById(R.id.seekBar1);
        sBar.setProgress(0);
        sBar.incrementProgressBy(10);
        sBar.setMax(100);
        txtStart = (TextView) dialog.findViewById(R.id.txtStart);
        txtLimit = (TextView) dialog.findViewById(R.id.txtLimit);
        txtwalletchips = (TextView) dialog.findViewById(R.id.txtwalletchips);
        float numberamount = Float.parseFloat(wallet);
        txtwalletchips.setText("" + NumberFormat.getNumberInstance(Locale.US).format(numberamount));
        txtBootamount = (TextView) dialog.findViewById(R.id.txtBootamount);
        txtPotLimit = (TextView) dialog.findViewById(R.id.txtPotLimit);
        txtNumberofBlind = (TextView) dialog.findViewById(R.id.txtNumberofBlind);
        txtMaximumbetvalue = (TextView) dialog.findViewById(R.id.txtMaximumbetvalue);
        imgclosetop = (ImageView) dialog.findViewById(R.id.imgclosetop);
        imgclosetop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        imgCreatetablecustom = (ImageView) dialog.findViewById(R.id.imgCreatetable);
        imgCreatetablecustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(context, CustomisedTablev2.class);
                Intent intent = new Intent(context, PublicTable_New.class);
                intent.putExtra("gametype", PublicTable_New.CUSTOME_TABLE);
                intent.putExtra("bootvalue", pval + "");
                startActivity(intent);
                dialog.dismiss();
            }
        });
        // tView.setText(sBar.getProgress() + "/" + sBar.getMax());
        sBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                pval = progress;
                progress = progress / 10;
                if (progress == 1) {

                    pval = 50;

                } else if (progress == 2) {
                    pval = 100;
                } else if (progress == 3) {

                    pval = 500;
                } else if (progress == 4) {

                    pval = 1000;

                } else if (progress == 5) {

                    pval = 5000;

                } else if (progress == 6) {

                    pval = 10000;
                } else if (progress == 7) {

                    pval = 25000;
                } else if (progress == 8) {

                    pval = 50000;
                } else if (progress == 9) {

                    pval = 100000;
                } else if (progress == 10) {

                    pval = 250000;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //write custom code to on start progress
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                txtBootamount.setText("Boot amount : " + kvalue(pval) + "");

                int valueforpot = pval * 1024;
                int valueformaxi = pval * 128;
                txtPotLimit.setText("Pot limit : " + kvalue(valueforpot) + "");
                txtMaximumbetvalue.setText("Maximumbet balue : " + kvalue(valueformaxi) + "");
                txtNumberofBlind.setText("Number of Blinds : 4");
                //tView.setText(pval + "/" + seekBar.getMax());
            }
        });


        dialog.show();
        Functions.setDialogParams(dialog);
    }

    private void GameLeave() {


        StringRequest stringRequest = new StringRequest(Request.Method.POST, Const.GAME_TABLE_LEAVE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //progressDialog.dismiss();
                        System.out.println("" + response);
                        // finish();

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
                params.put("user_id", prefs.getString("user_id", ""));
                params.put("token", prefs.getString("token", ""));
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("token", Const.TOKEN);
                return headers;
            }
        };

        Volley.newRequestQueue(context).add(stringRequest);

    }

    public void openLuckJackpotActivity(View view) {
        startActivity(new Intent(context, LuckJackPot_A.class));
    }

    public void openSevenup(View view) {
        startActivity(new Intent(context, SevenUp_A.class));
    }

    public void openPublicTeenpatti(View view) {
        PlaySaund(R.raw.buttontouchsound);
        LoadTableFragments(ActiveTables_BF.TEENPATTI);
    }

    public void openPrivateTeenpatti(View view) {
        PlaySaund(R.raw.buttontouchsound);
        float gamecount = 0;
        if(game_played != null && !game_played.equals(""))
        {
            gamecount  = Float.parseFloat(game_played);
        }
        float game_for_privatetemp = Float.parseFloat(game_for_private);
        if (gamecount > game_for_privatetemp ){

            showDialoagPrivettable();

        }else {

            Functions.showToast(context, "To Unblock Private Table you have to Play at least "+game_for_privatetemp+
                    " Games.");

        }
    }

    public void openCustomeTeenpatti(View view) {
        PlaySaund(R.raw.buttontouchsound);
        showDialoagonBack();
    }

    public void openRummyGame(View view) {
        Dialog_SelectPlayer();
//        LoadPointRummyActiveTable(ActiveTables_BF.RUMMY5);
    }

    public void openRummyPullGame(View view) {
        Dialog_SelectPlayerPool();
    }

    public void openPokerGame(View view) {
        LoadPokerGameActiveTable(ActiveTables_BF.RUMMY5);
    }


    public void openRummyDealGame(View view) {
//        LoadDealRummyActiveTable(ActiveTables_BF.RUMMY2);
        DialogDealType dialogDealType = new DialogDealType(this){
            @Override
            protected void startGame(Bundle bundle) {
                openRummyGames(bundle);
            }
        };
        dialogDealType.show();
    }

    private void openRummyGames(Bundle bundle){
        Intent intent = new Intent(context, RummyDealGame.class);
        if(bundle != null)
            intent.putExtras(bundle);

        if(context != null && !context.isFinishing())
            context.startActivity(intent);
    }


    public void openAndharbahar(View view) {
//        startActivity(new Intent(context, AndharBahar_Home_A.class));
        startActivity(new Intent(context, Andhar_Bahar_NewUI.class).putExtra("room_id","1"));
    }

    public void openColorPred(View view) {
//        str_colr_pred="1";
        Intent intent = new Intent(context, ColorPrediction.class);
//        intent.putExtra("room_id" ,gameModelArrayList.get(position).getId());
//        intent.putExtra("min_coin" ,gameModelArrayList.get(position).getMin_coin());
//        intent.putExtra("max_coin" ,gameModelArrayList.get(position).getMax_coin());
        context.startActivity(intent);
    }


    public void openDragonTiger(View view) {
        startActivity(new Intent(context, DragonTiger_A.class));
    }

    public void openCarRoulette(View view) {
        startActivity(new Intent(context, CarRoullete_A.class));
    }

    public void openAnimalRoullete(View view) {
        startActivity(new Intent(context, AnimalRoulette_A.class));
    }

    public interface itemClick{
        void OnDailyClick(String id);
    }
    TextView txtnotfound;

    private void LoadPullRummyActiveTable(String TAG){
        RummyActiveTables_BF rummyActiveTables_bf = new RummyActiveTables_BF(TAG);
        rummyActiveTables_bf.show(getSupportFragmentManager(),rummyActiveTables_bf.getTag());
    }

    private void LoadPokerGameActiveTable(String TAG){
        PokerActiveTables_BF pokerActiveTables_bf = new PokerActiveTables_BF(TAG);
        pokerActiveTables_bf.show(getSupportFragmentManager(),pokerActiveTables_bf.getTag());
    }

    private void LoadDealRummyActiveTable(String TAG){
        RummyDealActiveTables_BF rummyDealActiveTables_bf = new RummyDealActiveTables_BF(TAG);
        rummyDealActiveTables_bf.show(getSupportFragmentManager(),rummyDealActiveTables_bf.getTag());
    }


    public static long getDateDiff(SimpleDateFormat format, String oldDate, String newDate) {
        try {
            return TimeUnit.DAYS.convert(format.parse(newDate).getTime() - format.parse(oldDate).getTime(), TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }


    public String kvalue (int number){

        String numberString = "";
        if (Math.abs(number / 1000000) > 1) {
            numberString = (number / 1000000) + "m";

        } else if (Math.abs(number / 1000) > 1) {
            numberString = (number / 1000) + "k";

        } else {
            numberString = number+"";

        }
        return numberString;
    }

    @Override
    protected void onStart() {
        super.onStart();

        playSound(R.raw.game_soundtrack,true);
    }

    @Override
    protected void onDestroy() {
        stopPlaying();
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopPlaying();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopPlaying();
    }

    Sound soundMedia;
    public void playSound(int sound,boolean isloop) {

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String value = prefs.getString("issoundon", "1");

        if (value.equals("1")) {
            soundMedia.PlaySong(sound,isloop,context);
        }
        else {
            stopPlaying();
        }


    }

    private void stopPlaying() {
        soundMedia.StopSong();
    }

    private void checkForBanState() {
        String user_state = "";
        if(Variables.latitude > 0 && Variables.longitude > 0)
        {
            Address address = getAddressFromLatLong(Variables.latitude,Variables.longitude,context);
            if(address != null)
                user_state = address.getAdminArea();
        }

        for (String state: ban_states) {
            if(state.trim().equalsIgnoreCase(user_state))
            {
                DialogRestrictUser.getInstance(context).show();
                break;
            }
        }
    }

    public static Address getAddressFromLatLong(double lat, double long_temp, Context context) {

        Address address = null;

        if (lat <= 0 && long_temp  <= 0)
            return address;

        try {
            Geocoder geocoder;
            List<Address> addresses;
            geocoder = new Geocoder(context, Locale.getDefault());

            addresses = geocoder.getFromLocation(lat, long_temp, 1);
            address = addresses.get(0);

        } catch (Exception e) {
            e.printStackTrace();
            // new GetClass().execute();
        }

        return address;
    }

    private boolean beforeClickPermissionRat;
    private boolean afterClickPermissionRat;
    public void requestLocationPermissions(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            beforeClickPermissionRat = shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION);
            requestPermissions(Functions.LOCATION_PERMISSIONS, Variables.REQUESTCODE_LOCATION);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case ENABLE_LOCATION_CODE: {
                if (resultCode == RESULT_OK) {

                    storeLatlong();

                }
                else {
                    finishAffinity();
                }
            }
            break;
        }
    }

    // before after
// FALSE  FALSE  =  Was denied permanently, still denied permanently --> App Settings
// FALSE  TRUE   =  First time deny, not denied permanently yet --> Nothing
// TRUE   FALSE  =  Just been permanently denied --> Changing my caption to "Go to app settings to edit permissions"
// TRUE   TRUE   =  Wasn't denied permanently, still not denied permanently --> Nothing
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case Variables.REQUESTCODE_LOCATION:

                for (int i = 0, len = permissions.length; i < len; i++) {
                    String permission = permissions[i];
                    if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        // user rejected the permission

                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                            afterClickPermissionRat = shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION);
                        }
                        if ((!afterClickPermissionRat && !beforeClickPermissionRat)) {
                            // user also CHECKED "never ask again"
                            // you can either enable some fall back,
                            // disable features of your app
                            // or open another dialog explaining
                            // again the permission and directing to
                            // the app setting

//                            showUserClearAppDataDialog();

//                            openMapActivity();
                            finishAffinity();
                            break;
                        }
                        else if((afterClickPermissionRat && !beforeClickPermissionRat))
                        {

//                            if(!Functions.isAndroid11())
//                            {
//                                openMapActivity();
//                                break;
//                            }

                        }else {
                            showRationale(permission, R.string.permission_denied_contacts);
                            // user did NOT check "never ask again"
                            // context is a good place to explain the user
                            // why you need the permission and ask if he wants
                            // to accept it (the rationale)
                        }
                    }
                }

                try {

                    if(isPermissionGranted(grantResults))
                    {
                        enable_permission();
                    }
                    else {

                        if((afterClickPermissionRat && !beforeClickPermissionRat)
                                || (afterClickPermissionRat && beforeClickPermissionRat))
                        {
                            EnableGPS();
                        }
                    }
                }
                catch (ArrayIndexOutOfBoundsException e)
                {
                    e.printStackTrace();
                }

                break;
        }

    }

    public void EnableGPS() {

        if (Functions.check_location_permissions(context)) {
            if (!GpsUtils.isGPSENABLE(context)) {
                requestLocationAccess();
//                showFragment();
            } else {
                enable_permission();
            }
        } else {
//            showFragment();
            requestLocationAccess();
        }
    }

    public void requestLocationAccess() {

        if (Functions.check_location_permissions(context)) {
            enable_permission();
        } else {
            requestLocationPermissions();
        }
    }


    private void showRationale(String permission, int permission_denied_contacts) {
    }


    private boolean isPermissionGranted(int[] grantResults){
        boolean isGranted = true;

        for (int result: grantResults) {

            if(result != PackageManager.PERMISSION_GRANTED)
            {
                isGranted = false;
                break;
            }

        }

        return isGranted;
    }


    private void storeLatlong() {
        LatLng latLng = getLatLong();
        Variables.latitude = latLng.latitude;
        Variables.longitude = latLng.longitude;

        checkForBanState();
    }

    public LatLng getLatLong(){
        if(getLocationlatlong!=null)
            return getLocationlatlong.getLatlong();
        else
        {
            getLocationlatlong = new GetLocationlatlong(context);
        }

        return getLocationlatlong.getLatlong();
    }

    private void enable_permission() {

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        boolean GpsStatus = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!GpsStatus) {

            new GpsUtils(context).turnGPSOn(isGPSEnable -> {

                if(isGPSEnable)
                    enable_permission();

            });
        } else if (Functions.check_location_permissions(context)) {
//            dismissFragment();
            storeLatlong();
        }
    }

    GetLocationlatlong getLocationlatlong;
    private void initilizeLocation(){
        getLocationlatlong = new GetLocationlatlong(context);
    }

}



//login_box