package com.example.appMutante.Repository;

import com.example.appMutante.Entity.DNASequence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DNARepository extends JpaRepository<DNASequence, Long> {
    Optional<DNASequence> findBySequence(String[] sequence);
    long countByIsMutant(boolean isMutant);
}

