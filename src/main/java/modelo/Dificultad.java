package modelo;

public enum Dificultad {

    EASY(3), MEDIUM(6), HARD(8);
    private int dificultad;


    Dificultad(int dificultad) {
        this.dificultad = dificultad;
    }

    public int getDificultad() {
        return dificultad;
    }

}
