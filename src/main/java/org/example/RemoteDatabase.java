package org.example;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public class RemoteDatabase {
    private static final String URL = "jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7723553";
    private static final String USER = "sql7723553";
    private static final String PASSWORD = "ty8Ltg6r9Z";

    public static void main(String[] args){

        Jdbi jdbi = Jdbi.create(URL, USER, PASSWORD);
        jdbi.installPlugin(new SqlObjectPlugin());

        jdbi.useExtension(PersonData.class, dao -> dao.createTable());

        jdbi.useExtension(PersonData.class, dao -> {
            dao.insert(new Person(1, "Bihter", "Yöreoglu", "bihter@yoreoglu.com"));
            dao.insert(new Person(2,"Behlül","Haznedar","behlul@haznedar.com"));
            dao.insert(new Person(3,"Adnan","Ziyagil","adnan@ziyagil.com"));
        });

        jdbi.useExtension(PersonData.class, dao -> {
            List<Person> persons = dao.list();
            for (Person person : persons){
                System.out.println(person);
            }
        });

        jdbi.useExtension(PersonData.class, dao -> dao.delete(2));
    }


    public interface PersonData{

        @SqlUpdate
        void createTable();

        @SqlUpdate
        void insert(Person person);

        @SqlQuery
        List<Person> list();

        @SqlUpdate
        void delete();
    }

    public static class Person {
        private int id;
        private String name;
        private String surname;
        private String email;

        public Person(int id, String name, String surname, String email) {
            this.id = id;
            this.name = name;
            this.surname = surname;
            this.email = email;
        }

        @Override
        public String toString() {
            return "Person {" +
                    "id:" + id +
                    ", name" + name +
                    ", surname" + surname +
                    ", email" + email +
                    "}";
        }
    }
}

