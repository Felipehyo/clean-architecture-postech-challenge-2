
import com.postech.domain.entities.CPF;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CPFTest {

    @Test
    void aoUtilizarUmCPFRealOhMesmoDeveSerIdentificadoComoValido() {
        String numeroCpf = "41112017097";

        CPF cpf = new CPF(numeroCpf);

        assertThat(cpf.validar()).isTrue();
    }

    @Test
    void aoUtilizarUmCPFFalsoOhMesmoDeveSerIdentificadoComoInvalido() {
        String numeroCpf = "41112017091";

        CPF cpf = new CPF(numeroCpf);

        assertThat(cpf.validar()).isFalse();
    }

}
