package xyz.seanchao.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * OrderItem: items corresponding to an order.
 * <p>
 * Order is ignored to avoid recursion hazard
 */
@Entity
@Table(name = "order_item")
@IdClass(OrderItemId.class)
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer",
        "fieldHandler", "order"})
public class OrderItem {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Orders order;

    @Id
    @Column(name = "book_id")
    private Integer book;

    @Basic
    @Column(name = "amount")
    int amount;

    public OrderItem() {
    }

    public OrderItem(Integer bookId, int amount) {
        this.book = (bookId);
        this.amount = amount;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public Integer getBook() {
        return book;
    }

    public void setBook(Integer book) {
        this.book = book;
    }


    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "order=" + order.getId() +
                ", book=" + book +
                ", amount=" + amount +
                '}';
    }
}

class OrderItemId implements Serializable {
    private Integer order;
    private Integer book;

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getBook() {
        return book;
    }

//    public void setBook(Integer book) {
//        this.book = book;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemId that = (OrderItemId) o;
        return Objects.equals(order, that.order) &&
                Objects.equals(book, that.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, book);
    }
}
