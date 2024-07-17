package com.example.parkinglotcharge;

public class PriceBookRepository {
    private PriceBook priceBook;

    public PriceBookRepository(PriceBook priceBook) {
        this.priceBook = priceBook;
    }

    public PriceBook getPriceBook() {
        return priceBook;
    }

    public void setPriceBook(PriceBook priceBook) {
        this.priceBook = priceBook;
    }
}
