package view.util;

import java.util.Scanner;

public class InputUtil {
    public static int inputPositive(String mess) {
        int result = 0;
        Scanner sc = new Scanner(System.in);

        int flag = 0;
        do {
            System.out.print(mess);
            String str = sc.nextLine();

            try {
                result = Integer.parseInt(str);
                if (result > 0)
                    flag = 1;
                else
                    System.out.println("Must input positive number!");

            } catch (Exception e) {
                // TODO: handle exception
                System.out.println("Nhập sai format");
            }
        } while (flag == 0);
        return result;
    }

    public static int inputInt(String mess) {
        int result = 0;
        Scanner sc = new Scanner(System.in);

        int flag = 0;
        do {
            System.out.print(mess);
            String str = sc.nextLine();

            try {
                result = Integer.parseInt(str);
                flag = 1;

            } catch (Exception e) {
                // TODO: handle exception
                System.out.println("Nhập sai format");
            }
        } while (flag == 0);
        return result;
    }
    public static int inputN(String mess) {
        int result = 0;
        Scanner sc = new Scanner(System.in);

        int flag = 0;
        do {
            System.out.print(mess);
            String str = sc.nextLine();

            try {
                result = Integer.parseInt(str);
                if (result >= 0)
                    flag = 1;
                else
                    System.out.println("Must input positive number!");

            } catch (Exception e) {
                // TODO: handle exception
                System.out.println("Nhập sai format");
            }
        } while (flag == 0);
        return result;
    }
    public static String inputString(String mess) {
        String result = "";
        Scanner sc = new Scanner(System.in);

        int flag = 0;
        do {
            System.out.print(mess);
            result = sc.nextLine();
            if (result.length()>0) {
                flag = 1;
            } else {
                System.out.println("Nhập sai format");
            }
        } while (flag == 0);
        return result;
    }
    public static double inputDoublePositive(String mess) {
        double result = 0;
        Scanner sc = new Scanner(System.in);

        int flag = 0;
        do {
            System.out.print(mess);
            String str = sc.nextLine();

            try {
                result = Double.parseDouble(str);
                if (result > 0)
                    flag = 1;
                else
                    System.out.println("Must input positive number!");

            } catch (Exception e) {
                // TODO: handle exception
                System.out.println("Nhập sai format");
            }
        } while (flag == 0);
        return result;
    }
    public static float inputFloatPositive(String mess) {
        float result = 0;
        Scanner sc = new Scanner(System.in);

        int flag = 0;
        do {
            System.out.print(mess);
            String str = sc.nextLine();

            try {
                result = Float.parseFloat(str);
                if (result > 0)
                    flag = 1;
                else
                    System.out.println("Must input positive number!");

            } catch (Exception e) {
                // TODO: handle exception
                System.out.println("Nhập sai format");
            }
        } while (flag == 0);
        return result;
    }
}
