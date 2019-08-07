package com.example.tryandroidmusicplayer.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;

import com.example.tryandroidmusicplayer.R;

/**
 * 1. input icon
 * 2. input hint
 * 3. is_password
 */
public class InputView extends FrameLayout {

    private  int inputIcon;
    private String inputHint;
    private boolean isPassword;

    private View myView;
    private ImageView myIcon;
    private EditText myInput;

    public InputView(Context context) {
        super(context);
        init(context,null);
    }

    public InputView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public InputView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public InputView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs == null) return;

//        get custom attrs
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.inputView);
        inputIcon = typedArray.getResourceId(R.styleable.inputView_input_icon, R.mipmap.welcome);
        inputHint = typedArray.getString(R.styleable.inputView_input_hint);
        isPassword = typedArray.getBoolean(R.styleable.inputView_is_password,false);
        typedArray.recycle();

        myView = LayoutInflater.from(context).inflate(R.layout.input_view, this, false);
        myIcon = myView.findViewById(R.id.iv_icon);
        myInput = myView.findViewById(R.id.et_input);

//        bind layout and attr
        myIcon.setImageResource(inputIcon);
        myInput.setHint(inputHint);
        myInput.setInputType(isPassword ? InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD : InputType.TYPE_CLASS_PHONE);

        addView(myView);
    }

    /**
     * return user input
     * @return
     */
    public String getInputStr () {
        return myInput.getText().toString().trim();
    }
}
