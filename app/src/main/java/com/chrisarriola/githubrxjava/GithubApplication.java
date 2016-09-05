package com.chrisarriola.githubrxjava;

import android.app.Application;
import android.databinding.ViewDataBinding;

import com.manaschaudhari.android_mvvm.ViewModel;
import com.manaschaudhari.android_mvvm.adapters.ViewModelBinder;
import com.manaschaudhari.android_mvvm.utils.BindingUtils;

public class GithubApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        BindingUtils.setDefaultBinder(new ViewModelBinder() {
            @Override
            public void bind(ViewDataBinding viewDataBinding, ViewModel viewModel) {
                viewDataBinding.setVariable(com.chrisarriola.githubrxjava.BR.vm, viewModel);
            }
        });
    }
}
