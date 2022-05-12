package entities;

import java.io.File;

public class Item {
    private File photo;
    private String itemName;
    private String code;
    private String quantity;
    private String purchasePrice;
    private String priceUSD;
    private String priceEUR;

    public static Builder newEntity() {
        return new Item().new Builder();
    }

    public File getPhoto() {
        return photo;
    }

    public String getItemName() {
        return itemName;
    }

    public String getCode() {
        return code;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getPurchasePrice() {
        return purchasePrice;
    }

    public String getPriceUSD() {
        return priceUSD;
    }

    public String getPriceEUR() {
        return priceEUR;
    }

    public class Builder {
        private Builder() {}

        public Builder withPhoto(File photo) {
            Item.this.photo = photo;
            return this;
        }

        public Builder withItemName(String itemName) {
            Item.this.itemName = itemName;
            return this;
        }

        public Builder withCode(String code) {
            Item.this.code = code;
            return this;
        }

        public Builder withQuantity(String quantity) {
            Item.this.quantity = quantity;
            return this;
        }

        public Builder withPurchasePrice(String purchasePrice) {
            Item.this.purchasePrice = purchasePrice;
            return this;
        }

        public Builder withPriceUSD(String priceUSD) {
            Item.this.priceUSD = priceUSD;
            return this;
        }

        public Builder withPriceEUR(String priceEUR) {
            Item.this.priceEUR = priceEUR;
            return this;
        }

        public Item build(){
            return Item.this;
        }
    }
}
