package camt.se234.project.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String saleOrderId;
    @Builder.Default
    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<SaleTransaction> transactions = new ArrayList<>();

    public SaleOrder(String saleOrderId){
        this.saleOrderId = saleOrderId;
    }

    public SaleOrder(List<SaleTransaction> saleTransaction){
        this.transactions = saleTransaction;
    }

    public double getTotalPrice(){
        double totalPrice = 0;
        for (SaleTransaction transaction : transactions) {
            totalPrice += transaction.getAmount() * transaction.getProduct().getPrice();
        }
        return totalPrice;
    }

    public Long getId() {
        return id;
    }

    public String getSaleOrderId() {
        return saleOrderId;
    }

    public List<SaleTransaction> getTransactions() {
        return transactions;
    }
}