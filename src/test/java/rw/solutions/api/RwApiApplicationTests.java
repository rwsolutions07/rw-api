package rw.solutions.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import rw.solutions.api.model.record.CadastroPessoa;
import rw.solutions.api.security.model.record.DadosLogin;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class RwApiApplicationTests {
	
	@Autowired(required=true)
	private TestRestTemplate restTemplate;
	
	private String tokenJWT = "";

	@Test
	void contextLoads() {
		assertThat(restTemplate).isNotNull();
	}
	
	@BeforeAll
	public void login() throws Exception {
		
		final String baseUrl = "http://localhost:8080/autenticacao/login";
        URI uri = new URI(baseUrl);
        DadosLogin employee = new DadosLogin("rwsolutions", "RWSolutions01");
         
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(this.tokenJWT);

        HttpEntity<DadosLogin> request = new HttpEntity<DadosLogin>(employee, headers);
         
        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);
         
        assertEquals(HttpStatus.OK, result.getStatusCode());
        
        String jsonBody = result.getBody();
        JsonNode response = new ObjectMapper().readTree(jsonBody);
        
        assertThat(response.get("token")).isNotNull();
        this.tokenJWT = response.get("token").textValue();
		
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testGet() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:8080/pessoa", Page.class)).isNotNull();
	}
	
	@Test
	public void testPost() throws Exception {
		
		final String baseUrl = "http://localhost:8080/pessoa";
        URI uri = new URI(baseUrl);
        CadastroPessoa employee = new CadastroPessoa("Roni", "desenvolvedor", "desenvolvedor", "https://www.linkedin.com/in/almeidaroni07/");
         
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(this.tokenJWT);

        HttpEntity<CadastroPessoa> request = new HttpEntity<>(employee, headers);
         
        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);
         
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
		
	}

}
