package net.javaguides.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.model.Postulante;
import net.javaguides.springboot.repository.PostulanteRepository;

@Service
public class PostulanteServiceImpl implements PostulanteService {

	@Autowired
	private PostulanteRepository postulanteRepository;

	@Override
	public List<Postulante> getAllPostulantes() {
		return postulanteRepository.findAll();
	}

	@Override
	public void savePostulante(Postulante postulante) {
		this.postulanteRepository.save(postulante);
	}

	@Override
	public Postulante getPostulanteById(long id) {
		Optional<Postulante> optional = postulanteRepository.findById(id);
		Postulante postulante = null;
		if (optional.isPresent()) {
			postulante = optional.get();
		} else {
			throw new RuntimeException(" Employee not found for id :: " + id);
		}
		return postulante;
	}

	@Override
	public void deletePostulanteById(long id) {
		this.postulanteRepository.deleteById(id);
	}

	@Override
	public Page<Postulante> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.postulanteRepository.findAll(pageable);
	}
}
