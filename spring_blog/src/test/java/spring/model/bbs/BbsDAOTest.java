package spring.model.bbs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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

public class BbsDAOTest {
	
	private static BeanFactory beans;
	private static BbsDAO bdao;

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
		
		bdao = (BbsDAO)beans.getBean("bdao");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test @Ignore
	public void testCheckRefno() {
		int bbsno = 34;
		assertTrue(bdao.checkRefno(bbsno));
	}

	@Test @Ignore
	public void testTotal() {
//		assertEquals(bdao.total("", ""), 35);
		assertEquals(bdao.total("wname", "홍길동"), 1);
	}

	@Test @Ignore
	public void testUpAnsnum() {
		fail("Not yet implemented");
	}

	@Test 
	public void testCreateReply() {
		BbsDTO dto = bdao.readReply(37);
		dto.setWname("답변자");
		dto.setContent("답변내용");
		dto.setPasswd("123");
		dto.setFilename("test03.txt");
		dto.setFilesize(100);
		Map map = new HashMap();
		map.put("grpno", dto.getGrpno());
		map.put("ansnum", dto.getAnsnum());
		bdao.upAnsnum(map);
		assertTrue(bdao.createReply(dto));
	}

	@Test @Ignore
	public void testDelete() {
		int bbsno = 34;
		assertTrue(bdao.delete(bbsno));
	}

	@Test @Ignore
	public void testUpdate() {
		BbsDTO dto = new BbsDTO();
		dto.setBbsno(34);
		dto.setWname("홍길동");
		dto.setTitle("제목변경");
		dto.setContent("내용변경");
		dto.setFilename("test01");
		dto.setFilesize(150);
		
		assertTrue(bdao.update(dto));
	}

	@Test @Ignore
	public void testPassCheck() {
		Map map = new HashMap();
		map.put("bbsno", 34);
		map.put("passwd", "123");
		assertTrue(bdao.passCheck(map));
	}

	@Test @Ignore
	public void testReadReply() {
		BbsDTO dto = bdao.readReply(35);
		assertEquals(dto.getGrpno(), 24);
		assertEquals(dto.getIndent(), 1);
		assertEquals(dto.getAnsnum(), 1);
		assertEquals(dto.getTitle(), "123");
	}

	@Test @Ignore
	public void testRead() {
		bdao.upViewcnt(34);
		BbsDTO dto = bdao.read(34);
		assertNotNull(dto);
	}

	@Test @Ignore
	public void testUpViewcnt() {
		fail("Not yet implemented");
	}

	@Test @Ignore
	public void testList() {
		Map map = new HashMap();
		map.put("col", "wname");
		map.put("word", "");
		map.put("sno", 1);
		map.put("eno", 5);
		List<BbsDTO> list = bdao.list(map);
		assertEquals(list.size(), 5);

	}

	@Test @Ignore
	public void testCreate() {
		BbsDTO dto = new BbsDTO();
		dto.setWname("홍길동");
		dto.setTitle("글제목");
		dto.setContent("글 내용");
		dto.setPasswd("1234");
		dto.setFilename("test.txt");
		dto.setFilesize(100);
		
		assertTrue(bdao.create(dto));
	}

}
