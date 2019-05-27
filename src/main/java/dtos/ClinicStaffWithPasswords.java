package dtos;

public class ClinicStaffWithPasswords {
    private int id;
    private String surname;
    private String name;
    private String title;
    private String email;
    private String password;

    public ClinicStaffWithPasswords(int id, String surname, String name, String title, String email, String password) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.title = title;
        this.email = email;
        this.password = password;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
