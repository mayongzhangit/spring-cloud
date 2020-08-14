package com.myz.user.provider.resource;

import com.myz.user.api.UserApi;
import com.myz.user.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Random;

/**
 * @author yzMa
 * @desc
 * @date 2020/7/28 20:59
 * @email 2641007740@qq.com
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserResource implements UserApi {

    private RestTemplate restTemplate =  new RestTemplate(); // 该出的restTemplate不会被trace的

    @Resource(name = "myRestTemplate")
    private RestTemplate myRestTemplate;

    Random random = new Random();

    @GetMapping("/stress-test")
    public String stressTest(@RequestParam Long cost,boolean exFlag){
        if (exFlag) throw new RuntimeException("manual ex");

        try {
            Thread.sleep(cost);
        } catch (InterruptedException e) {
            log.error("user ex",e);
        }
        return "test success";
    }

    /**
     *
     * @param userDto
     * @return
     */
    @PostMapping("/save")
    public Long saveUser(@RequestBody UserDto userDto) {
        log.info("userDto={}",userDto);
        return userDto.getId();
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/get/{id}")
    public UserDto getById(@PathVariable Long id) throws InterruptedException {
        log.info("user#getById entry");


        ResponseEntity<String> forEntity = myRestTemplate.getForEntity("https://www.baidu.com", String.class);
        System.out.println(forEntity.getBody());
        int sleepTime = random.nextInt(5000);

        Thread.sleep(sleepTime);

        UserDto userDto = new UserDto();
        userDto.setId(id);
        userDto.setName("name"+id);
        userDto.setBirthday(new Date());
        userDto.setSleepTime(sleepTime);
        log.info("user#getById exit");
        return userDto;
    }
}
