package com.jianastrero.mobilesoftwareengineerexampropelrr.binding;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

public class NonNullObservableField<T> extends ObservableField<T> {

    public NonNullObservableField(T value) {
        set(value);
    }

    @NonNull
    @Override
    public void set(T value) {
        if (value != null)
            super.set(value);
    }

    @NonNull
    @Override
    public T get() {
        final T superGet = super.get();
        if (superGet != null) {
            return superGet;
        } else {
            throw new RuntimeException("This cant be null, why is it null");
        }
    }
}
