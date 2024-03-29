package kr.or.ddit.comm.dao;

import java.text.ParseException;
import java.util.Collections;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.util.MyBatisUtil;

public class MyBatisDao {

	public <T> T selectOne(String statement) {
		SqlSession sqlSession = MyBatisUtil.getInstance(true); // 세션열기 (오토커밋)/셀렉트라 오토커밋여부가 상관없엉

		T obj = null;

		try {
			// 실제작업영역임

			obj = sqlSession.selectOne(statement);

		} catch (PersistenceException e) {
			throw new RuntimeException("마이바티스에서 데이터 조회 중 예외발생!!!", e);
		} finally {
			sqlSession.close();
		}

		return obj;

	}

	public <T> T selectOne(String statement, Object parameter) {
		SqlSession sqlSession = MyBatisUtil.getInstance(true); // 세션열기 (오토커밋)

		T obj = null;

		try {
			// 실제작업영역임

			obj = sqlSession.selectOne(statement, parameter);

		} catch (PersistenceException e) {
			throw new RuntimeException("마이바티스에서 데이터 조회 중 예외발생!!!", e);
		} finally {
			sqlSession.close();
		}

		return obj;

	}

	public <T> List<T> selectList(String statement) {

		SqlSession sqlSession = MyBatisUtil.getInstance();

		List<T> list = Collections.EMPTY_LIST; // 널포인터익셉션발생하지 않음

		try {
			list = sqlSession.selectList(statement);

		} catch (PersistenceException e) {
			throw new RuntimeException("마이바티스에서 목록 조회 중 예외 발생!!!", e);

		} finally {
			sqlSession.close();
		}
		return list;

	}

	public <T> List<T> selectList(String statement, Object parameter) {

		SqlSession sqlSession = MyBatisUtil.getInstance();

		List<T> list = Collections.EMPTY_LIST; // 널포인터익셉션발생하지 않음

		try {
			list = sqlSession.selectList(statement, parameter);

		} catch (PersistenceException e) {
			throw new RuntimeException("마이바티스에서 목록 조회 중 예외 발생!!!", e);

		} finally {
			sqlSession.close();
		}
		return list;

	}

	public int insert(String statement, Object parameter) {

		SqlSession sqlSession = MyBatisUtil.getInstance();

		int cnt = 0;

		try {

			cnt = sqlSession.insert(statement, parameter);

			sqlSession.commit(); // 커밋 하는 것이 중요! 롤백은 끄는시점에 자동으로 됨

		} catch (PersistenceException e) {
			sqlSession.rollback();
			throw new RuntimeException("마이바티스에서 데이터 등록중 예외 발생!!!", e);
		} finally {
			sqlSession.close();
		}

		return cnt;

	}
	
	public int update(String statement, Object parameter) {

		SqlSession sqlSession = MyBatisUtil.getInstance();

		int cnt = 0;

		try {

			cnt = sqlSession.update(statement, parameter);

			sqlSession.commit(); // 커밋 하는 것이 중요! 롤백은 끄는시점에 자동으로 됨

		} catch (PersistenceException e) {
			sqlSession.rollback();
			throw new RuntimeException("마이바티스에서 데이터 수정중 예외 발생!!!", e);
		} finally {
			sqlSession.close();
		}

		return cnt;

	}

	public int delete(String statement, Object parameter) {

		SqlSession sqlSession = MyBatisUtil.getInstance();

		int cnt = 0;

		try {

			cnt = sqlSession.delete(statement, parameter);

			sqlSession.commit(); // 커밋 하는 것이 중요! 롤백은 끄는시점에 자동으로 됨

		} catch (PersistenceException e) {
			sqlSession.rollback();
			throw new RuntimeException("마이바티스에서 데이터 삭제중 예외 발생!!!", e);
		} finally {
			sqlSession.close();
		}

		return cnt;

	}
}
