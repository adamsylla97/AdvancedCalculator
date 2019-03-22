package com.example.advancedcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SimpleCalculator extends AppCompatActivity {

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

    public void deleteFromMemory(View view) {
//        if(operationMemory.length()!=0){
//            operationMemory = operationMemory.substring(0,operationMemory.length()-1);
//            updateMemoryTextView();
//        }
    }

    private void getResult(StringBuilder operationMemory) {
        String tempMemory = operationMemory.toString();
        StringBuilder tempNumber = new StringBuilder();
        String[] tempStringTab = operationMemory.toString().split(" ");
        List<Double> numbersList = new ArrayList<>();
        List<String> signsList = new ArrayList<>();

        for (int i = 0; i < tempStringTab.length; i++) {

            if (isNumber(tempStringTab[i])) {
                numbersList.add(Double.parseDouble(tempStringTab[i]));
            }

            if (isSign(tempStringTab[i])) {
                if (tempStringTab[i].equals("+") || tempStringTab[i].equals("-")) {
                    signsList.add(tempStringTab[i]);
                }
            }

        }

    }

    String rej1 = "";
    String rej2 = "";
    String operator = "";

    public void setOperator(String sign){
        operator = sign;
    }

    public void setRegisters(){

        if(rej1.length() == 0){
            rej1 = resultMemory.toString();
        } else {
            rej2 = resultMemory.toString();
        }

        Log.i("rej1",rej1);
        Log.i("rej2",rej2);
    }

    private Double mainResult = 0.0;

    public Double addition(){
        try{

            Double result = 0.0;
            result = Double.parseDouble(rej1) + Double.parseDouble(rej2);
            rej1 = result.toString();
            return result;

        } catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    public Double subtraction(){
        try{

            Double result = 0.0;
            result = Double.parseDouble(rej1) - Double.parseDouble(rej2);
            rej1 = result.toString();
            return result;

        } catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    public void computeResult(){

        if(rej1.length() != 0 && rej2.length() != 0){

            switch (operator){
                case "+":
                    mainResult = addition();
                    Log.i("main result",mainResult.toString());
                    break;
                case "-":
                    mainResult = subtraction();
                    Log.i("main result",mainResult.toString());
                    break;
                default:
                    Log.i("ERROR","NO SUCH OPERATION");
            }
        }

    }

    public void pressButton(View view) {
        Button pressedButton = (Button) view;
        Log.i("Button pressed:", pressedButton.getTag().toString());
        String pressedButtonTag = "";
        pressedButtonTag = pressedButton.getTag().toString();

        if(isNumber(pressedButtonTag)){

            resultMemory.append(pressedButtonTag);

        }

        if(isSign(pressedButtonTag)){

            setRegisters();

            computeResult();

            setOperator(pressedButtonTag);

            if(resultMemory.length()!=0){
                operationMemory.append(resultMemory).append(" ");
                resultMemory.delete(0,resultMemory.length());
            }

            if(!isSign(String.valueOf(operationMemory.charAt(operationMemory.length()-2)))){
                operationMemory.append(pressedButtonTag).append(" ");
            }

            if(operationMemory.length()!=0 && isSign(String.valueOf(operationMemory.charAt(operationMemory.length()-2)))){
                operationMemory.delete(operationMemory.length()-2,operationMemory.length());
                operationMemory.append(pressedButtonTag).append(" ");
            }

        }

        updateMemoryTextView();
        updateResultTextView();

    }

    private void updateMemoryTextView() {
        if (operationMemory.length() < 22) {
            operationMemoryTextView.setText(operationMemory);
        } else {
            operationMemoryTextView.setText(operationMemory.substring(operationMemory.length() - 22));
        }
    }

    private void updateResultTextView() {
        if (resultMemory.length() < 11) {
            resultTextView.setText(resultMemory);
        } else {
            resultTextView.setText(resultMemory.substring(resultMemory.length() - 11));
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_calc_main);

        operationMemoryTextView = findViewById(R.id.operationMemoryTextView);
        resultTextView = findViewById(R.id.resultTextView);
    }

}