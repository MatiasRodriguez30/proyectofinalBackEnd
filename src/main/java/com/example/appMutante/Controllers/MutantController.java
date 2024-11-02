package com.example.appMutante.Controllers;
import com.example.appMutante.DTO.DNARequest;
import com.example.appMutante.Service.MutantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mutant")
@RequiredArgsConstructor
public class MutantController {

    private final MutantService mutantService;

    @PostMapping
    public ResponseEntity<Void> isMutant(@RequestBody DNARequest dnaRequest) {
        boolean isMutant = mutantService.checkAndSaveDna(dnaRequest);

        if (isMutant) {
            return ResponseEntity.ok().build(); // 200 OK para mutantes
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // 403 Forbidden para humanos
        }
    }
}

