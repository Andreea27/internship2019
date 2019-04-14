package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class ATM extends Entity{
    private LocalTime openingtime;
    private LocalTime closingtime;

    public ATM(){}

    public ATM(String name, LocalTime openingtime, LocalTime closingtime) {
        super(name);
        this.openingtime = openingtime;
        this.closingtime = closingtime;
    }

    public String getName() {
        return super.getName();
    }

    public void setName(String name) {
        super.setName(name);
    }

    public LocalTime getOpeningtime() {
        return openingtime;
    }

    public void setOpeningtime(LocalTime openingtime) {
        this.openingtime = openingtime;
    }

    public LocalTime getClosingtime() {
        return closingtime;
    }

    public void setClosingtime(LocalTime closingtime) {
        this.closingtime = closingtime;
    }

    @Override
    public String toString() {
        return "model.ATM{" +
                "name='" + super.getName() + '\'' +
                ", openingtime=" + openingtime.toString()+
                ", closingtime=" + closingtime.toString()+
                '}';
    }
}

