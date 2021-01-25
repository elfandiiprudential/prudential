package com.prudential.rental;

import com.prudential.rental.dbo.RedisClient;
//import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RentalTransaction {
    //@Transactional
    public static Map<String, Long> GetItems(){
        Map<String, Long> items = new HashMap<>();
        List<String> vehicles = RedisClient.Range("vehicles");
        for (String vehicle: vehicles){
            long stock = RedisClient.Get(vehicle);
            if (stock > 0L) {
                items.put(vehicle, stock);
            }
        }
        return items;
    }


    public static boolean TryAndRent(String vehicle){
        if (!RedisClient.Exist(vehicle)) {
            return false;
        }
        long res = RedisClient.Decr(vehicle);
        if (res < 0L){
            return false;
        }
        return true;
    }


}
