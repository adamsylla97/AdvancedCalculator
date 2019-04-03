package com.example.advancedcalculator;

import java.util.*;

public class ComputeResults {

    public static String[] computeSimpleOperations(String[] tab){

        for(int i=0; i<tab.length-1; i++){

            if(tab[i].contains("sin("))
                tab[i] = CalculatorFunctions.computeSin(tab[i]);
            else if(tab[i].contains("cos("))
                tab[i] = CalculatorFunctions.computeCos(tab[i]);
            else if(tab[i].contains("tan("))
                tab[i] = CalculatorFunctions.computeTan(tab[i]);
            else if(tab[i].contains("ln("))
                tab[i] = CalculatorFunctions.computeLn(tab[i]);
            else if(tab[i].contains("sqrt("))
                tab[i] = CalculatorFunctions.computeSqrt(tab[i]);
            else if(tab[i].contains("sqr("))
                tab[i] = CalculatorFunctions.computeSqr(tab[i]);
            else if(tab[i].contains("log("))
                tab[i] = CalculatorFunctions.computeLog(tab[i]);

        }

        return tab;

    }

    public static List<String> computeAdvancedOperations(String[] tab){

        List<String> listWithParts = Arrays.asList(tab);

        for(int i=0; i<listWithParts.size(); i++){

            if(listWithParts.get(i).contains("*")){
                String temp = CalculatorFunctions.multiplication(listWithParts.get(i-1),listWithParts.get(i+1));
                listWithParts.set(i,temp);
                listWithParts.set(i+1,temp);
                listWithParts.set(i-1,temp);
            } else if(listWithParts.get(i).contains("/")){
                String temp = CalculatorFunctions.division(listWithParts.get(i-1),listWithParts.get(i+1));
                listWithParts.set(i,temp);
                listWithParts.set(i+1,temp);
                listWithParts.set(i-1,temp);
            } if(listWithParts.get(i).contains("^")){
                String temp = CalculatorFunctions.power(listWithParts.get(i-1),listWithParts.get(i+1));
                listWithParts.set(i,temp);
                listWithParts.set(i+1,temp);
                listWithParts.set(i-1,temp);
            }

        }

        Boolean nextDelete = false;

        int test = 0;

        for(int i=listWithParts.size()-1; i>=0; i--){

            try{
                Double.parseDouble(listWithParts.get(i));
                nextDelete = true;
            } catch (Exception e){
                nextDelete = false;
            }

            if(nextDelete){
                test++;
            } else {
                test = 0;
            }

            if(test > 1){
                listWithParts.set(i,"TO_DELETE");
            }

        }

        List<String> returnList = new ArrayList();

        returnList.addAll(listWithParts);

        List remove = new ArrayList();
        remove.add("TO_DELETE");

        returnList.removeAll(remove);

        return returnList;

    }

    public static String computeFinalResult(List<String> listWithParts){

        String finalResult = listWithParts.get(0);

        System.out.println(listWithParts.size());

        for(int i=1; i<listWithParts.size()-1; i++){

            if(listWithParts.get(i).contains("+")){
                finalResult = CalculatorFunctions.addition(finalResult,listWithParts.get(i+1));
            } else if(listWithParts.get(i).contains("-")){
                finalResult = CalculatorFunctions.subtraction(finalResult,listWithParts.get(i+1));
            }

        }

        return finalResult;

    }

}
