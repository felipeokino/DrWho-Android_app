package v1.localhost.drwho.connection;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import v1.localhost.drwho.utils.BookResponse;
import v1.localhost.drwho.utils.DoctorResponse;
import v1.localhost.drwho.utils.ScheduleResponse;
import v1.localhost.drwho.models.AppointmentBook;
import v1.localhost.drwho.models.AppointmentSchedule;
import v1.localhost.drwho.models.Client;
import v1.localhost.drwho.models.Doctor;

/**
 * Created by Felipe on 19/10/2017.
 */

public interface iRetrofit {

    String url = "http://192.168.1.40:8080/v1/";
    //String url = "http://200.136.203.180:8080/v1/";
    //String url = "http://186.219.90.133:8080/v1/";


    //----------------------------------------------------------------------------------------------

    //Request Client
        @POST("client/create")
        Call<Client> addClient (@Body Client client);

        @GET("client/retrieveAllClients")
        Call<List<Client>> getUsers ();

        @GET("client/retrieveById?")
        Call<Client> getClientById(@Query("id") long id);

        @GET("client/retrieveByCpf?")
        Call<Client> getClientByCpf(@Query("cpf") String cpf);

    //----------------------------------------------------------------------------------------------


    //Request Doctors

        @POST("doctor/create")
        Call<Doctor> addDoctors(@Body Doctor doctor);

    /*    @GET("doctor/retrieveById?id={id}")
        Call<Doctor> getById(@Path("id") long id);*/

        @GET("doctor/retrieveAllDoctors?page=0&size=20")
        Call <DoctorResponse> getAllDoctors();

        @GET("doctor/retrieveBySpecialization?")
        Call <DoctorResponse> getBySpecialization(@Query("specialization") String specialization, @Query("page")int page, @Query("size") int size);

        @GET("doctor/retrieveByCpf?")
        Call<Doctor> getDoctorByCpf(@Query("cpf") String cpf);


    //----------------------------------------------------------------------------------------------


    //Appointment Schedule

    @POST("appointmentSchedule/create")
    Call <AppointmentSchedule> createAppointment(@Body AppointmentSchedule appointmentSchedule);

    @PUT("appointmentSchedule/update")
    Call<Void> updateAppointment(@Body AppointmentSchedule appointmentSchedule);

    @GET("appointmentSchedule/retrieveAllAppointmentSchedules?page=0&&size=20")
    Call<ScheduleResponse> GetAllSchedules();

    @GET("appointmentSchedule/retrieveByUserId?")
    Call<ScheduleResponse> GetByClient(@Query("id") long id, @Query("page") long page, @Query("size") long size);

    @GET("appointmentSchedule/retrieveByDoctorId?")
    Call<ScheduleResponse> GetByDoctor(@Query("id") long id, @Query("page") long page, @Query("size") long size);

    //----------------------------------------------------------------------------------------------


    //Appointment Book

    @POST("appointmentBook/create")
    Call<AppointmentBook> addAppointment(@Body AppointmentBook appointmentBook);

    @GET("appointmentBook/retrieveById?")
    Call<AppointmentBook> getById(@Query("id") long id);

    @GET("appointmentBook/retrieveAllAppointmentBooks?page=0&size=20")
    Call<BookResponse> getBooks();


    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

}
