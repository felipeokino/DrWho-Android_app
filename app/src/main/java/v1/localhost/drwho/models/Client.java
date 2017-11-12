package v1.localhost.drwho.models;

/**
 * Created by Felipe on 19/10/2017.
 */

public class Client{

    private long id;
    private String email;
    private String name;
    private String cpf;
    private String address;
    private String phoneNumber;
    private boolean isDeleted;

    public Client() {
    }

    public Client(String email, String name, String cpf, String address, String phoneNumber, boolean isDeleted) {
        this.email = email;
        this.name = name;
        this.cpf = cpf;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.isDeleted = isDeleted;
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
}