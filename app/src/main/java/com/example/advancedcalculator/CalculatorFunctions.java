package com.example.advancedcalculator;

import android.util.Log;

public class CalculatorFunctions {

    public static Boolean isNumber(String x){
        try{
            if(x.contains("(")){
                return true;
            }
            Double.parseDouble(x);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public static String addition(String x, String y){
        Double tempX = Double.valueOf(x);
        Double tempY = Double.valueOf(y);

        return String.valueOf(tempX+tempY);
    }

    public static String subtraction(String x, String y){
        Double tempX = Double.valueOf(x);
        Double tempY = Double.valueOf(y);

        return String.valueOf(tempX-tempY);
    }

    public static String multiplication(String x, String y){
        Double tempX = Double.valueOf(x);
        Double tempY = Double.valueOf(y);

        return String.valueOf(tempX*tempY);
    }

    public static String division(String x, String y){
        Double tempX = Double.valueOf(x);
        Double tempY = Double.valueOf(y);

        if(tempY == 0){
            return null;
        }
        return String.valueOf(tempX/tempY);
    }

    public static String power(String x, String y){
        double tempX = Double.valueOf(x);
        double tempY = Double.valueOf(y);

        return String.valueOf(Math.pow(tempX,tempY));
    }

    public static String computeLog(String x){
        String temp = x.substring(4,x.length()-1);
        Double tempDouble = Double.valueOf(temp);

        if(tempDouble.equals(0.0)){
            return null;
        }
        return String.valueOf(Math.log10(tempDouble));
    }

    public static String computeLn(String x){
        String temp = x.substring(3,x.length()-1);
        Double tempDouble = Double.valueOf(temp);

        if(tempDouble.equals(0.0)){
            return null;
        }
        return String.valueOf(Math.log(tempDouble));
    }

    public static String computeSqrt(String x){
        String temp = x.substring(5,x.length()-1);
        Double tempDouble = Double.valueOf(temp);

        return String.valueOf(Math.sqrt(tempDouble));
    }

    public static String computeSqr(String x){
        String temp = x.substring(4,x.length()-1);
        Double tempDouble = Double.valueOf(temp);

        return String.valueOf(Math.pow(tempDouble,2.0));
    }

    public static String computeSin(String x){
        String temp = x.substring(4,x.length()-1);
        Double tempDouble = Double.valueOf(temp);

        return String.valueOf(Math.sin(tempDouble));
    }

    public static String computeCos(String x){
        String temp = x.substring(4,x.length()-1);
        Double tempDouble = Double.valueOf(temp);

        return String.valueOf(Math.cos(tempDouble));
    }

    public static String computeTan(String x){
        String temp = x.substring(4,x.length()-1);
        Double tempDouble = Double.valueOf(temp);

        return String.valueOf(Math.tan(tempDouble));
    }

}

