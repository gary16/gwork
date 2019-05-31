package com.gwork.boot;

import com.gwork.boot.statemachine.service.StateMachineService;
import com.gwork.boot.statemachine.service.impl.StateMachineServiceImpl;
import com.gwork.boot.utils.SpringContextUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 */
@SpringBootApplication
public class AppRun {

  public static void main(String[] args) throws InterruptedException {

      SpringApplication.run(AppRun.class, args);
      handle();
  }

  public static void handle() throws InterruptedException {
      for(;;){
          StateMachineService service = SpringContextUtils.getBean(StateMachineServiceImpl.class);
          service.go2State1();
          Thread.sleep(2000);
      }
  }


}