package com.lsla.bank;

import com.lsla.bank.error.dto.ErrorDTO;
import com.lsla.bank.loan.dto.LoanRequestDTO;
import com.lsla.bank.loan.dto.LoanResponseDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoanControllerApiApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void applyLoanCorrectPath() throws Exception {
		HttpEntity<LoanRequestDTO> entity = new HttpEntity<>(new LoanRequestDTO(2000, 10), new HttpHeaders());
		ResponseEntity<LoanResponseDTO> response = restTemplate.exchange(
				createURLWithPort(), HttpMethod.POST, entity, LoanResponseDTO.class);

		Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());
		Assert.assertEquals(createURLWithPort() + "/1", response.getHeaders().getLocation().toString());
	}

	@Test
	public void wrongAmount() {
		HttpEntity<LoanRequestDTO> entity = new HttpEntity<>(new LoanRequestDTO(0, 10), new HttpHeaders());
		ResponseEntity<ErrorDTO> response = restTemplate.exchange(
				createURLWithPort(), HttpMethod.POST, entity, ErrorDTO.class);

		Assert.assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
	}

	private String createURLWithPort() {
		return "http://localhost:" + port + "/api/v1/loans";
	}

	@Test
	public void contextLoads() {
	}



}
