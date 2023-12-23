package com.example.suitmediaassignment;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText nameEditText, sentenceEditText;
    Button checkButton, nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEditText = findViewById(R.id.nameEditText);
        sentenceEditText = findViewById(R.id.sentenceEditText);
        checkButton = findViewById(R.id.checkButton);
        nextButton = findViewById(R.id.nextButton);

        checkButton.setOnClickListener(view -> {

            String sentence = sentenceEditText.getText().toString();

            boolean isPalindrome = checkPalindrome(sentence);

            if (isPalindrome) {
                showDialog("isPalindrome");
            } else {
                showDialog("not palindrome");
            }
        });

        findViewById(R.id.nextButton).setOnClickListener(view -> {
            String enteredName = nameEditText.getText().toString().trim();
            Log.d("MainActivity", "Entered Name: " + enteredName);  // Log for debugging
            if (!enteredName.isEmpty()) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("enteredName", enteredName);
                startActivity(intent);
            }
        });
    }

    private boolean checkPalindrome(String str) {
        // Implement palindrome checking logic here
        // For simplicity, you can reverse the string and compare
        String reversed = new StringBuilder(str).reverse().toString();
        return str.equals(reversed);
    }

    private void showDialog(String message) {
        // Implement dialog logic here
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Do nothing, or handle OK button click if needed
                    }
                });
        builder.create().show();
    }
}
