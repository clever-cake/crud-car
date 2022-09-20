package com.kuche.crudcar.persistance;

import com.kuche.crudcar.persistance.model.PersistedCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<PersistedCar, Long> {

}
