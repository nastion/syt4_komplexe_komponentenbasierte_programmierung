package pfuchs.syt4.westbahn.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pfuchs.syt4.westbahn.model.*;
import pfuchs.syt4.westbahn.repositories.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Controller
public class TicketshopController {
    @Autowired
    BahnhofRepository repo;
    @Autowired
    ZugRepository zugRepo;
    @Autowired
    BenutzerRepository benutzerRepo;
    @Autowired
    ReservierungRepository resRepo;
    @Autowired
    TicketRepository ticketRepo;
    @Autowired
    StreckeRepository streckeRepo;
    @Autowired
    pfuchs.syt4.westbahn.web.Controller controller;

    @PostMapping("/shop")
    public ModelAndView shop(@Valid String fromString,
                             @Valid String toString,
                             @Valid Long zugId) {
        ModelAndView modelAndView = new ModelAndView();

        Bahnhof from = repo.findByName(fromString);
        Bahnhof to = repo.findByName(toString);

        Zug zug = (zugRepo.findById(zugId).isPresent())?zugRepo.findById(zugId).get():null;
        if (zug != null)
            modelAndView.addObject("zug", zug);

        if (controller.is_logged_in())
            modelAndView.addObject("logged_in", true);
        Benutzer b = new Benutzer();
        modelAndView.addObject("user", b);
        Strecke strecke = new Strecke(from, to);
        modelAndView.addObject("strecke", strecke);
        modelAndView.addObject("bahnhoefe", repo.findAll());
        modelAndView.setViewName("shop");
        return modelAndView;
    }

    @GetMapping(value = "/shop")
    public ModelAndView ticket(){
        ModelAndView modelAndView = new ModelAndView();

        if (controller.is_logged_in())
            modelAndView.addObject("logged_in", true);
        modelAndView.addObject("bahnhoefe", repo.findAll());
        modelAndView.setViewName("shop");
        return modelAndView;
    }

    @GetMapping(value = "/shop", params = "reservierung")
    public ModelAndView reservierung() {
        ModelAndView modelAndView = new ModelAndView();
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        List<Zug> zuegeOnDay = zugRepo.findByMatchMonthAndMatchDay(intTo2Digits(cal.get(Calendar.YEAR))+"-"
                +intTo2Digits(cal.get(Calendar.MONTH)+1)+"-"+intTo2Digits(cal.get(Calendar.DATE)-1));
        Set<Zug> zuegeInDirection = getZuegeInRightDirection("Wien Westbhf", "Wien Hütteldorf");

        Bahnhof from = repo.findByName("Wien Westbhf");
        Bahnhof to = repo.findByName("Wien Hütteldorf");

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        List<AuswahlZug> zeiten = addZuege(zuegeOnDay, zuegeInDirection, sdf, from, to);

        if (controller.is_logged_in())
            modelAndView.addObject("logged_in", true);
        modelAndView.addObject("zeiten", zeiten);
        modelAndView.addObject("bahnhoefe", repo.findAll());
        modelAndView.setViewName("shop");
        return modelAndView;
    }

    @GetMapping(value = "/shop", params = "zeitkarte")
    public ModelAndView zeitkarte() {
        ModelAndView modelAndView = new ModelAndView();
        if (controller.is_logged_in())
            modelAndView.addObject("logged_in", true);
        modelAndView.addObject("bahnhoefe", repo.findAll());
        modelAndView.setViewName("shop");
        return modelAndView;
    }

    @GetMapping("/update/shop")
    public String update(@RequestParam(name="from") String fromString,
                         @RequestParam(name="to") String toString,
                         @RequestParam(name="date") String date,
                         Model model) {

        List<Zug> zuegeOnDay = zugRepo.findByMatchMonthAndMatchDay(date);
        Set<Zug> zuegeInDirection = getZuegeInRightDirection(fromString, toString);

        Bahnhof from = repo.findByName(fromString);
        Bahnhof to = repo.findByName(toString);

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        List<AuswahlZug> zeiten = addZuege(zuegeOnDay, zuegeInDirection, sdf, from, to);

        model.addAttribute("zeiten", zeiten);
        return "updateShop";
    }

    @GetMapping("/shop/confirm")
    public String confirm(@RequestParam(value = "from") String fromString,
                          @RequestParam(value = "to") String toString,
                          @RequestParam(required = false) String amount,
                          @RequestParam(required = false) String ticketOption,
                          @RequestParam(required = false) String resart,
                          @RequestParam(required = false) String date,
                          @RequestParam(required = false) String zug,
                          @RequestParam(required = false) String zeitopt,
                          Model model) {
        if (!this.controller.is_logged_in()) {
            Benutzer b = new Benutzer();
            model.addAttribute("user", b);
            return "login";
        }

    if (amount != null) {
            Einzelticket einzelticket = new Einzelticket();
            Bahnhof from = repo.findByName(fromString);
            Bahnhof to = repo.findByName(toString);
            Strecke strecke;
            if (streckeRepo.findByStartAndEnde(from, to) == null) {
                strecke = new Strecke(from, to);
                streckeRepo.save(strecke);
            } else strecke = streckeRepo.findByStartAndEnde(from, to);
            if (ticketOption != null)
                if (ticketOption.equalsIgnoreCase("FAHRRAD"))
                    einzelticket.setTicketOption(TicketOption.FAHRRAD);
                else einzelticket.setTicketOption(TicketOption.GROSSGEPAECK);
            einzelticket.setStrecke(strecke);
            einzelticket.setAnzahlPers(Integer.parseInt(amount));
            ticketRepo.saveAndFlush(einzelticket);
            this.controller.getLogged_in().addTicket(einzelticket);
            benutzerRepo.saveAndFlush(this.controller.getLogged_in());
        } else if (resart != null) {
            Reservierung reservierung = new Reservierung();
            reservierung.setBenutzer(this.controller.getLogged_in());
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date parsed = format.parse(date);
                reservierung.setDatum(parsed);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (resart.equalsIgnoreCase("SITZPLATZ"))
                reservierung.setReservierungsart(Reservierungsart.SITZPLATZ);
            else if (resart.equalsIgnoreCase("FAHRRAD"))
                reservierung.setReservierungsart(Reservierungsart.FAHRRAD);
            else reservierung.setReservierungsart(Reservierungsart.ROLLSTUHLSTELLPLATZ);
            Bahnhof from = repo.findByName(fromString);
            Bahnhof to = repo.findByName(toString);
            Strecke strecke;
            if (streckeRepo.findByStartAndEnde(from, to) == null) {
                strecke = new Strecke(from, to);
                streckeRepo.save(strecke);
            } else strecke = streckeRepo.findByStartAndEnde(from, to);
            reservierung.setStrecke(strecke);
            reservierung.setZug(zugRepo.findById(Long.parseLong(zug)).get());
            resRepo.save(reservierung);
        } else if (zeitopt != null) {
            Zeitkarte zeitkarte = new Zeitkarte();
            if (zeitopt.equalsIgnoreCase("JAHRESKARTE"))
                zeitkarte.setTyp(ZeitkartenTyp.JAHRESKARTE);
            else if (zeitopt.equalsIgnoreCase("MONATSKARTE"))
                zeitkarte.setTyp(ZeitkartenTyp.MONATSKARTE);
            else zeitkarte.setTyp(ZeitkartenTyp.WOCHENKARTE);
            Bahnhof from = repo.findByName(fromString);
            Bahnhof to = repo.findByName(toString);
            Strecke strecke;
            if (streckeRepo.findByStartAndEnde(from, to) == null) {
                strecke = new Strecke(from, to);
                streckeRepo.save(strecke);
            } else strecke = streckeRepo.findByStartAndEnde(from, to);
            zeitkarte.setStrecke(strecke);
            this.controller.getLogged_in().addTicket(zeitkarte);
            ticketRepo.save(zeitkarte);
            benutzerRepo.save(this.controller.getLogged_in());
        }
        return "redirect:";
    }

    private List<AuswahlZug> addZuege(List<Zug> zuegeOnDay, Set<Zug> zuegeInDirection, SimpleDateFormat sdf, Bahnhof from, Bahnhof to) {
        List<AuswahlZug> zeiten = new ArrayList<>();
        for (Zug z : zuegeOnDay) {
            if (zuegeInDirection.contains(z)) {
                AuswahlZug zug = new AuswahlZug();
                zug.setZug(z);
                zug.setStartZeit(sdf.format(z.getStartZeit().getTime()+from.getAbsZeitEntfernung()*60000));
                zug.setEndZeit(sdf.format(z.getStartZeit().getTime()+to.getAbsZeitEntfernung()*60000));
                zeiten.add(zug);
            }
        }
        return zeiten;
    }

    private String intTo2Digits(int number) {
        return number/10+""+number%10;
    }

    private Set<Zug> getZuegeInRightDirection(String from, String to) {
        int indexFrom = -1;
        int indexTo = -1;
        for (int i = 0; i < repo.findAll().size(); ++i)
            if (repo.findAll().get(i).getName().equalsIgnoreCase(from))
                indexFrom = i;
            else if (repo.findAll().get(i).getName().equalsIgnoreCase(to))
                indexTo = i;
        Set<Zug> zuege;
        if (indexFrom < indexTo)
            zuege = zugRepo.findAllFromWien();
        else zuege = zugRepo.findAllFromSalzburg();
        return zuege;
    }
}
