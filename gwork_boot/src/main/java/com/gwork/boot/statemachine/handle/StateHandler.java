package com.gwork.boot.statemachine.handle;

import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;

/**
 * @author jialiang.chen
 * @title: StateHandle
 * @projectName gwork
 * @date 2019/5/20 11:23
 */
//@WithStateMachine(id="stateHandler")
public class StateHandler {

    @OnTransition(target = "LOCKED")
    void toState1() {
        //System.out.println(" to state 1 ");
    }

    @OnTransition(target = "UNLOCKED")
    void toState2() {
        //System.out.println(" to state 2 ");
    }

}