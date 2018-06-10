package pfuchs.syt4.westbahn.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import pfuchs.syt4.westbahn.model.Bahnhof;
import pfuchs.syt4.westbahn.model.Zug;
import pfuchs.syt4.westbahn.repositories.BahnhofRepository;
import pfuchs.syt4.westbahn.repositories.BenutzerRepository;
import pfuchs.syt4.westbahn.repositories.ZugRepository;

@Controller
public class LoginController {
    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("login");
        return modelAndView;
    }
}
