package com.example.coursework1.HikingMoutain;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Button;
import com.example.coursework1.DatabaseHelper;
import com.example.coursework1.Observation.MainActivity2;
import com.example.coursework1.Observation.ObservationDetail;
import com.example.coursework1.R;

import java.util.ArrayList;

public class HikingAdapter extends RecyclerView.Adapter<HikingAdapter.DetailViewHolder> {
    private Context context;
    private ArrayList <HikingModel> models;
    private ArrayList <HikingModel> filteredList;

    public HikingAdapter() {
    }

    public HikingAdapter(Context context, ArrayList<HikingModel> models) {
        this.context = context;
        this.models = models;
        this.filteredList = new ArrayList<>(models);
    }

    @NonNull
    @Override
    public DetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_hiking_adapter, parent, false);
        return new DetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailViewHolder holder, int position) {
        HikingModel detailModel = models.get(position);
        if (detailModel == null) {
            return;
        }
        holder.name.setText(detailModel.getName());
        holder.elevation.setText(detailModel.getElevation());
        holder.distance.setText(detailModel.getDistance());
        holder.parking_available.check(detailModel.getParking_availableSelected());
        holder.td.check(detailModel.getTdSelected());
        holder.level.check(detailModel.getLevelSelected());
        holder.date.setText(detailModel.getDate());
        holder.location.setText(detailModel.getLocation());
        holder.description.setText(detailModel.getDescription());

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int adapterPosition = holder.getAdapterPosition();
                HikingModel model = models.get(adapterPosition);
                Intent edit = new Intent(context, EditActivity.class);
                edit.putExtra("NAME", model.getName());
                edit.putExtra("ELEVATION", model.getElevation());
                edit.putExtra("DISTANCE", model.getDistance());
                edit.putExtra("PARKING_AVAILABLE", model.getParking_availableSelected());
                edit.putExtra("TD", model.getTdSelected());
                edit.putExtra("LEVEL", model.getLevelSelected());
                edit.putExtra("DATE", model.getDate());
                edit.putExtra("LOCATION", model.getLocation());
                edit.putExtra("DESCRIPTION", model.getDescription());
                context.startActivity(edit);
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int adapterPosition = holder.getAdapterPosition();
                HikingModel model = models.get(adapterPosition);
                DatabaseHelper database= new DatabaseHelper(context);
                new AlertDialog.Builder(context)
                        .setTitle("Confirm Delete")
                        .setMessage("You delete this climbing diary, are you sure?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                int trip_id = detailModel.getId();
                                database.deleteDetail(trip_id);
                                models.remove(adapterPosition);
                                notifyItemRemoved(adapterPosition);

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .show();
            }
        });
        holder.add_observations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addObservation();
            }
        });

        holder.view_observation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewObservation();
            }
        });

    }
    private void addObservation(){
        Intent m = new Intent(context, MainActivity2.class);
        context.startActivity(m);
    }

    private void viewObservation(){
        Intent v = new Intent(context, ObservationDetail.class);
        context.startActivity(v);
    }

    public void filterList(String text) {
        text = text.toLowerCase();
        models.clear();
        if (text.length() == 0) {
            models.addAll(filteredList);
        } else {
            for (HikingModel model : filteredList) {
                if (model.getName().toLowerCase().contains(text)) {
                    models.add(model);
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return models.size() ;
    }

    class DetailViewHolder extends RecyclerView.ViewHolder{
        private TextView name, elevation , distance, date, location, description;
        private RadioGroup parking_available, td, level;
        private Button edit, delete, add_observations, view_observation;

        public DetailViewHolder(@NonNull View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.name);
            elevation = itemView.findViewById(R.id.elevation);
            distance = itemView.findViewById(R.id.distance);
            parking_available = itemView.findViewById(R.id.radioGroup);
            td = itemView.findViewById(R.id.radioGroup4);
            level = itemView.findViewById(R.id.radioGroup2);
            date = itemView.findViewById(R.id.date);
            location = itemView.findViewById(R.id.location);
            description = itemView.findViewById(R.id.description);
            edit = itemView.findViewById(R.id.edit);
            delete = itemView.findViewById(R.id.delete);
            add_observations = itemView.findViewById(R.id.addObservations);
            view_observation = itemView.findViewById(R.id.buttonViewObservations);

        }
    }

}


