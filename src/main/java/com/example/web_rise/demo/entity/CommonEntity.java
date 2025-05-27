package com.example.web_rise.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "test_id_gen")
    @SequenceGenerator(name = "test_id_gen", sequenceName = "test_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "create_date")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    protected Date createDate;

    @Column(name = "update_date")
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    protected Date updateDate;

//    @PrePersist
//    protected void prePersist() {
//        updateCreateDate();
//    }
//
//    @PreUpdate
//    protected void preUpdate() {
//        updateUpdateDate();
//    }

    private void updateCreateDate() {
        createDate = new Date();
    }

    private void updateUpdateDate() {
        updateDate = new Date();

    }

}
