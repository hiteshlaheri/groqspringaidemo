package in.mbs.groq.groqdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Description;

import java.util.function.Function;

@SpringBootApplication
public class GroqDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GroqDemoApplication.class, args);
	}

	@Bean
	@Description("Get the weather in location")
	public GroqDemoApplication.MockWeatherService weatherFunction() {
		return new MockWeatherService();
	}

	public static class MockWeatherService implements Function<MockWeatherService.WeatherRequest, MockWeatherService.WeatherResponse> {

		public record WeatherRequest(String location, String unit) {}
		public record WeatherResponse(double temp, String unit) {}

		@Override
		public WeatherResponse apply(WeatherRequest request) {
			double temperature = request.location().contains("Amsterdam") ? 20 : 25;
			return new WeatherResponse(temperature, request.unit);
		}

}

}
