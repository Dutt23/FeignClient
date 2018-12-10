package sd.dutt.shatyaki.FeignClient.configuration;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.retry.annotation.EnableRetry;

import feign.Contract;
import feign.Retryer;
import feign.codec.Decoder;
import feign.codec.ErrorDecoder;
import sd.dutt.shatyaki.FeignClient.errors.ErrorResponse;

@Configuration
@EnableRetry
public class FeignClientConfig {

	@Autowired
	private ObjectFactory<HttpMessageConverters> messageConverters;

	@Autowired
	Environment env;

	// private ReasonOfFailure reasonOfFailure;
	//
	// TechOddFailureResponse techOddFailureResponse;

	@Bean
	public Retryer retryer() {
		return new FeignRetry();
	}

	@Bean
	public Decoder myDecoder() {
		return new SpringDecoder(messageConverters);
	}

	@Bean
	public Contract useFeignAnnotations() {
		return new Contract.Default();
	}

	@Bean
	public ErrorDecoder uaaErrorDecoder(Decoder decoder) {
		return (h, response) -> {
			try {

				ErrorResponse errorResponse = (ErrorResponse) decoder.decode(response, ErrorResponse.class);

				if (errorResponse.getCode().equals("Fatal code")) {
					return new NullPointerException();
				}

				else {
					// reasonOfFailure = new ReasonOfFailure();
					// techOddFailureResponse = new TechOddFailureResponse();
					// reasonOfFailure.setReasonCode(errorResponse.getCode());
					// reasonOfFailure.setReasonText(errorResponse.getMessage());
					// techOddFailureResponse.setReasonOfFailure(reasonOfFailure);
					// return new ApiException(techOddFailureResponse);
					return new NullPointerException();
				}

			} catch (Exception e) {
				return new NullPointerException();
			}
		};
	}

}
