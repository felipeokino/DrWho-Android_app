package v1.localhost.drwho.adapter.scheduleAdapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import v1.localhost.drwho.R;
import v1.localhost.drwho.connection.iRetrofit;
import v1.localhost.drwho.login.SingletonUser;
import v1.localhost.drwho.models.AppointmentSchedule;
import v1.localhost.drwho.models.Client;

/**
 * Created by felipe on 09/11/17.
 */

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.MyViewHolder> {
    private ArrayList<AppointmentSchedule> appointmentSchedules;
    private Context context;
    Client client;

    public ScheduleAdapter(ArrayList<AppointmentSchedule> appointmentSchedules, Context context) {
        this.appointmentSchedules = appointmentSchedules;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_schedule,
                        parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final AppointmentSchedule appointmentSchedule = appointmentSchedules.get(position);

        holder.txtName.setText("Paciente: " + appointmentSchedule.getClient().getName());
        holder.txtDay.setText("Dia: " + appointmentSchedule.getDateSchedule());
        holder.txtDoctorName.setText("Doutor: " + appointmentSchedule.getDoctor().getName());
        holder.txtSpecs.setText("Especialidade: " + appointmentSchedule.getDoctor().getSpecialization());
        holder.txtHour.setText("Hora: " + appointmentSchedule.getStartTimeScheduled());

        if (!appointmentSchedule.isDeleted())
            holder.cancel.setVisibility(View.INVISIBLE);
        else
            holder.cancel.setVisibility(View.VISIBLE);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                UpdateObject(appointmentSchedule);
                holder.cancel.setVisibility(View.VISIBLE);
                return true;
            }
        });
    }

    public void SearchClientById(long id) {
        iRetrofit clientById = iRetrofit.retrofit.create(iRetrofit.class);
        Call<Client> call = clientById.getClientById(id);
        call.enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                try {
                    if (response.code() == 200) {
                        client = response.body();
                    } else {
                        Toast.makeText(context, "Error" + response.code() + response.message(), Toast.LENGTH_LONG).show();
                    }

                } catch (Exception e) {
                    Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return appointmentSchedules.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtName;
        TextView txtSpecs;
        TextView txtDoctorName;
        TextView txtDay;
        TextView txtHour;
        TextView _space;
        CardView linear;
        TextView cancel;

        public MyViewHolder(View itemView) {
            super(itemView);

            linear = (CardView) itemView.findViewById(R.id.parent);
            txtName = (TextView) itemView.findViewById(R.id.txtName);
            txtDoctorName = (TextView) itemView.findViewById(R.id.txtDoctorName);
            txtDay = (TextView) itemView.findViewById(R.id.txtDay);
            txtSpecs = (TextView) itemView.findViewById(R.id.txtSpecs);
            txtHour = (TextView) itemView.findViewById(R.id.txtItemHour);
            _space = (TextView) itemView.findViewById(R.id.txtSpace);
            cancel = (TextView) itemView.findViewById(R.id.txtCancelada);
        }
    }

    public List<AppointmentSchedule> getNoteList() {
        return appointmentSchedules;
    }

    public void setNoteList(List<AppointmentSchedule> noteList) {
        this.appointmentSchedules.clear();
        this.appointmentSchedules.addAll(noteList);
        notifyDataSetChanged();
    }

    public void UpdateObject(AppointmentSchedule appointmentSchedule) {
        try {
            appointmentSchedule.setDeleted(true);
            iRetrofit update = iRetrofit.retrofit.create(iRetrofit.class);
            Call<Void> call = update.updateAppointment(appointmentSchedule);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.code() == 204) {
                        Toast.makeText(context, "Consulta cancelada", Toast.LENGTH_LONG).show();
                        notifyDataSetChanged();
                    } else {
                        Toast.makeText(context, "Fatal Error: " + response.message(), Toast.LENGTH_LONG).show();

                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {

                }
            });
        } catch (Exception e) {
            Toast.makeText(context, "Fatal Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
