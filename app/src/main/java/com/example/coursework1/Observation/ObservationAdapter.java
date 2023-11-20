package com.example.coursework1.Observation;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Button;

import com.example.coursework1.R;

import java.util.ArrayList;

public class ObservationAdapter extends RecyclerView.Adapter<ObservationAdapter.DetailViewHolder>{

    private Context context;
    private ArrayList<ObservationModel> model2s;

    public ObservationAdapter(Context context, ArrayList<ObservationModel> model2s) {
        this.model2s = model2s;
        this.context = context;
    }


    @NonNull
    @Override
    public DetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_observation_adapter, parent, false);
        return new ObservationAdapter.DetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailViewHolder holder, int position) {
        ObservationModel detailModel2 = model2s.get(position);
        if(detailModel2 == null){
            return;
        }
        holder.comment.setText(detailModel2.getComment());
        holder.observation.setText(detailModel2.getObservation());
        holder.editTime.setText(detailModel2.getEditTime());

    }
    @Override
    public int getItemCount() {
        return model2s.size();
    }

    class DetailViewHolder extends RecyclerView.ViewHolder{
        private TextView observation, editTime, comment;
        private Button btnPrevious;

        public DetailViewHolder(@NonNull View itemView){
            super(itemView);
            comment = itemView.findViewById(R.id.comment);
            observation = itemView.findViewById(R.id.observation);
            editTime = itemView.findViewById(R.id.editTime);


        }
    }
}