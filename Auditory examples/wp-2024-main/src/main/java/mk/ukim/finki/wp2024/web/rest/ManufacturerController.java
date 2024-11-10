package mk.ukim.finki.wp2024.web.rest;

import mk.ukim.finki.wp2024.model.Manufacturer;
import mk.ukim.finki.wp2024.service.ManufacturerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/manufacturers")
public class ManufacturerController {
    private final ManufacturerService manufacturerService;

    public ManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @GetMapping
    public List<Manufacturer> findAll(){
        return this.manufacturerService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Manufacturer> findById(@PathVariable Long id){
        return this.manufacturerService.findById(id)
                .map(m->ResponseEntity.ok().body(m))
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Manufacturer> save(@RequestParam String name,@RequestParam String address){
        return this.manufacturerService.save(name, address)
                .map(m->ResponseEntity.ok().body(m))
                .orElseGet(()->ResponseEntity.badRequest().build());
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id){
        if(this.manufacturerService.deleteById(id))
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }
}
