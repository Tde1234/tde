package com.example.test;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnLinearLayout = findViewById(R.id.btn_linearlayout);
        Button btnTableLayout = findViewById(R.id.btn_tablelayout);
        Button btnConstraintLayout1 = findViewById(R.id.btn_constraintlayout1);
        Button btnConstraintLayout2 = findViewById(R.id.btn_constraintlayout2);

        btnLinearLayout.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, MainActivity1.class));
        });

        btnTableLayout.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, MainActivity2.class));
        });

        btnConstraintLayout1.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, MainActivity3.class));
        });

        btnConstraintLayout2.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, MainActivity4.class));
        });
    }
}