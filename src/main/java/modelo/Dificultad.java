package modelo;

public enum Dificultad {

    EASY(4), MEDIUM(6), HARD(8);
    private int velocidad;


    Dificultad(int dificultad) {
        this.velocidad = dificultad;
    }

    public int getVelocidad() {
        return velocidad;
    }

}
