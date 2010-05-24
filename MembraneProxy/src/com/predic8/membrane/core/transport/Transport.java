package com.predic8.membrane.core.transport;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import com.predic8.membrane.core.Router;
import com.predic8.membrane.core.interceptor.Interceptor;
import com.predic8.membrane.core.model.IPortChangeListener;

public class Transport {

	protected Set<IPortChangeListener> menuListeners = new HashSet<IPortChangeListener>();
	
	private List<Interceptor> interceptors = new Vector<Interceptor>();

	private Router router;
	
	private void setRouterForInterceptors() {
		for (Interceptor interceptor : interceptors) {
			interceptor.setRouter(router);
		}
	}
	
	public void addMenuViewerListener(IPortChangeListener mViewer) {
		menuListeners.add(mViewer);
	
	}

	public void removeMenuViewerListener(IPortChangeListener mViewer) {
		menuListeners.remove(mViewer);
	
	}

	public List<Interceptor> getInterceptors() {
		return interceptors;
	}

	public void setInterceptors(List<Interceptor> interceptors) {
		this.interceptors = interceptors;
	}

	public void setRouter(Router router) {
		this.router = router;
		setRouterForInterceptors();
	}
	
	public Router getRouter() {
		return router;
	}
	
}
