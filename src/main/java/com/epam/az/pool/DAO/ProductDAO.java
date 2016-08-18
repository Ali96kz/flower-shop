package com.epam.az.pool.DAO;

import com.epam.az.pool.entity.Product;

public class ProductDAO extends AbstractDAO<Product> {

    public int insert(Product product) {
        String sql = ("INSERT INTO Product(originId, name, description, type)" +
                "VALUES(" + product.getOrigin().getId() + ", \'" + product.getName() + "\',\' " +
                product.getDescription() + "\', \'" + product.getType() +"\');");

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

}
