package com.tookscan.tookscan.message.repository.mysql;

import com.tookscan.tookscan.message.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}
