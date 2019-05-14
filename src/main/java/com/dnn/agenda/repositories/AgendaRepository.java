/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dnn.agenda.repositories;

import com.dnn.agenda.entidades.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author deivid
 */
public interface AgendaRepository extends JpaRepository<Agenda,Long>{
    
}
