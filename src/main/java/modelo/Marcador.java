package modelo;

import javafx.geometry.Pos;

import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Marcador extends HBox {

   private final int TAMANO_NUMEROS = 30;
   private Text puntosIA;
   private Text puntosJugador;
   public Marcador() {
      inicializar();
   }



   private void inicializar() {

     setTranslateY(50);
     setMinWidth(HelpTools.WIDTH);
     setAlignment(Pos.CENTER);
     setSpacing(100);

     getChildren().add(inicializarMarcadorIa());

     getChildren().add(inicializarMarcadorJugador());

   }

   private HBox inicializarMarcadorIa(){
      HBox puntiacionIA = new HBox();
      this.puntosIA = new Text("0");
      puntosIA.setFont(Font.font("", FontWeight.BOLD ,TAMANO_NUMEROS));
      puntosIA.setFill(HelpTools.COLOR_ITEMS);
      puntiacionIA.getChildren().add(puntosIA);
      return puntiacionIA;
   }

   private HBox inicializarMarcadorJugador(){
      HBox puntuacionJugador = new HBox();
      this.puntosJugador = new Text("0");
      puntosJugador.setFont(Font.font("", FontWeight.BOLD ,TAMANO_NUMEROS));
      puntosJugador.setFill(HelpTools.COLOR_ITEMS);
      puntuacionJugador.getChildren().add(puntosJugador);
      return puntuacionJugador;
   }

   public void anotarPuntoIA(int nuevaPuntuacion){
      puntosIA.setText(String.valueOf(nuevaPuntuacion));
   }

   public void anotarPuntoJugador(int nuevaPuntuacion){
      puntosJugador.setText(String.valueOf(nuevaPuntuacion));
   }
}
