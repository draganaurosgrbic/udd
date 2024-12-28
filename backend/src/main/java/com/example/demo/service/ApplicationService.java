package com.example.demo.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ApplicationSearchResult;
import com.example.demo.dto.ApplicationUpload;
import com.example.demo.mapper.ApplicationMapper;
import com.example.demo.model.Application;
import com.example.demo.model.ApplicationIndexUnit;
import com.example.demo.repository.ApplicationIndexRepository;
import com.example.demo.repository.ApplicationRepository;
import com.example.demo.utils.CustomLogger;
import com.example.demo.utils.PDFHandler;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ApplicationService {

	private final ApplicationRepository repo;
	private final ApplicationIndexRepository indexRepo;
	private final ApplicationMapper mapper;
	private final FileService fileService;
	private final CustomLogger logger;
	private final UserService userService;
	private final ElasticsearchRestTemplate template;

	public List<Application> read() {
		return repo.findAll();
	}

	public Application upload(ApplicationUpload upload) throws IOException {
		logger.storeApplicationSubmitLog();

		Application model = mapper.map(upload);
		ApplicationIndexUnit indexUnit = mapper.mapToIndexUnit(upload);

		String cvLocation = fileService.store(upload.getCvFile());
		String letterLocation = fileService.store(upload.getLetterFile());

		model.setCvLocation(cvLocation);
		model.setLetterLocation(letterLocation);

		indexUnit.setCvLocation(cvLocation);
		indexUnit.setLetterLocation(letterLocation);
		indexUnit.setCvText(PDFHandler.parse(cvLocation));
		indexUnit.setLetterText(PDFHandler.parse(letterLocation));

		indexRepo.save(indexUnit);
		return repo.save(model);
	}

	public List<ApplicationSearchResult> search(Query query) {
		return template.search(query, ApplicationIndexUnit.class).stream()
				.map(res -> new ApplicationSearchResult(res.getContent(), res.getHighlightFields()))
				.collect(Collectors.toList());
	}

	public void announceFormAccess() {
		logger.storeApplicationFormAccessLog(userService.getLoggedInUser().getCity(),
				userService.getLoggedInUser().getLat(), userService.getLoggedInUser().getLng());
	}

}
