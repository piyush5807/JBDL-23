package com.example.jbdl.majorproject2;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Integer> {

    // select * from Wallet w where userEmail = :email
    Wallet findByUserEmail(String email);
}
