package pfuchs.syt4.westbahn.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import pfuchs.syt4.westbahn.model.Benutzer;
import pfuchs.syt4.westbahn.model.Reservierung;
import pfuchs.syt4.westbahn.model.Ticket;
import pfuchs.syt4.westbahn.repositories.BenutzerRepository;
import pfuchs.syt4.westbahn.repositories.ReservierungRepository;
import pfuchs.syt4.westbahn.repositories.TicketRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class CartController {
    @Autowired
    BenutzerRepository repo;
    @Autowired
    TicketRepository ticketRepo;
    @Autowired
    ReservierungRepository resRepo;

    @Autowired
    pfuchs.syt4.westbahn.web.Controller controller;

    @GetMapping("/cart")
    public ModelAndView cart() {
        ModelAndView modelAndView = new ModelAndView();
        if (!this.controller.is_logged_in()) {
            Benutzer b = new Benutzer();
            modelAndView.addObject("user", b);
            modelAndView.setViewName("login");
            return modelAndView;
        }
        Set<Ticket> tickets = this.controller.getLogged_in().getTickets();
        System.out.println(tickets.size());
        List<Reservierung> reservierungen =  resRepo.findAllByBenutzer(this.controller.getLogged_in());

        modelAndView.addObject("tickets", tickets);
        modelAndView.addObject("reservierungen", reservierungen);
        modelAndView.setViewName("cart");
        return modelAndView;
    }

    @PostMapping(value = "/cart/submit")
    public ModelAndView checkout(Long id) {
        ModelAndView modelAndView = new ModelAndView();
        this.controller.ticketKaufen(id);
        modelAndView.addObject("tickets", this.controller.getLogged_in().getTickets());
        modelAndView.addObject("reservierungen", resRepo.findAllByBenutzer(this.controller.getLogged_in()));
        modelAndView.setViewName("cart");
        return modelAndView;
    }

    @PostMapping(value = "/cart/submit", params = "reservierung")
    public ModelAndView checkout_reservierung(Long id) {
        ModelAndView modelAndView = new ModelAndView();
        this.controller.reservierungDurchführen(id);
        modelAndView.addObject("tickets", this.controller.getLogged_in().getTickets());
        modelAndView.addObject("reservierungen", resRepo.findAllByBenutzer(this.controller.getLogged_in()));
        modelAndView.setViewName("cart");
        return modelAndView;
    }

}
