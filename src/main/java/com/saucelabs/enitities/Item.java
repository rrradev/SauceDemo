package com.saucelabs.enitities;

public class Item {

    private final String name;
    private final String desc;
    private final String price;

    private Item(ItemBuilder builder) {
        this.name = builder.name;
        this.desc = builder.desc;
        this.price = builder.price;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public String getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", price=" + price +
                '}';
    }

    public static class ItemBuilder {

        private final String name;
        private String desc;
        private String price;

        public ItemBuilder(String name) {
            this.name = name;
        }

        public ItemBuilder desc(String desc) {
            this.desc = desc;
            return this;
        }

        public ItemBuilder price(String price) {
            this.price = price;
            return this;
        }

        public Item build() {
            return new Item(this);
        }
    }
}
