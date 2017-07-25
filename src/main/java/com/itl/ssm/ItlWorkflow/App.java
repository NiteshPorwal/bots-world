package com.itl.ssm.ItlWorkflow;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.ObjectStateMachineFactory;
import org.springframework.statemachine.config.model.ConfigurationData;
import org.springframework.statemachine.config.model.DefaultStateMachineModel;
import org.springframework.statemachine.config.model.StateData;
import org.springframework.statemachine.config.model.StateMachineModel;
import org.springframework.statemachine.config.model.StatesData;
import org.springframework.statemachine.config.model.TransitionData;
import org.springframework.statemachine.config.model.TransitionsData;
import org.springframework.statemachine.transition.Transition;

/**
 * Hello world!
 * 
 */
public class App {

	public enum States {
		SI, S1, S2, S3, S4, SF, SH, S10, S11, S101, S111, S112, S12, S121, S122, S13, S20, S21, S201, S211, S212, S1011, S1012, S2011, S2012, S30, S31, S32, S33
	}

	public enum Events {
		E1, E2, E3, E4, EF, EH
	}

	public static enum Setter {
		DYNAMIC_ENUM_EXAMPLE {
			List<String> list = null;

			@Override
			public List<String> addStates(List<String> yourValue) {
				list = yourValue;
				return yourValue;
			}

			@Override
			public List<String> getStates() {
				// TODO Auto-generated method stub
				return list;
			}
		};
		public abstract List<String> addStates(List<String> value);

		public abstract List<String> getStates();
	}

	public static void main(String[] args) {

		/*
		 * CompositeStateMachineListener<States, Events> listener = new
		 * CompositeStateMachineListener<States, Events>();
		 * StateMachineListenerAdapter<States, Events> adapter1 = new
		 * StateMachineListenerAdapter<States, Events>();
		 * AppStateMachineListener appStMachine = new AppStateMachineListener();
		 * listener.register(appStMachine);
		 */
		// OrderedComposite<StateMachineListener<String, String>> listeners =
		// listener.getListeners();

		ConfigurationData<String, String> configurationData = new org.springframework.statemachine.config.model.ConfigurationData<>();

		Collection<StateData<String, String>> stateData = new ArrayList<>();
		stateData.add(new StateData<String, String>("S1", true));
		stateData.add(new StateData<String, String>("S2"));
		stateData.add(new StateData<String, String>("S3"));
		StatesData<String, String> statesData = new StatesData<>(stateData);

		Collection<TransitionData<String, String>> transitionData = new ArrayList<>();
		TransitionData<String, String> transitionData2 = new TransitionData<String, String>("S1", "S2", "E1");

		transitionData.add(new TransitionData<String, String>("S1", "S2", "E1"));
		transitionData.add(new TransitionData<String, String>("S2", "S3", "E2"));
		transitionData.add(new TransitionData<String, String>("S2", "S3", "E3"));
		TransitionsData<String, String> transitionsData = new TransitionsData<>(transitionData);

		StateMachineModel<String, String> stateMachineModel = new DefaultStateMachineModel<String, String>(
				configurationData, statesData, transitionsData);
		ObjectStateMachineFactory<String, String> factory = new ObjectStateMachineFactory<>(stateMachineModel);
		BeanFactory beanFactory = new DefaultListableBeanFactory();
		factory.setBeanFactory(beanFactory);

		StateMachine<String, String> stateMachine = factory.getStateMachine();

		stateMachine.start();
		System.out.println(stateMachine.getState().getIds());
		// assertThat(stateMachine.getState().getIds(), contains("S1"));
		stateMachine.sendEvent("E1");
		// assertThat(stateMachine.getState().getIds(), contains("S2"));
		System.out.println(stateMachine.getState().getIds());
		stateMachine.sendEvent("E2");
		System.out.println(stateMachine.getState().getIds());
		stateMachine.sendEvent("E3");
		System.out.println(stateMachine.getState().getIds());
		stateMachine.sendEvent("E3");
		System.out.println(stateMachine.getState().getIds());
		stateMachine.getId();
		System.out.println(stateMachine.getId());
		Collection<Transition<String, String>> transitions = stateMachine.getTransitions();
		for (Iterator iterator = transitions.iterator(); iterator.hasNext();) {
			Transition<String, String> transition = (Transition<String, String>) iterator.next();
			System.out.println(transition.getKind());
		}
	}

}
