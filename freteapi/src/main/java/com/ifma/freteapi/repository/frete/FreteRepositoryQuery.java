package com.ifma.freteapi.repository.frete;

import com.ifma.freteapi.model.Frete;
import com.ifma.freteapi.repository.filter.FreteFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FreteRepositoryQuery {
    Page<Frete> filtra(FreteFiltro freteFiltro /*, Pageable pageable */);
}
