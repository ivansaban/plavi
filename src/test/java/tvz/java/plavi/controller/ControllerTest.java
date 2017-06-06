package tvz.java.plavi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import tvz.java.plavi.model.dto.UserLoginRequest;
import javax.servlet.Filter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.junit.Assert.assertEquals;


/**
 * Created by NIS on 2.6.2017..
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private Filter springSecurityFilterChain;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .addFilters(springSecurityFilterChain)
                .build();
    }

    @Test
    public void testLoginSucces() throws Exception {
        UserLoginRequest userLogin = new UserLoginRequest();
        userLogin.setUsername("user");
        userLogin.setPassword("pass");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(userLogin);

        MvcResult mvcResult = mockMvc.perform(post("/api/login")
                .contentType("application/json;charset=UTF-8")
                .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn();

        String expected = "{\"id\":1,\"firstname\":\"Pero\",\"lastname\":\"Perić\",\"username\":\"user\",\"gender\":\"male\",\"role\":{\"id\":1,\"name\":\"USER\"}}";
        JSONAssert.assertEquals(expected, mvcResult.getResponse().getContentAsString(), true);
    }

    @Test
    public void testLoginFailure() throws Exception {
        UserLoginRequest userLogin = new UserLoginRequest();
        userLogin.setUsername("test");
        userLogin.setPassword("test");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(userLogin);

        MvcResult mvcResult = mockMvc.perform(post("/api/login")
                .contentType("application/json;charset=UTF-8")
                .content(requestJson))
                .andExpect(status().isForbidden())
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andReturn();

        assertEquals("Bad Credentials", mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void testGetLoggedUserSuccess() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/getLoggedUser").with(user("user").password("pass").roles("USER"))
                .contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn();

        String expected = "{\"id\":1,\"firstname\":\"Pero\",\"lastname\":\"Perić\",\"username\":\"user\",\"gender\":\"male\",\"role\":{\"id\":1,\"name\":\"USER\"}}";
        JSONAssert.assertEquals(expected, mvcResult.getResponse().getContentAsString(), true);
    }

    @Test
    public void testGetLoggedUserFail() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/getLoggedUser")
                .contentType("application/json;charset=UTF-8"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andReturn();

        assertEquals("Not Found", mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void testLogout() throws Exception {
        mockMvc.perform(get("/api/logout").with(user("user").password("pass").roles("USER")))
                .andExpect(status().isOk());
    }
}
