import mutanteapp.utility.ADN;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Pablo on 6/5/2019.
 */
public class ADNTest {
    ADN mutante;
    long TInicio, TFin, tiempo;

    @Before
    public void InializeTest() {
        mutante = new ADN();
        //mutante =ADN.getInstance();
    }


    @Test
    public void testDesializeMethod() {

        System.out.println("---------------------------------------------------------------------");
        System.out.println("testDesializeMethod");
        System.out.println("---------------------------------------------------------------------");
        String[] dna = {"ATGCGA", "CAGTGC"};
        mutante.setAdnSecuence(dna);
        Assert.assertTrue(mutante.getAdnSecuence().get('A').get(0).get(0) == 0);
        Assert.assertTrue(mutante.getAdnSecuence().get('A').get(0).get(1) == 5);
        Assert.assertTrue(mutante.getAdnSecuence().get('T').get(0).get(0) == 1);
        Assert.assertTrue(mutante.getAdnSecuence().get('G').get(0).get(0) == 2);
        Assert.assertTrue(mutante.getAdnSecuence().get('C').get(0).get(0) == 3);

        Assert.assertTrue(mutante.getAdnSecuence().get('A').get(1).get(0) == 1);
        Assert.assertTrue(mutante.getAdnSecuence().get('T').get(1).get(0) == 3);
        Assert.assertTrue(mutante.getAdnSecuence().get('G').get(1).get(0) == 2);
        Assert.assertTrue(mutante.getAdnSecuence().get('G').get(1).get(1) == 4);
        Assert.assertTrue(mutante.getAdnSecuence().get('C').get(1).get(0) == 0);
        Assert.assertTrue(mutante.getAdnSecuence().get('C').get(1).get(1) == 5);

        Assert.assertTrue(mutante.getAdnSecuence().size() == 4);

        System.out.println(mutante);

    }

    /*
        Testeamos un mutante que tenga adn en la quinta fila
    */

    @Test
    public void testMutanteByRowWithC() {
        System.out.println("---------------------------------------------------------------------");
        System.out.println("testMutanteByRow");
        System.out.println("---------------------------------------------------------------------");
        String[] adnMutante = {"ATGCGA", "CAGTGC", "TTCTGT", "TGTAGG", "CCCCTA", "TCACTG"};

        TInicio = System.currentTimeMillis();
        mutante.setAdnSecuence(adnMutante);
        Assert.assertTrue(mutante.isHuman());
        Assert.assertEquals(java.util.Optional.of('C'), java.util.Optional.ofNullable(mutante.getOutput()));
        System.out.println(mutante.getAdnSecuence().get('C'));

        TFin = System.currentTimeMillis();
        System.out.println(mutante);
        System.out.println(TInicio - TFin);
        //Assert.assertTrue(mutante.getOutput()==4);
    }

    /*
        Repetimos el test Para medir la eficiencia del algoritmo
    */


    @Test
    public void testMutanteByRowWithG() {
        System.out.println("---------------------------------------------------------------------");
        System.out.println("testMutanteByRow");
        System.out.println("---------------------------------------------------------------------");
        String[] adnMutante = {"ATGCGA", "GGGGGC", "TTCTAT", "TGTACG", "CCTCTA", "TCACTG"};

        TInicio = System.currentTimeMillis();
        mutante.setAdnSecuence(adnMutante);
        Assert.assertTrue(mutante.isHuman());
        TFin = System.currentTimeMillis();
        System.out.println(TInicio - TFin);
        Assert.assertEquals(java.util.Optional.of('G'), java.util.Optional.ofNullable(mutante.getOutput()));
        System.out.println(mutante.getAdnSecuence().get('G'));
        System.out.println(mutante);

        //Assert.assertTrue(mutante.getOutput()==4);
    }


    /*
        Testeamos un mutante que tenga adn en la 3er columna
    */

    @Test
    public void testMutanteByColumn() {

        System.out.println("---------------------------------------------------------------------");
        System.out.println("testMutanteByColumn");
        System.out.println("---------------------------------------------------------------------");
        String[] adnMutante = {"ATGCGA", "CAGTGC", "TTGTGT", "TGGATC", "ACCCTA", "TCACTG"};
        //mutanteapp.utility.ADN mutante2 = new mutanteapp.utility.ADN();
        TInicio = System.currentTimeMillis();
        mutante.setAdnSecuence(adnMutante);
        Assert.assertTrue(mutante.isHuman());
        TFin = System.currentTimeMillis();
        System.out.println(mutante);
        System.out.println(TInicio - TFin);

    }


    /*
      Testeamos un mutante que tenga adn en la diagonal principal fila 0 columna 0, fila 1 columna 1.....
    */

    @Test
    public void testMutanteByMainDiagonalsWithA() {
        System.out.println("---------------------------------------------------------------------");
        System.out.println("testMutanteByMainDiagonalsWithA");
        System.out.println("---------------------------------------------------------------------");
        String[] adnMutante = {"ATGCGA", "CAGTGC", "TTATGT", "TGGAAC", "ACCCTA", "TCACTG"};
        //mutanteapp.utility.ADN mutante = new mutanteapp.utility.ADN();
        TInicio = System.currentTimeMillis();
        mutante.setAdnSecuence(adnMutante);
        Assert.assertTrue(mutante.isHuman());
        TFin = System.currentTimeMillis();
        System.out.println(mutante);
        System.out.println(TInicio - TFin);
    }


    /*
        Testeamos un mutante por diagonal principal para letra T y ver su tiempo de ejecucion
    */


    @Test
    public void testMutantByMainDiagonalsWithT() {
        System.out.println("---------------------------------------------------------------------");
        System.out.println("testMutantByMainDiagonalsWithT");
        System.out.println("---------------------------------------------------------------------");
        String[] adnMutante = {"TTGCGA", "ATGTGC", "TATTGT", "TGGTAC", "ACCCTA", "TCACTG"};
        //mutanteapp.utility.ADN mutante2 = new mutanteapp.utility.ADN();
        TInicio = System.currentTimeMillis();
        mutante.setAdnSecuence(adnMutante);
        Assert.assertTrue(mutante.isHuman());
        TFin = System.currentTimeMillis();
        System.out.println(mutante);
        System.out.println(TInicio - TFin);
    }


    /*
        Testeamos un mutante por diagonal principal para letra G y ver su tiempo de ejecucion
    */

    @Test
    public void testMutantByMainDiagonalsWithG() {
        System.out.println("---------------------------------------------------------------------");
        System.out.println("testMutantByMainDiagonalsWithG");
        System.out.println("---------------------------------------------------------------------");
        String[] adnMutante = {"GTGCGA", "AGTTGC", "TAGTGT", "TGAGCC", "ACCCTA", "TCACTG"};
        //mutanteapp.utility.ADN mutante2 = new mutanteapp.utility.ADN();
        TInicio = System.currentTimeMillis();
        mutante.setAdnSecuence(adnMutante);
        Assert.assertTrue(mutante.isHuman());
        TFin = System.currentTimeMillis();
        System.out.println(mutante);
        System.out.println(TInicio - TFin);
    }

    /*
        Testeamos un mutante por la antidiagonal princial es fila 5 columna 0 , fila 4 columna 1 ...
    */

    @Test
    public void testMutantByAntiDiagonalsWithA() {
        System.out.println("---------------------------------------------------------------------");
        System.out.println("testMutantByAntiDiagonalsWithA");
        System.out.println("---------------------------------------------------------------------");
        String[] adnMutante = {"GTGCGA", "AGTTGC", "TAATGT", "TGAACC", "ACCCAG", "TCACTA"};
        //mutanteapp.utility.ADN mutante2 = new mutanteapp.utility.ADN();
        TInicio = System.currentTimeMillis();
        mutante.setAdnSecuence(adnMutante);
        Assert.assertTrue(mutante.isHuman());
        TFin = System.currentTimeMillis();
        System.out.println(mutante);
        System.out.println(TInicio - TFin);
    }


    /*
        Repetimos el mismo caso de prueba anterior con la letra C , pero para medir el tiempo
        de ejecucion Arrancado de la FIla 5 Columna 0, y continuando por Fila 4 Columna 1 y asi sucesivamente.
    */

    @Test
    public void testMutantByAntiDiagonalsWithC() {
        System.out.println("---------------------------------------------------------------------");
        System.out.println("testMutantByAntiDiagonalsWithC");
        System.out.println("---------------------------------------------------------------------");
        String[] adnMutante = {"GTGCGA", "AAGTGC", "TCTCGT", "TGCACG", "ACGTCG", "CCACTG"};
        //mutanteapp.utility.ADN mutante2 = new mutanteapp.utility.ADN();
        TInicio = System.currentTimeMillis();
        mutante.setAdnSecuence(adnMutante);
        Assert.assertTrue(mutante.isHuman());
        TFin = System.currentTimeMillis();
        System.out.println(mutante);
        System.out.println(TInicio - TFin);
    }

    /*
        Repetimos el mismo caso de prueba anterior con la letra T , pero para medir el tiempo
        de ejecucion Arrancado de la FIla 5 Columna 0, y continuando por Fila 4 Columna 1 y asi sucesivamente.
    */


    @Test
    public void testMutantByAntiDiagonalsWithT() {
        System.out.println("---------------------------------------------------------------------");
        System.out.println("testMutantByAntiDiagonalsWithT");
        System.out.println("---------------------------------------------------------------------");
        String[] adnMutante = {"GTGCGA", "AAGTGC", "TTTCGT", "TGTACG", "ATGTCG", "TCACTG"};
        //mutanteapp.utility.ADN mutante2 = new mutanteapp.utility.ADN();
        TInicio = System.currentTimeMillis();
        mutante.setAdnSecuence(adnMutante);
        Assert.assertTrue(mutante.isHuman());
        TFin = System.currentTimeMillis();
        System.out.println(mutante);
        System.out.println(TInicio - TFin);
    }



    /*
        Repetimos el mismo caso de prueba anterior con la letra T , pero esta vez arrancando de
        la Fila 4 Columan y siguiendo por Fila 3 Columna 2, y asi hasta sucesivamente.
    */


    @Test
    public void testMutantByAntiDiagonalsWithT2() {
        System.out.println("---------------------------------------------------------------------");
        System.out.println("testMutantByAntiDiagonalsWithT2");
        System.out.println("---------------------------------------------------------------------");
        String[] adnMutante = {"GTGCGA", "AGTATC", "AAGTGT", "GTTACG", "ATGTCG", "GCACTG"};
        //mutanteapp.utility.ADN mutante2 = new mutanteapp.utility.ADN();
        TInicio = System.currentTimeMillis();
        mutante.setAdnSecuence(adnMutante);
        Assert.assertTrue(mutante.isHuman());
        TFin = System.currentTimeMillis();
        System.out.println(mutante);
        System.out.println(TInicio - TFin);
    }



    /*
        Realizamo la busqueda por las diagonales no principales en este caso arrancando por Fila 0 Columna 1,
        y Cuantinando por Fila 1 Columna 2, y asi sucesivamente.
        En este caso debe presentarse la secuencia de la letra T fila 0 Columna 1, Fila 1 Columna 2,
        Fila 2 Columna 3, Fila 3 Columna 4.
    */


    @Test
    public void testMutantByTransverSalDiagonalWithT() {
        System.out.println("---------------------------------------------------------------------");
        System.out.println("testMutantByTransverSalDiagonal");
        System.out.println("---------------------------------------------------------------------");
        String[] adnMutante = {"GTGCGA", "ACTTGC", "TATTGT", "GGACTC", "ACCCTA", "TCACTG"};
        //mutanteapp.utility.ADN mutante2 = new mutanteapp.utility.ADN();
        TInicio = System.currentTimeMillis();
        mutante.setAdnSecuence(adnMutante);
        Assert.assertTrue(mutante.isHuman());
        TFin = System.currentTimeMillis();
        System.out.println(mutante);
        System.out.println("Tiempo de Ejecucion del Algoritmo " + Math.abs(TInicio - TFin));
    }


    /*
        Repitimos el caso de prueba anterior pero la primera ocurrencia de T arrancara en
        Fila 1 Columna 2 hasta llegar a la Fila 4 Columna 5.
    */


    @Test
    public void testMutantByTransverSalDiagonalWithT2() {
        System.out.println("---------------------------------------------------------------------");
        System.out.println("testMutantByTransverSalDiagonalWithT2");
        System.out.println("---------------------------------------------------------------------");
        String[] adnMutante = {"GTGCGA", "ACTAGC", "TAGTGT", "TGAGTC", "ACCGTT", "TCACGT"};
        //mutanteapp.utility.ADN mutante2 = new mutanteapp.utility.ADN();
        TInicio = System.currentTimeMillis();
        mutante.setAdnSecuence(adnMutante);
        Assert.assertTrue(mutante.isHuman());
        Assert.assertEquals(java.util.Optional.of('T'), java.util.Optional.ofNullable(mutante.getOutput()));
        TFin = System.currentTimeMillis();
        System.out.println(mutante);
        System.out.println("Tiempo de Ejecucion del Algoritmo " + Math.abs(TInicio - TFin));
    }



    /*
        Repitimos el caso de prueba anterior pero la primera ocurrencia de T arrancara en
        Fila 2  Columna 3 , Fila 3 Columna 4, Fila 4 Columna 5. Como la matriz es de 6 X 6
        esperamos que falle.
    */


    @Test
    public void testMutantByTransverSalDiagonalWithT3() {
        System.out.println("---------------------------------------------------------------------");
        System.out.println("testMutantByTransverSalDiagonalWithT2");
        System.out.println("---------------------------------------------------------------------");
        String[] adnMutante = {"GTGCGA", "ACGTGC", "TAGTGT", "TGAGTC", "ACCGTT", "TCACGC"};
        //mutanteapp.utility.ADN mutante2 = new mutanteapp.utility.ADN();
        TInicio = System.currentTimeMillis();
        mutante.setAdnSecuence(adnMutante);

        Assert.assertFalse(mutante.isHuman());
        //Assert.assertEquals(java.util.Optional.of('T'),mutante.getOutput());
        TFin = System.currentTimeMillis();
        System.out.println(mutante);
        System.out.println("Tiempo de Ejecucion del Algoritmo " + Math.abs(TInicio - TFin));

    }


    /*
       Testeamos que exista una coincidencia por debajo de la Diagonal principal, en este caso
       Probaremos que exista la coincidencia de la letra en A en las posiciones Fila 2 Columna 1
       Fila 3 Columna 2 y asi sucesivamente.
    */

    @Test
    public void testMutantByAntiTransversalesWithA() {
        System.out.println("---------------------------------------------------------------------");
        System.out.println("testMutantByAntiTransversalesWithA");
        System.out.println("---------------------------------------------------------------------");
        String[] adnMutante = {"GTGCGA", "ACTTGC", "TAGTGT", "TGAGTC", "GCCATA", "TCACTG"};
        //mutanteapp.utility.ADN mutante2 = new mutanteapp.utility.ADN();
        TInicio = System.currentTimeMillis();
        mutante.setAdnSecuence(adnMutante);
        System.out.println(mutante);
        Assert.assertTrue(mutante.isHuman());
        TFin = System.currentTimeMillis();
        System.out.println(mutante);
        System.out.println("Tiempo de Ejecucion del Algoritmo " + Math.abs(TInicio - TFin));

    }






    /*
        Testeamos el caso donde no se coincida por ninguno de los casos anteriores.
        Es decir no exista una secuencia de que verifique que el adn es Mustante
    */


    @Test
    public void testIsNotMutant() {
        System.out.println("---------------------------------------------------------------------");
        System.out.println("testIsNotMutant");
        System.out.println("---------------------------------------------------------------------");
        String[] adnMutante = {"GTGCGA", "ACTTGC", "TAGTGT", "TGAGCC", "ACCCTA", "TCACTG"};
        //mutanteapp.utility.ADN mutante2 = new mutanteapp.utility.ADN();
        TInicio = System.currentTimeMillis();
        mutante.setAdnSecuence(adnMutante);
        Assert.assertFalse(mutante.isHuman());
        TFin = System.currentTimeMillis();
        System.out.println(mutante);
        System.out.println(TInicio - TFin);
    }

    /*
        Testeamos el caso donde se ingrese un string vacio
    */

    @Test
    public void testWithMutantEmpty() {
        System.out.println("---------------------------------------------------------------------");
        System.out.println("testWithMutantEmpty");
        System.out.println("---------------------------------------------------------------------");
        String[] adnMutante = {""};
        //mutanteapp.utility.ADN mutante2 = new mutanteapp.utility.ADN();
        TInicio = System.currentTimeMillis();
        mutante.setAdnSecuence(adnMutante);
        Assert.assertFalse(mutante.isHuman());
        TFin = System.currentTimeMillis();
        System.out.println(mutante);
        System.out.println(TInicio - TFin);
    }


}
