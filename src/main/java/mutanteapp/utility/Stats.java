package mutanteapp.utility;

/**
 * Created by Pablo on 10/5/2019.
 */
public class Stats {
    int count_mutant_dna;
    int count_human_dna;
    float ratio;

    public Stats(int count_mutant_dna, int count_human_dna,float ratio) {
        this.count_mutant_dna = count_mutant_dna;
        this.count_human_dna = count_human_dna;
        this.ratio = ratio/100;
    }

    public Stats() {
    }


    public int getCount_mutant_dna() {
        return count_mutant_dna;
    }

    public void setCount_mutant_dna(int count_mutant_dna) {
        this.count_mutant_dna = count_mutant_dna;
    }

    public int getCount_human_dna() {
        return count_human_dna;
    }

    public void setCount_human_dna(int count_human_dna) {
        this.count_human_dna = count_human_dna;
    }

    public float getRatio() {
        return ratio;
    }

    public void setRatio(float ratio) {
        this.ratio = ratio;
    }
}
