package com.example.parkinglotcharge;

public class PriceBookRepositoryImpl implements PriceBookRepository {
    private PriceBook priceBook;

    public PriceBookRepositoryImpl(PriceBook priceBook) {
        this.priceBook = priceBook;
    }

    public PriceBook getPriceBook() {
        return priceBook;
    }

    public void setPriceBook(PriceBook priceBook) {
        this.priceBook = priceBook;
    }
}
