package org.pharmEasy.dao;

import org.pharmEasy.db.model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionDao extends JpaRepository<Prescription, Integer> {
}
