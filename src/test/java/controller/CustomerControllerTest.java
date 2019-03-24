package controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


import javax.sql.DataSource;
import javax.ws.rs.core.Response.Status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
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
import dto.ErrorMessage;
import entity.Customer;

@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MainMvcConfig.class })
@WebAppConfiguration
@TestPropertySource("classpath:test.properties")
public class CustomerControllerTest {
	
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
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testGetCustomerByIdExists() throws Exception{
		MvcResult mvcResult = mockMvc.perform(get("/customer/{id}", "111111")).andDo(print()).andReturn();
		Customer customer = mapper.readValue(mvcResult.getResponse().getContentAsString(), Customer.class);
		assertEquals(Status.OK.getStatusCode(), mvcResult.getResponse().getStatus());
		assertNotNull(customer);
	}
	
	@Test
	public void testGetCustomerByIdDoesNotExist() throws Exception{
		MvcResult mvcResult = mockMvc.perform(get("/customer/{id}", "147488")).andReturn();
		assertEquals(Status.OK.getStatusCode(), mvcResult.getResponse().getStatus());
		assertTrue(mvcResult.getResponse().getContentAsString().length() == 0);
	}
	
	@Test
	public void testDeleteCustomerByIdExists() throws Exception {
		MvcResult mvcResult = mockMvc.perform(delete("/customer/{id}", "111111")).andDo(print()).andReturn();
		assertEquals(Status.OK.getStatusCode(), mvcResult.getResponse().getStatus());
	}

	@Test
	public void testDeleteCustomerByIdDoesNotExist() throws Exception {
		MvcResult mvcResult = mockMvc.perform(delete("/customer/{id}", "147488")).andReturn();
		assertEquals(422, mvcResult.getResponse().getStatus());
		assertEquals("application/json;charset=UTF-8", mvcResult.getResponse().getContentType());
		ErrorMessage errorMessage = mapper.readValue(mvcResult.getResponse().getContentAsString(), ErrorMessage.class);
		assertEquals("Could not delete Customer id=147488, because it does not exist.", errorMessage.getMessage());
	}

	@Test
	public void testUpdateCustomerByIdExists() throws Exception {
		MvcResult mvcResult = mockMvc.perform(put("/customer/{id}", "111111").param("creditLimit", "11")).andDo(print())
				.andReturn();
		assertEquals(Status.OK.getStatusCode(), mvcResult.getResponse().getStatus());
	}

	@Test
	public void testUpdateCustomerByIdDoesNotExist() throws Exception {
		MvcResult mvcResult = mockMvc.perform(put("/customer/{id}", "147488").param("creditLimit", "10")).andReturn();

		assertEquals(422, mvcResult.getResponse().getStatus());
		assertEquals("application/json;charset=UTF-8", mvcResult.getResponse().getContentType());
		ErrorMessage errorMessage = mapper.readValue(mvcResult.getResponse().getContentAsString(), ErrorMessage.class);
		assertEquals("Could not update Customer id=147488, because it does not exist.", errorMessage.getMessage());
	}
}
