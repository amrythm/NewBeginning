package org.pharmEasy.controller;

import org.pharmEasy.constants.PrescriptionMappings;
import org.pharmEasy.dao.PrescriptionDao;
import org.pharmEasy.db.model.Prescription;
import org.pharmEasy.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(PrescriptionMappings.PRESCRIPTIONS)
public class PrescriptionController {

        @Autowired
        PrescriptionDao prescriptionDao;

        @GetMapping
        public List<Prescription> findAll() {
            return prescriptionDao.findAll();
        }

        @PostMapping
        public Prescription addNewPrescription(@RequestBody Prescription prescription) {
            return prescriptionDao.save(prescription);
        }

        @PutMapping("{id}")
        public Prescription updateUser(@PathVariable int id, @RequestBody Prescription prescription) {
            Optional<Prescription> prescription1 = prescriptionDao.findById(id);

            if(prescription1.isEmpty()) {
                throw new ResourceNotFoundException();
            }else {
                prescription.setId(id);
                return prescriptionDao.save(prescription);
            }
        }

        @DeleteMapping("{id}")
        public void deleteUser(@PathVariable int id) {
            prescriptionDao.deleteById(id);
        }
}

