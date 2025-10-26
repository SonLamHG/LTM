package RMI;

import java.io.Serializable;

public class ProductX implements Serializable {
    private static final long serialVersionUID = 20171107L;

    public String id;
    public String code;
    public String discountCode;
    public int discount;

    public ProductX(String id, String code, String discountCode, int discount) {
        this.id = id;
        this.code = code;
        this.discountCode = discountCode;
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "ProductX{" +
               "id='" + id + '\'' +
               ", code='" + code + '\'' +
               ", discountCode='" + discountCode + '\'' +
               ", discount=" + discount +
               '}';
    }
}
    