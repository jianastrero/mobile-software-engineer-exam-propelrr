package com.jianastrero.mobilesoftwareengineerexampropelrr.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import com.jianastrero.mobilesoftwareengineerexampropelrr.binding.NonNullObservableField;

public class MainViewModel extends AndroidViewModel {

    public NonNullObservableField<String> fullName = new NonNullObservableField<>("");
    public NonNullObservableField<String> emailAddress = new NonNullObservableField<>("");
    public NonNullObservableField<String> mobileNumber = new NonNullObservableField<>("");
    public NonNullObservableField<String> gender = new NonNullObservableField<>("");
    public NonNullObservableField<String> age = new NonNullObservableField<>("");

    public String[] genders = new String[]{ "Male", "Female" };

    public MainViewModel(@NonNull Application application) {
        super(application);
    }
}
