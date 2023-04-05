package model;

public class Leader extends Personnel {
    private int numOfMember;


    public Leader(String name, String phone, float date, float wage) {
        super(name, phone, date, wage);
        this.wage = 300;
    }

    public Leader() {
        super();
        this.wage = 300;
    }

    public int getNumOfMember() {
        return numOfMember;
    }

    public void setNumOfMember(int numOfMember) {
        this.numOfMember = numOfMember;
    }

    @Override
    public float calcSalary() {
        return super.calcSalary() + numOfMember * 100;

    }

    @Override
    public String getPosition() {
        return "Trưởng phòng";
    }

    @Override
    public String getMore() {
        return String.valueOf(this.numOfMember);
    }

    @Override
    public void removeMore() {
        numOfMember--;
    }

    public void addMember() {
        numOfMember++;
    }
}
