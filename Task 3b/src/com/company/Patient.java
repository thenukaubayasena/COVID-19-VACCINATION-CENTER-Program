package com.company;

public class Patient extends Booth{
    private String firstName ;
    private String surname ;
    private int age ;
    private String city ;
    private long nicOrPassport ;
    private String vaccineRequested ;

    public Patient(String firstNameRef,String surnameRef,int ageRef,String cityRef,long nicOrPassportRef,String vaccineRequestedRef) {

        setFirstName(firstNameRef);
        setSurname(surnameRef);
        setAge(ageRef);
        setCity(cityRef);
        setNicOrPassport(nicOrPassportRef);
        setVaccineRequested(vaccineRequestedRef);
    }

    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }
    public void setSurname(String surname) {

        this.surname = surname;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public void setNicOrPassport(long nicOrPassport) {
        this.nicOrPassport = nicOrPassport;
    }
    public void setVaccineRequested(String vaccineRequested) {
        this.vaccineRequested = vaccineRequested;
    }



    public String getFirstName() {

        return firstName;
    }
    public String getSurname() {

        return surname;
    }
    public int getAge() {
        return age;
    }
    public String getCity() {
        return city;
    }
    public long getNicOrPassport() {
        return nicOrPassport;
    }
    public String getVaccineRequested() {
        return vaccineRequested;
    }
}
