package com.katza.nofarapplication;

import android.os.Bundle;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // הפעלת Edge-to-Edge
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_main2);

        // קבלת ה-layout הראשי
        linearLayout = findViewById(R.id.main);

        // טיפול ב-insets (status bar, navigation bar)
        ViewCompat.setOnApplyWindowInsetsListener(linearLayout, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // =====================
        // 1. HorizontalScrollView עם LinearLayout אופקי
        // =====================
        HorizontalScrollView horizontalScrollView = new HorizontalScrollView(this);
        LinearLayout.LayoutParams hsParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        horizontalScrollView.setLayoutParams(hsParams);

        LinearLayout llHorizontal = new LinearLayout(this);
        llHorizontal.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams llParamsH = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        llHorizontal.setLayoutParams(llParamsH);

        horizontalScrollView.addView(llHorizontal);

        for (int i = 1; i <= 50; i++) {
            ImageView imageView = new ImageView(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(200, 200);
            layoutParams.setMargins(5, 5, 5, 5);
            imageView.setLayoutParams(layoutParams);

            int imageKey = getResources().getIdentifier("img" + (i % 3), "drawable", getPackageName());
            imageView.setImageResource(imageKey);

            llHorizontal.addView(imageView);
        }

        linearLayout.addView(horizontalScrollView);

        // =====================
        // 2. ScrollView אנכי עם LinearLayout אנכי
        // =====================
        ScrollView scrollView = new ScrollView(this);
        LinearLayout.LayoutParams svParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        scrollView.setLayoutParams(svParams);

        LinearLayout llVertical = new LinearLayout(this);
        llVertical.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams llParamsV = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        llVertical.setLayoutParams(llParamsV);

        scrollView.addView(llVertical);

        for (int i = 1; i <= 50; i++) {
            ImageView imageView = new ImageView(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 200);
            layoutParams.setMargins(5, 5, 5, 5);
            imageView.setLayoutParams(layoutParams);

            int imageKey = getResources().getIdentifier("img" + (i % 3), "drawable", getPackageName());
            imageView.setImageResource(imageKey);

            llVertical.addView(imageView);
        }

        linearLayout.addView(scrollView);
    }
}
