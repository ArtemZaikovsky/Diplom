package com.degree.GraduateWork.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestStatus {
    @Id
    private Long id;
    private String status;

    @OneToOne(mappedBy = "status")
    @Transient
    private Request request;

    public RequestStatus(String status) {
        if(status.contains("В обработке")){
            this.id = 1L;
        } else if (status.contains("Одобрено.")){
            this.id = 2L;
        } else if (status.contains("Отклонено.")){
            this.id = 3L;
        }
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }
}
