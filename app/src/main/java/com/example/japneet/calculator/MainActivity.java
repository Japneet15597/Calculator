package com.example.japneet.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private EditText result;
    private EditText newNumber;
    private TextView displayOperation;

    private Double operand1 = null;
    private String pendingOperation = "=";

    public static final String KEY_PENDING_OPERATION = "PendingOperation";
    public static final String KEY_OPERAND1 = "Operand1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.result);
        newNumber = findViewById(R.id.newNumber);
        displayOperation = findViewById(R.id.operation);

        Button button0 = findViewById(R.id.button0);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        Button button7 = findViewById(R.id.button7);
        Button button8 = findViewById(R.id.button8);
        Button button9 = findViewById(R.id.button9);

        Button buttonNeg = findViewById(R.id.buttonNeg);
        Button buttonDivide = findViewById(R.id.buttonDivide);
        Button buttonMultiply = findViewById(R.id.buttonMultiply);
        Button buttonDot = findViewById(R.id.buttonDot);
        Button buttonMinus = findViewById(R.id.buttonMinus);
        Button buttonEquals = findViewById(R.id.buttonEquals);
        Button buttonPlus = findViewById(R.id.buttonPlus);
        Button buttonClear = findViewById(R.id.buttonClear);

        View.OnClickListener clearListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newNumber.setText("");
                result.setText("");
                operand1 = null;
            }
        };

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                newNumber.append(b.getText().toString());
            }
        };

        View.OnClickListener negListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = newNumber.getText().toString();
                if (value.length() == 0) {
                    newNumber.setText("-");
                } else {
                    Double doubleValue = Double.valueOf(value);
                    doubleValue *= -1;
                    newNumber.setText(doubleValue.toString());//
                }
            }
        };

        buttonNeg.setOnClickListener(negListener);
        button0.setOnClickListener(listener);
        button1.setOnClickListener(listener);
        button2.setOnClickListener(listener);
        button3.setOnClickListener(listener);
        button4.setOnClickListener(listener);
        button5.setOnClickListener(listener);
        button6.setOnClickListener(listener);
        button7.setOnClickListener(listener);
        button8.setOnClickListener(listener);
        button9.setOnClickListener(listener);
        buttonDot.setOnClickListener(listener);
        buttonClear.setOnClickListener(clearListener);

        View.OnClickListener opListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                String op = b.getText().toString();
                String value = newNumber.getText().toString();

                Double doubleValue = Double.valueOf(value);
                performOperation(doubleValue, op);

                pendingOperation = op;
                displayOperation.setText(pendingOperation);
            }
        };

        buttonDivide.setOnClickListener(opListener);
        buttonMultiply.setOnClickListener(opListener);
        buttonMinus.setOnClickListener(opListener);
        buttonEquals.setOnClickListener(opListener);
        buttonPlus.setOnClickListener(opListener);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(KEY_PENDING_OPERATION, pendingOperation);
        if (operand1 != null) {
            outState.putDouble(KEY_OPERAND1, operand1);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        pendingOperation = savedInstanceState.getString(KEY_PENDING_OPERATION);
        operand1 = savedInstanceState.getDouble(KEY_OPERAND1);
        displayOperation.setText(pendingOperation);
    }

    private void performOperation(Double value, String operation) {
        if (null == operand1) {
            operand1 = value;
        } else {
            if (pendingOperation.equals("=")) {
                pendingOperation = operation;
            }
            switch (pendingOperation) {
                case "=":
                    operand1 = value;
                    break;

                case "/":
                    if (value == 0) {
                        operand1 = 0.0;
                    } else {
                        operand1 /= value;
                    }
                    break;

                case "*":
                    operand1 *= value;
                    break;

                case "-":
                    operand1 -= value;
                    break;

                case "+":
                    operand1 += value;
                    break;
            }
        }

        result.setText(operand1.toString());
        newNumber.setText("");
    }

}
