package model;

import database.CRUD;
import database.ConfigDB;

import entity.MedicalDate;
import entity.Patient;


import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DateModel implements CRUD {

    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();

        MedicalDate objDate =  (MedicalDate) obj;

        try{
            String sql = "INSERT INTO patient (id_speciality, id_patient, day_date, time_date, reason) VALUES (?, ?, ?, ?, ?);";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            objPrepared.setInt(1, objDate.getIdSpecialty());
            objPrepared.setInt(2, objDate.getIdPatient());
            objPrepared.setString(3, objDate.getDayDate());
            objPrepared.setString(4, objDate.getTimeDate());
            objPrepared.setString(5, objDate.getReason());

            objPrepared.execute();

            ResultSet objResult = objPrepared.getGeneratedKeys();

            while(objResult.next()){
                objDate.setId(objResult.getInt(1));
            }
            JOptionPane.showMessageDialog(null, "DATE INSERTION WAS SUCCESSFULLY");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error >>> " + e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }

        return objDate;
    }

    @Override
    public List<Object> findAll() {
        List<Object> listDate = new ArrayList<>();

        Connection objConnection = ConfigDB.openConnection();

        try{
            String sql = "SELECT * FROM date;";

            PreparedStatement objPrepared = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepared.executeQuery();

            while(objResult.next()){
                MedicalDate objDate = new MedicalDate();

                objDate.setIdSpecialty(objResult.getInt("id_specialty"));
                objDate.setIdPatient(objResult.getInt("id_patient"));
                objDate.setId(objResult.getInt("id_date"));
                objDate.setDayDate(objResult.getString("day_date"));
                objDate.setTimeDate(objResult.getString("time_date"));
                objDate.setReason(objResult.getString("reason"));

                listDate.add(objDate);

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error >>> "+ e.getMessage());
        }

        return listDate;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();

        MedicalDate objDate = (MedicalDate) obj;

        boolean isUpdated = false;
        try {
            String sql = "UPDATE date SET day_date = ?  where id_date = ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);
            objPrepared.setString(1, objDate.getDayDate());
            objPrepared.setInt(2,objDate.getId());


            int totalRowAffected = objPrepared.executeUpdate();

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

        MedicalDate objDate = (MedicalDate) obj;
        Connection objConnection = ConfigDB.openConnection();

        boolean isDeleted = false;
        try {

            String sql = "DELETE FROM date WHERE id_date = ?";
            // Create the prepareStatement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            // Give value to ?
            objPrepare.setInt(1, objDate.getId());
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
    public static MedicalDate findByDate(String date){
        Connection objConnection = ConfigDB.openConnection();
        MedicalDate objDate = null;
        try{                                         // ? = query parameter
            String sql = "SELECT * FROM date WHERE day_date = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1, date);
            ResultSet objResult = objPrepare.executeQuery();

            while(objResult.next()){
                objDate = new MedicalDate();
                objDate.setDayDate(objResult.getString("day_date"));
                objDate.setReason(objResult.getString("reason"));
                objDate.setIdSpecialty(objResult.getInt("id_specialty"));
                objDate.setIdPatient(objResult.getInt("id_patient"));
                objDate.setTimeDate(objResult.getString("time_date"));
                objDate.setTimeDate(objResult.getString("id_date"));
            }


        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, "ERROR >>>" + e.getMessage());
        }
        ConfigDB.closeConnection();
        return objDate;

    }
public static MedicalDate findById(int id){

    Connection objConnection = ConfigDB.openConnection();
    MedicalDate objDate = null;
    try{                                         // ? = query parameter
        String sql = "SELECT * FROM date WHERE id_date = ?;";
        PreparedStatement objPrepare = objConnection.prepareStatement(sql);
        objPrepare.setInt(1, id);
        ResultSet objResult = objPrepare.executeQuery();

        if(objResult.next()){
            objDate = new MedicalDate();
            objDate.setDayDate(objResult.getString("day_date"));
            objDate.setReason(objResult.getString("reason"));
            objDate.setIdSpecialty(objResult.getInt("id_specialty"));
            objDate.setIdPatient(objResult.getInt("id_patient"));
        }


    }
    catch (SQLException e){
        JOptionPane.showMessageDialog(null, "ERROR >>>" + e.getMessage());
    }
    ConfigDB.closeConnection();
    return objDate;



    }

}
