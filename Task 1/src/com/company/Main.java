package com.company;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
public class Main {
    private static void initialise(String[] boothRef) {

        for (int x = 0; x < 6; x++) {
            boothRef[x] = "e";
        }
        System.out.println("Program initialised successfully!");
    }

    public static void viewAllBooths(String[] boothRef) {

        for (int x = 0; x < 6; x++) {
            if (boothRef[x].equals("e")) {
                System.out.println("Booth " + x + " is empty");
            } else {
                System.out.println("Booth " + x + " occupied by " + boothRef[x]);
            }
        }
    }

    public static int addPatientToBooth(String[] boothRef,int vaccinesRef) {
        if(vaccinesRef<20) {
            System.out.println("\t****The remaining vaccinations are less than 20****");
        }

        int boothNum ;
        String patientName;
        Scanner input = new Scanner(System.in);
        System.out.println("Enter booth number from 0 to 5:" );
        boothNum = input.nextInt();
        if(boothNum >= 0 && boothNum < 6){
            if(boothRef[boothNum].equals("e")){
                System.out.println("Enter patient's name for the booth " + boothNum +" :" ) ;
                patientName = input.next();
                boothRef[boothNum] = patientName ;
                vaccinesRef--;
                System.out.println("Patient added to the booth successfully!");}
            else{
                System.out.println("Booth No : "+boothNum+" is already taken by "+ boothRef[boothNum]);
            }
        }
        else{
            System.out.println("Invalid Entry");
        }
        return vaccinesRef;
    }

    public static void viewEmptyBooths(String[] boothRef) {

        for (int x = 0; x < 6; x++) {
            if (boothRef[x].equals("e")) {
                System.out.println("Booth " + x + " is empty");
            }
        }
    }

    public static void deletePatient(String[] boothRef) {

        Scanner input = new Scanner(System.in);
        System.out.println("Enter the booth number of the patient's occupied booth:");
        int boothNum = input.nextInt();
        if (boothRef[boothNum].equals("e")) {
            System.out.println("Booth no : " + boothNum + " is already empty");
        } else {
            if (boothNum >= 0 && boothNum < 6) {
                boothRef[boothNum] = "e";
                System.out.println("Patient removed from the booth successfully!");
            } else {
                System.out.println("Invalid Entry");
            }
        }
    }

    public static void viewRemainingVac(int vaccinesRef){
        System.out.println("Remaining Vaccinations : "+vaccinesRef);
        if(vaccinesRef<20) {
            System.out.println("\t****The remaining vaccines are less than 20****");
        }
    }

    public static int addVacToStock (int vaccinesRef){
        Scanner input = new Scanner(System.in);
        System.out.println("Remaining Vaccines : "+vaccinesRef);
        System.out.print("Number of Vaccines to add to the stock: ");
        vaccinesRef+=input.nextInt();
        System.out.println("Vaccines added to the stock successfully! ");
        return vaccinesRef;
    }

    public static void storeProgramDataToFile(String[] boothRef,int vaccinesRef) throws IOException {
        File saveFile = new File("vaccineData.txt");
        PrintStream writer = new PrintStream(saveFile);
        for (String temp : boothRef) {

            writer.println(temp);
        }
        writer.println(vaccinesRef);
        System.out.println("Data has been added to the file successfully");
        writer.close();
    }

    public static int getDataFromFile(String[] boothRef) throws IOException, ClassNotFoundException {
        int vaccineRef;
        int n;
        int index = 0;
        for (n = 0; n <= 5; n++) {

            boothRef[index] = Files.readAllLines(Paths.get("vaccineData.txt")).get(n);

            index++;
        }
        vaccineRef = Integer.parseInt(Files.readAllLines(Paths.get("vaccineData.txt")).get(6));

        System.out.println("Data has been loaded from the file successfully");
        return vaccineRef;
    }

    public static void orderedPatientList(String [] booth) {

        String [] boothRef = new String[booth.length];
        for(int k=0;k< boothRef.length;k++){
            boothRef[k] = booth[k];
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

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner input = new Scanner(System.in);
        int vaccines = 150;
        String[] vaccineCenter = new String[6];
        boolean repeat = true;
        initialise(vaccineCenter);

        while(repeat) {
            System.out.println("         Welcome to IIT Vaccination Center\n\n" +
                    "Please Enter the relevant number or letters for your choice\n" +
                    "100 or VVB: View all Vaccination Booths\n" +
                    "101 or VEB: View all Empty Booths\n" +
                    "102 or APB: Add Patient to a Booth\n" +
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
                viewAllBooths(vaccineCenter);
            }else if(menuChoice.equalsIgnoreCase("101") || menuChoice.equalsIgnoreCase("VEB")) {
                viewEmptyBooths(vaccineCenter);
            }else if(menuChoice.equalsIgnoreCase("102") || menuChoice.equalsIgnoreCase("APB")) {
                vaccines=addPatientToBooth(vaccineCenter,vaccines);
            }else if(menuChoice.equalsIgnoreCase("103") || menuChoice.equalsIgnoreCase("RPB")) {
                deletePatient(vaccineCenter);
            }else if(menuChoice.equalsIgnoreCase("104") || menuChoice.equalsIgnoreCase("VPS")) {
                orderedPatientList(vaccineCenter);
            }else if(menuChoice.equalsIgnoreCase("105") || menuChoice.equalsIgnoreCase("SPD")) {
                storeProgramDataToFile(vaccineCenter,vaccines);
            }else if(menuChoice.equalsIgnoreCase("106") || menuChoice.equalsIgnoreCase("LPD")) {
                vaccines=getDataFromFile(vaccineCenter);
            }else if(menuChoice.equalsIgnoreCase("107") || menuChoice.equalsIgnoreCase("VRV")) {
                viewRemainingVac(vaccines);
            }else if(menuChoice.equalsIgnoreCase("108") || menuChoice.equalsIgnoreCase("AVS")) {
                vaccines=addVacToStock(vaccines);
            }else{
                System.out.println("Entered value not recognized");
            }
        }
    }

}