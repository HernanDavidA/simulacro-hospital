package controller;

import entity.Doctor;
import entity.Specialty;
import model.DoctorModel;
import model.SpecialtyModel;

import javax.print.Doc;
import javax.swing.*;

public class DoctorController {
    public static void create(){
        DoctorModel objDoctorModel = new DoctorModel();
        String name = JOptionPane.showInputDialog("Insert name of the Doctor");
        String lastName = JOptionPane.showInputDialog("Insert last name of the doctor");
        int idSpecialty = Integer.parseInt(JOptionPane.showInputDialog(null ,
                " Insert the id where the doctor belongs \n" + getAllStringSpecialities()));

        Doctor objDoctor = new Doctor();
//          Create an instance of author
        objDoctor.setName(name);
        objDoctor.setLastName(lastName);
        objDoctor.setIdSpecialty(idSpecialty);
        // Call the method of intersection and save the object thar return on author previously instantiated, we have to cast it
        objDoctor = (Doctor) objDoctorModel.insert(objDoctor);
    }
    public static String getAll(){
        DoctorModel objDoctorModel = new DoctorModel();
        String listDoctor = "\n DOCTOR LIST \n";
        for (Object ite : objDoctorModel.findAll()){
            // Become the Object to coder
            Doctor objDoctor = (Doctor) ite;
            listDoctor += objDoctor.toString() +"\n";
        }
        return listDoctor;
    }
    public static void getAllString(){
        DoctorModel objDoctorModel = new DoctorModel();
        String listDoctor = "\n DOCTOR LIST \n";
        for (Object ite : objDoctorModel.findAll()){
            // Become the Object to coder
            Doctor objDoctor = (Doctor) ite;
            listDoctor += objDoctor.toString() +"\n";
        }
        JOptionPane.showMessageDialog(null,listDoctor);
    }
    public static void update(){
        DoctorModel objDoctorModel = new DoctorModel();
        String listDoctor = getAll();
        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listDoctor + "\n Enter the id of the doctor to edit"));
        Doctor objDoctor = objDoctorModel.findById(idUpdate);
        if (objDoctor == null) {
            JOptionPane.showMessageDialog(null, "Specialty not found");
        } else {

            int idSpecialty = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the new specialty to " + objDoctor.getName() + ": " + getAllStringSpecialities() , objDoctor.getIdSpecialty()));

            objDoctor.setIdSpecialty(idSpecialty);
            objDoctor.setIdDoctor(idUpdate);
            objDoctorModel.update(objDoctor);
        }
    }

    public static void delete(){
        DoctorModel objDoctorModel = new DoctorModel();

        String listDoctor = getAll();

        int idDeleted = Integer.parseInt(JOptionPane.showInputDialog(listDoctor +  "\n  Enter the ID of the specialty to delete"));

        Doctor objDoctor = objDoctorModel.findById(idDeleted);
        int confirm = 1;
        if(objDoctor == null){
            JOptionPane.showMessageDialog(null, "Doctor not found");
        }else{
            confirm = JOptionPane.showConfirmDialog(null,"Are you sure want to delete the doctor? \n" + objDoctor.getName() + " " + objDoctor.getLastName());
            if (confirm == 0){
                objDoctor.setIdDoctor(idDeleted);
                objDoctorModel.delete(objDoctor);

                System.out.println("Success");
            }
        }
    }

    public static void findById(){
        DoctorModel objDoctorModel = new DoctorModel();
        int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the doctor's ID"));
        String listDoctor = getAll();
        Doctor objDoctor = objDoctorModel.findById(id);
        if(objDoctor == null){
            JOptionPane.showMessageDialog(null, "Doctor not found");
        }else{
            JOptionPane.showMessageDialog(null, "Doctor found\n" + objDoctor);
        }
    }


    public static void showBySpeciality(){
        DoctorModel objDoctorModel = new DoctorModel();

        int idSpecialty = Integer.parseInt(JOptionPane.showInputDialog(null,  "\n Enter the id of the specialty where the doctor belongs \n" + getAllStringSpecialities()));

        String listDoctors = "\n Doctors list \n";
        for(Object ite : objDoctorModel.showBySpecialty(idSpecialty)){
            Doctor objDoctor = (Doctor) ite;

            listDoctors += objDoctor.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null, listDoctors);
    }
    public static String getAllStringSpecialities(){
        SpecialtyModel objSpecialtyModel = new SpecialtyModel();
        String listSpecialty = "\n SPECIALTY LIST \n";
        for (Object ite : objSpecialtyModel.findAll()){
            // Become the Object to coder
            Specialty objSpecialty = (Specialty) ite;
            listSpecialty += objSpecialty.toString() +"\n";
        }
        return listSpecialty;

    }
}

