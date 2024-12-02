public class Block {
    private int quantity;
    private int price;

    public Block(int quantity, int price) {
        this.quantity = quantity;
        this.price = price;
    }

    // Getters
    public int getQuantity() {
        return this.quantity;
    }

    public int getPrice() {
        return this.price;
    }

    // Setters
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    // method to reduce the quantity directly on the block
    public void sellShares(int amount) {
        if (amount <= this.quantity) {
            this.quantity -= amount;
        }
        else {
            System.out.println("Cannot sell remaining shares!");
        }
    }
}

