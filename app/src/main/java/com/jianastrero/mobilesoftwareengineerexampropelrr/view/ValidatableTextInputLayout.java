package com.jianastrero.mobilesoftwareengineerexampropelrr.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Patterns;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.textfield.TextInputLayout;
import com.jianastrero.mobilesoftwareengineerexampropelrr.model.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ValidatableTextInputLayout extends TextInputLayout {

    private boolean validate = false;

    private List<Pair<Pattern, String>> regexMessagePairs = new ArrayList<>();

    private TextWatcher validationTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) { }

        @Override
        public void afterTextChanged(Editable s) {
            if (validate) {
                doValidation();
            }
        }
    };

    public ValidatableTextInputLayout(@NonNull Context context) {
        super(context);
        init();
    }

    public ValidatableTextInputLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ValidatableTextInputLayout(
        @NonNull Context context,
        @Nullable AttributeSet attrs,
        int defStyleAttr
    ) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setErrorEnabled(true);
    }

    public ValidatableTextInputLayout addValidator(String validator, String errorMessage) {
        regexMessagePairs.add(new Pair<>(Pattern.compile(validator), errorMessage));

        return this;
    }

    public ValidatableTextInputLayout validateAsEmail() {
        regexMessagePairs.add(
            new Pair<>(
                Patterns.EMAIL_ADDRESS,
                "Invalid Email"
            )
        );

        return this;
    }

    public ValidatableTextInputLayout validateAsPhone() {
        regexMessagePairs.add(
            new Pair<>(
                Pattern.compile("((09)|(\\+639))[0-9]{9}"),
                "Invalid Philippine Phone Number"
            )
        );

        return this;
    }

    public boolean validate() {
        validate = true;

        final EditText editText = getEditText();

        if (editText != null) {
            editText.removeTextChangedListener(validationTextWatcher);
            editText.addTextChangedListener(validationTextWatcher);
        }

        return doValidation();
    }

    private boolean doValidation() {

        final EditText editText = getEditText();

        if (editText != null) {

            final Editable editable = editText.getText();
            final String text;
            if (editable == null) {
                text = "";
            } else {
                text = editable.toString();
            }

            if (text.isEmpty()) {
                setError(getAllErrors());
            } else {
                for (int i = 0; i < regexMessagePairs.size(); i++) {
                    final Pair<Pattern, String> pair = regexMessagePairs.get(i);

                    if (!pair.getFirst().matcher(text).matches()) {
                        setError(pair.getSecond());
                        return false;
                    }
                }
            }
        } else {
            setError(getAllErrors());
        }

        return true;
    }

    private String getAllErrors() {
        String string = "";

        for (int i = 0; i < regexMessagePairs.size(); i++) {
            string += regexMessagePairs.get(i).getSecond() + "\n";
        }

        return string.substring(0, string.length() - 1);
    }
}
