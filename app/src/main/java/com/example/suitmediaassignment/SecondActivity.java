package com.example.suitmediaassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class SecondActivity extends AppCompatActivity {

    private TextView welcomeTextView, showNameTextView, selectedUserNameTextView;
    private Button chooseUserButton;

    private final BroadcastReceiver updateUserNameReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction() != null && intent.getAction().equals("updateUserName")) {
                String selectedUserName = intent.getStringExtra("selectedUserName");
                selectedUserNameTextView.setText(selectedUserName);
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        IntentFilter intentFilter = new IntentFilter("updateUserName");
        LocalBroadcastManager.getInstance(this).registerReceiver(updateUserNameReceiver, intentFilter);

        welcomeTextView = findViewById(R.id.welcomeTextView);
        showNameTextView = findViewById(R.id.showNameTextView);
        selectedUserNameTextView = findViewById(R.id.selectedUserNameTextView);
        chooseUserButton = findViewById(R.id.chooseUserButton);

        // Get the name from the First Screen
        Intent intent = getIntent();
        if (intent.hasExtra("enteredName")) {
            String enteredname = intent.getStringExtra("enteredName");
            showNameTextView.setText(enteredname);
        }

        chooseUserButton.setOnClickListener(view -> {
            // Navigate to Third Screen or perform other actions as needed
            Intent thirdIntent = new Intent(SecondActivity.this, ThirdActivity.class);
            startActivity(thirdIntent);
        });


    }
}
