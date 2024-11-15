//package com.example.buoi1.model;
//
//import jakarta.persistence.*;
//
//@Table(name = "USER_DEMO")
//@Entity
//public class UserDemo {
//    @Id
//    @Column
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private int id;
//    @Column
//    private String firstName;
//    @Column
//    private String lastName;
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//}
package com.example.buoi1.model;

import jakarta.persistence.*;

@Entity
@Table(name = "USER_DEMO")
public class UserDemo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    // Getters and Setters for fields, including 'company'
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
