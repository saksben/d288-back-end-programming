package com.assessment.demo;

import com.assessment.demo.dao.CustomerRepository;
import com.assessment.demo.dao.DivisionRepository;
import com.assessment.demo.entities.Customer;
import com.assessment.demo.entities.Division;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {
    private final CustomerRepository customerRepository;
    private final DivisionRepository divisionRepository;

    public Bootstrap(CustomerRepository customerRepository, DivisionRepository divisionRepository) {
        this.customerRepository = customerRepository;
        this.divisionRepository = divisionRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Division tonyDiv = divisionRepository.findById(Long.valueOf(4)).orElse(null);
        Division peterDiv = divisionRepository.findById(Long.valueOf(38)).orElse(null);
        Division sherlockDiv = divisionRepository.findById(Long.valueOf(101)).orElse(null);
        Division frasierDiv = divisionRepository.findById(Long.valueOf(46)).orElse(null);
        Division poirotDiv = divisionRepository.findById(Long.valueOf(101)).orElse(null);

        Customer tony = new Customer(Long.valueOf(2), "Tony", "Stark", "10880 Malibu Point", "90265", "(123)456-7890", null, null, tonyDiv);
        Customer peter = new Customer(Long.valueOf(3), "Peter", "Griffin", "31 Spooner St", "02907", "(123)456-7890", null, null, peterDiv);
        Customer sherlock = new Customer(Long.valueOf(4), "Sherlock", "Holmes", "221B Baker St", "NW1 6XE", "(123)456-7890", null, null, sherlockDiv);
        Customer frasier = new Customer(Long.valueOf(5), "Frasier", "Crane", "Apartment 1901, Elliott Bay Towers", "98101", "(123)456-7890", null, null, frasierDiv);
        Customer poirot = new Customer(Long.valueOf(6), "Hercule", "Poirot", "Apt. 56B, Whitehaven Mansions", "EC2Y 5HN", "(123)456-7890", null, null, poirotDiv);

        customerRepository.save(tony);
        customerRepository.save(peter);
        customerRepository.save(sherlock);
        customerRepository.save(frasier);
        customerRepository.save(poirot);

    }
}
