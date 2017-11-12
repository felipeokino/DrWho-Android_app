package v1.localhost.drwho.models;

/**
 * Created by felipe on 22/10/17.
 */

public class Doctor{
    private long id;
    private String email;
    private String name;
    private String cpf;
    private String crm;
    private String address;
    private String phoneNumber;
    private String specialization;
    private AppointmentBook appointmentBook;
    private boolean isDeleted;


    public Doctor() {
    }

    public Doctor(String email, String name, String cpf,
                  String crm, String address, String phoneNumber, String specialization, AppointmentBook appointmentBook, boolean isDeleted) {
        this.email = email;
        this.name = name;
        this.cpf = cpf;
        this.crm = crm;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.specialization = specialization;
        this.appointmentBook = appointmentBook;
        this.isDeleted = isDeleted;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public AppointmentBook getAppointmentBook() {
        return appointmentBook;
    }

    public void setAppointmentBook(AppointmentBook appointmentBook) {
        this.appointmentBook = appointmentBook;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}
