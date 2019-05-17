package com.tekitsolutions.remindme.Adapter;

import android.content.Context;
import android.util.AttributeSet;

public class CustomSpinnerClass extends androidx.appcompat.widget.AppCompatSpinner {

    public CustomSpinnerClass(Context context) {
        super(context);
    }

    public CustomSpinnerClass(Context context, int mode) {
        super(context, mode);
    }

    public CustomSpinnerClass(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setSelection(int position, boolean animate) {
        boolean sameSelected = position == getSelectedItemPosition();
        super.setSelection(position, animate);
        if (sameSelected) {
            //TODO:-> Spinner does not call the OnItemSelectedListener if the same item is selected, so do it manually now
            getOnItemSelectedListener().onItemSelected(this, getSelectedView(), position, getSelectedItemId());
        }
    }

    @Override
    public void setSelection(int position) {
        boolean sameSelected = position == getSelectedItemPosition();
        super.setSelection(position);
        if (sameSelected) {
            //TODO:-> Spinner does not call the OnItemSelectedListener if the same item is selected, so do it manually now
            getOnItemSelectedListener().onItemSelected(this, getSelectedView(), position, getSelectedItemId());
        }
    }
}


