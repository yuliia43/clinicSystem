package models;


import java.time.Instant;
import java.util.Date;
import java.util.List;

/**
 * @author Yullia Shcherbakova
 * @project final
 */
public class PatientCard {
    private int id;
    private String surname;
    private String name;
    private char sex;
    private Date birthday_date;
    private List<Diagnosis> diagnosis;


    /**
     * @param id
     * @param surname
     * @param name
     * @param sex
     * @param birthday_date
     */
    public PatientCard(int id, String surname, String name, char sex, Date birthday_date) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.sex = sex;
        this.birthday_date = birthday_date;
    }

    /**
     * @param surname
     * @param name
     * @param sex
     * @param birthday_date
     */
    public PatientCard(String surname, String name, char sex, Date birthday_date) {
        this.surname = surname;
        this.name = name;
        this.sex = sex;
        this.birthday_date = birthday_date;
    }

    /**
     *
     */
    public PatientCard() {
        birthday_date = Date.from(Instant.now());
    }

    /**
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @param surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return
     */
    public char getSex() {
        return sex;
    }

    /**
     * @param sex
     */
    public void setSex(char sex) {
        this.sex = sex;
    }

    /**
     * @return
     */
    public Date getBirthday_date() {
        return birthday_date;
    }

    /**
     * @param birthday_date
     */
    public void setBirthday_date(Date birthday_date) {
        this.birthday_date = birthday_date;
    }

    /**
     * @return
     */
    public List<Diagnosis> getDiagnosis() {
        return diagnosis;
    }

    /**
     * @param diagnosis
     */
    public void setDiagnosis(List<Diagnosis> diagnosis) {
        this.diagnosis = diagnosis;
    }

    /**
     * @return
     */
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
