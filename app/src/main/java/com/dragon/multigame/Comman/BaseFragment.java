package com.dragon.multigame.Comman;

import androidx.fragment.app.Fragment;

import com.dragon.multigame.Activity.Homepage;


public abstract class BaseFragment extends Fragment {
    public Homepage activity;

    protected void setActivity(Homepage activity) {
        this.activity = activity;
    }

    public abstract void onBack();
}


