package com.example.jbdl.minorproject1.repository;

import com.example.jbdl.minorproject1.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
}
