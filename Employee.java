package com.example.csvuploader.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import javax.persistence.*;
import java.util.Date;

@Table(name="tbl_employee")
@Entity
@Setter
@Getter
@ToString
public class Employee {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;

    @CreationTimestamp
    @Column(name="created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name="updated_at")
    private Date updatedAt;
}
