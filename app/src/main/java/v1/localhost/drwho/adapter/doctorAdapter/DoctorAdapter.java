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
import java.util.List;

import v1.localhost.drwho.R;
import v1.localhost.drwho.activity.CreateDoctor;
import v1.localhost.drwho.activity.SearchDoctor;
import v1.localhost.drwho.models.Doctor;

/**
 * Created by felipe on 09/11/17.
 */

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.MyViewHolder> {
    private List<Doctor> doctors;
            Context context;
    private Calendar calendar;

    public DoctorAdapter(ArrayList<Doctor> _doctors, Context context) {
        this.doctors = _doctors;
        this.context = context;
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
        final Doctor doctor = doctors.get(position);

        holder.txtName.setText(doctor.getName());
        holder.txtSpecs.setText(doctor.getSpecialization());

        holder.linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent = new Intent(context, CreateDoctor.class);
                //intent.putExtra("doctorExtra", CreateDoctor.class);
                context.startActivity(intent);*/
                Toast.makeText(context, "Nao funciona otario", Toast.LENGTH_LONG).show();

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
