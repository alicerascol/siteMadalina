package com.sitelogin.Site.domain;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

// ar trebui sa existe niste validari pe obiect
// email - sa aiba forma de unui email
// password - sa aiba cel putin 8 caractere, din care 2 cifre si o litera mare etc
// hint: regex, anotari de validare


//hibernate a creat tabela in baza de date - bad practice

//We have been using Hibernate to generate DB structures for years
// in multiple projects and multiple environments and we never had
// an issue with it. But while this approach is great for creating
// new stuff (tables, columns, indexes, etc.), it cannot be used for
// modifying or removing existing stuff. That's why we started using
// Flyway (for modification of existing structures).

//tabele trebuie create folosind migrari -> pentru asta folosim flyway

@Entity
@Table(name = "user_tbl", uniqueConstraints=@UniqueConstraint(columnNames={"email"}))
public class User {

    @NotNull(message = "Last name cannot be null")
    private String lastName;

    @NotNull(message = "First name cannot be null")
    private String firstName;

    @Id
//    din cate am citit, sql nu stie de auto_increment, el stie de identity
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @NotNull(message = "Username cannot be null")
    private String userName;

//    am comentat asta sa nu mai fiu nevoita sa bag 8 caractere de fiecare data
//    @Size(min = 8, message = "Password should have at least 8 characters")
    private String password;

    @Email (message = "Enter a valid email address")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    public User(String lastName, String firstName, int ID, String userName, String password, String email) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.ID = ID;
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    public User(){}

    @Column(name = "id", nullable = false)
    public int getID() {
        return ID;
    }

    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "first_name", nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // You should use camel case for the properties, the dialect could be translating
    // them to underscore separated names when you use non-camel case
    @Column(name = "user_name", unique = true, nullable = false)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "email", nullable = false, unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
