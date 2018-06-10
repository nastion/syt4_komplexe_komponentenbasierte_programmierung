package pfuchs.syt4.westbahn.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import pfuchs.syt4.westbahn.model.Benutzer;
import pfuchs.syt4.westbahn.repositories.BenutzerRepository;
import pfuchs.syt4.westbahn.repositories.ZugRepository;

import javax.validation.Valid;

@Controller
public class RegistrationController {
    @Autowired
    private ZugRepository zugRepo;
    @Autowired
    private BenutzerRepository benutzerRepo;

    @GetMapping("/register")
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("register");
        Benutzer user = new Benutzer();
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView valid_register(@Valid Benutzer benutzer) {
        ModelAndView modelAndView = new ModelAndView();
        if (benutzerRepo.findByEMail(benutzer.getEMail()) == null) {
            modelAndView.setViewName("login");
            benutzerRepo.save(benutzer);
            modelAndView.addObject("success", "Successfully registered!");
        } else {
            modelAndView.setViewName("register");
            benutzer = new Benutzer();
            modelAndView.addObject("user", benutzer);
            modelAndView.addObject("error", "User exists already");
        }
        return modelAndView;
    }
}
