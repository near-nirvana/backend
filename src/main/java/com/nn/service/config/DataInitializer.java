package com.nn.service.config;

import com.nn.service.model.Showcase;
import com.nn.service.repo.ShowcaseImplDao;
import com.google.inject.Inject;

/**
 * Created by santiago.ginzburg on 2/12/16.
 */
public class DataInitializer {

    private final ShowcaseImplDao showcaseImplDao;

    @Inject
    public DataInitializer(ShowcaseImplDao showcaseImplDao) {
        this.showcaseImplDao = showcaseImplDao;
    }

    public void run() {
        Showcase showcase = new Showcase();
        showcase.setShowCase("case 1");
        showcaseImplDao.save(showcase);

        Showcase showcase2 = new Showcase();
        showcase2.setShowCase("case 2");
        showcaseImplDao.save(showcase2);
    }
}
