package aiss.api;

import java.util.HashSet;

import java.util.Set;

import javax.ws.rs.core.Application;

import aiss.api.resource.PersonResource;
import aiss.api.resource.CityResource;


public class AISSBergApplication extends Application {
	
	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> classes = new HashSet<Class<?>>();
	
	public AISSBergApplication() {
		
		singletons.add(PersonResource.getInstance());
		singletons.add(CityResource.getInstance());
		
	}

	public Set<Class<?>> getClasses() {
		return classes;
	}

	public Set<Object> getSingletons() {
		return singletons;
	}
}

