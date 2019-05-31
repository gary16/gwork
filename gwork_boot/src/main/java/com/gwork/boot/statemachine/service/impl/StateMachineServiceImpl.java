package com.gwork.boot.statemachine.service.impl;

import com.gwork.boot.statemachine.constant.EventEnum;
import com.gwork.boot.statemachine.constant.StateEnum;
import com.gwork.boot.statemachine.service.StateMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Service;

/**
 * @author jialiang.chen
 * @title: StateMachineServiceImpl
 * @projectName gwork
 * @date 2019/5/20 11:34
 */
@Service
public class StateMachineServiceImpl implements StateMachineService {

    @Autowired
    StateMachine<StateEnum, EventEnum> stateMachine;

    @Override
    public void go2State1() {
        stateMachine.start();
        stateMachine.sendEvent(EventEnum.COIN);
        stateMachine.stop();
    }

    @Override
    public void go2State2() {
        stateMachine.start();
        stateMachine.sendEvent(EventEnum.PUSH);
        stateMachine.stop();
    }
}
