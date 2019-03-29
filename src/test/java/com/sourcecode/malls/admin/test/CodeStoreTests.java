package com.sourcecode.malls.admin.test;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import com.sourcecode.malls.admin.config.MsTestConfig;
import com.sourcecode.malls.admin.domain.redis.CodeStore;
import com.sourcecode.malls.admin.repository.redis.impl.CodeStoreRepository;

@RunWith(SpringRunner.class)
@MsTestConfig
public class CodeStoreTests {
	@Autowired
	private CodeStoreRepository repository;

	@Test
	public void test1() {
		CodeStore data = new CodeStore();
		data.setCategory("test");
		data.setKey("001");
		data.setValue("haha");
		repository.save(data);
		Assert.assertNotNull(data.getId());
		Optional<CodeStore> op = repository.findByCategoryAndKey("test", "001");
		Assert.assertTrue(op.isPresent());
		Assert.assertTrue(op.get().getValue().equals("haha"));
	}
	
	@Test
	public void test2() throws Exception {
		CodeStore data = new CodeStore();
		data.setCategory("test");
		data.setKey("003");
		data.setValue("haha");
		repository.save(data);
		Assert.assertNotNull(data.getId());
		Optional<CodeStore> op = repository.findByCategoryAndKey("test", "003");
		Assert.assertTrue(op.isPresent());
		Thread.sleep(10000l);
		op = repository.findByCategoryAndKey("test", "003");
		Assert.assertTrue(!op.isPresent());
	}
}
