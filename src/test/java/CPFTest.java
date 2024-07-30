
import com.postech.domain.entities.CPF;
import com.postech.domain.exceptions.DominioException;
import org.junit.Test;


public class CPFTest {

    @Test
    public void aoUtilizarUmCPFRealOhMesmoDeveSerIdentificadoComoValido() {
        String numeroCpf = "41112017097";

        new CPF(numeroCpf);
    }

    @Test(expected = DominioException.class)
    public void aoUtilizarUmCPFFalsoOhMesmoDeveSerIdentificadoComoInvalido() {
        String numeroCpf = "41112017091";

        new CPF(numeroCpf);
    }

}
