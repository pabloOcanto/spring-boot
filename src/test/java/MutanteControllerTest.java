import com.fasterxml.jackson.databind.ObjectMapper;
import mutanteapp.Application;
import mutanteapp.controller.MutanteController;
import mutanteapp.service.MutanteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * Created by Pablo on 11/5/2019.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(controllers =MutanteController.class)
public class MutanteControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    MutanteService mutanteService;

    @Test
    public void postMutanteTest() {
        try {
            mvc.perform(MockMvcRequestBuilders.get("/v1.0/status/"))
                    .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
        } catch (Exception e){
            e.printStackTrace();
        }


    }

}
