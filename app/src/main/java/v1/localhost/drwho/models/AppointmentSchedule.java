package v1.localhost.drwho.models;

import java.util.Date;

/**
 * Created by felipe on 04/11/17.
 */

public class AppointmentSchedule {
    private long id;

    private Client client;
    private Doctor doctor;
    private Date dateSchedule;
    private Date startTimeScheduled;
    private Date endTimeScheduled;

    public AppointmentSchedule(long id, Client client, Doctor doctor, Date dateSchedule,
                               Date startTimeScheduled, Date endTimeScheduled, boolean isDeleted) {

        this.id = id;
        this.client = client;
        this.doctor = doctor;
        this.dateSchedule = dateSchedule;
        this.startTimeScheduled = startTimeScheduled;
        this.endTimeScheduled = endTimeScheduled;
        this.isDeleted = isDeleted;
    }

    private boolean isDeleted;

    public AppointmentSchedule() {
    }

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

    public Date getDateSchedule() {
        return dateSchedule;
    }

    public void setDateSchedule(Date dateSchedule) {
        this.dateSchedule = dateSchedule;
    }

    public Date getStartTimeScheduled() {
        return startTimeScheduled;
    }

    public void setStartTimeScheduled(Date startTimeScheduled) {
        this.startTimeScheduled = startTimeScheduled;
    }

    public Date getEndTimeScheduled() {
        return endTimeScheduled;
    }

    public void setEndTimeScheduled(Date endTimeScheduled) {
        this.endTimeScheduled = endTimeScheduled;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }



}
