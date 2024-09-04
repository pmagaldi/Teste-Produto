package com.example.demo.Test;

import org.slf4j.LoggerFactory;
import java.util.logging.Logger;

public class TransacaoValidator {

    private static final Logger LOGGER = LoggerFactory.getLogger(CapturaTransacaoService.class);

    //Retirado pois não é usado
    //private static final String BIT_02 = "02";

    //Alterar nome para especificar o que é essa lista e não utilizado
    //private static final List<String> listaDeValidacaoBit = List.of("02", "03", "04", "05", "12");

    public void validateTrasacaoIsoModel(ISOModel m) {
        LOGGER.info("Início da validacao");

        boolean isNotPreenchido = m.getBit02() == null;
        boolean validateAux = m.getBit02() != null && m.getBit02().getValue().isEmpty();
        boolean auxValidacao = m.getBit02() != null && m.getBit02().getValue().isEmpty() && m.getBit03() == null;
        String valor = isNotPreenchido ? "01" : "02";

        if(isNotValid(isNotPreenchido, validateAux, auxValidacao, valor)) {
            throw new IllegalArgumentException("Valores não preenchidos");
        }

        try{
            //duplicata de verificacao
//            if(!isNotValid(isNotPreenchido, validateAux, auxValidacao, valor)) {
//
//            }
            // separado para uma funcao de verificacao
            // retirado o contains pois sempre vai ser false
//            if(m.getBit03() != null) {
//                if(m.getBit04() != null && listaDeValidacaoBit.contains("10")) {
//                    if(m.getBit05() != null) {
//                        if(m.getBit12() != null) {
//                            salvar(m, auxValidacao);
//                        }
//                    }
//                }
//            }
            if(isNull(m)){
                throw new IllegalArgumentException("Argument is null");
            }
            salvarTransacao(m, auxValidacao);
        } catch (Exception e) {
            throw new IllegalArgumentException("Falha no salvamento da transacao");
        }
    }

    private boolean isNull(ISOModel m){
        return m.getBit03() == null
                || m.getBit04() == null
                || m.getBit05() == null
                || m.getBit12() == null;
    }

    private boolean isNotValid(boolean validaPreenchido, boolean validaVazio, boolean validaAux, String str) {
        return validaPreenchido
                || validaVazio
                && !validaAux
                && str.equals("01");
    }

    private void salvarTransacao(ISOModel m, boolean auxValidacao) {
        if(auxValidacao) {
            throw new IllegalArgumentException("Validacao do valor aux falhou");
        }
        LOGGER.info("Salvando transacao " + m.getBit02().getValue());
    }
}
