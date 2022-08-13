package org.example.internetshopspring.repository;

import org.example.internetshopspring.entity.Order;
import org.example.internetshopspring.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "select o from Order o where o.user.id = :userId and o.status <> :notStatus")
    Page<Order> findConfirmedOrders(Long userId, Status notStatus, Pageable pageable);
}
