package com.gwork.app.springdemo.config;

import com.gwork.app.springdemo.statemchine.constant.EventEnum;
import com.gwork.app.springdemo.statemchine.constant.StateEnum;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

/**
 * @author jialiang.chen
 * @title: DatabaseConfig
 * @projectName gwork
 * @date 2019/5/17 18:59
 */
@Configuration
@EnableStateMachine
public class DenoStateMachineConfig extends EnumStateMachineConfigurerAdapter<StateEnum, EventEnum> {

    @Override
    public void configure(StateMachineStateConfigurer<StateEnum, EventEnum> states)
            throws Exception {
        states
                .withStates()
                .initial(StateEnum.STATE1)
                .states(EnumSet.allOf(StateEnum.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<StateEnum, EventEnum> transitions)
            throws Exception {
        transitions
                .withExternal().source(StateEnum.STATE1).target(StateEnum.STATE2).event(EventEnum.EVENT1)
                .and()
                .withExternal().source(StateEnum.STATE2).target(StateEnum.STATE1).event(EventEnum.EVENT2);
    }
}