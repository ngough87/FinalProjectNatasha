package com.skilldistillery.paseo.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WalkTypeTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	
	private WalkType walkType;
	
	
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
		walkType = em.find(WalkType.class, 1);
		
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		walkType = null;
	}

	@Test
	void test() {
		
//		+----+---------------+------------+
//		| id | name          | descripion |
//		+----+---------------+------------+
//		|  1 | stroll        | NULL       |
//		|  2 | dog_walking   | NULL       |
//		|  3 | speed_walking | NULL       |
//		+----+---------------+------------+
		
		assertNotNull(walkType);
		assertEquals("stroll", walkType.getName());
	}
	}

