package model;

import database.CRUD;
import database.ConfigDB;
import entity.Doctor;
import entity.Patient;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Patient objPatient =  (Patient) obj;
        try{
            String sql = "INSERT INTO patient (name, last_name, birth_date, nit) VALUES (?, ?, ?, ?);";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            objPrepared.setString(1, objPatient.getName());
            objPrepared.setString(2, objPatient.getLastName());
            objPrepared.setString(3, objPatient.getBirthDate());
            objPrepared.setString(4, objPatient.getNit());
            objPrepared.execute();
            ResultSet objResult = objPrepared.getGeneratedKeys();
            while(objResult.next()){
                objPatient.setId(objResult.getInt(1));
            }
            JOptionPane.showMessageDialog(null, "PATIENT INSERTION WAS SUCCESSFULLY");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error >>> " + e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }
        return objPatient;
    }
    @Override
    public List<Object> findAll() {
        List<Object> listPatient = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        try{
            String sql = "SELECT * FROM patient;";

            PreparedStatement objPrepared = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepared.executeQuery();

            while(objResult.next()){
                Patient objPatient = new Patient();

                objPatient.setName(objResult.getString("name"));
                objPatient.setLastName(objResult.getString("last_name"));
                objPatient.setId(objResult.getInt("id_patient"));
                objPatient.setBirthDate(objResult.getString("birth_date"));
                objPatient.setNit(objResult.getString("nit"));

                listPatient.add(objPatient);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error >>> "+ e.getMessage());
        }
        return listPatient;
    }
    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Patient objPatient = (Patient) obj;
        boolean isUpdated = false;
        try {
            String sql = "UPDATE patient SET name = ?, last_name = ?, birth_date = ?, nit = ?   where id_patient = ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);
            objPrepared.setString(1, objPatient.getName());
            objPrepared.setString(2,objPatient.getLastName());
            objPrepared.setString(3, objPatient.getBirthDate());
            objPrepared.setString(4, objPatient.getNit());
            objPrepared.setInt(5, objPatient.getId());
            int totalRowAffected = objPrepared.executeUpdate();
            System.out.println(totalRowAffected);
            if (totalRowAffected > 0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "Updated successfully");
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
        Patient objPatient = (Patient) obj;

        Connection objConnection = ConfigDB.openConnection();

        boolean isDeleted = false;
        try {

            String sql = "DELETE FROM patient WHERE id_patient = ?";
            // Create the prepareStatement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            // Give value to ?
            objPrepare.setInt(1, objPatient.getId());
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
    public static Patient searchByNit(String nit) {
        Connection objConnection = ConfigDB.openConnection();
        Patient objPatient = null;
        try{
            String sql = "SELECT * FROM patient WHERE nit = ?;";

            PreparedStatement objPrepared = objConnection.prepareStatement(sql);

            objPrepared.setString(1, nit);


            ResultSet objResult = objPrepared.executeQuery();

            if (objResult.next()){
                objPatient = new Patient();

                objPatient.setName(objResult.getString("name"));
                objPatient.setBirthDate(objResult.getString("birth_date"));
                objPatient.setLastName(objResult.getString("last_name"));
                objPatient.setNit(objResult.getString("nit"));
                objPatient.setId(objResult.getInt("id_patient"));
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error >>> " + e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }

        return objPatient;

    }
    public static Patient findById(int id){
        // Open the connection
        Connection objConnection = ConfigDB.openConnection();
        Patient objPatient = null;
        try{                                         // ? = query parameter
            String sql = "SELECT * FROM patient WHERE id_patient = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, id);
            ResultSet objResult = objPrepare.executeQuery();

            if(objResult.next()){
                objPatient = new Patient();
                objPatient.setName(objResult.getString("name"));
                objPatient.setLastName(objResult.getString("last_name"));
                objPatient.setBirthDate(objResult.getString("birth_date"));
                objPatient.setNit(objResult.getString("nit"));
            }
            ConfigDB.closeConnection();
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, "ERROR >>>" + e.getMessage());
        }
        return objPatient;
    }
}