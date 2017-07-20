package com.rn.randomchooser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        // Get the result from the previous activity
        Bundle bundle = getIntent().getExtras();
        String resultText = bundle.getString("resultText");

        // Find the result text box and set its text to the result
        TextView resultTextView = (TextView) this.findViewById(R.id.result);
        resultTextView.setText(resultText);
    }
}
