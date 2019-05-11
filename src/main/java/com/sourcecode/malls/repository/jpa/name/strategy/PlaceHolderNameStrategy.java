package com.sourcecode.malls.repository.jpa.name.strategy;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class PlaceHolderNameStrategy extends SpringPhysicalNamingStrategy {
	@Autowired
	private Environment env;

	@Override
	public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment jdbcEnvironment) {
		String tableName = env.resolvePlaceholders(name.getText());
		return super.toPhysicalTableName(Identifier.toIdentifier(tableName), jdbcEnvironment);
	}
}
