package model;


import java.util.Scanner;

import static view.util.InputUtil.inputDoublePositive;
import static view.util.InputUtil.inputString;

public class Company {
    private String name;
    private String taxCode;
    public static double monthRevenue;

    public void inputInformation(Scanner scanner) {
        System.out.println("====NHẬP THÔNG TIN CÔNG TY====");
        name = inputString("Tên công ty");
        boolean check = false;
        do {
            taxCode = inputString("Mã số thuế:");
            if (taxCode.length() == 12) {
                check = true;
            } else {
                System.out.println("Nhập lại, mã số thuế có 12 chữ!");
                check = false;
            }
        } while (check == false);
        monthRevenue = inputDoublePositive("Doanh thu tháng:");
    }

    public void outputInfomation() {
        System.out.println("Tên công ty: " + this.name);
        System.out.println("Mã số thuế: " + this.taxCode);
        System.out.println("Doanh thu tháng: " + monthRevenue);
    }
}
