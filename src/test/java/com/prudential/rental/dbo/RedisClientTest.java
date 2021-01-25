package com.prudential.rental.dbo;

import com.prudential.rental.RentalApplication;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = RentalApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RedisClientTest {

    @Test
    void range() throws Exception{
        List<String> res = RedisClient.Range("test_range");
        assertEquals(res.get(0), "first_ele");
        assertEquals(res.get(1), "second_ele");
    }

    @Test
    void decr() throws Exception{
        long res = RedisClient.Get("test_decr");
        res -= 1L;
        long decr = RedisClient.Decr("test_decr");
        assertEquals(res, decr);
        // should reset decr though
    }

    @Test
    void get() throws Exception{
        long res = RedisClient.Get("test_get");
        assertEquals(res, 5L);
    }

    @Test
    void exist() throws Exception{
        boolean exist = RedisClient.Exist("test_get");
        boolean not_exist = RedisClient.Exist("this_key_doest_not_exist");
        assertEquals(exist, true);
        assertEquals(not_exist, false);
    }
}