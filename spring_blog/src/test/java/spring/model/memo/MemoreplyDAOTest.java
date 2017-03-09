package spring.model.memo;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class MemoreplyDAOTest {
	
	private static BeanFactory beans;
	private static MemoreplyDAO dao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Resource resource = new ClassPathResource("blog.xml");
		
		beans = new XmlBeanFactory(resource);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		dao = (MemoreplyDAO)beans.getBean("mrdao");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test 
	public void testRcount() {
		
	 assertEquals(dao.rcount(918), 1);
	}

	@Test @Ignore
	public void testCreate() {
		MemoreplyDTO dto = new MemoreplyDTO();
		dto.setMemono(918);
		dto.setContent("´ñ±Û1");
		dto.setId("user1");
		assertTrue(dao.create(dto));
	}

	@Test @Ignore
	public void testRead() {
		MemoreplyDTO dto = dao.read(6);
		assertNotNull(dto);
//		assertEquals(dto.getMemono(), 918);
//		assertEquals(dto.getId(), "user1");
//		assertEquals(dto.getContent(), "´ñ±Û1");
	}

	@Test @Ignore
	public void testUpdate() {
		MemoreplyDTO dto = new MemoreplyDTO();
		dto.setMemono(918);
		dto.setContent("´ñ±Ûº¯°æ");
		dto.setId("user1");
		dto.setRnum(3);
		
	}
 
	@Test @Ignore
	public void testList() {
		Map map = new HashMap();
		map.put("memono", 918);
		map.put("sno", 1);
		map.put("eno", 5);
		List<MemoreplyDTO> list = dao.list(map);
		assertEquals(list.size(), 5);
	}

	@Test @Ignore
	public void testTotal() {
		int memono = 918;
		int cnt = dao.total(memono);
		assertEquals(cnt, 1);
	}

	@Test @Ignore
	public void testDelete() {
		int rnum = 6;
		assertTrue(dao.delete(rnum));
	}

	@Test @Ignore
	public void testBdelete() throws Exception {
		int memono = 918;
		assertEquals(dao.bdelete(memono), 2);
	}

}
