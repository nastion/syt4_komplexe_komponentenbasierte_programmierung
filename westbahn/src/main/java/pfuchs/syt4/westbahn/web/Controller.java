package pfuchs.syt4.westbahn.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pfuchs.syt4.westbahn.model.*;
import pfuchs.syt4.westbahn.repositories.BenutzerRepository;
import pfuchs.syt4.westbahn.repositories.ReservierungRepository;
import pfuchs.syt4.westbahn.repositories.TicketRepository;

@Component
public class Controller {
    @Autowired
    private BenutzerRepository repo;
    @Autowired
    private TicketRepository ticketRepo;
    @Autowired
    private ReservierungRepository resRepo;

    private Benutzer logged_in = null;
    public boolean login(Benutzer benutzer) {
        if (repo.findByEMail(benutzer.getEMail()) != null &&
            repo.findByEMail(benutzer.getEMail()).getPasswort().equals(benutzer.getPasswort())) {
            this.logged_in = repo.findByEMail(benutzer.getEMail());
            return true;
        }
        return false;
    }

    public boolean logout() {
        if (this.logged_in == null)
            return false;
        this.logged_in = null;
        return true;
    }

    public boolean is_logged_in() {
        return this.logged_in != null;
    }

    public Benutzer getLogged_in() {
        return logged_in;
    }

    public void ticketKaufen(Long ticketId) {
        Ticket ticket = ticketRepo.findById(ticketId).get();
        Zahlung zahlung = new Kreditkarte();
        ticket.setZahlung(zahlung);
        this.logged_in.removeTicket(ticket);
        repo.saveAndFlush(this.logged_in);
        String text = "Ticket " + ticketId + " von " + ticket.getStrecke().getStart().getName() + " nach "
                + ticket.getStrecke().getEnde().getName() + " (Kosten " + ticket.preis() + "€) wurde erfolgreich bestellt";
        System.out.println("Sending SMS to " + this.logged_in.getSmsNummer() + " with Text:");
        System.out.println(text);
    }

    public void reservierungDurchführen(Long reservierungId) {
        Zahlung zahlung = new Kreditkarte();
        Reservierung reservierung = resRepo.getOne(reservierungId);
        reservierung.setZahlung(zahlung);
        resRepo.delete(reservierung);
        String text = "Ticket " + reservierungId + " von " + reservierung.getStrecke().getStart().getName() + " nach "
                + reservierung.getStrecke().getEnde().getName() + " um " + reservierung.getZug().getStartZeit() + " wurde erfolgreich bestellt";
        System.out.println("Sending SMS to " + this.logged_in.getSmsNummer() + " with Text:");
        System.out.println(text);
    }
}
