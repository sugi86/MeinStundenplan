package com.example.fh.meinstundenplan;


class Fach {
    private String semester;
    private String name;
    private String tag;
    private String beginn;
    private String ende;
    private String raum;
    private String dozent;
    private String kuerzel;
    private boolean checked;
    private String id;


    //Konstruktor
    public Fach(String sem, String n, String t, String b, String e,
                String r, String doz, String kue, boolean ak, String id) {
        setSemester(sem);
        setName(n);
        setTag(t);
        setBeginn(b);
        setEnde(e);
        setRaum(r);
        setDozent(doz);
        setKuerzel(kue);
        setChecked(ak);
        setId(id);
    }

    //Setter und Getter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getBeginn() {
        return beginn;
    }

    public void setBeginn(String beginn) {
        this.beginn = beginn;
    }

    public String getEnde() {
        return ende;
    }

    public void setEnde(String ende) {
        this.ende = ende;
    }

    public String getRaum() {
        return raum;
    }

    public void setRaum(String raum) {
        this.raum = raum;
    }

    public String getDozent() {
        return dozent;
    }

    public void setDozent(String dozent) {
        this.dozent = dozent;
    }

    public String getKuerzel() {
        return kuerzel;
    }

    public void setKuerzel(String kuerzel) {
        this.kuerzel = kuerzel;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean aktiv) {
        this.checked = aktiv;
    }

    //Ausgabefunktion für Backup Funktion
    public String CSVtoString() {
        return semester + ";" + name + ";" + tag + ";" + beginn + ";" + ende + ";" + raum
                + ";" + dozent + ";" + kuerzel + ";" + checked + ";" + id;
    }

    //Ausgabefunktion für die Anzeige im Katalog und/oder Stundenplan
    public String createTitle() {
        return semester + " - " + name + " - " + tag + " - " + raum + '\n' + beginn + " - " + ende + '\n' + dozent;
    }

    public String createTitle_ohne_Tag() {
        return name + '\n' + " " + semester;
    }

    public String createAttribute() {
        return raum + '\n' + beginn + " - " + ende + '\n' + dozent;
    }
}
