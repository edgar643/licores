package Model;

public class DTO_Alcohol {
    private String nom;
    private Float graduation;
    private String procedence;
    private int year;
    private int type;
    private Founder[] founders;
    private int[] combinations;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Float getGraduation() {
        return graduation;
    }

    public void setGraduation(Float graduation) {
        this.graduation = graduation;
    }

    public String getProcedence() {
        return procedence;
    }

    public void setProcedence(String procedence) {
        this.procedence = procedence;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Founder[] getFounders() {
        return this.founders;
    }

    public void setFounders(Founder[] founders) {
        this.founders = founders;
    }

    public int[] getCombinations() {
        return combinations;
    }

    public void setCombinations(int[] combinations) {
        this.combinations = combinations;
    }

    public class Founder {
        private String name;
        private int born;

        public int getBorn() {
            return born;
        }

        public void setBorn(int born) {
            this.born = born;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}


