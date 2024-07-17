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

    //Repository 的職責、物件生命週期
    // ex :calculate(ParkingSession parkingSession)
    // Solution : Repository + Factory
    //  TIPS:
    // 先HARD-CODE 幫你刻出形狀讓IDE幫你填上
    // 用新測項來幫你描述邏輯 (RED)
    // 改寫成真實邏輯 (GREEN)
    // 如有需要，重構(REFACTOR)
    //將行為委託給entity 、以取代{資料操作}


    public long calcualte(String plate){

        ParkingSession parkingSession = parkingSessionRepository.find(plate);
        if(parkingSession==null){
            return 0L;
        }



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
