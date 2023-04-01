package com.exemple.model;

import java.sql.SQLException;
import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class productDAO extends  BaseDAO<product> {
    public productDAO() throws SQLException {
    }

    @Override
    public void save(product object) throws SQLException {

        String req = "insert into produits ( name , description, price) values (? , ?, ?) ;";


        this.preparedStatement = this.connection.prepareStatement(req);

        this.preparedStatement.setString(1, object.getName());
        this.preparedStatement.setString(2, object.getDescription());
        this.preparedStatement.setFloat(3, object.getPrice());


        this.preparedStatement.execute();

    }

    @Override
    public void update(product object) throws SQLException {

    }

    @Override
    public void delete(product object) throws SQLException {
        if (this.connection.isClosed()) {
            throw new SQLException("Connection is closed");
        }

        System.out.println("Deleting product with ID " + object.getProd_id());

        String query = "DELETE FROM produits WHERE prod_id = ?";
        preparedStatement = this.connection.prepareStatement(query);
        this.preparedStatement.setLong(1 , object.getProd_id());
        this.preparedStatement.execute();
    }

    @Override
    public product getOne(Long id) throws SQLException {
        return null;
    }

    @Override
    public List<product> getAll() throws SQLException {

        List<product> mylist = new ArrayList<product>();
        String req = " select * from produits";


        this.statement = this.connection.createStatement();

        this.resultSet = this.statement.executeQuery(req);

        while (this.resultSet.next()) {

            mylist.add(new product(this.resultSet.getInt(1), this.resultSet.getString(2),
                    this.resultSet.getString(3), this.resultSet.getString(4)));

        }

        return mylist;
    }
}