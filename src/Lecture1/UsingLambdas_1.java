package Lecture1;

//import lombok.*;

//import java.util.function.Function;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.function.Function;

/**
 * Created by user on 28.11.18.
 */
public class UsingLambdas_1 {
    public static void main(String[] args) {

        Person person = new Person("Vasia", "asd@asd.com");
        User user = personToUser(person);
        System.out.println("-------convenient mode---------");
        System.out.println("user- "+user);


        System.out.println("----- mode with function ---------");
        Function<Person, User> function = new Function<Person, User>() {
            @Override
            public User apply(Person person) {
                User user = new User();

                user.setId(person.getName().hashCode()+person.getEmail().hashCode()+"");
                user.setLogin(person.getEmail());

                return user;
            }
        };

        User user1 = function.apply(new Person("Nazar", "nazar_lw@ukr.net"));
        System.out.println("user1 - "+user1);

        System.out.println("------- mode with Lambdas---------");

        Function<Person, User> function1 = (p)->{

            User u = new User();

            u.setId(p.getName().hashCode()+p.getEmail().hashCode()+"");
            u.setLogin(p.getEmail());
            return u;
        };

        User user2 = function1.apply(new Person("Galia", "g@ukr.net"));
        System.out.println(user2);
    }

    public static User personToUser(Person person){
        User user = new User();

        user.setId(person.getName().hashCode()+person.getEmail().hashCode()+"");
        user.setLogin(person.getEmail());

        return user;
    }
}

//@Getter
//@Setter
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
class Person{

    private String name;
    private String email;

    public Person() {
    }

    public Person(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (name != null ? !name.equals(person.name) : person.name != null) return false;
        return email != null ? email.equals(person.email) : person.email == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

//@Data
class User{
    private String id;
    private String login;

    public User() {
    }

    public User(String id, String login) {
        this.id = id;
        this.login = login;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        return login != null ? login.equals(user.login) : user.login == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", login='" + login + '\'' +
                '}';
    }
}