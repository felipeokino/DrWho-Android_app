package v1.localhost.drwho.adapter.doctorAdapter;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import v1.localhost.drwho.R;
import v1.localhost.drwho.activity.CreateDoctor;
import v1.localhost.drwho.activity.MainActivity;
import v1.localhost.drwho.activity.SearchDoctor;
import v1.localhost.drwho.connection.iRetrofit;
import v1.localhost.drwho.login.SingletonUser;
import v1.localhost.drwho.models.AppointmentSchedule;
import v1.localhost.drwho.models.Client;
import v1.localhost.drwho.models.Doctor;

/**
 * Created by felipe on 09/11/17.
 */

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.MyViewHolder> {
    private List<Doctor> doctors;
            Context context;
    String date;
    String startHour, endHour;

    public DoctorAdapter(ArrayList<Doctor> _doctors, Context context, String date, String startHour, String endHour) {
        this.doctors = _doctors;
        this.context = context;
        this.date = date;
        this.startHour = startHour;
        this.endHour = endHour;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_doctor,
                        parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Client id = SingletonUser.getInstance().getUsuario();
        final Doctor doctor = doctors.get(position);
        final AppointmentSchedule appointmentSchedule = new AppointmentSchedule(id,  doctor, date, startHour, endHour, false);

        holder.txtName.setText(doctor.getName());
        holder.txtSpecs.setText(doctor.getSpecialization());

        holder.linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            try {
                iRetrofit createAppointment = iRetrofit.retrofit.create(iRetrofit.class);
                Call<AppointmentSchedule> call = createAppointment.createAppointment(appointmentSchedule);
                call.enqueue(new Callback<AppointmentSchedule>() {
                    @Override
                    public void onResponse(Call<AppointmentSchedule> call, Response<AppointmentSchedule> response) {
                        int codeDoctor = response.code();

                        if (codeDoctor == 201){
                            Toast.makeText(context, "Consulta marcada com sucesso ",
                                    Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(context, "Falha: " + String.valueOf(codeDoctor)+ " " + response.message(),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<AppointmentSchedule> call, Throwable t) {

                    }
                });
                }catch (Exception e){
                    Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return doctors.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtName;
        TextView txtSpecs;
        CardView linear;

        public MyViewHolder(View itemView) {
            super(itemView);

            linear = (CardView) itemView.findViewById(R.id.parent);
            txtName = (TextView) itemView.findViewById(R.id.txtName);
            txtSpecs = (TextView) itemView.findViewById(R.id.txtSpecs);
        }
    }
}
