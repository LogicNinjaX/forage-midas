package com.jpmc.midascore.repository;

import com.jpmc.midascore.entity.TransactionRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionRepository extends JpaRepository<TransactionRecord, UUID> {
}
