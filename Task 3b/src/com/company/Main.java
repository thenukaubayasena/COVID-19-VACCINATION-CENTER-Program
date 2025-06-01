package com.company;

import java.io.IOException;
import java.util.*;
public class Main {

    public static void main(String[] args) throws IOException{
        Scanner input = new Scanner(System.in);
        Booth[] boothClass = new Booth[6];
        for(int l=0;l< boothClass.length;l++){

            boothClass[l] = new Booth();
        }
        VaccinationCenter vaccinationCenterClass = new VaccinationCenter(boothClass);
        Patient[] patient = new Patient[boothClass.length];
        boolean repeat = true;
        vaccinationCenterClass.initialise(boothClass);

        while(repeat) {
            System.out.println("         Welcome to IIT Vaccination Center\n\n" +
                    "Please Enter the relevant number or letters for your choice\n" +
                    "100 or VVB: View all Vaccination Booths\n" +
                    "101 or VEB: View all Empty Booths\n" +
                    "102 or APB: Add Patient to a Booth\"\n" +
                    "103 or RPB: Remove Patient from a Booth\n" +
                    "104 or VPS: View Patients Sorted in alphabetical order\n" +
                    "105 or SPD: Store Program Data into file\n" +
                    "106 or LPD: Load Program Data from file\n" +
                    "107 or VRV: View Remaining Vaccinations\n" +
                    "108 or AVS: Add Vaccinations to the Stock\n" +
                    "999 or EXT: Exit the Program");
            String menuChoice = input.next().toUpperCase();

            if(menuChoice.equals("999") || menuChoice.equalsIgnoreCase("EXT")) {
                repeat = false;
                System.out.println("Exiting....");
                System.out.println("Stay Safe....Thanks.....");
            }else if(menuChoice.equalsIgnoreCase("100") || menuChoice.equalsIgnoreCase("VVB")) {
                vaccinationCenterClass.viewAllBooths(patient);
            }else if(menuChoice.equalsIgnoreCase("101") || menuChoice.equalsIgnoreCase("VEB")) {
                vaccinationCenterClass.viewEmptyBooths();
            }else if(menuChoice.equalsIgnoreCase("102") || menuChoice.equalsIgnoreCase("APB")) {
                vaccinationCenterClass.addPatientToBooth(patient);
            }else if(menuChoice.equalsIgnoreCase("103") || menuChoice.equalsIgnoreCase("RPB")) {
                System.out.println("Enter the booth number of the patient's occupied booth:");
                int boothNum = input.nextInt();
                vaccinationCenterClass.deletePatient(boothNum,patient);
            }else if(menuChoice.equalsIgnoreCase("104") || menuChoice.equalsIgnoreCase("VPS")) {
                vaccinationCenterClass.orderedPatientList();
            }else if(menuChoice.equalsIgnoreCase("105") || menuChoice.equalsIgnoreCase("SPD")) {
                vaccinationCenterClass.storeProgramDataToFile(boothClass,patient);
            }else if(menuChoice.equalsIgnoreCase("106") || menuChoice.equalsIgnoreCase("LPD")) {
                boothClass = vaccinationCenterClass.getDataFromFile(boothClass,patient);
            }else if(menuChoice.equalsIgnoreCase("107") || menuChoice.equalsIgnoreCase("VRV")) {
                vaccinationCenterClass.viewRemainingVac();
            }else if(menuChoice.equalsIgnoreCase("108") || menuChoice.equalsIgnoreCase("AVS")) {
                vaccinationCenterClass.addVacToStock();
            }else{
                System.out.println("Entered value not recognized");
            }
        }
    }

}
