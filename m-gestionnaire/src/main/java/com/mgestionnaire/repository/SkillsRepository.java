/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgestionnaire.repository;

import com.mgestionnaire.models.Skills;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author hp i3
 */
public interface SkillsRepository extends JpaRepository<Skills, Integer> {
    
}
