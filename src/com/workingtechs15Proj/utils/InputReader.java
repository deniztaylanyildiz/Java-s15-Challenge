package com.workingtechs15Proj.utils;
import java.util.Scanner;

public class InputReader {
    private Scanner scanner = new Scanner(System.in);

    public String readString(String msg) {
        System.out.print(msg);
        return scanner.nextLine();
    }

    public double readDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double val = scanner.nextDouble();
                scanner.nextLine(); // Temizlik
                return val;
            } catch (Exception e) {
                System.out.println("[!] Geçersiz sayı formatı!");
                scanner.nextLine();
            }
        }
    }
}