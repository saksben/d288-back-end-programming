package com.assessment.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Getter
@Setter
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;

    @NotBlank(message = "First name is mandatory")
    @Column(name = "customer_first_name")
    private String firstName;

    @NotBlank(message = "Last Name is mandatory")
    @Column(name = "customer_last_name")
    private String lastName;

    @NotBlank(message = "Address is mandatory")
    @Column(name = "address")
    private String address;

    @NotBlank(message = "Postal Code is mandatory")
    @Size(min=5, max=5, message = "Postal Code must be 5 numbers long")
    @Positive(message = "Postal Code must be a positive number")
    @Column(name = "postal_code")
    private String postal_code;

    @NotBlank(message = "Phone number is mandatory")
    @Size(min=10, max=10, message = "Phone number must be 10 numbers long in the US (exclude country code)")
    @Positive(message = "Phone number must be a positive number")
    @Column(name = "phone")
    private String phone;

    @Column(name = "create_date")
    @CreationTimestamp
    private Date create_date;

    @Column(name = "last_update")
    @UpdateTimestamp
    private Date last_update;

    @ManyToOne
    @JoinColumn(name = "division_id")
    private Division division;

    @OneToMany(mappedBy = "customer", cascade={CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Cart> carts;

    public Customer() {
    }

    public void add(Cart cart) {
        if (cart != null) {
            if (carts == null) {
                carts = new HashSet<>();
            }
            carts.add(cart);
            cart.setCustomer(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        return Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
