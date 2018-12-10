package sd.dutt.shatyaki.FeignClient.apiclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;

import feign.RequestLine;
import sd.dutt.shatyaki.FeignClient.errors.ErrorResponse;

@FeignClient(url = "${url}", name = "${name}")
public interface ApiClient {

	@RequestLine("PUT /path")
	public String invokeTechQCApi(@RequestBody ErrorResponse errorResponse);
}
