package com.dragon.multigame;

import android.content.Context;
import android.content.Intent;

import com.dragon.multigame._TeenPatti.PublicTable_New;

class IntentHelper {

    private  volatile static IntentHelper mInstance;

    public static IntentHelper getInstance() {
        if (null == mInstance) {
            // To make thread safe
            synchronized (IntentHelper.class) {
                // check again as multiple treads ca reach above step
                if (null == mInstance) {
                    mInstance = new IntentHelper();
                }
            }
        }

        return mInstance;
    }
    
    Intent TeenpattiTableList(Context context){
        return new Intent(context, PublicTable_New.class);
    }
}
