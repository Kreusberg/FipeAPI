package br.com.alura.FipeAPI.principal;

import br.com.alura.FipeAPI.model.DadosVeiculo;
import br.com.alura.FipeAPI.service.ConsumeApi;
import br.com.alura.FipeAPI.service.DataConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

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

//        DadosVeiculo data =  converter.getData(json, DadosVeiculo.class);

//      Get inicial data based on the vehicle type
        List<DadosVeiculo> data = converter.getData(json, new TypeReference<List<DadosVeiculo>>(){});

        data.forEach(d -> {
            System.out.println("Cód: " + d.codigo() + " | Descrição: " + d.nome());
        });

    }
}
