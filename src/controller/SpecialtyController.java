package controller;

import entity.Specialty;
import model.SpecialtyModel;
import javax.swing.*;
public class SpecialtyController {
public static void create(){
    SpecialtyModel objSpecialtyModel = new SpecialtyModel();
    String name = JOptionPane.showInputDialog("Insert name of the specialty");
    String description = JOptionPane.showInputDialog("Insert description");
    Specialty objSpecialty = new Specialty();
//          Create an instance of author
    objSpecialty.setName(name);
    objSpecialty.setDescription(description);
    // Call the method of intersection and save the object thar return on author previously instantiated, we have to cast it
    objSpecialty = (Specialty) objSpecialtyModel.insert(objSpecialty);
    JOptionPane.showMessageDialog(null, objSpecialty.toString());
}
public static void getAllString(){
    SpecialtyModel objSpecialtyModel = new SpecialtyModel();
    String listSpecialty = "\n SPECIALTY LIST \n";
    for (Object ite : objSpecialtyModel.findAll()){
        // Become the Object to coder
        Specialty objSpecialty = (Specialty) ite;
        listSpecialty += objSpecialty.toString() +"\n";
    }
    JOptionPane.showMessageDialog(null, listSpecialty);
}
    public static String getAll(){
        SpecialtyModel objSpecialtyModel = new SpecialtyModel();
        String listSpecialty = "\n SPECIALTY LIST \n";
        for (Object ite : objSpecialtyModel.findAll()){
            // Become the Object to coder
            Specialty objSpecialty = (Specialty) ite;
            listSpecialty += objSpecialty.toString() +"\n";
        }

        return listSpecialty;
    }
    public static void update(){
        SpecialtyModel objSpecialtyModel = new SpecialtyModel();
        String listSpecialty = getAll();
        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listSpecialty + "\n Enter the id of the specialty to edit"));
        Specialty objSpecialty = objSpecialtyModel.findById(idUpdate);
        if (objSpecialty == null) {
            JOptionPane.showMessageDialog(null, "Specialty not found");
        } else {
            String name = JOptionPane.showInputDialog(null, "Enter the new name:", objSpecialty.getName());
            String description = JOptionPane.showInputDialog(null, "Enter the new description:", objSpecialty.getDescription());
            objSpecialty.setName(name);
            objSpecialty.setDescription(description);
            objSpecialtyModel.update(objSpecialty);
        }
    }

    public static void delete(){
        SpecialtyModel objSpecialtyModel = new SpecialtyModel();

        String listBook = getAll();

        int idDeleted = Integer.parseInt(JOptionPane.showInputDialog(listBook +  "\n  Enter the ID of the specialty to delete"));

        Specialty objSpecialty = objSpecialtyModel.findById(idDeleted);
        int confirm = 1;
        if(objSpecialty == null){
            JOptionPane.showMessageDialog(null, "Specialty not found");
        }else{
            confirm = JOptionPane.showConfirmDialog(null,"Are you sure want to delete the specialty? \n" + objSpecialty.getName());
            if (confirm == 0){
                objSpecialtyModel.delete(objSpecialty);
                System.out.println("Success");
            }
        }
    }

    public static void findById(){
        SpecialtyModel objSpecialtyModel = new SpecialtyModel();
        int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the specialty's ID"));
        String listSpecialty = getAll();
        Specialty objSpecialty = objSpecialtyModel.findById(id);
        if(objSpecialty == null){
            JOptionPane.showMessageDialog(null, "Specialty not found");
        }else{
            JOptionPane.showMessageDialog(null, "Specialty found\n" + objSpecialty);
        }
    }

}
