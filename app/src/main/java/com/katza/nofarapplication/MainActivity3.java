package com.katza.nofarapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity3 extends AppCompatActivity {

    Button  btnSave;
    SharedPreferences sp;
    EditText etFname,etLname;
    TextView tvDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();

        sp=getSharedPreferences("NOFARSPfil",MODE_PRIVATE);
        String StrFname=sp.getString("Fname",null);
        String StrLname=sp.getString("Lname",null);
        if (StrFname!=null&& StrLname !=null)
            tvDisplay.setText("welcame"+StrFname+StrLname);

    }

    private void initViews()
    {
        btnSave = findViewById(R.id.btnSubmit);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
SharedPreferences.Editor editor=sp.edit();
editor.putString("Fname",etFname.getText().toString());
editor.putString("Lname",etLname.getText().toString());
editor.commit();
            }
        });
etFname=findViewById(R.id.etFname);
etLname= findViewById(R.id.etLname);
tvDisplay=findViewById(R.id.tvDisplay);

    }


}