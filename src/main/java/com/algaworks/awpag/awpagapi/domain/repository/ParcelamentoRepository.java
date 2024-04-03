package com.algaworks.awpag.awpagapi.domain.repository;

import com.algaworks.awpag.awpagapi.domain.model.Parcelamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParcelamentoRepository extends JpaRepository<Parcelamento,Long> {
}
