package es.piscis.piscisoutoffice.autenticacion;

import android.content.Context;

import org.mockito.Mock;
import org.junit.Rule;

import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import es.piscis.piscisoutoffice.model.Connection.FactoriaDeConexiones;
import es.piscis.piscisoutoffice.model.Room.BBDD.BaseDeDatos;
import es.piscis.piscisoutoffice.presenter.autenticacion.AutenticacionPresenter;
import es.piscis.piscisoutoffice.view.autenticacion.IContratoAutenticacion;

public class AutenticacionPresenterITest {

    // SUT
    private AutenticacionPresenter sut;

    // MOCKS
    @Mock
    IContratoAutenticacion.View mockView;
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    // BASE DE DATOS
    BaseDeDatos db;

    // OBJETOS
    String dni;
    String contrasenha;

    public void setUp() {
        FactoriaDeConexiones.obtenerConexionLocal();
    }

    public void tearDown() throws Exception {
    }



    public void testOnLoginClicked() {
        db = BaseDeDatos.getInstancia();
        sut =new AutenticacionPresenter(mockView, db);
    }

    public void testOnActualizarClicked() {
    }
}