package com.prueba.modelo.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.prueba.modelo.VO.Proyecto;

@Repository
public interface ProyectosRepository extends JpaRepository<Proyecto, Long> {
	
	public Proyecto findByid(long id);
	
	@Query(value = "SELECT p FROM Proyecto p")
    Page<Proyecto> findAllWithPagination(Pageable pageable);

	@Query("SELECT p FROM Proyecto p ORDER BY p.id DESC")
    Proyecto findLastInserted();
}
