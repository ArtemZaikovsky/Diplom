package com.degree.GraduateWork.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String goods;
    private Integer quantity;
    private Integer price;
    private String comment;

    @OneToOne
    private RequestStatus status;

    @ManyToOne()
    private User user;

    public Request(String goods, Integer quantity, Integer price, String comment, User user) {
        this.goods = goods;
        this.quantity = quantity;
        this.price = price;
        this.comment = comment;
        this.user = user;
    }

    public Request(String goods, Integer quantity, Integer price) {
        this.goods = goods;
        this.quantity = quantity;
        this.price = price;
    }

    public void setRequestStatus(String status) {
        this.status = new RequestStatus(status);
    }
}
