package co.edu.uniquindio.habitx.controller;

import co.edu.uniquindio.habitx.model.ArticuloNutricional;
import co.edu.uniquindio.habitx.repositories.ArticuloNutricionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/articulos")
public class ArticuloNutricionalController {

    @Autowired
    private ArticuloNutricionalRepository articuloNutricionalRepository;

    @GetMapping
    public ResponseEntity<List<ArticuloNutricional>> listarArticulosNutricionales() {
        return new ResponseEntity<>(articuloNutricionalRepository.findAll(), HttpStatus.OK);
    }
}