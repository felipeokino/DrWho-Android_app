package v1.localhost.drwho.connection;

import retrofit2.Call;
import retrofit2.http.POST;
import v1.localhost.drwho.models.AppointmentSchedule;

/**
 * Created by felipe on 04/11/17.
 */

public interface iRetrofitSchedule {
    @POST("AppointmentSchedule/create")
    Call <AppointmentSchedule> MyAppointments();


}
