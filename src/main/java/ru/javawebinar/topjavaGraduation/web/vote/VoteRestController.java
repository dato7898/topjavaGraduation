package ru.javawebinar.topjavaGraduation.web.vote;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javawebinar.topjavaGraduation.model.Vote;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = VoteRestController.REST_URL)
public class VoteRestController extends AbstractVoteController {
    static final String REST_URL = "/rest/votes";

    @Override
    @GetMapping("/{id}")
    public Vote get(@PathVariable int id) {
        return super.get(id);
    }

    @Override
    @GetMapping
    public List<Vote> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping("/byUser")
    public List<Vote> getAllByUser(@RequestParam int userId) {
        return super.getAllByUser(userId);
    }

    @Override
    @GetMapping("/byLunch")
    public List<Vote> getAllByLunch(@RequestParam int lunchId) {
        return super.getAllByLunch(lunchId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> createWithLocation(@RequestBody Vote vote, @RequestParam int lunchId) {
        Vote created = super.create(vote, lunchId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/rest/votes/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
