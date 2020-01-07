
import dao.ServicosDAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.Clientes;
import model.Servicos;
import org.junit.Ignore;
import org.junit.Test;

public class ServicosDAOTest {

    Clientes c;

    Servicos s;
    ServicosDAO sdao;

    @Test
    @Ignore
    public void salvar() {
        c = new Clientes();
        s = new Servicos();
        sdao = new ServicosDAO();

        c.setCodigo(1);

        s.setClientes(c);
        s.setData(new Date());
        s.setDescricao("teste");
        s.setValor(10);
        s.setNumero(400);

        sdao.salvar(s);
    }

    @Test
    @Ignore
    public void listar() {
        sdao = new ServicosDAO();

        for (Servicos ser : sdao.listAll("")) {
            System.out.println(ser.getDescricao());
        }
    }

    @Ignore
    @Test
    public void buscarPorCodigo() {
        s = new Servicos();
        sdao = new ServicosDAO();

        s.setCodigo(1);
        sdao.bucarPorCodigo(s);

        System.out.println(s);

    }

    @Ignore
    @Test
    public void excluir() {
        s = new Servicos();
        sdao = new ServicosDAO();

        s.setCodigo(1);

        sdao.excluir(s);
    }

    @Ignore
    @Test
    public void editar() {
        c = new Clientes();

        c.setCodigo(1);

        s = new Servicos();
        sdao = new ServicosDAO();

        s.setCodigo(4);
        s.setDescricao("What is Lorem Ipsum?\n"
                + "Lorem Ipsum is simply dummy text of the printing and typesetting industry. "
                + "Lorem Ipsum has been the industry's standard dummy text ever "
                + "since the 1500s, when an unknown printer took a galley of type "
                + "and scrambled it to make a type specimen book. It has survived not "
                + "only five centuries, but also the leap into electronic typesetting, "
                + "remaining essentially unchanged. It was popularised in the 1960s with the "
                + "release of Letraset sheets containing Lorem Ipsum passages, and more recently"
                + " with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
        s.setData(new Date());
        s.setNumero(500);

        s.setClientes(c);

        sdao.editar(s);
    }

    @Ignore
    @Test
    public void consultaData() throws ParseException {
        s = new Servicos();
        sdao = new ServicosDAO();

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date data_inicial = formato.parse("02/10/2018");
        Date data_final = formato.parse("02/10/2018");

        //novo.setLancamento(data);

        for (Servicos ser : sdao.consultaEntreDatas(data_inicial, data_final)) {
            System.out.println(ser);
        }
    }
    
    @Ignore
    @Test
    public void buscarNumero(){
        sdao = new ServicosDAO();
        
        for (Servicos ser : sdao.buscarNumero(0)) {
            System.out.println(ser.getClientes().getNome() + " - " + ser.getNumero());
        }
    }
}
