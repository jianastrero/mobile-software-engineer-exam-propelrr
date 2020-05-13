package com.jianastrero.mobilesoftwareengineerexampropelrr.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewParent;
import android.widget.DatePicker;

public class ScrollableDatePicker extends DatePicker {
    public ScrollableDatePicker(Context context) {
        super(context);
    }

    public ScrollableDatePicker(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollableDatePicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ScrollableDatePicker(
        Context context,
        AttributeSet attrs,
        int defStyleAttr,
        int defStyleRes
    ) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev)
    {
        if (ev.getActionMasked() == MotionEvent.ACTION_DOWN)
        {
            ViewParent p = getParent();
            if (p != null)
                p.requestDisallowInterceptTouchEvent(true);
        }

        return false;
    }
}
