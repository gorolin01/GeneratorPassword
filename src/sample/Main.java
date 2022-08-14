package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Random;
import java.util.Scanner;
import java.lang.*;
import java.io.*;


public class Main {

//    private static int keyGen = ;
    private static String [][] SymbolMass =    {{"1234567890"},
                                {"qwertyuiop"},
                                {"asdfghjkl"},
                                {"zxcvbnm"}};
    //шаблон пароля
    //Qweyui2167Hjk

    public static void main(String[] args) throws java.lang.Exception {

       /* Scanner inp = new Scanner(System.in);
        String s=inp.next();
        int f=inp.nextInt();
        int t=inp.nextInt();*/
        System.out.println(convert10To62(3534985814L));
        //System.out.println(convert10To36(3534985814L));
        System.out.println(convert62To10(convert10To62(3534985814L)));
        /*for(int i = 0; i < 100; i++){
            System.out.println(generateCodeNum());
        }
        generateCodeWord();*/

        long codeTest = GenerateCode();
        System.out.println(codeTest);
        System.out.println(CodeDecoder(codeTest));
        //System.out.println(SymbolMass[1][0]);

    }

    public static String CodeDecoder(long code){

        //декодирует десятичный код

        String code_str = Long.toString(code);
        String result =
        decoderWord(code_str.charAt(0), code_str.charAt(1)) +
        decoderWord(code_str.charAt(2), code_str.charAt(3)) +
        decoderNum(code_str.charAt(4), code_str.charAt(5))  +
        decoderNum(code_str.charAt(6), code_str.charAt(7))  +
        decoderWord(code_str.charAt(8), code_str.charAt(9));

        return result;
    }

    public static String decoderWord(char a, char b){

        int a1 = Integer.parseInt(String.valueOf(a));
        int b1 = Integer.parseInt(String.valueOf(b));
        String result = "";

        switch (a1){
            case 1 :
                result = String.valueOf(SymbolMass[1][0].charAt(b1)) + SymbolMass[1][0].charAt(b1 - 1) +
                        SymbolMass[1][0].charAt(b1 - 2);
                break;
            case 2 :
                result = String.valueOf(SymbolMass[1][0].charAt(b1)) + SymbolMass[1][0].charAt(b1 + 1) +
                        SymbolMass[1][0].charAt(b1 + 2);
                break;
            case 3 :
                result = String.valueOf(SymbolMass[2][0].charAt(b1)) + SymbolMass[2][0].charAt(b1 - 1) +
                        SymbolMass[2][0].charAt(b1 - 2);
                break;
            case 4 :
                result = String.valueOf(SymbolMass[2][0].charAt(b1)) + SymbolMass[2][0].charAt(b1 + 1) +
                        SymbolMass[2][0].charAt(b1 + 2);
                break;
            case 5 :
                result = String.valueOf(SymbolMass[3][0].charAt(b1)) + SymbolMass[3][0].charAt(b1 - 1) +
                        SymbolMass[3][0].charAt(b1 - 2);
                break;
            case 6 :
                result = String.valueOf(SymbolMass[3][0].charAt(b1)) + SymbolMass[3][0].charAt(b1 + 1) +
                        SymbolMass[3][0].charAt(b1 + 2);
                break;
        }

        return result;
    }

    public static String decoderNum(char a, char b){

        int a1 = Integer.parseInt(String.valueOf(a));
        int b1 = Integer.parseInt(String.valueOf(b));
        String result = "";

        switch (a1){
            case 1 :
                result = String.valueOf(SymbolMass[0][0].charAt(b1)) + SymbolMass[0][0].charAt(b1 - 1);
                break;
            case 2 :
                result = String.valueOf(SymbolMass[0][0].charAt(b1)) + SymbolMass[0][0].charAt(b1 + 1);
                break;
        }

        return result;
    }

    public static long GenerateCode(){

        //Генерирует код в десятичном представлении

        String result = Integer.toString(generateCodeWord()) + Integer.toString(generateCodeWord()) +
                Integer.toString(generateCodeNum()) + Integer.toString(generateCodeNum()) + Integer.toString(generateCodeWord());

        return Long.parseLong(result);
    }

    public static int generateCodeWord(){

        //шифруем каждое слово (пример: ghj) его рядом и направлением 1 - первый ряд, левое направление
        //2 - первый ряд, правое направление и т.д. до 6. Второй аргумент это позиция начального элемента
        //от 0 до 9 или меньше, в зависимости от ряда.

        int position = -1;
        int row = randomMinMax(1, 6);

        switch (row){
            case 1 :
                position = randomMinMax(2, 9);
                break;
            case 2 :
                position = randomMinMax(0, 7);
                break;
            case 3 :
                position = randomMinMax(2, 8);
                break;
            case 4 :
                position = randomMinMax(0, 6);
                break;
            case 5 :
                position = randomMinMax(2, 6);
                break;
            case 6 :
                position = randomMinMax(0, 4);
                break;
        }

        return (row * 10) + position;
    }

    public static int generateCodeNum(){

        //шифрует пару цифр (пример: 34) направление и позиция

        int position = -1;
        int direction = randomMinMax(1, 2);

        switch (direction){
            case 1 :
                position = randomMinMax(1, 9);
                break;
            case 2 :
                position = randomMinMax(0, 8);
        }

        return (direction * 10) + position;
    }

    public static int randomMinMax(int min, int max){

        int diff = max - min;
        Random rand = new Random();
        int res = rand.nextInt(diff + 1);
        res += min;
        return res;

    }

    public static String convert10To62(long number) {
        String result = "";
        long rest = 0;
        String alphabet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        int FOUNDATION_SYSTEM = alphabet.length();

        while(number != 0){

            rest = number % FOUNDATION_SYSTEM;
            number = number / FOUNDATION_SYSTEM;
            result += alphabet.charAt((int)rest);

            if(number <= FOUNDATION_SYSTEM){

                result += alphabet.charAt((int)number);
                number = 0;

            }

        }

        return invert(result);
    }

    public static String convert10To36(long number) {
        String result = "";
        long rest = 0;
        String alphabet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int FOUNDATION_SYSTEM = alphabet.length();

        while(number != 0){

            rest = number % FOUNDATION_SYSTEM;
            number = number / FOUNDATION_SYSTEM;
            result += alphabet.charAt((int)rest);

            if(number <= FOUNDATION_SYSTEM){

                result += alphabet.charAt((int)number);
                number = 0;

            }

        }

        return invert(result);
    }

    public static long convert62To10(String number) {

        long result = 0;
        String alphabet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        int FOUNDATION_SYSTEM = alphabet.length();

        for(int i = 0; i < number.length(); i++){

            result += alphabet.indexOf(number.charAt(i)) * Math.pow(FOUNDATION_SYSTEM, number.length() - i - 1);

        }

        //return Long.parseLong(result);
        return result;
    }

    public static String invert(String s){

        String res = "";

        for(int i = s.length() - 1; i >= 0; --i){

            res += s.charAt(i);

        }

        return res;
    }


}
