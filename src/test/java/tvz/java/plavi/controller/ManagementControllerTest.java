package tvz.java.plavi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import tvz.java.plavi.model.dto.UserAddRequest;
import javax.servlet.Filter;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by NIS on 12.6.2017..
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ManagementControllerTest {

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
    public void testAddUser() throws Exception {
        UserAddRequest userAddRequest = new UserAddRequest();
        userAddRequest.setUsername("test");
        userAddRequest.setPassword("test");
        userAddRequest.setFirstname("Test");
        userAddRequest.setLastname("Test");
        userAddRequest.setGender("male");
        userAddRequest.setRoleId(1L);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(userAddRequest);

        mockMvc.perform(post("/api/addUser")
                .contentType("application/json;charset=UTF-8")
                .content(requestJson))
                .andExpect(status().isOk());
    }
}
