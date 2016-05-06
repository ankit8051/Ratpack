/**
 * 
 */
package org.gradle;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import static ratpack.jackson.Jackson.json;

import java.nio.charset.Charset;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.http.TypedData;

/**
 * @author a.patel
 *
 */
@Component
public class DemoHandler implements Handler {

	public void handle(Context ctx) throws Exception {
		ctx.byMethod(m -> m.get(() -> {
			String type = ctx.getRequest().getQueryParams().get("type");
			ctx.render("INSIDE GET METHOD"+type);
		}).post(() -> {
			System.out.println("#################Char Set::"+Charset.defaultCharset().name());
			String type = ctx.getRequest().getQueryParams().get("type");
			if(type.equalsIgnoreCase("inputstream")){
			ctx.getRequest().getBody().map(TypedData::getInputStream).then(t -> {
				if (t.available() > 0) {
					ObjectMapper mapper = new ObjectMapper();
					CreditCard creditCardJsonEntity = mapper.readValue(t,
							new TypeReference<CreditCard>() {
					});
					ctx.render(json(creditCardJsonEntity));
				}
			});	
			}else if(type.equalsIgnoreCase("text")){
				ctx.getRequest().getBody().map(TypedData::getText).then(t -> {
					if (StringUtils.isNotBlank(t)) {
						ObjectMapper mapper = new ObjectMapper();
						CreditCard creditCardJsonEntity = mapper.readValue(t,
								new TypeReference<CreditCard>() {
						});
						ctx.render(json(creditCardJsonEntity));
					}
				});
			}else if(type.equalsIgnoreCase("bytes")){
				ctx.getRequest().getBody().map(TypedData::getBytes).then(t -> {
					if (t.length > 0) {
						ObjectMapper mapper = new ObjectMapper();
						CreditCard creditCardJsonEntity = mapper.readValue(t,
								new TypeReference<CreditCard>() {
						});
						ctx.render(json(creditCardJsonEntity));
					}
				});
			}
		}));
	}

}
