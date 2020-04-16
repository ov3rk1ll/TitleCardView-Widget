package dev.ov3rk1ll.demo.title_card_view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import dev.ov3rk1ll.widget.TitleCardView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TitleCardView card = findViewById(R.id.card);
    }
}
