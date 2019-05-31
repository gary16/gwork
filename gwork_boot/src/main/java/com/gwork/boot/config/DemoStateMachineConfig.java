package com.gwork.boot.config;

import com.gwork.boot.statemachine.action.CoinAction;
import com.gwork.boot.statemachine.action.GlobalErrorAction;
import com.gwork.boot.statemachine.action.PushAction;
import com.gwork.boot.statemachine.constant.EventEnum;
import com.gwork.boot.statemachine.constant.StateEnum;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

import java.util.EnumSet;

/**
 * @author jialiang.chen
 * @title: DatabaseConfig
 * @projectName gwork
 * @date 2019/5/17 18:59
 */
@Configuration
@EnableStateMachine
public class DemoStateMachineConfig extends EnumStateMachineConfigurerAdapter<StateEnum, EventEnum> {


    @Override
    public void configure(StateMachineConfigurationConfigurer<StateEnum, EventEnum> config)
            throws Exception {
        config
                .withConfiguration().machineId("myMachineId")
                .autoStartup(true)
                .listener(listener());
    }

    @Override
    public void configure(StateMachineStateConfigurer<StateEnum, EventEnum> states)
            throws Exception {
        states
                .withStates()
                .initial(StateEnum.LOCKED)
                .states(EnumSet.allOf(StateEnum.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<StateEnum, EventEnum> transitions)
            throws Exception {
        transitions
                .withExternal()
                .source(StateEnum.LOCKED)
                .target(StateEnum.UNLOCKED)
                .event(EventEnum.COIN)
                .action(getCoinAction(),getGlobalErrorAction())
                .and()
                .withExternal()
                .source(StateEnum.UNLOCKED)
                .target(StateEnum.LOCKED)
                .event(EventEnum.PUSH)
                .action(getPushAction(),getGlobalErrorAction());
    }

    @Bean
    public StateMachineListener<StateEnum, EventEnum> listener() {
        return new StateMachineListenerAdapter<StateEnum, EventEnum>() {
            @Override
            public void stateChanged(State<StateEnum, EventEnum> from, State<StateEnum, EventEnum> to) {
                System.out.println("State change to " + to.getId());
            }
        };
    }

    @Bean
    public Action<StateEnum, EventEnum> getCoinAction() {
     return new CoinAction();
    }

    @Bean
    public Action<StateEnum, EventEnum> getPushAction() {
        return new PushAction();
    }

    @Bean
    public Action<StateEnum, EventEnum> getGlobalErrorAction() {
        return new GlobalErrorAction();
    }


}