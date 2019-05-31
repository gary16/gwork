package com.gwork.app.springdemo.statemchine.handle;

import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;

/**
 * @author jialiang.chen
 * @title: StateHandle
 * @projectName gwork
 * @date 2019/5/20 11:23
 */
@WithStateMachine
public class StateHandler {

    @OnTransition(target = "STATE1")
    void toState1() {
        System.out.println(" to state 1 ");
    }

    @OnTransition(target = "STATE2")
    void toState2() {
        System.out.println(" to state 2 ");
    }

}