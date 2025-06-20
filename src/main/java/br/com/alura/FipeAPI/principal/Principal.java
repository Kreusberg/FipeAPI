package br.com.alura.FipeAPI.principal;

import br.com.alura.FipeAPI.model.DadosVeiculo;
import br.com.alura.FipeAPI.service.ConsumeApi;
import br.com.alura.FipeAPI.service.DataConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;
import java.util.Scanner;

public class Principal {

    private Scanner scanner = new Scanner(System.in);
    private ConsumeApi consumer = new ConsumeApi();
    private DataConverter converter = new DataConverter();
    private final String URL = "https://parallelum.com.br/fipe/api/v1/";

    public void exibeMenu() throws JsonProcessingException {

        System.out.println("""
                Primeiramente, selecione o tipo de veículo que deseja procurar:
                Carros
                Motos
                Caminhões
                """);

        String resposta = scanner.nextLine().toLowerCase();

        var json = consumer.getData(URL + resposta + "/marcas");

//      Get inicial data based on the vehicle type
        List<DadosVeiculo> data = converter.getData(json, new TypeReference<List<DadosVeiculo>>(){});

        data.forEach(d -> {
            System.out.println("Cód: " + d.codigo() + " | Descrição: " + d.nome());
        });

        System.out.println("Informe o código da marca a ser consultada");
        var codeBrandResponde = scanner.nextLine().toLowerCase();

        var brandJson = consumer.getData(URL + resposta + "/marcas/" + codeBrandResponde + "/modelos");

        JsonNode root = converter.getDataTree(brandJson);
        JsonNode modelosNode = root.get("modelos");

        List<DadosVeiculo> modelos = converter.getData(modelosNode.toString(), new TypeReference<List<DadosVeiculo>>() {});

        modelos.forEach(m -> {
            System.out.println("Cód: " + m.codigo() + " | Descrição: " + m.nome());
        });

    }
}
