package Views;
import Models.CajeroModel;
public class CajeroAutomatico {
    public static void main(String[] args) {
        CajeroModel model = new CajeroModel();
        CajeroView view = new CajeroView();
        Controllers.CajeroController controlador = new Controllers.CajeroController(model, view);
        controlador.m_inciarSistema();
    }
}
