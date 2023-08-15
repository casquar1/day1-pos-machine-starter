package pos.machine;

import java.util.ArrayList;
import java.util.List;

public class PosMachine {
    public String printReceipt(List<String> barcodes) {
        return null;
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
}
