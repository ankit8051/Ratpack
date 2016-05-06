/**
 * 
 */
package org.gradle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ratpack.func.Action;
import ratpack.handling.Chain;

@Component
public class Router implements Action<Chain> {
	
	@Autowired
	DemoHandler demoHandler;

	public void execute(Chain chain) throws Exception {
		chain.prefix("demo", chain1 -> chain1.all(demoHandler));
	}

}
