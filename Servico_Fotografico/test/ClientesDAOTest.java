
import dao.ClientesDAO;
import model.Clientes;
import org.junit.Ignore;
import org.junit.Test;

public class ClientesDAOTest {

    Clientes c;
    ClientesDAO cdao;

    @Test
    //@Ignore
    public void salvar() {
        c = new Clientes();
        cdao = new ClientesDAO();

        c.setNome("Elivelton");
        c.setCpf("091.004.129-61");
        c.setTelefone("(43)3559-1246");
        c.setCelular("(43)99645-4525");
        c.setEndereco("Rua dos Limoeiros, n° 62 - Centro, Joaquim Távora - PR");

        cdao.salvar(c);
    }

//    @Ignore
//    @Test
//    public void listar() {
//        //c = new Clientes();
//        cdao = new ClientesDAO();
//
//        for (Clientes c : cdao.listAll()) {
//            System.out.println(c);
//        }
//    }

    @Ignore
    @Test
    public void bucarPorCodigo() {
        c = new Clientes();
        cdao = new ClientesDAO();

        c.setCodigo(2);
        cdao.buscarPorCodigo(c);

        if (c.getCodigo() != null) {
            System.out.println(c.getCodigo());
        }
    }

    @Test
    @Ignore
    public void editar() {
        c = new Clientes();
        cdao = new ClientesDAO();

        c.setCodigo(1);
        c.setNome("teste 1");
        c.setCelular("546");
        cdao.editar(c);

    }

    @Ignore
    @Test
    public void excluir() {
        c = new Clientes();
        cdao = new ClientesDAO();

        c.setCodigo(1);
        cdao.excluir(c);
    }
    
    @Ignore
    @Test
    public void pesquisarPorNome(){
        c = new Clientes();
        cdao = new ClientesDAO();
        
        
       
        for(Clientes cli : cdao.pesquisarPorNome("AA")){
            System.out.println(cli);
        }
        
    }
}
