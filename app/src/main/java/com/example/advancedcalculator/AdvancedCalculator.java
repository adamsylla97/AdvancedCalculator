package com.example.advancedcalculator;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.math.*;

public class AdvancedCalculator extends AppCompatActivity {

    TextView operationMemoryTextView;
    TextView resultTextView;
    StringBuilder resultMemory = new StringBuilder();
    StringBuilder operationMemory = new StringBuilder();

    private boolean isSign(String sign) {
        if (sign.equals("+") || sign.equals("-") || sign.equals("*") || sign.equals("/")) {
            return true;
        }
        return false;
    }

    private boolean isNumber(String number) {
        if (number.matches("\\d")) {
            return true;
        }
        return false;
    }

    String rej1 = "";
    String rej2 = "";
    String operator = "";

    public void setOperator(String sign) {
        operator = sign;
    }

    public void setRegisters() {

        if (rej1.length() == 0) {
            rej1 = resultMemory.toString();
        } else {
            rej2 = resultMemory.toString();
        }

        Log.i("rej1", rej1);
        Log.i("rej2", rej2);
    }

    private Double mainResult = 0.0;

    public Double addition() {
        try {

            Double result = 0.0;
            result = Double.parseDouble(rej1) + Double.parseDouble(rej2);
            rej1 = result.toString();
            return result;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public Double subtraction() {
        try {

            Double result = 0.0;
            result = Double.parseDouble(rej1) - Double.parseDouble(rej2);
            rej1 = result.toString();
            return result;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public Double multiplication() {
        try {

            Double result = 0.0;
            result = Double.parseDouble(rej1) * Double.parseDouble(rej2);
            rej1 = result.toString();
            return result;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public Double division() {
        try {

            Double result = 0.0;
            result = Double.parseDouble(rej1) / Double.parseDouble(rej2);


            if (Double.parseDouble(rej2) == 0.0) {
                result = 0.0;
                Toast.makeText(AdvancedCalculator.this, "Nie dziel przez 0!", Toast.LENGTH_LONG).show();
            }

            rej1 = result.toString();

            return result;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public void computeResult() {

        if (rej1.length() != 0 && rej2.length() != 0) {

            switch (operator) {
                case "+":
                    mainResult = addition();
                    Log.i("main result", mainResult.toString());
                    break;
                case "-":
                    mainResult = subtraction();
                    Log.i("main result", mainResult.toString());
                    break;
                case "*":
                    mainResult = multiplication();
                    Log.i("main result", mainResult.toString());
                    break;
                case "/":
                    mainResult = division();
                    Log.i("main result", mainResult.toString());
                    break;
                default:
                    Log.i("ERROR", "NO SUCH OPERATION");
            }
        }

    }

    public void clearOperationMemory() {
        operationMemory.delete(0, operationMemory.length());
        updateMemoryTextView();
    }

    public void clearResultMemory() {
        resultMemory.delete(0, resultMemory.length());
        updateResultTextView();
    }

    public void clearRegisters(View view) {
        rej1 = "";
        rej2 = "";
        mainResult = 0.0;
        operator = "";
    }

    public void equalsClicked(View view) {
        clearOperationMemory();
        setRegisters();
        computeResult();
        clearResultMemory();
        if (rej1.length() == 0 && rej2.length() == 0) {
            resultTextView.setText("");
        } else {
            updateResultTextViewWithResult();
        }
        clearRegisters(view);

    }

    public void bskpClicked(View view) {
        clearOperationMemory();
        setRegisters();
        computeResult();
        clearResultMemory();
        clearRegisters(view);
        resultTextView.setText("");
    }

    public void plusMinusClicked(View view) {

        if (resultMemory.length() != 0) {
            if (resultMemory.charAt(0) == '-') {
                resultMemory.delete(0, 1);
            } else {
                String tempResultMemory = resultMemory.toString();
                Toast.makeText(AdvancedCalculator.this, tempResultMemory.toString(), Toast.LENGTH_LONG).show();
                resultMemory.delete(0, resultMemory.length());
                resultMemory.append("-").append(tempResultMemory);
                Log.i("result memory after", resultMemory.toString());
            }
        }

        updateResultTextView();

    }

    public void dotClicked(View view) {

        if (resultMemory.length() == 0) {
            resultMemory.append("0").append(".");
        } else if (!(resultMemory.charAt(resultMemory.length() - 1) == '.')) {
            resultMemory.append(".");
        }

        updateResultTextView();

    }



    public void pressButton(View view) {
        Button pressedButton = (Button) view;
        Log.i("Button pressed:", pressedButton.getTag().toString());
        String pressedButtonTag = "";
        pressedButtonTag = pressedButton.getTag().toString();

        if (isNumber(pressedButtonTag)) {

            if (resultMemory.length() > 0) {
                if (!(pressedButtonTag.equals("0") && resultMemory.charAt(resultMemory.length() - 1) == '0' && resultMemory.length() == 1)) {
                    resultMemory.append(pressedButtonTag);
                    updateResultTextView();
                }
            } else {

                resultMemory.append(pressedButtonTag);
                updateResultTextView();

            }


        }

        if (operationMemory.length() == 0 && resultMemory.length() == 0 && isSign(pressedButtonTag)) {

            resultMemory.append("0").append(" ");

        }

        if (isSign(pressedButtonTag)) {

            setRegisters();

            computeResult();

            setOperator(pressedButtonTag);

            if (rej1.length() != 0 && rej2.length() != 0) {
                updateResultTextViewWithResult();
            }


            if (resultMemory.length() != 0) {
                operationMemory.append(resultMemory).append(" ");
                resultMemory.delete(0, resultMemory.length());
            }

            if (operationMemory.length() != 0 && !isSign(String.valueOf(operationMemory.charAt(operationMemory.length() - 2)))) {
                operationMemory.append(pressedButtonTag).append(" ");
            }

            if (operationMemory.length() != 0 && isSign(String.valueOf(operationMemory.charAt(operationMemory.length() - 2)))) {
                operationMemory.delete(operationMemory.length() - 2, operationMemory.length());
                operationMemory.append(pressedButtonTag).append(" ");
            }

        }

        updateMemoryTextView();

    }

    private void updateMemoryTextView() {
        if (operationMemory.length() < 22) {
            operationMemoryTextView.setText(operationMemory);
        } else {
            operationMemoryTextView.setText(operationMemory.substring(operationMemory.length() - 22));
        }
    }

    private void updateResultTextViewWithResult() {

        String mainResultSting = "";

        try {
            Integer mainResultInteger = Integer.parseInt(mainResult.toString());
            mainResultSting = mainResultInteger.toString();
        } catch (Exception e) {
            Log.i("ERROR", "I CAN'T PARSE TO INT");
            mainResultSting = mainResult.toString();
            if (mainResultSting.length() > 11) {
                mainResultSting = mainResultSting.substring(0, 11);
            }
        } finally {
            if (mainResultSting.length() < 11) {
                resultTextView.setText(mainResultSting);
            } else {
                resultTextView.setText(mainResultSting.substring(mainResultSting.length() - 11));
            }
        }

    }

    private void updateResultTextView() {
        if (resultMemory.length() < 11) {
            resultTextView.setText(resultMemory);
        } else {
            resultTextView.setText(resultMemory.substring(resultMemory.length() - 11));
        }
    }

    int clickCounter = 0;

    public void doubleClick(View view) {

        clickCounter++;

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (clickCounter == 1) {
                    if (resultMemory.length() > 0) {
                        resultMemory = resultMemory.delete(resultMemory.length() - 1, resultMemory.length());
                        updateResultTextView();
                    }
                } else if (clickCounter == 2) {
                    resultMemory = resultMemory.delete(0, resultMemory.length());
                    updateResultTextView();
                }
                clickCounter = 0;
            }
        }, 500);


    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_calc_main);

        operationMemoryTextView = findViewById(R.id.operationMemoryTextView);
        resultTextView = findViewById(R.id.resultTextView);
    }

}