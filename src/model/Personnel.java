package model;

import java.util.Scanner;

import static view.util.InputUtil.inputFloatPositive;
import static view.util.InputUtil.inputString;

public abstract class Personnel {
    private int id;
    protected String name;
    protected String phone;
    protected float date;
    protected float wage;

    private static int STT = 1;

    /* constructors */
    public Personnel() {
        this.id = STT++;
    }

    public Personnel( String name, String phone, float date, float wage) {
        this.name = name;
        this.phone = phone;
        this.date = date;
        this.wage = wage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public float getDate() {
        return date;
    }

    public void setDate(float date) {
        this.date = date;
    }

    public float getWage() {
        return wage;
    }

    public void setWage(float wage) {
        this.wage = wage;
    }

    /* methods */
    public float calcSalary() {
        return date * wage;
    }

    public void inputInfomation(Scanner sc) {
        System.out.println("====NHẬP THÔNG TIN====");


        this.name = inputString("Họ tên:");
        do {
            this.phone = inputString("Số điện thoại:");
            if (this.phone.length() != 10) {
                System.out.println("Số điện thoại phải có 10 số, Nhập lại !!!");
            }
        } while (this.phone.length() != 10);

        this.date = inputFloatPositive("Số ngày làm:");

    }

    public void outputInfomation() {
        System.out.println("====XUẤT THÔNG TIN===");
        System.out.printf("Mã số: %d\n", id);
        System.out.printf("Họ tên: %s\n", name);
        System.out.printf("Chức vụ: %s\n", getPosition());
        System.out.printf("Số điện thoại: %s\n", phone);
        System.out.printf("Số ngày làm: %.0f\n", date);
        System.out.printf("Lương một ngày: %.0f\n", wage);
    }

    public abstract String getPosition();

    public abstract String getMore() ;

    public abstract void removeMore();
}
