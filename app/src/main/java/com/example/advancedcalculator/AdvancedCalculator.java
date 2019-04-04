package com.example.advancedcalculator;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

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
        if(sign.equals("x^2") || sign.equals("sqrt") || sign.equals("sin") || sign.equals("cos") || sign.equals("tan") || sign.equals("log") || sign.equals("ln") || sign.equals("%")){
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

        isOperationLast = false;
        lastOperationLength = 0;

        if(resultMemory.length() > 0){
            operationMemory.append(resultMemory);
        }


        if (operationMemory.length() > 0) {
            computeFinalResult(operationMemory.toString());
            updateResultTextView();
            resultMemory.delete(0, resultMemory.length());
        }

        if(resultMemory.length()> 0){
            operationMemory.append(resultMemory).append(" ");
        }

        clearOperationMemory();
        updateMemoryTextView();
        //clearResultMemory();
        //clearRegisters(view);
        //clearOperationMemory();

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
        } else if (!resultMemory.toString().contains(".")){
            resultMemory.append(".");
        }

        updateResultTextView();

    }

    int operationMemoryToClear = 0;
    Double resultOfOperation = 0.0;
    Boolean operationLast = false;
    int lastOperationLength = 0;

    public void powerToTwo(View view){

        Double temp = 0.0;

        if(resultMemory.length() == 0){
            temp = 0.0;
        } else {
            temp = Double.valueOf(resultMemory.toString());
        }

        if(operationLast){
            operationMemory.delete(operationMemory.length()-lastOperationLength,operationMemory.length());
        }

        resultMemory.delete(0,resultMemory.length());
        //resultMemory.append("sqr(").append(temp.toString()).append(")");
        operationMemory.append("sqr(").append(temp.toString()).append(")").append(" ");

        operationLast = true;
        lastOperationLength = 6 + temp.toString().length();

        computeFinalResult(operationMemory.toString());

        updateResultTextView();
        resultMemory.delete(0,resultMemory.length());
        updateMemoryTextView();
    }

    public void computeSqrt(View view){

        Double temp = 0.0;

        if(resultMemory.length() == 0){
            temp = 0.0;
        } else {
            temp = Double.valueOf(resultMemory.toString());
        }

        if(operationLast){
            operationMemory.delete(operationMemory.length()-lastOperationLength,operationMemory.length());
        }

        resultMemory.delete(0,resultMemory.length());
        //resultMemory.append("sqrt(").append(temp.toString()).append(")");
        operationMemory.append("sqrt(").append(temp.toString()).append(")").append(" ");

        operationLast = true;
        lastOperationLength = 7 + temp.toString().length();
        computeFinalResult(operationMemory.toString());


        updateResultTextView();
        resultMemory.delete(0,resultMemory.length());
        updateMemoryTextView();
    }

    public void computeSin(View view){

        Double temp = 0.0;

        if(resultMemory.length() == 0){
            temp = 0.0;
        } else {
            temp = Double.valueOf(resultMemory.toString());
        }

        if(operationLast){
            operationMemory.delete(operationMemory.length()-lastOperationLength,operationMemory.length());
        }

        resultMemory.delete(0,resultMemory.length());
        //resultMemory.append("sin(").append(temp.toString()).append(")");
        operationMemory.append("sin(").append(temp.toString()).append(")").append(" ");

        operationLast = true;
        lastOperationLength = 6 + temp.toString().length();

        computeFinalResult(operationMemory.toString());

        updateResultTextView();
        resultMemory.delete(0,resultMemory.length());
        updateMemoryTextView();

    }

    public void computeCos(View view){

        Double temp = 0.0;

        if(resultMemory.length() == 0){
            temp = 0.0;
        } else {
            temp = Double.valueOf(resultMemory.toString());
        }

        if(operationLast){
            operationMemory.delete(operationMemory.length()-lastOperationLength,operationMemory.length());
        }

        resultMemory.delete(0,resultMemory.length());
        //resultMemory.append("cos(").append(temp.toString()).append(")");
        operationMemory.append("cos(").append(temp.toString()).append(")").append(" ");

        operationLast = true;
        lastOperationLength = 6 + temp.toString().length();

        computeFinalResult(operationMemory.toString());

        updateResultTextView();
        resultMemory.delete(0,resultMemory.length());
        updateMemoryTextView();

    }

    public void computeCTan(View view){
        Double temp = 0.0;

        if(resultMemory.length() == 0){
            temp = 0.0;
        } else {
            temp = Double.valueOf(resultMemory.toString());
        }

        if(operationLast){
            operationMemory.delete(operationMemory.length()-lastOperationLength,operationMemory.length());
        }

        resultMemory.delete(0,resultMemory.length());
        //resultMemory.append("tan(").append(temp.toString()).append(")");
        operationMemory.append("tan(").append(temp.toString()).append(")").append(" ");

        operationLast = true;
        lastOperationLength = 6 + temp.toString().length();

        computeFinalResult(operationMemory.toString());

        updateResultTextView();
        resultMemory.delete(0,resultMemory.length());
        updateMemoryTextView();
    }

    public void computeLog(View view){

        Double temp = 0.0;

        if(resultMemory.length() == 0){
            temp = 0.0;
        } else {
            temp = Double.valueOf(resultMemory.toString());
        }

        if(operationLast){
            operationMemory.delete(operationMemory.length()-lastOperationLength,operationMemory.length());
        }

        if(temp == 0.0 || temp < 0){
            Toast.makeText(AdvancedCalculator.this,"ZAKAZANA AKCJA log(" + temp + ")",Toast.LENGTH_LONG).show();
            resultMemory.delete(0,resultMemory.length());

            operationLast = false;
            lastOperationLength = 0;

            clearOperationMemory();
            updateResultTextView();
        } else {
            resultMemory.delete(0,resultMemory.length());
            //resultMemory.append("log(").append(temp.toString()).append(")");
            operationMemory.append("log(").append(temp.toString()).append(")").append(" ");

            operationLast = true;
            lastOperationLength = 6 + temp.toString().length();

            computeFinalResult(operationMemory.toString());

            updateResultTextView();

            resultMemory.delete(0,resultMemory.length());

            updateMemoryTextView();
        }



    }

    public void computeLn(View view){

        Double temp = 0.0;

        if(resultMemory.length() == 0){
            temp = 0.0;
        } else {
            temp = Double.valueOf(resultMemory.toString());
        }

        if(operationLast){
            operationMemory.delete(operationMemory.length()-lastOperationLength,operationMemory.length());
        }

        if(temp == 0.0 || temp < 0){
            Toast.makeText(AdvancedCalculator.this,"ZAKAZANA AKCJA ln(" + temp + ")",Toast.LENGTH_LONG).show();
            resultMemory.delete(0,resultMemory.length());

            operationLast = false;
            lastOperationLength = 0;

            clearOperationMemory();
            updateResultTextView();
        } else {
            resultMemory.delete(0,resultMemory.length());
            //resultMemory.append("ln(").append(temp.toString()).append(")");
            operationMemory.append("ln(").append(temp.toString()).append(")").append(" ");

            operationLast = true;
            lastOperationLength = 5 + temp.toString().length();

            computeFinalResult(operationMemory.toString());

            updateResultTextView();
            resultMemory.delete(0,resultMemory.length());
            updateMemoryTextView();
        }

    }

    public void computeProcent(){

        Toast.makeText(AdvancedCalculator.this,"TEST %%%%",Toast.LENGTH_SHORT).show();

        Double temp = 0.0;

        Log.i("rej1", rej1);
        Log.i("rej2",rej2);

        if(mainResult == 0.0){
            temp = Double.valueOf(rej1);
        } else {
            temp = mainResult;
        }

        Log.i("temp",temp.toString());

        Double result = 0.0;

        String procentValue = resultMemory.toString();
        result = Double.valueOf(procentValue)/100*temp;



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


        operationMemory.append(" ").append(procentValue).append("%").append(" ");
        operationMemoryToClear = 3+String.valueOf(temp).length();


        resultOfOperation = result;
        mainResult = result;

        Log.i("af rej1", rej1);
        Log.i("af rej2",rej2);

        updateAfterOperation = false;

        updateResultTextView();

    }



    boolean updateAfterOperation = true;

    public boolean isOperationLast = false;

    public void computeFinalResult(String operationMemory){

        Log.i("Operation memory",operationMemory);

        String[] operationTab = operationMemory.split(" ");

        for(int i=0; i<operationTab.length; i++){
            Log.i("value",operationTab[i]);
        }

        String[] operationTab2;



        String[] alternativeTab2 = new String[operationTab.length-1];

        if(!CalculatorFunctions.isNumber(operationTab[operationTab.length-1])){
            for( int i = 0; i<operationTab.length-1; i++){
                alternativeTab2[i] = operationTab[i];
            }

            operationTab2 = ComputeResults.computeSimpleOperations(alternativeTab2);
        } else {
            operationTab2 = ComputeResults.computeSimpleOperations(operationTab);
        }

        Boolean doNotContinue = false;

        for (int i = 0; i < operationTab2.length; i++) {
            if (operationTab2[i].equals("/")) {
                {
                    try {

                        Double temp = Double.parseDouble(operationTab2[i + 1]);

                        if (temp.equals(0.0)) {
                            Toast.makeText(AdvancedCalculator.this, "NIE DZIEL PRZEZ 0", Toast.LENGTH_LONG).show();
                            doNotContinue = true;
                        }

                    } catch (Exception e) {
                        Log.i("dzielenie przez zero", "false");
                    }
                }
            }
        }

        if(!doNotContinue){

            List<String> tempList = ComputeResults.computeAdvancedOperations(operationTab2);
            String finalResult = ComputeResults.computeFinalResult(tempList);
            String shortenFinalResult = CalculatorFunctions.shortenString(finalResult);
            resultMemory.delete(0, resultMemory.length());
            resultMemory.append(shortenFinalResult);

        } else {
            resultMemory.delete(0,resultMemory.length());
            clearOperationMemory();
        }



       // Log.i("final result",finalResult);

    }

    public void pressButton(View view) {
        Button pressedButton = (Button) view;
        Log.i("Button pressed:", pressedButton.getTag().toString());
        String pressedButtonTag = "";
        pressedButtonTag = pressedButton.getTag().toString();

        if(isNumber(pressedButtonTag)){

            operationLast = false;
            lastOperationLength = 0;

            if(resultMemory.toString().contains("(")){
                resultMemory.delete(0,resultMemory.length());
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

        if(isSign(pressedButtonTag)){

            operationLast = false;
            lastOperationLength = 0;

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

            if(operationMemory.length() > 0){
                computeFinalResult(operationMemory.toString());
                updateResultTextView();
                resultMemory.delete(0,resultMemory.length());
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
            resultTextView.setText(resultMemory.substring(resultMemory.length()-11,resultMemory.length()));
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

        super.onSaveInstanceState(outState);

        outState.putString("resultMemory",resultMemory.toString());
        outState.putString("operationMemory",operationMemory.toString());
        outState.putBoolean("operationLast",operationLast);
        outState.putInt("lastOperationLength",lastOperationLength);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);

        String result = savedInstanceState.getString("resultMemory");
        String operation = savedInstanceState.getString("operationMemory");
        Boolean operationLastTemp = savedInstanceState.getBoolean("operationLast");
        Integer lastOperationLengthTemp = savedInstanceState.getInt("lastOperationLength");

        if(operationLastTemp != null){
            operationLast = operationLastTemp;
        }

        if(lastOperationLengthTemp != null){
            lastOperationLength = lastOperationLengthTemp;
        }

        if(result != null){
            updateResultTextView(result);
            resultMemory.append(result);
        }

        if (operation != null){
            updateMemoryTextView(operation);
            operationMemory.append(operation);
        }

        updateResultTextView(resultMemory.toString());
        updateMemoryTextView(operationMemory.toString());

    }

}