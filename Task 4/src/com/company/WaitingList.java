package com.company;

import java.util.LinkedList;
import java.util.Queue;

public class WaitingList{

    private Queue<String> waitingList = new LinkedList<>();

    public Queue<String> getWaitingList() {
        return waitingList;
    }

    public void addToWaitingList(String patientNameRef){
        if(waitingList.size()<=10){
            waitingList.add(patientNameRef);
            System.out.println("patient has been added to the waiting list successfully");
        }else{
            System.out.println("Waiting list is full at the moment. Please try again later.");
        }

    }

    public String deleteFromWaitingList(){
        String nextPatientToVaccination = waitingList.poll();
        return nextPatientToVaccination;
    }
}
