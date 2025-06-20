package br.com.alura.FipeAPI.service;

import br.com.alura.FipeAPI.model.DadosVeiculo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public interface IDataConverter {

    <T> T getData(String json, TypeReference<List<DadosVeiculo>> typeReference) throws JsonProcessingException;

}
