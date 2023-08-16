package pos.machine;

public class ReceiptItem {
    private String name;
    private int quantity;
    private int unitPrice;
    private int subTotal;

    public ReceiptItem(String name, int quantity, int unitPrice) {
        this.name = name;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public int getSubTotal() {
        return subTotal * quantity;
    }

    public void setSubTotal(int subTotal) {
        this.subTotal = subTotal;
    }
}
