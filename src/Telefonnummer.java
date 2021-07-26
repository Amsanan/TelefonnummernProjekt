public class Telefonnummer {
    /**
     * Diese Klasse dient der Zusammenbringung der Werte der Json-Objekte(Vorwahl Objekten) und den Nummern des Sipgate-Arrays
     */
    String Land;
    String Abkürzung;
    String Tnummer;
    String vorwahl;
    /*
     *Aus dem Grund, dass die Attribute von Vorwahl-Objekten gespeichert werden müssen, sind hier drei Attribute identisch .
     * Zusätzlich muss die Telefonnummer gespeichert werden, weshalb noch ein Attribut  hinzugefügt wurde.
     */
    public Telefonnummer(String Land,String Abkürzung,String Tnummer,String vorwahl){
        this.Land =Land;
        this.Abkürzung =Abkürzung;
        this.Tnummer=Tnummer;
        this.vorwahl=vorwahl;
    }
    public String getAbkürzung() {
        return Abkürzung;
    }



    public String getTnummer() {
        return Tnummer;
    }

    public void setTnummer(String tnummer) {
        Tnummer = tnummer;
    }



    public String getVorwahl() {
        return vorwahl;
    }





    public String getLand() {
        return Land;
    }





}
