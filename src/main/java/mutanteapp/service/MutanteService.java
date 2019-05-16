package mutanteapp.service;

import mutanteapp.entity.MutanteEntity;
import mutanteapp.repository.MutanteRepository;
import mutanteapp.utility.ADN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


/**
 * Created by Pablo on 10/5/2019.
 */
@Service
public class MutanteService {
    @Autowired
    private MutanteRepository mutanteRepository;
    ExecutorService executor = Executors.newSingleThreadExecutor();

    public MutanteService() {
    }


    public boolean save(String[] secuence, boolean status) {

        try {
            this.mutanteRepository.save(new MutanteEntity(secuence, status));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getCountOfRecods() {
        try {
            return (int) this.mutanteRepository.count();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

    }


    public int getCountOfMutants() {
        try {
            return this.mutanteRepository.countByStatus(true).intValue();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

    }

    public int getCountOfHumans() {
        try {
            return this.mutanteRepository.countByStatus(false).intValue();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

    }

    public boolean checkDna(String[] secuence) throws ExecutionException, InterruptedException {
        Future<Boolean> future = executor.submit(() -> {
            ADN adn = new ADN();
            adn.setAdnSecuence(secuence);
            boolean status = adn.isHuman();
            adn = null;
            return status;
        });

        return future.get();
    }


}
