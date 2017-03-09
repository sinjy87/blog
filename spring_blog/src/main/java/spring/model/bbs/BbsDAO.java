package spring.model.bbs;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



@Repository
public class BbsDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	/**
	 * 부모글인지 확인
	 * 부모글이면 삭제 못함
	 * @param Bbsno 삭제하려고하는 글번호
	 * @return true 부모글번호,  false부모글번호 아님.
	 */
	public boolean checkRefno(int bbsno){
		boolean flag = false;
		int cnt = sqlSession.selectOne("bbs.checkRefno", bbsno);
		if(cnt > 0)flag = true;
		return flag;
	}
	public int total(String col, String word){
		
		Map map = new HashMap();
		map.put("col", col);
		map.put("word", word);
		
		return sqlSession.selectOne("bbs.total", map);
		
	}
	
	public void upAnsnum(Map map) {
		sqlSession.update("bbs.upAnsnum", map);
	}

	public boolean createReply(BbsDTO dto) {
		boolean flag = false;
		int cnt = sqlSession.insert("bbs.createReply", dto);
		if(cnt >0)flag = true;
		return flag;
	}

	public boolean delete(int bbsno) {
		boolean flag = false;
		int cnt = sqlSession.delete("bbs.delete", bbsno);
		if(cnt >0)flag =true;
		return flag;
	}

	public boolean update(BbsDTO dto) {
		boolean flag = false;
		int cnt = sqlSession.update("bbs.update", dto);
		if(cnt > 0)flag = true;
		return flag;
	}

	public boolean passCheck(Map map) {
		boolean flag = false;
		int cnt = sqlSession.selectOne("bbs.passCheck",map);
		if(cnt >0)flag = true;
		return flag;
	}

	public BbsDTO readReply(int bbsno) {
	
		return sqlSession.selectOne("bbs.readReply", bbsno);
	}

	public BbsDTO read(int bbsno) {// ?씫湲곗슜 由щ뱶
		
		return sqlSession.selectOne("bbs.read", bbsno);

	}

	public void upViewcnt(int bbsno) {
		sqlSession.update("bbs.upViewcnt", bbsno);
	}

	public List<BbsDTO> list(Map map) {
		
		return sqlSession.selectList("bbs.list", map);
	}

	public boolean create(BbsDTO dto) {
		boolean flag = false;

		int cnt = sqlSession.insert("bbs.create",dto);
		if(cnt > 0)flag = true;

		return flag;
	}

}
