package com.katza.nofarapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class Activitysecend extends AppCompatActivity {

    int calculatedAge = -1; // משתנה לשמירת הגיל המחושב

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activitysecend);

        TextView tvDisplay = findViewById(R.id.tvDisplay);
        EditText etBirthYear = findViewById(R.id.etAge);
        Button btnCalculate = findViewById(R.id.btnCalculate); // כפתור חדש לחישוב
        Button btnFinish = findViewById(R.id.btnFinish);

        // קבלת השם מהעמוד הקודם והצגת שלום
        String name = getIntent().getStringExtra("userName");
        tvDisplay.setText("שלום - " + name);

        // לחיצה על כפתור חישוב
        btnCalculate.setOnClickListener(v -> {
            String yearText = etBirthYear.getText().toString();
            if (!yearText.isEmpty()) {
                int birthYear = Integer.parseInt(yearText);
                int currentYear = Calendar.getInstance().get(Calendar.YEAR);
                calculatedAge = currentYear - birthYear;

                // עדכון התצוגה בעמוד השני כך שיציג גם את השם וגם את הגיל
                tvDisplay.setText("שלום - " + name + "\nגיל - " + calculatedAge);
            }
        });

        // כפתור פיניש שמחזיר לעמוד הראשון
        btnFinish.setOnClickListener(v -> {
            if (calculatedAge != -1) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("age", calculatedAge);
                setResult(RESULT_OK, resultIntent);
            }
            finish();
        });
    }
}