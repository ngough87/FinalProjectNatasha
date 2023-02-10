package com.skilldistillery.paseo.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class UserTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private User user;

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
		em = emf.createEntityManager();
		user = em.find(User.class, 1);
				}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		user = null;
	}

	@Test
	void test_user_entity_mappings() {
		assertNotNull(user);
		assertEquals("admin", user.getUsername());
		assertNotNull(user.getGender());
		assertNull(user.getAddress());
	
	}
	
	@Test
	void test_user_many_to_one_mappings () {
		assertNotNull(user);
		assertTrue(user.getPreferredWalkCats().size() > 0);
		assertTrue(user.getPreferredWalkLocations().size() > 0);
		assertTrue(user.getPreferredWalkTypes().size() > 0);
		assertTrue(user.getFollowedUsers().size() > 0);
		assertTrue(user.getJoinedWalks().size() > 0);
		assertTrue(user.getCreatedWalks().size() > 0);
		assertTrue(user.getPreferredGenders().size() > 0);
	}

}
