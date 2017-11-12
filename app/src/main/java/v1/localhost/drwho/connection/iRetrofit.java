package v1.localhost.drwho.connection;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import v1.localhost.drwho.classes.DoctorResponse;
import v1.localhost.drwho.models.AppointmentBook;
import v1.localhost.drwho.models.AppointmentSchedule;
import v1.localhost.drwho.models.Client;
import v1.localhost.drwho.models.Doctor;

/**
 * Created by Felipe on 19/10/2017.
 */

public interface iRetrofit {

    //String url = "http://192.168.0.7:8080/v1/";
    //String url = "http://200.136.203.180:8080/v1/";
    String url = "http://192.168.1.40:8080/v1/";

        @POST("appointmentBook/create")
        Call<AppointmentBook> addAppointment(@Body AppointmentBook appointmentBook);

        @GET("appointmentBook/retrieveById?id={id}")
        Call<AppointmentBook> getById(@Path("id") long id);

        @GET("appointmentBook/retrieveAllAppointmentBooks?page=0&size=20")
        Call<List<AppointmentBook>> getAppointments();

    //----------------------------------------------------------------------------------------------

    //TODO Request Clients

        @POST("client/create")
        Call<Client> addClient (@Body Client client);

        @GET("client/retrieveAllClients")
        Call<List<Client>> getUsers ();

    //----------------------------------------------------------------------------------------------


    //TODO Request Doctors

        @POST("doctor/create")
        Call<Doctor> addDoctors(@Body Doctor doctor);

    /*    @GET("doctor/retrieveById?id={id}")
        Call<Doctor> getById(@Path("id") long id);*/

        @GET("doctor/retrieveAllDoctors?page=0&size=20")
        Call <DoctorResponse> getAllDoctors();

        @GET("doctor/retrieveBySpecialization?page=0&size=20")
        Call <DoctorResponse> getBySpecialization();


    //----------------------------------------------------------------------------------------------


    //TODO Appointment Schedule

    @POST("AppointmentSchedule/create")
    Call <AppointmentSchedule> MyAppointments();

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

}
