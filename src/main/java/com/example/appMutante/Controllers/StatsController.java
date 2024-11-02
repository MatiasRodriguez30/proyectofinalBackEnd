package com.example.appMutante.Controllers;

import com.example.appMutante.Service.MutantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/stats")
@RequiredArgsConstructor
public class StatsController {

    private final MutantService mutantService;

    @GetMapping
    public Map<String, Object> getStats() {
        return mutantService.getStats();
    }
}

