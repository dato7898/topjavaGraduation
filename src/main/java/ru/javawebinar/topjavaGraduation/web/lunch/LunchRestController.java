package ru.javawebinar.topjavaGraduation.web.lunch;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javawebinar.topjavaGraduation.model.Lunch;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = LunchRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class LunchRestController extends AbstractLunchController {

    static final String REST_URL = "/rest/lunch";

    @Override
    @GetMapping
    public List<Lunch> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping("/{id}")
    public Lunch get(@PathVariable int id) {
        return super.get(id);
    }

    @PostMapping(value = "/admin", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Lunch> createWithLocation(@RequestBody Lunch lunch) {
        Lunch created = super.create(lunch);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @Override
    @DeleteMapping("/admin/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

    @Override
    @PutMapping(value = "/admin/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Lunch lunch, @PathVariable int id) {
        super.update(lunch, id);
    }

    @GetMapping("/filter")
    public List<Lunch> getBetween(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                  @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return super.getBetween(startDate, endDate);
    }
}
