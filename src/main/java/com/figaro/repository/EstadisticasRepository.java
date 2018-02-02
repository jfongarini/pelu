package com.figaro.repository;

import java.util.Date;
import java.util.List;

import org.hibernate.query.Query;

import com.figaro.model.Turno;

@SuppressWarnings({ "unchecked" })
public class EstadisticasRepository extends AbstractRepository{
	
	private static String QUERY_GET_MOVIMIENTOS = "FROM Turno t WHERE (t.desde BETWEEN ?1 AND ?2)";
	
	public List<Turno> searchBetween(Date from, Date to) {
		String querySql = QUERY_GET_MOVIMIENTOS;	
		Query<Turno> query = getCurrentSession().createQuery(querySql);
	    query.setParameter(1, from);
	    query.setParameter(2, to);
	    return query.getResultList();
	}
}
