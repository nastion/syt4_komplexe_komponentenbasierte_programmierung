package pfuchs.syt4.westbahn.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import pfuchs.syt4.westbahn.model.Bahnhof;
import pfuchs.syt4.westbahn.model.Zug;
import pfuchs.syt4.westbahn.repositories.BahnhofRepository;
import pfuchs.syt4.westbahn.repositories.ZugRepository;

@Controller
public class SearchController {
    @Autowired
    private ZugRepository zugRepo;
    @Autowired
    private BahnhofRepository bahnhofRepo;

    @GetMapping(value = {"/", "/search"})
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();

        Bahnhof b = new Bahnhof("test");
        Bahnhof b2 = new Bahnhof("test-bahnhof");
        Zug zug = new Zug(b, b2);

        bahnhofRepo.save(b);
        bahnhofRepo.save(b2);
        zugRepo.save(zug);

        modelAndView.setViewName("search");
        modelAndView.addObject("zuege", zugRepo.findAll());
        return modelAndView;
    }
}
