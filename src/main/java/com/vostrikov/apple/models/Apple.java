package com.vostrikov.apple.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.text.DecimalFormat;

@Entity
public class Apple {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message="color should not by empty")
    private ColorType color;

    @NotEmpty(message="weight should not by empty")
    @Min(value=0, message="weight should not by greater then 0")
    @Digits(integer=10, fraction=2)
    private Float weight;

    @NotEmpty(message="sort should not by empty")
    private String sort;
    private Boolean sour;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ColorType getColor() {
        return color;
    }

    public void setColor(ColorType color) {
        this.color = color;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Boolean getSour() {
        return sour;
    }

    public void setSour(Boolean sour) {
        this.sour = sour;
    }

    public Apple() {
    }

    public Apple(Float weight, ColorType color, String sort, Boolean sour) {
        this.weight = weight;
        this.color = color;
        this.sort = sort;
        this.sour = sour;
    }
}
