public class Vorwahl {
    /**
     * Die Klasse Vorwahl ist wichtig um Json-Objekte in Java Objekte umzuwandeln.
     * Hierbei ist zu beachten, dass diese auch mit Gettern erreichbar sind.
     */
    private String Land;
    private String vorwahl;
    private String Abkürzung;
    /*
     * Aus dem Grund, dass die Json Objekte 3 Attribute besitzen, besitzt diese Klasse auch nur 3 Attribute.
     */
    public Vorwahl(String pLand,String pVorwahl,String pAbkürzung){
       this.Land = pLand;
       this.Abkürzung =pAbkürzung;
       this.vorwahl=pVorwahl;
    }

    public String getVorwahl() {
        return vorwahl;
    }

    public String getLand() {
        return Land;
    }

    public String getAbkürzung() {
        return Abkürzung;
    }

}
