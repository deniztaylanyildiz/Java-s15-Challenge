package com.workingtechs15Proj.utils;
import java.util.Scanner;

public class InputReader {
    private Scanner scanner = new Scanner(System.in);

    public String readString(String msg) {
        System.out.print(msg);
        return scanner.nextLine();
    }

    public double readDouble(String msg) {
        try {
            return Double.parseDouble(readString(msg));
        } catch (Exception e) {
            System.out.println("Hata: Geçersiz sayısal değer!");
            return 0;
        }
    }
}