package com.rn.randomselector;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class EnterOptionsActivity extends AppCompatActivity {

    private List<EditText> entries = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_options);

        // Add two entries by default
        addEntry(null);
        addEntry(null);
    }

    // Called when the user wants to add a new entry to the list
    public void addEntry(View view) {
        // Create a new text box and add it to the list
        EditText newEntry = new EditText(this);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        entries.add(newEntry);

        // Add the text box to the end of the list of existing entries
        LinearLayout entriesList = (LinearLayout) this.findViewById(R.id.entriesList);
        entriesList.addView(newEntry, layoutParams);
        newEntry.requestFocus();

        // Set the minus button's state appropriately
        configureMinusButtonEnabledState();
    }

    // Called when the user wants to remove the last entry in the list
    public void removeEntry(View view) {
        // Remove the last entry in the list
        LinearLayout entriesList = (LinearLayout) this.findViewById(R.id.entriesList);
        entriesList.removeView(entries.get(entries.size() - 1));
        entries.remove(entries.size() - 1);

        // Set the minus button's enabled state appropriately
        configureMinusButtonEnabledState();
    }

    // Called when the user wants to move to the next activity
    public void generateResult(View view) {
        // Make sure every entry has some text in it
        for(EditText entry : entries) {
            String text = entry.getText().toString().trim();
            if(text.equals("")) {
                Toast.makeText(this, "Please fill out every entry!", Toast.LENGTH_LONG).show();
                return;
            }
        }

        // Pick a random result
        int resultIndex = (int) (Math.random() * entries.size());
        String resultText = entries.get(resultIndex).getText().toString();

        // Move to the results activity
        Intent intent = new Intent(this, ResultsActivity.class);
        intent.putExtra("resultText", resultText);
        startActivity(intent);
    }

    // If there is at least one entry in the list, the minus button should be enabled
    // Otherwise, it should be disabled
    private void configureMinusButtonEnabledState() {
        Button minusButton = (Button) this.findViewById(R.id.minusButton);
        minusButton.setEnabled(entries.size() > 0);
    }
}
