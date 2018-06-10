package pfuchs.syt4.westbahn.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pfuchs.syt4.westbahn.model.Benutzer;
import pfuchs.syt4.westbahn.repositories.BenutzerRepository;

@Component
public class Controller {
    @Autowired
    private BenutzerRepository repo;

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
}
