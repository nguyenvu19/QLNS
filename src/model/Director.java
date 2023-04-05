package model;

import java.util.Scanner;

import static view.util.InputUtil.inputFloatPositive;

public class Director extends Personnel {
    private float companyShares;

    public Director(String name, String phone, float date, float wage) {
        super(name, phone, date, wage);
        this.wage = 300;
    }

    public Director(String name, String phone, float date, float wage, float companyShares) {
        super(name, phone, date, wage);
        this.companyShares = companyShares;
        this.wage = 300;
    }

    public Director() {
        super();
        this.wage = 300;
    }

    @Override
    public String getPosition() {
        return "Giám đốc";
    }

    @Override
    public String getMore() {
        return String.valueOf(this.companyShares);
    }

    @Override
    public void removeMore() {
    }

    @Override
    public void inputInfomation(Scanner sc) {
        super.inputInfomation(sc);
        do {
            this.companyShares = inputFloatPositive("Cổ phần:");
            if (this.companyShares > 1) {
                System.out.println("Cổ phần chỉ từ 0->1");
            }
        } while (this.companyShares > 1);
    }
}
