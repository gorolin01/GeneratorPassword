package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Scanner;
import java.lang.*;
import java.io.*;


public class Main {

//    private static int keyGen = ;
//    private static String [] WordMass = {{"qwertyuiop"},
//                                        {},{}};
    //шаблон пароля
    //Qweyui2167Hjk

    public static void main(String[] args) throws java.lang.Exception {

       /* Scanner inp = new Scanner(System.in);
        String s=inp.next();
        int f=inp.nextInt();
        int t=inp.nextInt();*/
        System.out.println(convert10To62(3534985814L));
        System.out.println(convert10To36(3534985814L));
        System.out.println(convert62To10(convert10To62(3534985814L)));


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
