package com.gwork.boot.statemachine.action;

import com.gwork.boot.statemachine.constant.EventEnum;
import com.gwork.boot.statemachine.constant.StateEnum;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

/**
 * @author jialiang.chen
 * @title: CoinAction
 * @projectName gwork
 * @date 2019/5/21 19:56
 */
@Component
public class CoinAction implements Action<StateEnum, EventEnum> {
    @Override
    public void execute(StateContext<StateEnum, EventEnum> context) {
        System.out.println(" coin coin coin ");
    }
}
