package com.exemple.model;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class creditDAO extends  BaseDAO<credit> {
    public creditDAO() throws SQLException {

        super();
    }

    @Override
    public void save(credit object) throws SQLException {

        String req = "insert into credits ( client_id , product_id) values (? , ?) ;";


        this.preparedStatement = this.connection.prepareStatement(req);
        this.preparedStatement.setLong(1, object.getClient_id());

        this.preparedStatement.setLong(2, object.getProduit_id());



        this.preparedStatement.execute();
        System.out.println("credit saved to data base ");
    }

    @Override
    public void update(credit object) throws SQLException {

    }

    @Override
    public void delete(credit object) throws SQLException {

    }

    @Override
    public credit getOne(Long id) throws SQLException {
        return null;
    }

    @Override
    public List<credit> getAll() throws SQLException{

        List<credit> mylist = new ArrayList<credit>();
        String req = " select * from credits" ;


        this.statement = this.connection.createStatement();

        this.resultSet =  this.statement.executeQuery(req);

        while (this.resultSet.next()){

            mylist.add( new credit(this.resultSet.getLong(1) ,
                    this.resultSet.getLong(2),
                    this.resultSet.getLong(3)

                    ));


        }

        return mylist;
    }
}
