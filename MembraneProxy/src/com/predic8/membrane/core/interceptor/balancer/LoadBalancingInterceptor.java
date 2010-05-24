package com.predic8.membrane.core.interceptor.balancer;

import java.util.List;

import com.predic8.membrane.core.exchange.Exchange;
import com.predic8.membrane.core.interceptor.AbstractInterceptor;
import com.predic8.membrane.core.interceptor.Outcome;

public class LoadBalancingInterceptor extends AbstractInterceptor {

	private List<String> endpoints;

	private DispatchingStrategy dispatchingStrategy;
	
	public List<String> getEndpoints() {
		return endpoints;
	}

	public void setEndpoints(List<String> endpoints) {
		this.endpoints = endpoints;
	}
	
	@Override
	public Outcome handleRequest(Exchange exc) throws Exception {
		String destination = dispatchingStrategy.dispatch(this);
		exc.setOriginalRequestUri(destination);
		exc.getDestinations().clear();
		exc.getDestinations().add(destination);
		
		for (String dest : endpoints) {
			if (!dest.equals(destination))
				exc.getDestinations().add(dest);
		}
		
		return Outcome.CONTINUE;
	}

	@Override
	public Outcome handleResponse(Exchange exc) throws Exception {
		dispatchingStrategy.done(exc);
		return Outcome.CONTINUE;
	}
	
	public DispatchingStrategy getDispatchingStrategy() {
		return dispatchingStrategy;
	}

	public void setDispatchingStrategy(DispatchingStrategy strategy) {
		this.dispatchingStrategy = strategy;
	}
	
}
