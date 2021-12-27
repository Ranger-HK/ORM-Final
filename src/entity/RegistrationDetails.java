package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class RegistrationDetails implements SuperEntity{
    @Id
    private
    String regNumber;
    @ManyToOne
            @JoinColumn(name = "registerNumber",referencedColumnName = "regNumber")
    private
    String programmeID;
    private String joinDate;


    public RegistrationDetails() {
    }

    public RegistrationDetails(String regNumber, String programmeID, String joinDate) {
        this.regNumber = regNumber;
        this.programmeID = programmeID;
        this.joinDate = joinDate;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getProgrammeID() {
        return programmeID;
    }

    public void setProgrammeID(String programmeID) {
        this.programmeID = programmeID;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    @Override
    public String toString() {
        return "RegistrationDetails{" +
                "regNumber='" + regNumber + '\'' +
                ", programmeID='" + programmeID + '\'' +
                ", joinDate='" + joinDate + '\'' +
                '}';
    }
}
