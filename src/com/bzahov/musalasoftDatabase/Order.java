package com.bzahov.musalasoftDatabase;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name="Orders")
public class Order {
    private int id;
    private Timestamp purchaseDate;
    private BigDecimal totalPrice;
    private boolean isCheckout;
    private Customers customersByCustomerId;
    private Currencies currenciesByCurrencyType;
    private DeliveryType deliveryTypeByDeliveryType;
    private Commands commandsByCommandId;

    @Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "PurchaseDate")
    public Timestamp getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Timestamp purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    @Basic
    @Column(name = "TotalPrice")
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Basic
    @Column(name = "IsCheckout")
    public boolean isCheckout() {
        return isCheckout;
    }

    public void setCheckout(boolean checkout) {
        isCheckout = checkout;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order orders = (Order) o;
        return id == orders.id &&
                isCheckout == orders.isCheckout &&
                Objects.equals(purchaseDate, orders.purchaseDate) &&
                Objects.equals(totalPrice, orders.totalPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, purchaseDate, totalPrice, isCheckout);
    }

    @ManyToOne
    @JoinColumn(name = "CustomerID", referencedColumnName = "ID", nullable = false)
    public Customers getCustomersByCustomerId() {
        return customersByCustomerId;
    }

    public void setCustomersByCustomerId(Customers customersByCustomerId) {
        this.customersByCustomerId = customersByCustomerId;
    }

    @ManyToOne
    @JoinColumn(name = "CurrencyType", referencedColumnName = "ID", nullable = false)
    public Currencies getCurrenciesByCurrencyType() {
        return currenciesByCurrencyType;
    }

    public void setCurrenciesByCurrencyType(Currencies currenciesByCurrencyType) {
        this.currenciesByCurrencyType = currenciesByCurrencyType;
    }

    @ManyToOne
    @JoinColumn(name = "DeliveryType", referencedColumnName = "ID", nullable = false)
    public DeliveryType getDeliveryTypeByDeliveryType() {
        return deliveryTypeByDeliveryType;
    }

    public void setDeliveryTypeByDeliveryType(DeliveryType deliveryTypeByDeliveryType) {
        this.deliveryTypeByDeliveryType = deliveryTypeByDeliveryType;
    }

    @ManyToOne
    @JoinColumn(name = "CommandID", referencedColumnName = "ID", nullable = false)
    public Commands getCommandsByCommandId() {
        return commandsByCommandId;
    }

    public void setCommandsByCommandId(Commands commandsByCommandId) {
        this.commandsByCommandId = commandsByCommandId;
    }
}
