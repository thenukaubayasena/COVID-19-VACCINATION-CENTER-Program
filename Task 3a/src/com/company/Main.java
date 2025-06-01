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
    public static void viewAllBooths(String [] boothRef,String[] firstNameRef,String[] surnameRef,String[] vaccineRequestedRef){

        for (int x = 0; x < 6; x++ ) {
            if(boothRef[x].equals("e")){
                System.out.println("Booth " + x + " is empty");
            }else {
                for (int i = 0; i < 200; i++) {
                    System.out.print("-");
                }
                System.out.println();
                System.out.println("Booth " + x + " is occupied by the patient " + boothRef[x]);
                System.out.println("Additional Details ("+
                        "  First Name of the Patient : " + firstNameRef[x]+  "," +
                        "  Surname of the patient : " + surnameRef[x] + "," +
                        "  Vaccination Requested : " + vaccineRequestedRef[x] + ")");
                for (int i = 0; i < 200; i++) {
                    System.out.print("-");
                }
                System.out.println();
            }
        }
    }

    public static void addPatientToBooth(String [] boothRef,int[] vaccinesRef,String[] firstNameRef,String[] surnameRef,String[] vaccineRequestedRef){
        if(vaccinesRef[0]<20) {
            System.out.println("\t****The remaining AstraZeneca vaccines are less than 20****");
        }else if(vaccinesRef[1]<20) {
            System.out.println("\t****The remaining AstraZeneca vaccines are less than 20****");
        }else if(vaccinesRef[2]<20) {
            System.out.println("\t****The remaining AstraZeneca vaccines are less than 20****");
        }
        String firstName,surname,vaccineRequested;

        int boothNum = -1;
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the first name of the patient : ");
        firstName = input.next();
        System.out.print("Enter the surname of the patient : ");
        surname = input.next();
        System.out.print("Enter the preferred vaccination by the patient : ");
        vaccineRequested = input.next();
        if(vaccineRequested.equalsIgnoreCase("AstraZeneca")){
            System.out.println("Only Booth 0 & 1 are reserved for \"AstraZeneca\" vaccinations.  ");
            if(boothRef[0].equals("e")){
                boothNum=0;
                vaccinesRef[0]--;
            }else if(boothRef[1].equals("e")){
                boothNum=1;
                vaccinesRef[0]--;
            }else{
                System.out.println("All \"AstraZeneca\" booths are occupied at the moment.Please try again shortly");
            }
        }else if(vaccineRequested.equalsIgnoreCase("Sinopharm")){
            System.out.println("Booth 2 & 3 are reserved for \"Sinopharm\" vaccinations. ");
            if(boothRef[2].equals("e")){
                boothNum=2;
                vaccinesRef[1]--;
            }else if(boothRef[3].equals("e")){
                boothNum=3;
                vaccinesRef[1]--;
            }else{
                System.out.println("All \"Sinopharm\" booths are occupied at the moment.Please try again shortly");
            }
        }else if(vaccineRequested.equalsIgnoreCase("Pfizer")){
            System.out.println("Booth 4 - 6 are reserved for \"Pfizer\" vaccinations. ");
            if(boothRef[4].equals("e")){
                boothNum=4;
                vaccinesRef[2]--;
            }else if(boothRef[5].equals("e")){
                boothNum=5;
                vaccinesRef[2]--;
            }else{
                System.out.println("All \"Pfizer\" booths are occupied at the moment.Please try again shortly");
            }
        }else{
            System.out.println("Invalid Entry!");
        }if(boothNum!=-1){
            boothRef[boothNum]=firstName;
            firstNameRef[boothNum]=firstName;
            surnameRef[boothNum]=surname;
            vaccineRequestedRef[boothNum]=vaccineRequested;
            System.out.println("Patient added to the booth No."+boothNum+" successfully");

        }
    }

    public static void viewEmptyBooths(String[] boothRef) {

        for (int x = 0; x < 6; x++) {
            if (boothRef[x].equals("e")) {
                System.out.println("Booth " + x + " is empty");
            }
        }
    }

    public static void viewRemainingVac(int[] vaccinesRef){
        System.out.println("Remaining AstraZeneca Vaccinations : "+ vaccinesRef[0]);
        System.out.println("Remaining Synofarm Vaccinations : "+ vaccinesRef[1]);
        System.out.println("Remaining Pfizer Vaccinations : "+ vaccinesRef[2]);
        if(vaccinesRef[0]<20) {
            System.out.println("\t****The remaining AstraZeneca vaccines are less than 20****");
        }else if(vaccinesRef[1]<20) {
            System.out.println("\t****The remaining AstraZeneca vaccines are less than 20****");
        }else if(vaccinesRef[2]<20) {
            System.out.println("\t****The remaining AstraZeneca vaccines are less than 20****");
        }
    }

    public static void addVacToStock (int[] vaccinesRef){
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the code of vaccination type\n");
        System.out.println("1001 - AstraZeneca");
        System.out.println("1002 - Synopharm");
        System.out.println("1003 - Pfizer");
        int code = input.nextInt();
        if (code == 1001){
            System.out.print("Number of AstraZeneca Vaccines to add to the stock: ");
            vaccinesRef[0] += input.nextInt();
            System.out.println("Vaccines added to the stock successfully! ");
        }else if (code == 1002){
            System.out.print("Number of Synopharm Vaccines to add to the stock: ");
            vaccinesRef[1] += input.nextInt();
            System.out.println("Vaccines added to the stock successfully! ");
        }else if (code == 1003){
            System.out.print("Number of Pfizer Vaccines to add to the stock: ");
            vaccinesRef[2] += input.nextInt();
            System.out.println("Vaccines added to the stock successfully! ");
        }else{
            System.out.println("Invalid Entry!");
        }
    }

    public static void deletePatient(String [] boothRef){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the booth number of the patient's occupied booth:");
        int boothNum = input.nextInt();
        if(boothRef[boothNum].equals("e")){
            System.out.println("Booth no : "+boothNum+" is already empty");
        }
        else{
            if(boothNum>=0 && boothNum<6){
                boothRef[boothNum] = "e";
                System.out.println("Patient removed from the booth successfully!");
            }
            else{
                System.out.println("Invalid Entry");
            }
        }
    }

    public static void storeProgramDataToFile(String[] boothRef,String[] firstNameRef,String[] surnameRef,String[] vaccineRequestedRef,int[] vaccinesRef) throws IOException {

        File saveFile = new File("vaccineData.txt");
        PrintStream writer = new PrintStream(saveFile);
        for (String temp : boothRef) {

            writer.println(temp);
        }
        for (String temp : firstNameRef) {

            writer.println(temp);
        }
        for (String temp : surnameRef) {

            writer.println(temp);
        }
        for (String temp : vaccineRequestedRef) {

            writer.println(temp);
        }
        for (int temp : vaccinesRef) {

            writer.println(temp);
        }
        System.out.println("Data has been added to the file successfully");
        writer.close();
    }

    public static void getDataFromFile(String[] boothRef,String[] firstNameRef,String[] surnameRef,String[] vaccineRequestedRef,int [] vaccinesRef) throws IOException {

        int n;
        int index = 0;
        for (n = 0; n <= 5; n++) {

            boothRef[index] = Files.readAllLines(Paths.get("vaccineData.txt")).get(n);

            index++;
        }
        index=0;
        for(n=6;n<=11;n++){

            firstNameRef[index] = Files.readAllLines(Paths.get("vaccineData.txt")).get(n);
            index++;
        }
        index = 0;
        for(n=12;n<=17;n++){

            surnameRef[index] = Files.readAllLines(Paths.get("vaccineData.txt")).get(n);
            index++;
        }
        index = 0;
        for(n=18;n<=23;n++){

            vaccineRequestedRef[index] = Files.readAllLines(Paths.get("vaccineData.txt")).get(n);
            index++;
        }
        index = 0;
        for(n=24;n<=26;n++){

            vaccinesRef[index] = Integer.parseInt(Files.readAllLines(Paths.get("vaccineData.txt")).get(n));
            index++;
        }
        System.out.println("Data has been loaded from the file successfully");
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

    public static void main(String[] args) throws IOException{
        int [] vaccines = new int [3];
        vaccines[0]=50;
        vaccines[1]=50;
        vaccines[2]=50;
        Scanner input = new Scanner(System.in);
        String[] vaccineCenter = new String[6];
        String [] firstName = new String[6];
        String [] surname = new String[6];
        String [] vaccineRequested = new String[6];
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

            if (menuChoice.equals("999") || menuChoice.equalsIgnoreCase("EXT")) {
                repeat = false;
                System.out.println("Exiting....");
                System.out.println("Stay Safe....Thanks.....");
            } else if (menuChoice.equalsIgnoreCase("100") || menuChoice.equalsIgnoreCase("VVB")) {
                viewAllBooths(vaccineCenter, firstName, surname, vaccineRequested);
            } else if (menuChoice.equalsIgnoreCase("101") || menuChoice.equalsIgnoreCase("VEB")) {
                viewEmptyBooths(vaccineCenter);
            } else if (menuChoice.equalsIgnoreCase("102") || menuChoice.equalsIgnoreCase("APB")) {
                addPatientToBooth(vaccineCenter, vaccines, firstName, surname, vaccineRequested);
            } else if (menuChoice.equalsIgnoreCase("103") || menuChoice.equalsIgnoreCase("RPB")) {
                deletePatient(vaccineCenter);
            } else if (menuChoice.equalsIgnoreCase("104") || menuChoice.equalsIgnoreCase("VPS")) {
                orderedPatientList(vaccineCenter);
            } else if (menuChoice.equalsIgnoreCase("105") || menuChoice.equalsIgnoreCase("SPD")) {
                storeProgramDataToFile(vaccineCenter, firstName, surname, vaccineRequested, vaccines);
            } else if (menuChoice.equalsIgnoreCase("106") || menuChoice.equalsIgnoreCase("LPD")) {
                getDataFromFile(vaccineCenter, firstName, surname, vaccineRequested,vaccines);
            } else if (menuChoice.equalsIgnoreCase("107") || menuChoice.equalsIgnoreCase("VRV")) {
                viewRemainingVac(vaccines);
            } else if (menuChoice.equalsIgnoreCase("108") || menuChoice.equalsIgnoreCase("AVS")) {
                addVacToStock(vaccines);
            } else {
                System.out.println("Entered value not recognized");
            }
        }
    }

}