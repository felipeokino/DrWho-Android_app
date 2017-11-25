package v1.localhost.drwho.models;

import android.os.Parcel;

import java.io.Serializable;

/**
 * Created by felipe on 04/11/17.
 */

public class AppointmentSchedule implements Serializable {

    private final  static long serialVersionUID = 1L;
    private long id;
    private long id_client;
    private Client client;
    private Doctor doctor;
    private String dateSchedule;
    private String startTimeScheduled;
    private String endTimeScheduled;
    private boolean isDeleted;

    public AppointmentSchedule(Client client, Doctor doctor, String dateSchedule,
                               String startTimeScheduled, String endTimeScheduled, boolean isDeleted) {

        this.client = client;
        this.doctor = doctor;
        this.dateSchedule = dateSchedule;
        this.startTimeScheduled = startTimeScheduled;
        this.endTimeScheduled = endTimeScheduled;
        this.isDeleted = isDeleted;
    }
    public AppointmentSchedule(long id_client, Doctor doctor, String dateSchedule,
                               String startTimeScheduled, String endTimeScheduled, boolean isDeleted) {

        this.id_client = id_client;
        this.doctor = doctor;
        this.dateSchedule = dateSchedule;
        this.startTimeScheduled = startTimeScheduled;
        this.endTimeScheduled = endTimeScheduled;
        this.isDeleted = isDeleted;
    }

    public AppointmentSchedule(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getDateSchedule() {
        return dateSchedule;
    }

    public void setDateSchedule(String dateSchedule) {
        this.dateSchedule = dateSchedule;
    }

    public String getStartTimeScheduled() {
        return startTimeScheduled;
    }

    public void setStartTimeScheduled(String startTimeScheduled) {
        this.startTimeScheduled = startTimeScheduled;
    }

    public String getEndTimeScheduled() {
        return endTimeScheduled;
    }

    public void setEndTimeScheduled(String endTimeScheduled) {
        this.endTimeScheduled = endTimeScheduled;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public long getId_client() {
        return id_client;
    }

    public void setId_client(long id_client) {
        this.id_client = id_client;
    }

}
