package spring.model.memo;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.model.IReplyDAO;

@Repository
public class MemoreplyDAO implements IReplyDAO{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public int rcount(int memono){
	    return sqlSessionTemplate.selectOne("memoreply.rcount", memono);
	}

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	public boolean create(MemoreplyDTO dto) {
		boolean flag = false;

		int cnt = (Integer) sqlSessionTemplate.insert("memoreply.create", dto);
		if (cnt > 0)
			flag = true;

		return flag;
	}

	public MemoreplyDTO read(int rnum) {

		return (MemoreplyDTO) sqlSessionTemplate.selectOne("memoreply.read", rnum);

	}

	public boolean update(MemoreplyDTO dto) {
		boolean flag = false;

		int cnt = sqlSessionTemplate.update("memoreply.update", dto);
		if (cnt > 0)
			flag = true;

		return flag;
	}

	public List<MemoreplyDTO> list(Map map) {

		return sqlSessionTemplate.selectList("memoreply.list", map);
	}

	public int total(int memono) {
		return (Integer) sqlSessionTemplate.selectOne("memoreply.total", memono);
	}

	public boolean delete(int rnum) {
		boolean flag = false;
		int cnt = sqlSessionTemplate.delete("memoreply.delete", rnum);
		if (cnt > 0)
			flag = true;

		return flag;
	}

	/** 하나의 글의 여러댓글들 삭제 */
	public int bdelete(int memono) throws Exception {
		return sqlSessionTemplate.delete("memoreply.bdelete", memono);

	}
}