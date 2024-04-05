package controller;

import entity.MedicalDate;
import entity.Patient;
import entity.Specialty;
import model.DateModel;
import model.PatientModel;
import model.SpecialtyModel;

import javax.swing.*;

public class DateController {

    public static void create(){
        DateModel objDateModel = new DateModel();
        String listPatient = getAllPatient();
        int idSpecialty = Integer.parseInt(JOptionPane.showInputDialog(null , " Insert the id where the type of date belongs \n" + getAllSpecialties()));
        int idPatient = Integer.parseInt(JOptionPane.showInputDialog(listPatient + "Insert the id of the patient"));
        String dayDate = JOptionPane.showInputDialog("Enter the day of the medical date");
        String timeDate = JOptionPane.showInputDialog("Enter the time of the medical date");
        String reason = JOptionPane.showInputDialog("Enter the reason of the date");

        MedicalDate objDate = new MedicalDate();
//          Create an instance of author
        objDate.setIdSpecialty(idSpecialty);
        objDate.setIdPatient(idPatient);
        objDate.setDayDate(dayDate);
        objDate.setTimeDate(timeDate);
        objDate.setReason(reason);
        // Call the method of intersection and save the object thar return on author previously instantiated, we have to cast it
        objDate = (MedicalDate) objDateModel.insert(objDate);
    }

    public static void getAllDateString(){

        String listDate = getAllDate();

        JOptionPane.showMessageDialog(null, listDate);
    }

    public static void update(){
        DateModel objDateModel = new DateModel();
        String listDate = getAllDate();
        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listDate + "\n Enter the id of the date to edit the day of the date"));
        MedicalDate objDate = objDateModel.findById(idUpdate);
        if (objDate == null) {
            JOptionPane.showMessageDialog(null, "Patient not found");
        } else {

            String dayDate = JOptionPane.showInputDialog(null, "Enter the day of the date", objDate.getDayDate());

            objDate.setDayDate(dayDate);

            objDate.setId(idUpdate);
            objDateModel.update(objDate);
        }
    }
    public static String getAllDate(){
        DateModel objDateModel = new DateModel();
        String listDate = "\n Date LIST \n";
        for (Object ite : objDateModel.findAll()){

            MedicalDate objDate = (MedicalDate) ite;
            listDate += objDate.toString() +"\n";
        }
        return listDate;
    }

    public static void findByDate(){
        DateModel objDateModel = new DateModel();
        String date = JOptionPane.showInputDialog(null, "Enter the date of the date to search");
        String listDate = getAllDate();
        MedicalDate objDate = objDateModel.findByDate(date);
        if(objDate == null){
            JOptionPane.showMessageDialog(null, "Patient not found");
        }else{
            JOptionPane.showMessageDialog(null, "Patient found\n" + objDate);
        }
    }
    public static String getAllSpecialties(){
        SpecialtyModel objSpecialtyModel = new SpecialtyModel();
        String listSpecialty = "\n SPECIALTY LIST \n";
        for (Object ite : objSpecialtyModel.findAll()){
            // Become the Object to coder
            Specialty objSpecialty = (Specialty) ite;
            listSpecialty += objSpecialty.toString() +"\n";
        }

        return listSpecialty;
    }
    public static String getAllPatient(){
        PatientModel objPatientModel = new PatientModel();
        String listPatient = "\n PATIENT LIST \n";
        for (Object ite : objPatientModel.findAll()){
            // Become the Object to coder
            Patient objDoctor = (Patient) ite;
            listPatient += objDoctor.toString() +"\n";
        }
        return listPatient;
    }


    public static void delete(){
        DateModel objDateModel = new DateModel();
        String listDate = getAllDate();
        int idDeleted = Integer.parseInt(JOptionPane.showInputDialog(listDate +  "\n  Enter the ID of the patient to delete"));

        MedicalDate objDate = objDateModel.findById(idDeleted);
        int confirm = 1;
        if(objDate == null){
            JOptionPane.showMessageDialog(null, "Medical date not found");
        }else{
            confirm = JOptionPane.showConfirmDialog(null,"Are you sure want to delete the date of \n" + objDate.getDayDate() + " of the pacient" + objDate.getIdPatient());
            if (confirm == 0){
                objDate.setId(idDeleted);
                objDateModel.delete(objDate);
            }
        }
    }
    }

