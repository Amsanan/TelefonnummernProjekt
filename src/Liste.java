import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Liste {
    /**
     * Diese Klasse dient dem Prozess der Formatierung der Telefonnummern und Listung der formatierten Telefonnummern
     */
    public Liste(){

        printTnummern();

    }
    String[] SipgateNummern={"0211/6355550","0049 (0)211 - 635555 0","+49 0211 - 6 3 5 5 5 5 0","001 202 326 7300","000093736290331"};

    Readerjson readerjson= new Readerjson();
    ArrayList<Vorwahl> List =readerjson.javaObjectsfromJson();
    ArrayList<Telefonnummer> telefonnummern =new ArrayList<>();

    public void Telefonnummer1Kreir() {


       /*Es werden Telefonnummern-Objekte durch die Vorwahl-Objekten aus der Liste und den Sipgatenummern erstellt.
        * Diese Telefonnummern sollen dem E164-Nummerierungsplan entsprechen.
        */
        //For-Each Schleife 1
        for (String num : SipgateNummern) {


            /*
             * Es wird von 1 bis 3 hochgezählt.
             * Pro Zählung wird ein substring erstellt.
             * Dieser Substring beginnt mit einem  Plus-zeichen, da Vorwahlen mit einem Pluszeichen anfangen.
             * Zusätzlich erhält er von den Nummern aus dem Sipgate-Nummern Array die ersten drei Stellen.
             */
                for (int k = 1; k <= 3; k++) {
                    String tempstring = "+" + num.substring(0, k);


                    //For-Each Schleife 2
                    /*
                    * Es werden Vorwahl-Objekte aus der List ausgelesen.
                    * Der jeweilige Dial_code  der Vorwahl-Objekte wird mit dem tempstring verglichen.
                    * Bei Übereinstimmung:
                    * Wird ein Telefonnummern-Objekt anhand der Methode E164 und TempTelefon erstellt.
                    * Dieses Telefonnummern-Objekt wird in der Liste telefonnummern abgespeichert
                    */
                    for (Vorwahl vorwahl : List) {
                        if (tempstring.compareTo(vorwahl.getVorwahl()) == 0) {
                            telefonnummern.add(E164(TempTelefon(vorwahl, num)));
                        }
                    }
                }
            }
    }


    /*Es wird ein Telefonnummern-Objekt zurückgegeben.
     * Die Attribute :
     * Land, Abkürzung und vorwahl/dial_code sind vom mitgegebenen Objekt vorwahl abhängig.
     * Die Tnummer ist von dem mitgegeben String pTnummer abhängig.
     */
    public Telefonnummer TempTelefon(Vorwahl vorwahl,String pTnummer){
        return new Telefonnummer(vorwahl.getLand(), vorwahl.getAbkürzung(), pTnummer, vorwahl.getVorwahl());
    }


    /*Es wird ein Telefonnummern-Objekt zurückgegeben(telefonn).
     *   Es wird zuerst vom mitgegebenen Telefonnummern-Objekt(telefonn) das Attribut vorwahl zwischengespeichert (tempVorwahl).
     * Es wird ein String tempstring erstellt:
        * Es erhält den Wert der tnummer des telefonn und ergänzt es am Anfang des Strings mit einem Pluszeichen.
        * Nach  der Vorwahl des Telefon-Objektes(telefonn) wird ein Leerzeichen eingefügt.
     * Das Attribut Tnummer des Telefon-Objektes erhält den Wert des tempStrings.
     */
    public Telefonnummer E164(Telefonnummer telefonn){
        String tempVorwahl=telefonn.getVorwahl();
        String tempstring =telefonn.Tnummer.substring(0,0)+'+'+ telefonn.Tnummer;
        tempstring=tempstring.substring(0,tempVorwahl.length())+' '+tempstring.substring(tempVorwahl.length());
        telefonn.setTnummer(tempstring);
        return telefonn;
    }


    /*
    * Die Methode SipgateTnSort entfernt alle Zeichen, die nicht als Ziffer erkannt wird.
    * Es wird auch schon überprüft, ob die Nummern im Array zu groß oder zu klein sind.
    */
    public void SipgateTnSort() {


        /*
         * Es wird über das Array SipgateNummern iteriert:
         */
        for (int i = 0; i < SipgateNummern.length; i++) {

            //Zeichen werden entfernt
                SipgateNummern[i] = SipgateNummern[i].replaceAll("[^\\d]", "");
                StringBuilder stringBuilder = new StringBuilder(SipgateNummern[i]);

                //Die Nullen am Anfang werden entfernt
                int zaehler = 0;
                while (SipgateNummern[i].charAt(zaehler) == '0') {
                    stringBuilder.setCharAt(zaehler, ' ');
                    zaehler++;
                }
                SipgateNummern[i] = stringBuilder.toString();
                SipgateNummern[i] = SipgateNummern[i].replace(" ", "");

                //Die Nummern die zu kurz oder zu lang sind werden entfernt
                if(SipgateNummern[i].length()>15){
                    System.out.println("Die Telefonnummer"+": "+SipgateNummern[i]+" "+"ist leider zu groß für eine Telefonnummer");
                    SipgateNummern[i]="000";
                }else if(SipgateNummern[i].length()<=3){
                    System.out.println("Die Telefonnummer"+": "+SipgateNummern[i]+" "+"ist leider zu klein für eine Telefonnummer und ist eher eine Vorwahl");
                    SipgateNummern[i]="000";
                }
            }
            Telefonnummer1Kreir();
    }

    /*
     *  Die Methode SipgateTnSort wird ausgeführt.
     * Danach werden die Nummern listenartig ausgegeben.
     */
   public void printTnummern(){
       SipgateTnSort();

       for (Telefonnummer telefonnummer : telefonnummern) {
           String Land = telefonnummer.getLand();
           System.out.println("Land" + ": " + Land);
           String Abkürzung = telefonnummer.getAbkürzung();
           System.out.println("Abkürzung" + ": " + Abkürzung);
           String Tnummer = telefonnummer.getTnummer();
           System.out.println("Telefonnummer" + ": " + Tnummer);
           String Vorwahl = telefonnummer.getVorwahl();
           System.out.println("Vorwahl" + ": " + Vorwahl + '\n');
       }
   }
    private class Readerjson {
        /**
         * Die Reader-Klasse dient dazu die Vorwahlen und das dazu gehörige Land mit Abkürzung aus der Json-Datei in Java umzuwandeln und brauchbar zu machen
         */



        ArrayList<Vorwahl>list =new ArrayList<>();


        public ArrayList<Vorwahl> javaObjectsfromJson(){

                 /*
                 * Vorerst wird ein Array erstellt, welches JsonObjekte speichern kann.
                 * Das jsonarray wird mit dem Array aus  der Json-Datei(Dial_Code.json) gefüllt.
                    *Es wird über das jsonarray iteriert.
                    * Jedes Json-Objekt aus dem Array wird in ein Vorwahl Objekt konvertiert.
                    * Diese Vorwahl-Objekte werden in eine Liste gespeichert.
                 * Die Liste wird zurückgegeben.
                 */
            try {
                Gson gson=new Gson();
                InputStreamReader inputStreamReader =getinputStreamR("/Dial_Code.json");
                JsonObject[] jsonarray =gson.fromJson(inputStreamReader,JsonObject[].class);
                for (JsonObject jsonObject : jsonarray) {
                    Vorwahl vorwahl = gson.fromJson(jsonObject, Vorwahl.class);
                    list.add(vorwahl);

                }
                inputStreamReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return list;
        }
            public InputStreamReader getinputStreamR(String json_File){
                InputStream inputStream=getClass().getResourceAsStream(json_File);
                InputStreamReader inputStreamR=new InputStreamReader(inputStream,StandardCharsets.UTF_8);
                return inputStreamR;
            }


    }

    
}
