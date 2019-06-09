package models;


import java.time.Instant;
import java.util.Date;
import java.util.List;

public class PatientCard {
    private int id;
    private String surname;
    private String name;
    private char sex;
    private Date birthday_date;
    private List<Diagnosis> diagnosis;


    public PatientCard(int id, String surname, String name, char sex, Date birthday_date) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.sex = sex;
        this.birthday_date = birthday_date;
    }

    public PatientCard(String surname, String name, char sex, Date birthday_date) {
        this.surname = surname;
        this.name = name;
        this.sex = sex;
        this.birthday_date = birthday_date;
    }

    public PatientCard() {
        birthday_date = Date.from(Instant.now());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public Date getBirthday_date() {
        return birthday_date;
    }

    public void setBirthday_date(Date birthday_date) {
        this.birthday_date = birthday_date;
    }

    public List<Diagnosis> getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(List<Diagnosis> diagnosis) {
        this.diagnosis = diagnosis;
    }

    @Override
    public String toString() {
        return "PatientCard{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", birthday_date=" + birthday_date +
                ", diagnosis=" + diagnosis +
                '}';
    }
}
