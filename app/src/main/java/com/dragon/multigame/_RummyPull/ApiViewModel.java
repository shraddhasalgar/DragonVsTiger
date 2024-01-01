package com.dragon.multigame._RummyPull;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.dragon.multigame.Interface.ApiRequest;
import com.dragon.multigame.Interface.Callback;
import com.dragon.multigame.SampleClasses.Const;
import com.dragon.multigame.Utils.Functions;
import com.dragon.multigame.Utils.SharePref;
import com.dragon.multigame.model.TableModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class ApiViewModel {

    Context context;
    Callback callback;
    String Pref_cards = "cards_";


    private  volatile static ApiViewModel mInstance;

    public static ApiViewModel getInstance() {
        if (null == mInstance) {
            // To make thread safe
            synchronized (ApiViewModel.class) {
                // check again as multiple treads ca reach above step
                if (null == mInstance) {
                    mInstance = new ApiViewModel();
                }
            }
        }

        return mInstance;
    }

    /**
     * initialization of context, use only first time later it will use this again and again
     *
     * @param context app context: first time
     */
    public ApiViewModel init(Context context) {
        try {

            if (context != null) {
                this.context = context;
            }

        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }

        return mInstance;
    }

    public void setCallback(Callback callback)
    {
        this.callback = callback;
    }

    public void CALL_API_get_table(TableModel tableModel, Callback callback) {

        HashMap params = new HashMap();
        params.put("user_id",""+ SharePref.getU_id());
        params.put("token",""+SharePref.getAuthorization());

        if(tableModel != null)
        {
            params.put("invitation_code",""+tableModel.getInvitation_code());
            if(Functions.isStringValid(tableModel.getPassword()))
                params.put("password",tableModel.getPassword());

        }

        ApiRequest.Call_Api(context, Const.Rummypooljoin_table, params, new Callback() {
            @Override
            public void Responce(String resp, String type, Bundle bundle) {

                try {

                    JSONObject jsonObject = new JSONObject(resp);
                    String code = jsonObject.getString("code");
                    String message = jsonObject.getString("message");

                    if(code.equalsIgnoreCase("200"))
                    {
                       String table_id = jsonObject
                                .getJSONArray("table_data")
                                .getJSONObject(0)
                                .optString("table_id");

                        callback.Responce(table_id,"",null);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    public void CALL_API_start_game(Callback callback) {

        HashMap params = new HashMap();
        params.put("user_id",""+ SharePref.getU_id());
        params.put("token",""+SharePref.getAuthorization());

        ApiRequest.Call_Api(context, Const.Rummypoolstart_game, params, new Callback() {
            @Override
            public void Responce(String resp, String type, Bundle bundle) {
                Log.v("start_game",resp);
                try {
                    JSONObject jsonObject = new JSONObject(resp);
                    String code = jsonObject.getString("code");
                    String message = jsonObject.getString("message");

                    if(code.equalsIgnoreCase("200"))
                    {
                       String game_id = jsonObject.optString("game_id");

                       callback.Responce(game_id,"",null);
                    }
                    else {
                        Toast.makeText(context, ""+message, Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });



    }

    public void CALL_API_ASK_START_GAME(){

        HashMap params = new HashMap();
        params.put("user_id",""+SharePref.getU_id());
        params.put("token",""+SharePref.getAuthorization());

        ApiRequest.Call_Api(context, Const.Rummypoolask_start_game, params, new Callback() {
            @Override
            public void Responce(String resp, String type, Bundle bundle) {

                if(resp != null)
                {

                    try {
                        JSONObject jsonObject = new JSONObject(resp);
                        String code = jsonObject.optString("code");
                        String message = jsonObject.optString("message");

                        if(code.equals("200"))
                        {
                            Functions.showToast(context,""+message);
                        }
                        else {
                            Functions.showToast(context,""+message);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }

            }
        });

    }

    public void CALL_API_DO_START_GAME(String start_game_id,int type,Callback callback){

        HashMap params = new HashMap();
        params.put("user_id",""+SharePref.getU_id());
        params.put("token",""+SharePref.getAuthorization());
        params.put("start_game_id",""+start_game_id);

        // type = 1 accept
        // type = 2 reject
        params.put("type",""+type);

        ApiRequest.Call_Api(context, Const.Rummypooldo_start_game, params, new Callback() {
            @Override
            public void Responce(String resp, String type, Bundle bundle) {

                if(resp != null)
                {

                    try {
                        JSONObject jsonObject = new JSONObject(resp);
                        String code = jsonObject.optString("code");
                        String message = jsonObject.optString("message");

                        if(code.equals("200"))
                        {
                            Functions.showToast(context,""+message);
                            if(callback != null)
                                callback.Responce("success","",null);
                        }
                        else {
                            Functions.showToast(context,""+message);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }

            }
        });

    }


    public void CALL_API_getCardList(String game_id,Callback callback) {

        HashMap params = new HashMap();
        params.put("game_id",""+game_id);
        params.put("user_id",""+ SharePref.getU_id());
        params.put("token",""+SharePref.getAuthorization());

        ApiRequest.Call_Api(context, Const.Rummypoolmy_card, params, new Callback() {
            @Override
            public void Responce(String resp, String type, Bundle bundle) {

                try {
                    JSONObject jsonObject = new JSONObject(resp);

                    if(callback != null)
                        callback.Responce(resp,"",null);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    public void CALL_API_status(String game_id,String table_id,Callback callback) {


        HashMap params = new HashMap();
        params.put("user_id",""+ SharePref.getU_id());
        params.put("token",""+SharePref.getAuthorization());
        params.put("game_id",""+game_id);
        if(Functions.checkisStringValid(table_id))
            params.put("table_id",""+table_id);

        ApiRequest.Call_Api(context, Const.Rummypoolstatus, params, new Callback() {
            @Override
            public void Responce(String resp, String type, Bundle bundle) {

                    callback.Responce(resp,"",null);
            }
        });

    }

    public void CALL_API_get_drop_card(String Submitcardslist,Callback callback) {

        HashMap params = new HashMap();
        params.put("user_id",""+ SharePref.getU_id());
        params.put("token",""+SharePref.getAuthorization());

        params.put("json",""+Submitcardslist);


        ApiRequest.Call_Api(context, Const.Rummypoolget_drop_card, params, new Callback() {
            @Override
            public void Responce(String resp, String type, Bundle bundle) {

                callback.Responce(resp,null,null);
            }
        });

    }

    public void CALL_API_rejoin_game_amount(Callback callback) {

        HashMap params = new HashMap();
        params.put("user_id",""+ SharePref.getU_id());
        params.put("token",""+SharePref.getAuthorization());


        ApiRequest.Call_Api(context, Const.Rummypoolrejoin_game_amount, params, new Callback() {
            @Override
            public void Responce(String resp, String type, Bundle bundle) {

                callback.Responce(resp,null,null);
            }
        });



    }

    public void CALL_API_renew_game() {

        HashMap params = new HashMap();
        params.put("user_id",""+ SharePref.getU_id());
        params.put("token",""+SharePref.getAuthorization());


        ApiRequest.Call_Api(context, Const.Rummypoolrejoin_game, params, new Callback() {
            @Override
            public void Responce(String resp, String type, Bundle bundle) {

                Log.v("pack_game",resp);

                try {
                    JSONObject jsonObject = new JSONObject(resp);
                    String code = jsonObject.getString("code");
                    String message = jsonObject.getString("message");

                    if(code.equalsIgnoreCase("200"))
                    {
                        Toast.makeText(context, ""+message, Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(context, ""+message, Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });



    }

    public void CALL_API_leave_table(String Submitcardslist) {

        HashMap params = new HashMap();
        params.put("user_id",""+ SharePref.getU_id());
        params.put("token",""+SharePref.getAuthorization());

        params.put("json",""+Submitcardslist);


        ApiRequest.Call_Api(context, Const.Rummypoolleave_table, params, new Callback() {
            @Override
            public void Responce(String resp, String type, Bundle bundle) {

                Log.v("pack_game",resp);

                try {
                    JSONObject jsonObject = new JSONObject(resp);
                    String code = jsonObject.getString("code");
                    String message = jsonObject.getString("message");

                    if(code.equalsIgnoreCase("200"))
                    {

                        //                        Toast.makeText(MainActivity.this, ""+message, Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(context, ""+message, Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });



    }

    public void CALL_API_pack_game(String Submitcardslist,Callback callback) {

        HashMap params = new HashMap();
        params.put("user_id",""+ SharePref.getU_id());
        params.put("token",""+SharePref.getAuthorization());

        params.put("json",""+Submitcardslist);


        ApiRequest.Call_Api(context, Const.Rummypoolpack_game, params, new Callback() {
            @Override
            public void Responce(String resp, String type, Bundle bundle) {

                callback.Responce(resp,null,null);
            }
        });



    }



}
