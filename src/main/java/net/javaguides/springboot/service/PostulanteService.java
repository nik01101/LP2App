package net.javaguides.springboot.service;

import java.util.List;

import org.springframework.data.domain.Page;

import net.javaguides.springboot.model.Postulante;

public interface PostulanteService {
	List<Postulante> getAllPostulantes();
	void savePostulante(Postulante postulante);
	Postulante getPostulanteById(long id);
	void deletePostulanteById(long id);
	Page<Postulante> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
