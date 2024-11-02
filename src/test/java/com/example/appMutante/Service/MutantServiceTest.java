package com.example.appMutante.Service;

import com.example.appMutante.Repository.DNARepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MutantServiceTest {

    @InjectMocks
    private MutantService mutantService;

    @Mock
    private DNARepository dnaRepository;

    public MutantServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testIsMutant() {
        String[] mutantDna = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        assertTrue(mutantService.isMutant(mutantDna));
    }
}
