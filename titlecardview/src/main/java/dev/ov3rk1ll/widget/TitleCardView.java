package dev.ov3rk1ll.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

public class TitleCardView extends CardView {
    private Button mActionButton;
    private LinearLayout mContentView;

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

        LinearLayout layoutTitle = findViewById(R.id.layoutTitle);
        layoutTitle.setBackgroundColor(cardTitleBackgroundColor);

        TextView titleText = findViewById(R.id.textTitle);
        titleText.setText(typedArray.getString(R.styleable.TitleCardView_cardTitle));
        titleText.setTextColor(cardTitleTextColor);

        mContentView = findViewById(R.id.content);

        mActionButton = findViewById(R.id.buttonAction);
        String buttonText = typedArray.getString(R.styleable.TitleCardView_cardActionText);
        if(buttonText == null) {
            mActionButton.setVisibility(View.GONE);
        } else {
            mActionButton.setVisibility(View.VISIBLE);
            mActionButton.setText(buttonText);
        }

        typedArray.recycle();

        super.setPreventCornerOverlap(false);
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        if(mContentView == null){
            super.addView(child, index, params);
        } else {
            //Forward these calls to the content view
            mContentView.addView(child, index, params);
        }
    }

    public void setOnActionClickListener(@Nullable OnClickListener l) {
        if (!isClickable()) {
            setClickable(true);
        }
        mActionButton.setOnClickListener(l);
    }
}
