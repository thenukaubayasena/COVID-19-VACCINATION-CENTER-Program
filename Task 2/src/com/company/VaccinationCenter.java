package com.company;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class VaccinationCenter{
    private Booth[] booths;
    private int vaccines = 150;

    public VaccinationCenter(Booth [] booths){

        setVaccinationCenter(booths);
    }

    public VaccinationCenter() {

    }

    public void setVaccinationCenter(Booth[] boothRef) {

        this.booths = boothRef;
    }

    public int getVaccines() {
        return vaccines;
    }
    public void setVaccines(int vaccines) {
        this.vaccines = vaccines;
    }

    public void initialise(Booth[] boothRef) {

        for (int x = 0; x < 6 ; x++ ){
            boothRef[x].setPatientName("e");
        }
        setVaccinationCenter(boothRef);
    }
    public void addPatientToBooth(){
        if(this.getVaccines()<20) {
            System.out.println("\t****The remaining vaccinations are less than 20****");
        }

        int boothNum ;
        String roomName;
        Scanner input = new Scanner(System.in);
        System.out.println("Enter booth number from 0 to 5:" );
        boothNum = input.nextInt();
        if(boothNum >= 0 && boothNum < 6){
            if(booths[boothNum].getPatientName().equals("e")){
                System.out.println("Enter patient's name for the booth " + boothNum +" :" ) ;
                roomName = input.next();
                booths[boothNum].setPatientName(roomName);
                int reduceVaccine=this.getVaccines()-1;
                this.setVaccines(reduceVaccine);
                System.out.println("Patient added to the booth successfully!");}
            else{
                System.out.println("Booth No : "+boothNum+" is already taken by "+booths[boothNum].getPatientName());
            }
        }
        else{
            System.out.println("Invalid Entry");
        }
        setVaccinationCenter(booths);
    }
    public void deletePatient(int boothNumRef){

        if(booths[boothNumRef].getPatientName().equals("e")){
            System.out.println("Booth no : "+boothNumRef+" is already empty");
        }
        else{
            if(boothNumRef>=0 && boothNumRef<6) {
                booths[boothNumRef].setPatientName("e");
                System.out.println("Patient removed from the booth successfully!");
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

    public void viewAllBooths(){

        for (int x = 0; x < 6; x++ ) {
            if(booths[x].getPatientName().equals("e")){
                System.out.println("Booth " + x + " is empty");
            }else {
                System.out.println("Booth " + x + " occupied by " + booths[x].getPatientName());
            }
        }
    }

    public void storeProgramDataToFile(Booth[] boothRef) throws IOException {

        File saveFile = new File("vaccineData.txt");
        PrintStream writer = new PrintStream(saveFile);
        int index = 0;
        while(index < boothRef.length){
            writer.println(boothRef[index].getPatientName());
            index++;
        }
        writer.println(this.getVaccines());
        writer.close();
        System.out.println("Data has been added to the file successfully");
    }

    public Booth [] getDataFromFile(Booth[] boothRef) throws IOException {

        int n;
        String patientName;
        int index=0;
        for(n=0;n<=5;n++){
            patientName = Files.readAllLines(Paths.get("vaccineData.txt")).get(n);
            boothRef[index] = new Booth(patientName);
            index++;
        }
        this.setVaccines(Integer.parseInt(Files.readAllLines(Paths.get("vaccineData.txt")).get(6)));
        return boothRef;
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
        System.out.println("Remaining Vaccinations : "+this.getVaccines());
        if(this.getVaccines()<20) {
            System.out.println("\t****The remaining vaccines are less than 20****");
        }
    }

    public void addVacToStock (){
        int newVaccines=this.getVaccines();
        Scanner input = new Scanner(System.in);
        System.out.println("Remaining Vaccines : "+this.getVaccines());
        System.out.print("Number of Vaccines to add to the stock: ");
        newVaccines += input.nextInt();
        System.out.println("Vaccines added to the stock successfully! ");
        this.setVaccines(newVaccines);
    }
}
