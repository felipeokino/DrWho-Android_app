package v1.localhost.drwho.utils;

import java.util.ArrayList;

import v1.localhost.drwho.models.AppointmentBook;

/**
 * Created by felipe on 25/11/17.
 */

public class BookResponse {
    ArrayList<AppointmentBook> content;

    public ArrayList<AppointmentBook> getResults() {
        return content;
    }

    public void setResults(ArrayList<AppointmentBook> results) {
        this.content = results;
    }

    public int getSize(ArrayList<AppointmentBook> results){
        return results.size();
    }

    public int getLastId(ArrayList<AppointmentBook> results){
        return results.lastIndexOf(results);

    }
}
