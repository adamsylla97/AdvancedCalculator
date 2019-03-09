package com.example.advancedcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SimpleCalculator extends AppCompatActivity {

    TextView operationMemoryTextView;
    String operationMemory = "";

    private boolean isSign(String sign){
        if(sign.equals("+") || sign.equals("-") || sign.equals("*") || sign.equals("/")){
            Log.i("SIGN:","TRUE"+sign);
            return true;
        }
        return false;
    }

    private boolean isNumber(String number){
        if(number.matches("\\d")){
            Log.i("NUMBER:","TRUE"+number);
            return true;
        }
        return false;
    }

    public void pressButton(View view){
        Button pressedButton = (Button) view;
        Log.i("Button pressed:", pressedButton.getTag().toString());
        String pressedButtonTag = "";
        pressedButtonTag = pressedButton.getTag().toString();

        isSign(pressedButtonTag);
        isNumber(pressedButtonTag);

        if(operationMemory.length() == 0){
            operationMemory = operationMemory + " " + pressedButtonTag;
        }
        else{

            if(isSign(pressedButtonTag)){

                if(isSign(String.valueOf(operationMemory.charAt(operationMemory.length()-1)))){ //if last sign in memory is sign then we swap it
                    operationMemory = operationMemory.substring(0,operationMemory.length()-1)+pressedButtonTag;
                } else {
                    operationMemory = operationMemory + " " + pressedButtonTag;
                }

            } else{

                if(isNumber(String.valueOf(operationMemory.charAt(operationMemory.length()-1)))){ //if last sign in memory is number then we add that number to memory without space
                    operationMemory =operationMemory + pressedButtonTag;
                } else {
                    operationMemory = operationMemory + " " + pressedButtonTag;
                }

            }

        }

        if(operationMemory.length()<22){
            operationMemoryTextView.setText(operationMemory);
        } else {
            operationMemoryTextView.setText(operationMemory.substring(operationMemory.length()-22));
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_calc_main);

        operationMemoryTextView = findViewById(R.id.operationMemoryTextView);
        operationMemoryTextView.setText("");
    }

}
