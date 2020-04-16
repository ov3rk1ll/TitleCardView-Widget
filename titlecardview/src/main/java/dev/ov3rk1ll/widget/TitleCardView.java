package dev.ov3rk1ll.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.cardview.widget.CardView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@SuppressWarnings("unused")
public class TitleCardView extends CardView {
    private LinearLayout contentView;
    private Button actionButton;
    private TextView titleText;
    private LinearLayout layoutTitle;

    public TitleCardView(@NonNull Context context) {
        this(context, null, 0);
    }

    public TitleCardView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleCardView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        // ?android:textColorPrimary
        TypedArray defaultTypedArray = context.obtainStyledAttributes(attrs, new int[] { R.attr.colorAccent, android.R.attr.textColorPrimaryInverse });
        int accentColor = defaultTypedArray.getColor(0, Color.BLACK);
        int textColorPrimary = defaultTypedArray.getColor(1, Color.TRANSPARENT);
        defaultTypedArray.recycle();

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleCardView, defStyleAttr, 0);

        int cardTitleBackgroundColor = typedArray.getColor(R.styleable.TitleCardView_cardTitleBackgroundColor, accentColor);

        int cardTitleTextColor = typedArray.getColor(R.styleable.TitleCardView_cardTitleTextColor, textColorPrimary);

        LayoutInflater.from(context).inflate(R.layout.title_card_view_layout, this, true);

        layoutTitle = findViewById(R.id.layoutTitle);
        layoutTitle.setBackgroundColor(cardTitleBackgroundColor);

        titleText = findViewById(R.id.textTitle);
        titleText.setText(typedArray.getString(R.styleable.TitleCardView_cardTitle));
        titleText.setTextColor(cardTitleTextColor);

        contentView = findViewById(R.id.content);

        actionButton = findViewById(R.id.buttonAction);
        String buttonText = typedArray.getString(R.styleable.TitleCardView_cardActionText);
        if(buttonText == null) {
            actionButton.setVisibility(GONE);
        } else {
            actionButton.setVisibility(VISIBLE);
            actionButton.setText(buttonText);
        }

        typedArray.recycle();

        super.setPreventCornerOverlap(false);
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        if(contentView == null){
            super.addView(child, index, params);
        } else {
            contentView.addView(child, index, params);
        }
    }

    public void setCardTitle(@StringRes int resid) {
        titleText.setText(resid);
    }

    public void setCardTitle(CharSequence text) {
        titleText.setText(text);
    }

    public void setCardTitleTextColor(@ColorInt int color) {
        titleText.setTextColor(color);
    }

    public void setCardTitleBackgroundColor(@ColorInt int color) {
        layoutTitle.setBackgroundColor(color);
    }

    public void setOnActionClickListener(@Nullable OnClickListener l) {
        if (!isClickable()) {
            setClickable(true);
        }
        actionButton.setOnClickListener(l);
    }

    @IntDef({VISIBLE, INVISIBLE, GONE})
    @Retention(RetentionPolicy.SOURCE)
    @interface Visibility {}
    public void setActionButtonVisibility(@Visibility int visibility) {
        actionButton.setVisibility(visibility);
    }

    public void setActionButtonText(@StringRes int resid) {
        actionButton.setText(resid);
        setActionButtonVisibility(resid == 0 ? GONE: VISIBLE);
    }

    public void setActionButtonText(CharSequence text) {
        actionButton.setText(text);
        setActionButtonVisibility(TextUtils.isEmpty(text) ? GONE: VISIBLE);
    }
}
