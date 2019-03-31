package com.example.advancedcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public void buttonClicked(View view){

        Button pressedButton = (Button) view;
        Log.i("PRESSED:",pressedButton.getTag().toString());

        Intent simpleIntent = new Intent(this, SimpleCalculator.class);
        Intent advancedIntent = new Intent(this, AdvancedCalculator.class);
        Intent aboutIntent = new Intent(this, About.class);


        switch (pressedButton.getTag().toString()){

            case "simple" :
                MainActivity.this.startActivity(simpleIntent);
                break;
            case "advanced" :
                MainActivity.this.startActivity(advancedIntent);
                break;
            case "about" :
                MainActivity.this.startActivity(aboutIntent);
                break;
            case "exit" :
                finish();
                break;
            default:
                Log.i("ERROR MENU:","NO SUCH OPTION");
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
