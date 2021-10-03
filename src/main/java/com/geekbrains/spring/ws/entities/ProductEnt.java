package com.geekbrains.spring.ws.entities;

import lombok.*;
import javax.persistence.*;
import java.util.Objects;

@Data
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class ProductEnt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private Integer price;

    @ManyToOne
    @JoinColumn
    private CategoryEnt category;

    @Column
    private String picturePath;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEnt productEnt = (ProductEnt) o;
        return title.equals(productEnt.title) && price.equals(productEnt.price) && Objects.equals(picturePath, productEnt.picturePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, price, picturePath);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", picturePath='" + picturePath + '\'' +
                '}';
    }
}
