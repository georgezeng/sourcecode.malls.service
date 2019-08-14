package com.sourcecode.malls.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DtoTransferFacade {
	@Transactional(readOnly = true)
	public <E, T> List<T> pageToList(Function<Void, Page<E>> pageFunction, Function<E, T> asDTOFunction) {
		return pageFunction.apply(null).get().map(asDTOFunction).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public <E, T> T entityToDTO(Function<Void, Optional<E>> optionalFunction, Predicate<Optional<E>> predicate,
			Function<E, T> asDTOFunction) {
		Optional<E> op = optionalFunction.apply(null);
		if (predicate.test(op)) {
			return asDTOFunction.apply(op.get());
		}
		return null;
	}
}
