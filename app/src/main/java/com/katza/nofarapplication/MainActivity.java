package com.katza.nofarapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnHide, btnShow;
    ImageView imageView;
    int randomNumber; // מספר אקראי שהמשתמש צריך לנחש
    private Switch k;
    private SeekBar h; // הגדרת ה-SeekBar

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // הגרלת מספר אקראי בין 1 ל-100
        Random rand = new Random();
        randomNumber = rand.nextInt(100) + 1;

        initViews();
    }

    private void initViews() {
        imageView = findViewById(R.id.imageView);
        k = findViewById(R.id.k);
        h = findViewById(R.id.h); // קישור ל-SeekBar

        // קובע את רמת השקיפות (alpha) לפי מיקום ה־SeekBar
        h.setMax(100);
        h.setProgress(100);

        h.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float alphaValue = progress / 100f; // המרת הערך ל-alph בין 0 ל-1
                imageView.setAlpha(alphaValue); // עדכון השקיפות של התמונה
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, "התחלת להזיז את הבר", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, "שחררת את הבר", Toast.LENGTH_SHORT).show();
            }
        });

        k.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Toast.makeText(this, "Image visible", Toast.LENGTH_SHORT).show();
                imageView.setVisibility(View.VISIBLE);  // מציג את התמונה
            } else {
                Toast.makeText(this, "Image invisible", Toast.LENGTH_SHORT).show();
                imageView.setVisibility(View.INVISIBLE);  // מחביא את התמונה
            }
        });

        btnHide = findViewById(R.id.buttonHide);
        btnHide.setOnClickListener(v -> imageView.setVisibility(View.INVISIBLE));

        btnShow = findViewById(R.id.buttonShow);
        btnShow.setOnClickListener(v -> imageView.setVisibility(View.VISIBLE));
    }

    // פונקציות להראות ולהסתיר את התמונה
    public void imgChange(View view) {
        Toast.makeText(this, "Image visible", Toast.LENGTH_SHORT).show();
        imageView.setVisibility(View.VISIBLE);  // מציג את התמונה
    }

    public void imgChange1(View view) {
        Toast.makeText(this, "Image invisible", Toast.LENGTH_SHORT).show();
        imageView.setVisibility(View.INVISIBLE);  // מחביא את התמונה
    }

    // פונקציה לניחוש מספר
    public void num(View view) {
        EditText input = findViewById(R.id.ghj); // שדה קלט של המשתמש
        TextView text = findViewById(R.id.l);    // שדה תוצאה

        // ננסה להמיר את הקלט למספר
        try {
            int userInput = Integer.parseInt(input.getText().toString()); // המרת הקלט למספר שלם

            if (userInput == randomNumber) {
                text.setText("ניחוש נכון!");  // אם המספר נכון
            } else {
                text.setText("לא נכון, נסה שוב.");  // אם המספר לא נכון
            }
        } catch (NumberFormatException e) {
            text.setText("אנא הזן מספר תקין.");  // אם הקלט לא מספר
        }
    }

    @Override
    public void onClick(View v) {}
}
