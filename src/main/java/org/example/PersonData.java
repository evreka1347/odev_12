package org.example;

import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface PersonData{
    @SqlUpdate
    void createTable();

    @SqlUpdate
    void insert(Person person);

    @SqlQuery
    List<Person> list();
    
    @SqlUpdate
    void delete(int i);
}
