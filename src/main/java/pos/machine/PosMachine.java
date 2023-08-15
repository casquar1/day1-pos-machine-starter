package pos.machine;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PosMachine {
    public String printReceipt(List<String> barcodes) {
        List<ReceiptItem> receiptItems =decodeToItems(barcodes);
        Receipt receipt = calculateCost(receiptItems);
        return renderReceipt(receipt);
    }

    private List<ReceiptItem> decodeToItems(List<String> barcodes) {
        List<Item> items = ItemsLoader.loadAllItems();
        List<ReceiptItem> receiptItems = new ArrayList<>();

        items.forEach(item -> {
            int quantity = (int) barcodes.stream().filter(barcode -> barcodes.equals(item.getBarcode())).count();
            ReceiptItem receiptItem = new ReceiptItem(item.getName(), quantity, item.getPrice(), 0);
            receiptItems.add(receiptItem);
        });
        return receiptItems;
    }

    private List<ReceiptItem> calculateItemsCost(List<ReceiptItem> receiptItems) {
        receiptItems.forEach(receiptItem -> receiptItem.setSubTotal(receiptItem.getUnitPrice() * receiptItem.getQuantity()));
        return receiptItems;
    }
}
