package camt.se234.project.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String transactionId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    SaleOrder order;
    @OneToOne(fetch = FetchType.EAGER)
    Product product;
    int amount;

    public SaleTransaction( Product product, int amount) {
        this.product = product;
        this.amount = amount;
    }
    public Long getId() {
        return id;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public SaleOrder getOrder() {
        return order;
    }

    public Product getProduct() {
        return product;
    }

    public int getAmount() {
        return amount;
    }

    public void setOrder(SaleOrder order) {
        this.order = order;
    }


}