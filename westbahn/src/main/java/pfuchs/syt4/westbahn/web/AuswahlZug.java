package pfuchs.syt4.westbahn.web;

import pfuchs.syt4.westbahn.model.Zug;

public class AuswahlZug {
    private String startZeit;
    private String endZeit;

    public String getStartZeit() {
        return startZeit;
    }

    public void setStartZeit(String startZeit) {
        this.startZeit = startZeit;
    }

    public String getEndZeit() {
        return endZeit;
    }

    public void setEndZeit(String endZeit) {
        this.endZeit = endZeit;
    }

    public Zug getZug() {
        return zug;
    }

    public void setZug(Zug zug) {
        this.zug = zug;
    }

    private Zug zug;
}
