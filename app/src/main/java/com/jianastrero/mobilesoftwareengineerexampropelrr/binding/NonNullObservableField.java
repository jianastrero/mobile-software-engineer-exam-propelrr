package com.jianastrero.mobilesoftwareengineerexampropelrr.binding;

import androidx.databinding.ObservableField;

public class NonNullObservableField<T> extends ObservableField<T> {

    public NonNullObservableField(T value) {
        set(value);
    }

    @Override
    public void set(T value) {
        if (value != null)
            super.set(value);
    }

    @Override
    public T get() {
        if (super.get() != null) {
            return super.get();
        } else {
            throw new RuntimeException("This cant be null, why is it null");
        }
    }
}
