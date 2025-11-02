package com.katza.nofarapplication;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

        btnHide = findViewById(R.id.buttonHide);
        btnHide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imageView.setVisibility(View.INVISIBLE);
            }
        });
        btnShow = findViewById(R.id.buttonShow);
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imageView.setVisibility(View.VISIBLE);  //
            }
        });
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
    public void onClick(View v) {

    }
}