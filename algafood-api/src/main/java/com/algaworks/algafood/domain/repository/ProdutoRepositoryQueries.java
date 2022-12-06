package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.modelo.FotoProduto;


public interface ProdutoRepositoryQueries {	
	FotoProduto save(FotoProduto foto);
	void delete(FotoProduto foto);

}
