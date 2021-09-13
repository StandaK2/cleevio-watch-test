package cz.cleevio.watch.service.impl;

import cz.cleevio.watch.exception.ApiError;
import cz.cleevio.watch.exception.ApiValidationError;
import cz.cleevio.watch.model.Watch;
import cz.cleevio.watch.repository.WatchRepository;
import cz.cleevio.watch.service.WatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class WatchServiceImpl implements WatchService {

    private final WatchRepository watchRepository;

    @Autowired
    public WatchServiceImpl(WatchRepository watchRepository) {
        this.watchRepository = watchRepository;
    }

    @Override
    public void createOrUpdateWatch(Watch watch) {
            watchRepository.save(watch);
    }
}
