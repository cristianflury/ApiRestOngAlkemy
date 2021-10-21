package org.alkemy.somosmas.mapper;

import lombok.RequiredArgsConstructor;
import org.alkemy.somosmas.dto.SlideDto;
import org.alkemy.somosmas.dto.SlideRequestDto;
import org.alkemy.somosmas.dto.SlideResponseDto;
import org.alkemy.somosmas.model.Slide;
import org.alkemy.somosmas.repository.OrganizationRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SlideMapper {

	private  final OrganizationRepository organizationRepository;

	public SlideDto slideToDto (Slide slide) {
		SlideDto slideDto = new SlideDto();
		slideDto.setId(slide.getId());
		slideDto.setText(slide.getText());
		slideDto.setImageBase64(slide.getImageUrl());
		slideDto.setOrder(slide.getOrder());
		slideDto.setOrganizationId(slide.getOrganization().getId());

		return slideDto;
	}

	public Slide dtoToSlide(SlideDto slideDto) {
		Slide slide = new Slide();
		slide.setId(slideDto.getId());
		slide.setText(slideDto.getText());
		slide.setImageUrl(slideDto.getImageBase64());
		slide.setOrder(slideDto.getOrder());
		slide.setOrganization(organizationRepository.getById(slideDto.getOrganizationId()));

		return slide;
	}

	public List<SlideDto> slideSetToDtoList(Set<Slide> slides) {

		return slides.stream().map(this::slideToDto).collect(Collectors.toList());

	}
	public SlideResponseDto toDto (Slide slide) {
		SlideResponseDto dto = new SlideResponseDto();
		dto.setId(slide.getId());
		dto.setImageUrl(slide.getImageUrl());
		dto.setOrder(slide.getOrder());
		dto.setText(slide.getText());
		return dto;
	}
	public Slide toEntity(SlideRequestDto dto){
		Slide slide = new Slide();
		slide.setText(dto.getText());
		slide.setImageUrl(dto.getImageBase64());
		slide.setOrder(dto.getOrder());
		return slide;
	}
}
