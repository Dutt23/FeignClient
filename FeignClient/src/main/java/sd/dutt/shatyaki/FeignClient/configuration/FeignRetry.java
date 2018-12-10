package sd.dutt.shatyaki.FeignClient.configuration;

import feign.RetryableException;
import feign.Retryer;

public class FeignRetry implements Retryer {
	private final int maxAttempts;
	private final long backoff;
	int attempt;

	public FeignRetry() {
		// Back off time and number of retries max
		this(3000, 4);
	}

	public FeignRetry(long backoff, int maxAttempts) {
		this.backoff = backoff;
		this.maxAttempts = maxAttempts;
		this.attempt = 1;
	}

	@Override
	public void continueOrPropagate(RetryableException e) {

		if (attempt++ >= maxAttempts) {
			throw e;
		}

		try {
			Thread.sleep(backoff);
		} catch (InterruptedException ignored) {
			Thread.currentThread().interrupt();
		}

	}

	@Override
	public Retryer clone() {
		return new FeignRetry(backoff, maxAttempts);

	}
}
