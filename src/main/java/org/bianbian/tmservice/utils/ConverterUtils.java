package org.bianbian.tmservice.utils;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConverterUtils {
	
	private static ModelMapper modelMapper;
	
	@Autowired
	public void setModelMapper(ModelMapper modelMapper) {
		ConverterUtils.modelMapper = modelMapper;
	}
	
	public static <S, T> T map(S source, Class<T> targetClass){
		return modelMapper.map(source, targetClass);
	}
	
	public static <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
	    return source
		    .stream()
		    .map(element -> modelMapper.map(element, targetClass))
		    .collect(Collectors.toList());
	}
}
