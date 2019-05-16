import com.fasterxml.jackson.databind.ObjectMapper;
import mutanteapp.Application;
import mutanteapp.controller.MutanteController;
import mutanteapp.service.MutanteService;
import org.json.JSONArray;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * Created by Pablo on 11/5/2019.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(MutanteController.class)
@ContextConfiguration(classes = Application.class)
public class MutanteControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    MutanteService mutanteService;

    @Before
    public void setUp() {
        String[] secuence = {"AGGTCT"}; //mockeamos un mutante falso como respuesta

        try {
            Mockito.when(mutanteService.checkDna(secuence)).thenReturn(false);
            Mockito.when(mutanteService.save(secuence, false)).thenReturn(true);

            String[] adnMutante = {"GTGCGA", "ACTAGC", "TAGTGT", "TGAGTC", "ACCGTT", "TCACGT"};//mockeamos un mutante verdadero como respuesta
            Mockito.when(mutanteService.checkDna(adnMutante)).thenReturn(true);
            Mockito.when(mutanteService.save(adnMutante, true)).thenReturn(true);

            Mockito.when(mutanteService.getCountOfHumans()).thenReturn(15);//Retorna el total de humanos
            Mockito.when(mutanteService.getCountOfMutants()).thenReturn(5);//Retorna el total de mutantes
            Mockito.when(mutanteService.getCountOfRecods()).thenReturn(20);//Retorna el total de registros humanos y mutantes
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Test
    public void checkIsWorking() {
        try {
            this.mvc.perform(MockMvcRequestBuilders.get("/v1.0/status/").accept(MediaType.ALL
            )).andExpect(MockMvcResultMatchers.status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void postMutanteWithOutDataTest() {
        try {
            this.mvc.perform(MockMvcRequestBuilders.post("/v1.0/mutante/")
                    .contentType("application/json"))
                    .andExpect(MockMvcResultMatchers.status().isBadRequest());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void postMutante403Test() {
        try {
            String[] secuence = {"AGGTCT"};
            JSONArray jsonArray = new JSONArray(secuence);
            this.mvc.perform(MockMvcRequestBuilders.post("/v1.0/mutante/")
                    .contentType("application/json")
                    .content(String.valueOf(jsonArray)))
                    .andExpect(MockMvcResultMatchers.status().isForbidden());
            Mockito.verify(mutanteService, Mockito.atLeast(1)).checkDna(secuence);
            Mockito.verify(mutanteService, Mockito.atLeast(1)).save(secuence, false);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Test
    public void postMutante200Test() {
        try {
            String[] secuence = {"GTGCGA", "ACTAGC", "TAGTGT", "TGAGTC", "ACCGTT", "TCACGT"};
            JSONArray jsonArray = new JSONArray(secuence);
            this.mvc.perform(MockMvcRequestBuilders.post("/v1.0/mutante/")
                    .contentType("application/json")
                    .content(String.valueOf(jsonArray)))
                    .andExpect(MockMvcResultMatchers.status().isOk());
            Mockito.verify(mutanteService, Mockito.atLeast(1)).checkDna(secuence);
            Mockito.verify(mutanteService, Mockito.atLeast(1)).save(secuence, true);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    @Test
    public void getStats() {
        try {
            this.mvc.perform(MockMvcRequestBuilders.get("/v1.0/stats/"))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.count_mutant_dna").value(5))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.count_human_dna").value(15))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.ratio").value(0.25));
            Mockito.verify(mutanteService, Mockito.atLeast(1)).getCountOfHumans();
            Mockito.verify(mutanteService, Mockito.atLeast(1)).getCountOfMutants();
            Mockito.verify(mutanteService, Mockito.atLeast(1)).getCountOfRecods();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
