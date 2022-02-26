package com.rs.market.persistence.entity;

import javax.persistence.*;

@Entity
@Table(name = "compras_productos")
public class ShoppingProduct {
    @EmbeddedId
    private ShoppingProductPK id;

    @Column(name = "cantidad")
    private Integer quantity;

    private Double total;

    @Column(name = "estado")
    private Boolean state;

    @ManyToOne
    @MapsId("idPurchase")
    @JoinColumn(name = "id_compra", insertable = false ,updatable = false)
    private Purchase purchase;

    @ManyToOne
    @JoinColumn(name = "id_producto",insertable = false, updatable = false)
    private Product product;



    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ShoppingProductPK getId() {
        return id;
    }

    public void setId(ShoppingProductPK id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }
}
