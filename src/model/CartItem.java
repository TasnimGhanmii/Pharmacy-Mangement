package model;

// CartItem Class
public class CartItem {
    private int medId;
    private double price;
    private int nbUnits;
    private double totalPrice;

    public CartItem(int medId, double price, int nbUnits, double totalPrice) {
        this.medId = medId;
        this.price = price;
        this.nbUnits = nbUnits;
        this.totalPrice = totalPrice;
    }

    public int getMedId() {
        return medId;
    }

    public double getPrice() {
        return price;
    }

    public int getNbUnits() {
        return nbUnits;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}