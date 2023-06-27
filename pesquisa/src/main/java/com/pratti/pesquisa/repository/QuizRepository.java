/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pratti.pesquisa.repository;

import com.pratti.pesquisa.model.QuizModel;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Roger
 */
public interface QuizRepository extends JpaRepository<QuizModel, UUID> {

    boolean existsByDescricao(String descricao);
    
    @Query(value = "SELECT a.resposta, COUNT(*) AS quantidade, q.id, q.nome_campo FROM answers AS a JOIN questions AS q ON q.id = a.id_question JOIN quizzes as quiz on quiz.id = a.id_quiz WHERE q.tipo = 'alternativa' and quiz.id = :X GROUP BY a.resposta, q.id, q.nome_campo;", nativeQuery = true)
    List<Object> buscarRespostas(@Param("X")UUID id);
    
    @Query(value = "SELECT JSON_ARRAYAGG(\n" +
                    "    JSON_OBJECT(\n" +
                    "        'resposta', resposta,\n" +
                    "        'tipo', tipo,\n" +
                    "        'id', id,\n" +
                    "        'nome_campo', nome_campo\n" +
                    "    )\n" +
                    ") AS resultado\n" +
                    "FROM (\n" +
                    "    SELECT a.resposta, q.id, q.nome_campo, q.tipo\n" +
                    "         FROM answers AS a\n" +
                    "                        JOIN questions AS q ON q.id = a.id_question\n" +
                    "                        JOIN quizzes AS quiz ON quiz.id = a.id_quiz\n" +
                    "                        where quiz.id =  :X\n" +
                    "                        GROUP BY a.resposta, q.id, q.nome_campo, q.tipo\n" +
                    ") AS subquery;", nativeQuery = true)  
    List<Object> buscarRepostasQuestionario(@Param("X")UUID id);
}
