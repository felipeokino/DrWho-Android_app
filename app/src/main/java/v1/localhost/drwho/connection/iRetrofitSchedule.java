package v1.localhost.drwho.connection;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.POST;
import v1.localhost.drwho.classes.ScheduleResponse;
import v1.localhost.drwho.models.AppointmentSchedule;

/**
 * Created by felipe on 04/11/17.
 */

public interface iRetrofitSchedule {

    String url = "http://10.115.73.40:8080/v1/";


    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build();


}
