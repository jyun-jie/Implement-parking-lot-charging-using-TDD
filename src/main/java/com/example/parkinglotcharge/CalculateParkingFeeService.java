package com.example.parkinglotcharge;

import java.time.Duration;
import java.util.List;

public class CalculateParkingFeeService {


    private Duration FIFTEEN_MINUTES = Duration.ofMinutes(15L);
    private PriceBookRepositoryImpl priceBookRepository;
    private ParkingSessionRepository parkingSessionRepository;


    public CalculateParkingFeeService(PriceBookRepositoryImpl priceBookRepository, ParkingSessionRepository parkingSessionRepository) {
        //priceBook = new PriceBook();
        this.priceBookRepository = priceBookRepository;
        this.parkingSessionRepository = parkingSessionRepository;
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
    //將行為委託給entity 、以取代{資料操作}

    public long calcualte(String plate){

        //parkingSessionRepository.save(parkingSession1);
        ParkingSession parkingSession = parkingSessionRepository.find(plate);


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
