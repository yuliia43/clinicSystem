package dtos;

/**
 * @author Yullia Shcherbakova
 * @project final
 */
public class ClinicStaffWithPasswords {
    private int id;
    private String surname;
    private String name;
    private String title;
    private String email;
    private String password;

    /**
     * @param id
     * @param surname
     * @param name
     * @param title
     * @param email
     * @param password
     */
    public ClinicStaffWithPasswords(int id, String surname, String name, String title, String email, String password) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.title = title;
        this.email = email;
        this.password = password;
    }

    /**
     *
     */
    public ClinicStaffWithPasswords() {
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
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
