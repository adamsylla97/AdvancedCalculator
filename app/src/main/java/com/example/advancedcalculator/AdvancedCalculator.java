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
        if (sign.equals("+") || sign.equals("-") || sign.equals("*") || sign.equals("/") || sign.equals("^")) {
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

    private boolean isOperation(String sign){
        if(sign.equals("x^2") || sign.equals("sqrt") || sign.equals("sin") || sign.equals("cos") || sign.equals("tan") || sign.equals("log") || sign.equals("ln")){
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

    public Double power() {
        try {

            Double result = 0.0;


            result = Math.pow(Double.parseDouble(rej1),Double.parseDouble(rej2));
            rej1 = result.toString();
            Toast.makeText(AdvancedCalculator.this,result.toString(),Toast.LENGTH_LONG).show();
            Log.i("POWER rej1",rej1);
            Log.i("POWER rej2",rej2);
            Log.i("POWER result",result.toString());
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
                case "^":
                    mainResult = power();
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
        operationMemoryToClear = 0;
        isOperationLast = false;
        updateAfterOperation = true;
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

    int operationMemoryToClear = 0;
    Double resultOfOperation = 0.0;

    public void powerToTwo(){

        Double temp = 0.0;

        Log.i("rej1", rej1);
        Log.i("rej2",rej2);

        if(resultMemory.length() == 0){
            temp = 0.0;
        } else {
            temp = Double.valueOf(resultMemory.toString());
        }

        Log.i("temp",temp.toString());

        Double result = Math.pow(temp,2);

        Log.i("result",result.toString());
        if(rej1.length() == 0){
            rej2 = result.toString();
            resultMemory.delete(0,resultMemory.length());
            resultMemory.append(rej1);
        } else {
            rej2 = result.toString();
            resultMemory.delete(0,resultMemory.length());
            resultMemory.append(rej2);
        }

        operationMemory.append(" ").append("sqr(").append(temp.toString()).append(")").append(" ");
        operationMemoryToClear = 7+String.valueOf(temp).length();

        resultOfOperation = result;
        mainResult = result;

        Log.i("af rej1", rej1);
        Log.i("af rej2",rej2);

        updateAfterOperation = false;

        updateResultTextView();

    }

    public void computeSqrt(){

        Double temp = 0.0;

        Log.i("rej1", rej1);
        Log.i("rej2",rej2);

        if(resultMemory.length() == 0){
            temp = 0.0;
        } else {
            temp = Double.valueOf(resultMemory.toString());
        }

        Log.i("temp",temp.toString());

        Double result = Math.sqrt(temp);

        Log.i("result",result.toString());
        if(rej1.length() == 0){
            rej1 = result.toString();
            resultMemory.delete(0,resultMemory.length());
            resultMemory.append(rej1);
        } else {
            rej2 = result.toString();
            resultMemory.delete(0,resultMemory.length());
            resultMemory.append(rej2);
        }

        operationMemory.append(" ").append("sqrt(").append(temp.toString()).append(")").append(" ");
        operationMemoryToClear = 8+String.valueOf(temp).length();

        resultOfOperation = result;
        mainResult = result;

        Log.i("af rej1", rej1);
        Log.i("af rej2",rej2);

        updateAfterOperation = false;

        updateResultTextView();

    }

    public void computeSin(){

        Double temp = 0.0;

        Log.i("rej1", rej1);
        Log.i("rej2",rej2);

        if(resultMemory.length() == 0){
            temp = 0.0;
        } else {
            temp = Double.valueOf(resultMemory.toString());
        }

        Log.i("temp",temp.toString());

        Double result = Math.sin(Math.toRadians(temp));

        Log.i("result",result.toString());
        if(rej1.length() == 0){
            rej1 = result.toString();
            resultMemory.delete(0,resultMemory.length());
            resultMemory.append(rej1);
        } else {
            rej2 = result.toString();
            resultMemory.delete(0,resultMemory.length());
            resultMemory.append(rej2);
        }

        operationMemory.append(" ").append("sin(").append(temp.toString()).append(")").append(" ");
        operationMemoryToClear = 7+String.valueOf(temp).length();

        resultOfOperation = result;
        mainResult = result;

        Log.i("af rej1", rej1);
        Log.i("af rej2",rej2);

        updateAfterOperation = false;

        updateResultTextView();

    }

    public void computeCos(){

        Double temp = 0.0;

        Log.i("rej1", rej1);
        Log.i("rej2",rej2);

        if(resultMemory.length() == 0){
            temp = 0.0;
        } else {
            temp = Double.valueOf(resultMemory.toString());
        }

        Log.i("temp",temp.toString());

        Double result = Math.cos(Math.toRadians(temp));

        Log.i("result",result.toString());
        if(rej1.length() == 0){
            rej1 = result.toString();
            resultMemory.delete(0,resultMemory.length());
            resultMemory.append(rej1);
        } else {
            rej2 = result.toString();
            resultMemory.delete(0,resultMemory.length());
            resultMemory.append(rej2);
        }

        operationMemory.append(" ").append("cos(").append(temp.toString()).append(")").append(" ");
        operationMemoryToClear = 7+String.valueOf(temp).length();

        resultOfOperation = result;
        mainResult = result;

        Log.i("af rej1", rej1);
        Log.i("af rej2",rej2);

        updateAfterOperation = false;

        updateResultTextView();

    }

    public void computeCTan(){

        Double temp = 0.0;

        Log.i("rej1", rej1);
        Log.i("rej2",rej2);

        if(resultMemory.length() == 0){
            temp = 0.0;
        } else {
            temp = Double.valueOf(resultMemory.toString());
        }

        Log.i("temp",temp.toString());

        Double result = Math.tan(Math.toRadians(temp));

        Log.i("result",result.toString());
        if(rej1.length() == 0){
            rej1 = result.toString();
            resultMemory.delete(0,resultMemory.length());
            resultMemory.append(rej1);
        } else {
            rej2 = result.toString();
            resultMemory.delete(0,resultMemory.length());
            resultMemory.append(rej2);
        }

        operationMemory.append(" ").append("tan(").append(temp.toString()).append(")").append(" ");
        operationMemoryToClear = 7+String.valueOf(temp).length();

        resultOfOperation = result;
        mainResult = result;

        Log.i("af rej1", rej1);
        Log.i("af rej2",rej2);

        updateAfterOperation = false;

        updateResultTextView();

    }

    public void computeLog(){

        Double temp = 0.0;

        Log.i("rej1", rej1);
        Log.i("rej2",rej2);

        if(resultMemory.length() == 0){
            temp = 0.0;
        } else {
            temp = Double.valueOf(resultMemory.toString());
        }

        Log.i("temp",temp.toString());

        Double result = 0.0;
        Boolean noError = true;

        if(temp == 0.0){
            Toast.makeText(AdvancedCalculator.this,"Zakazane dzialanie (Log(0))",Toast.LENGTH_LONG).show();
            noError = false;
        } else{
            result = Math.log10(temp);
        }

        Log.i("result",result.toString());
        if(rej1.length() == 0){
            rej1 = result.toString();
            resultMemory.delete(0,resultMemory.length());
            resultMemory.append(rej1);
        } else {
            rej2 = result.toString();
            resultMemory.delete(0,resultMemory.length());
            resultMemory.append(rej2);
        }

        if(noError){
            operationMemory.append(" ").append("ln(").append(temp.toString()).append(")").append(" ");
            operationMemoryToClear = 6+String.valueOf(temp).length();
        }

        resultOfOperation = result;
        mainResult = result;

        Log.i("af rej1", rej1);
        Log.i("af rej2",rej2);

        updateAfterOperation = false;

        updateResultTextView();

    }

    public void computeLn(){

        Double temp = 0.0;

        Log.i("rej1", rej1);
        Log.i("rej2",rej2);

        if(resultMemory.length() == 0){
            temp = 0.0;
        } else {
            temp = Double.valueOf(resultMemory.toString());
        }

        Log.i("temp",temp.toString());

        Double result = 0.0;
        Boolean noError = true;

        if(temp == 0.0){
            Toast.makeText(AdvancedCalculator.this,"Zakazane dzialanie (Ln(0))",Toast.LENGTH_LONG).show();
            noError = false;
        } else{
            result = Math.log10(temp);
        }

        Log.i("result",result.toString());
        if(rej1.length() == 0){
            rej1 = result.toString();
            resultMemory.delete(0,resultMemory.length());
            resultMemory.append(rej1);
        } else {
            rej2 = result.toString();
            resultMemory.delete(0,resultMemory.length());
            resultMemory.append(rej2);
        }

        if(noError){
            operationMemory.append(" ").append("ln(").append(temp.toString()).append(")").append(" ");
            operationMemoryToClear = 6+String.valueOf(temp).length();
        }

        resultOfOperation = result;
        mainResult = result;

        Log.i("af rej1", rej1);
        Log.i("af rej2",rej2);

        updateAfterOperation = false;

        updateResultTextView();

    }

    boolean updateAfterOperation = true;

    public void advancedButtonClicked(String pressedButtonText) {

        switch (pressedButtonText){

            case "x^2":
                powerToTwo();
                break;
            case "sqrt":
                computeSqrt();
                break;
            case "sin":
                computeSin();
                break;
            case "cos":
                computeCos();
                break;
            case "tan":
                computeCTan();
                break;
            case "log":
                computeLog();
                break;
            case "ln":
                computeLn();
                break;
            default:
                Log.i("ERROR","NO SUCH OPTION");

        }

    }

    public boolean isOperationLast = false;

    public void pressButton(View view) {
        Button pressedButton = (Button) view;
        Log.i("Button pressed:", pressedButton.getTag().toString());
        String pressedButtonTag = "";
        pressedButtonTag = pressedButton.getTag().toString();

        if (isNumber(pressedButtonTag)) {

            if(isOperationLast){
                resultMemory.delete(0,resultMemory.length());
                if(operationMemory.length() > operationMemoryToClear){
                    operationMemory.delete(operationMemory.length()-operationMemoryToClear,operationMemory.length());
                }
                operationMemoryToClear = 0;
                isOperationLast = false;
            }


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

        if(isOperation(pressedButton.getText().toString())){

            isOperationLast = true;

            setRegisters();

            advancedButtonClicked(pressedButton.getText().toString());

        }

        if (operationMemory.length() == 0 && resultMemory.length() == 0 && isSign(pressedButtonTag)) {

            resultMemory.append("0").append(" ");

        }

        if (isSign(pressedButtonTag)) {

            isOperationLast = false;
            updateAfterOperation = true;

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

    private void updateMemoryTextView(String operationMemory) {
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
            if(mainResult == 0 && rej1.length()!=0){
                mainResult = Double.valueOf(rej1);
            }
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
            resultTextView.setText(resultMemory.substring(0,12));
        }
    }

    private void updateResultTextView(String resultMemory) {
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

    @Override
    protected void onSaveInstanceState(Bundle outState){

        outState.putString("resultMemory",resultMemory.toString());
        outState.putString("operationMemory",operationMemory.toString());
        outState.putString("rej1",rej1);
        outState.putString("rej2",rej2);
        outState.putDouble("result",mainResult);
        outState.putString("operator",operator);
        outState.putBoolean("isOperationLast",isOperationLast);
        outState.putBoolean("updateAfterOperation",updateAfterOperation);

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);

        String result = savedInstanceState.getString("resultMemory");
        String operation = savedInstanceState.getString("operationMemory");

        String rej1Temp = savedInstanceState.getString("rej1");
        String rej2Temp = savedInstanceState.getString("rej2");
        Double mainResultTemp = savedInstanceState.getDouble("result");
        String operatorTemp = savedInstanceState.getString("operator");

        if(rej1Temp != null) {
            rej1 = rej1Temp;
        }

        if(rej2Temp != null){
            rej2 = rej2Temp;
        }

        if(mainResultTemp != null){
            mainResult = mainResultTemp;
        }

        if(operatorTemp != null){
            operator = operatorTemp;

        }

        if(result != null){
            updateResultTextView(result);
            resultMemory.append(result);
        }

        if (operation != null){
            updateMemoryTextView(operation);
            operationMemory.append(result);
        }

    }

}