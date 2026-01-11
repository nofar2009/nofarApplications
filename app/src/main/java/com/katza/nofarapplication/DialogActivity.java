package com.katza.nofarapplication;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
// ה-Imports החדשים עבור התפריט
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DialogActivity extends AppCompatActivity {

    SharedPreferences sp;
    Dialog dialog;
    EditText etUserName, etPassword;
    Button btnOpenDialog, btnCustomLogin;
    TextView tvWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dialog);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        sp = getSharedPreferences("details1", MODE_PRIVATE);

        btnOpenDialog = findViewById(R.id.btnOpenDialog);
        tvWelcome = findViewById(R.id.tvWelcome);

        String savedUser = sp.getString("username", null);
        if (savedUser != null) {
            tvWelcome.setText("שלום " + savedUser);
        }

        btnOpenDialog.setOnClickListener(v -> openLoginDialog());
    }

    private void openLoginDialog() {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_layout);
        dialog.setTitle("Login");
        dialog.setCancelable(true);

        etUserName = dialog.findViewById(R.id.etUserName);
        etPassword = dialog.findViewById(R.id.etPassword);
        btnCustomLogin = dialog.findViewById(R.id.btnCustomLogin);

        btnCustomLogin.setOnClickListener(v -> {
            String user = etUserName.getText().toString().trim();
            String pass = etPassword.getText().toString().trim();

            if (user.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "יש למלא את כל השדות", Toast.LENGTH_SHORT).show();
            } else {
                sp.edit()
                        .putString("username", user)
                        .putString("password", pass)
                        .apply();

                String savedUserFromSp = sp.getString("username", "");
                tvWelcome.setText("שלום " + savedUserFromSp);

                Toast.makeText(this, "התחברת בהצלחה", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    // --- הוספת התפריט לכאן ---

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // מחבר את קובץ ה-XML של התפריט (וודא שהשם main_menu תואם לקובץ שלך)
        getMenuInflater().inflate(R.menu.manu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // טיפול בלחיצות על פריטי התפריט
        int id = item.getItemId();

        if (id == R.id.dialog_activity) {
            Toast.makeText(this, "לחצת על דיאלוג", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,DialogActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_about) {
            Toast.makeText(this, "לחצת על אודות", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (id == R.id.MainActivity2) {
            Toast.makeText(this, "לחצת על main1", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,DialogActivity.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.MainActivity3) {
            Toast.makeText(this, "לחצת על main2", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,DialogActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}