package pfuchs.syt4.westbahn.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import pfuchs.syt4.westbahn.model.Benutzer;
import pfuchs.syt4.westbahn.repositories.BenutzerRepository;
import pfuchs.syt4.westbahn.repositories.ReservierungRepository;
import pfuchs.syt4.westbahn.repositories.ZugRepository;

import javax.validation.Valid;

@Controller
public class RegistrationController {
    @Autowired
    private BenutzerRepository benutzerRepo;
    @Autowired
    private ReservierungRepository resRepo;
    @Autowired
    private pfuchs.syt4.westbahn.web.Controller controller;

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
            benutzer = new Benutzer();
            modelAndView.addObject("user", benutzer);
            modelAndView.addObject("success", "Successfully registered!");
        } else {
            modelAndView.setViewName("register");
            benutzer = new Benutzer();
            modelAndView.addObject("user", benutzer);
            modelAndView.addObject("error", "User exists already");
        }
        return modelAndView;
    }

    @GetMapping("/unregister")
    public String unregister() {
        if (!controller.is_logged_in())
            return "redirect/login";
        resRepo.deleteAll(resRepo.findAllByBenutzer(controller.getLogged_in()));
        benutzerRepo.delete(controller.getLogged_in());
        controller.logout();
        return "redirect:";
    }
}
