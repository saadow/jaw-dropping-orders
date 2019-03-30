package security;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.math.BigDecimal;

import javax.sql.DataSource;
import javax.ws.rs.core.Response.Status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import config.MainMvcConfig;
import dto.CustomerRequest;
import dto.ErrorMessage;
import entity.Customer;
import util.CustomerDtoModelsUtil;

@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MainMvcConfig.class })
@WebAppConfiguration
@TestPropertySource("classpath:test.properties")
public class CustomerControllerSecurityTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private DataSource dataSource;

	private MockMvc mockMvc;
	private ObjectMapper mapper = new ObjectMapper();

	@Before
	public void setup() {
		Resource initSchema = new ClassPathResource("scripts\\schema.sql");
		Resource data = new ClassPathResource("scripts\\data.sql");
		DatabasePopulator databasePopulator = new ResourceDatabasePopulator(initSchema, data);
		DatabasePopulatorUtils.execute(databasePopulator, dataSource);
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.apply(SecurityMockMvcConfigurers.springSecurity()).build();
	}

	@Test
	@WithMockUser(roles = "ADMIN")
	public void testAddCustomerAuth() throws Exception {
		String json = mapper.writeValueAsString(CustomerDtoModelsUtil.customerRequest());
		MvcResult mvcResult = mockMvc.perform(post("/customer").contentType(MediaType.APPLICATION_JSON).content(json))
				.andReturn();
		assertEquals(Status.OK.getStatusCode(), mvcResult.getResponse().getStatus());
	}

	@Test
	@WithMockUser(roles = "ADMIN")
	public void testDeleteCustomerExistAuth() throws Exception {
		MvcResult mvcResult = mockMvc.perform(delete("/customer/{id}", "1111")).andReturn();
		assertEquals(Status.OK.getStatusCode(), mvcResult.getResponse().getStatus());
	}

	@Test
	@WithMockUser(roles = "ADMIN")
	public void testDeleteCustomerNotExistAuth() throws Exception {
		MvcResult mvcResult = mockMvc.perform(delete("/customer/{id}", "7488")).andReturn();
		assertEquals(422, mvcResult.getResponse().getStatus());
		assertEquals("application/json;charset=UTF-8", mvcResult.getResponse().getContentType());
		ErrorMessage errorMessage = mapper.readValue(mvcResult.getResponse().getContentAsString(), ErrorMessage.class);
		assertEquals("Could not delete Customer id=7488, because it does not exist.", errorMessage.getMessage());
	}

	@Test
	@WithMockUser(roles = "ADMIN")
	public void testUpdateCustomerExistAuth() throws Exception {
		MvcResult mvcResult = mockMvc.perform(put("/customer/{id}", "1111").param("creditLimit", "77")).andReturn();
		assertEquals(Status.OK.getStatusCode(), mvcResult.getResponse().getStatus());
	}

	@Test
	@WithMockUser(roles = "ADMIN")
	public void testUpdateCustomerNotExistAuth() throws Exception {
		MvcResult mvcResult = mockMvc.perform(put("/customer/{id}", "7488").param("creditLimit", "77")).andReturn();

		assertEquals(422, mvcResult.getResponse().getStatus());
		assertEquals("application/json;charset=UTF-8", mvcResult.getResponse().getContentType());
		ErrorMessage errorMessage = mapper.readValue(mvcResult.getResponse().getContentAsString(), ErrorMessage.class);
		assertEquals("Could not update Customer id=7488, because it does not exist.", errorMessage.getMessage());
	}

	@Test
	public void testAddCustomerNoAuth() throws Exception {
		String json = mapper.writeValueAsString(CustomerDtoModelsUtil.customerRequest());
		MvcResult mvcResult = mockMvc.perform(post("/customer").contentType(MediaType.APPLICATION_JSON).content(json))
				.andReturn();
		assertEquals(Status.UNAUTHORIZED.getStatusCode(), mvcResult.getResponse().getStatus());
	}
	
	@Test
	public void testUpdateCustomerNoAuth() throws Exception {
		String json = mapper.writeValueAsString(CustomerDtoModelsUtil.customerRequest());
		MvcResult mvcResult = mockMvc.perform(put("/customer").contentType(MediaType.APPLICATION_JSON).content(json))
				.andReturn();
		assertEquals(Status.UNAUTHORIZED.getStatusCode(), mvcResult.getResponse().getStatus());
	}
	
	@Test
	public void testDeleteCustomerNoAuth() throws Exception {
		String json = mapper.writeValueAsString(CustomerDtoModelsUtil.customerRequest());
		MvcResult mvcResult = mockMvc.perform(delete("/customer").contentType(MediaType.APPLICATION_JSON).content(json))
				.andReturn();
		assertEquals(Status.UNAUTHORIZED.getStatusCode(), mvcResult.getResponse().getStatus());
	}
}
