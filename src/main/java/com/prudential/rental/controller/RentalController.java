package com.prudential.rental.controller;

import com.prudential.rental.RentalTransaction;
import com.prudential.rental.dbo.RedisClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.model.IModel;
import redis.clients.jedis.Jedis;

import java.nio.charset.StandardCharsets;
import java.util.Map;

@Controller
public class RentalController {
    @RequestMapping(value = "/" ,method = RequestMethod.GET)
    public String GetSlash(){
        //  Jedis jedis = RedisClient.Initialize();
        return "Hi";
    }

    @RequestMapping(value = "/out_of_stock" ,method = RequestMethod.GET)
    public String OutOfStock(){
        return "out_of_stock";
    }

    @RequestMapping(value = "/ok" ,method = RequestMethod.GET)
    public String Ok(){
        return "ok";
    }

    @RequestMapping(value = "/index" ,method = RequestMethod.GET)
    public String GetRental(Model model){
        Map<String, Long> vehicles = RentalTransaction.GetItems();
        model.addAttribute("vehicles", vehicles);
        return "index";
    }

    @RequestMapping(value = "/submit" ,method = RequestMethod.POST)
    public String submit(@RequestParam("vehicle") String vehicle){
        boolean success = RentalTransaction.TryAndRent(vehicle);
        if (!success) {
            return "redirect:out_of_stock";
        }
        else
            return "redirect:ok";
    }
}
