package com.example.coursework1.Observation;

public class ObservationModel {

    private String observation, editTime, comment;
    public ObservationModel(){

    }
    public String getComment(){
        return comment;
    }
    public void setComment(String comment){
        this.comment = comment;
    }

    public String getObservation(){
        return observation;
    }
    public void setObservation(String observation){
        this.observation = observation;
    }

    public String getEditTime(){
        return editTime;
    }
    public void setEditTime(String editTime){
        this.editTime = editTime;
    }



}
