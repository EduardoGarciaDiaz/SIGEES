/*
 * Autor: Daniel Garcia Arcos y Tristan Eduardo Suarez Santiago
 * Fecha de creación: xx/05/2023
 * Descripción: Contorlado de la vista AsignarCajon.
 */

package javafxsigees.controladores;

import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javafx.util.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafxsigees.JavaFXSIGEES;
import javafxsigees.modelos.INotificacionOperacionMulta;
import javafxsigees.modelos.dao.AlquilerCajonDAO;
import javafxsigees.modelos.dao.CuotaDAO;
import javafxsigees.modelos.dao.DAOException;
import javafxsigees.modelos.dao.TarjetaDAO;
import javafxsigees.modelos.pojo.AlquilerCajon;
import javafxsigees.modelos.pojo.Cuota;
import javafxsigees.modelos.pojo.Tarjeta;
import javafxsigees.modelos.pojo.Usuario;
import javafxsigees.utils.Utilidades;


public class FXMLAsignarCajonController implements Initializable, INotificacionOperacionMulta {

    
//<editor-fold defaultstate="collapsed" desc="Inyección de cajones">
     @FXML
    private ImageView btnPerfil;
    @FXML
    private Pane btnCerrarSesion;
    @FXML
    private ImageView ivTipoVehiculo;
    @FXML
    private Label lbServicoGratis;
    @FXML
    private Label lbCuotaCobro;
    @FXML
    private ImageView imvRegistarMulta;
    @FXML
    private ImageView imvVolver;
    @FXML
    private Label lbServicoGratis1;
    @FXML
    private ImageView btnCancelar;
    @FXML
    private Pane btnTrajetaPerdida;
    @FXML
    private Rectangle ciculoDisponible;
    @FXML
    private Label lbDisponible;
    @FXML
    private Pane paneInformacionCajonAsignado;
    @FXML
    private ImageView ivTipoVehiculo1;
    @FXML
    private Label lbIdentificadorTarjeta1;
    @FXML
    private Label lbPisoDespachador1;
    @FXML
    private Label lbTipoVehiculoDespachador1;
    @FXML
    private Label lbHoraAsignacionDespachador1;
    @FXML
    private Label lbFechaAsignacionDespachador1;
    @FXML
    private Pane paneInformacionCobroCajon;
    @FXML
    private ImageView ivTipoVehiculo11;
    @FXML
    private Label lbIdentificadorTarjeta11;
    @FXML
    private Label lbFechaAsignacionCobro;
    @FXML
    private Label lbFechaSalidaCobro;
    @FXML
    private Label lbHoraAsignacionCobro;
    @FXML
    private Label lbHorasTotales;
    @FXML
    private Label lbHoraSalidaCobro;
    @FXML
    private Label lbTotalalPago;
    @FXML
    private Pane paneBtnRegistarMulta;
    @FXML
    private Pane pnBtnPisoUno;
    @FXML
    private Pane pnBtnPisoDos;
    @FXML
    private Pane pnBtnPisoTres;
    @FXML
    private Pane panePisoUno;
    @FXML
    private Rectangle cajonB13;
    @FXML
    private Pane panePisoDos;
    @FXML
    private Rectangle cajonB12;
    @FXML
    private Rectangle cajonB11;
    @FXML
    private Rectangle cajonB10;
    @FXML
    private Rectangle cajonB9;
    @FXML
    private Rectangle cajonB8;
    @FXML
    private Rectangle cajonB7;
    @FXML
    private Rectangle cajonB6;
    @FXML
    private Rectangle cajonB3;
    @FXML
    private Rectangle cajonB2;
    @FXML
    private Rectangle cajonB5;
    @FXML
    private Rectangle cajonB4;
    @FXML
    private Rectangle cajonB14;
    @FXML
    private Rectangle cajonB15;
    @FXML
    private Rectangle cajonB25;
    @FXML
    private Rectangle cajonB24;
    @FXML
    private Rectangle cajonB27;
    @FXML
    private Rectangle cajonB26;
    @FXML
    private Rectangle cajonB23;
    @FXML
    private Rectangle cajonB22;
    @FXML
    private Rectangle cajonB21;
    @FXML
    private Rectangle cajonB20;
    @FXML
    private Rectangle cajonB19;
    @FXML
    private Rectangle cajonB18;
    @FXML
    private Rectangle cajonB17;
    @FXML
    private Rectangle cajonB16;
    @FXML
    private Rectangle cajonB1;
    @FXML
    private Rectangle cajonB28;
    @FXML
    private Pane panePisoTres;
    @FXML
    private Pane panePisoCuatro;
    @FXML
    private Pane pnBtnPisoCuatro;
    @FXML
    private Label lbPiso;
    @FXML
    private Label lbTipoVehiculo;
    @FXML
    private Label lbFecha;
    @FXML
    private Label lbTarifa;
    @FXML
    private Pane paneCajonNoSeleccionado;
    @FXML
    private Rectangle cajonA1;
    @FXML
    private Pane paneInformacionCajon;
    @FXML
    private Rectangle cajonA2;
    @FXML
    private Label lbIdentificadorTarjeta;
    @FXML
    private Rectangle cajonA13;
    @FXML
    private Rectangle cajonA12;
    @FXML
    private Rectangle cajonA11;
    @FXML
    private Rectangle cajonA10;
    @FXML
    private Rectangle cajonA9;
    @FXML
    private Rectangle cajonA8;
    @FXML
    private Rectangle cajonA7;
    @FXML
    private Rectangle cajonA6;
    @FXML
    private Rectangle cajonA3;
    @FXML
    private Rectangle cajonA5;
    @FXML
    private Rectangle cajonA4;
    @FXML
    private Rectangle cajonA14;
    @FXML
    private Rectangle cajonA15;
    @FXML
    private Rectangle cajonA25;
    @FXML
    private Rectangle cajonA24;
    @FXML
    private Rectangle cajonA27;
    @FXML
    private Rectangle cajonA26;
    @FXML
    private Rectangle cajonA23;
    @FXML
    private Rectangle cajonA22;
    @FXML
    private Rectangle cajonA21;
    @FXML
    private Rectangle cajonA20;
    @FXML
    private Rectangle cajonA19;
    @FXML
    private Rectangle cajonA18;
    @FXML
    private Rectangle cajonA17;
    @FXML
    private Rectangle cajonA16;
    @FXML
    private Rectangle cajonA41;
    @FXML
    private Rectangle cajonA40;
    @FXML
    private Rectangle cajonA39;
    @FXML
    private Rectangle cajonA38;
    @FXML
    private Rectangle cajonA37;
    @FXML
    private Rectangle cajonA36;
    @FXML
    private Rectangle cajonA35;
    @FXML
    private Rectangle cajonA34;
    @FXML
    private Rectangle cajonA31;
    @FXML
    private Rectangle cajonA33;
    @FXML
    private Rectangle cajonA32;
    @FXML
    private Rectangle cajonA42;
    @FXML
    private Rectangle cajonA43;
    @FXML
    private Rectangle cajonA29;
    @FXML
    private Rectangle cajonA50;
    @FXML
    private Rectangle cajonA49;
    @FXML
    private Rectangle cajonA48;
    @FXML
    private Rectangle cajonA47;
    @FXML
    private Rectangle cajonA46;
    @FXML
    private Rectangle cajonA45;
    @FXML
    private Rectangle cajonA44;
    @FXML
    private Rectangle cajonA53;
    @FXML
    private Rectangle cajonA54;
    @FXML
    private Rectangle cajonA56;
    @FXML
    private Rectangle cajonA55;
    @FXML
    private Rectangle cajonA59;
    @FXML
    private Rectangle cajonA58;
    @FXML
    private Rectangle cajonA57;
    @FXML
    private Rectangle cajonA28;
    @FXML
    private Rectangle cajonA60;
    @FXML
    private Rectangle cajonA61;
    @FXML
    private Rectangle cajonA62;
    @FXML
    private Rectangle cajonA52;
    @FXML
    private Rectangle cajonA51;
    @FXML
    private Rectangle cajonA30;
    @FXML
    private Rectangle cajonB41;
    @FXML
    private Rectangle cajonB40;
    @FXML
    private Rectangle cajonB39;
    @FXML
    private Rectangle cajonB38;
    @FXML
    private Rectangle cajonB37;
    @FXML
    private Rectangle cajonB36;
    @FXML
    private Rectangle cajonB35;
    @FXML
    private Rectangle cajonB34;
    @FXML
    private Rectangle cajonB31;
    @FXML
    private Rectangle cajonB30;
    @FXML
    private Rectangle cajonB33;
    @FXML
    private Rectangle cajonB32;
    @FXML
    private Rectangle cajonB42;
    @FXML
    private Rectangle cajonB43;
    @FXML
    private Rectangle cajonB29;
    @FXML
    private Rectangle cajonB50;
    @FXML
    private Rectangle cajonB49;
    @FXML
    private Rectangle cajonB48;
    @FXML
    private Rectangle cajonB47;
    @FXML
    private Rectangle cajonB46;
    @FXML
    private Rectangle cajonB45;
    @FXML
    private Rectangle cajonB44;
    @FXML
    private Rectangle cajonB53;
    @FXML
    private Rectangle cajonB54;
    @FXML
    private Rectangle cajonB56;
    @FXML
    private Rectangle cajonB55;
    @FXML
    private Rectangle cajonB59;
    @FXML
    private Rectangle cajonB58;
    @FXML
    private Rectangle cajonB57;
    @FXML
    private Rectangle cajonB60;
    @FXML
    private Rectangle cajonB61;
    @FXML
    private Rectangle cajonB62;
    @FXML
    private Rectangle cajonB52;
    @FXML
    private Rectangle cajonB51;
    @FXML
    private Rectangle cajonC13;
    @FXML
    private Rectangle cajonC12;
    @FXML
    private Rectangle cajonC11;
    @FXML
    private Rectangle cajonC10;
    @FXML
    private Rectangle cajonC9;
    @FXML
    private Rectangle cajonC8;
    @FXML
    private Rectangle cajonC7;
    @FXML
    private Rectangle cajonC6;
    @FXML
    private Rectangle cajonC3;
    @FXML
    private Rectangle cajonC2;
    @FXML
    private Rectangle cajonC5;
    @FXML
    private Rectangle cajonC4;
    @FXML
    private Rectangle cajonC14;
    @FXML
    private Rectangle cajonC15;
    @FXML
    private Rectangle cajonC25;
    @FXML
    private Rectangle cajonC24;
    @FXML
    private Rectangle cajonC27;
    @FXML
    private Rectangle cajonC26;
    @FXML
    private Rectangle cajonC23;
    @FXML
    private Rectangle cajonC22;
    @FXML
    private Rectangle cajonC21;
    @FXML
    private Rectangle cajonC20;
    @FXML
    private Rectangle cajonC19;
    @FXML
    private Rectangle cajonC18;
    @FXML
    private Rectangle cajonC17;
    @FXML
    private Rectangle cajonC16;
    @FXML
    private Rectangle cajonC41;
    @FXML
    private Rectangle cajonC40;
    @FXML
    private Rectangle cajonC39;
    @FXML
    private Rectangle cajonC38;
    @FXML
    private Rectangle cajonC37;
    @FXML
    private Rectangle cajonC36;
    @FXML
    private Rectangle cajonC35;
    @FXML
    private Rectangle cajonC34;
    @FXML
    private Rectangle cajonC31;
    @FXML
    private Rectangle cajonC30;
    @FXML
    private Rectangle cajonC33;
    @FXML
    private Rectangle cajonC32;
    @FXML
    private Rectangle cajonC42;
    @FXML
    private Rectangle cajonC43;
    @FXML
    private Rectangle cajonC29;
    @FXML
    private Rectangle cajonC1;
    @FXML
    private Rectangle cajonC50;
    @FXML
    private Rectangle cajonC49;
    @FXML
    private Rectangle cajonC48;
    @FXML
    private Rectangle cajonC47;
    @FXML
    private Rectangle cajonC46;
    @FXML
    private Rectangle cajonC45;
    @FXML
    private Rectangle cajonC44;
    @FXML
    private Rectangle cajonC53;
    @FXML
    private Rectangle cajonC54;
    @FXML
    private Rectangle cajonC56;
    @FXML
    private Rectangle cajonC55;
    @FXML
    private Rectangle cajonC59;
    @FXML
    private Rectangle cajonC58;
    @FXML
    private Rectangle cajonC57;
    @FXML
    private Rectangle cajonC28;
    @FXML
    private Rectangle cajonC60;
    @FXML
    private Rectangle cajonC61;
    @FXML
    private Rectangle cajonC62;
    @FXML
    private Rectangle cajonC52;
    @FXML
    private Rectangle cajonC51;
    @FXML
    private Rectangle cajonD13;
    @FXML
    private Rectangle cajonD12;
    @FXML
    private Rectangle cajonD11;
    @FXML
    private Rectangle cajonD10;
    @FXML
    private Rectangle cajonD9;
    @FXML
    private Rectangle cajonD8;
    @FXML
    private Rectangle cajonD7;
    @FXML
    private Rectangle cajonD6;
    @FXML
    private Rectangle cajonD3;
    @FXML
    private Rectangle cajonD2;
    @FXML
    private Rectangle cajonD5;
    @FXML
    private Rectangle cajonD4;
    @FXML
    private Rectangle cajonD14;
    @FXML
    private Rectangle cajonD15;
    @FXML
    private Rectangle cajonD25;
    @FXML
    private Rectangle cajonD24;
    @FXML
    private Rectangle cajonD27;
    @FXML
    private Rectangle cajonD26;
    @FXML
    private Rectangle cajonD23;
    @FXML
    private Rectangle cajonD22;
    @FXML
    private Rectangle cajonD21;
    @FXML
    private Rectangle cajonD20;
    @FXML
    private Rectangle cajonD19;
    @FXML
    private Rectangle cajonD18;
    @FXML
    private Rectangle cajonD17;
    @FXML
    private Rectangle cajonD16;
    @FXML
    private Rectangle cajonD41;
    @FXML
    private Rectangle cajonD40;
    @FXML
    private Rectangle cajonD39;
    @FXML
    private Rectangle cajonD38;
    @FXML
    private Rectangle cajonD37;
    @FXML
    private Rectangle cajonD36;
    @FXML
    private Rectangle cajonD35;
    @FXML
    private Rectangle cajonD34;
    @FXML
    private Rectangle cajonD31;
    @FXML
    private Rectangle cajonD30;
    @FXML
    private Rectangle cajonD33;
    @FXML
    private Rectangle cajonD32;
    @FXML
    private Rectangle cajonD42;
    @FXML
    private Rectangle cajonD43;
    @FXML
    private Rectangle cajonD29;
    @FXML
    private Rectangle cajonD1;
    @FXML
    private Rectangle cajonD50;
    @FXML
    private Rectangle cajonD49;
    @FXML
    private Rectangle cajonD48;
    @FXML
    private Rectangle cajonD47;
    @FXML
    private Rectangle cajonD46;
    @FXML
    private Rectangle cajonD45;
    @FXML
    private Rectangle cajonD44;
    @FXML
    private Rectangle cajonD53;
    @FXML
    private Rectangle cajonD54;
    @FXML
    private Rectangle cajonD56;
    @FXML
    private Rectangle cajonD55;
    @FXML
    private Rectangle cajonD59;
    @FXML
    private Rectangle cajonD58;
    @FXML
    private Rectangle cajonD57;
    @FXML
    private Rectangle cajonD28;
    @FXML
    private Rectangle cajonD60;
    @FXML
    private Rectangle cajonD61;
    @FXML
    private Rectangle cajonD62;
    @FXML
    private Rectangle cajonD52;
    @FXML
    private Rectangle cajonD51;
    @FXML
    private Rectangle cajonD63;
    @FXML
    private Rectangle cajonD64;
//</editor-fold>
   
    private Tarjeta tarjetaSeleccionada;
    private AlquilerCajon alquilerCajonRegistro;
    private Rectangle cajonActual;
    private Usuario usuarioGeneral;
    @FXML
    private Label lbTarifaActual;
    String meses[] = {"enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre", "octubre", "noviembre",
        "diciembre"};

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        prepararAnimacionMenu();
        cargarCajones();     
        pnBtnPisoUno.setStyle("fx-background-color: white;");
    }  
    
    public void inicializarRol(Usuario usuarioRol) {
        usuarioGeneral = new Usuario();
        this.usuarioGeneral = usuarioRol;
        if(usuarioRol.getIdTipoUsuario() == 2) {
            imvRegistarMulta.setVisible(false);
        }else {
            imvRegistarMulta.setVisible(true);
        }
    }
    
    public void cargarEstadoCajones(ObservableList<Node> cajones){
        Iterator<Node> it = cajones.iterator();
        while (it.hasNext()) {
            Node next = it.next();
            if(Rectangle.class == next.getClass()) {
                Rectangle cajon = (Rectangle) next;
                Tarjeta tarjeta = obtenerInformacionCajon(cajon);
                
                if ("Disponible".equals(tarjeta.getNombreEstadoCajon())) {
                    cajon.setFill(Color.web("#EFEFEF"));
                } 
                if ("Asignado".equals(tarjeta.getNombreEstadoCajon())) {
                    cajon.setFill(Color.web("#B4C8EF"));
                }
                if ("No disponible".equals(tarjeta.getNombreEstadoCajon())) {
                    cajon.setFill(Color.web("#ff0808"));
                }
            }
        }
    }
    
    private void prepararAnimacionMenu() {
        TranslateTransition menuDesplegado = new TranslateTransition(new Duration(350.0), btnCerrarSesion);
        menuDesplegado.setToX(0);
        TranslateTransition menuCerrado = new TranslateTransition(new Duration(350.0), btnCerrarSesion);
        btnPerfil.setOnMouseClicked((MouseEvent evt)->{
            if (btnCerrarSesion.getTranslateX() != 0) {
                menuDesplegado.play();
            }else{
                menuCerrado.setToX(318.0);
                menuCerrado.play();
            }
        });
    }

    @FXML
    private void clicBtnPisoUno(MouseEvent event) {
        pnBtnPisoUno.setStyle("-fx-background-color: white");
        pnBtnPisoDos.setStyle("-fx-background-color: #f9f9f9");
        pnBtnPisoTres.setStyle("-fx-background-color: #f9f9f9");
        pnBtnPisoCuatro.setStyle("-fx-background-color: #f9f9f9");
        panePisoUno.setVisible(true);
        panePisoDos.setVisible(false);
        panePisoTres.setVisible(false);
        panePisoCuatro.setVisible(false);
    }

    @FXML
    private void clicBtnPisoDos(MouseEvent event) {
        pnBtnPisoDos.setStyle("-fx-background-color: white");
        pnBtnPisoUno.setStyle("-fx-background-color: #f9f9f9");
        pnBtnPisoTres.setStyle("-fx-background-color: #f9f9f9");
        pnBtnPisoCuatro.setStyle("-fx-background-color: #f9f9f9");
        panePisoDos.setVisible(true);
        panePisoUno.setVisible(false);
        panePisoTres.setVisible(false);
        panePisoCuatro.setVisible(false);
    }

    @FXML
    private void clicBtnPisoTres(MouseEvent event) {
        pnBtnPisoTres.setStyle("-fx-background-color: white");
        pnBtnPisoUno.setStyle("-fx-background-color: #f9f9f9");
        pnBtnPisoDos.setStyle("-fx-background-color: #f9f9f9");
        pnBtnPisoCuatro.setStyle("-fx-background-color: #f9f9f9");
        panePisoTres.setVisible(true);
        panePisoUno.setVisible(false);
        panePisoDos.setVisible(false);
        panePisoCuatro.setVisible(false);
    }

    @FXML
    private void clicBtnPisoCuatro(MouseEvent event) {
        pnBtnPisoCuatro.setStyle("-fx-background-color: white");
        pnBtnPisoUno.setStyle("-fx-background-color: #f9f9f9");
        pnBtnPisoDos.setStyle("-fx-background-color: #f9f9f9");
        pnBtnPisoTres.setStyle("-fx-background-color: #f9f9f9");
        panePisoCuatro.setVisible(true);
        panePisoUno.setVisible(false);
        panePisoDos.setVisible(false);
        panePisoTres.setVisible(false);
    }

    @FXML
    private void clicCancelar(MouseEvent event) {
        paneInformacionCajon.setVisible(false);
        paneCajonNoSeleccionado.setVisible(true);
        paneInformacionCajonAsignado.setVisible(false);
        paneInformacionCobroCajon.setVisible(false);
        tarjetaSeleccionada=null;
    }

    @FXML
    private void clicCajon(MouseEvent event) {
        Rectangle cajon = (Rectangle) event.getSource();
        cajonActual = cajon;
        tarjetaSeleccionada = obtenerInformacionCajon(cajon);
        if(usuarioGeneral.getIdTipoUsuario() == 2) {            
            if(tarjetaSeleccionada.getIdTarjeta() == -1) {
            Utilidades.mostrarDialogoSimple("Tarjeta no registrada", "No se ha asignado una tarjeta al cajon. ", Alert.AlertType.INFORMATION);
            paneCajonNoSeleccionado.setVisible(true);
            } else {
                switch (tarjetaSeleccionada.getNombreEstadoCajon()) {
                    case "Disponible":
                        paneCajonNoSeleccionado.setVisible(false);
                        setInformacionCajon(tarjetaSeleccionada);
                        paneInformacionCajon.setVisible(true);
                        break;
                    case "Asignado":
                        Utilidades.mostrarDialogoSimple("Accion no permitida", 
                                "No puedes asignar un cajon asignado. Intenta con otro.", Alert.AlertType.INFORMATION);
                        paneInformacionCajon.setVisible(false);
                        paneCajonNoSeleccionado.setVisible(true);
                        break;
                    case "No disponible":
                        Utilidades.mostrarDialogoSimple("Accion no permitida", 
                                "Este cajon no esta disponible en este momento. Intenta con otro.", Alert.AlertType.INFORMATION);
                        paneInformacionCajon.setVisible(false);
                        paneCajonNoSeleccionado.setVisible(true);
                    default:
                        System.out.println("error");
                }
            }   
        }else {
            if(tarjetaSeleccionada.getIdTarjeta() == -1) {
            Utilidades.mostrarDialogoSimple("Tarjeta no registrada", "No se ha asignado una tarjeta al cajón. ", Alert.AlertType.INFORMATION);
            paneCajonNoSeleccionado.setVisible(true);
            }else {
                switch(tarjetaSeleccionada.getNombreEstadoCajon()) {
                    case"Disponible":
                        Utilidades.mostrarDialogoSimple("Cajón Sin asignar", "No puedes cobrar un cajón sin asignar ", Alert.AlertType.WARNING);
                        tarjetaSeleccionada=null; 
                        break;
                    case "Ocupado":
                        Utilidades.mostrarDialogoSimple("Cajón Ocupado", "No puedes cobrar un cajón que aun esta ocupado", Alert.AlertType.WARNING);
                        tarjetaSeleccionada=null;
                        break;
                    case"No disponible":
                        Utilidades.mostrarDialogoSimple("Cajón No Disponible", "No puedes cobrar un cajón que no tiene Tarjeta ", Alert.AlertType.WARNING);
                        tarjetaSeleccionada=null;
                        break;
                    case "Asignado": {
                        AlquilerCajonDAO alquilerDAO = new AlquilerCajonDAO();   
                        paneCajonNoSeleccionado.setVisible(false); 
                        try {
                            alquilerCajonRegistro = alquilerDAO.obtenerCajonAlquilado(tarjetaSeleccionada.getNumeroCajon(), tarjetaSeleccionada.getPiso());
                            setInformacionCajonAsignado(tarjetaSeleccionada);
                            paneInformacionCajonAsignado.setVisible(true);
                        } catch (DAOException ex) {                        
                           Utilidades.mostrarDialogoSimple("Error terminal:", "Ocurrio un error al cargar la informacion del cajón ", Alert.AlertType.ERROR);
                        } 
                    }                  
                }
            }
        }            
    }
    
    private void setInformacionCajon(Tarjeta tarjeta) {
        if (tarjeta != null) {
            LocalDate fechaActual = LocalDate.now();
            lbIdentificadorTarjeta.setText(obtenerLetraPiso(tarjeta.getPiso())+""+tarjeta.getNumeroCajon());
            lbPiso.setText(String.valueOf(tarjeta.getPiso()));
            lbTipoVehiculo.setText(tarjeta.getTipoVehiculo());            
            lbFecha.setText(fechaActual.getDayOfMonth() + " de "+meses[fechaActual.getMonthValue()-1]+ " del " +fechaActual.getYear());
            lbTarifaActual.setText(Double.toString(obtenerCuotaActual().getCantidad()));
            setFotoTipoVehiculo(tarjetaSeleccionada);
        }
    }
    
    private void setInformacionCajonAsignado(Tarjeta tarjeta) {
         if (tarjeta != null) {                      
            lbIdentificadorTarjeta1.setText(obtenerLetraPiso(tarjeta.getPiso())+""+tarjeta.getNumeroCajon());
            lbPisoDespachador1.setText(String.valueOf(tarjeta.getPiso()));
            lbTipoVehiculoDespachador1.setText(tarjeta.getTipoVehiculo());            
            lbFechaAsignacionDespachador1.setText(Utilidades.convertirFechaToString(alquilerCajonRegistro.getFechaHoraInicio()).substring(0, 10));
            lbHoraAsignacionDespachador1.setText(Utilidades.convertirFechaToString(alquilerCajonRegistro.getFechaHoraInicio()).substring(11));
            setFotoTipoVehiculo(tarjetaSeleccionada);
        }
    }
    
    private void setInformacionCajonCobro(Tarjeta tarjeta) {
         if (tarjeta != null) {   
            LocalDate fechaActual = LocalDate.now();
            LocalTime horaActual = LocalTime.now();            
            lbIdentificadorTarjeta11.setText(obtenerLetraPiso(tarjeta.getPiso())+""+tarjeta.getNumeroCajon()); 
            String fechaAsignacion = Utilidades.convertirFechaToString(alquilerCajonRegistro.getFechaHoraInicio()).substring(0, 10);
            String horaAsignacion = Utilidades.convertirFechaToString(alquilerCajonRegistro.getFechaHoraInicio()).substring(11); 
            String fechaSalida = fechaActual.getYear()+ "-"+fechaActual.getMonthValue()+"-"+fechaActual.getDayOfMonth();            
            String horaSalida = horaActual.getHour() +":"+horaActual.getMinute()+ ":" +horaActual.getSecond();
            lbFechaAsignacionCobro.setText(fechaAsignacion);
            lbHoraAsignacionCobro.setText(horaAsignacion);            
            lbFechaSalidaCobro.setText(fechaSalida);            
            lbHoraSalidaCobro.setText(horaSalida);          
            setFotoTipoVehiculo(tarjetaSeleccionada);
            validarTiempo(fechaAsignacion, horaAsignacion,fechaSalida, horaSalida);                    
        }
    }
    
    private void setFotoTipoVehiculo(Tarjeta tarjeta) {
        if ("Vehiculo".equals(tarjeta.getTipoVehiculo())) {
                ivTipoVehiculo.setImage(new Image("file:src/javafxsigees/recursos/auto.png"));
                ivTipoVehiculo1.setImage(new Image("file:src/javafxsigees/recursos/auto.png"));
                ivTipoVehiculo11.setImage(new Image("file:src/javafxsigees/recursos/auto.png"));
            } else {
                ivTipoVehiculo.setImage(new Image("file:src/javafxsigees/recursos/motocicleta.png"));
                ivTipoVehiculo1.setImage(new Image("file:src/javafxsigees/recursos/motocicleta.png"));
                ivTipoVehiculo11.setImage(new Image("file:src/javafxsigees/recursos/motocicleta.png"));
            }
    }
    
    private Cuota obtenerCuotaActual() {
        CuotaDAO cuotaDAO = new CuotaDAO();
        Cuota cuota = new Cuota();
        cuota.setIdCuota(-1);
        try {
            cuota = cuotaDAO.obtenerCuotaVigente();
        } catch (DAOException ex) {
            Utilidades.mostrarDialogoSimple("Error", ex.getMessage(), Alert.AlertType.ERROR);
        }
        return cuota;
    }

    private Tarjeta obtenerInformacionCajon(Rectangle cajon) {
        Tarjeta tarjeta = new Tarjeta();
        try {
            String idCajon = cajon.getId();
            int piso = obtenerNumeroPiso(idCajon);
            int numeroCajon =Integer.valueOf(idCajon.substring(6));
            TarjetaDAO tarjetaDAO = new TarjetaDAO();
            tarjeta = tarjetaDAO.obtenerCajon(numeroCajon, piso);
        } catch (DAOException ex) {
            ex.printStackTrace();
        }
        return tarjeta;
    }
    
    private int obtenerNumeroPiso(String idCajon) {
        int piso = -1;
        char letraPiso = idCajon.charAt(5);
        switch (letraPiso) {
            case 'A':
                piso = 1;
                break;
            case 'B':
                piso = 2;
                break;
            case 'C':
                piso = 3;
                break;
            case 'D':
                piso = 4;
                break;
        }
        return piso;
    }
    
    private char obtenerLetraPiso(int numeroPiso) {
        char letraPiso = 'a';
        switch (numeroPiso) {
            case 1:
                letraPiso = 'A';
                break;
            case 2:
                letraPiso = 'B';
                break;
            case 3:
                letraPiso = 'C';
                break;
            case 4:
                letraPiso = 'D';
                break;
            default:
                letraPiso = 'a';
        }
        
        return letraPiso;
    }
    
    private void validarTiempo(String fechaAsignaicion, String horaAsignacion, String fechaSalida, String horaSalida) {   
        try {
            SimpleDateFormat fecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date primeraFecha = fecha.parse(fechaAsignaicion+" "+horaAsignacion);
            Date segundaFecha = fecha.parse(fechaSalida+" "+horaSalida);
            long diferenciaMilisecond = segundaFecha.getTime()-primeraFecha.getTime();
            TimeUnit time = TimeUnit.MINUTES;
            long diferenciaMinutos = time.convert(diferenciaMilisecond, time.MILLISECONDS);
            System.err.println(diferenciaMilisecond+"     "+diferenciaMinutos);
            if(diferenciaMinutos >= 11) {
                calcularMonto(diferenciaMinutos+diferenciaMinutos);
            }else {
                lbHorasTotales.setText(Long.toString(diferenciaMinutos)+" m");
                lbCuotaCobro.setText("$"+alquilerCajonRegistro.getCuota()+" por hora.");
                lbServicoGratis.setText("Tiempo de estadia menor a 10 minutos, Servicio gratuito");
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }
    
    private void calcularMonto(long tiempoEstacionamiento) {
        double totalHoras = tiempoEstacionamiento/60;
        double totalMinutos = tiempoEstacionamiento%60;        
        double cuota = Double.parseDouble(alquilerCajonRegistro.getCuota());
        double monto = Double.parseDouble(alquilerCajonRegistro.getCuota())* totalHoras + cuota;
        alquilerCajonRegistro.setMonto(monto);
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        String moneyTotalPago = formatter.format(monto);
        alquilerCajonRegistro.setMonto(monto);        
        lbCuotaCobro.setText("$"+alquilerCajonRegistro.getCuota()+" por hora.");
        lbHorasTotales.setText(String.valueOf(totalHoras)+" h " + String.valueOf(totalMinutos)+" min." );
        lbTotalalPago.setText(moneyTotalPago);
    }
    
     private void desactivarCajones(boolean desactivarCajones) {
        if(desactivarCajones) {
            panePisoUno.setDisable(true);   
            panePisoDos.setDisable(true);
            panePisoTres.setDisable(true);
            panePisoCuatro.setDisable(true);
            paneBtnRegistarMulta.setDisable(true);
            btnCerrarSesion.setDisable(true);
            btnPerfil.setDisable(true);
            imvVolver.setDisable(false);
        }else {
            panePisoUno.setDisable(false);   
            panePisoDos.setDisable(false);
            panePisoTres.setDisable(false);
            panePisoCuatro.setDisable(false);
            paneBtnRegistarMulta.setDisable(false);
            btnCerrarSesion.setDisable(false);
            btnPerfil.setDisable(false);          
        }
     } 
     
    private void actualizarTarjeta(Tarjeta tarjeta) {
        TarjetaDAO tarjetaDAO = new TarjetaDAO();
        try {
            tarjetaDAO.actualizarTarjeta(tarjeta);
        } catch (DAOException ex) {
            Utilidades.mostrarDialogoSimple("Error", ex.getMessage(), Alert.AlertType.ERROR);
        }
    }    
    
    private String obtenerIdCajon(Tarjeta tarjeta) {
        String piso = String.valueOf(obtenerLetraPiso(tarjeta.getPiso()));
        String numero = String.valueOf(tarjeta.getNumeroCajon());
        return "cajon" + piso + numero;
        
    }
    
    public void cargarCajones() {
        cargarEstadoCajones(panePisoUno.getChildren());
        cargarEstadoCajones(panePisoDos.getChildren());
        cargarEstadoCajones(panePisoTres.getChildren());
        cargarEstadoCajones(panePisoCuatro.getChildren());
    }
    
    private void irVentanaMultas(boolean tarjetaPerdida) {
        try {
            FXMLLoader accesoControlador = new FXMLLoader(JavaFXSIGEES.class.getResource("vistas/FXMLRegistrarMulta.fxml"));
            Parent vista = accesoControlador.load();
            FXMLRegistrarMultaController multas = accesoControlador.getController();            
            multas.inicializarInformacion(tarjetaPerdida, tarjetaSeleccionada, usuarioGeneral.getIdUsuario(), this);  
            Stage escenarioMultas = new Stage();            
            escenarioMultas.setScene(new Scene(vista));
            escenarioMultas.setTitle("Multas");
            escenarioMultas.setResizable(false);
            escenarioMultas.initModality(Modality.APPLICATION_MODAL);
            escenarioMultas.showAndWait();
        } catch (IOException ex) {
            Utilidades.mostrarDialogoSimple("Error", ex.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void clicCerrarSesión(MouseEvent event) {
        Stage escenario = (Stage) paneCajonNoSeleccionado.getScene().getWindow();
        escenario.setScene(Utilidades.inicializarEscena("vistas/FXMLInicioSesion.fxml"));
        escenario.setResizable(false);
        escenario.setTitle("Inicio Sesión");
        escenario.centerOnScreen();
        escenario.show();
    }
    
    private Date obtenerFechaActual() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LocalDate fechaActual = LocalDate.now();
        LocalTime horaActual = LocalTime.now(); 
        Date fechaHoraInicio = null;
        try {
            fechaHoraInicio = formatter.parse(
            fechaActual.getYear()+"-"+fechaActual.getMonthValue()+"-"+fechaActual.getDayOfMonth()+" "
            +(horaActual.getHour())+":"+horaActual.getMinute()+":"+horaActual.getSecond());
        } catch (ParseException ps) {
            
        }
        return fechaHoraInicio;
        
    }

    @FXML
    private void clicAsignarCajon(MouseEvent event) {
        if (tarjetaSeleccionada != null) {
            AlquilerCajon alquilerNuevo = new AlquilerCajon();
            alquilerNuevo.setFechaHoraInicio(obtenerFechaActual());
            alquilerNuevo.setIdTarjeta(tarjetaSeleccionada.getIdTarjeta());
            alquilerNuevo.setIdUsuario(usuarioGeneral.getIdUsuario());
            alquilerNuevo.setIdCuota(obtenerCuotaActual().getIdCuota());
            AlquilerCajonDAO alquilerCajonDAO = new AlquilerCajonDAO();
            try {
                alquilerCajonDAO.guardarAlquilerCajon(alquilerNuevo);
                tarjetaSeleccionada.setIdEstadoCajon(2);
                actualizarTarjeta(tarjetaSeleccionada);
                String idCajon = obtenerIdCajon(tarjetaSeleccionada);
                Rectangle cajon = (Rectangle) lbFecha.getScene().lookup("#"+idCajon);
                cajon.setFill(Color.web("#B4C8EF"));
                paneInformacionCajon.setVisible(false);
                paneCajonNoSeleccionado.setVisible(true);
            } catch (DAOException ex) {
                Utilidades.mostrarDialogoSimple("Error", ex.getMessage(), Alert.AlertType.ERROR);
            }
        }
    }
    
    public void bloquearElemntos(boolean desactivar) {
        if(desactivar) {            
            desactivarCajones(true);
            btnCancelar.setDisable(true);
            btnTrajetaPerdida.setDisable(true);
            lbServicoGratis1.setVisible(true);      
            paneInformacionCobroCajon.setVisible(false); 
            cargarCajones();
        }else {
            desactivarCajones(false);
            btnCancelar.setDisable(false);
            btnTrajetaPerdida.setDisable(false);
            lbServicoGratis1.setVisible(false);
            paneCajonNoSeleccionado.setVisible(true);       
        }
        
    }
   
    @FXML
    private void clicBtnDetener(MouseEvent event) {
        desactivarCajones(true);
        paneCajonNoSeleccionado.setVisible(false);
        Tarjeta tarjeta = obtenerInformacionCajon(cajonActual);
        setInformacionCajonCobro(tarjeta);
        paneInformacionCajonAsignado.setVisible(false);
        paneInformacionCobroCajon.setVisible(true);
    }

    @FXML
    private void clicBtnTarjetaPerdida(MouseEvent event) {
        irVentanaMultas(true);
    }

    @FXML
    private void clicBack(MouseEvent event) {
        desactivarCajones(false);
        paneInformacionCobroCajon.setVisible(false);
        paneInformacionCajonAsignado.setVisible(true);        
        cargarCajones();
    }

    @FXML
    private void clicBtnRegistraPago(MouseEvent event) {
        int respuesta = -1;
        AlquilerCajonDAO alquilerDAO = new AlquilerCajonDAO(); 
        AlquilerCajon alquilerCajonPago =new AlquilerCajon();     
        alquilerCajonPago.setFechaHoraInicio(Utilidades.convertirStringToDate(lbFechaSalidaCobro.getText()+ " "+lbHoraSalidaCobro.getText()));
        alquilerCajonPago.setFechaHoraSalida(Utilidades.convertirStringToDate(lbFechaSalidaCobro.getText()+ " "+lbHoraSalidaCobro.getText()));
        alquilerCajonPago.setIdAlquilerCajon(alquilerCajonRegistro.getIdAlquilerCajon());
        try {
            if(lbTotalalPago.getText().equals("Tiempo de estadia menor a 10 minutos, Servicio gratuito")) {
                alquilerCajonPago.setMonto(0.0);
                respuesta = alquilerDAO.registrarPagoAlquilerCajon(alquilerCajonPago);
            }else {
                alquilerCajonPago.setMonto(alquilerCajonRegistro.getMonto());
                respuesta = alquilerDAO.registrarPagoAlquilerCajon(alquilerCajonPago);
                paneCajonNoSeleccionado.setVisible(true);
             }       
        } catch (DAOException e) {
            e.printStackTrace();
        }    
        if(respuesta != -1) {
            if(tarjetaSeleccionada.getIdEstadoCajon()!=4){
                tarjetaSeleccionada.setIdEstadoCajon(1);
            }            
            actualizarTarjeta(tarjetaSeleccionada);            
            paneInformacionCobroCajon.setVisible(false);
            desactivarCajones(false);
            bloquearElemntos(false);
            cargarCajones();
            tarjetaSeleccionada=null;
        } else {
            Utilidades.mostrarDialogoSimple("Fallo al registrar", "Ocurrio un error al registrar el pago", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void clicBtnRgistrarMulta(MouseEvent event) {
        irVentanaMultas(false);
    }

    @Override
    public void notitficacionOperacionExitosa() {
        bloquearElemntos(true);
    }
    
}
