package com.amp.acmedates.domain;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

@Entity
@Table(name = "date_product")
public class DateProduct implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    @SequenceGenerator(name="date_product_iddate_product_seq",
        sequenceName="date_product_iddate_product_seq", allocationSize=1)
    private Long id;

    @NotNull
    @Column(name = "name", unique = true)
    private String name;

    @NotNull
    @Column(name = "delivery_date", nullable = false)
    private ZonedDateTime deliveryDate;

    @Column(name = "declared_value", nullable = false)
    private Double declaredValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ZonedDateTime getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(ZonedDateTime deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Double getDeclaredValue() {
        return declaredValue;
    }

    public void setDeclaredValue(Double declaredValue) {
        this.declaredValue = declaredValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DateProduct that = (DateProduct) o;
        return id.equals(that.id) &&
            name.equals(that.name) &&
            deliveryDate.equals(that.deliveryDate) &&
            declaredValue.equals(that.declaredValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, deliveryDate, declaredValue);
    }

    @Override
    public String toString() {
        return "DateProduct{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", deliveryDate=" + deliveryDate +
            ", declaredValue=" + declaredValue +
            '}';
    }
}
