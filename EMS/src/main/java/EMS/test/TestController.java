package EMS.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestController {

	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping("/bandar")
	public String  gettoken(@RequestParam("token") String token) {
		System.out.println(token);
		String forObject = restTemplate.getForObject("http://SECURITY-APP/securityApp/validate?token=" + token, String.class);
		return forObject;
	}
}
