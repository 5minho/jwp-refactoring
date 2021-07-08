package kitchenpos.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import kitchenpos.product.domain.Price;
import kitchenpos.product.domain.Product;

@Entity
@Table
public class MenuProduct {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "menu_id", nullable = false, foreignKey = @ForeignKey(name = "fk_menu_product_menu"))
    private Menu menu;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false, foreignKey = @ForeignKey(name = "fk_menu_product_product"))
    private Product product;

    @Column(nullable = false)
    private long quantity;

    protected MenuProduct() { }

    public MenuProduct(Product product, long quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public long getQuantity() {
        return quantity;
    }

    public Menu getMenu() {
        return menu;
    }

    public Product getProduct() {
        return product;
    }

    Price calculatePrice() {
        return product.getPrice().times(quantity);
    }

    void toMenu(Menu menu) {
        this.menu = menu;
    }
}
