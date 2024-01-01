package com.dragon.multigame.SampleClasses;

import android.content.Context;
import android.net.ConnectivityManager;

public class Const {

    // Enter your Base URL
  public static final String MAIN = "http://143.110.186.117/letscard/";
//   public static final String MAIN = "https://goldwin.fun/";
//    public static final String MAIN = "https://maikal.co.in/";
//public static final String MAIN = "https://luckywinnerss.in/";
//    public static final String MAIN = "http://167.71.30.138/";

    public static final String IMGAE_PATH = MAIN + "data/post/";
    public static final String REDEEM_IMGAE_PATH = MAIN + "data/Redeem/";

    public static final String BSE_URL = MAIN+"api/";

    /// Rummy Game
    public  static final String get_Cash_QR_Code = MAIN+"uploads/logo/";
    public  static final String get_Add_Cash_QR = BSE_URL+"User/qr_code";
    public static final String get_table = BSE_URL+"Rummy/get_table";
    public static final String rummy_join_table = BSE_URL+"Rummy/join_table";
    public static final String PRI_RUMMY_GAME= BSE_URL+"Rummy/get_private_table";
    public static final String start_game = BSE_URL+"Rummy/start_game";
    public static final String pack_game = BSE_URL+"Rummy/pack_game";
    public static final String leave_table = BSE_URL+"Rummy/leave_table";
    public static final String my_card = BSE_URL+"Rummy/my_card";

    public static final String status = BSE_URL+"Rummy/status";
    public static final String card_value = BSE_URL+"Rummy/card_value";
    public static final String drop_card = BSE_URL+"Rummy/drop_card";
    public static final String get_card = BSE_URL+"Rummy/get_card";
    public static final String get_drop_card = BSE_URL+"Rummy/get_drop_card";
    public static final String declare = BSE_URL+"Rummy/declare";
    public static final String declare_back = BSE_URL+"Rummy/declare_back";


    public static final String share_wallet = BSE_URL+"rummy/share_wallet";
    public static final String do_share_wallet = BSE_URL+"rummy/do_share_wallet";
    public static final String join_table = BSE_URL+"Rummy/join_table";
    public static final String do_start_game = BSE_URL+"Rummy/do_start_game";
    public static final String ask_start_game = BSE_URL+"Rummy/ask_start_game";
    public static final String rejoin_game = BSE_URL+"Rummy/rejoin_game";
    public static final String rejoin_game_amount = BSE_URL+"Rummy/rejoin_game_amount";


    /// Rummy Pool Game
    public static final String Rummypoolget_table = BSE_URL+"RummyPool/get_table";
    public static final String Rummypoolstart_game = BSE_URL+"RummyPool/start_game";
    public static final String Rummypoolpack_game = BSE_URL+"RummyPool/pack_game";
    public static final String Rummypoolleave_table = BSE_URL+"RummyPool/leave_table";
    public static final String Rummypoolmy_card = BSE_URL+"RummyPool/my_card";

    public static final String Rummypoolstatus = BSE_URL+"RummyPool/status";
    public static final String Rummypoolcard_value = BSE_URL+"RummyPool/card_value";
    public static final String Rummypooldrop_card = BSE_URL+"RummyPool/drop_card";
    public static final String Rummypoolget_card = BSE_URL+"RummyPool/get_card";
    public static final String Rummypoolget_drop_card = BSE_URL+"RummyPool/get_drop_card";
    public static final String Rummypooldeclare = BSE_URL+"RummyPool/declare";
    public static final String Rummypooldeclare_back = BSE_URL+"RummyPool/declare_back";


    public static final String Rummypoolshare_wallet = BSE_URL+"RummyPool/share_wallet";
    public static final String Rummypooldo_share_wallet = BSE_URL+"RummyPool/do_share_wallet";
    public static final String Rummypooljoin_table = BSE_URL+"RummyPool/join_table";
    public static final String Rummypooldo_start_game = BSE_URL+"RummyPool/do_start_game";
    public static final String Rummypoolask_start_game = BSE_URL+"RummyPool/ask_start_game";
    public static final String Rummypoolrejoin_game = BSE_URL+"RummyPool/rejoin_game";
    public static final String Rummypoolrejoin_game_amount = BSE_URL+"RummyPool/rejoin_game_amount";
    public static final String RummypoolgetTableMaster = BSE_URL+"RummyPool/get_table_master";


    /// Rummy Deal Game
    public static final String RummyDealget_table = BSE_URL+"RummyDeal/get_table";
    public static final String RummyDealstart_game = BSE_URL+"RummyDeal/start_game";
    public static final String RummyDealpack_game = BSE_URL+"RummyDeal/pack_game";
    public static final String RummyDealleave_table = BSE_URL+"RummyDeal/leave_table";
    public static final String RummyDealmy_card = BSE_URL+"RummyDeal/my_card";

    public static final String RummyDealstatus = BSE_URL+"RummyDeal/status";
    public static final String RummyDealcard_value = BSE_URL+"RummyDeal/card_value";
    public static final String RummyDealdrop_card = BSE_URL+"RummyDeal/drop_card";
    public static final String RummyDealget_card = BSE_URL+"RummyDeal/get_card";
    public static final String RummyDealget_drop_card = BSE_URL+"RummyDeal/get_drop_card";
    public static final String RummyDealdeclare = BSE_URL+"RummyDeal/declare";
    public static final String RummyDealdeclare_back = BSE_URL+"RummyDeal/declare_back";


    public static final String RummyDealshare_wallet = BSE_URL+"RummyDeal/share_wallet";
    public static final String RummyDealdo_share_wallet = BSE_URL+"RummyDeal/do_share_wallet";
    public static final String RummyDealjoin_table = BSE_URL+"RummyDeal/join_table";
    public static final String RummyDealdo_start_game = BSE_URL+"RummyDeal/do_start_game";
    public static final String RummyDealask_start_game = BSE_URL+"RummyDeal/ask_start_game";
    public static final String RummyDealrejoin_game = BSE_URL+"RummyDeal/rejoin_game";
    public static final String RummyDealrejoin_game_amount = BSE_URL+"RummyDeal/rejoin_game_amount";
    public static final String RummyDealgetTableMaster = BSE_URL+"RummyDeal/get_table_master";


    /// Andhar Bahar Game
//    public static final String MAIN2 = "https://androappstech.com/teenpattihub/";
//    public static final String BSE_URL2 = MAIN2+"api/";
    public static final String AnderBahar = BSE_URL+"AnderBahar/get_active_game";
    public static final String PUTBET = BSE_URL+"AnderBahar/place_bet";
    public static final String CENCEL_BET = BSE_URL+"AnderBahar/cancel_bet";
    public static final String REPEAT_BET = BSE_URL+"AnderBahar/repeat_bet";
    public static final String GETROOM = BSE_URL+"AnderBahar/room";
    public static final String GETHISTORY = BSE_URL+"User/wallet_history";
    public static final String redeem = BSE_URL+"User/redeem";

    //colorpredict

    public static final String ColorPrediction = BSE_URL+"ColorPrediction/get_active_game";
    public static final String CP_PUTBET = BSE_URL+"ColorPrediction/place_bet";
    public static final String CP_CENCEL_BET = BSE_URL+"ColorPrediction/cancel_bet";
    public static final String CP_REPEAT_BET = BSE_URL+"ColorPrediction/repeat_bet";    //no use
    public static final String CP_GETROOM = BSE_URL+"ColorPrediction/room";

    // Dragon and Tiger
    public static final String DragonTigerStatus = BSE_URL+"DragonTiger/get_active_game";
    public static final String DragonTigerPUTBET = BSE_URL+"DragonTiger/place_bet";
    public static final String DragonTigerCENCEL_BET = BSE_URL+"DragonTiger/cancel_bet";
    public static final String DragonTigerREPEAT_BET = BSE_URL+"DragonTiger/repeat_bet";
    public static final String DragonTigerGETROOM = BSE_URL+"DragonTiger/room";

    // HeadTail
    public static final String HeadTailStatus = BSE_URL+"HeadTail/get_active_game";
    public static final String HeadTailPUTBET = BSE_URL+"HeadTail/place_bet";
    public static final String HeadTailCENCEL_BET = BSE_URL+"HeadTail/cancel_bet";
    public static final String HeadTailREPEAT_BET = BSE_URL+"HeadTail/repeat_bet";
    public static final String HeadTailGETROOM = BSE_URL+"HeadTail/room";

    // JackpotGames
    public static final String JackpotStatus = BSE_URL+"jackpot/get_active_game";
    public static final String JackpotPUTBET = BSE_URL+"jackpot/place_bet";
    public static final String JackpotCENCEL_BET = BSE_URL+"jackpot/cancel_bet";
    public static final String JackpotREPEAT_BET = BSE_URL+"jackpot/repeat_bet";
    public static final String JackpotGETROOM = BSE_URL+"jackpot/room";
    public static final String JackpotWinnerHistory = BSE_URL+"jackpot/jackpot_winners";
    public static final String JackpotlastWinnerHistory = BSE_URL+"jackpot/last_winners";

    // CarRouletteGames
    public static final String CarRouletteStatus = BSE_URL+"CarRoulette/get_active_game";
    public static final String CarRoulettePUTBET = BSE_URL+"CarRoulette/place_bet";
    public static final String CarRouletteCENCEL_BET = BSE_URL+"CarRoulette/cancel_bet";
    public static final String CarRouletteREPEAT_BET = BSE_URL+"CarRoulette/repeat_bet";
    public static final String CarRouletteGETROOM = BSE_URL+"CarRoulette/room";
    public static final String CarRouletteWinnerHistory = BSE_URL+"CarRoulette/CarRoulette_winners";
    public static final String CarRoulettelastWinnerHistory = BSE_URL+"CarRoulette/last_winners";

    // AnimalRouletteGames
    public static final String AnimalRouletteStatus = BSE_URL+"AnimalRoulette/get_active_game";
    public static final String AnimalRoulettePUTBET = BSE_URL+"AnimalRoulette/place_bet";
    public static final String AnimalRouletteCENCEL_BET = BSE_URL+"AnimalRoulette/cancel_bet";
    public static final String AnimalRouletteREPEAT_BET = BSE_URL+"AnimalRoulette/repeat_bet";
    public static final String AnimalRouletteGETROOM = BSE_URL+"AnimalRoulette/room";
    public static final String AnimalRouletteWinnerHistory = BSE_URL+"AnimalRoulette/CarRoulette_winners";
    public static final String AnimalRoulettelastWinnerHistory = BSE_URL+"AnimalRoulette/last_winners";

    // SevenUpGames
    public static final String SevenupStatus = BSE_URL+"SevenUp/get_active_game";
    public static final String SevenupPUTBET = BSE_URL+"SevenUp/place_bet";
    public static final String SevenupCENCEL_BET = BSE_URL+"SevenUp/cancel_bet";
    public static final String SevenupREPEAT_BET = BSE_URL+"SevenUp/repeat_bet";
    public static final String SevenupGETROOM = BSE_URL+"SevenUp/room";





    public static final String PRODUCT_OR_CATEGORY = BSE_URL+"category/list";
    public static final String APP_DATA = BSE_URL+"User/app";
    public static final String LOGIN = BSE_URL+"User/login";
    public static final String GAME_TABLE = BSE_URL+"/Game/get_table";
    public static final String CUSTOMISED_GAME_TABLE = BSE_URL+"/Game/get_customise_table";
    public static final String GAME_TABLE_JOIN= BSE_URL+"/Game/join_table";
    public static final String PRI_GAME_TABLE_CREAT= BSE_URL+"/Game/get_private_table";
    public static final String GAME_TABLE_LEAVE = BSE_URL+"/Game/leave_table";
    public static final String GAME_STATUS = BSE_URL+"/Game/status";
    public static final String GAME_PACK = BSE_URL+"/Game/pack_game";
    public static final String GAME_CHAAL = BSE_URL+"/Game/chaal";
    public static final String GAME_SHOW = BSE_URL+"/Game/show_game";
    public static final String GAME_SIDE_SHOW_CANCEL = BSE_URL+"/Game/do_slide_show";
    public static final String GAME_SIDE_SHOW = BSE_URL+"/Game/slide_show";
    public static final String GAME_SWITCH_TABLE = BSE_URL+"Game/switch_table";
    public static final String GAME_START= BSE_URL+"Game/start_game";
    public static final String SEE_CARDS= BSE_URL+"Game/see_card";
    public static final String REGISTER = BSE_URL+"User/register";
    public static final String email_login = BSE_URL+"User/email_login";
    public static final String SEND_OTP = BSE_URL+"User/send_otp";
    public static final String ONLY_SEND_OTP = BSE_URL+"User/only_send_otp";
    public static final String PROFILE = BSE_URL+"User/profile";
    public static final String GET_CHIP_PLAN = BSE_URL+"Plan";
    public static final String REMOVE_FROM_CART = BSE_URL+"order/remove_cart";
    public static final String CART_LIST = BSE_URL+"order/cart";
    public static final String ORDERS = BSE_URL+"order/my_order";
    public static final String PLCE_ORDER = BSE_URL+"plan/initiatePayment";
    public static final String PY_NOW = BSE_URL+"Plan/pay_now";
    public static final String SEARCH = BSE_URL+"category/search";
    public static final String BANNER = BSE_URL+"banner/list";
    public static final String BANNER_IMG_URL = MAIN+"data/banner/";
    public static final String MAIL_USERlISTING = BSE_URL+"User/bot";
    public static final String USER_WINNIG = BSE_URL+"User/winning_history";
    public static final String USER_UPDATE = BSE_URL+"user/update_profile";
    public static final String GAME_TIPS = BSE_URL+"Game/tip";
    public static final String GAME_CHATS = BSE_URL+"Game/chat";
    public static final String WELCOME_BONUS = BSE_URL+"User/welcome_bonus";
    public static final String COLLECT_BONUS = BSE_URL+"User/collect_welcome_bonus";
    public static final String GIFTS_LIST = BSE_URL+"Plan/gift";
    public static final String TERMS_CONDITION = BSE_URL+"user/setting";
    public static final String USER_WINNING_LIST = BSE_URL+"User/leaderboard";
    public static final String get_table_master = BSE_URL+"Game/get_table_master";

    public static final String RummygetTableMaster = BSE_URL+"rummy/get_table_master";

    public static final String USER_Redeem_History_LIST = BSE_URL+"Redeem/WithDrawal_log";
    public static final String SEND_USER_REDEEM_DATA = BSE_URL+"Redeem/Withdraw";
    public static final String GET_Redeem_List = BSE_URL+"Redeem/list";
    public static final String check_adhar = BSE_URL+"user/check_adhar";

    public static String paytm_verify_checksum = BSE_URL+"Plan/paytm_pay_now_api";
    public static String paytm_token_api = BSE_URL+"Plan/paytm_token_api";



    public static final String TOKEN = "c7d3965d49d4a59b0da80e90646aee77548458b3377ba3c0fb43d5ff91d54ea28833080e3de6ebd4fde36e2fb7175cddaf5d8d018ac1467c3d15db21c11b6909";

    public static String cashfree_token = BSE_URL + "Plan/cashfree_token_api";
    public static String cashfree_verify = BSE_URL + "Plan/cashfree_pay_now_api";

    public static String payu_token = BSE_URL + "Plan/payumoney_token_api";
    public static String payu_verify = BSE_URL + "Plan/payumoney_pay_now_api";
    public static String payu_salt = BSE_URL + "Plan/payumoney_salt";
    public static String payu_callback = BSE_URL + "Payumoney/call_back";

    public static String forgetpassword = BSE_URL + "User/forgot_password";
    public static final String login_withpassword = BSE_URL+"User/login";

    //  Chapleen have code
    public static final String create_transaction = BSE_URL+"User/create_transaction";
    public static final String transaction_status = BSE_URL+"User/transaction_status";
    public static final String addPaymentProof = BSE_URL+"User/addPaymentProof";

    public static final String payments_IMGAE_PATH = MAIN + "data/payments/";

    public static final String callback_place = BSE_URL + "Plan/Place_Order_upi";






    public static final String RUMMY_DEAL_HISTORY = BSE_URL+"User/wallet_history_rummy_deal";
    public static final String RUMMY_POOL_HISTORY = BSE_URL+"User/wallet_history_rummy_pool";
    public static final String COLOR_PREDICTION_HISTORY = BSE_URL+"User/wallet_history_color_prediction";
    public static final String CAR_ROULETTE_HISTORY = BSE_URL+"User/wallet_history_car_roulette";
    public static final String ANIMAL_ROULETTE_HISTORY = BSE_URL+"User/wallet_history_animal_roulette";
    public static final String JACKPOT_HISTORY = BSE_URL+"User/wallet_history_jackpot";
    public static final String SEVEN_UP_HISTORY = BSE_URL+"User/wallet_history_seven_up";
 public static final String HEAD_TAIL_HISTORY = BSE_URL+"User/wallet_history_head_tail";
 public static final String POKER_HISTORY = BSE_URL+"User/poker_gamelog_history";



    public static final String DRAGON_TIGER_HISTORY = BSE_URL+"User/wallet_history_dragon";
    public static final String TEENPATTI_GAMELOG_HISTORY = BSE_URL+"User/teenpatti_gamelog_history";
    public static final String RUMMY_GAMELOG_HISTORY = BSE_URL+"User/rummy_gamelog_history";

    public static final String RUMMY_USER_CARD = BSE_URL + "rummy/user_card";
    public static final String RUMMY_GET_AVAILABLE_CARDS = BSE_URL + "rummy/get_available_cards";
    public static final String RUMMY_SWAP_CARD = BSE_URL + "rummy/swap_card";

    public static final String RummyDeal_USER_CARD = BSE_URL + "RummyDeal/user_card";
    public static final String RUMMYDEAL_GET_AVAILABLE_CARDS = BSE_URL + "RummyDeal/get_available_cards";
    public static final String RUMMYDEAL_SWAP_CARD = BSE_URL + "RummyDeal/swap_card";

    public static final String Rummypool_USER_CARD = BSE_URL + "RummyPool/user_card";
    public static final String RUMMYPOOL_GET_AVAILABLE_CARDS = BSE_URL + "RummyPool/get_available_cards";
    public static final String RUMMYPOOL_SWAP_CARD = BSE_URL + "RummyPool/swap_card";


    // Poker-Const

    public static final String poker_get_table_master = BSE_URL+"poker/get_table_master";
    public static final String poker_TABLE = BSE_URL+"/poker/get_table";
    public static final String pokerCUSTOMISED_GAME_TABLE = BSE_URL+"/poker/get_customise_table";
    public static final String poker_TABLE_JOIN= BSE_URL+"/poker/join_table";
    public static final String pokerPRI_GAME_TABLE_CREAT= BSE_URL+"/poker/get_private_table";
    public static final String poker_TABLE_LEAVE = BSE_URL+"/poker/leave_table";
    public static final String poker_STATUS = BSE_URL+"/poker/status";
    public static final String poker_PACK = BSE_URL+"/poker/pack_game";
    public static final String poker_CHAAL = BSE_URL+"/poker/chaal";
    public static final String poker_SHOW = BSE_URL+"/poker/show_game";
    public static final String poker_SIDE_SHOW_CANCEL = BSE_URL+"/poker/do_slide_show";
    public static final String poker_SIDE_SHOW = BSE_URL+"/poker/slide_show";
    public static final String poker_SWITCH_TABLE = BSE_URL+"poker/switch_table";
    public static final String poker_START= BSE_URL+"poker/start_game";
    public static final String poker_SEE_CARDS= BSE_URL+"poker/see_card";
    public static final String poker_TIPS = BSE_URL+"poker/tip";
    public static final String poker_CHATS = BSE_URL+"poker/chat";

    public static boolean isNetworkAvailable(Context context) {
        if (context == null)
            return true;
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        android.net.NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }
}
