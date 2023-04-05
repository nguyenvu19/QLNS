package controller;

import model.Director;
import model.Leader;
import model.Personnel;
import model.Staff;
import view.Menu;
import view.util.PrintFormat;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static model.Company.monthRevenue;
import static view.util.InputUtil.inputInt;
import static view.util.InputUtil.inputN;

public class HumanManagement {
    private List<Personnel> listPersonnel;

    public HumanManagement() {
        listPersonnel = new ArrayList<Personnel>();

        listPersonnel.add(new Staff("Tuấn", "113", 20, 200));
        listPersonnel.add(new Staff("Vương", "114", 20, 200));

        listPersonnel.add(new Leader("Hoàn", "113", 20, 300));
        listPersonnel.add(new Leader("Khoa", "113", 20, 300));

        listPersonnel.add(new Director("Phúc", "225", 20, 500));
        listPersonnel.add(new Director("Long", "225", 20, 500, (float) 0.5));
    }

    public boolean add(Personnel personnel) {
        if (personnel == null)
            return false;

        if (personnel.getId() == 0)
            return false;

        for (Personnel person : listPersonnel) {
            if (person.getId() == personnel.getId())
                return false;
        }

        return listPersonnel.add(personnel);
    }

    public void phanBoNhanVien(Scanner sc) {
        int luaChon = -1;
        List<Staff> dsNhanVienChuaPhanBo = new ArrayList<Staff>();
        List<Leader> dsTruongPhong = new ArrayList<Leader>();

        for (Personnel person : listPersonnel) {
            if (person instanceof Staff) {
                if (((Staff) person).getLeader() == 0)
                    dsNhanVienChuaPhanBo.add((Staff) person);
            }

            if (person instanceof Leader)
                dsTruongPhong.add((Leader) person);
        }

        for (Staff nv : dsNhanVienChuaPhanBo) {
            System.out.println("=====--PHÂN BỔ NHÂN VIÊN--=====");
            nv.outputInfomation();
            drawLine(20);
            System.out.println("Chọn trưởng phòng để phân bổ: ");
            for (int i = 0; i < dsTruongPhong.size(); i++) {
                Leader tp = dsTruongPhong.get(i);
                System.out.printf("%d. %20s %5d\n", i + 1, tp.getName(), tp.getId());
            }
            System.out.println("0. Không phân bổ.");
            System.out.println("-1. Thoát chức năng phân bổ.");

            luaChon = inputInt("Lựa chọn:");

            if (luaChon == 0)
                continue;

            if (luaChon == -1)
                return;

            if (luaChon > 0 && luaChon <= dsTruongPhong.size()) {
                nv.setLeader(dsTruongPhong.get(luaChon - 1).getId());
                dsTruongPhong.get(luaChon - 1).addMember();
            } else {
                System.out.println("Lựa chọn không hợp lệ. Vui lòng tiến hành phân bổ lần sau.");
            }
        }

    }

    public float tinhTongLuong() {
        float tongLuong = 0;

        for (Personnel nhanSu : listPersonnel) {
            tongLuong += nhanSu.calcSalary();
        }

        return tongLuong;
    }

    public void xuatDanhSachNhanSu() {
        int width = 160;
        // in header
        drawLine(width);
        System.out.printf(PrintFormat.HEADER_FORMAT, "STT", "Mã số", "Họ tên", "Số điện thoại", "Số ngày làm",
                "Lương một ngày", "Lương", "Chức vụ", "Trưởng phòng/Số nhân viên/Cổ phần");
        drawLine(width);
        // in body
        for (int i = 0; i < listPersonnel.size(); i++) {
            Personnel ns = listPersonnel.get(i);
            System.out.printf(PrintFormat.BODY_FORMAT, i + 1, ns.getId(), ns.getName(), ns.getPhone(),
                    ns.getDate(), ns.getWage(), ns.calcSalary(), ns.getPosition(), ns.getMore());
        }
        // in bottom
        drawLine(width);
        System.out.printf(PrintFormat.BOTTOM_FORMAT, "Tổng lương", this.tinhTongLuong(), "");
        drawLine(width);
    }

    private void drawLine(int length) {
        for (int i = 0; i < length; i++)
            System.out.print("-");
        System.out.println();
    }

    public int findNhanSu(int maSo) {
        for (int i = 0; i < listPersonnel.size(); i++) {
            if (listPersonnel.get(i).getId() == maSo) {
                return i;
            }
        }
        return -1;
    }

    public void themNhanSu(Scanner sc) {
        Personnel addPerson;
        String loaiNhanSu = "";

        System.out.println("Bạn muốn thêm: ");
        System.out.println("1. Nhân viên.");
        System.out.println("2. Trưởng phòng.");
        System.out.println("3. Giám đốc.");
        System.out.print("Lựa chọn: ");

        loaiNhanSu = sc.nextLine();

        switch (loaiNhanSu) {
            case "1":
                addPerson = new Staff();
                break;
            case "2":
                addPerson = new Leader();
                break;
            case "3":
                addPerson = new Director();
                break;
            default:
                System.out.println("Sai format");
                return;
        }

        addPerson.inputInfomation(sc);

        if (add(addPerson))
            System.out.println("Đã thêm nhân sự thành công.");
        else
            System.out.println("Thêm nhân sự thất bại.");
    }

    public void xoaNhanSu(Scanner sc) {
        int maSo = inputN("Nhập mã nhân sự muốn xóa:");
        if (findNhanSu(maSo) >= 0) {
            Personnel reMoveNhanSu = listPersonnel.get(findNhanSu(maSo));
            if (reMoveNhanSu instanceof Staff) {
                int maTruongPhong = Integer.parseInt(reMoveNhanSu.getMore());
                for (int i = 0; i < listPersonnel.size(); i++) {
                    if (listPersonnel.get(i) instanceof Leader) {
                        if (maTruongPhong == listPersonnel.get(i).getId()) {
                            listPersonnel.get(i).removeMore();
                        }

                    }
                }
                listPersonnel.remove(reMoveNhanSu);
            }
            if (reMoveNhanSu instanceof Leader) {
                for (int i = 0; i < listPersonnel.size(); i++) {
                    if (listPersonnel.get(i) instanceof Staff) {
                        if (listPersonnel.get(i).getMore().equals(reMoveNhanSu.getId() + "")) {
                            listPersonnel.get(i).removeMore();
                        }

                    }
                }
                listPersonnel.remove(reMoveNhanSu);
            }
            if (reMoveNhanSu instanceof Director) {
                listPersonnel.remove(reMoveNhanSu);
            }
        } else {
            System.out.println("Mã số không tồn tại");
        }
    }

    public void xuatTongLuong() {
        System.out.println("Tổng lương: " + tinhTongLuong());
    }

    public void xuatNhanVienLuongCaoNhat() {
        ArrayList<Personnel> list = new ArrayList<Personnel>();
        float max = -1;
        for (Personnel x : listPersonnel) {
            if (x instanceof Staff) {
                if (x.calcSalary() > max) {
                    max = x.calcSalary();
                }
            }
        }

        for (Personnel x : listPersonnel) {
            if (x instanceof Staff) {
                if (x.calcSalary() == max) {
                    list.add(x);
                }
            }
        }
        if (list.size() > 0) {
            System.out.println("Các nhân viên có tổng lương lớn nhất là:");
            System.out.printf("%20s |%20s |%20s", "Mã số", "Tên Nhân Viên", "Tổng lương");
            System.out.println();
            for (Personnel x : list) {
                System.out.printf("%20d |%20s |%20f", x.getId(), x.getName(), x.calcSalary());
                System.out.println();
            }
        } else {
            System.out.println("Không tồn tại nhân viên!!!");
        }

    }

    public void xuatTruongPhongNhieuNVNhat() {
        ArrayList<Personnel> list = new ArrayList<Personnel>();
        float max = -1;
        for (Personnel x : listPersonnel) {
            if (x instanceof Leader) {
                if (Float.parseFloat(x.getMore()) > max) {
                    max = Float.parseFloat(x.getMore());
                }
            }
        }

        for (Personnel x : listPersonnel) {
            if (x instanceof Leader) {
                if (Float.parseFloat(x.getMore()) == max) {
                    list.add(x);
                }
            }
        }
        if (list.size() > 0) {
            if (max > 0) {
                System.out.println("Các trưởng phòng có nhiều nhân viên nhất là:");
                System.out.printf("%20s |%20s |%20s", "Mã số", "Tên trưởng phòng", "Số lượng nhân viên");
                System.out.println();
                for (Personnel x : list) {
                    System.out.printf("%20d |%20s |%20s", x.getId(), x.getName(), x.getMore());
                    System.out.println();
                }
            } else {
                System.out.println("Tất cả trưởng phòng đều có số lượng nhân viên = 0");
            }
        } else {
            System.out.println("Không tồn tại trưởng phòng!!!");
        }

    }

    public void xuatGiamDocCoCoPhanNhieuNhat() {
        ArrayList<Personnel> list = new ArrayList<Personnel>();
        float max = -1;
        for (Personnel x : listPersonnel) {
            if (x instanceof Director) {
                if (Float.parseFloat(x.getMore()) > max) {
                    max = Float.parseFloat(x.getMore());
                }
            }
        }

        for (Personnel x : listPersonnel) {
            if (x instanceof Director) {
                if (Float.parseFloat(x.getMore()) == max) {
                    list.add(x);
                }
            }
        }
        if (list.size() > 0) {
            if (max > 0) {
                System.out.println("Các giám đốc có số cổ phần nhiều nhất là:");
                System.out.printf("%20s |%20s |%20s", "Mã số", "Tên Giám Đốc", "Số cổ phần");
                System.out.println();
                for (Personnel x : list) {
                    System.out.printf("%20d |%20s |%20s", x.getId(), x.getName(), x.getMore());
                    System.out.println();
                }
            } else {
                System.out.println("Các giám đốc có số cổ phần đều bằng = 0");
            }

        } else {
            System.out.println("Không tồn tại giám đốc!!!");
        }

    }

    public void xuatThuNhapCuaGiamDoc() {
        ArrayList<Personnel> list = new ArrayList<Personnel>();
        Menu subMenu = new Menu();
        for (Personnel x : listPersonnel) {
            if (x instanceof Director) {
                list.add(x);
            }
        }

        if (list.size() > 0) {
            System.out.println("Các giám đốc có số cổ phần nhiều nhất là:");
            System.out.printf("%20s |%20s |%20s", "Mã số", "Tên Giám Đốc", "Tổng thu nhập");
            System.out.println();
            for (Personnel x : listPersonnel) {
                float loinhuan = (float) monthRevenue - tinhTongLuong();
                System.out.printf("%20d |%20s |%20s", x.getId(), x.getName(), x.calcSalary() + Float.parseFloat(x.getMore()) * loinhuan);
                System.out.println();
            }
        } else {
            System.out.println("Không tồn tại giám đốc!!!");
        }

    }

    public void sortOrderByAlphabet() {
        Personnel ns1, ns2, ns3;
        for (int i = 0; i < listPersonnel.size(); i++) {
            for (int j = i + 1; j < listPersonnel.size(); j++) {
                ns1 = listPersonnel.get(i);
                ns2 = listPersonnel.get(j);
                if (ns1.getName().compareTo(ns2.getName()) > 0) {
                    ns3 = listPersonnel.get(i);
                    listPersonnel.set(i, listPersonnel.get(j));
                    listPersonnel.set(j, ns3);
                }
            }
        }
    }

    public void sortOrderBySalary() {
        Personnel ns1, ns2, ns3;
        for (int i = 0; i < listPersonnel.size(); i++) {
            for (int j = i + 1; j < listPersonnel.size(); j++) {
                ns1 = listPersonnel.get(i);
                ns2 = listPersonnel.get(j);
                if (ns1.calcSalary() > ns2.calcSalary()) {
                    ns3 = listPersonnel.get(i);
                    listPersonnel.set(i, listPersonnel.get(j));
                    listPersonnel.set(j, ns3);
                }
            }
        }
    }
}
