package mutanteapp.controller;

import mutanteapp.service.MutanteService;
import mutanteapp.utility.Stats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

/**
 * Created by Pablo on 10/5/2019.
 */
@RestController
@RequestMapping("/v1.0")
public class MutanteController {

    @Autowired
    private MutanteService mutanteService;

    @GetMapping("/status")
    public String status() {
        return "OK";
    }

    @PostMapping(path = "/mutante", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> checkMutante(@RequestBody String[] secuence) throws ExecutionException, InterruptedException {
        HttpStatus httpStatus;
        boolean status = mutanteService.checkDna(secuence);

        if (!mutanteService.save(secuence, status)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error to save, try it again");
        }

        if (status) {
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }

    }

    @GetMapping(path = "/stats", produces = {"application/json"})
    public ResponseEntity<Stats> stats() {
        int countOfHumanssRecords = mutanteService.getCountOfHumans();
        int countMutantsRecords = mutanteService.getCountOfMutants();
        int countRecords = mutanteService.getCountOfRecods();
        if (countRecords == 0) {
            return ResponseEntity.status(HttpStatus.OK).body(new Stats(countMutantsRecords, countOfHumanssRecords, 0.0f));
        }

        if (countMutantsRecords >= 0 || countOfHumanssRecords > 0) {
            float f = (countMutantsRecords * 100) / countRecords;
            return ResponseEntity.status(HttpStatus.OK).body(new Stats(countMutantsRecords, countOfHumanssRecords, f));
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);

    }

}