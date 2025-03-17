/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.services;
import com.tth.pojo.Category;
import com.tth.pojo.Choice;
import com.tth.pojo.JdbcUtils;
import com.tth.pojo.Question;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

/**
 *
 * @author admin
 */
public class CategoryServices {
    public List<Category> getCategories() throws SQLException {
        
        List<Category> cates = new ArrayList<>();
        try(Connection conn = JdbcUtils.getConn()){
            PreparedStatement stm = conn.prepareCall("SELECT * FROM category ");
            
            
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Category c = new Category(rs.getInt("id"),rs.getString("name"));
                cates.add(c);
            }
            
        return cates;
        }
    }
    
    
    
    
    
}