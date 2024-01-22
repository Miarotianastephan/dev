package com.dev.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dev.model.marque.Marque;
import com.dev.service.MarqueService;

import lombok.RequiredArgsConstructor;
import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/marque")
public class MarqueController {
    private final MarqueService marqueService;

    @GetMapping(path = "/allarque", produces = "application/json")
    public List<Marque> getAllMarque() {
        return marqueService.findAllMarque();
    }

    @PostMapping("/insertMarque")
    public Marque insertMarque(@RequestBody Marque marque) {
        return marqueService.save(marque);
    }

    @PostMapping("/updateMarque")
    public Marque updateMarque(@RequestParam int idMarque, @RequestParam String nomMarque) {
        return marqueService.update(idMarque, nomMarque);
    }

    @PostMapping("/deleteMarque")
    public void deleteMarque(@RequestParam int idMarque) {
        marqueService.delete(idMarque);
    }
}
