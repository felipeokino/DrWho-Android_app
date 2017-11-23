package v1.localhost.drwho.connection;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import v1.localhost.drwho.models.AppointmentBook;

/**
 * Created by felipe on 03/11/17.
 */

public interface iRetrofitAppointmentBook {


    String url = "http://10.42.3.43:8080/v1/";



     Retrofit retrofit = new Retrofit.Builder()
    .baseUrl(url)
    .addConverterFactory(GsonConverterFactory.create())
    .build();
}