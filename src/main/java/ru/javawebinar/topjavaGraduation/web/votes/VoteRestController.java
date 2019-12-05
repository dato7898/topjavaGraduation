package ru.javawebinar.topjavaGraduation.web.votes;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javawebinar.topjavaGraduation.model.Vote;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/rest/votes")
public class VoteRestController extends AbstractVoteController {

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
    public List<Vote> getAllByUserId(@RequestParam int userId) {
        return super.getAllByUserId(userId);
    }

    @Override
    @GetMapping("/byLunch")
    public List<Vote> getAllByLunchId(@RequestParam int lunchId) {
        return super.getAllByLunchId(lunchId);
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
