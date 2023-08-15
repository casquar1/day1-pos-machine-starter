package pos.machine;

import java.util.List;

public class Receipt {
    private List<ReceiptItem> receiptItems;
    private int totalPrice;

    public Receipt(List<ReceiptItem> receiptItems, int totalPrice) {
        this.receiptItems = receiptItems;
        this.totalPrice = totalPrice;
    }

    public List<ReceiptItem> getReceiptItems() {
        return receiptItems;
    }

    public void setReceiptItems(List<ReceiptItem> receiptItems) {
        this.receiptItems = receiptItems;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
