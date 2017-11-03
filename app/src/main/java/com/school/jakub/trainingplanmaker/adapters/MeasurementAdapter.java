package com.school.jakub.trainingplanmaker.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.school.jakub.trainingplanmaker.R;
import com.school.jakub.trainingplanmaker.controller.measurement.ManageMeasurementsActivity;
import com.school.jakub.trainingplanmaker.controller.measurement.ProfileMeasurementActivity;
import com.school.jakub.trainingplanmaker.model.Measurement;
import com.school.jakub.trainingplanmaker.services.MeasurementService;

import java.util.Calendar;

/**
 * Created by Jakub on 03-Nov-17.
 */

public class MeasurementAdapter extends ArrayAdapter<Measurement> {

    ImageView imageEdit;
    ImageView imageDelete;

    TextView bicepsLeft;
    TextView bicepsRight;
    TextView thighLeft;
    TextView thighRight;
    TextView weight;
    TextView waist;
    TextView chest;
    TextView date;

    MeasurementService service;

    Context context;

    public MeasurementAdapter(@NonNull Context context, MeasurementService service) {
        super(context, R.layout.measurement_adapter_row, service.getAllMeasurement());
        this.context = context;
        this.service = service;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.measurement_adapter_row, parent, false);
        initialize(customView);

        final Measurement measurement = getItem(position);

        bicepsLeft.setText("Biceps(l): " + measurement.getBicepsLeft() + " cm");
        bicepsRight.setText("Biceps(p): " + measurement.getBicepsRight() + " cm");
        thighLeft.setText("Udo(l): " + measurement.getThighLeft() + " cm");
        thighRight.setText("Udo(p): " + measurement.getThighRight() + " cm");
        waist.setText("Talia: " + measurement.getWaist() + " cm");
        chest.setText("Pierś: " + measurement.getChest() + " cm");
        weight.setText("Waga: " + measurement.getWeight() + " kg");


        Calendar cal = Calendar.getInstance();
        cal.setTime(measurement.getDate());

        date.setText(cal.get(Calendar.DAY_OF_MONTH)+ "." + cal.get(Calendar.MONTH) + "."+  cal.get(Calendar.YEAR));

        imageEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProfileMeasurementActivity.class);
                Calendar cal = Calendar.getInstance();
                cal.setTime(measurement.getDate());

                intent.putExtra("year", cal.get(Calendar.YEAR));
                intent.putExtra("month", cal.get(Calendar.MONTH));
                intent.putExtra("day", cal.get(Calendar.DAY_OF_MONTH));
                intent.putExtra("bicepsLeft", measurement.getBicepsLeft());
                intent.putExtra("bicepsRight", measurement.getBicepsRight());
                intent.putExtra("chest", measurement.getChest());
                intent.putExtra("waist", measurement.getWaist());
                intent.putExtra("thighLeft", measurement.getThighLeft());
                intent.putExtra("thighRight", measurement.getThighRight());
                intent.putExtra("weight", measurement.getWeight());
               context.startActivity(intent);
                ((ManageMeasurementsActivity)context).finish();
            }
        });

        imageDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                remove(measurement);
                notifyDataSetChanged();
                service.removeMeasurement(measurement);
//                if(position!=0){
//                    ((ManageMeasurementsActivity)context).setSelection(position);
//                }
            }
        });


        return customView;
    }

    private void initialize(View c) {
        imageEdit = (ImageView) c.findViewById(R.id.measurement_adapter_row_image_edit);
        imageDelete = (ImageView) c.findViewById(R.id.measurement_adapter_row_image_remove);

        bicepsLeft = (TextView) c.findViewById(R.id.measurement_adapter_row_biceps_left);
        bicepsRight = (TextView) c.findViewById(R.id.measurement_adapter_row_biceps_right);
        thighLeft = (TextView) c.findViewById(R.id.measurement_adapter_row_thigh_left);
        thighRight = (TextView) c.findViewById(R.id.measurement_adapter_row_thigh_right);
        weight = (TextView) c.findViewById(R.id.measurement_adapter_row_wieght);
        waist = (TextView) c.findViewById(R.id.measurement_adapter_row_waist);
        chest = (TextView) c.findViewById(R.id.measurement_adapter_row_chest);
        date = (TextView) c.findViewById(R.id.measurement_adapter_row_date);
    }
}
