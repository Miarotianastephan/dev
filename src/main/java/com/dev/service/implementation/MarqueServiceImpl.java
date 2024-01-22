package com.dev.service.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dev.model.marque.Marque;
import com.dev.repository.MarqueRepository;
import com.dev.service.MarqueService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MarqueServiceImpl implements MarqueService {
    private final MarqueRepository marqueRepository;

    @Override
    public List<Marque> findAllMarque() {
        return marqueRepository.findAll();
    }

    @Override
    public Marque save(Marque marque) {
        return marqueRepository.save(marque);
    }

    @Override
	public Marque update(int idMarque, String nomMarque) {
        Marque marque=marqueRepository.findById(idMarque).get();
        marque.setNomMarque(nomMarque);
        return marqueRepository.save(marque);
	}

	@Override
	public void delete(int idMarque) {
		marqueRepository.deleteById(idMarque);
	}
}
