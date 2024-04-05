package model;

import database.CRUD;
import database.ConfigDB;
import entity.Specialty;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SpecialtyModel implements CRUD {

    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Specialty objSpecialty = (Specialty) obj;

        try {
            String sql = "INSERT INTO specialities (name, description) VALUES (?, ? ) ;";

            PreparedStatement objPrepared = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepared.setString(1, objSpecialty.getName());
            objPrepared.setString(2, objSpecialty.getDescription());

            objPrepared.execute();
            ResultSet objResult = objPrepared.getGeneratedKeys();
            while (objResult.next()){
                objSpecialty.setId(objResult.getInt(1));
            }
            JOptionPane.showMessageDialog(null, "Specialty added successfully");


        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Error >>>" + e.getMessage());
        }
        return objSpecialty;
    }

    @Override
    public List<Object> findAll() {
        List<Object> listSpeciality = new ArrayList<>();
        // Open the connection
        Connection objConnection = ConfigDB.openConnection();
        try {
            String sql = "SELECT * FROM specialities;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()){
                // 6.1 Create a coder
                Specialty objSpecialty = new Specialty();
               objSpecialty.setId(objResult.getInt("id_specialty"));
                objSpecialty.setName(objResult.getString("name"));
                objSpecialty.setDescription(objResult.getString("description"));
                listSpeciality.add(objSpecialty);
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error >>> " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return listSpeciality;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();

        Specialty objSpecialty = (Specialty) obj;

        boolean isUpdated = false;
        try {
            String sql = "UPDATE specialities SET name = ?, description = ? where id_specialty = ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);
            objPrepared.setString(1, objSpecialty.getName());
            objPrepared.setString(2, objSpecialty.getDescription());
            objPrepared.setInt(3, objSpecialty.getId());

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

        Specialty objSpecialty = (Specialty) obj;

        Connection objConnection = ConfigDB.openConnection();

        boolean isDeleted = false;
        try {

            String sql = "DELETE FROM specialities WHERE id_specialty = ?";
            // Create the prepareStatement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            // Give value to ?
            objPrepare.setInt(1, objSpecialty.getId());
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

    public static Specialty findById(int id){

        // Open the connection

        Connection objConnection = ConfigDB.openConnection();
        Specialty objSpecialty = null;
        try{                                         // ? = query parameter
            String sql = "SELECT * FROM specialities WHERE id_specialty = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //
            objPrepare.setInt(1, id);

            ResultSet objResult = objPrepare.executeQuery();

            if(objResult.next()){
                objSpecialty = new Specialty();
                objSpecialty.setName(objResult.getString("name"));
                objSpecialty.setDescription(objResult.getString("description"));
                objSpecialty.setId(objResult.getInt("id_specialty"));

            }
            ConfigDB.closeConnection();

        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, "ERROR >>>" + e.getMessage());
        }

        return objSpecialty;
    }
}
