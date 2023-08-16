package pos.machine;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PosMachine {
    public String printReceipt(List<String> barcodes) {
        List<ReceiptItem> receiptItems = decodeToItems(barcodes);
        Receipt receipt = calculateCost(receiptItems);
        return renderReceipt(receipt);
    }

    private List<ReceiptItem> decodeToItems(List<String> barcodes) {
        List<Item> items = ItemsLoader.loadAllItems();
        List<ReceiptItem> receiptItems = new ArrayList<>();

        items.forEach(item -> {
            int quantity = (int) barcodes.stream().filter(barcode -> barcode.equals(item.getBarcode())).count();
            ReceiptItem receiptItem = new ReceiptItem(item.getName(), quantity, item.getPrice());
            receiptItems.add(receiptItem);
        });
        return receiptItems;
    }

    private List<ReceiptItem> calculateItemsCost(List<ReceiptItem> receiptItems) {
        receiptItems.forEach(receiptItem -> receiptItem.setSubTotal(receiptItem.getUnitPrice()));
        return receiptItems;
    }

    private int calculateTotalPrice(List<ReceiptItem> receiptItems) {
        return receiptItems.stream().mapToInt(ReceiptItem::getSubTotal).sum();
    }

    private Receipt calculateCost(List<ReceiptItem> receiptItems) {
        List<ReceiptItem> receiptItemsWithSubtotal = calculateItemsCost(receiptItems);
        return new Receipt(receiptItemsWithSubtotal, calculateTotalPrice(receiptItemsWithSubtotal));
    }

    private String generateReceipt(String itemsReceipt, int totalPrice) {
        StringBuilder receiptOutput = new StringBuilder();
        return receiptOutput.append("***<store earning no money>Receipt***\n")
                .append(itemsReceipt)
                .append("----------------------\n")
                .append("Total: ").append(totalPrice).append(" (yuan)\n")
                .append("**********************").toString();
    }

    private String generateItemsReceipt(Receipt receipt) {
        return receipt.getReceiptItems().stream()
                .map(item -> "Name: " + item.getName() + ", Quantity: " + item.getQuantity() + ", Unit price: "
                        + item.getUnitPrice() + " (yuan)" + ", Subtotal: " + item.getSubTotal() + " (yuan)")
                .collect(Collectors.joining("\n"));
    }

    private String renderReceipt(Receipt receipt) {
        String itemsReceipt = generateItemsReceipt(receipt);
        return generateReceipt(itemsReceipt, receipt.getTotalPrice());
    }
}