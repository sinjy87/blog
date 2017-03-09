package spring.model.bbs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class ReplyTest {

	public static void main(String[] args) {
		Resource resource = new ClassPathResource("blog.xml");

		BeanFactory beans = new XmlBeanFactory(resource);

		ReplyDAO dao = (ReplyDAO) beans.getBean("rdao");

		//create(dao);
		// read(dao);
		// update(dao);
		 //list(dao);
		//delete(dao);
		 total(dao);

	}

	private static void total(ReplyDAO dao) {
		int bbsno = 60;
		int total = dao.total(bbsno);
		p("total:" + total);

	}

	private static void delete(ReplyDAO dao) {

		if (dao.delete(1)) {
			p("����");
		} else {
			p("����");
		}

	}

	private static void list(ReplyDAO dao) {
		int sno = 1;
		int eno = 5;
		Map map = new HashMap();
		map.put("sno", sno);
		map.put("eno", eno);
		map.put("bbsno", 60);

		List<ReplyDTO> list = dao.list(map);

		for (int i = 0; i < list.size(); i++) {
			ReplyDTO dto = list.get(i);

			p(dto);
			p("-------------------");
		}

	}

	private static void update(ReplyDAO dao) {
		ReplyDTO dto = dao.read(1);
		dto.setContent("��� ������ �����մϴ�.");

		if (dao.update(dto)) {
			p("����");
			dto = dao.read(1);
			p(dto);
		} else {
			p("����");
		}

	}

	private static void read(ReplyDAO dao) {
		ReplyDTO dto = dao.read(1);
		p(dto);

	}

	private static void p(ReplyDTO dto) {
		p("��ȣ:" + dto.getRnum());
		p("����:" + dto.getContent());
		p("���̵�:" + dto.getId());
		p("�۹�ȣ:" + dto.getBbsno());
		p("��ϳ�¥:" + dto.getRegdate());

	}

	private static void create(ReplyDAO dao) {

		ReplyDTO dto = new ReplyDTO();
		dto.setId("user3");// member table���� id��������
		dto.setContent("��� ���� ����");
		dto.setBbsno(60);// bbs table���� �����ϴ� �۹�ȣ ��������
		if (dao.create(dto)) {
			p("����");
		} else {
			p("����");
		}

	}

	private static void p(String string) {
		System.out.println(string);

	}

}