package com.tekitsolutions.remindme.CardUtils;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;


/**
 * Created by Anurag on 11/08/18.
 */

public class CreditCardEditText extends androidx.appcompat.widget.AppCompatEditText implements TextWatcher {


    public Context context;

    BackButtonListener backButtonListener;

    public CreditCardEditText(Context context) {
        super(context);
        this.context = context;
    }

    public CreditCardEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public CreditCardEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    public interface BackButtonListener {
        void onBackClick();
    }
}
