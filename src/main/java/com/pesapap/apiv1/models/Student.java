package com.pesapap.apiv1.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "Student")
public class Student {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "STUDENT_NAME", length = 100, nullable = false)
    private String fullName;

    @Column(name = "REGISTRATION_ID", length = 50, nullable = false)
    private String registrationId;

    @Column(name = "COURSE_NAME", length = 50, nullable = false)
    private String courseName;

    @JsonIgnore
    @Column(name = "ANNUAL_FEE", length = 50, nullable = false)
    private Double annualFee;

    @Column(name = "PAID_FEES", length = 50)
    private Double paidFees;
    @Transient
    private Double feeBalance;

    @Column(name = "PAYMENT_CHANNEL", length = 50)
    private String paymentChannel;

}
