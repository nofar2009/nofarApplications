package com.katza.nofarapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class Activityfisrt extends AppCompatActivity {

    private TextView tvAgeDisplay; // TextView שיציג את הגיל שיחזור מהעמוד השני

    // הגדרת ה-Launcher שמחכה לתוצאה מהעמוד השני
    private final ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) { // בדיקה שהכל עבר בהצלחה [cite: 14, 15]
                        Intent data = result.getData();
                        if (data != null) {
                            int age = data.getIntExtra("age", 0); // קבלת הגיל המחושב [cite: 18]
                            tvAgeDisplay.setText("גיל מחושב: " + age); // עדכון הטקסט במסך הראשון [cite: 19, 53]
                        }
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activityfisrt);

        EditText etName = findViewById(R.id.etName);
        tvAgeDisplay = findViewById(R.id.tvDisplayAge); // ודאי שיש ID כזה ב-XML
        Button btnSend = findViewById(R.id.btnSend);

        btnSend.setOnClickListener(v -> {
            String name = etName.getText().toString();

            Intent intent = new Intent(Activityfisrt.this, Activitysecend.class);
            intent.putExtra("userName", name);

            // שימוש ב-launcher במקום startActivity רגיל כדי לחכות לתוצאה [cite: 29]
            launcher.launch(intent);
        });
    }
}