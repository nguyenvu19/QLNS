package model;

public class Staff extends Personnel {
    private int leader;

    public Staff(String name, String phone, float date, float wage) {
        super(name, phone, date, wage);
        this.wage = 100;
    }

    public Staff() {
        super();
        this.wage = 100;
    }

    public int getLeader() {
        return leader;
    }

    public void setLeader(int leader) {
        this.leader = leader;
    }

    @Override
    public String getPosition() {
        return "Nhân viên";

    }

    @Override
    public String getMore() {
        return String.valueOf(this.leader);

    }

    @Override
    public void removeMore() {
        this.leader = 0;

    }
}
