package com.skilldistillery.paseo.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WalkTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	
	private Walk walk;

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
		walk = em.find(Walk.class, 1);
		
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		walk = null;
	}

	@Test
	void test() {
		
		
//		+----+----------------+------------+----------------------------+------------------+--------------+-------------+---------+---------+------------+----------+----------------+---------+
//		| id | name           | date       | description                | walk_category_id | walk_type_id | location_id | user_id | privacy | start_time | end_time | main_image_url | enabled |
//		+----+----------------+------------+----------------------------+------------------+--------------+-------------+---------+---------+------------+----------+----------------+---------+
//		|  1 | Fun in the sun | 2023-01-12 | This was an amazing walk!  |                1 |            1 |           1 |       1 |       1 | NULL       | NULL     | NULL           |    NULL |
//		+----+----------------+------------+----------------------------+------------------+--------------+-------------+---------+---------+------------+----------+----------------+---------+

		assertNotNull(walk);
		assertEquals("Fun in the sun", walk.getName());
	}

}
