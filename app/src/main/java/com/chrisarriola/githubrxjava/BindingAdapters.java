package com.chrisarriola.githubrxjava;

import android.databinding.BindingConversion;
import android.view.View;

import rx.subjects.PublishSubject;

@SuppressWarnings("unused")
public class BindingAdapters {

    @BindingConversion
    public static View.OnClickListener subjectToListener(final PublishSubject<Void> subject) {
        if (subject != null) {
            return new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    subject.onNext(null);
                }
            };
        } else {
            return null;
        }
    }
}
