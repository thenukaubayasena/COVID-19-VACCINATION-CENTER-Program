package com.company;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Queue;
import java.util.Scanner;

public class VaccinationCenter{
    private Booth[] booths;
    private int astraZenecaVaccines = 50;
    private int synopharmVaccines = 50;
    private int pfizerVaccines = 50;

    public VaccinationCenter(Booth [] booths){

        setVaccinationCenter(booths);
    }
    public VaccinationCenter() {

    }
    public void setVaccinationCenter(Booth[] boothRef) {

        this.booths = boothRef;
    }

    public void requestAdditionalDetails(int boothNumRef,Patient [] patientRef,String vaccineRequestedRef){
        Scanner input= new Scanner(System.in);
        int age;
        String firstName,surname,city;
        long nicOrPassport;
        System.out.println("Please enter the details of the patient");
        System.out.print("Enter the first name of the patient : ");
        firstName = input.next();
        System.out.print("Enter the surname of the patient : ");
        surname = input.next();
        System.out.print("Enter the age of the patient : ");
        age = input.nextInt();
        System.out.print("Enter the current city of the patient : ");
        city = input.next();
        System.out.print("Enter the NIC number/Passport number of the patient : ");
        nicOrPassport = input.nextLong();
        booths[boothNumRef].setPatientName(firstName);
        patientRef[boothNumRef] = new Patient(firstName,surname,age,city,nicOrPassport,vaccineRequestedRef);
        System.out.println("Patient added to the booth No."+boothNumRef+" successfully");
    }

    public void initialise(Booth[] boothRef) {

        for (int x = 0; x < 6 ; x++ ){
            boothRef[x].setPatientName("e");
        }
        setVaccinationCenter(boothRef);
    }
    public void addPatientToBooth(Patient [] patientRef,WaitingList AstraWaitingListRef,WaitingList SynoWaitingListRef,WaitingList PfizerWaitingListRef){
        if(this.astraZenecaVaccines<20) {
            System.out.println("\t****The remaining AstraZeneca vaccines are less than 20****");
        }else if(this.synopharmVaccines<20) {
            System.out.println("\t****The remaining AstraZeneca vaccines are less than 20****");
        }else if(this.pfizerVaccines<20) {
            System.out.println("\t****The remaining AstraZeneca vaccines are less than 20****");
        }

        String firstName,vaccineRequested;
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the name of the patient");
        firstName = input.next();
        System.out.print("Enter the preferred vaccination by the patient (AstraZeneca,Sinopharm,Pfizer): ");
        vaccineRequested = input.next();
        if(vaccineRequested.equalsIgnoreCase("AstraZeneca")){
            System.out.println("Only Booth 0 & 1 are reserved for \"AstraZeneca\" vaccinations.  ");
            if(booths[0].getPatientName().equals("e")){
                requestAdditionalDetails(0,patientRef,vaccineRequested);
                this.astraZenecaVaccines--;
            }else if(booths[1].getPatientName().equals("e")){
                requestAdditionalDetails(1,patientRef,vaccineRequested);
                this.astraZenecaVaccines--;
            }else{
                System.out.println("All \"AstraZeneca\" booths are occupied at the moment. Patient will be added to the relevant vaccination waiting list");
                AstraWaitingListRef.addToWaitingList(firstName);
            }
        }else if(vaccineRequested.equalsIgnoreCase("Sinopharm")){
            System.out.println("Booth 2 & 3 are reserved for \"Sinopharm\" vaccinations.  ");
            if(booths[2].getPatientName().equals("e")){
                requestAdditionalDetails(2,patientRef,vaccineRequested);
                this.synopharmVaccines--;
            }else if(booths[3].getPatientName().equals("e")){
                requestAdditionalDetails(3,patientRef,vaccineRequested);
                this.synopharmVaccines--;
            }else{
                System.out.println("All \"Sinopharm\" booths are occupied at the moment. Patient will be added to the relevant vaccination waiting list");
                SynoWaitingListRef.addToWaitingList(firstName);
            }
        }else if(vaccineRequested.equalsIgnoreCase("Pfizer")){
            System.out.println("Booth 4 - 6 are reserved for \"Pfizer\" vaccinations. ");
            if(booths[4].getPatientName().equals("e")){
                requestAdditionalDetails(4,patientRef,vaccineRequested);
                this.pfizerVaccines--;
            }else if(booths[5].getPatientName().equals("e")){
                requestAdditionalDetails(5,patientRef,vaccineRequested);
                this.pfizerVaccines--;
            }else{
                System.out.println("All \"Pfizer\" booths are occupied at the moment. Patient will be added to the relevant vaccination waiting list");
                PfizerWaitingListRef.addToWaitingList(firstName);
            }
        }else{
            System.out.println("Invalid Entry!");
        }
        setVaccinationCenter(booths);
    }
    public void deletePatient(int boothNumRef,Patient [] patientRef,WaitingList astraWaitingListRef,WaitingList synoWaitingListRef,WaitingList pfizerWaitingListRef){

        if(booths[boothNumRef].getPatientName().equals("e")){
            System.out.println("Booth no : "+boothNumRef+" is already empty");
        }
        else{
            if(boothNumRef==0 || boothNumRef==1) {
                if(astraWaitingListRef!=null){
                    String nextPatient = astraWaitingListRef.deleteFromWaitingList();
                    booths[boothNumRef].setPatientName(nextPatient);
                    System.out.println("Patient "+nextPatient+" was added to the booth "+boothNumRef+" from the waiting list");
                    requestAdditionalDetails(boothNumRef,patientRef,"AstraZeneca");
                    astraZenecaVaccines--;
                }else {
                    booths[boothNumRef].setPatientName("e");
                    patientRef[boothNumRef] = null;
                    System.out.println("Patient removed from the booth successfully!");
                }
            }else if(boothNumRef==2 || boothNumRef==3) {
                if(synoWaitingListRef!=null){
                    String nextPatient = synoWaitingListRef.deleteFromWaitingList();
                    booths[boothNumRef].setPatientName(nextPatient);
                    System.out.println("Patient "+nextPatient+" was added to the booth "+boothNumRef+" from the waiting list");
                    requestAdditionalDetails(boothNumRef,patientRef,"Synopharm");
                    synopharmVaccines--;
                }else {
                    booths[boothNumRef].setPatientName("e");
                    patientRef[boothNumRef] = null;
                    System.out.println("Patient removed from the booth successfully!");
                }
            }else if(boothNumRef==4 || boothNumRef==5) {
                if(pfizerWaitingListRef!=null){
                    String nextPatient = pfizerWaitingListRef.deleteFromWaitingList();
                    booths[boothNumRef].setPatientName(nextPatient);
                    System.out.println("Patient "+nextPatient+" was added to the booth "+boothNumRef+" from the waiting list");
                    requestAdditionalDetails(boothNumRef,patientRef,"Pfizer");
                    pfizerVaccines--;
                }else {
                    booths[boothNumRef].setPatientName("e");
                    patientRef[boothNumRef] = null;
                    System.out.println("Patient removed from the booth successfully!");
                }
            }
            else{
                System.out.println("Invalid Entry");
            }
        }
        setVaccinationCenter(booths);
    }

    public void viewEmptyBooths(){

        for (int x = 0; x < 6; x++) {
            if (booths[x].getPatientName().equals("e")) {
                System.out.println("Booth " + x + " is empty");
            }
        }
    }

    public void viewAllBooths(Patient[] patientRef){

        for (int x = 0; x < 6; x++ ) {
            if(booths[x].getPatientName().equals("e")){
                System.out.println("Booth " + x + " is empty");
            }else {
                for (int i = 0; i < 200; i++) {
                    System.out.print("-");
                }
                System.out.println();
                System.out.println("Booth " + x + " occupied by " + booths[x].getPatientName());
                System.out.println("Additional Details ("+
                        "  First Name of the patient : " + patientRef[x].getFirstName() + "," +
                        "  Surname of the patient : " + patientRef[x].getSurname() + "," +
                        "  Age of the patient : " + patientRef[x].getAge() + "," +
                        "  Current City of the patient : " + patientRef[x].getCity() + "," +
                        "  NIC/Passport number of the patient : " + patientRef[x].getNicOrPassport() + "," +
                        "  Preferred Vaccination by the patient : " + patientRef[x].getVaccineRequested()+ ")");
                for (int i = 0; i < 200; i++) {
                    System.out.print("-");
                }
                System.out.println();
            }
        }
    }
    public void storeProgramDataToFile(Booth[] boothRef,Patient [] patientRef) throws IOException,NullPointerException {

        File saveFile = new File("vaccineData.txt");
        PrintStream writer = new PrintStream(saveFile);
        int index = 0;
        while(index < boothRef.length){
            writer.println(boothRef[index].getPatientName());
            index++;
        }
        index = 0;
        while(index < patientRef.length){
            if(patientRef[index] != null){
                writer.println( patientRef[index].getFirstName());
                writer.println( patientRef[index].getSurname());
                writer.println( patientRef[index].getAge());
                writer.println( patientRef[index].getCity());
                writer.println( patientRef[index].getNicOrPassport());
                writer.println( patientRef[index].getVaccineRequested());
            }else{
                writer.println("null");
                writer.println("null");
                writer.println(0);
                writer.println("null");
                writer.println(0);
                writer.println("null");
            }
            index ++;
        }
        writer.println(this.astraZenecaVaccines);
        writer.println(this.synopharmVaccines);
        writer.println(this.pfizerVaccines);
        writer.close();
        System.out.println("Data has been added to the file successfully");
    }

    public Patient[] getDataFromFile(Booth[] boothRef, Patient[] patientRef) throws IOException{

        int n;
        int age;
        String firstName,surname,city,vaccineRequested;
        long nicOrPassport;
        String patientName;
        int index = 0;
        for(n=0;n<=5;n++){

            patientName = Files.readAllLines(Paths.get("vaccineData.txt")).get(n);
            boothRef[index] = new Booth(patientName);
            index++;
        }
        setVaccinationCenter(boothRef);
        index=0;
        for(n = 6;n<=41;n=n+6){

            firstName = Files.readAllLines(Paths.get("vaccineData.txt")).get(n);
            surname = Files.readAllLines(Paths.get("vaccineData.txt")).get(n+1);
            age = Integer.parseInt(Files.readAllLines(Paths.get("vaccineData.txt")).get(n+2));
            city = Files.readAllLines(Paths.get("vaccineData.txt")).get(n+3);
            nicOrPassport = Long.parseLong(Files.readAllLines(Paths.get("vaccineData.txt")).get(n+4));
            vaccineRequested = Files.readAllLines(Paths.get("vaccineData.txt")).get(n+5);
            patientRef[index] = new Patient(firstName,surname,age,city,nicOrPassport,vaccineRequested);
            index++;
        }
        this.astraZenecaVaccines=Integer.parseInt(Files.readAllLines(Paths.get("vaccineData.txt")).get(42));
        this.synopharmVaccines=Integer.parseInt(Files.readAllLines(Paths.get("vaccineData.txt")).get(43));
        this.pfizerVaccines=Integer.parseInt(Files.readAllLines(Paths.get("vaccineData.txt")).get(44));
        System.out.println("Data has been loaded from the file successfully");
        return patientRef;
    }



    public void orderedPatientList(){

        String [] boothRef = new String[booths.length];
        for(int k=0;k< boothRef.length;k++){
            boothRef[k] = booths[k].getPatientName();
        }
        int boothLength = boothRef.length;

        for (int x = 0; x < boothLength - 1; x++) {
            for (int i = 0; i <= boothLength - 2; i++) {
                if (boothRef[i].toLowerCase().compareTo(boothRef[i+1].toLowerCase())>0){
                    String temp = boothRef[i];
                    boothRef[i] = boothRef [i+1];
                    boothRef[i+1] = temp;
                }
            }
        }

        int noOfEmptyBooths = 6;
        for(String temp:boothRef) {
            if(!temp.equals("e")){
                noOfEmptyBooths--;
                System.out.println(temp);
            }
        }
        if (noOfEmptyBooths==6){
            System.out.println("All booths are empty");
        }
    }
    public void viewRemainingVac(){
        System.out.println("Remaining AstraZeneca Vaccinations : "+ this.astraZenecaVaccines);
        System.out.println("Remaining Synofarm Vaccinations : "+ this.synopharmVaccines);
        System.out.println("Remaining Pfizer Vaccinations : "+ this.pfizerVaccines);
        if(this.astraZenecaVaccines<20) {
            System.out.println("\t****The remaining AstraZeneca vaccines are less than 20****");
        }else if(this.synopharmVaccines<20) {
            System.out.println("\t****The remaining AstraZeneca vaccines are less than 20****");
        }else if(this.pfizerVaccines<20) {
            System.out.println("\t****The remaining AstraZeneca vaccines are less than 20****");
        }
    }

    public void addVacToStock (){
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the code of vaccination type\n");
        System.out.println("1001 - AstraZeneca");
        System.out.println("1002 - Synopharm");
        System.out.println("1003 - Pfizer");
        int code = input.nextInt();
        if (code == 1001){
            System.out.print("Number of AstraZeneca Vaccines to add to the stock: ");
            this.astraZenecaVaccines += input.nextInt();
            System.out.println("Vaccines added to the stock successfully! ");
        }else if (code == 1002){
            System.out.print("Number of Synopharm Vaccines to add to the stock: ");
            this.synopharmVaccines += input.nextInt();
            System.out.println("Vaccines added to the stock successfully! ");
        }else if (code == 1003){
            System.out.print("Number of Pfizer Vaccines to add to the stock: ");
            this.pfizerVaccines += input.nextInt();
            System.out.println("Vaccines added to the stock successfully! ");
        }else{
            System.out.println("Invalid Entry!");
        }
    }
}
