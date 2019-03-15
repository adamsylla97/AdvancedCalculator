package com.example.advancedcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SimpleCalculator extends AppCompatActivity {

    TextView operationMemoryTextView;
    TextView resultTextView;
    StringBuilder resultMemory = new StringBuilder();
    StringBuilder operationMemory = new StringBuilder();

    private boolean isSign(String sign){
        if(sign.equals("+") || sign.equals("-") || sign.equals("*") || sign.equals("/")){
            return true;
        }
        return false;
    }

    private boolean isNumber(String number){
        if(number.matches("\\d")){
            return true;
        }
        return false;
    }

    public void deleteFromMemory(View view){
//        if(operationMemory.length()!=0){
//            operationMemory = operationMemory.substring(0,operationMemory.length()-1);
//            updateMemoryTextView();
//        }
    }

    private void getResult(StringBuilder operationMemory){
        String tempMemory = operationMemory.toString();
        StringBuilder tempNumber = new StringBuilder();
        Double numberOne = 0.0;
        Double numberTwo = 0.0;
        for(int i=0; i<tempMemory.length(); i++){
            if(isNumber(String.valueOf(tempMemory.charAt(i)))){
                tempNumber.append(tempMemory.charAt(i));
            } else if(isSign(String.valueOf(tempMemory.charAt(i)))){

            }
        }
    }

    public void pressButton(View view){
        Button pressedButton = (Button) view;
        Log.i("Button pressed:", pressedButton.getTag().toString());
        String pressedButtonTag = "";
        pressedButtonTag = pressedButton.getTag().toString();

        if(isNumber(pressedButtonTag)){
            resultMemory.append(pressedButtonTag);
        }

        if(isSign(pressedButtonTag)){
            operationMemory.append(resultMemory).append(pressedButtonTag);
            resultMemory.delete(0,resultMemory.length());
        }

        Log.i("OPERATIONAL MEMORY:",operationMemory.toString());

        updateMemoryTextView();
        updateResultTextView();

    }

    private void updateMemoryTextView(){
        if(operationMemory.length()<22){
            operationMemoryTextView.setText(operationMemory);
        } else {
            operationMemoryTextView.setText(operationMemory.substring(operationMemory.length()-22));
        }
    }

    private void updateResultTextView(){
        if(resultMemory.length()<11){
            resultTextView.setText(resultMemory);
        } else {
            resultTextView.setText(resultMemory.substring(resultMemory.length()-11));
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
