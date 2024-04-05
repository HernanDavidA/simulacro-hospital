package model;

import database.CRUD;
import database.ConfigDB;
import entity.Doctor;
import entity.Specialty;

import javax.print.Doc;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoctorModel implements CRUD {

    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Doctor objDoctor = (Doctor) obj;

        try {
            String sql = "INSERT INTO doctor (name, last_name, id_specialty) VALUES (?, ?, ?) ;";

            PreparedStatement objPrepared = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepared.setString(1, objDoctor.getName());
            objPrepared.setString(2, objDoctor.getLastName());
            objPrepared.setInt(3, objDoctor.getIdSpecialty());


            objPrepared.execute();
            ResultSet objResult = objPrepared.getGeneratedKeys();
            while (objResult.next()){
                objDoctor.setIdSpecialty(objResult.getInt(1));
            }
            JOptionPane.showMessageDialog(null, "Doctor added successfully");


        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Error >>>" + e.getMessage());
        }
        return objDoctor;
    }

    @Override
    public List<Object> findAll() {
        List<Object> listDoctor = new ArrayList<>();
        // Open the connection
        Connection objConnection = ConfigDB.openConnection();
        try {
            String sql = "SELECT * FROM doctor;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()){
                Doctor objDoctor = new Doctor();
                objDoctor.setIdDoctor(objResult.getInt("id_doctor"));
                objDoctor.setName(objResult.getString("name"));
                objDoctor.setLastName(objResult.getString("last_name"));
                objDoctor.setIdSpecialty(objResult.getInt("id_specialty"));
                listDoctor.add(objDoctor);
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error >>> " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return listDoctor;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();

        Doctor objDoctor = (Doctor) obj;

        boolean isUpdated = false;
        try {
            String sql = "UPDATE doctor SET id_specialty = ?  where id_doctor = ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);
            objPrepared.setInt(1, objDoctor.getIdSpecialty());
            objPrepared.setInt(2,objDoctor.getIdDoctor());


            int totalRowAffected = objPrepared.executeUpdate();

            if (totalRowAffected > 0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "Updated successfully");
            }else{
                JOptionPane.showMessageDialog(null,"Falle");
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error >>>> " + e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }

        return isUpdated;
    }

    @Override
    public boolean delete(Object obj) {

        Doctor objDoctor = (Doctor) obj;
        Connection objConnection = ConfigDB.openConnection();

        boolean isDeleted = false;
        try {

            String sql = "DELETE FROM doctor WHERE id_doctor = ?";
            // Create the prepareStatement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            // Give value to ?
            objPrepare.setInt(1, objDoctor.getIdDoctor());
            // 7. Execute the query (executeUpdate) return the quantity of data affected
            int totalAffectedRows = objPrepare.executeUpdate();
            if (totalAffectedRows > 0 ){
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "The update was successfully");
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error >>>" + e.getMessage());
        }
        ConfigDB.closeConnection();
        return isDeleted;
    }

    public static Doctor findById(int id){
        // Open the connection
        Connection objConnection = ConfigDB.openConnection();
        Doctor objDoctor = null;
        try{                                         // ? = query parameter
            String sql = "SELECT * FROM doctor WHERE id_doctor = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, id);
            ResultSet objResult = objPrepare.executeQuery();

            if(objResult.next()){
                objDoctor = new Doctor();
                objDoctor.setName(objResult.getString("name"));
                objDoctor.setLastName(objResult.getString("last_name"));
                objDoctor.setIdSpecialty(objResult.getInt("id_specialty"));

            }
            ConfigDB.closeConnection();

        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, "ERROR >>>" + e.getMessage());
        }

        return objDoctor;
    }
    public static ArrayList<Doctor> showBySpecialty(int idSpecialty){
        ArrayList<Doctor> listDoctor = new ArrayList<>();

        Connection objConnection = ConfigDB.openConnection();

        try{
            String sql = "SELECT * FROM doctor INNER JOIN specialities ON doctor.id_specialty = specialities.id_specialty where specialities.id_specialty = ?;";

            PreparedStatement objPrepared = objConnection.prepareStatement(sql);
            objPrepared.setInt(1, idSpecialty);

            ResultSet objResult = objPrepared.executeQuery();

            while (objResult.next()){
                Doctor objDoctor = new Doctor();

                objDoctor.setName(objResult.getString("name"));
                objDoctor.setLastName(objResult.getString("last_name"));
                objDoctor.setIdSpecialty(objResult.getInt("id_specialty"));


                listDoctor.add(objDoctor);

            }

        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "ERROR >>>" + e.getMessage());
        }

        finally {
            ConfigDB.closeConnection();
        }
        return listDoctor;
    }
}
