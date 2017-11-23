package v1.localhost.drwho.models;

import java.util.Date;

/**
 * Created by felipe on 04/11/17.
 */

public class AppointmentSchedule {
    private long id;

    private long client;
    private Doctor doctor;
    private String dateSchedule;
    private String startTimeScheduled;
    private String endTimeScheduled;

    public AppointmentSchedule(long client, Doctor doctor, String dateSchedule,
                               String startTimeScheduled, String endTimeScheduled, boolean isDeleted) {

        this.client = client;
        this.doctor = doctor;
        this.dateSchedule = dateSchedule;
        this.startTimeScheduled = startTimeScheduled;
        this.endTimeScheduled = endTimeScheduled;
        this.isDeleted = isDeleted;
    }

    private boolean isDeleted;

    public AppointmentSchedule(long id, Doctor doctor, String date, Object startTimeScheduled, Object endTimeScheduled, boolean isDeleted) {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getClient() {
        return client;
    }

    public void setClient(long client) {
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



}
