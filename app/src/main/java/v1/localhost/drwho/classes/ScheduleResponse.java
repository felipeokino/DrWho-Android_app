package v1.localhost.drwho.classes;

import java.util.ArrayList;

import v1.localhost.drwho.models.AppointmentSchedule;

/**
 * Created by felipe on 10/11/17.
 */

public class ScheduleResponse {
    private ArrayList<AppointmentSchedule> content;

    public ArrayList<AppointmentSchedule> getResults() {
        return content;
    }

    public void setResults(ArrayList<AppointmentSchedule> results) {
        this.content = results;
    }

    public int getSize(ArrayList<AppointmentSchedule> results){
        return results.size();
    }
}
