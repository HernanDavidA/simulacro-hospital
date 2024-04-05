package controller;


import entity.Patient;
import model.PatientModel;

import javax.swing.*;

public class PatientController {

    public static void create(){
        PatientModel objDoctorModel = new PatientModel();
        String name = JOptionPane.showInputDialog("Insert name of the patient");
        String lastName = JOptionPane.showInputDialog("Insert last name of the patient");
        String birthDate = JOptionPane.showInputDialog("Enter the birth date (YYYY/MM/DD)");
        String nit = JOptionPane.showInputDialog("Enter the NIT of the patient");


        Patient objPatient = new Patient();
//          Create an instance of author
        objPatient.setName(name);
        objPatient.setLastName(lastName);
        objPatient.setBirthDate(birthDate);
        objPatient.setNit(nit);
        // Call the method of intersection and save the object thar return on author previously instantiated, we have to cast it
        objPatient = (Patient) objDoctorModel.insert(objPatient);
    }
    public static String getAll(){
        PatientModel objPatientModel = new PatientModel();
        String listPatient = "\n PATIENT LIST \n";
        for (Object ite : objPatientModel.findAll()){
            // Become the Object to coder
            Patient objDoctor = (Patient) ite;
            listPatient += objDoctor.toString() +"\n";
        }
        return listPatient;
    }
    public static void getAllString(){
        PatientModel objPatientModel = new PatientModel();
        String listPatient = "\n PATIENT LIST \n";
        for (Object ite : objPatientModel.findAll()){
            // Become the Object to coder
            Patient objPatient = (Patient) ite;
            listPatient += objPatient.toString() +"\n";
        }
        JOptionPane.showMessageDialog(null,listPatient);
    }
    public static void update(){
        PatientModel objPatientModel = new PatientModel();
        String listPatient = getAll();
        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listPatient + "\n Enter the id of the patient to edit"));
        Patient objPatient = objPatientModel.findById(idUpdate);
        if (objPatient == null) {
            JOptionPane.showMessageDialog(null, "Patient not found");
        } else {

            String name = JOptionPane.showInputDialog(null, "Enter the name : " , objPatient.getName());
            String lastName = JOptionPane.showInputDialog(null, "Enter the last name: ", objPatient.getLastName());
            String birthDate = JOptionPane.showInputDialog(null, "Enter the date", objPatient.getBirthDate());
            String nit = JOptionPane.showInputDialog(null, "Enter the nit ", objPatient.getNit());

            objPatient.setName(name);
            objPatient.setLastName(lastName);
            objPatient.setBirthDate(birthDate);
            objPatient.setNit(nit);

            objPatient.setId(idUpdate);
            objPatientModel.update(objPatient);
        }
    }

    public static void delete(){
        PatientModel objPatientModel = new PatientModel();
        String listPatient = getAll();
        int idDeleted = Integer.parseInt(JOptionPane.showInputDialog(listPatient +  "\n  Enter the ID of the patient to delete"));

        Patient objPatient = objPatientModel.findById(idDeleted);
        int confirm = 1;
        if(objPatient == null){
            JOptionPane.showMessageDialog(null, "Patient not found");
        }else{
            confirm = JOptionPane.showConfirmDialog(null,"Are you sure want to delete the Patient? \n" + objPatient.getName() + " " + objPatient.getLastName());
            if (confirm == 0){
                objPatient.setId(idDeleted);
                objPatientModel.delete(objPatient);
            }
        }
    }

    public static void findByNit(){
        PatientModel objPatientModel = new PatientModel();
        String id = JOptionPane.showInputDialog(null, "Enter the Patient's NIT");
        String listPatient = getAll();
        Patient objPatient = objPatientModel.searchByNit(id);
        if(objPatient == null){
            JOptionPane.showMessageDialog(null, "Patient not found");
        }else{
            JOptionPane.showMessageDialog(null, "Patient found\n" + objPatient);
        }
    }

}
