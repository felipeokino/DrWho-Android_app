package v1.localhost.drwho.adapter.appointmentScheduleAdapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import v1.localhost.drwho.R;
import v1.localhost.drwho.activity.InfoSchedule;
import v1.localhost.drwho.activity.ScheduleActivity;
import v1.localhost.drwho.models.AppointmentSchedule;

/**
 * Created by felipe on 09/11/17.
 */

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.MyViewHolder> {
    private List<AppointmentSchedule> appointmentSchedules;
    private Context context;

    public ScheduleAdapter(ArrayList<AppointmentSchedule> appointmentSchedules, Context context) {
        this.appointmentSchedules = new ArrayList<>();
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

        //holder.txtName.setText(appointmentSchedule.getClient().getName());
        holder.txtDay.setText(appointmentSchedule.getDateSchedule());
        holder.txtDoctorName.setText(appointmentSchedule.getDoctor().getName());
        holder.txtSpecs.setText(appointmentSchedule.getDoctor().getSpecialization());

        holder.linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO open appointmentSchedule;
                Intent intent = new Intent(context, ScheduleActivity.class);
                intent.putExtra("appointmentScheduleExtra", InfoSchedule.class);
                context.startActivity(intent);
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
        CardView linear;

        public MyViewHolder(View itemView) {
            super(itemView);

            linear = (CardView) itemView.findViewById(R.id.parent);
            txtName = (TextView) itemView.findViewById(R.id.txtName);
            txtDoctorName = (TextView) itemView.findViewById(R.id.txtDoctorName);
            txtDay = (TextView) itemView.findViewById(R.id.txtDay);
            txtSpecs = (TextView) itemView.findViewById(R.id.txtSpecs);
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
}
