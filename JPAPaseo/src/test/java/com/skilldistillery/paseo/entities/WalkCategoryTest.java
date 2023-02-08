package com.skilldistillery.paseo.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WalkCategoryTest {

	
	private static EntityManagerFactory emf;
	private EntityManager em;
	
	private WalkCategory walkCat;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("JPAPaseo");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em =emf.createEntityManager();
		walkCat = em.find(WalkCategory.class, 1);
		
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		walkCat = null;
	}

	@Test
	void test() {
		
//		+----+--------------+-------------+
//		| id | name         | description |
//		+----+--------------+-------------+
//		|  1 | city walking | NULL        |
//		|  2 | park         | NULL        |
//		|  3 | nature       | NULL        |
//		|  4 | mall walking | NULL        |
//		|  5 | tracks       | NULL        |
//		|  6 | trails       | NULL        |
//		+----+--------------+-------------+
		assertNotNull(walkCat);
		assertEquals("city walking", walkCat.getName());
		
}
}