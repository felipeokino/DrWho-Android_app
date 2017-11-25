package v1.localhost.drwho.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import v1.localhost.drwho.R;
import v1.localhost.drwho.models.AppointmentSchedule;

public class MyAppointments extends AppCompatActivity {
    TextView client;
    TextView clientName;
    TextView doc;
    TextView docName;
    TextView date;
    TextView dateAp;
    TextView hour;
    TextView hourAp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_appointments);

        client = (TextView) findViewById(R.id.txtClientName);
        clientName = (TextView) findViewById(R.id.txtClientAp);
        doc = (TextView) findViewById(R.id.txtDoctorName);
        docName = (TextView) findViewById(R.id.txtDocAp);
        date = (TextView) findViewById(R.id.txtDate);
        dateAp = (TextView) findViewById(R.id.txtDateAp);
        hour = (TextView) findViewById(R.id.txtHour);
        hourAp = (TextView) findViewById(R.id.txtHourAp);

        AppointmentSchedule appointmentSchedule;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            appointmentSchedule = (AppointmentSchedule) extras.get("extra");
            Log.d("Error: ", appointmentSchedule.getClient().getName());

        }else {
            appointmentSchedule = new AppointmentSchedule();
        }
    }

    private void bindParameters(AppointmentSchedule appointmentSchedule) {
        clientName.setText(appointmentSchedule.getClient().getName());
        docName.setText(appointmentSchedule.getDoctor().getName());
        dateAp.setText(appointmentSchedule.getDateSchedule());
        hourAp.setText(appointmentSchedule.getStartTimeScheduled());
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }
}