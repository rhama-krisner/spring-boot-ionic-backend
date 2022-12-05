package com.example.cursomc;

import com.example.cursomc.domain.*;
import com.example.cursomc.domain.Enums.EstadoPagamento;
import com.example.cursomc.domain.Enums.TipoCliente;
import com.example.cursomc.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public static void main(String[] args) {
        SpringApplication.run(CursomcApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Categoria cat01 = new Categoria(null, "Informática");
        Categoria cat02 = new Categoria(null, "Escritório");
        Categoria cat03 = new Categoria(null, "Cama, mesa e banho");
        Categoria cat04 = new Categoria(null, "Eletrônicos");
        Categoria cat05 = new Categoria(null, "Jardinagem");
        Categoria cat06 = new Categoria(null, "Decoração");
        Categoria cat07 = new Categoria(null, "Perfumaria");

        Produto p1 = new Produto(null, "Computador", 2000.00);
        Produto p2 = new Produto(null, "Impressora", 800.00);
        Produto p3 = new Produto(null, "Mouse", 80.00);

        Estado est01 = new Estado(null, "Minas Gerais");
        Estado est02 = new Estado(null, "São Paulo");

        Cidade c01 = new Cidade(null, "Uberlândia", est01);
        Cidade c02 = new Cidade(null, "São Paulo", est02);
        Cidade c03 = new Cidade(null, "Campinas", est02);

        Cliente cli01 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOA_FISICA);
        cli01.getTelefones().addAll(Arrays.asList("2736323", "92987745"));

        Endereco e01 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli01, c01);
        Endereco e02 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli01, c02);

        cli01.getEnderecos().addAll(Arrays.asList(e01, e02));


        cat01.getProdutos().addAll(Arrays.asList(p1, p2, p3));
        cat02.getProdutos().addAll(Arrays.asList(p2));

        p1.getCategorias().addAll(Arrays.asList(cat01));
        p2.getCategorias().addAll(Arrays.asList(cat01, cat02));
        p3.getCategorias().addAll(Arrays.asList(cat01));

        est01.getCidades().addAll(Arrays.asList(c01));
        est02.getCidades().addAll(Arrays.asList(c02, c03));

        categoriaRepository.saveAll(Arrays.asList(cat01, cat02, cat03, cat04, cat05, cat06, cat07));
        produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

        estadoRepository.saveAll(Arrays.asList(est01, est02));
        cidadeRepository.saveAll(Arrays.asList(c01, c02, c03));

        clienteRepository.saveAll(Arrays.asList(cli01));
        enderecoRepository.saveAll(Arrays.asList(e01, e02));

        //SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        DateTimeFormatter formatado = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        LocalDateTime localDateTime01 = LocalDateTime.parse("30/10/2022 10:32", formatado);
        LocalDateTime localDateTime02 = LocalDateTime.parse("10/10/2022 19:53", formatado);

        Pedido ped01 = new Pedido(null, localDateTime01, cli01, e01);
        Pedido ped02 = new Pedido(null, localDateTime02, cli01, e02);

        Pagamento pagto01 = new PagamentoComCartao(null, EstadoPagamento.PAGO, ped01, 6);
        ped01.setPagamento(pagto01);

        Pagamento pagto02 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped02, LocalDateTime.parse("20/10/2022 00:00", formatado), null);
        ped02.setPagamento(pagto02);

        cli01.getPedidos().addAll(Arrays.asList(ped01, ped02));

        pedidoRepository.saveAll(Arrays.asList(ped01, ped02));
        pagamentoRepository.saveAll(Arrays.asList(pagto01, pagto02));

        ItemPedido ip1 = new ItemPedido(ped01, p1, 0.00, 1, 2000.00);
        ItemPedido ip2 = new ItemPedido(ped01, p3, 0.00, 2, 80.00);
        ItemPedido ip3 = new ItemPedido(ped02, p2, 100.00, 1, 800.00);

        ped01.getItens().addAll(Arrays.asList(ip1, ip2));
        ped02.getItens().addAll(Arrays.asList(ip3));

        p1.getItens().addAll(Arrays.asList(ip1));
        p2.getItens().addAll(Arrays.asList(ip3));
        p3.getItens().addAll(Arrays.asList(ip2));

        itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));

    }
}
