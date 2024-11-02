package com.example.appMutante.Service;

import com.example.appMutante.Repository.DNARepository;
import com.example.appMutante.Entity.DNASequence;
import com.example.appMutante.DTO.DNARequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class MutantService {

    private final DNARepository dnaRepository;

    public boolean isMutant(String[] dna) {
        int sequencesFound = 0;

        for (int i = 0; i < dna.length; i++) {
            for (int j = 0; j < dna[i].length(); j++) {
                if (hasSequence(dna, i, j, 1, 0)) { // Horizontal
                    sequencesFound++;
                }
                if (hasSequence(dna, i, j, 0, 1)) { // Vertical
                    sequencesFound++;
                }
                if (hasSequence(dna, i, j, 1, 1)) { // Diagonal
                    sequencesFound++;
                }
                if (hasSequence(dna, i, j, 1, -1)) { // Diagonal inversa
                    sequencesFound++;
                }
                // Si encontramos dos secuencias mutantes, podemos retornar true inmediatamente
                if (sequencesFound > 1) return true;
            }
        }
        return false; // Si no se encontró más de una secuencia mutante
    }

    private boolean hasSequence(String[] dna, int x, int y, int dx, int dy) {
        int count = 0;
        char current = dna[x].charAt(y);

        for (int i = 0; i < 4; i++) {
            int nx = x + i * dx;
            int ny = y + i * dy;
            if (nx < 0 || ny < 0 || nx >= dna.length || ny >= dna[0].length() || dna[nx].charAt(ny) != current) {
                return false; // Secuencia interrumpida
            }
            count++;
        }
        return count == 4; // Debe haber exactamente 4 caracteres iguales consecutivos
    }

    public boolean checkAndSaveDna(DNARequest dnaRequest) {
        String[] sequence = dnaRequest.getDna();
        boolean isMutant = isMutant(sequence);

        // Guardar en la base de datos
        DNASequence dnaRecord = new DNASequence();
        dnaRecord.setSequence(sequence); // Guardamos el array directamente
        dnaRecord.setMutant(isMutant);
        dnaRepository.save(dnaRecord);

        return isMutant;
    }

    public Map<String, Object> getStats() {
        long countMutantDna = dnaRepository.countByIsMutant(true);
        long countHumanDna = dnaRepository.countByIsMutant(false);
        double ratio = countHumanDna == 0 ? 0 : (double) countMutantDna / countHumanDna;

        return Map.of(
                "count_mutant_dna", countMutantDna,
                "count_human_dna", countHumanDna,
                "ratio", ratio
        );
    }
}
