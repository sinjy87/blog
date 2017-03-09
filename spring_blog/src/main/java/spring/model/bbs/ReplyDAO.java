package spring.model.bbs;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.model.IReplyDAO;

@Repository
public class ReplyDAO implements IReplyDAO{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public int rcount(int bbsno){
	    return sqlSessionTemplate.selectOne("reply.rcount", bbsno);
	}

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	public boolean create(ReplyDTO dto) {
		boolean flag = false;

		int cnt = (Integer) sqlSessionTemplate.insert("reply.create", dto);
		if (cnt > 0)
			flag = true;

		return flag;
	}

	public ReplyDTO read(int rnum) {

		return (ReplyDTO) sqlSessionTemplate.selectOne("reply.read", rnum);

	}

	public boolean update(ReplyDTO dto) {
		boolean flag = false;

		int cnt = sqlSessionTemplate.update("reply.update", dto);
		if (cnt > 0)
			flag = true;

		return flag;
	}

	public List<ReplyDTO> list(Map map) {

		return sqlSessionTemplate.selectList("reply.list", map);
	}

	public int total(int bbsno) {
		return (Integer) sqlSessionTemplate.selectOne("reply.total", bbsno);
	}

	public boolean delete(int rnum) {
		boolean flag = false;
		int cnt = sqlSessionTemplate.delete("reply.delete", rnum);
		if (cnt > 0)
			flag = true;

		return flag;
	}

	/** 하나의 글의 여러댓글들 삭제 */
	public int bdelete(int bbsno) throws Exception {
		return sqlSessionTemplate.delete("reply.bdelete", bbsno);

	}
}