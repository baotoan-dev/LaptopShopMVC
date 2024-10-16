<<<<<<< HEAD
package vn.hbtoan.laptopshop.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderDetail {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long quantity;
    private double price;

    // order_id
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    // product_id
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
=======
package vn.hbtoan.laptopshop.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderDetail {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long quantity;
    private double price;

    // order_id
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    // product_id
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
>>>>>>> 893de404eba84b784dead3bb210b439cd81f42f2
