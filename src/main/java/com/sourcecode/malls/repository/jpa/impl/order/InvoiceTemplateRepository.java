package com.sourcecode.malls.repository.jpa.impl.order;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sourcecode.malls.domain.client.Client;
import com.sourcecode.malls.domain.client.InvoiceTemplate;

public interface InvoiceTemplateRepository
		extends JpaRepository<InvoiceTemplate, Long>, JpaSpecificationExecutor<InvoiceTemplate> {
	List<InvoiceTemplate> findAllByClient(Client client, Pageable pageable);
}
