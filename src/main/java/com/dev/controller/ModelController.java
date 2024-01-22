package com.dev.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.dev.model.models.Model;
import com.dev.service.ModelService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/model")
public class ModelController {
    private final ModelService modelService;

    @GetMapping(path = "/allModel", produces = "application/json")
    public List<Model> getAllModel() {
        return modelService.findAllModel();
    }

    @PostMapping("/insertModel")
    public Model insertModel(@RequestBody Model model) {
        System.out.println(model.getListeModelCategorie());
        return modelService.save(model);
        // return model;
    }

    @PostMapping("/updateModel")
    public Model updateModel(@RequestParam int idModel, @RequestParam String nomModel) {
        return modelService.update(idModel, nomModel);
    }

    @PostMapping("/deleteModel")
    public void deleteModel(@RequestParam int idModel) {
        modelService.delete(idModel);
    }
}
