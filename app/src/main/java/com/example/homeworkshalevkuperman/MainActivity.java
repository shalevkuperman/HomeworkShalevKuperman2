package com.example.homeworkshalevkuperman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText num1Edit, num2Edit;
    TextView textView2;
    double result;
    Button button;
    Spinner spinner;
    String userChoice;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        num1Edit = findViewById(R.id.num1EditText);
        num2Edit = findViewById(R.id.num2EditText);
        textView2 = findViewById(R.id.textView2);
        spinner = findViewById(R.id.operationSpinner);

        button = findViewById(R.id.calculateButton);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter
                .createFromResource
                        (this,
                                R.array.Operator_array,
                                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                userChoice = (String) parent.getItemAtPosition(position);
                Toast.makeText(context, userChoice, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double num1=Integer.parseInt(num1Edit.getText().toString());
                double num2=Integer.parseInt(num2Edit.getText().toString());
                try{
                    switch (userChoice) {
                        case "+":
                            result = num1 + num2;
                            break;
                        case "-":
                            result = num1 - num2;
                            break;
                        case "*":
                            result = num1 * num2;
                            break;
                        case "/":
                            if (num2 != 0) {
                                result = num1 / num2;
                            } else {
                                throw new ArithmeticException("Division by zero");
                            }
                            break;
                        case "^":
                            result = (int) Math.pow(num1, num2);
                            break;
                    }
                    textView2.setText(String.format("%.2f", (double) result));
                }
                catch(Exception e){
                    Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}