package pfuchs.syt4.westbahn.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import pfuchs.syt4.westbahn.model.Bahnhof;
import pfuchs.syt4.westbahn.model.Benutzer;
import pfuchs.syt4.westbahn.model.Zug;
import pfuchs.syt4.westbahn.repositories.BahnhofRepository;
import pfuchs.syt4.westbahn.repositories.BenutzerRepository;
import pfuchs.syt4.westbahn.repositories.ZugRepository;

import javax.validation.Valid;

@Controller
public class LoginController {
    @Autowired
    BenutzerRepository repo;

    @Autowired
    pfuchs.syt4.westbahn.web.Controller controller;

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();

        for (Benutzer b : repo.findAll())
            System.out.println(b.getEMail() + " :: " + b.getPasswort());

        Benutzer b = new Benutzer();
        modelAndView.addObject("user", b);
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @PostMapping("/login")
    public String login(Benutzer benutzer) {
        if (controller.login(benutzer)) {
            return "redirect:";
        }
        return "redirect:login?error";
    }

    @GetMapping("/logout")
    public String logout() {
        controller.logout();
        return "redirect:";
    }
}
