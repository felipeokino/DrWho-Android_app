package v1.localhost.drwho.classes;

import java.util.ArrayList;

import v1.localhost.drwho.models.Doctor;

/**
 * Created by felipe on 10/11/17.
 */

public class DoctorResponse {
    private ArrayList<Doctor> content;

    public ArrayList<Doctor> getResults() {
        return content;
    }

    public void setResults(ArrayList<Doctor> results) {
        this.content = results;
    }
}
