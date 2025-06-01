package com.company;
//
public class Booth extends VaccinationCenter{

    private String patientName ;

    public Booth() {

        super();
    }

    public Booth(String patientNameRef) {

        super();
        setPatientName(patientNameRef);
    }

    public void setPatientName(String patientName) {

        this.patientName = patientName;
    }

    public String getPatientName() {

        return patientName;
    }
}
