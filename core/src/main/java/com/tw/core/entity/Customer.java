package com.tw.core.entity;
import javax.persistence.*;

/**
 * Created by twer on 7/19/15.
 */
@Entity
@Table(name="Customer")
public class Customer {

    @Column(name = "customer")

    private String customer;
    @Column(name = "coach")

    private String coach;

    @Id
    @GeneratedValue
    @Column(name = "idCustomer")
    private Integer idCustomer;

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public Integer getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Integer idCustomer) {
        this.idCustomer = idCustomer;
    }
}
