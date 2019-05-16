import mutanteapp.entity.MutanteEntity;
import mutanteapp.repository.MutanteRepository;
import mutanteapp.service.MutanteService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Pablo on 13/5/2019.
 */
@RunWith(SpringRunner.class)
public class MutanteServiceTest {
    @TestConfiguration
    static class MutanteServiceContextConfiguration {
        @Bean
        public MutanteService mutanteService() {
            return new MutanteService();
        }
    }

    @Autowired
    private MutanteService mutanteService;

    @MockBean
    private MutanteRepository mutanteRepository;

    private MutanteEntity mutanteEntity;

    @Before
    public void setUp() {
        String[] secuence = {"ACGTC"};
        mutanteEntity = new MutanteEntity(secuence, true);
        Mockito.when(mutanteRepository.save(mutanteEntity)).thenReturn(mutanteEntity);

        long countMutants = 1;
        Mockito.when(mutanteRepository.countByStatus(true)).thenReturn(countMutants).thenThrow(RuntimeException.class);
    }

    @Test
    public void saveMutanteTest() {
        String[] secuence = {"ACGTC"};
        Assert.assertTrue(mutanteService.save(secuence, true));
        //Mockito.verify(mutanteRepository,Mockito.atLeast(1)).save(mutanteEntity);
    }

    /*

    @Test
    public void saveMutanteWithErrTest() {
        String[] secuence = {"ACGTC"};
        Assert.assertFalse(mutanteService.save(secuence, true));
        Mockito.verify(mutanteRepository, Mockito.atLeast(1)).save(mutanteEntity);
    }

    */


    @Test
    public void checkCountMuntans() {
        Assert.assertTrue(mutanteService.getCountOfMutants() == 1);
        Mockito.verify(mutanteRepository, Mockito.times(1)).countByStatus(true);
    }

    @Test
    public void checkCountMuntansWithErr() {
        Assert.assertFalse(mutanteService.getCountOfMutants() == -1);
        //Mockito.verify(mutanteRepository,Mockito.times(2)).countByStatus(true);
    }

    @Test
    public void checkDnaTest() {

        String[] adnNoMutante1 = {"GTGCGA", "ACTTGC", "TAGTGT", "TGAGCC", "ACCCTA", "TCACTG"};
        String[] adnMutante1 = {"GTGCGA", "ACTTGC", "TAGTGT", "TGAGTC", "GCCATA", "TCACTG"};
        String[] adnNoMutante2 = {"GTGCGA", "ACGTGC", "TAGTGT", "TGAGTC", "ACCGTT", "TCACGC"};
        String[] adnMutante2 = {"GTGCGA", "ACTAGC", "TAGTGT", "TGAGTC", "ACCGTT", "TCACGT"};

        System.out.println(System.currentTimeMillis());
        try {
            Assert.assertFalse(mutanteService.checkDna(adnNoMutante1));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Assert.assertTrue(mutanteService.checkDna(adnMutante1));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Assert.assertFalse(mutanteService.checkDna(adnNoMutante2));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Assert.assertTrue(mutanteService.checkDna(adnMutante2));
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(System.currentTimeMillis());


    }


}
