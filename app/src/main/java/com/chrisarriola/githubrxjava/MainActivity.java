package com.chrisarriola.githubrxjava;

import android.support.annotation.NonNull;

import com.manaschaudhari.android_mvvm.MvvmActivity;
import com.manaschaudhari.android_mvvm.ViewModel;

public class MainActivity extends MvvmActivity {

    @NonNull
    @Override
    public ViewModel createViewModel() {
        return new MainViewModel();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }
}
