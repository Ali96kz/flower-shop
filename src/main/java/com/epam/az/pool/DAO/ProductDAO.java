package com.epam.az.pool.DAO;

import com.epam.az.pool.entity.Product;
import com.epam.az.pool.entity.SyntheticFlower;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDAO extends AbstractDAO<Product> {

    public int insert(Product product) {
        String sql = ("INSERT INTO Product(originId, name, description, type, price)" +
                "VALUES(" + product.getOrigin().getId() + ", \'" + product.getName() + "\',\'" +
                product.getDescription() + "\', \'" + product.getType()+", "+product.getPrice()+"\');");

        return executeSql(sql.toString());
    }

    @Override
    public void update(Product product) {
        String sql = "Update Product Set " +
                "originId =  "+product.getOrigin().getId()
                +", name = '"+product.getName()+"', " +
                " description = '"+product.getDescription()+"'"+
                ", type = '"+product.getType()+"';";
        executeSql(sql);
    }

    @Override
    public Product findById(int id) {
        String sql = "Select originId, name, description, type, price FROM Product where id = "+id+";";
        Product product = new Product();
        ResultSet resultSet = executeSqlQuery(sql);
        try {
            if (resultSet.next()){
                    parseResultSet(product,resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        product.setId(id);
        return product;
    }
}
