package com.example.totalsat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TotalsatController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/dadosSat")
    public Map<String, Object> getDadosSat() {
        Map<String, Object> dados = new HashMap<>();

        try {
            String sql1 = "select count(*) as total_ativos from zeusretail.tab_sat where flg_ativo = 1";
            List<Map<String, Object>> rows1 = jdbcTemplate.queryForList(sql1);
            dados.put("total_ativos", rows1.get(0).get("total_ativos"));

            String sql2 = "select count(*) as vencendo_proximos_meses from zeusretail.tab_sat where cert_vencimento >= sysdate  and cert_vencimento <= add_months(sysdate, 3)";
            List<Map<String, Object>> rows2 = jdbcTemplate.queryForList(sql2);
            dados.put("vencendo_proximos_meses", rows2.get(0).get("vencendo_proximos_meses"));

            String sql3 = "select count(*) as certificados_vencidos from zeusretail.tab_sat where cert_vencimento <= sysdate";
            List<Map<String, Object>> rows3 = jdbcTemplate.queryForList(sql3);
            dados.put("certificados_vencidos", rows3.get(0).get("certificados_vencidos"));

            String sql4 = "select count(*) as pendente_transmissão from zeusretail.tab_sat where mt_usada <> '0 Mbytes'";
            List<Map<String, Object>> rows4 = jdbcTemplate.queryForList(sql4);
            dados.put("pendente_transmissão", rows4.get(0).get("pendente_transmissão"));

        } catch (Exception e) {
            System.err.println("Erro ao executar as consultas: " + e.getMessage());
            dados.put("error", "Erro ao carregar dados");
        }

        return dados;
    }
}