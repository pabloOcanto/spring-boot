package mutanteapp.utility;
import org.springframework.context.annotation.Bean;

import java.util.*;
import java.util.stream.IntStream;

/**
 * Created by Pablo on 3/5/2019.
 */

public class ADN {

    private String[] dna;
    private Map<Character, Map<Integer, List<Integer>>> adnSecuence;

    private int f;
    private Character output;
    private boolean status = false;


    public ADN() {
    }



    public Map<Character, Map<Integer, List<Integer>>> getAdnSecuence() {
        return adnSecuence;
    }

    public void setAdnSecuence(String[] dna) {
        this.dna = dna;
        this.adnSecuence = this.Desiarialize(dna);
    }

    public Character getOutput() {
        return output;
    }

    /*Deserializacion de los caracteres
        y por cada catacter se guarda en fila y columna que aparece
        {A={0=[0, 5], 1=[1], 2=[2], 3=[0, 2, 3], 4=[5], 5=[2]}
     */

    private Map<Character, Map<Integer, List<Integer>>> Desiarialize(String[] dna){
        f = 0;
        Map<Character, Map<Integer, List<Integer>>> map = new HashMap<>();
        Arrays.stream(dna).forEach(s -> {

            IntStream.range(0, s.length()).forEach(i -> {
                Map<Integer, List<Integer>> caracter;
                List<Integer> fila;
                Character c = s.charAt(i);
                caracter = map.get(c);
                if (caracter != null) {
                    fila = caracter.get(f);
                    if (fila != null) {
                        fila.add(i);
                        caracter.put(f, fila);
                        map.put(c, caracter);
                    } else {
                        fila = new ArrayList<>();
                        fila.add(i);
                        caracter.put(f, fila);
                        map.put(c, caracter);
                    }
                } else {
                    fila = new ArrayList<>();
                    fila.add(i);
                    caracter = new TreeMap<>();
                    caracter.put(f, fila);
                    map.put(c, caracter);
                }
            });
            f += 1;
        });

        return map;
    }

    /*Este metodo chequea primero si no existe coincidencia tanto en fila o columna de una mutanteapp.utility.ADN
      Sino se encontro, inicia el proceso de busqueda en las diagonales de la matriz del mutanteapp.utility.ADN
     */

    public boolean isHuman() {

        if (this.adnSecuence == null || this.adnSecuence.size() == 0){
            return false;
        }

        this.adnSecuence.forEach((c,m)->{
            //System.out.println("key "+c);
            if (!status){
                output =c;
                if (this.findByRowAndColumn(adnSecuence.get(c))){
                    status= true;
                } else {
                    if (this.findMatchesByDiagonal(adnSecuence.get(c))){
                        status= true;
                    }
                }
            }
        });

        if (status){
            return true;
        }

        return false;

    }



    private boolean findByRowAndColumn(Map<Integer, List<Integer>> map) {
        boolean check = false;
        int size = map.size();
        //System.out.println(map);
        for (int i = 0; i <= size; i++) {

            //i+1=[1]

            if (map.containsKey(i)){
                List<Integer> list = map.get(i); //i=0=[0, 5]
                //System.out.println("i " + i + " list " + list.toString());

                if (this.findByMatchesRow(list)) {
                    //System.out.println("Salida por findByMatchesRow");
                    //output =i;
                    check = true;
                    break;
                }

                if (this.findMatchesByColumn(map, list, i)) {
                    //System.out.println("Salida por findMatchesByColumn");
                    check = true;
                    break;
                }

            }


        }

        return check;

    }



    /*
   chequea Adn por fila si existe mas de 4 coincidencias consecutivas
  */
    private boolean findByMatchesRow(List<Integer> list){

       int matchByrow=0;
        for (int l =0; l<list.size()-1 && matchByrow<3;l++){

            if (list.get(l)+1 == list.get(l+1)){
                matchByrow +=1;
            } else {
                matchByrow =0;
            }
        }

        if (matchByrow == 3){
           return true;
        }

        return false;

    }

    /*
    Cheque se existe adn x columna si existe mas de 4 coincidencias
    */
    private boolean findMatchesByColumn(Map<Integer, List<Integer>> map,List<Integer> list, int i ){

        int matchByCoulmn = 0;

        for (int j = 0; j <= list.size()-1 && matchByCoulmn < 3; j++) {

            int elemento = list.get(j); // elemento i=0 columna =0 0

            //System.out.println("Fila "+i+" Columna "+j);
            int fila = i;
            matchByCoulmn = 0;
            for (int k = i + 1; k < map.size() && matchByCoulmn < 3; k++) { //Itero sobre el resto {1=[1], 2=[2], 3=[0, 2, 3], 4=[5], 5=[2]}

                if (map.containsKey(k)) {
                    if (map.get(k).contains(elemento) && (fila + 1 == k)) {
                        matchByCoulmn += 1;
                        fila += 1;
                    } else {
                        matchByCoulmn = 0;
                    }
                }

            }
        }


        if (matchByCoulmn == 3) {
            return true;
        }

        return false;


    }



    /*
    Cheque si existen coincidencias x diagonal principal , Anti Diagonal o transversales.
    */


    private boolean findMatchesByDiagonal(Map<Integer, List<Integer>> map) {


        int mainDiagoanl = 0;
        int antiDiagonal = 0;
        int transvDiagonal = 0;
        int antiTransvDiagonal = 0;
        int k = map.size()-1;

        for (int i = 0; i < map.size() && mainDiagoanl < 4 && antiDiagonal < 4 && transvDiagonal < 4 && antiTransvDiagonal<4; i++) {

            if (map.containsKey(i)) {

                if (map.get(i).contains(i)) {
                    mainDiagoanl += 1;
                } else {
                    mainDiagoanl = 0;
                }


            } else {
                mainDiagoanl = 0;

            }

            if (map.containsKey(k)) {

                if (map.get(k).contains(i)) {
                    antiDiagonal += 1;
                } else {
                    antiDiagonal = 0;
                }
            } else {
                antiDiagonal = 0;
            }

            k -=1;
            antiTransvDiagonal =0;
            transvDiagonal=0;
            for (int t=i+1;t<map.size() && mainDiagoanl < 4 && antiDiagonal < 4 && transvDiagonal < 4 && antiTransvDiagonal<4;t++){

                if (map.containsKey(t-1)) {
                    if (map.get(t-1).contains(t) ) {
                        transvDiagonal += 1;
                    } else {
                        transvDiagonal = 0;
                    }
                } else {
                    transvDiagonal = 0;
                }


                if (map.containsKey(t)) {
                    if (map.get(t).contains(t-1) ) {
                        antiTransvDiagonal += 1;
                    } else {
                        antiTransvDiagonal = 0;
                    }
                } else {
                    antiTransvDiagonal = 0;
                }

            }


        }



        if (mainDiagoanl == 4 ){
            //System.out.println("Salida por MainDiagonal");
            return true;
        }


        if (antiDiagonal == 4 ){
            //System.out.println("Salida por Antidiagonal");
            return true;
        }

        if (transvDiagonal == 4 ){
            //System.out.println("Salida por transvDiagonal");
            return true;
        }

        if (antiTransvDiagonal == 4 ){
            //System.out.println("Salida por antiTransvDiagonal");
            return true;
        }

        return false;
    }


    @Override
    public String toString() {

        return  " mutanteapp.utility.ADN secuence \n A" + adnSecuence.get('A') +"\n "+ "C" +adnSecuence.get('C')+ "\n "+ "G" + adnSecuence.get('G')  + "\n "+ "T"+ adnSecuence.get('T');

    }


}
