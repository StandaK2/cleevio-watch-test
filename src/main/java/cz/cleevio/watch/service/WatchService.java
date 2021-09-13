package cz.cleevio.watch.service;

import cz.cleevio.watch.model.Watch;

public interface WatchService {

    void createOrUpdateWatch(Watch watch);
}
