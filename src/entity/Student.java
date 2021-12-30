package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Student implements SuperEntity {
    @Id
    private String regNumber;
    private String name;
    private int age;
    private String contactNumber;
    private String address;
    private String dob;
    private String email;
    private String nic;
    private String gender;

    public List<Programme> getProgrammeList() {
        return programmeList;
    }

    public Student(String regNumber, String name, int age, String contactNumber, String address, String dob, String email, String nic, String gender, List<Programme> programmeList) {
        this.regNumber = regNumber;
        this.name = name;
        this.age = age;
        this.contactNumber = contactNumber;
        this.address = address;
        this.dob = dob;
        this.email = email;
        this.nic = nic;
        this.gender = gender;
        this.programmeList = programmeList;
    }

    public void setProgrammeList(List<Programme> programmeList) {
        this.programmeList = programmeList;
    }

    @ManyToMany
    private List<Programme> programmeList = new ArrayList<>();

    public Student() {
    }

    public Student(String regNumber, String name, int age, String contactNumber, String address, String dob, String email, String nic, String gender) {
        this.regNumber = regNumber;
        this.name = name;
        this.age = age;
        this.contactNumber = contactNumber;
        this.address = address;
        this.dob = dob;
        this.email = email;
        this.nic = nic;
        this.gender = gender;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Student{" +
                "regNumber='" + regNumber + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", contactNumber='" + contactNumber + '\'' +
                ", address='" + address + '\'' +
                ", dob='" + dob + '\'' +
                ", email='" + email + '\'' +
                ", nic='" + nic + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
