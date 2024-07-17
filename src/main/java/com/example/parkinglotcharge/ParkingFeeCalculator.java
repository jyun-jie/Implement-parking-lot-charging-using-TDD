package com.example.parkinglotcharge;

import java.time.Duration;
import java.util.List;

public class ParkingFeeCalculator {


    private Duration FIFTEEN_MINUTES = Duration.ofMinutes(15L);
    private PriceBookRepository priceBookRepository;



    public ParkingFeeCalculator(PriceBookRepository priceBookRepository) {
        //priceBook = new PriceBook();
        this.priceBookRepository = priceBookRepository;

    }
    //topic : 如何製作一個 汽車計算機
    //停車場
    //15分鐘免費
    //平日
    // 每日60元 (以半小時收費)
    //當日上限 150 (隔天另計)
    //假日
    //每小時 100元 (以半小時收費)
    //每日上限2400 (隔日另計)

    //物件生成與計算邏輯的耦合
    // ex : new PriceBook()
    // solution : Repository Pattern ;

    public long calcualte(ParkingSession parkingSession){

        PriceBook priceBook = priceBookRepository.getPriceBook();

        Duration duration = parkingSession.getTotalDuration();

        if (isShort(duration)) {
            return 0L;
        }

        List<DailySession> dailySessions = parkingSession.getDailySessions();

        return dailySessions.stream().mapToLong(priceBook::getDailyFee).sum();

    }

    private boolean isShort(Duration duration) {
        return duration.compareTo(FIFTEEN_MINUTES) <= 0;
    }


}
