package cz.cleevio.watch.controller;

import cz.cleevio.watch.model.Watch;
import cz.cleevio.watch.service.WatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/watch")
public class WatchController {

    private final WatchService watchService;

    @Autowired
    public WatchController(WatchService watchService) {
        this.watchService = watchService;
    }

    @PostMapping("/upload")
    public ResponseEntity<Watch> uploadWatch(@RequestBody @Valid Watch watch) {
        watchService.createOrUpdateWatch(watch);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
