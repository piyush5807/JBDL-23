package com.example.jbdl.minorproject1.repository;

import com.example.jbdl.minorproject1.models.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface RequestRepository extends JpaRepository<Request, Integer> {
}
