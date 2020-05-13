package com.jianastrero.mobilesoftwareengineerexampropelrr;

import android.os.Build;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import com.jianastrero.mobilesoftwareengineerexampropelrr.databinding.ActivityMainBinding;
import com.jianastrero.mobilesoftwareengineerexampropelrr.viewmodel.MainViewModel;

import java.security.DomainCombiner;
import java.time.Month;
import java.time.Year;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainViewModel viewModel;
    private ArrayAdapter<String> genderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        genderAdapter = new ArrayAdapter<>(
            this,
            R.layout.support_simple_spinner_dropdown_item,
            viewModel.genders
        );

        binding.setViewModel(viewModel);
        binding.actGender.setAdapter(genderAdapter);

        binding.tilFullName.addValidator(
            "[a-zA-Z,.]+",
            "Full name should only be letters, comma, and period"
        );
        binding.tilEmail.validateAsEmail();
        binding.tilMobileNumber.validateAsPhone();
        binding.tilGender.validateAsRequired();
        binding.tilAge.validateAsRequired();

        Calendar today = Calendar.getInstance();
        binding.datePicker.setMaxDate(today.getTimeInMillis());
        binding.datePicker.init(
            today.get(Calendar.YEAR),
            today.get(Calendar.MONTH),
            today.get(Calendar.DAY_OF_MONTH),
            (view, year, monthOfYear, dayOfMonth) -> {
                if (binding.tilAge.getEditText() != null) {
                    Calendar selected = Calendar.getInstance();
                    selected.set(year, monthOfYear, dayOfMonth);
                    int age = today.get(Calendar.YEAR) - selected.get(Calendar.YEAR);

                    if (today.get(Calendar.DAY_OF_YEAR) < selected.get(Calendar.DAY_OF_YEAR)){
                        age--;
                    }

                    viewModel.age.set("" + age);

                    try {
                        if (Integer.parseInt(viewModel.age.get()) <= 18) {
                            binding.tilAge.setError("You must be at least 18");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        binding.tilAge.setError("You must be at least 18");
                    }
                }
            }
        );
    }

    public void submit(View view) {
        final boolean[] valid = new boolean[]{
            binding.tilFullName.validate(),
            binding.tilEmail.validate(),
            binding.tilMobileNumber.validate(),
            binding.tilGender.validate(),
            binding.tilAge.validate()
        };

        boolean isValid = true;

        for (boolean b : valid) {
            isValid = isValid && b;
        }

        try {
            if (Integer.parseInt(viewModel.age.get()) <= 18) {
                binding.tilAge.setError("You must be at least 18");
                isValid = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            binding.tilAge.setError("You must be at least 18");
            isValid = false;
        }

        if (isValid) {

            // Do Something
        }
    }
}
