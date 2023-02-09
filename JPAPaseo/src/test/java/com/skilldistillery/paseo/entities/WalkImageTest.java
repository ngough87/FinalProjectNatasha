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

class WalkImageTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	
	private WalkImage walkImage;
	
	
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
		walkImage = em.find(WalkImage.class, 1);
		
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		walkImage = null;
	}

	@Test
	void test() {
		
//		+----+-------------------------------------------------------------------+---------+-------------+---------+
//		| id | image_url                                                         | walk_id | description | user_id |
//		+----+-------------------------------------------------------------------+---------+-------------+---------+
//		|  1 | https://pullmanchamber.com/wp-content/uploads/2015/04/Parks11.jpg |       1 | NULL        |       1 |
//		+----+-------------------------------------------------------------------+---------+-------------+---------+

		assertNotNull(walkImage);
		assertEquals("https://pullmanchamber.com/wp-content/uploads/2015/04/Parks11.jpg", walkImage.getImageUrl());
	}
}
