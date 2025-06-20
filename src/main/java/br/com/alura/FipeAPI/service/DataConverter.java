package br.com.alura.FipeAPI.service;

import br.com.alura.FipeAPI.model.DadosVeiculo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class DataConverter implements IDataConverter {

    private ObjectMapper mapper = new ObjectMapper();

//    @Override
//    public <T> T getData(String json, Class<T> classe) {
//
//        try {
//            return mapper.readValue(json, classe);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//
//    }


    @Override
    public <T> T getData(String json, TypeReference<List<DadosVeiculo>> classe) throws JsonProcessingException {

        try {
            return (T) mapper.readValue(json, classe);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    public JsonNode getDataTree(String json) {

        try {
            return mapper.readTree(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
